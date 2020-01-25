package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.basic_math.AnonymousTerm;
import com.exfizzassist.orchid.model.basic_math.NamedTerm;
import com.exfizzassist.orchid.model.basic_math.OrchidTerm;
import com.exfizzassist.orchid.model.sets.MapSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;

public class TermFactory extends OrchidFactory {

    /** The factory is also inherently a term which has a
     * name within the editor.  This is its name.*/
    String factoryName;

    /** OrchidTerm outputted by this factory*/
    OrchidTerm spawn;

    /** OrchidTerm inputted into this factory*/
    OrchidSocket socket;

    /** Initializer for the Term Factory.  Takes
     * the inputTerm as input and creates a factory out
     * of the map properties of the set of which the term
     * is an element
     *
     * Assumes that inputTerm is an element of a MapSet
     *
     * @param inputTerm*/
    TermFactory(NamedTerm inputTerm) {
        factoryName = inputTerm.getName();
        MapSet inputSet = (MapSet) inputTerm.getSet();
        spawn = new AnonymousTerm(inputSet.getMap().getOutput());
        socket = inputSet.getMap().generateInputSocket();
    }

    @Override
    int[] socketConfigure(int lastId, int nextId) {
        return new int[0];
    }
}
