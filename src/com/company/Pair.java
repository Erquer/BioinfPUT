package com.company;

import java.util.List;

public class Pair {
    private String solution;
    private List<Integer> usedOcli;

    public Pair(String solution, List<Integer> usedOcli) {
        this.solution = solution;
        this.usedOcli = usedOcli;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public List<Integer> getUsedOcli() {
        return usedOcli;
    }

    public void setUsedOcli(List<Integer> usedOcli) {
        this.usedOcli = usedOcli;
    }
}
