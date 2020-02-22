package com.exfizzassist.orchid.model.sets;

import java.text.SimpleDateFormat;

public class SimpleSet extends OrchidSet {

    /**
     * Set name
     */
    String name;

    public SimpleSet(String id, OrchidSet universalSet) {
        super(id);
        addSuperSet(universalSet);
    }

    public SimpleSet(String name, String id, OrchidSet universalSet) {
        super(id);
        addSuperSet(universalSet);
        this.name = name;
    }


    /**
     * Getter for name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean isSimpleSet() {
        return true;
    }
}
