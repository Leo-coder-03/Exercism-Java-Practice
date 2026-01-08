import java.util.*;

class School {

    private final Map<Integer, List<String>> gradeMap = new HashMap<>();
    private final Set<String> allStudents = new HashSet<>();

    boolean add(String student, int grade) {
        if (allStudents.contains(student)) {
            return false;
        }
        gradeMap.putIfAbsent(grade, new ArrayList<>());
        gradeMap.get(grade).add(student);
        allStudents.add(student);

        return true;
    }
    List<String> roster() {
        List<String> result = new ArrayList<>();

        gradeMap.keySet().stream()
                .sorted()
                .forEach(g -> {
                    List<String> students = new ArrayList<>(gradeMap.get(g));
                    Collections.sort(students);
                    result.addAll(students);
                });

        return result;
    }
    List<String> grade(int grade) {
        if (!gradeMap.containsKey(grade)) {
            return Collections.emptyList();
        }
        List<String> students = new ArrayList<>(gradeMap.get(grade));
        Collections.sort(students);
        return students;
    }
}
