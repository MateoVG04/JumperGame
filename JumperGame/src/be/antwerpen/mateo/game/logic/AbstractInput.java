package be.antwerpen.mateo.game.logic;

import be.antwerpen.mateo.game.input.Input;

import java.util.LinkedList;

public abstract class AbstractInput {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE, ESC};
    private LinkedList<Inputs> KeyInputs;
    public Inputs getInput(){
        return KeyInputs.poll();
    }
    //public Input.Inputs getInput(){
        //return KeyInputs.poll();
    //}
    public void InputListener(){
        KeyInputs = new LinkedList<Inputs>();
    }
    public boolean inputAvailable(){
        return KeyInputs.size() > 0;
    }


}
