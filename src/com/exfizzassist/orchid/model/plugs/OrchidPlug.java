package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;

public abstract class OrchidPlug {
    /**
     * This is the socket into which this plug plugs
     */
    private OrchidSocket socket;

    /**
     * Reference to its parent factory if one exists
     */
    private OrchidFactory factory;

    /**
     * Ref to editor complex
     */
    private EditorComplex editorComplex;

    /**
     * Plug ID*/
    private String id;

    OrchidPlug(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
        id = editorComplex.newId();
    }

    /**
     * Socket getter
     */
    public OrchidSocket getSocket() {
        return socket;
    }

    /**
     * Socket setter
     */
    public void setSocket(OrchidSocket socket) {
        this.socket = socket;
    }

    /**
     * Returns the first child socket that isn't filled
     * or null if it doesn't have a parent factory
     */
    abstract public OrchidSocket firstUnfilledSocket();

    /**
     * RETURNS true if the term doesn't have children,
     * or if all the child sockets are already plugged
     */
    abstract public boolean isFullyPlugged();

    /**
     * Populate the DOM with HTML corresponding
     * to this element
     */
    abstract public void populateHTML(Document document);

    public String getId() {
        return id;
    }

    /**
     * Sets the parentFactory of this plug.
     * Sets the factories parent id to this plug
     */
    public void setFactory(OrchidFactory factory) {
        this.factory = factory;
    }
}
