package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;

public class TermPlug extends OrchidPlug {

    public TermPlug(EditorComplex _editorComplex, String sequence) {
        /*TODO: PHASE I log 2-13-20 implement.*/
    }

    @Override
    public OrchidSocket firstUnfilledSocket() {
        return null;
    }

    @Override
    public boolean isFullyPlugged() {
        return false;
    }

    @Override
    public void populateHTML(Document document) {

    }
}
