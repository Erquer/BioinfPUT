package com.company.dfs;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    int data;
    boolean visited;
    List<Node> neighbours;

    Node(int data)
    {
        this.data=data;
        this.neighbours=new ArrayList<>();

    }
    public void addneighbours(Node neighbourNode)
    {
        this.neighbours.add(neighbourNode);
    }
    public List getNeighbours() {
        return neighbours;
    }
    public void setNeighbours(List neighbours) {
        this.neighbours = neighbours;
    }
}