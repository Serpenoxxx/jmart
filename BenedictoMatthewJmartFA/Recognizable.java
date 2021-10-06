package BenedictoMatthewJmartFA;

public class Recognizable
{
    public final int id;
    
    public Recognizable (int id)
    {
        this.id = id;
    }

    public boolean equals(Object a)
    {
        return (a instanceof Recognizable && this.id == ((Recognizable)a).id);
    }
    
    public boolean equals(Recognizable a)
    {
        return (this.id == a.id);
    }
}
