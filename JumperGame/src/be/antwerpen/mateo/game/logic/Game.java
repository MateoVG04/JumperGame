package be.antwerpen.mateo.game.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instanceGame;
    private static MovementComponent instanceMove;
    private static MovementSystem instanceMoveSystem;
    private static AbstractHero instanceHero;
    private static AbstractStaticPlatform instanceStaticPlatform;
    private AbstractHero hero;
    private AbstractStaticPlatform staticPlatform;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    private boolean isRunning;
    private boolean isPaused;
    private boolean inStartMenu;
    private boolean inGame;
    private boolean newPlatformsNeeded = false;
    private int GameCellsX = 20;
    private int GameCellsY = 20;
    private Clock clock = new Clock();
    private Clock clockMovementSystem = new Clock();
    private static AbstractFactory factory;
    private AbstractInput input;
    private static AbstractInput instanceInput = null;
    private long deltaT;
    private AbstractContext grCtx;
    private AbstractMenu menu;
    private static AbstractInterfaceMenuStrat instanceMenu;
    private static AbstractInterfaceMenuStrat instanceBackgroundMenu;
    private static AbstractContext instanceContext;
    private static AbstractHealthScrore instanceHealthScore;
    private int heroWidth = 30;
    private int heroHeight = 75;
    private int platformWidth =50;
    private int platformHeight =15;
    private boolean enterPressed = false;


    public Game(AbstractFactory f,AbstractInput i,AbstractContext gr){
        factory = f;
        input = i;
        this.instanceContext = gr;
        this.instanceInput = this.input;
    }

