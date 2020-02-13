package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.sets.HigherOrderSet;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class SetSocket extends OrchidSocket {

    public SetSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
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









    /*FIXME: Not sure why this is here, but I guess keep it around for if this
    *  is useful*/
    /** Higher Order set type.  Optional
     * If null, the higher order set type is assumed to
     * be the set of all sets, so any set can plug into
     * this socket.
     */
    HigherOrderSet parentSet;
}
