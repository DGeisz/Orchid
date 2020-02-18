package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.AnonymousTermPlug;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sets.MapSet;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.TermSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MapFactory extends OrchidFactory {

    /**
     * The term that is acting as the map
     */
    private TermSocket mapTermSocket;

    /**
     * The argument of the map
     */
    private TermSocket argument;

    /**
     * The set of which the output of this factory is an element
     */
    private OrchidSet outputParentSet;

    public MapFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId, OrchidSet outputParentSet) {
        super(_editorComplex);
        this.outputParentSet = outputParentSet;
        factoryType = "map-factory";
        mapTermSocket = new TermSocket(_editorComplex, this, editorComplex.getGenericMapSet(editorComplex.getUniversalSet(), outputParentSet));
        argument = new TermSocket(_editorComplex, this, editorComplex.getUniversalSet());
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        prevSocket.syncWithNext(mapTermSocket);
        mapTermSocket.syncWithNext(argument);
        argument.syncWithNext(nextSocket);
    }

    @Override
    void populateHTML(Document document) {
        super.populateHTML(document);
        Element thisElement = document.getElementById(getId());
        mapTermSocket.populateHTML(document);
        Element leftParenthesis = document.createElement("span");
        leftParenthesis.setAttribute("class", "left-parenthesis");
        leftParenthesis.setTextContent("(");
        Element rightParenthesis = document.createElement("span");
        rightParenthesis.setAttribute("class", "right-parenthesis");
        rightParenthesis.setTextContent(")");
        thisElement.appendChild(leftParenthesis);
        argument.populateHTML(document);
        thisElement.appendChild(rightParenthesis);
    }

    @Override
    public void commitNotification() {
        boolean argPlugged = argument.plugged();
        boolean mapTermPlugged = mapTermSocket.plugged();
        if (!argPlugged && !mapTermPlugged) {
            return;
        }
        if (!mapTermPlugged) {
            mapTermSocket.setElementOf(editorComplex.getGenericMapSet(argument.getElementOf(), outputParentSet);
        } else if (!argPlugged) {
            argument.setElementOf(((MapSet) mapTermSocket.getPlug().getTerm().getParentSet()).getSource());
        }
    }

    @Override
    public OrchidPlug getFactoryOutput() {
        if (output == null) {
            output = new AnonymousTermPlug(editorComplex, outputParentSet);
            output.setFactory(this);
            parentId = output.getId();
        }
        return output;
    }

    /**
     * Getter for mapTermSocket
     */
    public TermSocket getMapTermSocket() {
        return mapTermSocket;
    }
}
