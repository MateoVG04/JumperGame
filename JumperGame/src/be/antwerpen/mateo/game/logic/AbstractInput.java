package be.antwerpen.mateo.game.logic;

import java.util.LinkedList;

public abstract class AbstractInput {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE, ESC};
    private LinkedList<Inputs> KeyInputs;
    public abstract Inputs getInput();
    public abstract void InputListener();
    public abstract boolean inputAvailable();
    public abstract void setContext(AbstractContext gr);
}
