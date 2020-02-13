package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.TermSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EqualityFactory extends OrchidFactory {

    /**
     * The term on the left hand side
     */
    private TermSocket leftHandSide;

    /**
     * The term on the right hand side
     */
    private TermSocket rightHandSide;

    public EqualityFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        leftHandSide = new TermSocket(_editorComplex, this);
        rightHandSide = new TermSocket(_editorComplex, this);
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        prevSocket.syncWithNext(leftHandSide);
        leftHandSide.syncWithNext(rightHandSide);
        rightHandSide.syncWithNext(nextSocket);
    }

    @Override
    void populateHTML(Document document) {
        Element parent = document.getElementById(parentId);
        Element thisElement = document.createElement("span");
        thisElement.setAttribute("class", "equality-factory");
        thisElement.setAttribute("id", getId());
        parent.appendChild(thisElement);
        leftHandSide.populateHTML(document);
        Element equalsSign = document.createElement("span");
        equalsSign.setAttribute("class", "equals");
        equalsSign.setTextContent(" = ");
        /*TODO: PHASE II (Probably) Implement logic to redirect clicks
        *  to equals sign.*/
        thisElement.appendChild(equalsSign);
        rightHandSide.populateHTML(document);
    }
}
