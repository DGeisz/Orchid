package com.exfizzassist.orchid.model.sockets;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.plugs.NewTermNamePlug;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class DefinitionSocket extends OrchidSocket {


    public DefinitionSocket(EditorComplex _editorComplex, OrchidFactory _parentFactory) {
        super(_editorComplex, _parentFactory);
        socketType = "definition-socket";
    }

    @Override
    public void populateHTML(Document document) {
        super.populateHTML(document);
        Element thisElement = document.getElementById(getId());
        thisElement.setAttribute("class", "definition-socket");
    }

    @Override
    public String sequenceStatus(String sequence) {
        if (isAllowedSequence(sequence)) {
            return "green";
        }
        return "red";
    }

    @Override
    public boolean isAllowedSequence(String sequence) {
        return !editorComplex.isBuiltIn(sequence)
            && !editorComplex.isDefinedTerm(sequence)
            && !editorComplex.isMapBuilder(sequence);
    }

    @Override
    public String commitSequence(String sequence, Document document) {
        if (!isAllowedSequence(sequence)) {
            return "";
        }
        NewTermNamePlug newPlug = new NewTermNamePlug(editorComplex, this, sequence);
        setPlug(newPlug);
        newPlug.populateHTML(document);
        parentFactory.commitNotification();
        return getNextId();
    }
}
