package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractEnemy;
import be.antwerpen.mateo.game.logic.AbstractEntity;
import be.antwerpen.mateo.game.logic.MovementComponent;
import be.antwerpen.mateo.game.logic.MovementSystem;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dEnemy extends AbstractEnemy {
    private j2dContext grCtx;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    private int width;
    private int height;


    public j2dEnemy(MovementComponent movementComponent,MovementSystem movementSystem, j2dContext gr, int width, int height) {
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
        g2d.drawImage(this.grCtx.enemySprite, (int) Math.round((movementComponent.x / 1000.0) * grCtx.ScreenWidth), (int) Math.round((movementComponent.y / 1000.0) * grCtx.ScreenHeight), (int) Math.round((movementComponent.width / 1000.0) * grCtx.ScreenWidth), (int) Math.round((movementComponent.height / 1000.0) * grCtx.ScreenHeight), null);
    }
}
