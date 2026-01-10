import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class ListOps {

    static <T> List<T> append(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>();

        for (T el : list1) {
            result.add(el);
        }
        for (T el : list2) {
            result.add(el);
        }
        return result;
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        List<T> result = new ArrayList<>();

        for (List<T> list : listOfLists) {
            for (T el : list) {
                result.add(el);
            }
        }
        return result;
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (T el : list) {
            if (predicate.test(el)) {
                result.add(el);
            }
        }
        return result;
    }

    static <T> int size(List<T> list) {
        int count = 0;
        for (T ignored : list) {
            count++;
        }
        return count;
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
        List<U> result = new ArrayList<>();

        for (T el : list) {
            result.add(transform.apply(el));
        }
        return result;
    }

    static <T> List<T> reverse(List<T> list) {
        List<T> result = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        U acc = initial;

        for (T el : list) {
            acc = f.apply(acc, el); 
        }
        return acc;
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        U acc = initial;

        for (int i = list.size() - 1; i >= 0; i--) {
            acc = f.apply(list.get(i), acc); 
        }
        return acc;
    }

    private ListOps() {
    }
}
