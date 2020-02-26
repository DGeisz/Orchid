package com.exfizzassist.orchid.model.sets;

public class HigherOrderSet extends OrchidSet {

    public HigherOrderSet(String id, OrchidSet universalSet) {
        super(id);
        addSuperSet(universalSet);
    }

    public HigherOrderSet(String id, OrchidSet universalSet, OrchidSet setOfAllSets) {
        super(id);
        addSuperSet(universalSet);
        addSuperSet(setOfAllSets);
    }

    /**
     * Returns a new set corresponding to an element of this
     * set
     */
    public OrchidSet newChildSet(String id, OrchidSet universalSet) {
        /*TODO: Change this to reflect the type of set returned.
        *  For now, just returning a new simple set*/
        return new SimpleSet(id, universalSet);
    }


    @Override
    public boolean isHigherOrderSet() {
        return true;
    }
}
