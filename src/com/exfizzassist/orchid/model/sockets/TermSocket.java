package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.MapFactory;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.factories.SequenceState;
import com.exfizzassist.orchid.model.plugs.TermPlug;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class TermSocket extends OrchidSocket {

    /**
     * The set of which the term is an element.  I honestly can't think
     * of a better way to phrase this than elementOf
     */
    private OrchidSet elementOf;

    public TermSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        super(_editorComplex, _parentFactory);
        socketType = "term-socket";
        elementOf = editorComplex.getUniversalSet();
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



        return SequenceState.NOT_PERMITTED != parentFactory.sequenceStateInContext(sequence, getId());
    }

    @Override
    public String commitSequence(String sequence, Document document) {
        SequenceState sequenceState = parentFactory.sequenceStateInContext(sequence, getId());
        if (!isAllowedSequence(sequence) || sequenceState == SequenceState.INAPPLICABLE) {
            return getId();
        }
        if (sequenceState == SequenceState.TERM) {
            TermPlug termPlug = new TermPlug(editorComplex, sequence);
            setPlug(termPlug);
            termPlug.populateHTML(document);
            parentFactory.commitNotification();
            return getNextId();
        }
        MapFactory termMap = new MapFactory(editorComplex, getId(), getNextId());
        TermSocket mapTermSocket = termMap.getMapTermSocket();
        TermPlug termPlug = new TermPlug(editorComplex, sequence);
        mapTermSocket.setPlug(termPlug);
        setPlug(termMap.getFactoryOutput());
        plug.populateHTML(document);
        parentFactory.commitNotification();
        return getNextId();
    }

    public OrchidSet getElementOf() {
        return elementOf;
    }

    public void setElementOf(OrchidSet elementOf) {
        this.elementOf = elementOf;
    }
}
