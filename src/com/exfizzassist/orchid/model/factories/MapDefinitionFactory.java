package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sockets.DefinitionSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.SetSocket;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.w3c.dom.Document;

public class MapDefinitionFactory extends OrchidFactory {

    /**
     * Socket that holds the map's name
     */
    private DefinitionSocket definitionSocket;

    /**
     * Socket for the input set
     */
    private SetSocket inputSocket;

    /**
     * Socket for the output set
     */
    private SetSocket outputSocket;


    public MapDefinitionFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        definitionSocket = new DefinitionSocket(_editorComplex, this);
        inputSocket = new SetSocket(_editorComplex, this);
        outputSocket = new SetSocket(_editorComplex, this);
        definitionSocket.syncWithNext(inputSocket);
        inputSocket.syncWithNext(outputSocket);
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        prevSocket.syncWithNext(definitionSocket);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        outputSocket.syncWithNext(nextSocket);

    }

    @Override
    void populateHTML(Document document) {

    }
}
