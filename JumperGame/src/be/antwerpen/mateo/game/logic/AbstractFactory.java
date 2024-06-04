package be.antwerpen.mateo.game.logic;

public abstract class AbstractFactory {
    public abstract AbstractInput createInput(AbstractContext gr);
    public abstract AbstractFactory createStatus();
    public abstract AbstractEntity createEntity(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext gr);
    public abstract AbstractHero createHero(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext gr, int width, int height);
    public abstract AbstractInterfaceMenuStrat createMenu(String Strat, AbstractContext gr,int state);
    public abstract AbstractContext createContext(int heroWidt, int heroHeight);
    public abstract AbstractStaticPlatform createStaticPlatform(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext gr, int width, int height);
    public abstract AbstractHealthScrore createHealthScore(int health, int score, AbstractContext gr);
    public abstract void setGameDimensions(int GameCellx, int GameCellY);
    public abstract void render();
}
