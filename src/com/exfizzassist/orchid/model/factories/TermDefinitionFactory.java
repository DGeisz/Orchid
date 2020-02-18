package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.AnonymousTermPlug;
import com.exfizzassist.orchid.model.plugs.NewTermNamePlug;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sockets.DefinitionSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.TermSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TermDefinitionFactory extends OrchidFactory {

    /**
     * This is the socket that holds the new term's name
     */
    private DefinitionSocket definitionSocket;

    /**
     * This is the socket that contains the set of which
     * this term is an element*/
    private TermSocket setSocket;

    public TermDefinitionFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        factoryType = "term-definition-factory";
        definitionSocket = new DefinitionSocket(_editorComplex, this);
        setSocket = new TermSocket(_editorComplex, this, editorComplex.getSetOfSet());
        definitionSocket.syncWithNext(setSocket);
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        prevSocket.syncWithNext(definitionSocket);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        setSocket.syncWithNext(nextSocket);
    }

    @Override
    void populateHTML(Document document) {
        super.populateHTML(document);
        Element thisElement = document.getElementById(getId());
        definitionSocket.populateHTML(document);
        Element elementOf = document.createElement("span");
        elementOf.setAttribute("class", "element-of");
        elementOf.setTextContent(" ∈ ");
        thisElement.appendChild(elementOf);
        setSocket.populateHTML(document);
    }

    @Override
    public void commitNotification() {
        if (definitionSocket.plugged() && setSocket.plugged()) {
            /*TODO: Check if the model in setSocket corresponds to a defined
            *  rule, and if it does, make the term an element of that defined set.
            *  Otherwise, make the new term a member of a generic set whose parent is
            *  an element of the parent of the plug of setSocket*/
            String termName = ((NewTermNamePlug) definitionSocket.getPlug()).getSequence();
        }
    }

    @Override
    public OrchidPlug getFactoryOutput() {
        if (output == null) {
            output = new ModelPlug(editorComplex);
            output.setFactory(this);
            parentId = output.getId();
        }
        return output;
    }
}
