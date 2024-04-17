package be.antwerpen.mateo.game.logic;

import be.antwerpen.mateo.game.context.j2dContext;

public abstract class AbstractFactory {
    //public abstract AbstractInput createInput();

    public abstract AbstractInput createInput(j2dContext gr);

    public abstract AbstractFactory createStatus();
    public abstract AbstractEntity createEntity(int x, int y, int r, int g, int b);
    //public abstract AbstractHero createHero(MovementComponent movementComponent);

    public abstract AbstractHero createHero(MovementComponent movementComponent, MovementSystem movementSystem);

    public abstract void setGameDimensions(int GameCellx, int GameCellY);
    public abstract void render();
}
