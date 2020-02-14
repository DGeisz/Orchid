package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;

public class CartesianFactory extends OrchidFactory {

    public CartesianFactory(EditorComplex _editorComplex) {
        super(_editorComplex);
        factoryType = "cartesian-factory";
    }
}
