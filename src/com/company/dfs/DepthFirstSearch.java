package com.company.dfs;
import com.company.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DepthFirstSearch
{
    /*TODO: tworzenie listy następników z odległościami.
       Odległość = L(długość całego łańcucha) - [ilość znaków dla których odl. Lovensteina  == 0](w skrócie od L odejmujemy tyle o ile różnią się od siebie dane olinukleotydy)

       TODO: DFS i przeszukiwanie najkrótszej ścieżki w grafie dla grafu skierowanego o wagach krawędzi = odległości;
                Warunek stopu: długość łańcucha == limit, albo użyte wszystkie wierzchołki (brak błędów), idealne rozwiązanie

       TODO: Wybieranie najlepszego rozwiązania, najbardziej zbliżone do sekwencji z pliku .seq; lub to którego stosunek długości/ użyte wierzchołki jest najwyższy
                ; lub wybieranie tych rozwiązań, które mają długość limit, a następnie wybieramy to, które użyło najwięcej wierzchołków.

  */
    public static final String FILE_NAME = "600_01";
    public static final String TEST_FILE = "Files/" + FILE_NAME;
    public static final String SEQ_FILE = "Sequences/" + FILE_NAME + ".seq";
    public static Set<String > longestSequence = new HashSet<>();
    public List<Node > visitedNodes = new ArrayList<>();

    private static int limit;
    public static Set<Node> nodes;

    private static void readData(Scanner scanner) {
        List<String> lines = new ArrayList<>();
        nodes = new HashSet<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        limit = Integer.parseInt(lines.get(0));
        for (int i = 2; i < lines.size(); i++) {
            nodes.add(new Node(lines.get(i)));
        }
        System.out.println("Added: " + nodes.size() + " nodes");

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
    static class Node
    {
        String code;
        boolean visited;
        List<Node> neighbours;
        Node(String  data)
        {
            this.code=data;
            this.neighbours=new ArrayList<>();
            this.visited = false;

        }
        public void findNeighbours(Node node) {
            nodes.forEach(node1 -> {
                if(node1!= node){
                    //if Lovenstein distance is less than 4 between prefix our node and checking node in this iteration put it into neighbour.
                    int len = Copute(node.code.substring(1),node1.code.substring(0,node1.code.length()-1));
                    //System.out.println(len);
                    if(len == 0){
                        System.out.println("Add new neighbour in node: " + this.code);
                        this.addneighbours(node1);
                    }
                }
            });
        }
        public void addneighbours(Node neighbourNode)
        {
            this.neighbours.add(neighbourNode);
        }
        public List<Node> getNeighbours() {
            return neighbours;
        }
        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }
    }

    // Recursive DFS
    public  void dfs(Node node)
    {
        System.out.print(node.code + " ");
        List<Node> neighbours=node.getNeighbours();
        node.visited=true;
        visitedNodes.add(node);
        for (int i = 0; i < neighbours.size(); i++) {
            Node n=neighbours.get(i);
            if(n!=null && !n.visited)
            {
                dfs(n);
            }
        }
    }

    // Iterative DFS using stack
    public  void dfsUsingStack(Node node)
    {
        Stack<Node> stack=new  Stack<Node>();
        stack.add(node);
        while (!stack.isEmpty())
        {
            Node element=stack.pop();
            if(!element.visited)
            {
                System.out.print(element.code + " ");
                element.visited=true;
            }

            List<Node> neighbours=element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n=neighbours.get(i);
                if(n!=null && !n.visited)
                {
                    stack.add(n);
                }
            }
        }
    }
    private String combineDFSResult(){
       return "";
    }
    public static void main(String arg[])
    {

        DepthFirstSearch dfsExample = new DepthFirstSearch();
        try {
            File dataFile = new File(TEST_FILE);
            Scanner scanner = new Scanner(dataFile);
            readData(scanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        nodes.forEach(node -> node.findNeighbours(node));
        System.out.println("The DFS traversal of the graph using stack ");
        for (Node node:nodes
             ) {
            dfsExample.dfs(node);
            nodes.forEach(node1 -> node1.visited = false);
            System.out.println();
        }


        System.out.println();
        System.out.println(nodes.stream().findFirst().get().code.substring(1));
        System.out.println(nodes.stream().findFirst().get().code.substring(0,nodes.stream().findFirst().get().code.length()-1));

        // Resetting the visited flag for nodes
        nodes.forEach(node -> node.visited = false);


        System.out.println("The DFS traversal of the graph using recursion ");
        dfsExample.dfs(nodes.stream().findFirst().get());
    }
}