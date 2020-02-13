package com.exfizzassist.orchid.model.editor_model;

import com.exfizzassist.orchid.model.factories.LineFactory;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**Represents one editor page.  You know how you
 * can have multiple tabs?  Yeah like that,
 * but with pages.*/
public class OrchidPage {


    /**
     * Reference to its parent editor complex
     */
    private EditorComplex editorComplex;

    /**
     * List of all lines a page contains
     */
    private ArrayList<LineFactory> lineList;

    /**
     * The current line being edited
     */
    private LineFactory currLine;

    /** Gives a reference to last id in the current
     * line.  Used when creating new lines*/
    private String lastId;

    /**
     * The id of the page
     */
    private String pageId;


    /**
     * Initializes a new page with a given _EDITORCOMPLEX
     */
    OrchidPage(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
        pageId = editorComplex.newId();
        lineList = new ArrayList<>();
        newLine();
    }

    /**
     * Returns the last possible set of three consecutive
     * ids from its last line
     */
    String getLastId() {
        return lineList.get(lineList.size() - 1).getLastId();
    }

    /**
     * @return currLine
     */
    LineFactory getCurrentLine() {
        return currLine;
    }

    /**
     * Takes information about the structures this page contains
     * and populates the editor document skeleton with HTML. Returns
     * the last unfilled Id.
     */
    void populateEditorHTML(Document document) {
        Element body = document.getElementById("editor-body");
        Element pageHtml = document.createElement("span");
        pageHtml.setAttribute("class", "page");
        pageHtml.setAttribute("id", pageId);
        body.appendChild(pageHtml);
        for (LineFactory line : lineList) {
            line.populateHTML(document);
        }
    }

    /**
     * Creates a new line and appends the line to lineList
     */
    void newLine() {
        if (lineList.isEmpty()) {
            lineList.add(new LineFactory(editorComplex, pageId));
            lastId = lineList.get(0).lastId();
        } else {
            lineList.add(new LineFactory(lastId, editorComplex, pageId));
            lastId = lineList.get(lineList.size() - 1).lastId();
        }

    }
}