//    private static Game getInstanceGame(AbstractFactory f, AbstractInput i){
//        if (instanceGame == null){
//            instanceGame = new Game(f,i);
//        }
//        return instanceGame;
//    }
    public AbstractContext getContext(){
        AbstractContext contx = getInstanceContext(this.heroWidth,this.heroHeight);
        return contx;
    }

    private static MovementComponent getInstanceMove(int heroWidth, int heroHeight){
        if (instanceMove == null){
            instanceMove = new MovementComponent(500-Math.round(heroWidth/2.0),800,0,2,null, heroWidth, heroHeight);
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
        if (instanceInput == null){
            instanceInput = factory.createInput(getInstanceContext(this.heroWidth,this.heroHeight));
        }
        return instanceInput;
    }

    private static AbstractHero getInstanceHero(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext grCtx,int width, int height){
        if (instanceHero == null){
            instanceHero = factory.createHero(movementComponent,movementSystem,grCtx,width, height);
        }
        return instanceHero;
    }
    private static AbstractHealthScrore getInstanceHealthScore(int health, int score, AbstractContext gr){
        if (instanceHealthScore == null){
            instanceHealthScore = factory.createHealthScore(health,score,gr);
        }
        return instanceHealthScore;
    }
    private static AbstractStaticPlatform getInstanceStaticPlatform(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext gr, int width, int height){
        if (instanceStaticPlatform == null){
            instanceStaticPlatform = factory.createStaticPlatform(movementComponent,movementSystem,gr,width,height);
        }
        return instanceStaticPlatform;
    }
    private static void getInstanceBackgroundMenu(String Strat, AbstractContext gr){
        if (instanceBackgroundMenu == null) {
            instanceBackgroundMenu = factory.createMenu(Strat, gr);
        }
        instanceBackgroundMenu.execute();
    }
    private static void getInstanceMenu(String Strat, AbstractContext gr){
        if (instanceMenu == null) {
            instanceMenu = factory.createMenu(Strat, gr);
        }
        instanceMenu.execute();
    }

    private static AbstractContext getInstanceContext(int heroWidth,int heroHeight) {
        if (instanceContext == null) {
            instanceContext = factory.createContext(heroWidth, heroHeight);
        }
        return instanceContext;
    }

    public void run() {
        this.grCtx = getInstanceContext(this.heroWidth,this.heroHeight);
        isRunning = true;
        isPaused = false;
        inStartMenu = true;
        inGame = false;
//        int vorigAantalFrames = 0;
//        int huidigAantalFrames = 0;
//        long startStopwatch=0;
        boolean nogTiming = true;
        instanceHealthScore = getInstanceHealthScore(3,0,getInstanceContext(heroWidth,heroHeight));

        while (isRunning){
            clock.calculateDeltaT(true);


//            if (nogTiming) {
//                startStopwatch = clockMovementSystem.calculateDeltaT(true);
//                nogTiming = false;
//            }
            String strat = "backgroundMenu";
            //getInstanceBackgroundMenu(strat,getInstanceContext());
            // Delta
            //deltaTime = time.now() - time;
            //time = time.now();

            // x.pos += 10m -> snelheid afhankelijk van FPS
            // xdif = v * deltaT -> snelheid onafhankelijk van FPS

            // Handl Input
            input = getInstanceInput();
            input.setContext(grCtx);
            if (input.inputAvailable()) {
                Inputs direction = input.getInput();
                System.out.println(direction);
                if (direction == Inputs.ESC)
                    isPaused = ! isPaused;
                else if (direction == Inputs.ENTER){
                    enterPressed = true;
                }

                else
                    if (inGame) {
                        hero.movementSystem.setDirection(direction);
                    }
            }
//            if (!input.inputAvailable() && (movementSystem != null)){
//                movementSystem.setDirection(Inputs.NOInput);
//            }
            if (!isPaused) {
                //grCtx.setBackground();

                if (inStartMenu){
                    strat = "Menu";
                    getInstanceMenu(strat,getInstanceContext(this.heroWidth,this.heroHeight));
                    //grCtx.setBackground("StartMenu");
                    if (enterPressed){
                        inStartMenu = false;
                        inGame = true;
                    }
                    //inStartMenu = false;
                }
                else if(inGame){
                    movementComponent = getInstanceMove(this.heroWidth,this.heroHeight);
                    movementSystem = getInstanceMoveSystem();
                    hero = getInstanceHero(movementComponent,movementSystem,getInstanceContext(this.heroWidth,this.heroHeight),heroWidth,heroHeight);
                    staticPlatform = getInstanceStaticPlatform(movementComponent, movementSystem,getInstanceContext(this.heroWidth, this.heroHeight),this.platformWidth,this.platformHeight);
                    List<MovementComponent> movementComponentList = new ArrayList<>();
                    movementComponentList.add(hero.movementComponent);
                    movementComponentList.add(staticPlatform.getMovementComponent());

                    // moet geen boolean meer returnen want ik gebruik dit niet meer in een if/else statement
                    // In de update worden alle collisions gecheckt.
                    this.newPlatformsNeeded = this.movementSystem.update(movementComponentList,this.deltaT, this.clock, staticPlatform, instanceHealthScore);
                    // zet vx terug op 0, omdat als je een key hebt los gelaten dan moet je hero stoppen met opzij te bewegen
                    this.movementSystem.setVX(0);
//                if (this.newPlatformsNeeded){
//                    //staticPlatform.generatePlatformLocations();
//                    //staticPlatform.getMovementComponent().cordList = staticPlatform.generatePlatformLocations();
//                    System.out.println("wat is dit?"+staticPlatform.generatePlatformLocations());
//                }
                    //this.movementSystem.setVY(0);

                    //grCtx.setGameDimensions(this.GameCellsX,this.GameCellsY);
                    //grCtx.setWindowTitle("Jumping");

                    staticPlatform.draw();
                    hero.draw();
                    instanceHealthScore.draw();

                }
                else {
                    hero.draw();
                }
                grCtx.render();
            }
//            if (huidigAantalFrames-vorigAantalFrames == 2) {
//                if(System.currentTimeMillis()-startStopwatch<1000) {
//                    long stopwatch1 = clockMovementSystem.calculateDeltaT(false);
//                    System.out.println("Time (ms): " + stopwatch1 + ", aantal frames in tijd: " + (huidigAantalFrames - vorigAantalFrames));
//                    vorigAantalFrames = huidigAantalFrames;
//                    nogTiming = true;
//                }
//                hero.movementSystem.heroJumpFysics(System.currentTimeMillis()-startStopwatch);
//            }
            //huidigAantalFrames++;
            this.deltaT = clock.calculateDeltaT(false);
            //System.out.println(deltaT);
            clock.FixedFPS(this.deltaT);
        }
    }
}