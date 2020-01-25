package com.exfizzassist.orchid.model.sets;

import com.exfizzassist.orchid.model.basic_math.OrchidMap;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.TermSocket;

import java.util.ArrayList;

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
