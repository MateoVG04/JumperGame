package be.antwerpen.mateo.game.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static List<AbstractEntity> instanceGame;
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
    private boolean heroDied = false;
    private int GameCellsX = Config.getIntProperty("GAME_CELL_X");
    private int GameCellsY = Config.getIntProperty("GAME_CELL_Y");
    private int count = 0;
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
    private int aantalHartjes = Config.getIntProperty("AANTAL_LEVENS");
    private int heroWidth = Config.getIntProperty("HERO_WIDTH");
    private int heroHeight = Config.getIntProperty("HERO_HEIGHT");
    private int platformWidth = Config.getIntProperty("PLATFORM_WIDTH");
    private int platformHeight = Config.getIntProperty("PLATFORM_HEIGHT");
    private boolean enterPressed = false;
    private int ScreenWidth;
    private int ScreenHeight;


    public Game(AbstractFactory f,AbstractInput i,AbstractContext gr, int ScreenWidth, int ScreenHeight){
        factory = f;
        input = i;
        this.instanceContext = gr;
        this.instanceInput = this.input;
        this.ScreenWidth = ScreenWidth;
        this.ScreenHeight = ScreenHeight;
    }

    private static List<AbstractEntity> getInstanceGame(AbstractEntity ent){
        // Game singleton waar alle entities in zitten. Dan kan je gemakkelijk aan alle entities.
        if (instanceGame == null){
            instanceGame = new ArrayList<>();
            instanceGame.add(ent);
        }
        if(instanceGame.indexOf(ent) == -1){
            instanceGame.add(ent);
        }
        return instanceGame;
    }
//    public AbstractContext getContext(){
//        AbstractContext contx = getInstanceContext(this.heroWidth,this.heroHeight);
//        return contx;
//    }

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

    private AbstractInput getInstanceInput(String state){
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
    private static void getInstanceBackgroundMenu(String Strat, AbstractContext gr, int state){
        if (instanceBackgroundMenu == null) {
            instanceBackgroundMenu = factory.createMenu(Strat, gr,state);
        }
        instanceBackgroundMenu.execute(state);
    }
    private static void getInstanceMenu(String Strat, AbstractContext gr, int state){
        if (instanceMenu == null) {
            instanceMenu = factory.createMenu(Strat, gr,state);
        }
        instanceMenu.execute(state);
    }

    private static AbstractContext getInstanceContext(int heroWidth,int heroHeight) {
        if (instanceContext == null) {
            instanceContext = factory.createContext(heroWidth, heroHeight);
        }
        return instanceContext;
    }

    public void run() {
        String state = "menu";
        //this.grCtx = getInstanceContext(this.heroWidth,this.heroHeight);
        isRunning = true;
        isPaused = false;
        inStartMenu = true;
        inGame = false;
        boolean wordtInGame = false;
        boolean test = false;
        boolean isFirstGame = true;
        int stateNummer = 1;
        int enterNummer = 0;

        while (isRunning){
            this.grCtx = getInstanceContext(this.heroWidth,this.heroHeight);
            clock.calculateDeltaT(true);
            String strat = "backgroundMenu";
            input = getInstanceInput(state);
            input.setContext(grCtx);
//            boolean var = input.inputAvailable();
//            if (test){
//                var = true;
//            }
            if (input.inputAvailable()) {
                Inputs direction = input.getInput();
//                if (test){
//                    direction = Inputs.ENTER;
//                }
                if (state == "menu"){
                    if (direction == Inputs.DOWN){
                        stateNummer++;
                        if (stateNummer >= 4){
                            stateNummer = 1;
                        }
                    }
                    else if (direction == Inputs.UP){
                        stateNummer--;
                        if (stateNummer <= 0){
                            stateNummer = 3;
                        }
                    }
                    else if (direction == Inputs.ENTER){
                        enterPressed = true;
                    }
                }
                else {
                    if (direction == Inputs.ESC)
                        isPaused = !isPaused;
                    else if (direction == Inputs.ENTER) {
                        enterPressed = true;
                    } else if (inGame) {
                        hero.movementSystem.setDirection(direction);
                    }
                }
            }
            instanceHealthScore = getInstanceHealthScore(aantalHartjes,0,getInstanceContext(heroWidth,heroHeight));

            movementComponent = getInstanceMove(this.heroWidth,this.heroHeight);
            movementSystem = getInstanceMoveSystem();
            hero = getInstanceHero(movementComponent,movementSystem,getInstanceContext(this.heroWidth,this.heroHeight),heroWidth,heroHeight);
            getInstanceGame(hero);
            staticPlatform = getInstanceStaticPlatform(movementComponent, movementSystem,getInstanceContext(this.heroWidth, this.heroHeight),this.platformWidth,this.platformHeight);
            getInstanceGame(staticPlatform);
            List<MovementComponent> movementComponentList = new ArrayList<>();
            movementComponentList.add(hero.movementComponent);
            movementComponentList.add(staticPlatform.getMovementComponent());

            if (!isPaused) {
                if (inStartMenu){
                    strat = "Menu";
                    if (isFirstGame) {
                        getInstanceContext(this.heroWidth, this.heroHeight).setStateGraphics(state);
                        isFirstGame = false;
                    }
                    if (enterNummer == 10){
                        stateNummer = 10;
                    }
                    getInstanceMenu(strat,getInstanceContext(this.heroWidth,this.heroHeight),stateNummer);
                    enterNummer = 0;
                    if (enterPressed && (stateNummer == 1)){
                        inStartMenu = false;
                        inGame = true;
                        if (heroDied){
                            heroDied = false;
                        }
                        state = "game";
                    }
                    else if (enterPressed && (stateNummer == 2)){

                    }
                }
                else if(inGame){
                    if (count  != 1) {
                        getInstanceContext(this.heroWidth, this.heroHeight).setStateGraphics(state);
                        count = 1;
                    }
                    this.heroDied = this.movementSystem.update(movementComponentList,this.deltaT, this.clock, staticPlatform, instanceHealthScore,this.ScreenWidth,this.ScreenHeight);
                    // zet vx terug op 0, omdat als je een key hebt los gelaten dan moet je hero stoppen met opzij te bewegen
                    this.movementSystem.setVX(0);
                    staticPlatform.draw();
                    hero.draw();
                    instanceHealthScore.draw();
                    if (this.heroDied){
                        state = "menu";
                        inGame = false;
                        inStartMenu = true;
                        count = 0;
                        instanceHealthScore = null;
                        instanceInput = null;
                        movementComponent = null;
                        instanceMove = null;
                        movementSystem = null;
                        instanceMoveSystem = null;
                        hero = null;
                        instanceHero = null;
                        instanceGame = null;
                        staticPlatform = null;
                        instanceStaticPlatform = null;
                        movementComponent = null;
                        //test = true;
                        //instanceHealthScore.
                    }
                    //enterPressed = false;
                }
                else {
                    hero.draw();
                }
                enterPressed = false;
                grCtx.render();
            }
            this.deltaT = clock.calculateDeltaT(false);
            clock.FixedFPS(this.deltaT);
        }
    }
}