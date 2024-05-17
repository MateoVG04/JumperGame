package be.antwerpen.mateo.game.logic;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class AbstractStaticPlatform extends AbstractEntity{
    private int width;
    private int height;
    private boolean eerstePlatform = true;
    private List<List<CoordinatePoint>> cordList;
    private MovementComponent movementComponent;
    private MovementSystem movementSystem;
    private CoordinatePoint root;
    private AbstractHero hero;
    public AbstractStaticPlatform(MovementComponent movementComponent, MovementSystem movementSystem,int width, int height){
        super(movementComponent,movementSystem);
        this.hero = hero;
        this.width = width;
        this.height = height;
        this.root = new CoordinatePoint((int)Math.round(500 - (width/2.0)),875,this.width,this.height);
        this.cordList = generatePlatformLocations(true,root);
        this.movementComponent = movementComponent;
        this.movementComponent.cordList = this.cordList;
        this.movementSystem = movementSystem;
    }
    public List<List<CoordinatePoint>> getCordList(){
        return this.cordList;
    }
//    public List<CoordinatePoint> generatePlatformLocation(boolean isFirstSet){
//        // de boolean isFirstSet checkt of het de start platformen zijn die bij de start van de game worden gemaakt, of
//        // dat het nieuwe platformen zijn die gegenereert moeten worden omdat de hero boven 3/4 van het scherm is
//        // gejumped
//
//        // dit moet ik later uitbreiden, als de score hoger is, dat er stelsel matig minder en minder platformen gegenereerd
//        // worden. Dit hangt ook af van het movementSysteem van de hero, als die verder/hoger kan springen moet dit nog
//        // aangepast worden.
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = toolkit.getScreenSize();
//        int ScreenHeight = screenSize.height;
//        //System.out.println("(AbstPlatform) Screen height: "+ ScreenHeight);
//        this.coordinateList = new ArrayList<>();
//        Random random = new Random();
//        int numPlatforms;
//        int platformsGenerated=0;
//        int j=0;
//        int randx,randy;
//        if (isFirstSet) {
//            numPlatforms = random.nextInt(18, 24);  // Generate between 18 to 23 platforms
//            //System.out.println(numPlatforms);
//        }
//        else{
//            numPlatforms = random.nextInt(1,2); // generate between 1 to 1 platforms
//        }
//
//        while (platformsGenerated < numPlatforms) {
//            randx = random.nextInt((int) ((1 / 5.0) * 1000) + 10, (int) ((4 / 5.0) * 1000) - width - 10);
//            if (isFirstSet) {
//                randy = random.nextInt(10, 870);
//            }
//            else{
//                //System.out.println("(AbstPlatform) randy: "+ (int) ( (float)((ScreenHeight*(1/5.0))+10) ));
//                randy = random.nextInt(-200, -height);
//            }
//
//            if (!eerstePlatform) {
//                boolean collision = false;
//                for (CoordinatePoint el : coordinateList) {
//                    // Check for overlap
//                    if (randx < el.x + width + 10 && randx + width + 10 > el.x &&
//                            randy < el.y + height - 10 && randy + height + 10 > el.y) {
//                        collision = true;
//                        //break;
//                    }
//                }
//                if (!collision) {
//                    coordinateList.add(new CoordinatePoint(randx, randy,this.width,this.height));
//                    j++;
//                    platformsGenerated++;
//                }
//                else{
//                    if (isFirstSet) {
//                        numPlatforms++;
//                    }
//
//                    //System.out.println(numPlatforms);
//                }
//            } else {
//                // Place the first platform at a fixed position
//                randx = (int)Math.round(500 - (width/2.0));
//                randy = 875;
//                eerstePlatform = false;
//                coordinateList.add(new CoordinatePoint(randx, randy,this.width,this.height));
//                platformsGenerated++;
//            }
//            //coordinateList.get(j).printCord();
//            //System.out.println("(abstPlatform) #platf gen: "+ platformsGenerated+", numplatform: "+numPlatforms);
//
//        }
//        return coordinateList;
//    }

    public List<List<CoordinatePoint>> generatePlatformLocations(boolean isFirstSet, CoordinatePoint root){
        // lag: mogelijks blijven ze nieuwe schermen met platformen genereren => lagg
        // waarom gebruik je een lijst in een lijst en houd je die bij ipv enkel de bovenste lijst te gebruiken voor nieuwe
        // platform generatie -> antwoord: ik ga dit gebruiken voor het verwijderen van platformen die onder het scherm
        // komen, door enkel de laagste laag van de tree te checken omdat hier wss de platformen de ondeer het scherm zijn
        // ipv alle platformen te checken.

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int ScreenHeight = screenSize.height;
        //System.out.println("(AbstPlatform) Screen height: "+ ScreenHeight);
        List<List<CoordinatePoint>> children = new ArrayList<>();
        Random random = new Random();
        int numPlatforms;
        int platformsGenerated=0;
        int randx,randy;
        List<CoordinatePoint> nodeList = new ArrayList<>();
        nodeList.add(root);
        children.add(deepCopyListOfList(nodeList));
        nodeList.clear();
        children.get(0).get(0).printCord();
        PlatformTree<List<List<CoordinatePoint>>> platformTree = new PlatformTree<>(children.get(0).get(0));
        //nodeList.clear();
        int i = 0;
        //int j = 0;
        int averageMaxHeight = 0;
        while (averageMaxHeight > (-ScreenHeight*0.5)){
            averageMaxHeight = 0;
        //while (children.size() < 10){
            System.out.println("(Pltfrm): (crash)"+children.get(children.size()-1).size());
            if (!children.isEmpty() && !children.get(children.size()-1).isEmpty()) {
                for (CoordinatePoint cord : children.get(children.size()-1)){
                    System.out.println(cord.y);
                    averageMaxHeight += cord.y;
                }
                averageMaxHeight = (int)(averageMaxHeight/(float)(children.get(children.size()-1).size()));
                //System.out.println(averageMaxHeight);
            }
        //for (int i=0; i<children.size(); i++){
            //System.out.println("(AbsPLatf) size list: "+children.size()+", size leafs: "+children.get(i).size());
            List<CoordinatePoint> werkerLijst = new ArrayList<>();
            int j = 0;
            while (j<children.get(i).size()){
                //List<CoordinatePoint> werkerLijst = new ArrayList<>();

            //for (int j=0; j<children.get(i).size();j++) {
                if (children.size() >4){
                    numPlatforms = random.nextInt(1,3);  // mogelijks kan het voorkomen met heel kleine kans dat
                                                                     // leafs 0 children krijgen => stopt de game
                                                                     // maar heel onwaarschijnlijk
                }
                else {
                    numPlatforms = random.nextInt(1, 3);
                }
                platformsGenerated = 0;
                System.out.println(werkerLijst.size()>= 5);
                if (werkerLijst.size() >= 5){
                    break;
                }
                boolean collision = false;
                //System.out.println(i);
                int crashDetect = 0;
                while (platformsGenerated < numPlatforms) {
                    //if (isFirstSet) {
                        randx = random.nextInt(children.get(i).get(j).x - (2 * width), children.get(i).get(j).x + (2 * width));
                        randy = random.nextInt(children.get(i).get(j).y - 150, children.get(i).get(j).y - 50);
                        if ((randx <= (int) ((1 / 5.0) * 1000) + 10) || (randx >= (int) ((4 / 5.0) * 1000) - width - 10)){
                            collision = true;
                            System.out.println("Crash detect1");
                            crashDetect++;
                            System.out.println("value crash: "+crashDetect);
                            if (crashDetect >= 100){
                                collision = false;
                                System.out.println("crashDetect used"); // dit lost het wel op, maar zorgt er wel voor dat
                                // er platformen buiten de bounds getekend worden. MOgelijks op te lossen
                                // door hier dan handmatig platformen in het midden te tekenen.
                            }
                            //break;
                        }
                        else {
                            System.out.println("Crash detect2");
                            if (!werkerLijst.isEmpty()) {
                                for (CoordinatePoint el : werkerLijst) {
                                    // Check for overlap
                                    if (randx < el.x + width + 10 && randx + width + 10 > el.x &&
                                            randy < el.y + height - 10 && randy + height + 10 > el.y) {
                                        collision = true;
                                        //break;
                                    }
                                }
                            }
                        }
                        if (!collision) {
                            CoordinatePoint cord = new CoordinatePoint(randx, randy, width, height);
                            werkerLijst.add(cord);
                            //j++;
                            platformsGenerated++;
                        }
                        collision = false;
    //                            CoordinatePoint cord = new CoordinatePoint(randx, randy, width, height);
    //                            werkerLijst.add(cord);
    //                            platformsGenerated++;
                    //}
                    //else {

                    //}
                }
                j++;
            }
            System.out.println("test");
            //System.out.println(werkerLijst.size());
            children.add(werkerLijst);
            //werkerLijst.clear();
            i++;
            //children.add(nodeList);
        }
        platformTree.children = deepCopyListOfLists(children); // maak gebruik van een deepCopy omdat je anders
                                                               // gwn 2 pointers hebben die naar hetzelfde achterliggende
                                                               // geheugen wijzen, en als je dus 1 cleared dit ook
                                                               // voor de andere cleared. Maar als je dit niet cleared
                                                               // krijg je uiteindelijk een heap out of memory error => game crashed
        if (!children.isEmpty()) {
            children.clear();
        }
        System.out.println("Platf crash test");
        return platformTree.children;
    }

    public List<List<CoordinatePoint>> deepCopyListOfLists(List<List<CoordinatePoint>> original) {
        List<List<CoordinatePoint>> copy = new ArrayList<>();
        for (List<CoordinatePoint> sublist : original) {
            List<CoordinatePoint> sublistCopy = new ArrayList<>(sublist);  // This copies the sublist
            copy.add(sublistCopy);
        }
        return copy;
    }
    public List<CoordinatePoint> deepCopyListOfList(List<CoordinatePoint> original) {
        List<CoordinatePoint> copy = new ArrayList<>();
        for (CoordinatePoint sublist : original) {// This copies the sublist
            copy.add(sublist);
        }
        return copy;
    }

    abstract public void draw();
}
