package BenedictoMatthewJmartFA;

public class Recognizable implements Comparable<Recognizable> {
    public final int id;

    protected Recognizable() {
        this.id = 1;
    }

    public boolean equals(Object a) {
        return (a instanceof Recognizable && this.id == ((Recognizable) a).id);
    }

    public boolean equals(Recognizable a) {
        return (this.id == a.id);
    }

    public static <T> int setClosingId(Class<T> clazz, int i) {
        if (clazz.getSuperclass() == Recognizable.class) {
            return 0;
        }
        return i;
    }

    public static <T> int getClosingId(Class<T> clazz) {
        if (clazz.getSuperclass() == Recognizable.class) {
            return 0;
        }
        return -1;
    }

    @Override
    public int compareTo(Recognizable o) {
        return this.id / o.id;
    }
}
