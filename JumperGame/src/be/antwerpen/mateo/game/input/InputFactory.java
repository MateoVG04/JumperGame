package be.antwerpen.mateo.game.input;

import be.antwerpen.mateo.game.context.j2dContext;
import be.antwerpen.mateo.game.logic.*;

public class InputFactory extends AbstractFactory {
//    @Override
//    public AbstractInput createInput() {
//        return null;
//    }

    @Override
    public AbstractInput createInput(j2dContext gr) {
        Input input = new Input(gr);
        //return null;
        return input;
    }

    @Override
    public AbstractFactory createStatus() {
        return null;
    }

    @Override
    public AbstractEntity createEntity(int x, int y, int r, int g, int b) {
        return null;
    }

    @Override
    public AbstractHero createHero(MovementComponent movementComponent, MovementSystem movementSystem) {
        return null;
    }

    @Override
    public void setGameDimensions(int GameCellx, int GameCellY) {

    }

    @Override
    public void render() {

    }

}
