package com.exfizzassist.orchid.model.basic_math;

import com.exfizzassist.orchid.model.sets.OrchidSet;

public class NamedTerm extends OrchidTerm {

    /** Term name*/
    String name;

    public NamedTerm(String name, OrchidSet elementOf) {
        this.name = name;
        this.elementOf = elementOf;
    }

    public String getName(){
        return name;
    }

}
