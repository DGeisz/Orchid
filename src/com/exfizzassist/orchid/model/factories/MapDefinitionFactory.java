package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.ModelPlug;
import com.exfizzassist.orchid.model.plugs.NewTermNamePlug;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.plugs.TermPlug;
import com.exfizzassist.orchid.model.sets.MapSet;
import com.exfizzassist.orchid.model.sockets.DefinitionSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.sockets.TermSocket;
import com.exfizzassist.orchid.model.terms.BasicTerm;
import com.exfizzassist.orchid.model.terms.SetTerm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MapDefinitionFactory extends OrchidFactory {

    /**
     * Socket that holds the map's name
     */
    private DefinitionSocket definitionSocket;

    /**
     * Socket for the input (source) set
     */
    private TermSocket sourceSocket;

    /**
     * Socket for the output (target) set
     */
    private TermSocket targetSocket;


    public MapDefinitionFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        factoryType = "map-definition-factory";
        definitionSocket = new DefinitionSocket(_editorComplex, this);
        sourceSocket = new TermSocket(_editorComplex, this, editorComplex.getSetOfAllSets());
        targetSocket = new TermSocket(_editorComplex, this, editorComplex.getSetOfAllSets());
        targetSocket.setElementOf(editorComplex.getSetOfAllSets());
        definitionSocket.syncWithNext(sourceSocket);
        sourceSocket.syncWithNext(targetSocket);
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        prevSocket.syncWithNext(definitionSocket);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        targetSocket.syncWithNext(nextSocket);
    }

    @Override
    public void populateHTML(Document document) {
        super.populateHTML(document);
        Element thisElement = document.getElementById(getId());
        definitionSocket.populateHTML(document);
        Element colon = document.createElement("span");
        colon.setAttribute("class", "colon");
        colon.setTextContent(" : ");
        thisElement.appendChild(colon);
        sourceSocket.populateHTML(document);
        Element rightArrow = document.createElement("span");
        rightArrow.setAttribute("class", "right-arrow");
        rightArrow.setTextContent(" → ");
        thisElement.appendChild(rightArrow);
        targetSocket.populateHTML(document);
    }

    @Override
    public void commitNotification() {
        if (definitionSocket.plugged() && sourceSocket.plugged() && targetSocket.plugged()) {
            String termName = ((NewTermNamePlug) definitionSocket.getPlug()).getSequence();
            MapSet parentSet = editorComplex.getGenericMapSet(((SetTerm)((TermPlug) sourceSocket.getPlug()).getTerm()).getSet(),
                ((SetTerm)((TermPlug) targetSocket.getPlug()).getTerm()).getSet());
            BasicTerm newTerm = new BasicTerm(parentSet, editorComplex.newId(), false);
            newTerm.setName(termName);
            editorComplex.addTerm(termName, newTerm);
        }
    }

    @Override
    public OrchidPlug getFactoryOutput() {
        if (output == null) {
            output = new ModelPlug(editorComplex);
            output.setFactory(this);
            parentId = output.getId();
        }
        return output;
    }

    @Override
    public boolean isFullyPlugged() {
        return definitionSocket.isFullyPlugged()
            && sourceSocket.isFullyPlugged()
            && targetSocket.isFullyPlugged();
    }

    @Override
    public OrchidSocket firstUnfilledSocket() {
        if (!definitionSocket.plugged()) {
            return definitionSocket;
        } else if (!sourceSocket.isFullyPlugged()) {
            return sourceSocket.firstUnfilledSocket();
        }
        return targetSocket.firstUnfilledSocket();
    }
}
