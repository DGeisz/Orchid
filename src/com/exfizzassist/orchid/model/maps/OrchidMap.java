package com.exfizzassist.orchid.model.maps;


import com.exfizzassist.orchid.model.sets.OrchidSet;

public class OrchidMap {

    /** Input Set*/
    private OrchidSet input;

    /** Output set*/
    private OrchidSet output;

    OrchidMap(OrchidSet input, OrchidSet output) {
        this.input = input;
        this.output = output;
    }

    public OrchidSet getInput() {
        return input;
    }

    public OrchidSet getOutput() {
        return output;
    }

}

