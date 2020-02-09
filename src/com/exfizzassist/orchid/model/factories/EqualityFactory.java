package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import org.w3c.dom.Document;

public class EqualityFactory extends OrchidFactory {

    public EqualityFactory(EditorComplex _editorComplex, String parentId) {
        super(_editorComplex, parentId);
    }

    @Override
    void populateHTML(Document document) {

    }
}
