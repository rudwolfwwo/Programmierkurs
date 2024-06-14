package Schildkroete.src.data;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Hase extends Tier implements Runnable {
    private Thread thread;
    private int sleepProbability;
    private PropertyChangeSupport changes;

    public Hase(Position starting_position, int running_speed, String pathToImage, int spriteSize, int sleepProbability) throws IOException {
        super(starting_position, running_speed, pathToImage, spriteSize);
        this.sleepProbability = sleepProbability;
        changes = new PropertyChangeSupport(this);
    }
    public int getSleepProbability() {
        return sleepProbability;
    }
    private static boolean checkSleepProbability(int sleepProbability) {
        return sleepProbability >= 0 && sleepProbability <= 100;
    }

    public void setSleepProbability(int sleepProbability) throws IllegalArgumentException {
        if (checkSleepProbability(sleepProbability)) {
            this.sleepProbability = sleepProbability;
        } else {
            throw new IllegalArgumentException("Sleep Probability is not between 0 and 100!");
        }
    }

    public boolean isBehindXPosition(int value) {
        return this.getCurrentPosition().getX() > value;
    }

    public int getCurrentXPosition() {
        return this.getCurrentPosition().getX();
    }

    public void draw(Graphics g) {
        super.draw(g);
    }

    public void reset() {
        super.resetAnimal();
        //this.setCurrentPosition(this.getStartingPosition());
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }
    public void stop() {
        thread.interrupt();
        thread = null;
    }

    @Override
    public void run() {
        while (thread != null && !thread.isInterrupted()) {
            if (ThreadLocalRandom.current().nextInt(0,100) > sleepProbability) {
                int x = this.getCurrentPosition().getX();
                super.makeStep();
                changes.firePropertyChange("hase_position",x,getCurrentPosition().getX());
            }
            try {
                Thread.sleep(updateFrequency);
            } catch (InterruptedException i) {}
        }
    }
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}
