package BenedictoMatthewJmartFA;
import java.util.HashMap;

public class Serializable implements Comparable<Serializable> {

    public static final int id = 0;

    private static HashMap<Class<?>, Integer> mapCounter = new HashMap();

    protected Serializable(int id) {

        Integer counter = mapCounter.get(getClass());
        if(counter == null){
            mapCounter.put(getClass(), 1);
        } else {
            mapCounter.put(getClass(), counter + 1);
        }
    }

    public boolean equals(Object a) {
        return (a instanceof Serializable && this.id == ((Serializable) a).id);
    }

    public boolean equals(Serializable a) {
        return (this.id == a.id);
    }

    public static <T> int setClosingId(Class<T> clazz, int i) {
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
