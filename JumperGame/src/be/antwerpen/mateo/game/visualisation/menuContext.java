package be.antwerpen.mateo.game.visualisation;

public class menuContext {
    private menuInterfaceStrat Strat;
    public menuInterfaceStrat setStrategy(menuInterfaceStrat strategy){
        this.Strat = strategy;
        return Strat;
    }
    public void executeMenu(){
        this.Strat.execute();
    }
}
