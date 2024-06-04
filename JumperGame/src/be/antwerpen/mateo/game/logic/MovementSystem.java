package be.antwerpen.mateo.game.logic;

import java.util.ArrayList;
import java.util.List;

public class MovementSystem {
    private float dx = 0;
    private float dy = 0;
    private int dx_bewegen = Config.getIntProperty("DX_BEWEGEN");
    private int hoogte = Config.getIntProperty("JUMP_HOOGTE");
    private int a =0; // de uitrekkingsfactor bij de parabolische jump
    private float time = 0;
    private long timeJump = Config.getIntProperty("JUMP_TIME"); // 1000 ms => 1s
    private List<CoordinatePoint> platformRemove = new ArrayList<>();
    private List<List<CoordinatePoint>> addCords = new ArrayList<>();
    private int fysicsFormula;
    private int counter=0;
    private boolean heroDied=false;
    private boolean firstJump = true;
    private int ScreenWidth;
    private int ScreenHeight;
    private int scoreAdd = Config.getIntProperty("SCORE_ADD");
    private int aantalLevens = Config.getIntProperty("AANTAL_LEVENS");

    public boolean update(List<MovementComponent> movementComponentList,long deltaT ,Clock clock, AbstractStaticPlatform platform, AbstractHealthScrore healthScore, int ScreenWidth, int ScreenHeight){
        this.ScreenWidth = ScreenWidth;
        this.ScreenHeight = ScreenHeight;
        removePlatformUnderScreen(movementComponentList.get(1).cordList,ScreenHeight);

        float timeOfFrame = Math.max(((float)(1/clock.getFPS())),(float)(deltaT/1000.0));
        MovementComponent heroMove = movementComponentList.get(0);
        heroMove.vx = dx;
        heroMove.vy = dy;
        heroMove.x += heroMove.vx * timeOfFrame;
        heroMove.y += heroMove.vy * timeOfFrame;
        if (this.heroDied){
            heroMove.x = 450;
            heroMove.y = 100;
            this.heroDied = false;
        }

        // als de hero boven een bepaald punt van het scherm komt, moeten alle platformen en de hero zelf ook naar
        // beneden verschoven worden
        if (heroMove.y <= (float)((8/14.0)*1000)){
            updatePlatformPosition(movementComponentList,ScreenHeight,platform,healthScore);
        }

        // check voor elk platform of de hero hier op staat en dus moet gaan jumpen
        // De hero moet zijn toppunt (timeJump/2.0) van de parabool bereikt hebben anders mag er nog geen
        // nieuwe jump komen.
        for (int i = 0; i<movementComponentList.get(1).cordList.size(); i++) {
            for (CoordinatePoint cordPlatform : movementComponentList.get(1).cordList.get(i)) {
                if (heroMove.x < cordPlatform.x + cordPlatform.getWidth() &&
                    heroMove.x + heroMove.width > cordPlatform.x &&
                    heroMove.y + heroMove.height >= cordPlatform.y - 7 &&
                    heroMove.y + heroMove.height <= cordPlatform.y + cordPlatform.getHeight() + 1 &&
                    this.time >= ((float) timeJump) / 2.0) {
                    this.dy = 0;
                    heroJumpFysics(0);
                    this.time = 0;
                }
            }
        }
        addNewPlatforms(movementComponentList.get(1).cordList);
        if (!firstJump) {
            this.time += timeOfFrame;
        }
        heroJumpFysics(this.time);
        this.addCords.clear();
        firstJump = false;
        if (movementComponentList.get(0).x < (int) ((1 / 5.0) * 1000)){ // van links naar rechts teleporten
            movementComponentList.get(0).x = (int) ((4 / 5.0) * 1000) - 10;
        }
        if (movementComponentList.get(0).x > (int) ((4 / 5.0) * 1000)-10){ // van rechts naar links teleporten
            movementComponentList.get(0).x = (int) ((1 / 5.0) * 1000) + 10;
        }
        // als de hero van het scherm gaat/valt, moet je hero dood gaan.
        if ((movementComponentList.get(0).y >= 1000) || (healthScore.getHealth() <= 0)){
            heroDied = true;
        }
        return this.heroDied;
    }

