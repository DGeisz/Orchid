package com.exfizzassist.orchid.model.sets;

public abstract class OrchidSet {
    /** Identifier of the set.*/
    String identifier;
    
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
}
