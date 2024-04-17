package be.antwerpen.mateo.game.logic;

public class MovementSystem {
    private float dx = 0;
    private float dy = 2;
    private int hoog =0;
    public void update(AbstractHero hero, float deltaT){
            MovementComponent heroMove = hero.movementComponent;
            heroMove.vx = dx;
            heroMove.vy = dy;
            heroMove.x += heroMove.vx * deltaT;
            heroMove.y += heroMove.vy * deltaT;
    }

    public void setDirection(AbstractInput.Inputs direction){ //, float[] movementComponent){
        switch (direction){
            case LEFT  -> {dx = -1; dy = 0;}
            case RIGHT -> {dx =  1; dy = 0;}
        }
    }
}
