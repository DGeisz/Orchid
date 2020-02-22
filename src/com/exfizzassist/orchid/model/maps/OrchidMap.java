package com.exfizzassist.orchid.model.maps;


import com.exfizzassist.orchid.model.sets.OrchidSet;

public class OrchidMap {

    /** Source Set (source)*/
    private OrchidSet source;

    /** Target set (target)*/
    private OrchidSet target;

    public OrchidMap(OrchidSet source, OrchidSet target) {
        this.source = source;
        this.target = target;
    }

    public OrchidSet getSource() {
        return source;
    }

    public OrchidSet getTarget() {
        return target;
    }

}

