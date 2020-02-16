package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
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

    public MapFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        factoryType = "map-factory";
        mapTermSocket = new TermSocket(_editorComplex, this);
        argument = new TermSocket(_editorComplex, this);
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

    }

    @Override
    public OrchidPlug getFactoryOutput() {
        return null;
    }

    @Override
    public SequenceState sequenceStateInContext(String sequence, String socketId) {
        if (!editorComplex.isDefinedTerm(sequence)) {
            return SequenceState.NOT_PERMITTED;
        }
        OrchidTerm thisTerm = editorComplex.getTermRegistry().get(sequence);
        if (socketId.equals(mapTermSocket.getId())) {
            if (!thisTerm.getSet().isMapSet()) {
                return SequenceState.NOT_PERMITTED;
            }
            if (argument.plugged()) {

            }
        }
    }

    /**
     * Getter for mapTermSocket
     */
    public TermSocket getMapTermSocket() {
        return mapTermSocket;
    }
}
