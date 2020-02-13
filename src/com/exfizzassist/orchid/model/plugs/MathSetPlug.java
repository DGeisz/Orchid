package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;

public class MathSetPlug extends OrchidPlug {

    @Override
    public OrchidSocket firstUnfilledSocket() {
        return null;
    }

    @Override
    public boolean isFullyPlugged() {
        return false;
    }

    @Override
    public void populateHTML(Document document) {

    }
}
