package BenedictoMatthewJmartFA;
import java.util.*;

public class Algorithm {

    private Algorithm() {

    }
    public static <T> List<T> collect(T[] array, T value) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }

    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        Predicate<T> predicate = value::equals;
        return collect(iterable, predicate);
    }

    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Predicate<T> predicate = value::equals;
        return collect(iterator, predicate);
    }

    public static <T> List<T> collect(T[] array, Predicate<T> predicate) {
        return collect(array, predicate);
    }

    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> predicate) {
        List<T> collectList = new ArrayList<T>();
        Iterator<T> iterator = iterable.iterator();
        return collect(iterator, predicate);
    }

    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> predicate) {
        List<T> collectList = new ArrayList<T>();
        while (iterator.hasNext()) {
            T required = iterator.next();
            if (predicate.predicate(required)) {
                collectList.add(required);
            }
        }
        return collectList;
    }
    //
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

    public static <T extends Comparable<? super T>> T max(T[] array) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T max = iterator.next();
        while(iterator.hasNext()) {
            T next = iterator.next();
            if (max.compareTo(next) > 0) {
                max = next;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        return max(iterator);
    }

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator) {
        T max = iterator.next();
        while(iterator.hasNext()) {
            T next = iterator.next();
            if (max.compareTo(next) > 0) {
                max = next;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator) {
        Iterator<T> iterator = iterable.iterator();
        return max(iterator, comparator);
    }

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
        T max = iterator.next();
        while(iterator.hasNext()) {
            T next = iterator.next();
            if (comparator.compare(max, next) > 0) {
                max = next;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T min(T[] array) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        T min = iterator.next();
        while(iterator.hasNext()) {
            T next = iterator.next();
            if (next.compareTo(min) < 0) {
                min = next;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        return min(iterator, comparator);
    }

    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        return min(iterator);
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator) {
        T min = iterator.next();
        while(iterator.hasNext()) {
            T next = iterator.next();
            if (min.compareTo(next) > 0) {
                min = next;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator) {
        Iterator<T> iterator = iterable.iterator();
        return min(iterator, comparator);
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
        T min = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (comparator.compare(min, next) < 0) {
                min = next;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T max(T first, T second) {
        T max = first;
        if (second.compareTo(max) > 0) {
            max = second;
        }
        return max;
    }

    public static<T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator) {
        T max = first;
        if (comparator.compare(second, first) > 0) {
            max = second;
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator) {
        Iterator<T> iterator = Arrays.stream(array).iterator();
        return max(iterator, comparator);
    }

    public static <T extends Comparable<? super T>> T min(T first, T second) {
        T min = first;
        if (second.compareTo(min) < 0) {
            min = second;
        }
        return min;
    }

    public static<T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator) {
        T min = first;
        if (comparator.compare(second, first) < 0) {
            min = second;
        }
        return min;
    }
}
