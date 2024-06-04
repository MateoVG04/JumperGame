package be.antwerpen.mateo.game.logic;


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
    private int aantalPLatformen = Config.getIntProperty("NUMBER_OF_PLATFORM_GENERATED");
    private int scoreAdd = Config.getIntProperty("SCORE_ADD");
    public AbstractStaticPlatform(MovementComponent movementComponent, MovementSystem movementSystem,int width, int height){
        super(movementComponent,movementSystem);
        this.hero = hero;
        this.width = width;
        this.height = height;
        this.root = new CoordinatePoint((int)Math.round(500 - (width/2.0)),875,this.width,this.height);
        this.cordList = generatePlatformLocations(true,root,0);
        this.movementComponent = movementComponent;
        this.movementComponent.cordList = this.cordList;
        this.movementSystem = movementSystem;
    }
    public List<List<CoordinatePoint>> getCordList(){
        return this.cordList;
    }

    public List<List<CoordinatePoint>> generatePlatformLocations(boolean isFirstSet, CoordinatePoint root,int Score){
        // lag: mogelijks blijven ze nieuwe schermen met platformen genereren => lagg
        // waarom gebruik je een lijst in een lijst en houd je die bij ipv enkel de bovenste lijst te gebruiken voor nieuwe
        // platform generatie -> antwoord: ik ga dit gebruiken voor het verwijderen van platformen die onder het scherm
        // komen, door enkel de laagste laag van de tree te checken omdat hier wss de platformen de ondeer het scherm zijn
        // ipv alle platformen te checken.
        int count = 0; // count in de while lus zodat er niet een platform out of bound is generated.
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = toolkit.getScreenSize();
//        int ScreenHeight = ScreenHeight;
//        int ScreenWidth = screenSize.width;
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
        PlatformTree<List<List<CoordinatePoint>>> platformTree = new PlatformTree<>(children.get(0).get(0));
        //nodeList.clear();
        int i = 0;
        //int j = 0;
        int averageMaxHeight = 0;
        while (averageMaxHeight > (-1000*0.5)){
            averageMaxHeight = 0;
        //while (children.size() < 10){
            if (!children.isEmpty() && !children.get(children.size()-1).isEmpty()) {
                for (CoordinatePoint cord : children.get(children.size()-1)){
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

                // aantal platformen genereren (random)
                // dit werkt goed, alleen als het "aantalPlaformen" kleiner is dan 3 (dus 1 of 2) komt het soms voor
                // dat er out of bounds een platform met x=0 wordt gegenereert. Ik vind niet hoe dit komt, en het
                // beïnvloed de game (buiten visueel) niet dus het is niet zo erg.
                if (aantalPLatformen !=1) {
                    // we doen 1000*scoreAdd om rekening te houden met de scoreADD die je in de config kan instellen
                    // Als je dus sneller score bijkrijgt dan moet je ook in verhouding een grotere score hebben
                    // voordat het moeilijker wordt doordat er minder platformen spawnen
                    if (Score <= 2000*scoreAdd) {
                        numPlatforms = random.nextInt(1, aantalPLatformen);
                    }
                    else if (Score > 1000*scoreAdd){
                        if (aantalPLatformen - (int)((1/3)*aantalPLatformen) == 1){
                            numPlatforms = 1;
                        }
                        else {
                            numPlatforms = random.nextInt(1, aantalPLatformen - (int) ((1 / 3.0) * aantalPLatformen));
                        }
                    }
                    else{
                        numPlatforms = 1;
                    }
                }
                else{
                    numPlatforms = 1;
                }

                platformsGenerated = 0;
                if (werkerLijst.size() >= 5){
                    break;
                }
                boolean collision = false;
                //System.out.println(i);
                int crashDetect = 0;
                while (platformsGenerated < numPlatforms) {
                    //if (isFirstSet) {
                    randx = 0;
                    count = 0;
                    boolean outOfBound = false;
                    int minRange = children.get(i).get(j).x - (2 * width);
                    int maxRange = children.get(i).get(j).x + (2 * width);
                    int excludedMin = (int) ((1 / 5.0) * 1000) + 10;
                    int excludedMax = (int) ((4 / 5.0) * 1000) - width - 10;
                    int boundL = excludedMin;
                    int boundR = excludedMax-((int)(width/2.0)); // 715

                    randx = random.nextInt(maxRange - minRange + 1) + minRange;
                    if (randx <= excludedMin){ // eerst proberen met deze random generator om een nieuw platform te krijgen
                        randx = random.nextInt(330,595);
                        outOfBound = true;
                    }
                    else if (randx >= excludedMax-((int)(width/2.0))){
                        outOfBound = true;
                        randx = random.nextInt(230,695);
                    }

                    // 140 is de hoogte dat de hero comfortabel kan springen. Hierdoor zijn alle platformen haalbaar.
                    randy = random.nextInt(children.get(i).get(j).y - 140, children.get(i).get(j).y - 50);
                    // als het out of bound was wordt er een platform ergens random binnen de bounds gegenereert.£
                    // Maar dit platform kan heel ver van het vorige platform liggen omdat het random is...
                    // Daarom maak ik in dit geval de hoogte van het platform ten opzichte van het vorige platform veel
                    // kleiner zodat je een gemakkelijkere jump krijgt.
                    if (outOfBound){
                        randy = random.nextInt(children.get(i).get(j).y - 75, children.get(i).get(j).y - 25);
                    }
                    if ((randx <= (int) ((1 / 5.0) * 1000) + 10) || (randx >= (int) ((4 / 5.0) * 1000) - width - 10)){
                        collision = true;
                        crashDetect++;
                        if (crashDetect >= 100){
                            collision = false;
                            // er platformen buiten de bounds getekend worden. MOgelijks op te lossen
                            // door hier dan handmatig platformen in het midden te tekenen.
                        }
                        //break;
                    }
                    else {
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
