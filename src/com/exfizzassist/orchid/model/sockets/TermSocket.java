package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
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
        return null;
    }

    @Override
    public boolean isAllowedSequence(String sequence) {
        return false;
    }

    @Override
    public String commitSequence(String sequence, Document document) {
        return null;
    }
}
