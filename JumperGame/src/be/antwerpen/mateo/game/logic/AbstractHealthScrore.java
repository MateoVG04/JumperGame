package be.antwerpen.mateo.game.logic;

public abstract class AbstractHealthScrore {
    private int score = 0;
    private int health = 3;
//    public AbstractHealthScrore(int health, int score){
//        this.score = score;
//        this.health = health;
//    }
    public AbstractHealthScrore(int health, int score){
        this.score = score;
        this.health = health;
    }

    public void takeHealth(int health){
        this.health -= health;
    }
    public void addScore(int score){
        this.score += score;
    }
    public int getHealth(){
        return this.health;
    }
    public void setHealth(int damage){
        this.health -= damage;
    }
    public int getScore(){
        return this.score;
    }
    abstract public void draw();
}
