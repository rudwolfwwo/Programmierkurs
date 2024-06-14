package Schildkroete.src.data;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class Schildkroete extends Tier implements Runnable {
    private Thread thread;
    private PropertyChangeSupport changes;
    public Schildkroete(Position starting_position, int running_speed, String pathToImage, int spriteSize) throws IOException {
        super(starting_position, running_speed, pathToImage, spriteSize);
        changes = new PropertyChangeSupport(this);
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
            int x = this.getCurrentPosition().getX();
            super.makeStep();
            changes.firePropertyChange("schildkroete_position",x,getCurrentPosition().getX());

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
