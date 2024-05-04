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
            grCtx.getG2d().fillRect(0,0,grCtx.getScreenWidth(),grCtx.getScreenHeight());
            grCtx.getG2d().setColor(new Color(100,230,100));
            g2d.fillRect(size*2,size*2,size*4,size*2);
            Font f = new Font("Serif Font",1,18);
            g2d.setFont(f);
            grCtx.getG2d().setColor(new Color(0,0,0));

            // Comment: Hier zat ik lang mee vast. Doordat in de g2d het veld van de loops null bleek te zijn
            // omdat ik in j2dContext g2d al clearde in doDrawing() en daarna nog in setDimonsions g2d nog eens
            // ging clearen (clearRect()). En hierdoor zou je dan null gaan clearen => error wat tot een
            // nullPointerException lijde
            grCtx.getG2d().drawString("Start",size*2,size*2);

            //grCtx.render();
        }
        else{
            System.err.println("Graphics context not initialized!");
        }
    }
}
