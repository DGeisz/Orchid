package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;

public class CartesianFactory extends OrchidFactory {
    /*TODO: PHASE 2.5 (Or whenever) COMMIT*/
    public CartesianFactory(EditorComplex _editorComplex) {
        super(_editorComplex);
        factoryType = "cartesian-factory";
    }

    @Override
    public void commitNotification() {

    }

    @Override
    public OrchidPlug getFactoryOutput() {
        return null;
    }

    @Override
    public boolean isFullyPlugged() {
        return false;
    }

    @Override
    public OrchidSocket firstUnfilledSocket() {
        return null;
    }

}
