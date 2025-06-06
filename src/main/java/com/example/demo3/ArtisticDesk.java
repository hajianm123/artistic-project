package com.example.demo3;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ArtisticDesk {
    public static Stage stage;
    private static String selectedShape = "";

    public static void goToThirdView() {

        AnchorPane root = new AnchorPane();
        root.setMinSize(800, 500);
        Scene photographyScene = new Scene(root);

        Button backButton = new Button("back");
        AnchorPane.setBottomAnchor(backButton, 10.0);
        AnchorPane.setRightAnchor(backButton, 10.0);
        backButton.setOnAction(event -> {
            stage.setScene(HelloApplication.scene);
        });

        VBox vBox = new VBox(10);
        HBox hBoxForItems = new HBox();
        HBox hBoxForDraw = new HBox();
        vBox.getChildren().addAll(hBoxForItems, hBoxForDraw);
        root.getChildren().add(backButton);

        Canvas canvas = new Canvas(600, 400);
        GraphicsContext getcolor = canvas.getGraphicsContext2D();
        hBoxForDraw.getChildren().add(canvas);


        MenuButton menuButton = new MenuButton("اشکال");
        MenuItem circle = new MenuItem("دایره");
        MenuItem line = new MenuItem("خط");
        MenuItem rectangle = new MenuItem("مستطیل");
        MenuItem square = new MenuItem("مربع");
        ColorPicker colorPicker = new ColorPicker();
        Button drawButton = new Button("رسم");

        menuButton.getItems().addAll(circle, line, rectangle, square);
        hBoxForItems.getChildren().addAll(menuButton, colorPicker, drawButton);

        hBoxForItems.setPadding(new Insets(10, 0, 0, 10));
        hBoxForItems.setPrefWidth(790);
        hBoxForItems.setStyle("-fx-border-color: blue; -fx-border-radius: 10; -fx-border-width: 2px;");
        hBoxForDraw.setStyle("-fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2px;");

        root.getChildren().add(vBox);


        TextField radius = new TextField();
        TextField x = new TextField();
        TextField x2 = new TextField();
        TextField y = new TextField();
        TextField y2 = new TextField();
        TextField width = new TextField();
        TextField height = new TextField();


        Runnable clearFields = () -> {
            hBoxForItems.getChildren().retainAll(menuButton, colorPicker, drawButton);
        };


        circle.setOnAction(event -> {
            selectedShape = "circle";
            clearFields.run();
            radius.setPromptText("radius");
            x.setPromptText("x :");
            y.setPromptText("y :");
            hBoxForItems.getChildren().addAll(radius, x, y);
        });


        line.setOnAction(event -> {
            selectedShape = "line";
            clearFields.run();
            x.setPromptText("x :");
            y.setPromptText("y :");
            y2.setPromptText("y2 :");
            x2.setPromptText("x2 :");
            hBoxForItems.getChildren().addAll(x, y, x2, y2);
        });


        rectangle.setOnAction(event -> {
            selectedShape = "rectangle";
            clearFields.run();
            width.setPromptText("عرض");
            height.setPromptText("ارتفاع");
            x.setPromptText("x :");
            y.setPromptText("y :");
            hBoxForItems.getChildren().addAll(x, y, width, height);
        });


        square.setOnAction(event -> {
            selectedShape = "square";
            clearFields.run();
            width.setPromptText("طول و عرض");
            x.setPromptText("x :");
            y.setPromptText("y :");
            hBoxForItems.getChildren().addAll(x, y, width);
        });


        drawButton.setOnAction(event -> {
            getcolor.setFill(colorPicker.getValue());
            getcolor.setStroke(colorPicker.getValue());
            getcolor.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            switch (selectedShape){
                case "circle":
                    double r = Double.parseDouble(radius.getText());
                    double cx = Double.parseDouble(x.getText());
                    double cy = Double.parseDouble(y.getText());
                    if (cx - r >= 0 && cy - r >= 0 && cx + r <= canvas.getWidth() && cy + r <= canvas.getHeight()) {
                        getcolor.fillOval(cx - r, cy - r, 2 * r, 2 * r);
                    } else {
                        showAlert("دایره از محدوده‌ی صفحه خارج می‌شود!");
                    }
                    break;
                case "line":
                    double x1 = Double.parseDouble(x.getText());
                    double y1 = Double.parseDouble(y.getText());
                    double x2Val = Double.parseDouble(x2.getText());
                    double y2Val = Double.parseDouble(y2.getText());
                    if (x1 >= 0 && x1 <= canvas.getWidth() && x2Val >= 0 && x2Val <= canvas.getWidth() &&
                            y1 >= 0 && y1 <= canvas.getHeight() && y2Val >= 0 && y2Val <= canvas.getHeight()) {
                        getcolor.strokeLine(x1, y1, x2Val, y2Val);
                    } else {
                        showAlert("نقاط خط از محدوده‌ی صفحه خارج هستند!");
                    }

                    break;
                case "rectangle":
                    double rx = Double.parseDouble(x.getText());
                    double ry = Double.parseDouble(y.getText());
                    double rw = Double.parseDouble(width.getText());
                    double rh = Double.parseDouble(height.getText());
                    if (rx >= 0 && ry >= 0 && rx + rw <= canvas.getWidth() && ry + rh <= canvas.getHeight()) {
                        getcolor.fillRect(rx, ry, rw, rh);
                    } else {
                        showAlert("مستطیل از محدوده‌ی صفحه خارج می‌شود!");
                    }

                    break;
                case "square":
                    double sqx = Double.parseDouble(x.getText());
                    double sqy = Double.parseDouble(y.getText());
                    double side = Double.parseDouble(width.getText());
                    if (sqx >= 0 && sqy >= 0 && sqx + side <= canvas.getWidth() && sqy + side <= canvas.getHeight()) {
                        getcolor.fillRect(sqx, sqy, side, side);
                    } else {
                        showAlert("مربع از محدوده‌ی صفحه خارج می‌شود!");
                    }

                    break;
            }
        });

        stage.setScene(photographyScene);
        stage.setTitle("میز کار هنری");
        stage.setResizable(true);
        stage.show();
    }
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("هشدار");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
