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
    EditorComplex editorComplex;

    /** This is the socket that is docked to the dock, and whose contents
     * can be manipulated*/
    OrchidSocket dockedSocket;

    /** This is the line */
    OrchidLine line;

    public Dock(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
    }

    public String sequenceStatus(String sequence) {
        if (dockedSocket == null) {
            LineState lineState = line.sequenceStatus(sequence, editorComplex
                .getTermRegistry()
                .containsKey(sequence));
            return OrchidLine.stateMap.get(lineState);
        }
        return "";
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
            /* TODO: Handle persisted information.  For now, this simply adds
            *   a span corresponding to a new editor line with id 1 and returns 1*/
            Element newHtmlLine = editorDoc.createElement("span");
            newHtmlLine.setAttribute("id", Integer.toString(1));
            newHtmlLine.setAttribute("class", "line");
            return 1;

        } else {
            /*TODO: Handle an already populated */
            return 1;
        }
    }

    /*Helper method to print contents of a DOM.*/
    public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
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
