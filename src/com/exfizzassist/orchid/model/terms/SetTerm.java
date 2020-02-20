package com.exfizzassist.orchid.model.terms;

import com.exfizzassist.orchid.model.sets.OrchidSet;

public class SetTerm extends OrchidTerm {

    /**
     * Name of term
     */
    String name;

    /**
     * Set to which this term corresponds
     */
    OrchidSet set;

    public SetTerm(String name, OrchidSet set, OrchidSet parentSet) {
        super(parentSet);
        this.set = set;
        this.name = name;
    }

    public OrchidSet getSet() {
        return set;
    }

    @Override
    public boolean isSet() {
        return true;
    }
}
