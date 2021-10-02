package BenedictoMatthewJmartFA;
import java.util.Date;


public class Complaint extends Recognizable implements FileParser
{
    public Date date = new Date();
    public String desc;
    
    public Complaint(int id, String desc) {
        super(id);
        this.date = new java.util.Date();
        this.desc = desc;
    }
    
    public boolean read(String content) {
        return false;
    }
}
