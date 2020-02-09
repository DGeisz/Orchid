package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
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
     */
     String parentId;

    /**
     * Ref to editor complex
     */
    EditorComplex editorComplex;


    /**
     * The factory to which this socket connects
     */
    OrchidFactory parent;

    /**
     * The term that plugs into this socket
     */
    OrchidPlug plug;

    OrchidSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        parent = _parentFactory;
        editorComplex = _editorComplex;
        id = editorComplex.newId();
        editorComplex.addSocketToRegistry(this, id);
        parentId = parent.getId();
    }

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

    /** Getter for nextId*/
    public String getNextId() {
        return nextId;
    }

    /** Getter for prevId*/
    public String getPrevId() {
        return prevId;
    }

    /**
     * Takes in the editor DOM and populates the DOM
     * with its corresponding HTML
     */
    abstract public void populateHTML(Document document);

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

    /**
     * RETURNS true if the socket is plugged by a term
     */
    public boolean plugged() {
        return plug == null;
    }

    /**
     * RETURNS true if the plug of the socket and all children
     * plugs have been plugged.  Plug away, my bois
     */
    public boolean isFullyPlugged() {
        if (plugged()) {
            return plug.isFullyPlugged();
        }
        return false;
    }

    /**
     * RETURNS either itself or the first of its socket children
     * that isn't plugged.  If everything is plugged, then it
     * returns null
     */
    public OrchidSocket firstUnfilledSocket() {
        if (!plugged()) {
            return this;
        }
        return plug.firstUnfilledSocket();
    }
}
