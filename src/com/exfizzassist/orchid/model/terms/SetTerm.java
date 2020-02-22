package com.exfizzassist.orchid.model.terms;

import com.exfizzassist.orchid.model.sets.OrchidSet;

public class SetTerm extends OrchidTerm {

    /**
     * Set to which this term corresponds
     */
    OrchidSet set;

    public SetTerm(OrchidSet set, OrchidSet parentSet, String id, boolean derived) {
        super(parentSet, id, derived);
        this.set = set;
    }

    public OrchidSet getSet() {
        return set;
    }

    @Override
    public boolean isSet() {
        return true;
    }
}
