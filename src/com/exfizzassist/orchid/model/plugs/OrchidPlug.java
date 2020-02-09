package com.exfizzassist.orchid.model.plugs;

import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import org.w3c.dom.Document;

public abstract class OrchidPlug {
    /**
     * Returns the first child socket that isn't filled
     * or null if it doesn't have a parent factory
     */
    abstract public OrchidSocket firstUnfilledSocket();

    /**
     * RETURNS true if the term doesn't have children,
     * or if all the child sockets are already plugged
     */
    abstract public boolean isFullyPlugged();

    /**
     * Populate the DOM with HTML corresponding
     * to this element
     */
    abstract public void populateHTML(Document document);
}
