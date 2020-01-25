package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.factories.OrchidFactory;

public class MapDefinitionFactory extends OrchidFactory {

    MapDefinitionFactory() {
    }

    @Override
    int[] socketConfigure(int lastId, int nextId) {
        return new int[0];
    }
}
