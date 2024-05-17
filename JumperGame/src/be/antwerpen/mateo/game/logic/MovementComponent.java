package be.antwerpen.mateo.game.logic;

import java.util.List;

public class MovementComponent {
    public float x = 500;
    public float y = 875;
    public int width;
    public int height;
    public float vx, vy; // richting & snelheid
    public List<List<CoordinatePoint>> cordList;
    public MovementComponent(float x,float y,float vx,float vy,List<List<CoordinatePoint>> cordList, int width, int height){
      this.x = x;
      this.y = y;
      this.vx = vx;
      this.vy = vy;
      this.cordList = cordList;
      this.width = width;
      this.height = height;
    }
}
