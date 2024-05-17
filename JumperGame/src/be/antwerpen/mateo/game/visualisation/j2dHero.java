package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractContext;
import be.antwerpen.mateo.game.logic.AbstractHero;
import be.antwerpen.mateo.game.logic.MovementComponent;
import be.antwerpen.mateo.game.logic.MovementSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dHero extends AbstractHero {
    private j2dContext grCtx;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    private int width;
    private int height;


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
        //g2d.setColor(new Color(230,0,0));
        //g2d.fillRect(Math.round(movementComponent.x)*size,Math.round(movementComponent.y)*size,size,2*size);
        //System.out.println((int)Math.round((this.width/1000.0)*grCtx.ScreenWidth));
        //System.out.println((int)Math.round((this.height/1000.0)*grCtx.ScreenHeight));
        //g2d.fillRect((int)Math.round((this.movementComponent.x/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.y/1000.0)*grCtx.ScreenHeight),(int)Math.round((this.width/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.height/1000.0)*grCtx.ScreenHeight));
        //System.out.println((int)Math.round((this.movementComponent.x/1000.0)*grCtx.ScreenWidth));
        //System.out.println((int)Math.round((this.movementComponent.y/1000.0)*grCtx.ScreenHeight));
        //System.out.println((int)Math.round((this.width/1000.0)*grCtx.ScreenWidth));
        //System.out.println((int)Math.round((this.height/1000.0)*grCtx.ScreenHeight));
        //g2d.drawImage(grCtx.heroSprite.getSubimage(1,1,grCtx.heroSprite.getWidth(),grCtx.heroSprite.getHeight()),(int)Math.round((this.movementComponent.x/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.y/1000.0)*grCtx.ScreenHeight),null);
        g2d.drawImage(this.grCtx.heroSprite,(int)Math.round((this.movementComponent.x/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.y/1000.0)*grCtx.ScreenHeight),(int)Math.round((this.movementComponent.width/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.height/1000.0)*grCtx.ScreenHeight),null);
        //g2d.drawImage(grCtx.heroSprite.getSubimage((int)Math.round((this.movementComponent.x/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.y/1000.0)*grCtx.ScreenHeight),grCtx.heroSprite.getWidth(),grCtx.heroSprite.getHeight()),(int)Math.round((this.movementComponent.x/1000.0)*grCtx.ScreenWidth),(int)Math.round((this.movementComponent.y/1000.0)*grCtx.ScreenHeight),null);

        //g2d.drawImage(grCtx.heroSprite.getSubimage());


//        int screenWidth = this.grCtx.ScreenWidth;
//        int screenHeight = this.grCtx.ScreenHeight;
//
//        // Calculate positions and sizes based on the sprite dimensions and screen dimensions.
//        int x = (int) Math.round((this.movementComponent.x / 1000.0) * screenWidth);
//        int y = (int) Math.round((this.movementComponent.y / 1000.0) * screenHeight);
//        int width = (int) Math.round((this.width / 1000.0) * screenWidth);
//        int height = (int) Math.round((this.height / 1000.0) * screenHeight);
//
//        // Clamp the values to the dimensions of the hero sprite to prevent out-of-bound errors.
//        System.out.println(grCtx.heroSprite.getWidth());
//        x = Math.min(x, grCtx.heroSprite.getWidth() - 1);
//        y = Math.min(y, grCtx.heroSprite.getHeight() - 1);
//        width = Math.min(width, grCtx.heroSprite.getWidth() - x);
//        height = Math.min(height, grCtx.heroSprite.getHeight() - y);
//
//        if (width <= 0 || height <= 0) {
//            System.out.println("Invalid width or height for subimage.");
//            return;
//        }
//
//        // Get the subimage based on the clamped and validated dimensions.
//        BufferedImage subImage = grCtx.heroSprite.getSubimage(x, y, width, height);
//        g2d.drawImage(subImage, x, y, null);
    }
}
