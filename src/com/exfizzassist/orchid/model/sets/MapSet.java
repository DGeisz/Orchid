package com.exfizzassist.orchid.model.sets;

import com.exfizzassist.orchid.model.basic_math.OrchidMap;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;

public class MapSet extends OrchidSet {

    /**
     * Elements of this set are this type of map
     */
    OrchidMap map;

    public MapSet(OrchidMap map) {
        this.map = map;
    }

    @Override
    public boolean isMapSet() {
        return true;
    }

    public OrchidMap getMap() {
        return map;
    }
}
