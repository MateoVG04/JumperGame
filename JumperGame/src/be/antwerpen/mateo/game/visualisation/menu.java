package be.antwerpen.mateo.game.visualisation;
import java.awt.*;

public class Menu implements menuInterfaceStrat {
    private j2dContext grCtx;
    public Menu(j2dContext gr){
        this.grCtx = gr;
    }
    @Override
    public void execute() {
        Graphics2D g2d = grCtx.getG2d();
        if(g2d != null){
            int size = grCtx.getSize();
            grCtx.getG2d().setColor(new Color(100,100,230));
            grCtx.getG2d().fillRect((int)Math.floor(grCtx.getScreenWidth()*(1/5.0)),0,(int)Math.floor(grCtx.getScreenWidth()*(3/5.0)),grCtx.getScreenHeight());
            grCtx.getG2d().setColor(new Color(100,230,100));
            g2d.fillRect((int)Math.floor(grCtx.getScreenWidth()*(1/5.0))+size*2,size*2,(int)Math.floor(grCtx.getScreenWidth()*(3/5.0))-(size*4),size*2);
            g2d.fillRect((int)Math.floor(grCtx.getScreenWidth()*(1/5.0))+size*2,size*6,(int)Math.floor(grCtx.getScreenWidth()*(3/5.0))-(size*4),size*2);
            g2d.fillRect((int)Math.floor(grCtx.getScreenWidth()*(1/5.0))+size*2,size*10,(int)Math.floor(grCtx.getScreenWidth()*(3/5.0))-(size*4),size*2);
            Font f = new Font("Serif Font",1,18);
            g2d.setFont(f);
            grCtx.getG2d().setColor(new Color(0,0,0));

            // Comment: Hier zat ik lang mee vast. Doordat in de g2d het veld van de loops null bleek te zijn
            // omdat ik in j2dContext g2d al clearde in doDrawing() en daarna nog in setDimonsions g2d nog eens
            // ging clearen (clearRect()). En hierdoor zou je dan null gaan clearen => error wat tot een
            // nullPointerException lijde
            grCtx.getG2d().drawString("Start",Math.round(grCtx.getScreenWidth()/2)-size,size*3);
            grCtx.getG2d().drawString("Settings",Math.round(grCtx.getScreenWidth()/2)-(size*2),size*7);
            grCtx.getG2d().drawString("Heroes",Math.round(grCtx.getScreenWidth()/2)-size,size*11);
        }
        else{
            System.err.println("Graphics context not initialized!");
        }
    }
}
