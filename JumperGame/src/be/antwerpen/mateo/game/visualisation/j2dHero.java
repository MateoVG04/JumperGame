package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractContext;
import be.antwerpen.mateo.game.logic.AbstractHero;
import be.antwerpen.mateo.game.logic.MovementComponent;
import be.antwerpen.mateo.game.logic.MovementSystem;

import java.awt.*;

public class j2dHero extends AbstractHero {
    private j2dContext grCtx;


    public j2dHero(MovementComponent movementComponent,MovementSystem movementSystem, j2dContext gr) {
        super(movementComponent,movementSystem);
        this.grCtx = gr;


    }
    public void draw() {
        Graphics2D g2d = this.grCtx.getG2d();
        int size = this.grCtx.getSize();
        g2d.setColor(new Color(230,0,0));
        g2d.fillRect(Math.round(movementComponent.x)*size,Math.round(movementComponent.y)*size,size,2*size);
    }
}
