package com.exfizzassist.orchid.model.editor_model;

import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.view.EquationEditorController;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorComplex {

    /**
     * Registry of all defined terms in the current session
     * and the sets to which they correspond
     */
    HashMap<String, OrchidSet> termRegistry;

    /**
     * Registry of all sockets, where each socket is accessed
     * by its ID
     */
    HashMap<String, OrchidSocket> socketRegistry;

    /**
     * List of all pages contained within the editor
     */
    ArrayList<OrchidPage> pageList;

    /**
     * The current editor page
     */
    OrchidPage currPage;

    /**
     * The editor dock. This bad boi is super important
     */
    Dock dock;

    /**
     * Initializes a fresh editor environment where nothing
     * has yet been defined
     */
    public EditorComplex() {

        dock = new Dock(this);
        termRegistry = new HashMap<>();
        socketRegistry = new HashMap<>();
        pageList = new ArrayList<>();
        currPage = new OrchidPage(this);
        pageList.add(currPage);
    }

    /**
     * @return termRegistry
     */
    public HashMap<String, OrchidSet> getTermRegistry() {

        return termRegistry;
    }

    /**
     * @return socketRegistry
     */
    public HashMap<String, OrchidSocket> getSocketRegistry() {

        return socketRegistry;
    }

    /**
     * @return dock
     */
    public Dock getDock() {

        return dock;
    }

    /**
     * @return currPage
     */
    public OrchidPage getCurrentPage() {

        return currPage;
    }

    /**
     * Returns the socket corresponding to ID
     */
    public OrchidSocket getSocket(String id) {
        return socketRegistry.get(id);
    }


    public void configureEditorController(EquationEditorController editorController) {
        editorController.latchOntoDock(dock);
    }
}
