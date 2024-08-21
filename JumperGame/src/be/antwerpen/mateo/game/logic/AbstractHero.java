package be.antwerpen.mateo.game.logic;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHero extends AbstractEntity {
    // ik gebruik hier extends omdat we werken met overerving van classen
    private int width;
    private int height;
    private List<CoordinatePoint> bullets;
    private int bulletWidth = Config.getIntProperty("BULLET_WIDTH");
    private int bulletHeight = Config.getIntProperty("BULLET_HEIGHT");
    public AbstractHero(MovementComponent movementComponent, MovementSystem movementSystem,int width, int height) {
        super(movementComponent, movementSystem);
        this.width = width;
        this.height = height;
        this.bullets = new ArrayList<>();
    }

    public void shoot(){
        CoordinatePoint bullet = new CoordinatePoint((int)this.movementComponent.x + ((int)(bulletWidth/2.0)),(int)this.movementComponent.y + 10,bulletWidth,bulletHeight);
        movementComponent.bullets.add(bullet);
    }
    public int getBulletHeight(){
        return bulletHeight;
    }

    public int getBulletWidth(){
        return bulletWidth;
    }

    abstract public void draw();
}
