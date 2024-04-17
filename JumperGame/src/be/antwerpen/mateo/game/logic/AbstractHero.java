package be.antwerpen.mateo.game.logic;

public abstract class AbstractHero extends AbstractEntity {
    // ik gebruik hier extends omdat we werken met overerving van classen
    public MovementComponent movementComponent;
    public MovementSystem movementSystem;
    public AbstractHero(MovementComponent movementComponent, MovementSystem movementSystem){
        this.movementComponent = movementComponent;
        this.movementSystem = movementSystem;
    }
}
