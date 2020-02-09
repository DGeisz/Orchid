package com.exfizzassist.orchid.model.editor_model;

import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.factories.TermDefinitionFactory;
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
    private HashMap<String, OrchidSet> termRegistry;

    /**
     * Registry of all sockets, where each socket is accessed
     * by its ID
     */
    private HashMap<String, OrchidSocket> socketRegistry;

    /**
     * Registry of all defined sets*/
    private ArrayList<OrchidSet> setRegistry;

    /**
     * List of all pages contained within the editor
     */
    private ArrayList<OrchidPage> pageList;

    /**
     * The current editor page
     */
    private OrchidPage currPage;

    /**
     * The editor dock. This bad boi is super important
     */
    private Dock dock;

    /**
     * ArrayList of built in sequences
     */
    private static ArrayList<String> builtInSeq;

    static {
        builtInSeq.add("termDef");
        builtInSeq.add("mapDef");
        builtInSeq.add("setDef");
    }

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

    /**
     * @returns new id
     */
    public String newId() {
        /*TODO: Implement this method.  Everything relies on this method*/
        return "";
    }

    /**
     * Takes in the EquationEditorController and
     * latches the controller to the dock
     */
    public void configureEditorController(EquationEditorController controller) {
        controller.latchOntoDock(dock);
    }

    /**
     * Adds a new socket to the socketRegistry
     */
    public void addSocketToRegistry(OrchidSocket socket, String id) {
        socketRegistry.put(id, socket);
    }

    /**
     * RETURNS true if SEQUENCE is built in to the
     * system
     */
    public boolean isBuiltIn(String sequence) {
        return builtInSeq.contains(sequence);
    }

    /**
     * RETURNS true if SEQUENCE is in the termRegistry
     */
    public boolean isDefinedTerm(String sequence) {
        return termRegistry.containsKey(sequence);
    }

    /**
     * RETURNS true if once parsed, SEQUENCE
     * corresponds to a map
     */
    public boolean isMapBuilder(String sequence) {
        sequence = sequence.replaceAll("\\s", "");
        if (sequence.length() >= 3 && sequence.substring(sequence.length() - 2).equals("()")) {
            sequence = sequence.substring(0, sequence.length() - 2);
            return isDefinedTerm(sequence) && termRegistry.get(sequence).isMapSet();
        }
        return false;
    }

}
