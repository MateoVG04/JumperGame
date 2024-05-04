package be.antwerpen.mateo.game.logic;

public abstract class AbstractFactory {
    public abstract AbstractInput createInput();

    public abstract AbstractFactory createStatus();
    public abstract AbstractEntity createEntity(int x, int y, int r, int g, int b);
    public abstract AbstractHero createHero(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext gr);
    public abstract AbstractInterfaceMenuStrat createMenu(String Strat, AbstractContext gr);
    public abstract AbstractContext createContext();
    public abstract void setGameDimensions(int GameCellx, int GameCellY);
    public abstract void render();
}
