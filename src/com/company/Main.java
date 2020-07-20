package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static final String FILE_NAME = "600_01";
    public static final String TEST_FILE = "Files/" + FILE_NAME;
    public static final String SEQ_FILE = "Sequences/" + FILE_NAME +".seq";
    private static Data myData;
    private static void readData(Scanner scanner){
        List<String> lines = new ArrayList<>();
        while(scanner.hasNextLine()){
            lines.add(scanner.nextLine());
        }
        Map<Integer, String > myOcli = new HashMap<>();
        for(int i = 2; i < lines.size(); i++){
            myOcli.put(i-2, lines.get(i));
        }
        myData = new Data(Integer.parseInt(lines.get(0)), Integer.parseInt(lines.get(1)),myOcli);
    }
    public static int Copute(String a, String b){

       int aInt = a.length();
       int bInt = b.length();
       int[][] d = new int[aInt+1][bInt+1];
       for (int i = 0; i <= aInt; d[i][0] = i++);
       for (int j = 1; j <= bInt; d[0][j] = j++);

       for(int i = 1; i<= aInt;i++){
           for(int j = 1; j<=bInt; j++){
               int cost = (b.charAt(j-1) == a.charAt(i-1)) ? 0:1 ;
               int min1 = d[i-1][j] + 1;
               int min2 = d[i][j-1] +1;
               int min3 = d[i-1][j-1] + cost;
               d[i][j] = Math.min(Math.min(min1,min2), min3);
           }
       }

       return d[aInt][bInt];
    }

    public static void main(String[] args) {
	// write your code here

        try {
            File dataFile = new File(TEST_FILE);
            Scanner scanner = new Scanner(dataFile);
            readData(scanner);
            //myData.getOlinucleotides().forEach((integer, s) -> System.out.println("id: "+ integer+"; string: " + s));
            File seqFile = new File(SEQ_FILE);
            Scanner scanner1 = new Scanner(seqFile);
            String seq = scanner1.nextLine();
            Greedy greedy = new Greedy(myData);
            greedy.greedyAlgorithm();
            System.out.println("Score: " + Copute(seq,greedy.getResult()));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
