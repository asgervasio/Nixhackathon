package edu.ycp.cs320.cteichmann.persist;

import java.util.List;

public class Tile {
    int id, r, g, b;
    public Tile(int id, int r, int g, int b){
        this.id = id;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getId(){
        return id;
    }

    public int getR(){
        return r;
    }

    public int getG(){
        return g;
    }

    public int getB(){
        return b;
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
        return closestTile;
    }

}
