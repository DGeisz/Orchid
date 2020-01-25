package com.exfizzassist.orchid.model.sockets;

public class DefinitionSocket extends OrchidSocket {

    /** This is the string that is plugged into this socket*/
    private String termName;

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
}
