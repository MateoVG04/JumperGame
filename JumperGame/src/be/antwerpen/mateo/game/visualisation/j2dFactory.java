package be.antwerpen.mateo.game.visualisation;
import be.antwerpen.mateo.game.logic.*;


public class j2dFactory extends AbstractFactory {
//    @Override
//    public AbstractInput createInput(j2dContext gr) {
//        Input input = new Input(gr);
//        return null;
//        return input;
//    }
    @Override
    public AbstractInput createInput(AbstractContext gr) {
        Input input = new Input((j2dContext) gr);
        return input;
    }

    // hier ga je implementeren om bv een creatHero() te maken zodat je in main gewoon de j2dFactory kan vervangen
    // en dan voor de rest niks moet doen om van graphics te veranderen (hetzelfde voor input)
    // Dus hier ga je de implementatie van een entity doen. En dan in de game, zeggen van factory.creatHero()
    // En die dan gebruiken om het te kunnen tekenen....
    @Override
    public AbstractFactory createStatus() {
        return null;
    }

//    public j2dContext createContext(){
//        j2dContext grCtx = new j2dContext();
//        return grCtx;
//    }

    @Override
    public AbstractEntity createEntity(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext gr) {
        j2dEntity entity = new j2dEntity(movementComponent, movementSystem, (j2dContext) gr);
        return entity;
    }

    @Override
    public AbstractHero createHero(MovementComponent movementComponent,MovementSystem movementSystem, AbstractContext gr, int width, int height) {
        j2dHero hero = new j2dHero(movementComponent, movementSystem,(j2dContext) gr, width, height);
        return (AbstractHero) hero;
    }

    @Override
    public AbstractStaticPlatform createStaticPlatform(MovementComponent movementComponent, MovementSystem movementSystem, AbstractContext gr, int width, int height){
        j2dStaticPlatform staticPlatform = new j2dStaticPlatform(movementComponent, movementSystem, (j2dContext) gr, width, height);
        return (AbstractStaticPlatform) staticPlatform;
    }

//    @Override
//    public void createMenu(String Strat, AbstractContext gr) {
//
//    }

    @Override
    public AbstractContext createContext(int heroWidth, int heroHeight) {
        j2dContext window = new j2dContext(heroWidth,heroHeight);
        return window;
    }

    @Override
    public menuInterfaceStrat createMenu(String Strat, AbstractContext gr){
        menuInterfaceStrat menu;
        menuContext context = new menuContext();
        if (Strat == "Menu"){
            menu = context.setStrategy(new Menu((j2dContext) gr));
        }
        else if(Strat == "backgroundMenu"){
            menu = context.setStrategy(new backgroundMenu((j2dContext) gr));
        }
        else{
            menu = null;
        }
        //context.executeMenu();
        return menu;
    }

//    @Override
//    public j2dContext createContext() {
//        j2dContext window = new j2dContext();
//        return window;
//    }

    @Override
    public void setGameDimensions(int GameCellx, int GameCellY) {

    }

    @Override
    public void render() {

    }


}
