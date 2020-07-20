package com.company;

import java.util.*;

public class Greedy {
    private Data toFind;
    private String result;
    //provide information about order of used nucleotides
    private List<Integer> used = new ArrayList<>();

    public Greedy(Data toFind) {
        this.toFind = toFind;
    }

    private String findBestMatch(int id, String original){
        Map.Entry<Integer,Integer> min = null;
        Map<Integer,Integer> results = new HashMap<>();
        for(int i = 0; i < toFind.getNumberOfNucleotides(); i++){
            if(i != id && !used.contains(i)) {
                results.put(i, Main.Copute(original, toFind.getOlinucleotides().get(i)));
            }
        }
        for(Map.Entry<Integer,Integer> entry: results.entrySet()){
            if(min == null || min.getValue() > entry.getValue()){
                min = entry;
            }
        }
        System.out.println("chosen id:"+ min.getKey() + " with score = " + min.getValue());
        used.add(min.getKey());
        return toFind.getOlinucleotides().get(min.getKey());
    }

    public String getResult() {
        return result;
    }

    public void greedyAlgorithm(){
        String sufix; //string without 1st letter.
        String prefix; // string without last letter.
        int begin = new Random().nextInt(toFind.getNumberOfNucleotides());
        StringBuilder sequenceFound = new StringBuilder(toFind.getOlinucleotides().get(begin)); // final string we are looking for.
        used.add(begin);
        //main loop where we find best choice to concat the string.
        for(int i = 0; i < toFind.getNumberOfNucleotides()-1; i++){
            sufix = toFind.getOlinucleotides().get(i).substring(1);
            prefix = findBestMatch(i,sufix).substring(0,8);
            sequenceFound.append(prefix.charAt(prefix.length() - 1));
        }
        System.out.println("Sequence length: "+ sequenceFound.toString().length() +";found: " + sequenceFound.toString());
//        Collections.sort(used);
//        System.out.println(used);
        result = sequenceFound.toString();
    }
}
