package com.exfizzassist.orchid;

import com.exfizzassist.orchid.model.editor_model.EditorComplex;
import com.exfizzassist.orchid.view.EquationEditorController;
import com.exfizzassist.orchid.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private EditorComplex editorComplex;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Orchid");

        /*TODO: Set the application icon*/
//        this.primaryStage.getIcons().add();

        /*Build the outer architecture of the application*/
        initRootLayout();

        /* TODO: EPOCH II: Determine if there's persistence, and load
        *   an EditorComplex specific to the persisted file.*/
        editorComplex = new EditorComplex();

        /*Display the primary stage*/
        primaryStage.show();

        /* Populate the root layout with the editor*/
        showEquationEditor();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            /* Load root layout from fxml file. */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            /* Show the scene containing the root layout. */
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            /* Give the controller access to the main app. */
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the main equation editor screen inside the root layout.
     */
    public void showEquationEditor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EquationEditor.fxml"));
            AnchorPane equationEditor = loader.load();

            /* Set main equation editor into the center of root layout. */
            rootLayout.setCenter(equationEditor);

            /*Create new equationEditorController*/
            EquationEditorController editorController = loader.getController();

            /* Give equation editor access to the main app. */
            editorController.setMainApp(this);

            /* Use the editorComplex to configure the EquationEditorController */
            editorComplex.configureEditorController(editorController);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}