package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.editor_model.OrchidPage;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    OrchidFactory parentFactory;

    /**
     * The term that plugs into this socket
     */
    OrchidPlug plug;

    /**
     * Name of socket type for use with css
     */
    String socketType;

    OrchidSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        parentFactory = _parentFactory;
        editorComplex = _editorComplex;
        id = editorComplex.newId();
        editorComplex.addSocketToRegistry(this, id);
        parentId = parentFactory.getId();
    }

    @Override
    public String toString() {
        return socketType + " " + id;
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

    /**Getter for socket type*/
    public String getSocketType(){
        return socketType;
    }

    /**
     * Takes in the editor DOM and populates the DOM
     * with its corresponding HTML
     */
    public void populateHTML(Document document) {
        Element parentElement = document.getElementById(parentId);
        Element thisElement = document.createElement("span");
        thisElement.setAttribute("class", socketType);
        thisElement.setAttribute("id", getId());
        parentElement.appendChild(thisElement);
        if (plugged()) {
            plug.populateHTML(document);
        }else{
            thisElement.setTextContent("☐");
        }
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
     * structure type. Returns the ID of the next socket waiting
     * to be filled.
     */
    abstract public String commitSequence(String sequence, Document document);

    /**
     * Plugs the _PLUG into the socket
     */
    public void setPlug(OrchidPlug _plug) {
        plug = _plug;
        plug.setSocket(this);
    }

    public OrchidPlug getPlug() {
        return plug;
    }

    /**
     * RETURNS true if the socket is plugged by a term
     */
    public boolean plugged() {
        return plug != null;
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

    /**
     * Shares ids with the next socket
     */
    public void syncWithNext(OrchidSocket nextSocket) {
        setNextId(nextSocket.getId());
        nextSocket.setPrevId(getId());
    }
}
