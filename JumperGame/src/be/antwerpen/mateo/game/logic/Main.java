package be.antwerpen.mateo.game.logic;

import be.antwerpen.mateo.game.input.Input;
import be.antwerpen.mateo.game.visualisation.j2dFactory;
import be.antwerpen.mateo.game.context.j2dContext;

// git commit -a -m "title" -m "description"
// git push
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new j2dFactory();
        j2dContext gr = new j2dContext();
        AbstractInput input = new Input(gr);
        Game game = new Game(factory, input, gr);
        game.run();
    }
}
