package appl.froglogic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import lombok.SneakyThrows;

public class FrogEngine extends JFrame implements ActionListener, Runnable {
    private static final Image image;
    private static int frameCounter;
    private static final PanelWithBackground panelwithBackground;
    private final Random random = new Random();
    private Timer timer;
    private int frameSizeX;
    private int frameSizeY;
    private int frameStartY;
    private static final int SLEEP_FROG_FRAME_VALUE;
    private static final int MAX_FROG_FRAME_VALUE;
    private static final long SLEEP_FROG_FRAME_TIME;
    private static final int FIRST_FRAME_SLEEPING_TIME;
    private static final int ZERO_VALUE;
    private static final ResourceBundle resourceBundle;
    private static final int RANDOM_FRAME_SIZE_MAX_BOUND;

    static {
        resourceBundle = ResourceBundle.getBundle("application");
        ImageIcon loadImage = new ImageIcon(resourceBundle.getString("image.location"));
        image = loadImage.getImage();
        panelwithBackground = new PanelWithBackground();
        SLEEP_FROG_FRAME_VALUE = 3;
        MAX_FROG_FRAME_VALUE = 90;
        SLEEP_FROG_FRAME_TIME = 100L;
        FIRST_FRAME_SLEEPING_TIME = 13000;
        ZERO_VALUE = 0;
        frameCounter = ZERO_VALUE;
        RANDOM_FRAME_SIZE_MAX_BOUND = 300;

    }

    public FrogEngine() {
        setBounds(getStartX(), frameStartY, frameSizeX, frameSizeY);
        setTitle("Frog");
        add(panelwithBackground);
        setVisible(true);
        ++frameCounter;
    }

    @SneakyThrows
    public void actionPerformed(ActionEvent e) {
        sleepManager();
        new FrogEngine();
        if (frameCounter > MAX_FROG_FRAME_VALUE) {
            timer.stop();
        }
    }

    private void sleepManager() throws InterruptedException {
        if (frameCounter == 1) {
            Thread.sleep(FIRST_FRAME_SLEEPING_TIME);
        }
        if (frameCounter > SLEEP_FROG_FRAME_VALUE) {
            Thread.sleep(SLEEP_FROG_FRAME_TIME);
        }
    }

    private int getStartX() {
        if (frameCounter == ZERO_VALUE) {
            int startFramePoint = 600;
            this.frameSizeX = startFramePoint;
            this.frameSizeY = startFramePoint;
            this.frameStartY = ZERO_VALUE;
            return ZERO_VALUE;
        } else {
            frameSizeX = randomSize();
            frameSizeY = randomSize();
            int boundOfY = 1200;
            frameStartY = random.nextInt(boundOfY);
            int boundOfX = 1600;
            return random.nextInt(boundOfX);
        }
    }
    @Override
    public void run() {
        timer = new Timer(10, this);
        timer.start();
    }

    private int randomSize() {
        return random.nextInt(RANDOM_FRAME_SIZE_MAX_BOUND);
    }

    private static class PanelWithBackground extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, ZERO_VALUE, ZERO_VALUE, getWidth(), getHeight(), this);
        }
    }

}


