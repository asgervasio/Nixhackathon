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
            System.out.println("Exception Caught in ReadSampleSwatch Method");
        }
        return sampleTile;
    }

    public List<Tile> loadStandards(){
        List<Tile> tileList = new ArrayList<>();

        try{
            List<String> tileInfo;
            ReadTileCSV tileCSV = new ReadTileCSV("Grease.csv");
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
            System.out.println("Exception Caught in ReadSampleSwatch Method");
        }
        return tileList;
    }

    public List<Tile> loadGreaseStandards(){
        List<Tile> tileList = new ArrayList<>();

        try{
            List<Tile> greaseList = new ArrayList<>();
            List<String> greaseInfo = new ArrayList<>();
            ReadGreaseCSV greaseCSV = new ReadGreaseCSV("Grease.csv");
            for(int count = 0; count < 6; count++){
                greaseInfo = greaseCSV.next();
                List<String> splitString = Arrays.asList(greaseInfo.get(0).split(","));
                int id = Integer.parseInt(splitString.get(0).replaceAll("[\\D]",""));
                int r = Integer.parseInt(splitString.get(1).replaceAll("[\\D]",""));
                int g = Integer.parseInt(splitString.get(2).replaceAll("[\\D]",""));
                int b = Integer.parseInt(splitString.get(3).replaceAll("[\\D]",""));
                String colorName = splitString.get(4);
                String IronContent = splitString.get(5);
                String Description = splitString.get(6);

                Tile newTile = new Tile(id, r ,g, b);
                tileList.add(newTile);
            }
        }
        catch(Exception e){

        }

        return tileList;
    }




    public static void main(String args[]){
        try {
            ReadCSV rcsv = new ReadCSV("ye_colordata.csv");
            rcsv.next();
            List<String> returnString = rcsv.next();
            int red = Integer.parseInt(returnString.get(12).replaceAll("[\\D]",""));
            int green = Integer.parseInt(returnString.get(13).replaceAll("[\\D]",""));
            int blue = Integer.parseInt(returnString.get(14).replaceAll("[\\D]",""));

            Tile sampleTile = new Tile(-1,red, green, blue);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("NIX Sample Data");
            System.out.println("R:"+sampleTile.getR()+ " G:"+sampleTile.getG()+ " B:"+sampleTile.getB());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            List<Tile> tileList = new ArrayList<>();
            List<String> tileInfo = new ArrayList<>();
            /*
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
            */
            List<Tile> greaseList = new ArrayList<>();
            List<String> greaseInfo = new ArrayList<>();
            ReadGreaseCSV greaseCSV = new ReadGreaseCSV("Grease.csv");
            for(int count = 0; count < 6; count++){
                greaseInfo = greaseCSV.next();
                List<String> splitString = Arrays.asList(greaseInfo.get(0).split(","));
                int id = Integer.parseInt(splitString.get(0).replaceAll("[\\D]",""));
                int r = Integer.parseInt(splitString.get(1).replaceAll("[\\D]",""));
                int g = Integer.parseInt(splitString.get(2).replaceAll("[\\D]",""));
                int b = Integer.parseInt(splitString.get(3).replaceAll("[\\D]",""));
                String colorName = splitString.get(4);
                String IronContent = splitString.get(5);
                String Description = splitString.get(6);


                Tile newTile = new Tile(id, r ,g, b);
                newTile.setColorName(colorName);
                newTile.setIronContent(IronContent);
                newTile.setDescription(Description);

                tileList.add(newTile);
                /*  //TESTING ALL PARAMETERS OF STANDARD TILES
                System.out.println("ID: "+id);
                System.out.println("R: "+r+",G: "+g+",B: "+b);
                System.out.println(colorName);
                System.out.println(IronContent);
                System.out.println(Description);
                System.out.println("***************************************************************************************************************");
            */
            }
            Tile returnTile = sampleTile.compareTile(tileList);

            System.out.println("#################################################");
            System.out.println("Closest Tile");
            System.out.println("R:"+returnTile.getR()+ " G:"+returnTile.getG()+ " B:"+returnTile.getB());
            System.out.println("Color: "+returnTile.getColorName());
            System.out.println("Iron Content: "+ returnTile.getIronContent());
            System.out.println("NOTICE: "+ returnTile.getDescription());
            System.out.println("#################################################");
        }
        catch (Exception e){
            System.out.println("Exception Caught in ReadSampleSwatch Method");
        }
    }
}
