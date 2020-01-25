package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.basic_math.OrchidTerm;

public class TermFactory extends OrchidFactory {

    /** The factory is also inherently a term which has a
     * name within the editor.  This is its name.*/
    String factoryName;

    /** Reference to the OrchidTerm that is "output"
     * by this factory*/
    OrchidTerm spawn;

    /** Initializer for the Term Factory.  Takes
     * the inputTerm as input and creates a factory out
     * of the map properties of the set of which the term
     * is an element
     *
     * @param inputTerm*/
    TermFactory(OrchidTerm inputTerm) {
        factoryName = inputTerm.getName();
        sockets = inputTerm.getSet().generateSockets();
        spawn = inputTerm.getSet().generateSpawn();
    }

    @Override
    int[] socketConfigure(int lastId, int nextId) {
        return new int[0];
    }
}
