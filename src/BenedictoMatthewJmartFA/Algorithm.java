package BenedictoMatthewJmartFA;
import java.util.*;

public class Algorithm {

    private Algorithm() {

    }

    public static <T> int count(T[] array, T value) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        Predicate<T> predicate = value::equals;
        return count(iterator, predicate);
    }

    public static <T> int count(Iterable<T> iterable, T value) {
        Predicate<T> predicate = value::equals;
        return count(iterable, predicate);
    }

    public static <T> int count(Iterator<T> iterator, T value) {
        Predicate<T> predicate = value::equals;
        return count(iterator, predicate);
    }

    public static <T> int count(T[] array, Predicate<T> predicate) {
        return count(array, predicate);
    }

    public static <T> int count(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> iterator = iterable.iterator();
        return count(iterator, predicate);
    }

    public static <T> int count(Iterator<T> iterator, Predicate<T> predicate) {
        int result = 0;
        while(iterator.hasNext()) {
            if (predicate.predicate(iterator.next())) {
                return result;
            }
        }
        return result;
    }

    public static <T> boolean exists(T[] array, T value) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        Predicate<T> predicate = value::equals;
        return exists(iterator, predicate);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value) {
        Iterator<T> iterator = iterable.iterator();
        Predicate<T> predicate = value::equals;
        return exists(iterator, predicate);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value) {
        Predicate<T> predicate = value::equals;
        return exists(iterator, predicate);
    }

    public static <T> boolean exists(T[] array, Predicate<T> predicate) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        return exists(iterator, predicate);
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> iterator = iterable.iterator();
        return exists(iterator, predicate);
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> predicate) {
        T required = iterator.next();
        while (iterator.hasNext()) {
            if (predicate.predicate(required)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T find(T[] array, T value) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        Predicate<T> predicate = value::equals;
        return find(iterator, predicate);
    }

    public static <T> T find(Iterable<T> iterable, T value) {
        Predicate<T> predicate = value::equals;
        Iterator<T> iterator = iterable.iterator();
        return find(iterator, predicate);
    }

    public static <T> T find(Iterator<T> iterator, T value) {
        Predicate<T> predicate = value::equals;
        return find(iterator, predicate);
    }

    public static <T> T find(T[] array, Predicate<T> predicate) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        return find(iterator, predicate);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> iterator = iterable.iterator();

        return find(iterator, predicate);
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> predicate) {
        while(iterator.hasNext()) {
            T e = iterator.next();
            if (predicate.predicate(e)) {
                return e;
            }
        }
        return null;
    }

    public static <T extends Comparable<? super T>> T max(T first, T second) {
        T max = first;
        if (second.compareTo(max) > 0) {
            max = second;
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T min(T first, T second) {
        T min = first;
        if (second.compareTo(min) < 0) {
            min = second;
        }
        return min;
    }
}
