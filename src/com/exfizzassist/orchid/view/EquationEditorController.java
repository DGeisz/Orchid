package com.exfizzassist.orchid.view;

import com.exfizzassist.orchid.MainApp;
import com.exfizzassist.orchid.model.editor_model.Dock;
import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EquationEditorController {

    /** Reference to the main application*/
    private MainApp mainApp;

    /** Reference to the editor complex*/
    private EditorComplex editorComplex;
    
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
    private int currId;

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
        currId = 0;
        currSequence = "";
    }

    /**
     * Initializes the equation editor controller. Automatically
     * called after fxml has been loaded.
     */
    @FXML
    private void initialize() {
        //Test code to be sure I can control html from this class.
        editorEngine = mainEditor.getEngine();
        termsEngine = workingTerms.getEngine();

        /* If there's persisted content, this would be the place to load it in. */

        String content = "<html><body id=\"editor-body\"></body></html>";

        editorEngine.setUserStyleSheetLocation(getClass().getResource("../style/WebViewStyle.css").toString());
        editorEngine.loadContent(content, "text/html");
        termsEngine.loadContent(content, "text/html");

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
            currElement = doc.getElementById(Integer.toString(currId));
            currSequence += event.getCharacter();
            currElement.setTextContent(currSequence);
        }
        
        currElement.setAttribute("class", dock.sequenceStatus(currSequence));
        handled = false;
    }

    /**
     * Handles special keys like backspace otherwise
     */
    private void handleSpecialKeys(Document doc, KeyEvent event) {
        currElement = doc.getElementById(Integer.toString(currId));

        if (event.getCode().equals(KeyCode.BACK_SPACE)) {
            if (currSequence.length() > 0) {
                currSequence = currSequence.substring(0, currSequence.length() - 1);
                currElement.setTextContent(currSequence);
            }
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
     * Called by the main app to give reference
     * to the editor complex
     *
     * @param _editorComplex
     */
    public void setEditorComplex(EditorComplex _editorComplex) {
        editorComplex = _editorComplex;
        dock = editorComplex.getDock();
        editorComplex.newLine();
        currId = dock.getCurrentId();
        Document doc = editorEngine.getDocument();
        Element body = doc.getElementById("editor-body");
        Element newHtmlLine = doc.createElement("span");
        newHtmlLine.setAttribute("id", Integer.toString(currId));
        newHtmlLine.setAttribute("class", "line");
    }
    
    
}

