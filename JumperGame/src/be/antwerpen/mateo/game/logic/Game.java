package be.antwerpen.mateo.game.logic;

import be.antwerpen.mateo.game.context.j2dContext;
import be.antwerpen.mateo.game.input.Input;

import java.awt.*;

public class Game {
    private static Game instanceGame;
    private static MovementComponent instanceMove;
    private static MovementSystem instanceMoveSystem;
    private static AbstractHero instanceHero;
    private AbstractHero hero;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    private boolean isRunning;
    private boolean isPaused;
    private int GameCellsX = 0;
    private int GameCellsY = 0;
    private Clock clock = new Clock();
    private static AbstractFactory factory;
    private AbstractInput input;
    private static AbstractInput instanceInput;
    private long deltaT = 0;
    private j2dContext grCtx;

    public Game(AbstractFactory f,AbstractInput i, j2dContext gr){
        factory = f;
        input = i;
        grCtx = gr;
    }

    private static Game getInstanceGame(AbstractFactory f, AbstractInput i, j2dContext gr){
        if (instanceGame == null){
            instanceGame = new Game(f,i,gr);
        }
        return instanceGame;
    }

    private static MovementComponent getInstanceMove(){
        if (instanceMove == null){
            instanceMove = new MovementComponent(0,0,0,2);
        }
        return instanceMove;
    }
    // static keyword laat de method geaccessed worden zonder een object aan te maken van de class
    private static MovementSystem getInstanceMoveSystem(){
        if (instanceMoveSystem == null){
            instanceMoveSystem = new MovementSystem();
        }
        return instanceMoveSystem;
    }

    private AbstractInput getInstanceInput(){
        if (input == null){
            instanceInput = new factory.createInput(grCtx);
        }
        return instanceInput;
    }

    private static AbstractHero getInstanceHero(MovementComponent movementComponent, MovementSystem movementSystem){
        if (instanceHero == null){
            instanceHero = factory.createHero(movementComponent,movementSystem);
        }
        return instanceHero;
    }

    public void run() {
        clock.calculateDeltaT(true);
        isRunning = true;
        isPaused = false;

        while (isRunning){
            // Delta
            //deltaTime = time.now() - time;
            //time = time.now();

            // x.pos += 10m -> snelheid afhankelijk van FPS
            // xdif = v * deltaT -> snelheid onafhankelijk van FPS

            // Handl Input
            input = getInstanceInput();
            if (input.inputAvailable()) {
                AbstractInput direction = input;
                if (direction == AbstractInput.Inputs.ESC)
                    isPaused = ! isPaused;
                else
                    hero.movementSystem.setDirection(direction);
            }
            if (!isPaused) {
                float deltaTime = (float) (deltaT*Math.pow(10,-3));
                movementComponent = getInstanceMove();
                movementSystem = getInstanceMoveSystem();
                hero = getInstanceHero(movementComponent,movementSystem);
                movementSystem.update(hero,deltaTime);
                grCtx.getFrame().setTitle("Jumping");
                //snake.draw();
                //apple.draw();
                //Graphics2D g2d = grCtx.getG2d();
                //g2d.setColor(Color.BLACK);
                //Font f = new Font("Serif Font",1,18);
                //g2d.setFont(f);
                //g2d.drawString("Snake",10,20);
                //grCtx.render();
            }
            this.deltaT = clock.calculateDeltaT(false);

        }
    }

}