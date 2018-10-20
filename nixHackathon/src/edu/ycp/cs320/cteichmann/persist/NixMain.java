package edu.ycp.cs320.cteichmann.persist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NixMain {

    public Tile readSampleSwatch(String fileName){
        Tile sampleTile = new Tile(-1,0,0,0);
        try {
            ReadCSV rcsv = new ReadCSV(fileName);
            rcsv.next();
            List<String> returnString = rcsv.next();
            int red = Integer.parseInt(returnString.get(12).replaceAll("[\\D]", ""));
            int green = Integer.parseInt(returnString.get(13).replaceAll("[\\D]", ""));
            int blue = Integer.parseInt(returnString.get(14).replaceAll("[\\D]", ""));

            sampleTile = new Tile(-1, red, green, blue);
        }
        catch(Exception e){

        }
        return sampleTile;
    }

    public List<Tile> loadStandards(){
        List<Tile> tileList = new ArrayList<>();

        try{
            List<String> tileInfo;
            ReadTileCSV tileCSV = new ReadTileCSV("Tiles.csv");
            for(int count = 0; count < 40; count++){
                tileInfo = tileCSV.next();
                List<String> splitString = Arrays.asList(tileInfo.get(0).split(","));
                int id = Integer.parseInt(splitString.get(0).replaceAll("[\\D]",""));
                int r = Integer.parseInt(splitString.get(1).replaceAll("[\\D]",""));
                int g = Integer.parseInt(splitString.get(2).replaceAll("[\\D]",""));
                int b = Integer.parseInt(splitString.get(3).replaceAll("[\\D]",""));

                Tile newTile = new Tile(id, r ,g, b);

                tileList.add(newTile);
            }
        }
        catch(Exception e){

        }

        return tileList;
    }


    public static void main(String args[]){
        List<String> oString = new ArrayList<>();
        try {

            ReadCSV rcsv = new ReadCSV("ye_colordata.csv");
            rcsv.next();
            List<String> returnString = rcsv.next();
            int red = Integer.parseInt(returnString.get(12).replaceAll("[\\D]",""));
            int green = Integer.parseInt(returnString.get(13).replaceAll("[\\D]",""));
            int blue = Integer.parseInt(returnString.get(14).replaceAll("[\\D]",""));

            Tile sampleTile = new Tile(-1,red, green, blue);


            List<Tile> tileList = new ArrayList<>();
            List<String> tileInfo = new ArrayList<>();
            ReadTileCSV tileCSV = new ReadTileCSV("Tiles.csv");
            for(int count = 0; count < 40; count++){
                tileInfo = tileCSV.next();
                List<String> splitString = Arrays.asList(tileInfo.get(0).split(","));
                int id = Integer.parseInt(splitString.get(0).replaceAll("[\\D]",""));
                int r = Integer.parseInt(splitString.get(1).replaceAll("[\\D]",""));
                int g = Integer.parseInt(splitString.get(2).replaceAll("[\\D]",""));
                int b = Integer.parseInt(splitString.get(3).replaceAll("[\\D]",""));

                Tile newTile = new Tile(id, r ,g, b);

                tileList.add(newTile);
            }

            Tile returnTile = sampleTile.compareTile(tileList);

            System.out.println(returnTile.getId());
            System.out.println(returnTile.getR());
            System.out.println(returnTile.getG());
            System.out.println(returnTile.getB());

        }
        catch (Exception e){
        }
    }


}
