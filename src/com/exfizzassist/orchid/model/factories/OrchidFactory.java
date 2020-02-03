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
    private String id;

    /**
     * Id of the DOM parent element
     */
    private String parentId;

    /**
     * Reference to the editor complex*/
    private EditorComplex editorComplex;

    public OrchidFactory(EditorComplex _editorComplex, String parentId) {
        this.parentId = parentId;
        editorComplex = _editorComplex;
        id = editorComplex.newId();
    }

    /**
     * Populates the document with HTML
     * corresponding to this factory.  All
     * elements are children of parendId
     */
    abstract void populateHTML(Document document);

}
