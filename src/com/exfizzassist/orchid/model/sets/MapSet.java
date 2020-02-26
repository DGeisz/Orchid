package com.exfizzassist.orchid.model.sets;

import com.exfizzassist.orchid.model.maps.OrchidMap;

public class MapSet extends OrchidSet {

    /**
     * Elements of this set are this type of map
     */
    private OrchidMap map;

    /**
     * Indicates whether this set is a generic map set
     */
    boolean isGeneric;


    /** Constructor for a non*/
    public MapSet(OrchidMap map, String id, OrchidSet universalSet) {
        super(id);
        addSuperSet(universalSet);
        this.map = map;
        isGeneric = false;
    }

    @Override
    public boolean isSubsetOf(OrchidSet otherSet) {
        if (super.isSubsetOf(otherSet)) {
            return true;
        }
        if (otherSet.isMapSet()) {
            MapSet otherMapSet = (MapSet) otherSet;
            return map.getSource().isSubsetOf(otherMapSet.getMap().getSource())
                && map.getTarget().isSubsetOf(otherMapSet.getMap().getTarget());
        }
        return false;
    }

    @Override
    public boolean isMapSet() {
        return true;
    }

    public OrchidMap getMap() {
        return map;
    }
}
