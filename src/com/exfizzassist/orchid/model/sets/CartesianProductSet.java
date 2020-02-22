package com.exfizzassist.orchid.model.sets;

import java.util.ArrayList;

public class CartesianProductSet extends OrchidSet {


    /**
     * ArrayList of sets in the cartesian product
     */
    ArrayList<OrchidSet> setFactors;

    CartesianProductSet(String id, OrchidSet universalSet) {
        super(id);
        addSuperSet(universalSet);
    }


    @Override
    public boolean isSubsetOf(OrchidSet otherSet) {
        return false;
    }

    @Override
    public boolean isCartesianProductSet() {
        return true;
    }
}
