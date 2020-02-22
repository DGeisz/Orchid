package com.exfizzassist.orchid.model.sets;

import com.exfizzassist.orchid.model.maps.OrchidMap;

import java.util.ArrayList;

public abstract class OrchidSet {
    /**
     * Identifier of the set.
     */
    String id;

    /**
     * List of supersets
     */
    private ArrayList<OrchidSet> superSets;

    OrchidSet(String id) {
        superSets = new ArrayList<>();
        this.id = id;
    }

    /**
     * Id getter
     */
    public String getId() {
        return id;
    }

    /**
     * Adds a superset to superSets
     */
    public void addSuperSet(OrchidSet superSet) {
        superSets.add(superSet);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof OrchidSet) {
            return id.equals(((OrchidSet) object).getId());
        }
        return false;
    }

    /**
     * Returns true if the set is the subset
     * of otherSet
     */
    public boolean isSubsetOf(OrchidSet otherSet) {
        if (superSets.contains(otherSet)) {
            return true;
        }
        for (OrchidSet set : superSets) {
            if (set.isSubsetOf(otherSet)) {
                return true;
            }
        }
        return false;
    }

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
