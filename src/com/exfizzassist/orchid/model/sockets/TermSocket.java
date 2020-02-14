package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.MapFactory;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.factories.TermSequenceState;
import com.exfizzassist.orchid.model.plugs.TermPlug;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class TermSocket extends OrchidSocket {

    public TermSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        super(_editorComplex, _parentFactory);
        socketType = "term-socket";
    }

    @Override
    public void populateHTML(Document document) {
        super.populateHTML(document);
        Element thisElement = document.getElementById(getId());
        thisElement.setAttribute("class", "term-socket");
    }

    @Override
    public String sequenceStatus(String sequence) {
        if (isAllowedSequence(sequence)) {
            return "green";
        }
        return "blue";
    }

    @Override
    public boolean isAllowedSequence(String sequence) {
        return TermSequenceState.NOT_PERMITTED != parentFactory.sequenceStateInContext(sequence, getId());
    }

    @Override
    public String commitSequence(String sequence, Document document) {
        TermSequenceState sequenceState = parentFactory.sequenceStateInContext(sequence, getId());
        if (!isAllowedSequence(sequence) || sequenceState == TermSequenceState.INAPPLICABLE) {
            return getId();
        }
        if (sequenceState == TermSequenceState.TERM) {
            TermPlug termPlug = new TermPlug(editorComplex, this, sequence);
            setPlug(termPlug);
            termPlug.populateHTML(document);
            parentFactory.commitNotification();
            return getNextId();
        } else if (sequenceState == TermSequenceState.MAP) {
            MapFactory termMap = new MapFactory(editorComplex, getId(), getNextId());
            TermSocket mapTermSocket = termMap.getMapTermSocket();
            TermPlug termPlug = new TermPlug(editorComplex, mapTermSocket, sequence);
            mapTermSocket.setPlug(termPlug);
            setPlug(termMap.getFactoryOutput());
            plug.populateHTML(document);
            parentFactory.commitNotification();
            return getNextId();
        }
    }
}
