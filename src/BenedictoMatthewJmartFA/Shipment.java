package BenedictoMatthewJmartFA;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Shipment {

    static class Plan {
        public byte bit;

        private Plan(byte bit) {
            this.bit = bit;
        }
    }

    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EEE MMMM dd yyyy");
    public static final Plan INSTANT = new Plan((byte)00000001);
    public static final Plan SAME_DAY = new Plan((byte)00000010);
    public static final Plan NEXT_DAY = new Plan((byte)00000100);
    public static final Plan REGULER = new Plan((byte)00001000);
    public static final Plan KARGO = new Plan((byte)00010000);

    public String address;
    public int cost;
    public byte plan;
    public String receipt;

    public Shipment(String address, int cost, byte plan, String receipt) {
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }

    public String getEstimatedArrival(Date reference) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(reference);

        if (this.plan == ((byte)00000001) || this.plan == ((byte)00000010)) {
            return ESTIMATION_FORMAT.format(cal.getTime());
        } else if (this.plan == ((byte)00000100)) {
            cal.add(Calendar.DATE, 1);
            return ESTIMATION_FORMAT.format(cal.getTime());
        } else if (this.plan == ((byte)00001000)) {
            cal.add(Calendar.DATE, 2);
            return ESTIMATION_FORMAT.format(cal.getTime());
        } else if (this.plan == ((byte)00010000)) {
            cal.add(Calendar.DATE, 5);
            return ESTIMATION_FORMAT.format(cal.getTime());
        } else {
            return null;
        }
    }

    public boolean isDuration(Plan reference) {
        if ((this.plan & (1 << (reference.bit - 1))) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean isDuration(byte object, Plan reference) {
        if ((object & (1 << (reference.bit - 1))) >= 0) {
            return true;
        }
        return false;
    }
}
