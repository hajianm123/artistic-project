package com.example.demo3;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Paths;

public class CinemaDesk {
    public static Stage stage;
    @FXML
    public Slider volumeSlider;

    public static void goToFourthView() {
        try {
            FXMLLoader loader = new FXMLLoader(PhotographyDesk.class.getResource("cinema_view.fxml"));
            StackPane root = loader.load();
            Scene photographyScene = new Scene(root);
            stage.setScene(photographyScene);
            stage.setTitle("میز کار سینمایی");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML

    public void goBack(javafx.event.ActionEvent event) {
        if (mediaPlayer != null)
            mediaPlayer.stop();
        stage.setScene(HelloApplication.scene);
        stage.setTitle("میز کار");
        stage.show();
    }

    MediaPlayer mediaPlayer;
    String videoPath;
    @FXML
    MediaView mediaView;
    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private Label durationLabel;
    @FXML
    Slider slider;

    @FXML
    public void setVideo(ActionEvent event) {
        mediaPlayer = null;
        String selectedVideo = myChoiceBox.getValue();
        if (selectedVideo.equals("فیلم ۱")) {
            videoPath = Paths.get("src/main/resources/videos/first.mp4").toUri().toString();
            Media media1 = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media1);
            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.currentTimeProperty().addListener((observable, oldTime, newTime) -> {
                slider.setValue(newTime.toSeconds());
            });

            mediaPlayer.setOnReady(() -> {
                volumeSlider.setMax(mediaPlayer.getVolume());
                slider.setMax(mediaPlayer.getTotalDuration().toSeconds());
                volumeSlider.setValue(volumeSlider.getMax());
            });
            mediaPlayer.play();
            startTimeUpdater();
            System.out.println("film1");
        } else if (selectedVideo.equals("فیلم ۲")) {
            videoPath = Paths.get("src/main/resources/videos/second.mp4").toUri().toString();
            Media media2 = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media2);
            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.currentTimeProperty().addListener((observable, oldTime, newTime) -> {

                slider.setValue(newTime.toSeconds());
            });
            mediaPlayer.setOnReady(() -> {
                volumeSlider.setMax(Double.MAX_VALUE);
                slider.setMax(mediaPlayer.getTotalDuration().toSeconds());
                volumeSlider.setValue(volumeSlider.getMax());
            });
            mediaPlayer.play();

            startTimeUpdater();
            System.out.println("film2");
            slider.setMax(mediaPlayer.getTotalDuration().toSeconds());
        }
    }

    public void play(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }

    }

    public void pause(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }

    }

    private void startTimeUpdater() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                double currentTime = mediaPlayer.getCurrentTime().toSeconds();


                int minutes = (int) (currentTime / 60);
                int seconds = (int) (currentTime % 60);


                durationLabel.setText(String.format("%d:%02d", minutes, seconds));
            }
        };
        timer.start();
    }

    public void seekInVideo(MouseEvent mouseEvent) {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

    public void stop(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void setVolume(MouseEvent event) {
        mediaPlayer.setVolume(volumeSlider.getValue());
    }
}
