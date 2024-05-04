package be.antwerpen.mateo.game.logic;

import be.antwerpen.mateo.game.visualisation.Input;
import be.antwerpen.mateo.game.visualisation.*;

// git commit -a -m "title" -m "description"
// git push
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new j2dFactory();
        //j2dContext gr = new j2dContext();
        //AbstractInput input = new Input();
        Game game = new Game(factory,factory.createInput());
        game.run();
    }
}
