package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.LineFactory;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class EndLineSocket extends OrchidSocket {
    /*TODO: PHASE I: Implement this galatic overlord*/

    public EndLineSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        super(_editorComplex, _parentFactory);
        socketType = "end-line-socket";
    }

    @Override
    public void populateHTML(Document document) {
        super.populateHTML(document);
    }

    @Override
    public String sequenceStatus(String sequence) {
        return null;
    }

    @Override
    public boolean isAllowedSequence(String sequence) {
        return sequence.isEmpty();
    }

    @Override
    public String commitSequence(String sequence, Document document) {
        if (isAllowedSequence(sequence)) {
            String newId =  ((LineFactory) parentFactory).signalNewLine(document);
            System.out.println("New ID");
            System.out.println(newId);
            return newId;
        }
        return getId();
    }
}
