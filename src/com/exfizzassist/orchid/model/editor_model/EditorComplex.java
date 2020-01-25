package com.exfizzassist.orchid.model.editor_model;

import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorComplex {

    /** Registry of all defined terms in the current session
     * and the sets to which they correspond
     */
    HashMap<String, OrchidSet> termRegistry;

    /** Registry of all sockets, where each socket is accessed
     * by its ID */
    HashMap<Integer, OrchidSocket> socketRegistry;

    /** List of all lines defined within the editor*/
    ArrayList<OrchidLine> editorLines;

    /** The editor dock. This bad boi is super important*/
    Dock dock;


    public EditorComplex() {
        dock = new Dock(this);
        termRegistry = new HashMap<>();
        socketRegistry = new HashMap<>();
        editorLines = new ArrayList<>();
    }

    public HashMap<String, OrchidSet> getTermRegistry() {
        return termRegistry;
    }

    public HashMap<Integer, OrchidSocket> getSocketRegistry() {
        return socketRegistry;
    }

    public Dock getDock() {
        return dock;
    }
}
