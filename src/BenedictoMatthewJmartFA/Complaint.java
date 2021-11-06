package BenedictoMatthewJmartFA;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint
{
    public String desc;
    public Date date;

    public Complaint(int id, String desc)
    {
        this.desc = desc;
        this.date = new Date();
    }
    
    public String toString() {
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return "Complaint{date=" + sdf.format(this.date) + ",desc=" + "'" + this.desc + "'" + "}";
    }
    
}
