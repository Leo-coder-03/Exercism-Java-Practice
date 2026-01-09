import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class RestApi {

    private final Map<String, User> users = new HashMap<>();

    public RestApi(User... initialUsers) {
        for (User user : initialUsers) {
            users.put(user.name(), copyUser(user));
        }
    }

    public String get(String url) {
        return get(url, null);
    }

    public String get(String url, JSONObject payload) {
        if ("/users".equals(url)) {
            List<User> result;
            if (payload != null && payload.has("users")) {
                JSONArray names = payload.getJSONArray("users");
                result = new ArrayList<>();
                for (int i = 0; i < names.length(); i++) {
                    String name = names.getString(i);
                    if (users.containsKey(name)) result.add(users.get(name));
                }
            } else {
                result = new ArrayList<>(users.values());
            }
            result.sort(Comparator.comparing(User::name));
            JSONArray arr = new JSONArray();
            for (User u : result) arr.put(userToJson(u));
            return new JSONObject().put("users", arr).toString();
        }
        return "{}";
    }

    public String post(String url, JSONObject payload) {
        switch (url) {
            case "/add":
                return addUser(payload.getString("user")).toString();
            case "/iou":
                return addIou(payload.getString("lender"),
                        payload.getString("borrower"),
                        payload.getDouble("amount")).toString();
            default:
                return "{}";
        }
    }

    private JSONObject addUser(String name) {
        if (users.containsKey(name)) return userToJson(users.get(name));
        User newUser = User.builder().setName(name).build();
        users.put(name, newUser);
        return userToJson(newUser);
    }

    private JSONObject addIou(String lenderName, String borrowerName, double amount) {
        User lender = users.get(lenderName);
        User borrower = users.get(borrowerName);

        Map<String, Double> lenderOwes = mapFromIou(lender.owes());
        Map<String, Double> lenderOwedBy = mapFromIou(lender.owedBy());
        Map<String, Double> borrowerOwes = mapFromIou(borrower.owes());
        Map<String, Double> borrowerOwedBy = mapFromIou(borrower.owedBy());
        if (borrowerOwes.containsKey(lenderName)) {
            double existing = borrowerOwes.get(lenderName);
            if (existing > amount) {
                borrowerOwes.put(lenderName, existing - amount);
                lenderOwedBy.put(borrowerName, existing - amount);
                amount = 0;
            } else if (existing == amount) {
                borrowerOwes.remove(lenderName);
                lenderOwedBy.remove(borrowerName);
                amount = 0;
            } else {
                borrowerOwes.remove(lenderName);
                lenderOwedBy.remove(borrowerName);
                amount -= existing;
            }
        }
        if (lenderOwes.containsKey(borrowerName)) {
            double existing = lenderOwes.get(borrowerName);
            if (existing > amount) {
                lenderOwes.put(borrowerName, existing - amount);
                borrowerOwedBy.put(lenderName, existing - amount);
                amount = 0;
            } else if (existing == amount) {
                lenderOwes.remove(borrowerName);
                borrowerOwedBy.remove(lenderName);
                amount = 0;
            } else {
                lenderOwes.remove(borrowerName);
                borrowerOwedBy.remove(lenderName);
                amount -= existing;
            }
        }
        if (amount > 0) {
            lenderOwedBy.put(borrowerName, lenderOwedBy.getOrDefault(borrowerName, 0.0) + amount);
            borrowerOwes.put(lenderName, borrowerOwes.getOrDefault(lenderName, 0.0) + amount);
        }

        User newLender = rebuildUser(lenderName, lenderOwes, lenderOwedBy);
        User newBorrower = rebuildUser(borrowerName, borrowerOwes, borrowerOwedBy);

        users.put(lenderName, newLender);
        users.put(borrowerName, newBorrower);

        List<User> updatedUsers = Arrays.asList(newLender, newBorrower);
        updatedUsers.sort(Comparator.comparing(User::name));
        JSONArray arr = new JSONArray();
        for (User u : updatedUsers) arr.put(userToJson(u));
        return new JSONObject().put("users", arr);
    }

    private User rebuildUser(String name, Map<String, Double> owesMap, Map<String, Double> owedByMap) {
        User.Builder builder = User.builder().setName(name);
        owesMap.forEach(builder::owes);
        owedByMap.forEach(builder::owedBy);
        return builder.build();
    }

    private Map<String, Double> mapFromIou(List<Iou> ious) {
        Map<String, Double> map = new HashMap<>();
        for (Iou iou : ious) map.put(iou.name, iou.amount);
        return map;
    }

    private JSONObject userToJson(User u) {
        JSONObject owesJson = new JSONObject();
        JSONObject owedByJson = new JSONObject();
        double owesTotal = 0.0;
        double owedByTotal = 0.0;

        for (Iou i : u.owes()) {
            owesJson.put(i.name, i.amount);
            owesTotal += i.amount;
        }
        for (Iou i : u.owedBy()) {
            owedByJson.put(i.name, i.amount);
            owedByTotal += i.amount;
        }
        double balance = owedByTotal - owesTotal;

        return new JSONObject()
                .put("name", u.name())
                .put("owes", owesJson)
                .put("owedBy", owedByJson)
                .put("balance", balance);
    }

    private User copyUser(User u) {
        User.Builder builder = User.builder().setName(u.name());
        for (Iou iou : u.owes()) builder.owes(iou.name, iou.amount);
        for (Iou iou : u.owedBy()) builder.owedBy(iou.name, iou.amount);
        return builder.build();
    }
}
