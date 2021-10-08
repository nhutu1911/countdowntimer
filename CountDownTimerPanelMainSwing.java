package project1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CountDownTimerPanelMainSwing extends JPanel {

	private JMenuItem quitItem;
	private JMenuItem suspendItem;
	private JButton suspendTrue;
	private JButton suspendFalse;

	public CountDownTimerPanelMainSwing(JMenuItem quitItem, JMenuItem suspendItem) {
		this.quitItem = quitItem;
		this.suspendItem = suspendItem;

		JPanel panel1 = new JPanel();
		JPanel panel2= new JPanel();
		JPanel panel3 = new JPanel();
		panel1.add(new CountDownTimerPanelSwing());
		panel2.add(new CountDownTimerPanelSwing());
		panel3.add(new CountDownTimerPanelSwing());

		suspendFalse = new JButton("Suspend false");
		suspendTrue = new JButton("Suspend true");
		add(panel1);
		add(panel2);
		add(panel3);
		add (suspendFalse);
		add (suspendTrue);

		quitItem.addActionListener(new MyListener());
		suspendItem.addActionListener(new MyListener());
		suspendTrue.addActionListener(new MyListener());
		suspendFalse.addActionListener(new MyListener());
	}

	private class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == quitItem){
				System.exit(1);
			}
			if (e.getSource() == suspendFalse)
				CountDownTimer.setSuspended(false);
			if (e.getSource() == suspendTrue)
				CountDownTimer.setSuspended(true);

		}

	}
}
