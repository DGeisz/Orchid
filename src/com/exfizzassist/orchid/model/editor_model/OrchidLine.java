package com.exfizzassist.orchid.model.editor_model;


import com.exfizzassist.orchid.model.editor_model.LineState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrchidLine {

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

    OrchidLine() {
    }

    public LineState sequenceStatus(String sequence, Boolean isDefinedTerm) {
        if (isDefinedTerm) {
            return LineState.TERM;
        } else if (emptySequences.contains(sequence)) {
            return LineState.DEFINITION;
        }
        return LineState.UNDEFINED;
    }


}
