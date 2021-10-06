package BenedictoMatthewJmartFA;
import java.util.EnumSet;

public class ShipmentDuration
{
    public static final ShipmentDuration INSTANT = new ShipmentDuration(00000001);
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration(00000001);
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration(00000001);
    public static final ShipmentDuration REGULER = new ShipmentDuration(00000001);
    public static final ShipmentDuration KARGO = new ShipmentDuration(00000001);
    private int bit;
    
    public ShipmentDuration(int bit){
    this.bit = bit;
    }
    
    public ShipmentDuration(ShipmentDuration... args){
        for(ShipmentDuration i : args){
            this.bit = this.bit | i.bit;
        }
    }
    
    public boolean isDuration(ShipmentDuration reference){
        if((this.bit & (1 << (reference.bit -1))) > 0){
            return true;
        }else{
              return false;  
            }
        }
    }

