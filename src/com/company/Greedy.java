package com.company;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Greedy {

    private String result;
    //provide information about order of used nucleotides
    private final List<Integer> used = new ArrayList<>();
    private final Map<Integer,String > resultsFound = new HashMap<>();
    private Vertex previousVertex;


    public Map<Integer, List<Vertex>> getPartialSolutions() {
        return partialSolutions;
    }
    private final Map<Integer, List<Vertex>> partialSolutions = new HashMap<>();
    public Greedy() {

    }

    public String getResult() {
        return result;
    }

    private void useNextVertex(){

    }
    public Pair greedyAlgorithm(){
        Vertex nextVertex;
        List<List<Integer>> tempUsed = new ArrayList<>();
        List<Vertex> iterationSolution = new ArrayList<>();
        //start algorithm with first vertex of the list
        Vertex actualVertex = Main.getVertices().get(0);
        actualVertex.setUsed(true);
        used.add(actualVertex.getId());
        iterationSolution.add(actualVertex);
        String tempResult;
        //for every vertex find solution.
        StringBuilder builder = new StringBuilder();
        builder.append(actualVertex.getMyCode());
        for(int i = 0; i < Main.getVertices().size(); i++)
        {


            //go as deep as you can find next vertex
            while((nextVertex= actualVertex.chooseNextVertex()) != null){
                nextVertex.setPreviousId(actualVertex.getId());
                nextVertex.setUsed(true);
                builder.append(nextVertex.getMyCode().substring(nextVertex.getMyCode().length()-1));
                used.add(nextVertex.getId());
                iterationSolution.add(nextVertex);
                //if there is a dead end
                if(nextVertex.chooseNextVertex() == null){
                    //all of the vertices are used, we found perfect solution
                    if(used.size() == Main.getVertices().size()){
                        return new Pair(builder.toString(),used);
                    }else{
                        //check if previous vertex has another path to check.
                        if(previousVertex.chooseNextVertex() != null){
                            //there is another
                        }else{
                            
                        }
                    }
                }
                //make actual next vertex we chose

                actualVertex = nextVertex;
            }
            // assign to tempResult found solution in this iteration
            tempResult = builder.toString();
            System.out.println(tempResult);
            //set next actual vertex to searching and reset all other vertexes.
            builder.setLength(0);
            //put new restult into map, where integer is starting vertex id and string the result of comparison.
            resultsFound.put(actualVertex.getId(), tempResult);
            //set all used vertices to false for next iteration.
            for (Integer integer : used) {
                Main.getVertices().get(integer).setUsed(false);
            }
            partialSolutions.put(i, List.copyOf(iterationSolution));
            iterationSolution.clear();
            //clear used vertices in interation and make copy in tempUsed list.
            tempUsed.add(List.copyOf(used));
//            tempUsed.forEach((list)-> {
//                list.forEach((k) ->
//                        System.out.print(k + " ")
//            );
//                System.out.println();
//            });
            used.clear();
            //set new actual vertex from the list
            actualVertex = Main.getVertices().get(i);
            actualVertex.setUsed(true);
            used.add(actualVertex.getId());
            builder.append(actualVertex.getMyCode());

        }
        //search for the longest solution found
        AtomicInteger maxLength = new AtomicInteger(0);
        AtomicInteger longestIndex = new AtomicInteger();
        resultsFound.forEach((i, s) -> {
            if(s.length() > maxLength.get()){
                maxLength.set(s.length());
                longestIndex.set(i);
                System.out.println("New longest solution: " + s + " with id: " + i.toString());
            }
        });
        result = resultsFound.get(longestIndex.get());
        int i = 0;
        for(int j = 0; j < tempUsed.size(); j++){
            //if ID of best starting vertex equals first used vertex
            if(longestIndex.get() == tempUsed.get(j).get(tempUsed.get(j).size()-1) ){
                i = j;
            }
        }
        return new Pair(result, tempUsed.get(i));
    }
}
