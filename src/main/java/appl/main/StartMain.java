package appl.main;

import appl.froglogic.AudioPlayer;
import appl.froglogic.FrogEngine;
import javafx.application.Platform;

public class StartMain {
    public static void main(String[] args) {
        FrogEngine frogEngine = new FrogEngine();
        Thread frogThread = new Thread(frogEngine);
        Platform.startup(() ->
        {
            Thread audio = new Thread(new AudioPlayer());
            audio.start();
        });
        frogThread.start();
    }
}
