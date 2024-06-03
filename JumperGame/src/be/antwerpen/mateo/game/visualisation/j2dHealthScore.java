package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractHealthScrore;

import java.awt.*;
import java.awt.image.BufferedImage;

public class j2dHealthScore extends AbstractHealthScrore {
//    private int health = 3;
    //private int score = 0;
//    private j2dContext grCtx;
//
//    public j2dHealthScore(int health, int score, j2dContext gr){
//        super(health,score);
//        this.health = health;
//        this.score = score;
//        this.grCtx = gr;
//
//    }
//    public void draw(){
//        if (grCtx == null) {
//            throw new IllegalStateException("grCtx is not initialized");
//        }
//        Graphics2D g2d = this.grCtx.getG2d();
//        int size = this.grCtx.getSize();
//        g2d.setColor(Color.WHITE);
//        Font f = new Font("Serif Font",1,18);
//        g2d.setFont(f);
//        String score = "Score: "+this.score;
//        g2d.drawString(score,10,20);
//    }
    j2dContext grCtx;
    int prevScore = -1; // -1 zodat bij de eerste render met score 0 ook al een buffer wordt aangemaakt
    BufferedImage BufferScore;
    //BufferedImage BufferHealth;
    private int score = 0;
    private int health = 3;


    public j2dHealthScore(int health, int score, j2dContext gr) {
        super(health, score);
        this.grCtx = gr;
        this.score = score;
        this.health = health;
    }

    @Override
    public void draw() {
        // Enkel wanneer de score verandert zal de zwaar belastende drawString aangeroepen worden.
        // Alle andere momenten zal de buffer gebruikt worden.
        this.score = getScore();
        this.health = getHealth();
        //BufferHealth = new BufferedImage(this.grCtx.getScreenWidth(), this.grCtx.getScreenHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g2dHealth = this.grCtx.getG2d();
        int x = 10;
        int y = 40;
        for (int i = 0; i<health; i++){
            g2dHealth.drawImage(this.grCtx.healthSprite,x,y,grCtx.getHealthWidth(),grCtx.getHealthHeight(),null);
            x += 40;
        }
        if (prevScore != score){
            BufferScore = new BufferedImage(this.grCtx.getScreenWidth(), this.grCtx.getScreenHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE); // Use ARGB for transparency
            Graphics2D g2d = BufferScore.createGraphics();
            //g2d.setColor(new Color(0, 0, 0, 0)); // Transparent background
            Font font = g2d.getFont().deriveFont(18.0f);
            g2d.setFont(font);
            g2d.setColor(new Color(255, 255, 255));
            g2d.drawString(String.valueOf("Score: "+score), 10, 20);
            g2d.dispose(); // Dispose graphics object to release resources
            prevScore = score;
        }
        Graphics2D g2d = ((j2dContext) grCtx).getG2d();
        if (g2d != null)
            g2d.drawImage(BufferScore, 0, 0, null);
    }
}
