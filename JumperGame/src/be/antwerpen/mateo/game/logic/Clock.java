package be.antwerpen.mateo.game.logic;
public class Clock {
    private int clock;
    private int FPS=0;
    private long start;

    public Clock(){
        start = 0;
    }

    public long calculateDeltaT(boolean start){
        if (start){
            long startTime = System.currentTimeMillis();
            this.start = startTime;
            return startTime;
        }
        else{
            long stopTime = System.currentTimeMillis();
            long deltaT = stopTime - this.start;
            this.start =0;
            return deltaT;
        }
    }
}
