package edu.ycp.hackathon.greaseThief;

import javafx.scene.paint.Color;
import java.util.List;

public class Tile {
    int id, r, g, b;
    double deltaMatch;
    String colorName, ironContent, Description;
    public Tile(int id, int r, int g, int b){
        this.id = id;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getId(){
        return id;
    }

    public int getR(){ return r; }
    public int getG(){ return g; }
    public int getB(){ return b; }
    public Color getColor(){
        return new Color(this.r/255.0,this.g/255.0,this.b/255.0, 1);
    }

    public void setColorName(String color){
        this.colorName = color;
    }
    public String getColorName(){
        return colorName;
    }
    public void setIronContent(String ironContent){
        this.ironContent = ironContent;
    }
    public String getIronContent(){
        return ironContent;
    }
    public void setDescription(String Description){
        this.Description = Description;
    }
    public String getDescription(){
        return Description;
    }

    public Tile compareTile(List<Tile> tileSet){

        Tile closestTile = tileSet.get(0);
        double lowestDelta = 100.0f;

        for(int i = 0; i < tileSet.size(); i++){

            Tile curTile = tileSet.get(i);
            float deltaR = this.getR() - curTile.getR();
            float deltaG = this.getG() - curTile.getG();
            float deltaB = this.getB() - curTile.getB();

            double delta = Math.sqrt(Math.pow(deltaR, 2) + Math.pow(deltaG, 2) + Math.pow(deltaB, 2));
            if(delta < lowestDelta){
                closestTile = curTile;
                lowestDelta = delta;
            }
        }
        deltaMatch = lowestDelta;
        return closestTile;
    }

    public double getDeltaMatch(){
        return deltaMatch;
    }
}
