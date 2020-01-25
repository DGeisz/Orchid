package com.exfizzassist.orchid.model.sets;

public class SimpleSet extends OrchidSet {

    public SimpleSet(String name) {
        this.identifier = name;
    }

    /** Getter for name (we call "identifier name for this set)*/
    public String getName() {
        return identifier;
    }

    @Override
    public boolean isSimpleSet() {
        return true;
    }
}
