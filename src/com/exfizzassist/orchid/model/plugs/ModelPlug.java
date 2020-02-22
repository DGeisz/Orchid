package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ModelPlug extends OrchidPlug {

    public ModelPlug(EditorComplex _editorComplex) {
        super(_editorComplex);
    }


    @Override
    public void populateHTML(Document document) {
        Element parentElement = document.getElementById(getParentId());
        Element thisElement = document.createElement("span");
        thisElement.setAttribute("class", "model-plug");
        thisElement.setAttribute("id", getId());
        parentElement.appendChild(thisElement);
        if (getFactory() != null) {
            getFactory().populateHTML(document);
        }
    }
}
