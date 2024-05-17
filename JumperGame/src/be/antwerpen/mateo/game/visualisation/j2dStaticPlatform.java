package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractStaticPlatform;
import be.antwerpen.mateo.game.logic.CoordinatePoint;
import be.antwerpen.mateo.game.logic.MovementComponent;
import be.antwerpen.mateo.game.logic.MovementSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class j2dStaticPlatform extends AbstractStaticPlatform {
    private int width;
    private int height;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    private j2dContext grCtx;
    private List<List<CoordinatePoint>> cordList;
    public j2dStaticPlatform(MovementComponent movementComponent, MovementSystem movementSystem, j2dContext gr, int width, int heigth){
        super(movementComponent, movementSystem, width, heigth);
        this.width = width;
        this.height = heigth;
        this.grCtx = gr;
        this.cordList = getCordList();
    }
    @Override
    public void draw(){
        Graphics2D g2d = this.grCtx.getG2d();
        int size = this.grCtx.getSize();
        g2d.setColor(new Color(255,255,255));
        for (int i = 0; i<this.cordList.size(); i++) {
            for (CoordinatePoint el : this.cordList.get(i)) {
                g2d.fillRect((int) Math.round((el.x / 1000.0) * grCtx.ScreenWidth), (int) Math.round((el.y / 1000.0) * grCtx.ScreenHeight), (int) Math.round((this.width / 1000.0) * grCtx.ScreenWidth), (int) Math.round((this.height / 1000.0) * grCtx.ScreenHeight));
            }
        }
    }
}
