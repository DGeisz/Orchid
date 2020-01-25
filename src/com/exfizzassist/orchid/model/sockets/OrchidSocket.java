package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.factories.OrchidFactory;

public abstract class OrchidSocket {
    /** Id of the socket*/
    int id;

    /** Id of the next socket*/
    int nextId;

    /** Id of the previous socket*/
    int prevId;

    /** The factory to which this socket connects*/
    OrchidFactory parent;

    /**
     * Sets the three id references
     */
    public void setId(int id, int prevId, int nextId) {
        this.id = id;
        this.prevId = prevId;
        this.nextId = nextId;
    }
}
