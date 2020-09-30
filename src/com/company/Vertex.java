package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vertex contains acttual vertex we are in and next possible vertexes.
 */
public class Vertex {
    //my ID
    private final int id;
    //ID of previous vertex which chose me.
    private int previousId;
    //String contains my sequence of nucleotides
    private final String myCode;
    //Map contains every Id of vertexes which has Lovenstein lenght equals 0. Boolean value means if this vertex was visited from this vertex.
    private final List<Vertex> nextVertexes;
    private boolean used = false;

    public void findNext(List<Vertex> searched){
        String mySufix = myCode.substring(1);
        for(int i = 0; i < searched.size(); i++){
            //check every vertex instead of us
            if(i != this.id){
                String prefix = searched.get(i).myCode.substring(0,searched.get(i).myCode.length()-1);
                if(Main.Copute(mySufix,prefix) == 0){
                    nextVertexes.add(Main.getVertices().get(i));
                    System.out.println("Added new Vertex to nextVertexes: " + nextVertexes.get(nextVertexes.size()-1).myCode);
                }
            }
        }

    }
    public Vertex chooseNextVertex(){
        for(int i = 0; i < nextVertexes.size(); i++){
            if(!nextVertexes.get(i).isUsed()){
                return nextVertexes.get(i);
            }
        }
        return null;
    }
    public Vertex(int id, String myCode) {
        this.id = id;
        this.myCode = myCode;
        this.nextVertexes = new ArrayList<>();
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public int getPreviousId() {
        return previousId;
    }

    public void setPreviousId(int previousId) {
        this.previousId = previousId;
    }

    public String getMyCode() {
        return myCode;
    }

    public List<Vertex> getNextVertexes() {
        return nextVertexes;
    }
}
