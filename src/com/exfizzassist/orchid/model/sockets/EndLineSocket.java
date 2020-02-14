package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import org.w3c.dom.Document;


public class EndLineSocket extends OrchidSocket {
    /*TODO: PHASE I: Implement this galatic overlord*/

    public EndLineSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        super(_editorComplex, _parentFactory);
    }

    @Override
    public void populateHTML(Document document) {

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
