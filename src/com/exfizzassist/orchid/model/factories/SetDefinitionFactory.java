package com.exfizzassist.orchid.model.factories;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.model.plugs.ModelPlug;
import com.exfizzassist.orchid.model.plugs.NewTermNamePlug;
import com.exfizzassist.orchid.model.plugs.OrchidPlug;
import com.exfizzassist.orchid.model.sets.OrchidSet;
import com.exfizzassist.orchid.model.sets.SimpleSet;
import com.exfizzassist.orchid.model.sockets.DefinitionSocket;
import com.exfizzassist.orchid.model.sockets.OrchidSocket;
import com.exfizzassist.orchid.model.terms.SetTerm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SetDefinitionFactory extends OrchidFactory {

    /**
     * This is the socket that will hold
     * the name of the set currently being defined
     */
    private DefinitionSocket definitionSocket;

    /*TODO: Right now, you can basically just define a set of numbers
     *  with this factory.  In the future, I want to add set properties,
     *  operations, notions of subsets, the whole melarchy. Basically just a
     *  quick fyi that this boi needs work*/

    public SetDefinitionFactory(EditorComplex _editorComplex, String prevSocketId, String nextSocketId) {
        super(_editorComplex);
        factoryType = "set-definition-factory";
        definitionSocket = new DefinitionSocket(_editorComplex, this);
        OrchidSocket prevSocket = _editorComplex.getSocket(prevSocketId);
        prevSocket.syncWithNext(definitionSocket);
        OrchidSocket nextSocket = _editorComplex.getSocket(nextSocketId);
        definitionSocket.syncWithNext(nextSocket);
    }

    @Override
    public void populateHTML(Document document) {
        super.populateHTML(document);
        Element thisElement = document.getElementById(getId());
        definitionSocket.populateHTML(document);
        /*TODO: I super need to figure out how sets will be defined.
        *  Members of sets need properties and rules, and these need to
        *  be defined */
        Element setDefinitionSyntax = document.createElement("span");
        setDefinitionSyntax.setAttribute("class", "set-definition");
        setDefinitionSyntax.setTextContent(" = {}");
        thisElement.appendChild(setDefinitionSyntax);
    }

    @Override
    public void commitNotification() {
        /*TODO: Again, this needs to determine what kind of set is being built.
        *  this is for a later epoch.*/
        if (definitionSocket.plugged()) {
            String setName = ((NewTermNamePlug) definitionSocket.getPlug()).getSequence();
            /*TODO: Change this to something more meaningful*/
            OrchidSet newSet = new SimpleSet(setName, editorComplex.newId(), editorComplex.getUniversalSet());
            editorComplex.addSet(newSet);
            SetTerm newTerm = new SetTerm(newSet, editorComplex.getSetOfAllSets(), editorComplex.newId(), false);
            newTerm.setName(setName);
            editorComplex.addTerm(setName, newTerm);
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
        return definitionSocket.isFullyPlugged();
    }

    @Override
    public OrchidSocket firstUnfilledSocket() {
        return definitionSocket.firstUnfilledSocket();
    }
}
