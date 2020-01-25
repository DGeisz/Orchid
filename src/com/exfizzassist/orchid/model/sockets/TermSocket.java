package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.sets.OrchidSet;

public class TermSocket extends OrchidSocket {

    /** Expected Set of which the term should
     * be an element*/
    OrchidSet expectedSet;

    /** Reference to its factory*/
    OrchidFactory parentFactory;

    public TermSocket(OrchidSet expectedSet) {
        this.expectedSet = expectedSet;
    }
}
