package com.BenedictoMatthewJmartFA;
import com.BenedictoMatthewJmartFA.dbjson.Serializable;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Contains complaints and their dates.
 *
 * @author Benedicto Matthew W
 */

public class Complaint extends Serializable
{
    public Date date;
    public String desc;

    public Complaint(int id, String desc)
    {

        this.desc = desc;
        this.date = new Date();
    }

    public String toString(){
        String format = "dd/MM/yyyy";
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return "Complaint{date=" + format1.format(this.date) + ",desc=" + "'" + this.desc + "'" + "}";
    }
}
