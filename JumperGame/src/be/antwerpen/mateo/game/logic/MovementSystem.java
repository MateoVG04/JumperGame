package be.antwerpen.mateo.game.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MovementSystem {
    private float dx = 0;
    private float dy = 0;
    private int hoogte =3000;
    private int a =0;
    private float time = 0;
    private long timeJump = 1; // 1000 ms => 1s
    private List<CoordinatePoint> platformRemove = new ArrayList<>();
    private List<List<CoordinatePoint>> addCords = new ArrayList<>();
    private int fysicsFormula;
    private int counter=0;
    private boolean newPlatforms=false;
    private boolean firstJump = true;
//    public void update(AbstractHero hero, float deltaT){
//            MovementComponent heroMove = hero.movementComponent;
//            heroMove.vx = dx;
//            heroMove.vy = dy;
//            heroMove.x += heroMove.vx * deltaT;
//            heroMove.y += heroMove.vy * (deltaT/10.0);
//    }

    public boolean update(List<MovementComponent> movementComponentList,long deltaT ,Clock clock, AbstractStaticPlatform platform){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int ScreenHeight = screenSize.height;
        this.newPlatforms = false;
        removePlatformUnderScreen(movementComponentList.get(1).cordList,ScreenHeight);
//        System.out.println(movementComponentList.get(1).cordList.size());

//        System.out.println("(MS) size cordlist: "+movementComponentList.get(1).cordList.size());
        float timeOfFrame = Math.max(((float)(1/clock.getFPS())),(float)(deltaT/1000.0));
        MovementComponent heroMove = movementComponentList.get(0);
        heroMove.vx = dx;
        heroMove.vy = dy;
        heroMove.x += heroMove.vx * timeOfFrame;
        heroMove.y += heroMove.vy * timeOfFrame;
        //System.out.println(heroMove.y);

        // GOED VOOR TE DEBUGGEN
//        System.out.println("(MoveSys) heroX: "+heroMove.x+", heroY: "+heroMove.y+", heroVX: "+heroMove.vx+", heroVY: "+heroMove.vy+", timeOfFrame: "+timeOfFrame+", timeJump: "+this.time);
        int generatorDemper = 75; // als de platformen naar beneden gaan en er nieuw platformen net boven het scherm gegenereert
                                  // moeten worden heb ik het probleem dat er zo veel platformen gegenereert worden
                                  // dat het onmogelijk is om nog te vallen. Dus met deze demper zorg ik ervoor dat
                                  // enkel om de zo veel tijd een platform gegenereert wordt.
        if (heroMove.y <= (float)((8/14.0)*ScreenHeight)){
            updatePlatformPosition(movementComponentList,ScreenHeight,platform);
        }


        // check voor elk platform of de hero hier op staat en dus moet gaan jumpen
        for (int i = 0; i<movementComponentList.get(1).cordList.size(); i++) {
            for (CoordinatePoint cordPlatform : movementComponentList.get(1).cordList.get(i)) {
                //cordPlatform.printCord();
//            if (heroMove.y <= (float)((8/14.0)*ScreenHeight)){
//                cordPlatform.y += 5;
//                this.newPlatforms = true;
//                counter++;
//                if (counter >= 75) {
//                    this.addCords = platform.generatePlatformLocations(false);
//                    counter = 0;
//                }
//            }
                if (heroMove.x < cordPlatform.x + cordPlatform.getWidth() &&
                        heroMove.x + heroMove.width > cordPlatform.x &&
                        heroMove.y + heroMove.height >= cordPlatform.y - 7 &&
                        heroMove.y + heroMove.height <= cordPlatform.y + cordPlatform.getHeight() + 1 &&
                        this.time >= ((float) timeJump) / 2.0) {
//            int offset = 10;
//            if ((heroMove.x + heroMove.width - offset >= cordPlatform.x)&&
//                    (heroMove.y <= cordPlatform.y +18 &&
//                    (heroMove.x + offset <= cordPlatform.x + cordPlatform.getWidth() &&
//                    heroMove.y -10 >= cordPlatform.y))){
                    //Math.round(heroMove.y) + heroMove.height == cordPlatform.y){
                    this.dy = 0;
                    heroJumpFysics(0);
                    this.time = 0;
                    //System.out.println("(MoveSys) new jump");
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
        return this.newPlatforms;
    }

    private void removePlatformUnderScreen(List<List<CoordinatePoint>> cordList, int ScreenHeight){
//        for (int i = 0; i<cordList.size(); i++) {
//            for (CoordinatePoint cordPlatform : cordList.get(i)) {
//                if (cordPlatform.y > ScreenHeight) {
//                    this.platformRemove.add(cordPlatform);
//                    //System.out.println();
//                    //cordPlatform.printCord();
//                    //System.out.println("(MS) platform removed");
//                }
//            }
//        }
        //System.out.println("(MS) removePlatformUnderScreen()");
        if (cordList.get(0).get(0).y > 2*ScreenHeight){
            System.out.println("(MS) lowest branches deleted");
            System.out.println(cordList.get(0).size());
            cordList.remove(0);
        }
//        if ((this.platformRemove != null) || (!this.platformRemove.isEmpty())) {
//            for (CoordinatePoint cord: this.platformRemove){
//                cordList.remove(cord);
//                //System.out.println("def removed");
//            }
//        }
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

    private void updatePlatformPosition(List<MovementComponent> movementComponentList, int ScreenHeight, AbstractStaticPlatform platform) {
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
                this.newPlatforms = true;
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
                    System.out.println("(MS) Call platformsGen");
                    this.addCords = platform.generatePlatformLocations(false, new CoordinatePoint(workCord.x, workCord.y, movementComponentList.get(1).width, movementComponentList.get(1).height));
                    System.out.println("(MS) new size tree: "+this.addCords.size());
                    System.out.println("(MS) full size tree: "+movementComponentList.get(1).cordList.size());
                    System.out.println("(MS) highest branch size: "+movementComponentList.get(1).cordList.get(movementComponentList.get(1).cordList.size()-1).size());
                    counter = 0;
                }
            }
        }
        if (heroMove.y <= (float) ((5 / 14.0) * ScreenHeight)) {
            heroMove.y += 5;
        }
        heroMove.y += 5;
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
       // System.out.println(-(float)(Math.round((-2*a*1 + a*timeJump)/10.0)));
        fysicsFormula = (int)Math.round(-a*(time - timeJump/2.0))^2 + hoogte;
        //System.out.println((int)Math.round(-a*(time - timeJump/2.0))^2 + hoogte);
        this.dy = Math.min(-(float)(Math.round((-2*a*time + a*timeJump)/10.0)),-(float)(Math.round((-2*a*1 + a*timeJump)/10.0)));
        //System.out.println(-(float)(Math.round((-2*a*time + a*timeJump)/10.0)));
        //System.out.println(dy);
        if (time == 0){
            this.dy -= 0; // omdat ik vind dat die in het begin een te grote versnelling heeft en dus te hoog springt
                           // na een nieuwe jump
        }
    }
    public void setVX(int VX){
        this.dx = VX;
    }
//    public void setVY(int VY){
//        this.dy = VY;
//    }

    public void setDirection(Inputs direction){ //, float[] movementComponent){
        switch (direction){
            case LEFT  -> {dx = -700;}
            case RIGHT -> {dx =  700;}
            //case UP    -> {dx = 0 ; dy = -10;}
            //case DOWN  -> {dx = 0 ; dy =  10;}
            default    -> {dx = 0 ; dy =  0;}
        }
    }
}
