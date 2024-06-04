package be.antwerpen.mateo.game.visualisation;

import java.awt.*;
import java.io.IOException;

public class backgroundMenu implements menuInterfaceStrat{
    private j2dContext grCtx;
    private Image scaleImage;
    public backgroundMenu(j2dContext gr){
        this.grCtx = gr;
    }
//    public void resizeImage(){
//        ImageIcon icon = new ImageIcon("JumperGame/src/be/antwerpen/mateo/game/resources/backgroundGalaxy.jpg");
//        this.scaleImage = icon.getImage().getScaledInstance(28,28, Image.SCALE_DEFAULT);
//    }
    public void execute(int state){
        try {
            grCtx.getFrame().getContentPane().add(new JPanelBackground("JumperGame/src/be/antwerpen/mateo/game/resources/backgroundGalaxy.jpg"),BorderLayout.CENTER);
            grCtx.getFrame().validate();
            grCtx.getFrame().repaint();
        } catch(IOException e){
            System.err.println("Error j2dContext: line 52");
            e.printStackTrace();
        }
    }
}
