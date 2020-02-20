package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;

public class ModelPlug extends OrchidPlug {

    public ModelPlug(EditorComplex _editorComplex) {
        super(_editorComplex);
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
