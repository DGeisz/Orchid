package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/** This is a data structure that essentially serves to
 * be the parent of one or more sockets.  The actual functionality
 * of an object varies from factory to factory.*/
public abstract class OrchidFactory {

    /**
     * Id of the factory
     */
    String id;

    /**
     * Id of the DOM parent element
     */
    String parentId;

    /**
     * Reference to the editor complex
     */
    EditorComplex editorComplex;

    /**
     * Name of factory type for use with css
     */
    String factoryType;

    /**
     * Output of the factory
     */
    OrchidPlug output;

    public OrchidFactory(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
        id = editorComplex.newId();
    }

    /**
     * Populates the document with HTML
     * corresponding to this factory.  All
     * elements are children of parendId
     */
    public void populateHTML(Document document) {
        Element parent = document.getElementById(parentId);
        Element thisElement = document.createElement("span");
        if (getFactoryType() != null) {
            thisElement.setAttribute("class", getFactoryType());
        }
        thisElement.setAttribute("id", getId());
        parent.appendChild(thisElement);
    }

    /**
     * Is called to notify a factory that one of its sockets
     * has just had something committed into it.  This gives the
     * factory the chance to see if all sockets are filled, and if so
     * to do whatever the fracking factory is supposed to do.
     */
    abstract public void commitNotification();


    /**
     * Gets the output of the factory.  All factories output
     * a plug of some sort, so this method first generates the
     * output plug if it doesn't yet exist, and then returns the plug
     */
    abstract public OrchidPlug getFactoryOutput();

    /**
     * Returns true if all sockets are plugged
     */
    abstract public boolean isFullyPlugged();

    /**
     * Returns the first unfilled child socket,
     * or null if all sockets are filled*/
    abstract public OrchidSocket firstUnfilledSocket();

    /**
     * Get factoryType
     */
    public String getFactoryType() {
        return factoryType;
    }

    /**
     * Get id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
