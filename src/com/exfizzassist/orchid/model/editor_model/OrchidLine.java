package com.exfizzassist.orchid.model.editor_model;


import com.exfizzassist.orchid.model.editor_model.LineState;
import com.exfizzassist.orchid.model.sockets.EndLineSocket;
import com.exfizzassist.orchid.model.sockets.LineSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class OrchidLine {

    /**
     * The current socket manipulated in the line
     */
    private OrchidSocket currSocket;

    /**
     * Id of the previous line
     */
    private String prevLineId;

    /**
     * Socket for content of the line
     */
    private LineSocket contentSocket;

    /**
     * Socket to determine what
     * happens at the end of the line*/
    private EndLineSocket endLineSocket;

    /*TODO: Figure out how to initialize a socket so
    *  that it has the correct ids*/

    OrchidLine(String prevLineId) {
        this();
        this.prevLineId = prevLineId;
    }

    OrchidLine() {
        this.prevLineId = "";

    }

    /**
     * @return currSocket
     */
    OrchidSocket getCurrentSocket() {
        return currSocket;
    }


















    /*TODO: Determine if anything underneath this line is at all
    *  helpful, and if not, delete everything.*/

    /**
     * Map from enum states to their proper string values
     */
    public static HashMap<LineState, String> stateMap;

    /**
     * Sequences appropriate for an empty line
     */
    private static ArrayList<String> emptySequences;

    static {
        ArrayList<String> _emptySequences = new ArrayList<>();
        _emptySequences.add("map");
        _emptySequences.add("element");
        _emptySequences.add("term");
        emptySequences = (ArrayList<String>) Collections.unmodifiableList(_emptySequences);
    }

    static {
        Map<LineState, String> _stateMap = new HashMap<>();
        _stateMap.put(LineState.DEFINITION, "definition");
        _stateMap.put(LineState.TERM, "term");
        _stateMap.put(LineState.UNDEFINED, "undefined");
        stateMap = (HashMap<LineState, String>) Collections.unmodifiableMap(_stateMap);
    }
}
