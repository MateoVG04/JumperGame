package be.antwerpen.mateo.game.logic;

import java.util.LinkedList;
import be.antwerpen.mateo.game.logic.Inputs;

public abstract class AbstractInput {
    //public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE, ESC};
    protected LinkedList<Inputs> keyInputs;

    //public abstract Inputs getInput();
    public abstract void InputListener();
    public boolean inputAvailable(){
        if (keyInputs.size()>0) {
            //System.out.println("keyInput (AbsInput): "+ keyInputs.size());
            return true;
        }
        else{
            return false;
        }
    }
    public Inputs getInput() {
        Inputs key = keyInputs.poll();
        while(!keyInputs.isEmpty()){
            keyInputs.poll();
            //System.out.println("keyInput (AbsInput): "+ keyInputs.size());
        }
        return key;
    }
    public abstract void setContext(AbstractContext gr);
}
