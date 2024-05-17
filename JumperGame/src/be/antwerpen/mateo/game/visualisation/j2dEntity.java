package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractEntity;
import be.antwerpen.mateo.game.logic.MovementComponent;
import be.antwerpen.mateo.game.logic.MovementSystem;

import java.awt.*;

public class j2dEntity extends AbstractEntity{
    private j2dContext grCtx;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    public j2dEntity(MovementComponent movementComponent, MovementSystem movementSystem, j2dContext gr){
        super(movementComponent,movementSystem);
        this.grCtx = gr;
        this.movementComponent = movementComponent;
        this.movementSystem = movementSystem;
    }
    public void draw(){
        Graphics2D g2d = grCtx.getG2d();
        int size = grCtx.getSize();
        g2d.setColor(new Color(0,0,200));
        g2d.fillRect(1*size,1*size,size,size);
    }
}
