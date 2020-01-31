package com.exfizzassist.orchid.model.editor_model;

import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Dock {

    /** This is the dock's parent editor complex*/
    private EditorComplex editorComplex;

    /** This is the socket that is docked to the dock, and whose contents
     * can be manipulated*/
    private OrchidSocket dockedSocket;

    /** This is the line */
    private OrchidLine line;

    public Dock(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
    }

    /** Return a string corresponding to a css class which holds
     * a font color appropriate for the current status*/
    public String sequenceStatus(String sequence) {
        /*TODO: PHASE I: Implement*/
//        if (dockedSocket == null) {
//            LineState lineState = line.sequenceStatus(sequence, editorComplex
//                .getTermRegistry()
//                .containsKey(sequence));
//            return OrchidLine.stateMap.get(lineState);
//        }
        /*TODO: Delete this basic functionality*/
        if (sequence.equals("map")) {
            return "defined";
        } else {
            return "in-progress";
        }
    }

    /**
     * Takes in the skeleton editor DOM and populates it with
     * html.  The html is either new, or it is loaded from a persisted file in
     * some capacity.  */
    public int populateEditorHTML(Document editorDoc) {
        try {
            printDocument(editorDoc, System.out);
        } catch (IOException | TransformerException e) {
            System.out.println(e);
        }
        Element body = editorDoc.getElementById("editor-body");
        /* Determine if the body has any children, i.e html is already populated.*/
        if (body.getChildNodes().getLength() == 0) {
//            body.set
            /* TODO: EPOCH II: Handle persisted information.  For now, this simply adds
            *   a span corresponding to a new editor line with id 1 and returns 1*/
            Element newHtmlLine = editorDoc.createElement("span");
            newHtmlLine.setAttribute("id", Integer.toString(1));
            newHtmlLine.setAttribute("class", "line");
            body.appendChild(newHtmlLine);
            try {
                printDocument(editorDoc, System.out);
            } catch (IOException | TransformerException e) {
                System.out.println(e);
            }
            return 1;

        } else {
            /*TODO: EPOCH II: Handle an already populated */
            return 1;
        }
    }

    /**
     * Takes in the DOCUMENT and manipulates the DOM based on
     * the equation model to handle a backspace on an empty
     * socket, or an already committed sequence.  The only
     */
    public int handleBackSpace(Document document) {
        /*TODO: PHASE II: Implement this actual method.  For now this just returns 1*/
        return 1;
    }

    /**
     * Takes in an uncommitted sequence and returns whether the
     * sequence corresponds to a structure that is allowed in the
     * given context
     *
     * @param sequence
     */
    public boolean isAllowedSequence(String sequence) {
        /*TODO: PHASE I: Implement*/
        return true;
    }

    /**
     * Assumes the SEQUENCE is allowed.  Takes in the sequence
     * to commit as well as the editor DOCUMENT and builds html
     * corresponding to the structure to which SEQUENCE corresponds
     * RETURNS the next id for the controller to populate.
     */
    public int commitSequence(String sequence, Document document) {
        /*TODO: PHASE I: Implement*/
        return 1;
    }

    /** Helper method to print contents of a DOM.*/
    private static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc),
            new StreamResult(new OutputStreamWriter(out, "UTF-8")));
    }


}
