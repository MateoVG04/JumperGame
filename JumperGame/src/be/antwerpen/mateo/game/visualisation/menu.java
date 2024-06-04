package be.antwerpen.mateo.game.visualisation;
import org.luaj.vm2.ast.Str;

import java.awt.*;

public class Menu implements menuInterfaceStrat {
    private j2dContext grCtx;
    public Menu(j2dContext gr){
        this.grCtx = gr;
    }
    @Override
    public void execute(int state) {
        Graphics2D g2d = grCtx.getG2d();
        if(g2d != null){
            int size = grCtx.getSize();
            grCtx.getG2d().setColor(new Color(100,100,230));
            grCtx.getG2d().fillRect(0,0,grCtx.getScreenWidth(),grCtx.getScreenHeight());
            grCtx.getG2d().setColor(new Color(100,230,100));
            int startX = (int)(grCtx.getScreenWidth()/2.0) - (int)(((grCtx.getScreenWidth()*(2/5.0)))/2.0);
            int breedte = (int)(((grCtx.getScreenWidth()*(2/5.0))));
            if (state == 1){
                grCtx.getG2d().setColor(new Color(255,100,100));
                g2d.fillRect(startX,size*2,breedte,size*2);
                grCtx.getG2d().setColor(new Color(100,230,100));
                g2d.fillRect(startX,size*6,breedte,size*2);
                g2d.fillRect(startX,size*10,breedte,size*2);
            }
            else if (state == 2){
                g2d.fillRect(startX,size*2,breedte,size*2);
                grCtx.getG2d().setColor(new Color(255,100,100));
                g2d.fillRect(startX,size*6,breedte,size*2);
                grCtx.getG2d().setColor(new Color(100,230,100));
                g2d.fillRect(startX,size*10,breedte,size*2);
            }
            else if (state == 3){
                g2d.fillRect(startX,size*2,breedte,size*2);
                g2d.fillRect(startX,size*6,breedte,size*2);
                grCtx.getG2d().setColor(new Color(255,100,100));
                g2d.fillRect(startX,size*10,breedte,size*2);
            }
            Font f = new Font("Serif Font",1,25);
            g2d.setFont(f);
            grCtx.getG2d().setColor(new Color(0,0,0));

            // Comment: Hier zat ik lang mee vast. Doordat in de g2d het veld van de loops null bleek te zijn
            // omdat ik in j2dContext g2d al clearde in doDrawing() en daarna nog in setDimonsions g2d nog eens
            // ging clearen (clearRect()). En hierdoor zou je dan null gaan clearen => error wat tot een
            // nullPointerException lijde
            int StringX = (int)(grCtx.getScreenWidth()/2.0) - 40;
            grCtx.getG2d().drawString(" Start  ",StringX+10,(size*3)+10);
            grCtx.getG2d().drawString("Settings",StringX-5,(size*7)+10);
            grCtx.getG2d().drawString(" Heroes ", StringX-5,(size*11)+10);
        }
        else{
            System.err.println("Graphics context not initialized!");
        }
    }
}
