package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generic {
    //best solution found.
    private String finalResult;
    private Pair best;
    //list of recently found solutions
    //Lovenstein distance between algorithm solution and source sequence ( used for testing algorithm)
    private Integer score;
    private Map<Integer, List<Vertex>> population = new HashMap<>();
    //greedy algorithm to create one best solution and population to new solutions.

    Generic(Pair bestSolution){
        best = bestSolution;
        score = Integer.MAX_VALUE;
        population = Main.greedy.getPartialSolutions();
    }

    //main function in class.
    public void genericAlgorithm(){
        System.out.println("I'm the best genetic algorithm ever!");
        System.out.println("I get my population with size: " + population.size());


    }

}
