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

        //Set app icon
//        this.primaryStage.getIcons().add();

        initRootLayout();

        /* The editor complex encapsulates everything thats going on in the editor,
        * so when adding persistence, this is the place to do it.*/
        editorComplex = new EditorComplex();
        showEquationEditor();
/*        ***WebView Code***
        WebView webView = new WebView();

        WebEngine webEngine = webView.getEngine();
        String content = "<math displaystyle=\"true\"> \n" +
            "\n" +
            "  <munderover >\n" +
            "    <mo> &#x222B; <!--INTEGRAL--> </mo>\n" +
            "    <mn> 0 </mn>\n" +
            "    <mi> &#x221E; <!--INFINITY--> </mi>\n" +
            "  </munderover>\n" +
            "\n" +
            "</math>";

        webEngine.loadContent(content, "text/html");
        webView.getEngine().load("http://google.com");

        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);*/
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
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
            AnchorPane equationEditor = (AnchorPane) loader.load();

            // Set main equation editor into the center of root layout.
            rootLayout.setCenter(equationEditor);

            //Give equation editor access to the main app.
            EquationEditorController editorController = loader.getController();
            editorController.setMainApp(this);
            editorController.setEditorComplex(editorComplex);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}