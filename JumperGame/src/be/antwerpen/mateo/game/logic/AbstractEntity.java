package be.antwerpen.mateo.game.logic;

public abstract class AbstractEntity {
    private  int posX;
    private int posY;
    private int snelheidX;
    private int snelheidY;
    public MovementComponent movementComponent;
    public MovementSystem movementSystem;
    public AbstractEntity(MovementComponent movementComponent, MovementSystem movementSystem){
        this.movementComponent = movementComponent;
        this.movementSystem = movementSystem;
        this.posX = 0;
        this.posY = 0;
        this.snelheidX = 0;
        this.snelheidY = 0;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSnelheidX() {
        return snelheidX;
    }

    public int getSnelheidY() {
        return snelheidY;
    }

    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    public MovementSystem getMovementSystem() {
        return movementSystem;
    }
    //public abstract int getPosX();
    // positie (x,y)
    // size van het object
}
