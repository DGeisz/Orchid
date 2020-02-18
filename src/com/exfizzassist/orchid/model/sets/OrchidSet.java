package com.exfizzassist.orchid.model.sets;

import com.exfizzassist.orchid.model.maps.OrchidMap;

public abstract class OrchidSet {
    /** Identifier of the set.*/
    String identifier;

    /**
     * Returns true if the set is the subset
     * of otherSet
     */
    abstract public boolean isSubsetOf(OrchidSet otherSet);

    /**
     * A bunch of checks used to determine the nature
     * of the given set
     */
    public boolean isSimpleSet() {

        return false;
    }

    public boolean isCartesianProductSet() {
        return false;
    }

    public boolean isMapSet() {
        return false;
    }

    public boolean isHigherOrderSet() {
        return false;
    }

    public boolean isUniversalSet() {
        return false;
    }


}
