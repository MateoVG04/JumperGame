package be.antwerpen.mateo.game.logic;

public class Clock {
    private int clock;
    private double FPS;
    private long start;

    public Clock(){
        start = 0;
        FPS = 30;
    }

    public double getFPS(){
        return this.FPS;
    }

    public long calculateDeltaT(boolean start){
        if (start){
            this.start = System.currentTimeMillis();
            return this.start;
        }
        else{
            long stopTime = System.currentTimeMillis();
            long deltaT = stopTime - this.start;
            //this.start =0;
            return deltaT;

        }
    }

    public void FixedFPS(long deltaT){
        long timeToWait = Math.round((1/FPS)*1000) - deltaT;
        try {
            Thread.sleep(Math.max(0,timeToWait));
            //Thread.sleep(1000);
        }catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
