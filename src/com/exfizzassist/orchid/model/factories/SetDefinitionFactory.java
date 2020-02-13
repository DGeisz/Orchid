package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.DefinitionSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SetDefinitionFactory extends OrchidFactory {

    /**
     * This is the socket that will hold
     * the name of the set currently being defined
     */
    private DefinitionSocket definitionSocket;

    /*TODO: Right now, you can basically just define a set of numbers
     *  with this factory.  In the future, I want to add set properties,
     *  operations, notions of subsets, the whole melarchy. Basically just a
     *  quick fyi that this boi needs work*/

    public SetDefinitionFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        definitionSocket = new DefinitionSocket(_editorComplex, this);
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        prevSocket.syncWithNext(definitionSocket);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        definitionSocket.syncWithNext(nextSocket);
    }

    @Override
    void populateHTML(Document document) {
        Element parent = document.getElementById(parentId);
        Element thisElement = document.createElement("span");
        thisElement.setAttribute("class", "map-definition-factory");
        thisElement.setAttribute("id", getId());
        parent.appendChild(thisElement);
        definitionSocket.populateHTML(document);
        /*TODO: I super need to figure out how sets will be defined.
        *  Members of sets need properties and rules, and these need to
        *  be defined */
        Element setDefinitionSyntax = document.createElement("span");
        setDefinitionSyntax.setAttribute("class", "set-definition");
        setDefinitionSyntax.setTextContent(" = {}");
        thisElement.appendChild(setDefinitionSyntax);
    }
}
