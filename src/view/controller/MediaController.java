package view.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.LaunchWindow;

public class MediaController implements Initializable{

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;


    
    @FXML
    private MediaView mediaView;

    private File file;
    
    private Media media;
    
    private MediaPlayer mediaPlayer;
    
    @FXML
    void backToHomePage(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\HomePage.fxml");
    }

    @FXML
    void pauseMedia(ActionEvent event) {
    	mediaPlayer.pause();
    }

    @FXML
    void playMedia(ActionEvent event) {
    	mediaPlayer.play();
    }

    @FXML
    void resetMedia(ActionEvent event) {
    	if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
    	mediaPlayer.seek(Duration.seconds(0.0));
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		file = new File("D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\CAEVideo.mp4");
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
    	mediaView.setMediaPlayer(mediaPlayer);

	}

}