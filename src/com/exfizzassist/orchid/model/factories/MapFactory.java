package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sets.MapSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;

public class MapFactory extends OrchidFactory {

    /** The factory is also inherently a term which has a
     * name within the editor.  This is its name.*/
    String factoryName;

    /** OrchidTerm outputted by this factory*/
    OrchidPlug spawn;

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
    MapFactory(NamedTerm inputTerm) {
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
