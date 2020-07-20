package com.company;

import java.util.Map;

public class Data {
    private int length;
    private int numberOfNucleotides;

    public Map<Integer, String> getOlinucleotides() {
        return olinucleotides;
    }

    private Map<Integer, String> olinucleotides;

    public Data(int length, int numberOfNucleotides, Map<Integer,String > olinucleotides) {
        this.length = length;
        this.numberOfNucleotides = numberOfNucleotides;
        this.olinucleotides = olinucleotides;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNumberOfNucleotides() {
        return numberOfNucleotides;
    }

    public void setNumberOfNucleotides(int numberOfNucleotides) {
        this.numberOfNucleotides = numberOfNucleotides;
    }


    public void setOlinucleotides(Map<Integer,String> olinucleotides) {
        this.olinucleotides = olinucleotides;
    }
}