    private void removePlatformUnderScreen(List<List<CoordinatePoint>> cordList, int ScreenHeight){
        // door mijn structuur met de tree, is het veel efficienter om coordinaten onder het scherm te verwijderen
        // nu worden enkel de eerste lagen gecheckt in plaats dat je over alle platformen moet ittereren.
        boolean underScreen = true;
        int index = 0;
        // de eerste lagen in de tree (lijst in lijst) zijn de onderste platformen die het eerste onder het scherm komen
        while (underScreen == true) {
            int index2 = 0;
            while (cordList.get(index).size() > index2){
            //for (CoordinatePoint cord: cordList.get(index)) {
                if (cordList.get(index).get(index2).y > ScreenHeight+10) {
                    cordList.get(index).remove(index2);
                    index2++;
                }
                else{
                    underScreen = false;
                    index2++;
                }
            }
            if (cordList.get(index).isEmpty()){
                cordList.remove(index);
            }
            index++;
        }
    }

    private boolean isHeroOnPlatform(MovementComponent hero, CoordinatePoint platform) {
        return hero.x < platform.x + platform.getWidth() &&
                hero.x + hero.width > platform.x &&
                hero.y + hero.height >= platform.y - 2 &&
                hero.y + hero.height <= platform.y + platform.getHeight() + 2 &&
                dy > 0;
    }

    private void triggerJump(MovementComponent hero) {
        if (time >= (float) timeJump / 2.0) {
            dy = 0;
            heroJumpFysics(0);
            time = 0;
        }
    }

    private void updatePlatformPosition(List<MovementComponent> movementComponentList, int ScreenHeight, AbstractStaticPlatform platform, AbstractHealthScrore healthScrore) {
        int generatorDemper = 75;
        MovementComponent heroMove = movementComponentList.get(0);
        for (int i = 0; i< movementComponentList.get(1).cordList.size(); i++) {
            for (CoordinatePoint cordPlatform : movementComponentList.get(1).cordList.get(i)) {
                if (heroMove.y <= (float) ((5 / 14.0) * ScreenHeight)) {
                    cordPlatform.y += 5;
                    generatorDemper = 100;
                }
                cordPlatform.y += 5;
                counter++;
                int som = 0;
                float deler = 0;
                int averageMaxHeight = 0;
                CoordinatePoint workCord = new CoordinatePoint(0,0, movementComponentList.get(1).cordList.get(0).get(0).getWidth(),movementComponentList.get(1).cordList.get(0).get(0).getHeight());
                for (CoordinatePoint cord: movementComponentList.get(1).cordList.get(movementComponentList.get(1).cordList.size()-1)){
                    som += cord.y;
                    if (workCord.y > cord.y){
                        workCord = cord;
                    }
                }
                averageMaxHeight = (int)(som/deler);
                if ((counter >= generatorDemper) && (averageMaxHeight > ((int)(-ScreenHeight*(5/14.0))))) {
                    this.addCords = platform.generatePlatformLocations(false, new CoordinatePoint(workCord.x, workCord.y, movementComponentList.get(1).width, movementComponentList.get(1).height),healthScrore.getScore());
                    counter = 0;
                }
            }
        }
        if (heroMove.y <= (float) ((5 / 14.0) * ScreenHeight)) {
            heroMove.y += 5;
            healthScrore.addScore(scoreAdd);
        }
        heroMove.y += 5;
        healthScrore.addScore(scoreAdd);
    }

    private void addNewPlatforms(List<List<CoordinatePoint>> cordList) {
        if (!addCords.isEmpty()) {
            cordList.addAll(addCords);
            addCords.clear();
        }
    }


    public void heroJumpFysics(float time){
        // dit is de wiskunde formule die de jump van mijn hero simuleert over de tijd en de hoogte van de jump.
        // -a is een uitrekkings factor, timeJump is de totale tijd van de jump, die deel je door 2 omdat je het toppunt
        // in het midden van je tijdsinterval wilt hebben, time is de variabele (zoals x)
        // en h is de hoogte dat de hero springt.
        // om de dy (=vy = snelheid verticaal) te krijgen, moet je de formule van onze parabool
        a = (int)Math.round(hoogte/(double)(timeJump/2.0));
        //float gravity = 980;
        //a = (int)(gravity / 2.0f);
        fysicsFormula = (int)Math.round(-a*(time - timeJump/2.0))^2 + hoogte;
        this.dy = Math.min(-(float)(Math.round((-2*a*time + a*timeJump)/10.0)),-(float)(Math.round((-2*a*timeJump + a*timeJump)/10.0)));
    }
    public void setVX(int VX){
        this.dx = VX;
    }
//    public void setVY(int VY){
//        this.dy = VY;
//    }

    public void setDirection(Inputs direction){ //, float[] movementComponent){
        switch (direction){
            case LEFT  -> {dx = -dx_bewegen;}
            case RIGHT -> {dx =  dx_bewegen;}
            // dy moet berekent worden in de physics function (parabolische jump)
            default    -> {dx = 0 ; dy =  0;}
        }
    }
}
