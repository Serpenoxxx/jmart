package BenedictoMatthewJmartFA;


public interface FileParser
{
    public boolean read(String content);
    public default Object write(){
    return null;}
    public static Object newInstance(String content){
    return null;
    }
}
