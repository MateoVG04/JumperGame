package be.antwerpen.mateo.game.visualisation;
import be.antwerpen.mateo.game.context.j2dContext;
import be.antwerpen.mateo.game.logic.*;


public class j2dFactory extends AbstractFactory {
//    @Override
//    public AbstractInput createInput() {
//
//        return null;
//    }

    @Override
    public AbstractInput createInput(j2dContext gr) {
        return null;
    }

    // hier ga je implementeren om bv een creatHero() te maken zodat je in main gewoon de j2dFactory kan vervangen
    // en dan voor de rest niks moet doen om van graphics te veranderen (hetzelfde voor input)
    // Dus hier ga je de implementatie van een entity doen. En dan in de game, zeggen van factory.creatHero()
    // En die dan gebruiken om het te kunnen tekenen....
    @Override
    public AbstractFactory createStatus() {
        return null;
    }

    public j2dContext createContext(){
        j2dContext grCtx = new j2dContext();
        return null;
    }

    @Override
    public AbstractEntity createEntity(int x, int y, int r, int g, int b) {
        j2dEntity entity = new j2dEntity();
        return null;
    }

    @Override
    public AbstractHero createHero(MovementComponent movementComponent,MovementSystem movementSystem) {
        j2dHero hero = new j2dHero(movementComponent, movementSystem);
        return null;
    }

    @Override
    public void setGameDimensions(int GameCellx, int GameCellY) {

    }

    @Override
    public void render() {

    }
}
