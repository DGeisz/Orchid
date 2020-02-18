package com.exfizzassist.orchid.model.sets;

public class UniversalSet extends OrchidSet {

    @Override
    public boolean isSubsetOf(OrchidSet otherSet) {
        return false;
    }

    @Override
    public boolean isUniversalSet() {
        return true;
    }
}
