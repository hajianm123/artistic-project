package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LiteraryDesk {
    public static Stage stage;
    public ListView<String> myListView;


    public static void goToSecondView(){
        try {
            FXMLLoader loader = new FXMLLoader(PhotographyDesk.class.getResource("litercy_view.fxml"));
            AnchorPane root = loader.load();
            Scene photographyScene = new Scene(root);
            stage.setScene(photographyScene);
            stage.setTitle("میز کار ادبی");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML

    public void goBack(javafx.event.ActionEvent event) {
        stage.setScene(HelloApplication.scene);
        stage.setTitle("میز کار");
        stage.show();
    }
    @FXML
    TextField title;
    @FXML
    TextArea  description;
    @FXML
    DatePicker date;
    @FXML

    public void saveMemory(ActionEvent event) {
        Memory newMemory = new Memory();
        newMemory.subject = this.title.getText();
        newMemory.description = this.description.getText();
        newMemory.date = this.date.getValue().toString();
        myListView.getItems().add(newMemory.toString());
        title.setText("");
        description.setText("");
        date.setValue(null);
        myListView.setOnMouseClicked(event2 ->{
            String selected = myListView.getSelectionModel().getSelectedItem();
            Label showInfo = new Label();
            showInfo.setText(selected);
            Pane pane = new Pane(showInfo);
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initOwner(LiteraryDesk.stage);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        });
    }


}
