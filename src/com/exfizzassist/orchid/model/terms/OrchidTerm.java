package com.exfizzassist.orchid.model.terms;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.sets.OrchidSet;

public abstract class OrchidTerm {

    /**
     * Name of the term, if one exists
     */
    String name;

    /**
     * The Set of which this term is an element
     */
    OrchidSet parentSet;

    /**
     * Term id
     */
    String id;

    /**
     * Tells whether this term is derived from another structure
     * (i.e. is the output of a map)
     */
    boolean derived;

    OrchidTerm(OrchidSet parentSet, String id, boolean derived) {
        this.id = id;
        this.parentSet = parentSet;
        this.derived = derived;
        name = "";
    }

    /**
     * Getter of parentSet
     */
    public OrchidSet getParentSet() {
        return parentSet;
    }

    /**
     * Determine if term corresponds to a set
     */
    public boolean isSet() {
        return false;
    }

    /**
     * Gets isDerived
     */
    public boolean isDerived() {
        return derived;
    }

    /**
     * Sets name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name
     */
    public String getName() {
        return name;
    }
}
