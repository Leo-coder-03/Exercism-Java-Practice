import java.util.*;

class BookStore {

    private static final double BOOK_PRICE = 8.0;

    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.0,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

    double calculateBasketCost(List<Integer> books) {

        if (books.isEmpty())
            return 0.0;

        Map<Integer, Integer> counts = new HashMap<>();
        for (int book : books) {
            counts.put(book, counts.getOrDefault(book, 0) + 1);
        }

        List<Integer> groups = new ArrayList<>();

        while (counts.values().stream().anyMatch(c -> c > 0)) {
            int groupSize = 0;
            for (int book = 1; book <= 5; book++) {
                if (counts.getOrDefault(book, 0) > 0) {
                    counts.put(book, counts.get(book) - 1);
                    groupSize++;
                }
            }
            groups.add(groupSize);
        }

        int count5 = Collections.frequency(groups, 5);
        int count3 = Collections.frequency(groups, 3);

        int pairs = Math.min(count5, count3);

        for (int i = 0; i < pairs; i++) {
            groups.remove((Integer) 5);
            groups.remove((Integer) 3);
            groups.add(4);
            groups.add(4);
        }

        double total = 0.0;

        for (int size : groups) {
            double discount = DISCOUNTS.get(size);
            total += size * BOOK_PRICE * (1 - discount);
        }

        return total;
    }
}
