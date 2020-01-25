package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.sockets.OrchidSocket;

import java.util.ArrayList;

public abstract class OrchidFactory {

    /** Ordered list of sockets*/
    ArrayList<OrchidSocket> sockets;

    /** Method that sets the ids of
     * all the sockets and returns the next
     * usable id number*/
    abstract int[] socketConfigure(int lastId, int nextId);
}
