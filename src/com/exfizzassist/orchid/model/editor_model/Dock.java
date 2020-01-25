package com.exfizzassist.orchid.model.editor_model;

import com.exfizzassist.orchid.model.sockets.OrchidSocket;

public class Dock {

    /** This is the dock's parent editor complex*/
    EditorComplex editorComplex;

    /** This is the socket that is docked to the dock, and whose contents
     * can be manipulated*/
    OrchidSocket dockedSocket;

    /** This is the line */
    OrchidLine line;

    public Dock(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
    }

    public String sequenceStatus(String sequence) {
        if (dockedSocket == null) {
            LineState lineState = line.sequenceStatus(sequence, editorComplex
                .getTermRegistry()
                .containsKey(sequence));
            return OrchidLine.stateMap.get(lineState);
        }
        return "";
    }


}
