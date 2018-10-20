package edu.ycp.cs320.cteichmann.persist;

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

}
