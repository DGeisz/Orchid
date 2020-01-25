package com.exfizzassist.orchid.model.basic_math;

import com.exfizzassist.orchid.model.sets.OrchidSet;

public abstract class OrchidTerm {

    /** The set of which this node is an element*/
    protected OrchidSet elementOf;

    /** Returns the set of which this term is an element.*/
    public OrchidSet getSet(){
        return elementOf;
    }
}
