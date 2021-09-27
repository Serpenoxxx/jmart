package BenedictoMatthewJmartFA;
import java.util.EnumSet;

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
        
        public byte bit;
    
        private Duration(byte bit)
        {
            this.bit = bit;
        }
    }
}
