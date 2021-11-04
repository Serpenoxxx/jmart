package BenedictoMatthewJmartFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;


public class Jmart
{
    class Country
    {
        public String name;
        public int population;
        public List<String> listOfStates;
    }
    public static void main(String[] args) {
        String filepath = "D:\\College\\Sem3\\OOP\\jmart\\src\\BenedictoMatthewJmartFA\\city.json";
        Gson gson = new Gson();
        try
        {
           BufferedReader br = new BufferedReader(new FileReader(filepath));
           Country input = gson.fromJson(br, Country.class);
           System.out.println("name: " + input.name);
            System.out.println("population: " + input.population);
            System.out.println("states: " );
            input.listOfStates.forEach(state -> System.out.println(state));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }  
}