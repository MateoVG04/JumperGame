package be.antwerpen.mateo.game.logic;

import java.util.ArrayList;
import java.util.List;

public class PlatformTree<T> {
    // T is for generics, voor typesafety en flexibiliteit
    CoordinatePoint cord;
    List<List<CoordinatePoint>> children;
    public PlatformTree(CoordinatePoint cordPlatform){
        this.cord = cordPlatform;
        this.children = new ArrayList<>();
    }

    public void addChildren(List<List<CoordinatePoint>> children){
        this.children.addAll(children);
    }

    public CoordinatePoint getRoot(){
        return this.children.get(0).get(0);
    }


}
