package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import org.w3c.dom.Document;

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

    public OrchidFactory(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
        id = editorComplex.newId();
    }

    /**
     * Populates the document with HTML
     * corresponding to this factory.  All
     * elements are children of parendId
     */
    abstract void populateHTML(Document document);

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
