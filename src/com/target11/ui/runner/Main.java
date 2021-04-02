package com.target11.ui.runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("../shortcut/shortcut.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("../launch/launchPage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

//        root.getStylesheets().add(getClass()
//                .getResource("root.css")
//                .toExternalForm());
/*

        primaryStage.setTitle("Assist Me");


        primaryStage.show();*/

        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        // primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
