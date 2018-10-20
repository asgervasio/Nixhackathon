package edu.ycp.cs320.cteichmann.persist;

import java.util.ArrayList;
import java.util.Arrays;
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

            List<Tile> tileList = new ArrayList<>();
            List<String> tileInfo = new ArrayList<>();
            ReadTileCSV tileCSV = new ReadTileCSV("Tiles.csv");
            for(int count = 0; count < 30; count++){
                tileInfo = tileCSV.next();
                System.out.println(tileInfo.get(0));
                List<String> splitString = Arrays.asList(tileInfo.get(0).split(","));

                int id = Integer.parseInt(splitString.get(0));
                int r = Integer.parseInt(splitString.get(1));
                int g = Integer.parseInt(splitString.get(2));
                int b = Integer.parseInt(splitString.get(3));
                Tile newTile = new Tile(id, r ,g, b);

                tileList.add(newTile);
            }

        }
        catch (Exception e){
        }
    }
}
