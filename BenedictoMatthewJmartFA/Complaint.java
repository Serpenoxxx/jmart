package BenedictoMatthewJmartFA;

public class Complaint extends Recognizable implements FileParser
{
    public String date = "16-6-16";
    public String desc;
    
    public Complaint(int id, String desc) {
        super(id);
        this.desc = desc;
    }
    
    public boolean read(String content) {
        return false;
    }
}
