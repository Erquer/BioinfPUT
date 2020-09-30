package com.company;

import java.util.*;

public class Greedy {
    private final Data toFind;
    private String result;
    //provide information about order of used nucleotides
    private final List<Integer> used = new ArrayList<>();
    private final int stringLength;
    private Vertex actualVertex;

    public Greedy(Data toFind, int stringLength) {
        this.toFind = toFind;
        this.stringLength = stringLength;
    }

    public String getResult() {
        return result;
    }

    public void greedyAlgorithm(){
        Vertex nextVertex;
        //choosing starting vertex.
        //start with first vertex in the list
        actualVertex = Main.getVertices().get(0);
        actualVertex.setUsed(true);
        used.add(actualVertex.getId());

        //choose next vertex to solution.
        if((nextVertex = actualVertex.chooseNextVertex()) != null){
            //nextVertex exists.
            nextVertex.setPreviousId(actualVertex.getId());
            nextVertex.setUsed(true);
            actualVertex = nextVertex;
            used.add(actualVertex.getId());
        }else{
            //nextVertex doesnt exists.
            if(used.size() == Main.getVertices().size()){
                //when we used all of the vertices, algorithm stops
                return;
            }else{
                //algorithm didnt finish the work, not every vertex is used.


            }
        }




    }
}
