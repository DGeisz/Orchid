package com.exfizzassist.orchid.model.factories;


import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.editor_model.OrchidPage;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sockets.EndLineSocket;
import com.exfizzassist.orchid.model.sockets.LineSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class LineFactory extends OrchidFactory{

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
     * happens at the end of the line
     */
    private EndLineSocket endLineSocket;

    /**
     * Reference to the parent page
     */
    private OrchidPage parentPage;


    /*TODO: Figure out how to initialize a socket so
    *  that it has the correct ids*/

    public LineFactory(String prevLineId, EditorComplex _editorComplex, OrchidPage parentPage, String pageId) {
        this(_editorComplex, parentPage, pageId);
        this.prevLineId = prevLineId;
    }

    public LineFactory(EditorComplex _editorComplex, OrchidPage parentPage, String pageId) {
        super(_editorComplex);
        this.parentPage = parentPage;
        setParentId(pageId);
        this.prevLineId = "";
        contentSocket = new LineSocket(editorComplex, this);
        endLineSocket = new EndLineSocket(editorComplex, this);
        contentSocket.setPrevId("");
        contentSocket.syncWithNext(endLineSocket);
        endLineSocket.setNextId("");
    }

    /**
     * @return the id of the endLineSocket
     */
    public String lastId() {
        return endLineSocket.getId();
    }

    /**
     * Populates the document with the proper HTML
     */
    @Override
    public void populateHTML(Document document) {
        Element pageElement = document.getElementById(parentId);
        System.out.println("Page Element");
        System.out.println(pageElement);
        Element lineHtml = document.createElement("p");
        lineHtml.setAttribute("class", "line");
        lineHtml.setAttribute("id", getId());
        pageElement.appendChild(lineHtml);
        contentSocket.populateHTML(document);
        endLineSocket.populateHTML(document);
    }

    @Override
    public void commitNotification() {
    }

    /**
     * Called by EndLineSocket when the line is
     * finished and the editor is ready for a new line.
     * Returns the id of the first socket of the new line.
     */
    public String signalNewLine(Document document) {
        return parentPage.newLineAndPopulateHTML(document);
    }
    /**
     * Line factories are the only factories that don't return plugs.
     */
    @Override
    public OrchidPlug getFactoryOutput() {
        return null;
    }

    @Override
    public boolean isFullyPlugged() {
        return false;
    }


    /**
     * @returns either content socket or the first non-empty
     * child of the content socket
     */
    public OrchidSocket firstUnfilledSocket() {
        if (!contentSocket.plugged()) {
            return contentSocket;
        } else if (!contentSocket.isFullyPlugged()) {
            return endLineSocket;
        }
        return contentSocket.firstUnfilledSocket();
    }

    /**RETURNS the endLineSocket*/
    public EndLineSocket getEndLineSocket() {
        return endLineSocket;
    }
}
