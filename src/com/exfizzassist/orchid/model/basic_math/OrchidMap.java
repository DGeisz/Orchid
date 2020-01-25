package com.exfizzassist.orchid.model.basic_math;


import com.exfizzassist.orchid.model.sets.OrchidSet;

import java.util.ArrayList;

public class OrchidMap {
    /** Ordered List of input types*/
    private ArrayList<OrchidSet> input;

    /** Ordered list of output types*/
    private ArrayList<OrchidSet> output;

    OrchidMap(ArrayList<OrchidSet> input, ArrayList<OrchidSet> output) {
        this.input = input;
        this.output = output;
    }

    public ArrayList<OrchidSet> getInput() {
        return input;
    }

    public ArrayList<OrchidSet> getOutput() {
        return output;
    }
}
