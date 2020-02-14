package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.plugs.MathSetPlug;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.plugs.TermPlug;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class LineSocket extends OrchidSocket{

    public LineSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        super(_editorComplex, _parentFactory);
        socketType = "line-socket";
    }

    @Override
    public void populateHTML(Document document) {
        Element parentElement = document.getElementById(parentId);
        Element thisElement = document.createElement("span");
        thisElement.setAttribute("class", "line-socket");
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
            editorComplex.isDefinedTerm(sequence);
        /*TODO: for the time being, no mapBuilders.  Until later epoch*/
//            | editorComplex.isMapBuilder(sequence);
    }

    @Override
    public String commitSequence(String sequence, Document document) {
        if (!isAllowedSequence(sequence)) {
            return getId();
        } else if (editorComplex.isBuiltIn(sequence)) {
            OrchidFactory newFactory = editorComplex.factoryBuilder(sequence, getId(), getNextId());
            setPlug(newFactory.getFactoryOutput());
            plug.populateHTML(document);
        } else if (editorComplex.isDefinedTerm(sequence)) {
            setPlug(new TermPlug(editorComplex, this, sequence));
            plug.populateHTML(document);
        } else if (editorComplex.isDefinedSet(sequence)) {
            setPlug(new MathSetPlug(editorComplex, this, sequence));
            plug.populateHTML(document);
        }
        parentFactory.commitNotification();
        return getNextId();
    }
}
