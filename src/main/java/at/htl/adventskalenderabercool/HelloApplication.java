package at.htl.adventskalenderabercool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/at/htl/adventskalenderabercool/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        scene.getStylesheets().add(getClass().getResource("/at/htl/adventskalenderabercool/style.css").toExternalForm());
        stage.setMaximized(true);
        stage.setTitle("Merry Christmas!");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}