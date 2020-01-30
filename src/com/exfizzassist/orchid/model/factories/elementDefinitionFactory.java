package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.factories.OrchidFactory;
import com.exfizzassist.orchid.model.sockets.DefinitionSocket;
import com.exfizzassist.orchid.model.sockets.SetSocket;

import java.util.ArrayList;

public class elementDefinitionFactory extends OrchidFactory {

    elementDefinitionFactory() {}

    @Override
    int[] socketConfigure(int lastId, int nextId) {
        return new int[0];
    }
//        sockets = new ArrayList<>();
//        sockets.add(new DefinitionSocket());
//        sockets.add(new SetSocket());
//    }
//
//
//    @Override
//    int[] socketConfigure(int lastId, int currId) {
//        sockets.get(0).setId(currId, lastId, currId + 1);
//        sockets.get(1).setId(currId + 1, currId, currId + 2);
//        return new int[]{currId + 1, currId + 2};
//    }
}
