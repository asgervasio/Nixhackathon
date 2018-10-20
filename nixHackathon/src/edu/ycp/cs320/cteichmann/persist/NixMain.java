package edu.ycp.cs320.cteichmann.persist;

import java.util.ArrayList;
import java.util.List;

public class NixMain {
    //46 47  48
    public static void main(String args[]){
        List<String> oString = new ArrayList<>();
        try {
            ReadCSV rcsv = new ReadCSV("ye_colordata.csv");
            rcsv.next();
            List<String> returnString = rcsv.next();
            System.out.println(returnString.get(12));
            System.out.println(returnString.get(13));
            System.out.println(returnString.get(14));

        }


        catch (Exception e){

        }
    }

}
