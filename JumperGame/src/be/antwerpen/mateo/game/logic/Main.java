package be.antwerpen.mateo.game.logic;

import be.antwerpen.mateo.game.visualisation.*;

import java.awt.*;

// git commit -a -m "title" -m "description"
// git push
public class Main {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int ScreenHeight = screenSize.height;
        int ScreenWidth = screenSize.width;
        AbstractFactory factory = new j2dFactory();
        AbstractContext gr = factory.createContext(30,75);
        //AbstractInput input = new Input();
        Game game = new Game(factory,factory.createInput(gr),gr,ScreenWidth,ScreenHeight);
        game.run();
    }
}
