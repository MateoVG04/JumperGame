package be.antwerpen.mateo.game.visualisation;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class JPanelBackground extends JPanel {
    private Image backgroundImage;
    private j2dContext grCtx;


    public Image getBackgroundImage(){
        return this.backgroundImage;
    }
    public JPanelBackground(String fileName) throws IOException {
        super();
        this.setDoubleBuffered(true);
        backgroundImage = new ImageIcon("JumperGame/src/be/antwerpen/mateo/game/resources/backgroundGalaxy.jpg").getImage();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0,this.getWidth(),this.getHeight(), this);
        } else {
            g.setColor(Color.GRAY); // Fallback color
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }


}
