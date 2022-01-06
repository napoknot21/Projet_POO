package game;

import ig.WelcomeWindow;

import java.awt.*;

public class Game {

    private WelcomeWindow ww;

    public Game () {
        EventQueue.invokeLater(() -> {
            this.ww = new WelcomeWindow();
            this.ww.setVisible(true);
        });

    }

}
