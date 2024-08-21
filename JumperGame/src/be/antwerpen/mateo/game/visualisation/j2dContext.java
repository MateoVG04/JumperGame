package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractContext;
import be.antwerpen.mateo.game.logic.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class j2dContext extends AbstractContext {
    public int ScreenWidth;
    public int ScreenHeight;
    private String name;
    private JFrame frame;
    private JPanel panel;
    private BufferedImage g2dimage;     // used for drawing
    private Graphics2D g2d;             // always draw in this one
    //private JPanelBackground backgroundImage;
    private int size;                   // cel size
    public BufferedImage backgroundImg;
    public BufferedImage backSmog;
    public BufferedImage backSmog2;
    public BufferedImage heroSprite;
    public BufferedImage enemySprite;
    public BufferedImage healthSprite;
    public BufferedImage bulletSprite;
    private int heroWidth;
    private int heroHeight;
    private int healthWidth;
    private int healthHeight;
    private String state;
    private int count=0;


    public Graphics2D getG2d() {
        return g2d;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getScreenWidth(){
        return ScreenWidth;
    }
    public int getScreenHeight(){
        return ScreenHeight;
    }
    public int getHealthWidth(){return this.healthWidth;}
    public int getHealthHeight(){return this.healthHeight;}

    public int getSize() {
        return size;
    }
    public void setWindowTitle(String title){
        this.name = title;
        this.frame.setTitle(title);
    }

    public j2dContext(int heroWidth, int heroHeight) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        ScreenWidth = screenSize.width;
        ScreenHeight = screenSize.height;
        this.heroWidth = heroWidth;
        this.heroHeight = heroHeight;
        this.healthWidth = 20;
        this.healthHeight = 20;
        frame = new JFrame();
        setGameDimensions(Config.getIntProperty("GAME_CELL_X"),Config.getIntProperty("GAME_CELL_Y"));

        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        frame.setFocusable(true);
        frame.add(panel);
        frame.setTitle("Jumper Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(ScreenWidth, ScreenHeight);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public void render() {
        panel.repaint();
    }

//    public BufferedImage resizeImage(String entity,BufferedImage originalImage, int targetWidth, int targetHeight){
//        Image resultingImage = null;
//        if (entity == "hero"){
//            resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT); // + 300, +100
//        }
//        else if (entity == "enemy") {
//            resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT); // + 300, +100
//        }
//        else if (entity == "background") {
//            resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT); // + 300, +100
//        }
//        else if (entity == "health"){
//            resultingImage = originalImage.getScaledInstance(targetWidth,targetHeight,Image.SCALE_DEFAULT);
//        }
//        else if (entity == "Smog"){
//            resultingImage = originalImage.getScaledInstance(targetWidth,targetHeight,Image.SCALE_DEFAULT);
//        }
//        else if (entity == "Smog2"){
//            resultingImage = originalImage.getScaledInstance(targetWidth,targetHeight,Image.SCALE_DEFAULT);
//        }
//        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
//        if (entity == "hero") {
//            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null); // -100, -100
//        } else if (entity == "background") {
//            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null); // -100, -100
//        } else if (entity == "health") {
//            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
//        } else if (entity == "Smog") {
//            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
//        } else if (entity == "Smog2") {
//            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
//        }
//        return outputImage;
//    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = null;
        resultingImage = originalImage.getScaledInstance(targetWidth,targetHeight,Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    private void loadImages(int level) {
        backgroundImg = null;
        try {
            if (level == 1) {
                // Voor goeie images te maken gebruik Chat GPT
                // Voor de achtergrond van de image te verwijderen, gebruik https://www.remove.bg/upload
                backgroundImg = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/BackgrndGalaxy.png"));
                heroSprite = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/Hero_Astronaut3.png"));
                healthSprite = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/Health_hartje.png"));
                enemySprite = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/SpaceEnemy2.png"));
                backSmog = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/Cosmic_Fog_Right_blur.png"));
                backSmog2 = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/Cosmic_Fog_Left_blur.png"));
                bulletSprite = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/ShootBullet1.png"));
            }
            else if (level == 2){
                backgroundImg = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/BackgrndLavaCave.png"));
                //C:\Users\Mateo\Geavanceerde programmeertechnieken\Project - chose name later\JumperGame\src\be\antwerpen\mateo\game\resources\backgroundGalaxy.jpg
                //      src/be/antwerpen/mateo/game/resources/backgroundGalaxy.jpg
                heroSprite = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/ridder.png"));
                enemySprite = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/LavaEnemy2.png"));
                healthSprite = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/Health_hartje.png"));
                backSmog = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/Lava_Wall_Right.png"));
                backSmog2 = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/Lava_Wall_Left.png"));
                bulletSprite = null;
            }
        } catch (IOException e) {
            System.out.println("Unable to load .png!");
        }
    }

    public void setBackground(String gameMode){
        try {
            if (gameMode == "StartMenu") {
                backgroundImg = ImageIO.read(new File("JumperGame/src/be/antwerpen/mateo/game/resources/BackgrndGalaxy.png"));
            }
        } catch (IOException e){
            System.out.println("Unable to load .png!");
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
        if (g2d != null){
            if (state == "game") {
                g2d.drawImage(backgroundImg, -100, -100, null);
                g2d.drawImage(backSmog, 0, 0, null);
                g2d.drawImage(backSmog2, (int) (Math.round(frame.getWidth() * (4 / 5.0))), 0, null);
            }
        }
        graph2d.dispose();
    }

    public void setGameDimensions(int GameCellsX, int GameCellsY) {
        size = Math.min(ScreenWidth / GameCellsX, ScreenHeight / GameCellsY);
        frame.setLocation(0, 0);
        frame.setSize(ScreenWidth, ScreenHeight);
        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        g2d.setBackground(new Color(255, 255, 255));
        //g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
//        if (g2d != null)
//            g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void setStateGraphics(String state, int level){
        this.state = state;
        count=1;
        if (count == 1) {
            if (state == "game") {
                try {
                    loadImages(level);
                    backgroundImg = resizeImage(backgroundImg, frame.getWidth()+100, frame.getHeight()+100);
                    backSmog = resizeImage(backSmog, (int) (Math.round(frame.getWidth() * (1 / 5.0))), frame.getHeight());
                    backSmog2 = resizeImage(backSmog2, (int) (Math.round(frame.getWidth() * (1 / 5.0))), frame.getHeight());
                    heroSprite = resizeImage(heroSprite, (int) Math.round((heroWidth / 1000.0) * ScreenWidth), (int) Math.round((heroHeight / 1000.0) * ScreenHeight));
                    healthSprite = resizeImage(healthSprite, this.healthWidth, this.healthHeight);
                    enemySprite = resizeImage(enemySprite, (int) Math.round((Config.getIntProperty("ENEMY_WIDTH") / 1000.0) * ScreenWidth), (int) Math.round((Config.getIntProperty("ENEMY_WIDTH") / 1000.0) * ScreenHeight));
                    bulletSprite = resizeImage(bulletSprite, (int) Math.round((Config.getIntProperty("BULLET_WIDTH") / 1000.0) * ScreenWidth),(int) Math.round((Config.getIntProperty("BULLET_HEIGHT") / 1000.0) * ScreenHeight));
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }
                g2d.drawImage(backgroundImg, -100, -100, null); // -100, -100

            }
        }

    }
}


