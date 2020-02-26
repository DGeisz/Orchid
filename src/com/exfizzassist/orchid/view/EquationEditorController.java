package com.exfizzassist.orchid.view;

import com.exfizzassist.orchid.MainApp;
import com.exfizzassist.orchid.model.editor_model.Dock;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class EquationEditorController {

    /** Reference to the main application*/
    private MainApp mainApp;
    
    /** Reference to the equation dock*/
    private Dock dock;
    

    @FXML
    WebView mainEditor;
    /** WebEngine controlling the editor view.*/
    WebEngine editorEngine;

    @FXML
    WebView workingTerms;
    /** WebEngine controlling the view of working objects.*/
    WebEngine termsEngine;


    /**
     * Id of current object being created
     */
    private String currId;

    /**
     * Id of the previous object being created
     */
    private int prevId;

    /**
     * String sequence of object being created
     */
    private String currSequence;

    /**
     * Current DOM element being manipulated*/
    private Element currElement;



    /**
     * Indicates whether a keyEvent was already handled by the KeyPressed listener*/
    private boolean handled;


    /**
     * Constructor.
     * Called before the initialize() method.
     * */
    public EquationEditorController() {
        currId = "";
        currSequence = "";
    }

    /**
     * Initializes the equation editor controller. Automatically
     * called after fxml has been loaded.
     */
    @FXML
    private void initialize() {
        /* Creates the web engines for the two main windows*/
        editorEngine = mainEditor.getEngine();
        termsEngine = workingTerms.getEngine();


        /*Skeleton content for the current windows*/
        String editorContent = "<html><body id='editor-body'></body></html>";
        String termsContent = "<html><body id='terms-body'>" +
            "<p>Terms:</p>" +
            "<span id='defined-terms'>" +
            "</span>" +
            "</body>" +
            "</html>";

        /* Loads Stylesheet for the editor window*/
        editorEngine.setUserStyleSheetLocation(getClass().getResource("../style/WebViewStyle.css").toString());
        /*TODO: Change to its own sheet*/
        termsEngine.setUserStyleSheetLocation(getClass().getResource("../style/WebViewStyle.css").toString());

        /* Loads the starting content of this session*/
        editorEngine.loadContent(editorContent, "text/html");
        termsEngine.loadContent(termsContent, "text/html");


        /* The following commands tell the windows what to do when keys are typed*/
        mainEditor.setOnKeyTyped(event -> {
            Document doc = editorEngine.getDocument();
            handleNewKey(doc, event);
        });

        mainEditor.setOnKeyPressed(event -> {
            Document doc = editorEngine.getDocument();
            handleSpecialKeys(doc, event);
        });
    }

    /**
     * Handles a new key being typed into the console.
     *
     * @param doc
     * @param event
     */
    private void handleNewKey(Document doc, KeyEvent event) {
        if (!handled) {
            dock.intakeCharacter(event.getCharacter(), doc);
        }
        handled = false;
    }

    /**
     * Handles special keys like backspace otherwise
     */
    private void handleSpecialKeys(Document doc, KeyEvent event) {

        if (event.getCode().equals(KeyCode.BACK_SPACE)) {
            dock.handleBackSpace(doc);
            /*TODO: PHASE II: implement deletion from controller side*/
            /*This is basic functionality to delete a char.  Change when
            * you actually implement this*/
//            if (currSequence.length() > 0) {
//                currSequence = currSequence.substring(0, currSequence.length() - 1);
//                currElement.setTextContent(currSequence);
//            }
            /*End of basic implementation.*/
            handled = true;
        } else if (event.getCode().equals(KeyCode.ENTER)) {
            dock.attemptCommitSequence(doc);
            handled = true;
        }
    }


    /**
     * Called by the main application to give a reference
     * back to itself
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * This takes the _DOCK from the editorComplex
     * and gives the dock access to the skeleton
     * HTML of the editor and terms.  The dock then populates the
     * skeletons with the initial HTML and tells the controller
     * currId.
     */
    public void latchOntoDock(Dock _dock) {
        dock = _dock;

        /* Allows the dock to populate the skeleton html and tell
        * this controller the currId. The whole getLoadWorker nonsense
        * is in place so populateEditorHTML is called after the webengine
        * loads */
        editorEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                Document editorDoc = editorEngine.getDocument();
                dock.populateEditorHTML(editorDoc);
            }
        });

        /*TODO: EPOCH I: PHASE Lambda. Populate the termsHTML as well.  In first round of implementation
        *  I'm focusing specifically on the editor window, so this will be done later*/
    }

    /**
     * FOR DEBUGGING
     */
    public Document getEditorDocument() {

        return editorEngine.getDocument();
    }

    /**
     * Returns the document of the Terms page*/
    public Document getTermsDocument() {
        return termsEngine.getDocument();
    }
}

