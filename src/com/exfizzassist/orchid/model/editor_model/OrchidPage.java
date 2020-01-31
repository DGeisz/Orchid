package com.exfizzassist.orchid.model.editor_model;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class OrchidPage {

    /**
     * Reference to its parent editor complex
     */
    EditorComplex editorComplex;

    /**
     * List of all lines a page contains
     */
    ArrayList<OrchidLine> lineList;

    /**
     * The current line being edited
     */
    OrchidLine currLine;

    /**
     * Initializes a new page with a given _EDITORCOMPLEX
     */
    OrchidPage(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
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
