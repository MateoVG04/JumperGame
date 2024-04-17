package be.antwerpen.mateo.game.input;

import be.antwerpen.mateo.game.context.j2dContext;
import be.antwerpen.mateo.game.logic.AbstractInput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Input extends AbstractInput {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE,ESC};
    private LinkedList<Inputs> keyInputs;

    public Input(j2dContext gr) {
        gr.getFrame().addKeyListener(new KeyInputAdapter());
        keyInputs = new LinkedList<Inputs>();
    }
    public boolean inputAvailable() {
        return keyInputs.size() > 0;
    }
    public Inputs getInput() {
        return keyInputs.poll();
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

