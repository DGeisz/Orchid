package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class LineSocket extends OrchidSocket{

    public LineSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        super(_editorComplex, _parentFactory);
    }

    @Override
    public void populateHTML(Document document) {
        Element parentElement = document.getElementById(parentId);
        Element thisElement = document.createElement("span");
        thisElement.setAttribute("class", "lineSocket");
        thisElement.setAttribute("id", getId());
        parentElement.appendChild(thisElement);
        if (plugged()) {
            plug.populateHTML(document);
        }
    }

    @Override
    public String sequenceStatus(String sequence) {
        if (editorComplex.isBuiltIn(sequence) |
            editorComplex.isDefinedTerm(sequence) |
            editorComplex.isMapBuilder(sequence)
        ) {
            return "green";
        }
        return "blue";
    }

    @Override
    public boolean isAllowedSequence(String sequence) {
        return editorComplex.isBuiltIn(sequence) |
            editorComplex.isDefinedTerm(sequence) |
            editorComplex.isMapBuilder(sequence);
    }

    @Override
    public ArrayList<String> commitSequence(String sequence, Document document, String lastId, String nextId) {
        return null;
    }
}
