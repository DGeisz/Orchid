package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.ModelPlug;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sets.OrchidSet;
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
        factoryType = "equality-factory";
        leftHandSide = new TermSocket(_editorComplex, this, editorComplex.getUniversalSet());
        rightHandSide = new TermSocket(_editorComplex, this, editorComplex.getUniversalSet());
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        prevSocket.syncWithNext(leftHandSide);
        leftHandSide.syncWithNext(rightHandSide);
        rightHandSide.syncWithNext(nextSocket);
    }

    @Override
    void populateHTML(Document document) {
        super.populateHTML(document);
        Element thisElement = document.getElementById(getId());
        leftHandSide.populateHTML(document);
        Element equalsSign = document.createElement("span");
        equalsSign.setAttribute("class", "equals");
        equalsSign.setTextContent(" = ");
        /*TODO: PHASE II (Probably) Implement logic to redirect clicks
        *  to equals sign.*/
        thisElement.appendChild(equalsSign);
        rightHandSide.populateHTML(document);
    }

    @Override
    public void commitNotification() {
        boolean leftSocketPlugged = leftHandSide.plugged();
        boolean rightSocketPlugged = rightHandSide.plugged();
        if (!leftSocketPlugged && !rightSocketPlugged) {
            return;
        }
        if (!leftSocketPlugged) {
            leftHandSide.setElementOf(rightHandSide.getElementOf());
        } else if (!rightSocketPlugged) {
            rightHandSide.setElementOf(leftHandSide.getElementOf());
        } else {
            /*TODO: IMPLEMENT this whenever you figure out how to store equalities/rules.*/
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
