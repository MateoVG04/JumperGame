package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractEntity;

import java.awt.*;

public class j2dEntity extends AbstractEntity{
    private j2dContext grCtx;

    public void draw(){
        Graphics2D g2d = grCtx.getG2d();
        int size = grCtx.getSize();
        g2d.setColor(new Color(0,0,200));
        g2d.fillRect(1*size,1*size,size,size);
    }
}
