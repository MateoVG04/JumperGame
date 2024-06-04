package be.antwerpen.mateo.game.visualisation;

import be.antwerpen.mateo.game.logic.AbstractContext;
import be.antwerpen.mateo.game.visualisation.j2dContext;
import be.antwerpen.mateo.game.logic.AbstractInput;
import be.antwerpen.mateo.game.logic.Inputs;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.LinkedList;

public class Input extends AbstractInput {
    //public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE,ESC};

    public Input(j2dContext gr) {
        keyInputs = new LinkedList<>();
        gr.getFrame().addKeyListener(new KeyInputAdapter());
    }
//    public boolean inputAvailable() {
//        if (keyInputs.size()>0) {
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

    @Override
    public void setContext(AbstractContext gr) {
        if (gr instanceof j2dContext) {
            j2dContext j2d = (j2dContext) gr;
            j2d.getFrame().addKeyListener(new KeyInputAdapter());
        } else {
            //throw new IllegalArgumentException("visualisation/Input/line39 not an instance of j2dContext");
            System.out.println("Error: visualisation/Input/line39 not an instance of j2dContext");
        }
    }
//    @Override
//    public Inputs getInput() {
//        return keyInputs.poll();
//    }

    // heeft geen toepassing denkk, dus mag weg
    @Override
    public void InputListener() {

    }

    class KeyInputAdapter extends KeyAdapter {
        private boolean leftPressed = false;
        private boolean rightPressed = false;

        @Override
        public void keyPressed(KeyEvent e) {
//            switch (e.getID()) {
//                case KeyEvent.KEY_PRESSED:
//                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                        leftPressed = true;
//                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                        rightPressed = true;
//                    }
//                    break;
//                case KeyEvent.KEY_RELEASED:
//                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                        leftPressed = false;
//                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                        rightPressed = false;
//                    }
//                    break;
//            }
//            keyStillPressed();
//            }

            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    keyInputs.add(Inputs.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    keyInputs.add(Inputs.RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    keyInputs.add(Inputs.DOWN);
                    break;
                case KeyEvent.VK_UP:
                    keyInputs.add(Inputs.UP);
                    break;
                case KeyEvent.VK_SPACE:
                    keyInputs.add(Inputs.SPACE);
                    break;
                case KeyEvent.VK_ESCAPE:
                    keyInputs.add(Inputs.ESC);
                    break;
                case KeyEvent.VK_ENTER:
                    keyInputs.add(Inputs.ENTER);
                default:
                    break;
            }
        }
            // Werken met statemachine die wisselt tussen pressed van bv.: links. Zolang dat die pressed is, hoe je
            // de key bij. Vanaf dat het event unpressed gebeurt gooi je die key weg. Hierdoor zou het niet
            // uitmaken aan welke FPS je werkt, en zou je altijd in de zelfde tijd van het drukken van de
            // key een zelfde afstand afleggen. Stel je keyboard werkt aan 60 FPS maar je game tegen 120 FPS,
            // ga je maar 1 frame na de 60 FPS een input key hebben die een heel kleine stap zet. Maar door met
            // pressed en unpressed te werken ga je veel meer van die frames een input hebben en dus in die tijd
            // meerdere kleine stappen maken en "smoother" een beweging maken.

//            public void keyStillPressed() {
//                if (this.leftPressed){
//                    keyInputs.add(Inputs.LEFT);
//                }
//                else if (this.rightPressed){
//                    keyInputs.add(Inputs.RIGHT);
//                }
//
//
//            }
        }
    }


