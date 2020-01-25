package com.exfizzassist.orchid.model.basic_math;


import com.exfizzassist.orchid.model.sets.HigherOrderSet;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.SetSocket;
import com.exfizzassist.orchid.model.sockets.TermSocket;

import java.util.ArrayList;

public class OrchidMap {
    /** Input Set*/
    private OrchidSet input;

    /** Output set*/
    private OrchidSet output;

    OrchidMap(OrchidSet input, OrchidSet output) {
        this.input = input;
        this.output = output;
    }

    public OrchidSet getInput() {
        return input;
    }

    public OrchidSet getOutput() {
        return output;
    }

    public OrchidSocket generateInputSocket() {
        if (input.isHigherOrderSet()) {
            return new SetSocket((HigherOrderSet) input);
        }
        return new TermSocket(input);
    }
}
