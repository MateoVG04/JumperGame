package be.antwerpen.mateo.game.logic;

public abstract class AbstractContext {
    abstract public void setGameDimensions(int GameCellX, int GameCellY);
    abstract public void render();
    abstract public int getSize();
    abstract public void setWindowTitle(String name);
}
