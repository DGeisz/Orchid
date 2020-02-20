package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;

public class NewTermNamePlug extends OrchidPlug {

    /**
     * Sequence of characters held by this plug
     */
    String sequence;

    public NewTermNamePlug(EditorComplex _editorComplex, String sequence) {
        super(_editorComplex);
        this.sequence = sequence;
        /*TODO: PHASE I log 2-13-20 implement*/
    }

    public String getSequence() {
        return sequence;
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
