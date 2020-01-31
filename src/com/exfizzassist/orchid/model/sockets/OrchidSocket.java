package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.factories.OrchidFactory;
import org.w3c.dom.Document;

import java.util.ArrayList;

public abstract class OrchidSocket {
    /**
     * Id of the socket
     */
    int id;

    /**
     * Id of the next socket
     */
    int nextId;

    /**
     * Id of the previous socket
     */
    int prevId;

    /**
     * The factory to which this socket connects
     */
    OrchidFactory parent;

    /**
     * Sets the three id references
     */
    public void setId(int id, int prevId, int nextId) {
        this.id = id;
        this.prevId = prevId;
        this.nextId = nextId;
    }

    /**
     * Takes in a string SEQUENCE and outputs the
     * css class a font color corresponding to the nature
     * of the sequence
     */
    abstract public String sequenceStatus(String sequence);

    /**
     * Determines if a string SEQUENCE corresponds to
     * an allowable term for this socket
     */
    abstract public boolean isAllowedSequence(String sequence);

    /**
     * Finds the structure that corresponds to this SEQUENCE
     * and appends HTML to the DOCUMENT corresponding to the
     * structure type.  Uses LASTID and NEXTID to set the ids of
     * this sequence in between those of its superstructure
     */
    abstract public ArrayList<String> commitSequence(String sequence, Document document, String lastId, String nextId);
}
