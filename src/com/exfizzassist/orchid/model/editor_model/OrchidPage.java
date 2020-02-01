package com.exfizzassist.orchid.model.editor_model;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class OrchidPage {

    /*TODO:  PHASE I (Mostly a reminder) BIG ONE.  FIGURE OUT How to allocate ids*/

    /**
     * Reference to its parent editor complex
     */
    private EditorComplex editorComplex;

    /**
     * List of all lines a page contains
     */
    private ArrayList<OrchidLine> lineList;

    /**
     * The current line being edited
     */
    private OrchidLine currLine;

    /**
     * Starting Id of the page
     */
    private String startPageId;

    /**
     * Ending Id of the page
     */
    private String endPageId;

    /**
     * Initializes a new page with a given _EDITORCOMPLEX
     */
    OrchidPage(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
        startPageId = editorComplex.newId();
        endPageId = editorComplex.newId();
        currLine = new OrchidLine();
        lineList.add(currLine);
    }

    /**
     * Returns the last possible set of three consecutive
     * ids from its last line
     */
    ArrayList<String> getLastIds() {
        return lineList.get(lineList.size() - 1).getLastIds();
    }

    /**
     * @return currLine
     */
    OrchidLine getCurrentLine() {
        return currLine;
    }

    /**
     * Takes information about the structures this page contains
     * and populates the editor document skeleton with HTML. Returns
     * the last unfilled Id.
     */
    void populateEditorHTML(Document document) {
        for (OrchidLine line : lineList) {
            line.populateHTML(document);
        }
    }
}
