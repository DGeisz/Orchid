package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class TermSocket extends OrchidSocket {


    public TermSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
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
    public ArrayList<String> commitSequence(String sequence, Document document, String lastId, String nextId) {
        return null;
    }
}
