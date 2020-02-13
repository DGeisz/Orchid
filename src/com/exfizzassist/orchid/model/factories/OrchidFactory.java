package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
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

    public OrchidFactory(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
        id = editorComplex.newId();
    }

    /**
     * Populates the document with HTML
     * corresponding to this factory.  All
     * elements are children of parendId
     */
    void populateHTML(Document document) {
        Element parent = document.getElementById(parentId);
        Element thisElement = document.createElement("span");
        if (getFactoryType() != null) {
            thisElement.setAttribute("class", getFactoryType());
        }
        thisElement.setAttribute("id", getId());
        parent.appendChild(thisElement);
    }

    /**Get factoryType*/
    public String getFactoryType(){
        return factoryType;
    }

    /**Get id*/
    public String getId() {
        return id;
    }

    /**
     * Set id
     */
    public void setId(String id) {
        this.id = id;
    }

}
