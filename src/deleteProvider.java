import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class deleteProvider extends JFrame {
	private static final long serialVersionUID = 1L;
	private providerList providers;
	private deleteProvider delProv;
	JButton choose, verify, cancel;
	JLabel pNumberLabel;
	JTextField pNumber;
	JTextArea display;
	provider selected;
	
	public deleteProvider(providerList prov, final MainMenu handle) {
		super("Delete Provider");
		providers = prov;
		delProv = this;
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		this.setBounds(200, 150, 500, 500);

		choose = new JButton("Select Provider");
		verify = new JButton("Delete");
		cancel = new JButton("Cancel");
		pNumber = new JTextField(10);
		display = new JTextArea(15,15);
		pNumberLabel = new JLabel("Provider Number (9 digits): ");
		
		//keep button invisible until user has a member selected
		verify.setVisible(false);
		
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String number;
				int numberInt = 0;
				boolean isCorrect = true;
				
				try {
					number = pNumber.getText();
					numberInt = Integer.parseInt(number);
					if (number.length() > 9)
						isCorrect = false;
				}
				catch (RuntimeException e1) {
					isCorrect = false;
				}
				try {
					if (isCorrect == true) {
						selected = providers.getProvider(numberInt);
						//use selected member to display member info into text area
						String mName, mAddress, mCity, mState;
						int mZip, mNumb;
					
						mName = selected.getName();
						mNumb = selected.getNumber();
						mAddress = selected.getAddress();
						mCity = selected.getCity();
						mState = selected.getState();
						mZip = selected.getZipCode();
						mNumb = selected.getNumber();
					
						display.setText("--Provider Information--\nName: " + mName + "\nNumber: " + String.valueOf(mNumb) + "\nAddress: " + mAddress +
							"\nCity: " + mCity + "\nState: " + mState + "\nZipCode: " + String.valueOf(mZip));
	
						//reveal delete button
						choose.setVisible(false);
						verify.setForeground(Color.RED);
						verify.setVisible(true);
				}
					else
						JOptionPane.showMessageDialog(null, "One or More of the inputs was incorrect, Please check inputs and try again.");
				}
				catch (RuntimeException e2) {
					JOptionPane.showMessageDialog(null, "Provider does not exist");
				}
			}
		});
		
		verify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				providers.delete(selected.getNumber());
				handle.updateProviders(providers);
				delProv.setVisible(false);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delProv.setVisible(false);
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(pNumberLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(pNumber, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(choose, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(cancel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(verify, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		p.add(display, gbc);
	}
}
