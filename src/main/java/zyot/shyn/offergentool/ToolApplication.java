package zyot.shyn.offergentool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToolApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppState.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(ToolApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 720);
        stage.setTitle("Offer Gen Tool");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}