package com.exfizzassist.orchid.model.basic_math;

import com.exfizzassist.orchid.model.sets.OrchidSet;

public class OrchidTerm {

    /** The set of which this node is an element*/
    private OrchidSet elementOf;

    /** Name of the term*/
    private String name;

    /** Returns the set of which this term is an element.*/
    public OrchidSet getSet(){
        return elementOf;
    }

    /** Gets the name of the term.*/
    public String getName() {
        return name;
    }

}
