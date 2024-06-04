package be.antwerpen.mateo.game.visualisation;

public class menuContext {
    private menuInterfaceStrat Strat;
    private int state=0;
    public menuInterfaceStrat setStrategy(menuInterfaceStrat strategy,int state){
        this.Strat = strategy;
        this.state = state;
        return Strat;
    }
    public void executeMenu(){
        this.Strat.execute(state);
    }
}
