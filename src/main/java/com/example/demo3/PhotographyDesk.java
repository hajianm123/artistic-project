package com.example.demo3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PhotographyDesk {
    @FXML
    VBox vbox;
    Button goToFirstView;
    public static Stage stage;
    public static void goToFirstView(){
        try {
            FXMLLoader loader = new FXMLLoader(PhotographyDesk.class.getResource("photographyDesk.fxml"));
            VBox root = loader.load();
            Scene photographyScene = new Scene(root);
            stage.setScene(photographyScene);
            stage.setTitle("میز کار عکاسی");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML

    public void goBack(ActionEvent event) {
            stage.setScene(HelloApplication.scene);
            stage.setTitle("میز کار");
            stage.show();
    }

    @FXML
    public void showphoto(javafx.scene.input.MouseEvent mouseEvent) {

        String url = ((ImageView) mouseEvent.getSource()).getImage().getUrl();
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        if (imageView.getImage() != null) {
            Pane pane = new Pane(imageView);
            Scene scene = new Scene(pane);
            Stage stage2 = new Stage();
            stage2.initOwner(stage);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.setScene(scene);
            stage2.setResizable(false);
            stage2.show();
        } else {
            System.out.println("تصویر بارگذاری نشده است.");
        }
    }
}
