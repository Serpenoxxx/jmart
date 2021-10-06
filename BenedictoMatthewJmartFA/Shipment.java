package BenedictoMatthewJmartFA;
import java.util.EnumSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Shipment implements FileParser {
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    
    @Override
    public boolean read(String content) {
        return false;
    }
    
    class MultiDuration {
        public byte bit;
        
        public MultiDuration(Duration... args) {
        
            for (Duration md : args) {
                 this.bit = (byte) (this.bit | md.bit);    
             }
         }
        
        public boolean isDuration(Duration duration) {
            if ((this.bit & (1 << (duration.bit - 1))) >= 0) {
                 return true;
           }
            return false;
         }
        }

    static class Duration {      
        public static final Duration INSTANT = new Duration((byte)00000001);
        public static final Duration SAME_DAY = new Duration((byte)00000010);
        public static final Duration NEXT_DAY = new Duration((byte)00000100);
        public static final Duration REGULER = new Duration((byte)00001000);
        public static final Duration KARGO = new Duration((byte)00010000);
        public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EEE MMM dd yyyy");
        Calendar cal = Calendar.getInstance();
        
        public byte bit;
    
        private Duration(byte bit){
            this.bit = bit;
            }
        
    
    public String getEstimatedArrival(Date reference){
        Calendar cal = Calendar.getInstance();
        String format = ESTIMATION_FORMAT.format(cal.getTime());
        if(this.bit == (byte)00000001 || this.bit == (byte)00000010){
            return format;
        } else if (this.bit == (byte)000001000){
            cal.add(Calendar.DATE, 1);
            return format;
        } else if (this.bit == (byte)000010000){
            cal.add(Calendar.DATE, 2);
            return format;
        } else {
            cal.add(Calendar.DATE, 5);
            return format;
        }
        }
    }
}
