package com.murilo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public synchronized void add(Button button, String color) {
            button.getStyleClass().add(color);
    }

    public synchronized void remove(Button button, String color) {
            button.getStyleClass().remove(color);
    }

    @Override
    public void start(Stage primaryStage) throws Exception, InterruptedException {

        GridPane primaryPane = new GridPane();
        VBox boxContainer = new VBox();
        Button buttonRed = new Button();
        Button buttonYellow = new Button();
        Button buttonGreen = new Button();

        buttonRed.setPrefHeight(100);
        buttonRed.setPrefWidth(100);
        buttonRed.getStyleClass().add("button");

        buttonYellow.setPrefHeight(100);
        buttonYellow.setPrefWidth(100);
        buttonYellow.getStyleClass().add("button");

        buttonGreen.setPrefHeight(100);
        buttonGreen.setPrefWidth(100);
        buttonGreen.getStyleClass().add("button");

        boxContainer.setSpacing(10);
        boxContainer.setPrefHeight(350);
        boxContainer.setPrefWidth(110);
        boxContainer.setPadding(new Insets(15, 0, 0, 5));

        boxContainer.getChildren().addAll(buttonGreen, buttonYellow, buttonRed);
        boxContainer.getStyleClass().add("box");

        primaryPane.setPrefHeight(400);
        primaryPane.setPrefWidth(600);
        primaryPane.setVgap(10);
        primaryPane.setPadding(new Insets(20, 0, 0, 250));

        GridPane.setColumnIndex(buttonRed, 5);
        GridPane.setRowIndex(buttonRed, 1);
        GridPane.setColumnIndex(buttonYellow, 5);
        GridPane.setRowIndex(buttonYellow, 2);
        GridPane.setColumnIndex(buttonGreen, 5);
        GridPane.setRowIndex(buttonGreen, 3);

        primaryPane.getChildren().add(boxContainer);

        Scene primaryScene = new Scene(primaryPane, 600, 400);
        primaryScene.getStylesheets().add(getClass().getResource("Pane.css").toExternalForm());
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Traffic Light");
        primaryStage.show();

        Runnable greenRunnable = () -> {
            try {
                Thread.sleep(1000);
                add(buttonGreen, "green");
                remove(buttonRed, "red");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            };

        Runnable yellowRunnable = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            add(buttonYellow, "yellow");
            remove(buttonGreen, "green");
        };

        Runnable redRunnable = () -> {
            try {
                Thread.sleep(5000);
                add(buttonRed, "red");
                remove(buttonYellow, "yellow");
                remove(buttonGreen, "green");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable auxRunnable = () -> {
            try {
                Thread.sleep(7000);
                remove(buttonRed, "red");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread lightGreen = new Thread(greenRunnable);
        Thread lightYellow = new Thread(yellowRunnable);
        Thread lightRed = new Thread(redRunnable);
        Thread aux = new Thread(auxRunnable);

        lightGreen.setPriority(10);
        lightYellow.setPriority(5);
        lightRed.setPriority(1);

        lightGreen.start();
        lightYellow.start();
        lightRed.start();
        aux.start();


    }
}