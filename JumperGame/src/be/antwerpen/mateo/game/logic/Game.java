package be.antwerpen.mateo.game.logic;

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
    private boolean inStartMenu;
    private int GameCellsX = 20;
    private int GameCellsY = 20;
    private Clock clock = new Clock();
    private static AbstractFactory factory;
    private AbstractInput input;
    private static AbstractInput instanceInput = null;
    private long deltaT = 0;
    private AbstractContext grCtx;
    private AbstractMenu menu;
    private static AbstractInterfaceMenuStrat instanceMenu;
    private static AbstractContext instanceContext;


    public Game(AbstractFactory f,AbstractInput i){
        factory = f;
        input = i;
        this.instanceInput = this.input;
    }

//    private static Game getInstanceGame(AbstractFactory f, AbstractInput i){
//        if (instanceGame == null){
//            instanceGame = new Game(f,i);
//        }
//        return instanceGame;
//    }
    public AbstractContext getContext(){
        AbstractContext contx = getInstanceContext();
        return contx;
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
        if (instanceInput == null){
            instanceInput = factory.createInput();
        }
        return instanceInput;
    }

    private static AbstractHero getInstanceHero(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext grCtx){
        if (instanceHero == null){
            instanceHero = factory.createHero(movementComponent,movementSystem,grCtx);
        }
        return instanceHero;
    }

    private static void getInstanceMenu(String Strat, AbstractContext gr){
        if (instanceMenu == null) {
            instanceMenu = factory.createMenu(Strat, gr);
        }
        instanceMenu.execute();
    }

    private static AbstractContext getInstanceContext(){
        if (instanceContext == null){
            instanceContext = factory.createContext();
        }
        return instanceContext;
    }

    public void run() {
        this.grCtx = getInstanceContext();
        isRunning = true;
        isPaused = false;
        inStartMenu = true;

        while (isRunning){
            clock.calculateDeltaT(true);
            // Delta
            //deltaTime = time.now() - time;
            //time = time.now();

            // x.pos += 10m -> snelheid afhankelijk van FPS
            // xdif = v * deltaT -> snelheid onafhankelijk van FPS

            // Handl Input
            input = getInstanceInput();
            input.setContext(grCtx);
            if (input.inputAvailable()) {
                AbstractInput.Inputs direction = input.getInput();
                if (direction == AbstractInput.Inputs.ESC)
                    isPaused = ! isPaused;
                else
                    hero.movementSystem.setDirection(direction);
            }
            if (!isPaused) {
                float deltaTime = (float) (deltaT*Math.pow(10,-3));
                movementComponent = getInstanceMove();
                movementSystem = getInstanceMoveSystem();
                hero = getInstanceHero(movementComponent,movementSystem,getInstanceContext());
                movementSystem.update(hero,deltaTime);
                //grCtx.setGameDimensions(this.GameCellsX,this.GameCellsY);
                //grCtx.setWindowTitle("Jumping");
                if (inStartMenu){
                    String strat = "Menu";
                    getInstanceMenu(strat,getInstanceContext());
                    //inStartMenu = false;
                }
                else {
                    hero.draw();
                }
                //snake.draw();
                //apple.draw();
                //Graphics2D g2d = grCtx.getG2d();
                //g2d.setColor(Color.BLACK);
                //Font f = new Font("Serif Font",1,18);
                //g2d.setFont(f);
                //g2d.drawString("Snake",10,20);
                grCtx.render();
            }
            this.deltaT = clock.calculateDeltaT(false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie){
                Thread.currentThread().interrupt();
            }

        }
    }
}