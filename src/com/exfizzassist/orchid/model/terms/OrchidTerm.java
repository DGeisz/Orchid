package com.exfizzassist.orchid.model.terms;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sets.OrchidSet;

public abstract class OrchidTerm {

    /**
     * The Set of which this term is an element
     */
    OrchidSet parentSet;

    OrchidTerm(OrchidSet parentSet) {
        this.parentSet = parentSet;
    }

    /**
     * Getter of parentSet
     */
    public OrchidSet getParentSet() {
        return parentSet;
    }
}
