package be.antwerpen.mateo.game.logic;

public abstract class AbstractHero extends AbstractEntity {
    // ik gebruik hier extends omdat we werken met overerving van classen
    private int width;
    private int height;
    public AbstractHero(MovementComponent movementComponent, MovementSystem movementSystem,int width, int height){
        super(movementComponent,movementSystem);
        this.width = width;
        this.height = height;
    }

    abstract public void draw();
}
