package be.antwerpen.mateo.game.logic;

public class CoordinatePoint {
    public int x;
    public int y;
    private int width;
    private int height;

    public CoordinatePoint(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }


    public void printCord(){
        System.out.println("x: "+x+", y: "+y);
    }

}
