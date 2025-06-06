package com.example.demo3;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
     public static Scene scene;
    @Override
    public void start(Stage stage) {
        double buttonWidth = 100;
        AnchorPane anchorPane = new AnchorPane();
        VBox vBox = new VBox();
        VBox innerVBox = new VBox(7);
        Label label = new Label("میز کار");
        label.setStyle("-fx-font-size: 20px;");
        Button goToFirstView = new Button("میز کار عکاسی");
        Button goToSecondView = new Button("میز کار ادبی");
        Button goToThirdView = new Button("میز کار هنری");
        Button goToFourthView = new Button("میز کار سینمایی");
        goToFirstView.setMinWidth(buttonWidth);
        goToFirstView.setMaxWidth(buttonWidth);
        goToSecondView.setMinWidth(buttonWidth);
        goToSecondView.setMaxWidth(buttonWidth);
        goToThirdView.setMinWidth(buttonWidth);
        goToThirdView.setMaxWidth(buttonWidth);
        goToFourthView.setMinWidth(buttonWidth);
        goToFourthView.setMaxWidth(buttonWidth);
        Separator forBtn1 = new Separator();
        forBtn1.setOrientation(Orientation.HORIZONTAL);
        Separator forBtn2 = new Separator();
        forBtn2.setOrientation(Orientation.HORIZONTAL);
        Separator forBtn3 = new Separator();
        forBtn3.setOrientation(Orientation.HORIZONTAL);
        anchorPane.getChildren().add(vBox);
        AnchorPane.setTopAnchor(vBox, 20.0);
        AnchorPane.setLeftAnchor(vBox, 20.0);
        AnchorPane.setRightAnchor(vBox, 20.0);
        AnchorPane.setBottomAnchor(vBox, 20.0);
        vBox.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 5px;");
        vBox.getChildren().add(label);
        vBox.getChildren().add(innerVBox);
        innerVBox.setAlignment(Pos.CENTER);
        innerVBox.getChildren().addAll(goToFirstView,forBtn1, goToSecondView,forBtn2, goToThirdView,forBtn3, goToFourthView);
        scene = new Scene(anchorPane,400,250);
        stage.setScene(scene);
        stage.setTitle("میز کار");
        goToFirstView.setOnAction(event ->{
            PhotographyDesk.stage = stage;
            PhotographyDesk.goToFirstView();
        });
        goToSecondView.setOnAction(event ->{
            LiteraryDesk.stage = stage;
            LiteraryDesk.goToSecondView();
        });
        goToThirdView.setOnAction(event ->{
            ArtisticDesk.stage = stage;
            ArtisticDesk.goToThirdView();
        });
        goToFourthView.setOnAction(event ->{
            CinemaDesk.stage = stage;
            CinemaDesk.goToFourthView();
        });
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}