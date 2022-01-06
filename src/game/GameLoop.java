package game;

import game.Game;

public class GameLoop implements Runnable {


    private Game game;
    private boolean running;
    private final double updateRate = 1.0/60.0d;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop (Game game) {
        this.game = game;
    }


    public void run () {
        this.running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        while (this.running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate)/100d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator > updateRate) {
                this.update();
                accumulator -= updateRate;
            }
            this.render();
            this.printStats();
        }
    }

    public void render() {
        this.fps++;
    }

    public void update() {
        this.ups++;
    }

    public void printStats() {
        if (System.currentTimeMillis() > this.nextStatTime) {
            System.out.println(String.format("FPS: %d, UPS: %d", this.fps, this.ups));
            this.fps = 0;
            this.ups = 0;
            this.nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

}
