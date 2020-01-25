package com.exfizzassist.orchid.model.sets;

import com.exfizzassist.orchid.model.basic_math.OrchidMap;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.TermSocket;

import java.util.ArrayList;

public class OrchidSet {
    /** Name of the set.*/
    String name;

    /** Type of map elements of this set can act as*/
    OrchidMap map;

    public ArrayList<OrchidSocket> generateSockets() {
        ArrayList<OrchidSocket> newSockets = new ArrayList<>();
        for (OrchidSet set : map.getInput()) {
            newSockets.add(new TermSocket(set));
        }
        return newSockets;
    }
}
