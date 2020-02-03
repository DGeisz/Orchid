package com.exfizzassist.orchid.model.sockets;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class EndLineSocket extends OrchidSocket {
    @Override
    public String sequenceStatus(String sequence) {
        return null;
    }

    @Override
    public boolean isAllowedSequence(String sequence) {
        return false;
    }

    @Override
    public ArrayList<String> commitSequence(String sequence, Document document, String lastId, String nextId) {
        return null;
    }
}
