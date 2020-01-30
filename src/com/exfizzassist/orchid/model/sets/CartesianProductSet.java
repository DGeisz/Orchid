package com.exfizzassist.orchid.model.sets;

import java.util.ArrayList;

public class CartesianProductSet extends OrchidSet {


    /**
     * ArrayList of sets in the cartesian product
     */
    ArrayList<OrchidSet> setFactors;

    CartesianProductSet(String unparsed) {
        this.identifier = unparsed;
    }

    CartesianProductSet(ArrayList<OrchidSet> factors) {
        setFactors = factors;
    }

    @Override
    public boolean isCartesianProductSet() {
        return true;
    }
}
