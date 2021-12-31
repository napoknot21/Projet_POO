package ig;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WelcomePanel extends JPanel implements Runnable {

    public int width;
    public int height;

    private Thread thread;
    private boolean running = false;
    private BufferedImage img;
    private Graphics2D g;

    public WelcomePanel () {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //Pour avoir la taille de l'écran
        this.width = dim.width;
        this.height = dim.height;
        this.setSize(this.width/2,this.height/2);
        this.setFocusable(true);
        this.requestFocus();
        //this.setVisible(true);
    }

    public void addNotification () {
        super.addNotify();
        if (this.thread == null) {
            this.thread = new Thread(this,"GameThread");
            this.thread.start();
        }
    }

    public void init() {
        this.setRunning(true);
        this.img = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
        this.g = (Graphics2D) this.img.getGraphics();
    }

    @Override
    public void run() {
        this.init();
        while (running) {
            this.update();
            this.render();
            this.draw();
        }
    }

    public void setRunning (boolean state) {
        this.running = state;
    }

    private int x = 0;

    public void update () {
        this.x++;
        System.out.println(this.x);
    }

    public void render () {

    }

    public void draw () {

    }
}
