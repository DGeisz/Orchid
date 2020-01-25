package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.sets.HigherOrderSet;
import com.exfizzassist.orchid.model.sets.OrchidSet;

public class SetSocket extends OrchidSocket {

    /** Higher Order set type.  Optional
     * If null, the higher order set type is assumed to
     * be the set of all sets, so any set can plug into
     * this socket.
     */
    HigherOrderSet parentSet;

    /** Set that plugs into this socket.*/
    OrchidSet pluggedSet;

    public SetSocket() {
    }

    public SetSocket(HigherOrderSet parentSet) {
        this.parentSet = parentSet;
    }
}
