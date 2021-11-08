package BenedictoMatthewJmartFA;
import java.util.HashMap;

public class Serializable implements Comparable<Serializable> {

    public final int id;

    private static HashMap<Class<?>, Integer> mapCounter = new HashMap();

    protected Serializable() {

        Integer counter = mapCounter.get(getClass());
        if(counter == null){
            mapCounter.put(getClass(), 0);
        } else {
            mapCounter.put(getClass(), counter + 1);
        }
        this.id = mapCounter.get(getClass());
    }

    public boolean equals(Object a) {
        return (a instanceof Serializable && this.id == ((Serializable) a).id);
    }

    public boolean equals(Serializable a) {
        return (this.id == a.id);
    }

    public static <T> int setClosingId(Class<T> clazz, int id) {
        return mapCounter.put(clazz,id);
    }

    public static <T> int getClosingId(Class<T> clazz) {
        return mapCounter.get(clazz);
    }

    @Override
    public int compareTo(Serializable o) {
        return this.id / o.id;
    }
}
