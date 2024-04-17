package be.antwerpen.mateo.game.logic;

public class MovementComponent {
    public float x, y;
    public float vx, vy; // richting & snelheid
    public MovementComponent(float x,float y,float vx,float vy){
      this.x = x;
      this.y = y;
      this.vx = vx;
      this.vy = vy;
    }
}
