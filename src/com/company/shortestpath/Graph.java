package com.company.shortestpath;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    // getters and setters

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> sb.append(node.getName() + " "));
        return "Graph{" +
                "nodes= " + sb.toString() +
                '}';
    }
}
