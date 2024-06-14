package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class BankFrame extends JFrame implements ActionListener, PropertyChangeListener {
	
	private CashTransfer c;
	private JTextArea info;
	private JTextField amount;
	private JButton startWage;
	private JButton startBill;
	private JButton go;
	private gui.BankAccount b;
	
	public BankFrame(CashTransfer c, gui.BankAccount b) {
		this.c = c;
		this.b = b;
		b.addPropertyChangeListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel information = new JPanel();
		information.setLayout(new BorderLayout());
		JLabel konto = new JLabel("Konto:");
		information.add(konto, BorderLayout.NORTH);
		info = new JTextArea(5,20);
		info.setEnabled(false);
		info.setCaretPosition(0);
		information.add(new JScrollPane(info), BorderLayout.CENTER);
		add(information, BorderLayout.NORTH);
		
		JPanel threads = new JPanel();
		startWage = new JButton("Lohnabrechnung starten");
		startWage.addActionListener(this);
		threads.add(startWage);
		startBill = new JButton("Rechnungen starten");
		startBill.addActionListener(this);
		threads.add(startBill);
		add(threads, BorderLayout.CENTER);
		
		JPanel cashTransfer = new JPanel();
		JLabel transfer = new JLabel("Überweisung von: ");
		cashTransfer.add(transfer);
		amount = new JTextField(20);
		cashTransfer.add(amount);
		go = new JButton("Durchführen");
		go.addActionListener(this);
		cashTransfer.add(go);
		add(cashTransfer, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}
	
	/**
	 * Adds the message passed as a parameter to the message list in the TextArea of this Frame.
	 * @param message the message that should be displayed in the TextArea
	 */
	public void addToTextArea(String message) {
		info.insert(message + "\n", 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Lohnabrechnung starten")) {
			WageThread w = new WageThread(b,200,1500);
			startWage.setEnabled(false);
		} else if (e.getActionCommand().equals("Rechnungen starten")) {
			BillThread bil = new BillThread(b,100,1000);
			startBill.setEnabled(false);
		} else if (e.getActionCommand().equals("Durchführen")) {
			try {
				int x = Integer.parseInt(amount.getText());
				if (x <= 0) throw new Exception();
				try {
					c.transfer(x);
				} catch (Exception excep) {
					JOptionPane.showMessageDialog(this,excep.getMessage());
				}
			} catch (Exception excep) {
				JOptionPane.showMessageDialog(this,"Keine Ganze positive Zahl!");
				amount.setText("");
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		info.append(evt.getPropertyName() + "\n");
	}
	
	public static void main(String[] args) {
		gui.BankAccount b = new gui.BankAccount(1000);
		new BankFrame(new CashTransfer(3, b, 10000),b);
	}
	
}
