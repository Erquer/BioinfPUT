package com.company.shortestpath;

import com.company.Generic;
import com.company.Pair;
import com.company.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static final String FILE_NAME = "600_01";
    public static final String TEST_FILE = "Files/" + FILE_NAME;
    public static final String SEQ_FILE = "Sequences/" + FILE_NAME + ".seq";
    private static final Set<Node> nodes = new HashSet<>();
    private static Integer destinationLength;

    private static void readData(Scanner scanner) {
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        destinationLength =  Integer.parseInt(lines.get(0));
        for (int i = 2; i < lines.size(); i++) {
            nodes.add(new Node(lines.get(i)));
        }

    }
    public static int Copute(String a, String b) {
        int aInt = a.length();
        int bInt = b.length();
        int[][] d = new int[aInt + 1][bInt + 1];
        for (int i = 0; i <= aInt; d[i][0] = i++) ;
        for (int j = 1; j <= bInt; d[0][j] = j++) ;

        for (int i = 1; i <= aInt; i++) {
            for (int j = 1; j <= bInt; j++) {
                int cost = (b.charAt(j - 1) == a.charAt(i - 1)) ? 0 : 1;
                int min1 = d[i - 1][j] + 1;
                int min2 = d[i][j - 1] + 1;
                int min3 = d[i - 1][j - 1] + cost;
                d[i][j] = Math.min(Math.min(min1, min2), min3);
            }
        }

        return d[aInt][bInt];
    }
    private static void findNeighboursToNode(Node node){
        int length = node.getName().length();
        nodes.forEach(n ->{
            if(node != n){
                //diffrent node from us
                node.addDestination(n,length - Copute(node.getName(), n.getName()));
            }
        });
    }
    public static void main(String[] args) {

        try {
            File dataFile = new File(TEST_FILE);
            Scanner scanner = new Scanner(dataFile);
            readData(scanner);
            //myData.getOlinucleotides().forEach((integer, s) -> System.out.println("id: "+ integer+"; string: " + s));
            //for every node find neighbours with distance between them
            nodes.forEach(Main::findNeighboursToNode);
            File seqFile = new File(SEQ_FILE);
            Scanner scanner1 = new Scanner(seqFile);
            //System.out.println(nodes);
            // nodes.forEach(n -> System.out.println(n.getAdjacentNodes()));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        Graph graph = new Graph();
//        System.out.println(graph);
//
//        graph.addNode(nodeA);
//        graph.addNode(nodeB);
//        graph.addNode(nodeC);
//        graph.addNode(nodeD);
//        graph.addNode(nodeE);
//        graph.addNode(nodeF);
        graph.setNodes(nodes);

        calculateShortestPathFromSource(graph, nodes.stream().findFirst().get());
        System.out.println(graph);



    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Node, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }
    private static Node getLowestDistanceNode(Set< Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    private static void calculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
