package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.factories.OrchidFactory;
import org.w3c.dom.Document;

import java.util.ArrayList;

public abstract class OrchidSocket {
    /**
     * Id of the socket
     */
    private String id;

    /**
     * Id of the next socket
     */
    private String nextId;

    /**
     * Id of the previous socket
     */
    private String prevId;

    /**
     * Id of the parent element
     * (could be line, other socket*/

    /**
     * The factory to which this socket connects
     */
    OrchidFactory parent;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets next id
     */
    public void setNextId(String nextId) {
        this.nextId = nextId;
    }

    /**
     * Sets the previous id
     */
    public void setPrevId(String prevId) {
        this.prevId = prevId;
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
