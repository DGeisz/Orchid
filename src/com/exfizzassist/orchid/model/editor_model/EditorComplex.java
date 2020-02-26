package com.exfizzassist.orchid.model.editor_model;

import com.exfizzassist.orchid.model.factories.*;
import com.exfizzassist.orchid.model.maps.OrchidMap;
import com.exfizzassist.orchid.model.sets.HigherOrderSet;
import com.exfizzassist.orchid.model.sets.MapSet;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sets.UniversalSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.terms.OrchidTerm;
import com.exfizzassist.orchid.view.EquationEditorController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorComplex {

    /**
     * Reference to the EquationEditorController*/
    private EquationEditorController editorController;

    /**
     * Registry of all defined terms in the current session
     * and the sets to which they correspond
     */
    private HashMap<String, OrchidTerm> termRegistry;

    /**
     * Registry of all sockets, where each socket is accessed
     * by its ID
     */
    private HashMap<String, OrchidSocket> socketRegistry;

    /**
     * Registry of all defined sets
     */
    private HashMap<String, OrchidSet> setRegistry;

    /**
     * Registry of all generic map sets
     */
    private HashMap<GenericMapSetKey, String> genericMapSetRegistry;

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
     * Built in sequence names to prevent redundancy
     */

    private final String termDef = "term";
    private final String mapDef = "mapDef";
    private final String setDef = "set";
    private final String mapSeq = "map";
    private final String equality = "=";

    /**
     * ArrayList of built in sequences
     */
    private ArrayList<String> builtInSeq;
//    private ArrayList<String> builtInSeq = new ArrayList<String>() {{
//        builtInSeq.add(termDef);
//        builtInSeq.add(mapDef);
//        builtInSeq.add(setDef);
//        builtInSeq.add(equality);
//        builtInSeq.add(mapSeq);
//    }};

    /**
     * The universal set for this editor
     */
    private final UniversalSet universalSet = new UniversalSet(newId());

    /**
     * Set of all sets
     */
    private HigherOrderSet setOfAllSets;


    /**
     * Initializes a fresh editor environment where nothing
     * has yet been defined
     */
    public EditorComplex() {

        termRegistry = new HashMap<>();
        socketRegistry = new HashMap<>();
        setRegistry = new HashMap<>();
        pageList = new ArrayList<>();
        currPage = new OrchidPage(this);
        pageList.add(currPage);
        dock = new Dock(this);
        genericMapSetRegistry = new HashMap<>();

        /*TODO: Not quite sure what special properties to
        *  give the set of all sets, but it will just be average
        *   HigherOrderSet for now*/
        setOfAllSets = new HigherOrderSet(newId(), getUniversalSet());

        addSet(setOfAllSets);
        addSet(universalSet);

        /*TODO: Test.  Getting it up and running*/
        builtInSeq = new ArrayList<>();
        builtInSeq.add(mapDef);
        builtInSeq.add(termDef);
        builtInSeq.add(setDef);
        builtInSeq.add(equality);
        builtInSeq.add(mapSeq);
    }

    /**
     * @return termRegistry
     */
    public HashMap<String, OrchidTerm> getTermRegistry() {

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
     * TODO: Make this better, you schmeagerino
     *
     * @returns new id
     */
    private int idCount = 0;

    public String newId() {
        idCount++;
        return Integer.toString(idCount);
    }

    /**
     * Takes in the EquationEditorController and
     * latches the controller to the dock
     */
    public void configureEditorController(EquationEditorController controller) {
        editorController = controller;
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
     * TODO: This method should only be meant for later EPOCH when I have
     *  an input parser.  Until then, this is nothing.
     */
//    public boolean isMapBuilder(String sequence) {
//        sequence = sequence.replaceAll("\\s", "");
//        if (sequence.length() >= 3 && sequence.substring(sequence.length() - 2).equals("()")) {
//            sequence = sequence.substring(0, sequence.length() - 2);
//            return isDefinedTerm(sequence) && termRegistry.get(sequence).isMapSet();
//        }
//        return false;
//    }

    /**
     * Returns true if SEQUENCE is the sequence
     * that corresponds to building maps
     */
    public boolean isMapSequence(String sequence) {
        return mapSeq.equals(sequence);
    }

    /**
     * RETURNS a new OrchidFactory corresponding to SEQ
     */
    public OrchidFactory factoryBuilder(String seq, String lastSocketId, String nextSocketId) {
        if (seq.equals(termDef)) {
            return new TermDefinitionFactory(this, lastSocketId, nextSocketId);
        } else if (seq.equals(mapDef)) {
            return new MapDefinitionFactory(this, lastSocketId, nextSocketId);
        } else if (seq.equals(setDef)) {
            return new SetDefinitionFactory(this, lastSocketId, nextSocketId);
        } else if (seq.equals(equality)) {
            return new EqualityFactory(this, lastSocketId, nextSocketId);
        } else if (seq.equals(mapSeq)) {
            return new MapFactory(this, lastSocketId, nextSocketId, universalSet);
        }
        return null;
    }

    public UniversalSet getUniversalSet() {
        return universalSet;
    }

    public HigherOrderSet getSetOfAllSets() {
        return setOfAllSets;
    }

    /**
     * Returns the term corresponding to SEQUENCE unless sequence
     * doesn't correspond to a term.  Then it returns null
     */
    public OrchidTerm getTerm(String sequence) {
        if (termRegistry.containsKey(sequence)) {
            return termRegistry.get(sequence);
        }
        return null;
    }

    /**
     * Adds name/term combination to the term registry
     */
    public void addTerm(String name, OrchidTerm term) {
        if (!termRegistry.containsKey(name)) {
            termRegistry.put(name, term);
            Document termsDoc = editorController.getTermsDocument();
            Element termsList = termsDoc.getElementById("defined-terms");
            Element newTerm = termsDoc.createElement("p");
            newTerm.setAttribute("class", "single-term");
            newTerm.setTextContent(name);
            termsList.appendChild(newTerm);
        }
    }

    /**
     * Adds term/set combination to the set registry
     */
    public void addSet(OrchidSet set) {
        setRegistry.put(set.getId(), set);
    }

    /**
     * Returns a generic MapSet with corresponding to a map
     * from SOURCE to TARGET.  Creates a new generic MapSet
     * if one of this type doesn't yet exist.
     */
    public MapSet getGenericMapSet(OrchidSet source, OrchidSet target) {
        GenericMapSetKey currKey = new GenericMapSetKey(source.getId(), target.getId());
        if (genericMapSetRegistry.containsKey(currKey)) {
            return (MapSet) setRegistry.get(genericMapSetRegistry.get(currKey));
        }
        MapSet newGeneric = new MapSet(currKey.getMap(), newId(), getUniversalSet());
        addSet(newGeneric);
        genericMapSetRegistry.put(new GenericMapSetKey(source.getId(), target.getId()), newGeneric.getId());
        return newGeneric;
    }

    /**
     * Simple set that stores the source and target Ids for generic map sets.
     * This is specifically so that GenericMapSets can be quickly and easily found.
     */
    private class GenericMapSetKey {
        private String sourceId;
        private String targetId;
        private OrchidMap map;

        GenericMapSetKey(String sourceId, String targetId) {
            this.sourceId = sourceId;
            this.targetId = targetId;
        }

        OrchidMap getMap() {
            if (map == null) {
                System.out.println(setRegistry.get(sourceId));
                map = new OrchidMap(setRegistry.get(sourceId), setRegistry.get(targetId));
            }
            return map;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof GenericMapSetKey) {
                GenericMapSetKey s = (GenericMapSetKey) obj;
                return sourceId.equals(s.sourceId) && targetId.equals(s.targetId);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (sourceId + targetId).hashCode();
        }
    }
}
