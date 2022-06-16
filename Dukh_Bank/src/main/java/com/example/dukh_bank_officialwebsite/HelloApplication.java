package com.example.dukh_bank_officialwebsite;
import javafx.application.Application;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1323, 696);
        HelloController helloController= fxmlLoader.getController();
        helloController.setMyStage(stage);
        System.out.println(scene);
        System.out.println(stage);
        stage.setTitle("Dukh Bank Management Portal");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
}

    public static void main(String[] args) {
        launch();
    }
}