package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractContext;
import be.antwerpen.mateo.game.visualisation.j2dContext;
import be.antwerpen.mateo.game.logic.AbstractInput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Input extends AbstractInput {
    //public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE,ESC};
    private LinkedList<Inputs> keyInputs;

    public Input() {
        keyInputs = new LinkedList<Inputs>();
    }

    public void setContext(j2dContext gr){
        gr.getFrame().addKeyListener(new KeyInputAdapter());
    }
    public boolean inputAvailable() {
        if (keyInputs.size()>0) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void setContext(AbstractContext gr) {
        if (gr instanceof j2dContext){
            j2dContext j2d = (j2dContext) gr;
            j2d.getFrame().addKeyListener(new KeyInputAdapter());
        }
        else{
            //throw new IllegalArgumentException("visualisation/Input/line39 not an instance of j2dContext");
            System.out.println("Error: visualisation/Input/line39 not an instance of j2dContext");
        }
    }

    public Inputs getInput() {
        return keyInputs.poll();
    }

    @Override
    public void InputListener() {

    }

    class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT : keyInputs.add(Inputs.LEFT);  break;
                case KeyEvent.VK_RIGHT: keyInputs.add(Inputs.RIGHT); break;
                case KeyEvent.VK_DOWN : keyInputs.add(Inputs.DOWN);  break;
                case KeyEvent.VK_UP   : keyInputs.add(Inputs.UP);    break;
                case KeyEvent.VK_SPACE: keyInputs.add(Inputs.SPACE); break;
                case KeyEvent.VK_ESCAPE:keyInputs.add(Inputs.ESC);   break;
            }
        }
    }
}

