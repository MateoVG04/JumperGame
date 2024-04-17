package be.antwerpen.mateo.game.visualisation;
import be.antwerpen.mateo.game.context.j2dContext;

import java.awt.*;

public class Menu implements menuInterfaceStrat {
    private j2dContext grCtx;
    @Override
    public void execute() {
        Graphics2D g2d = grCtx.getG2d();
        int size = grCtx.getSize();
        g2d.setColor(new Color(230,230,0));
        g2d.fillRect(size,size,size,size);
    }
}
