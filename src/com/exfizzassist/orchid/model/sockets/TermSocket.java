package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.MapFactory;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.plugs.TermPlug;
import com.exfizzassist.orchid.model.sets.MapSet;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.terms.OrchidTerm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class TermSocket extends OrchidSocket {

    /**
     * The set of which the term is an element.  I honestly can't think
     * of a better way to phrase this than elementOf
     */
    private OrchidSet elementOf;

    public TermSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory, OrchidSet elementOf) {
        super(_editorComplex, _parentFactory);
        this.elementOf = elementOf;
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
        if (!editorComplex.isDefinedTerm(sequence)) {
            return false;
        }
        OrchidTerm currTerm = editorComplex.getTerm(sequence);
        if (currTerm.getParentSet().isSubsetOf(elementOf)) {
            return true;
        }
        if (currTerm.getParentSet().isMapSet()) {
            return ((MapSet) currTerm.getParentSet()).getMap().getTarget().isSubsetOf(elementOf);
        }
        return false;
    }

    @Override
    public String commitSequence(String sequence, Document document) {
        if (!editorComplex.isDefinedTerm(sequence)) {
            return getId();
        }
        OrchidTerm currTerm = editorComplex.getTerm(sequence);
        if (currTerm.getParentSet().isSubsetOf(elementOf)) {
            TermPlug termPlug = new TermPlug(editorComplex, currTerm);
            setPlug(termPlug);
            termPlug.populateHTML(document);
            parentFactory.commitNotification();
            return getNextId();
        }
        if (currTerm.getParentSet().isMapSet()
            && ((MapSet) currTerm.getParentSet()).getMap().getTarget().isSubsetOf(elementOf)) {
            MapFactory termMap = new MapFactory(editorComplex, getId(), getNextId(), elementOf);
            TermSocket mapTermSocket = termMap.getMapTermSocket();
            TermPlug termPlug = new TermPlug(editorComplex, currTerm);
            mapTermSocket.setPlug(termPlug);
            setPlug(termMap.getFactoryOutput());
            plug.populateHTML(document);
            parentFactory.commitNotification();
            return getNextId();
        }
        return getId();
    }

    public OrchidSet getElementOf() {
        return elementOf;
    }

    public void setElementOf(OrchidSet elementOf) {
        this.elementOf = elementOf;
    }
}
