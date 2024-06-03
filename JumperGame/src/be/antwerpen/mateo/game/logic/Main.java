package be.antwerpen.mateo.game.logic;

import be.antwerpen.mateo.game.visualisation.*;

// git commit -a -m "title" -m "description"
// git push
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new j2dFactory();
        AbstractContext gr = factory.createContext(25,50);
        //AbstractInput input = new Input();
        Game game = new Game(factory,factory.createInput(gr),gr);
        game.run();
    }
}
