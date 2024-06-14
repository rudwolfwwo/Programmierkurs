package gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private AtomicInteger guthaben;

    PropertyChangeSupport changes = new PropertyChangeSupport(this);
    public BankAccount(int guthaben) {
        this.guthaben = new AtomicInteger(guthaben);
    }
    public void deposit(int betrag) {
        guthaben.getAndAdd(betrag);
        changes.firePropertyChange("Lohnabrechnung | Einzahlung von " +
                betrag + " € | Neuer Kontostand: " + guthaben,null,null);
    }
    public void draw (int betrag) {
        guthaben.getAndAdd(-betrag);
        changes.firePropertyChange("Rechnung | Abheben von " +
                betrag + " € | Neuer Kontostand: " + guthaben,null,null);
    }
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
}
