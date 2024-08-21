package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.*;

import java.awt.*;

public class j2dHero extends AbstractHero {
    private j2dContext grCtx;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    private int width;
    private int height;
    private int bulletWidth = Config.getIntProperty("BULLET_WIDTH");
    private int bulletHeight = Config.getIntProperty("BULLET_HEIGHT");


    public j2dHero(MovementComponent movementComponent,MovementSystem movementSystem, j2dContext gr, int width, int height) {
        super(movementComponent,movementSystem, width, height);
        this.grCtx = gr;
        this.movementComponent = movementComponent;
        this.movementSystem = movementSystem;
        this.width = width;
        this.height = height;
    }
    public void draw() {
        Graphics2D g2d = this.grCtx.getG2d();
        int size = this.grCtx.getSize();
        g2d.drawImage(this.grCtx.heroSprite,(int)Math.round((this.movementComponent.x/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.y/1000.0)*grCtx.ScreenHeight),(int)Math.round((this.movementComponent.width/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.height/1000.0)*grCtx.ScreenHeight),null);
        if (this.grCtx.bulletSprite != null) {
            for (CoordinatePoint bullet : movementComponent.bullets) {
                g2d.drawImage(this.grCtx.bulletSprite,(int) Math.round((bullet.x / 1000.0) * grCtx.ScreenWidth),(int) Math.round((bullet.y / 1000.0) * grCtx.ScreenHeight),(int) Math.round((bulletWidth / 1000.0) * grCtx.ScreenWidth),(int) Math.round((bulletHeight / 1000.0) * grCtx.ScreenHeight),null);
            }
        }
    }
}
