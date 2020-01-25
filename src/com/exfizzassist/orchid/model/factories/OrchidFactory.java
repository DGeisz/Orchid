package com.exfizzassist.orchid.model.factories;

public abstract class OrchidFactory {

    /** Method that sets the ids of
     * all the sockets and returns the next
     * usable id number*/
    abstract int[] socketConfigure(int lastId, int nextId);
}
