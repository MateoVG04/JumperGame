package be.antwerpen.mateo.game.logic;
import java.util.List;

public abstract class AbstractEnemy extends AbstractEntity {
    private int width;
    private int height;
    public AbstractEnemy(MovementComponent movementComponent, MovementSystem movementSystem,int width, int height){
        super(movementComponent,movementSystem);
        this.width = width;
        this.height = height;
    }

    abstract public void draw();
}
