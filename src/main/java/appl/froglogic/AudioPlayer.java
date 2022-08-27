package appl.froglogic;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer extends Thread {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    @Override
    public void run() {
        String bip = resourceBundle.getString("audio.location");
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

}


