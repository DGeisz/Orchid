package com.exfizzassist.orchid.model.factories;


import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sockets.EndLineSocket;
import com.exfizzassist.orchid.model.sockets.LineSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class LineFactory extends OrchidFactory{

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
     * happens at the end of the line
     */
    private EndLineSocket endLineSocket;


    /**
     * ID of this current line
     */
    private String lineId;

    /*TODO: Figure out how to initialize a socket so
    *  that it has the correct ids*/

    public LineFactory(String prevLineId, EditorComplex _editorComplex, String pageId) {
        this(_editorComplex, pageId);
        this.prevLineId = prevLineId;
    }

    public LineFactory(EditorComplex _editorComplex, String pageId) {
        super(_editorComplex);
        setId(pageId);
        this.prevLineId = "";
        lineId = editorComplex.newId();
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
        Element lineHtml = document.createElement("span");
        lineHtml.setAttribute("class", "line");
        lineHtml.setAttribute("id", lineId);
        pageElement.appendChild(lineHtml);
        contentSocket.populateHTML(document);
        endLineSocket.populateHTML(document);
    }

    @Override
    public void commitNotification() {}

    /**
     * Line factories are the only factories that don't return plugs.
     */
    @Override
    public OrchidPlug getFactoryOutput() {
        return null;
    }

    @Override
    public SequenceState sequenceStateInContext(String sequence, String socketId) {
        return SequenceState.INAPPLICABLE;
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
