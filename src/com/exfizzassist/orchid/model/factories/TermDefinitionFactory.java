package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.DefinitionSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.SetSocket;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class TermDefinitionFactory extends OrchidFactory {

    /**
     * This is the socket that holds the new term's name
     */
    private DefinitionSocket definitionSocket;

    /**
     * This is the socket that contains the set of which
     * this term is an element*/
    private SetSocket setSocket;

    public TermDefinitionFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        definitionSocket = new DefinitionSocket(_editorComplex, this);
        setSocket = new SetSocket(_editorComplex, this);
        definitionSocket.syncWithNext(setSocket);
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        prevSocket.syncWithNext(definitionSocket);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        setSocket.syncWithNext(nextSocket);
    }

    @Override
    void populateHTML(Document document) {

    }
}
