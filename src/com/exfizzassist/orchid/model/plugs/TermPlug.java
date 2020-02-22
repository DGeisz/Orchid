package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.terms.OrchidTerm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TermPlug extends OrchidPlug {

    /**
     * Term to which this plug this plug corresponds
     */
    private OrchidTerm term;

    public TermPlug(EditorComplex _editorComplex, OrchidTerm term) {
        super(_editorComplex);
        this.term = term;
    }

    public OrchidTerm getTerm() {
        return term;
    }

    @Override
    public void populateHTML(Document document) {
        Element parentElement = document.getElementById(getParentId());
        Element thisElement = document.createElement("span");
        thisElement.setAttribute("class", "model-plug");
        thisElement.setAttribute("id", getId());
        parentElement.appendChild(thisElement);
        if (term.isDerived()) {
            getFactory().populateHTML(document);
            return;
        }
        thisElement.setTextContent(term.getName());
    }
}
