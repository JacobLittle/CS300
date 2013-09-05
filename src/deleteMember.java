import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteMember extends JFrame {
	private static final long serialVersionUID = 1L;
	private memberList members;
	private deleteMember delMem;
	JButton choose, verify, cancel;
	JLabel mNumberLabel;
	JTextField mNumber;
	JTextArea display;
	member selected;
	
	public deleteMember(memberList mem, final MainMenu handle) {
		super("Delete Member");
		members = mem;
		delMem = this;
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		this.setBounds(200, 150, 500, 500);

		choose = new JButton("Select Member");
		verify = new JButton("Delete");
		cancel = new JButton("Cancel");
		mNumber = new JTextField(10);
		display = new JTextArea(15,15);
		mNumberLabel = new JLabel("Member Number (9 digits): ");
		
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
					number = mNumber.getText();
					numberInt = Integer.parseInt(number);
					if (number.length() > 9)
						isCorrect = false;
				}
				catch (RuntimeException e1) {
					isCorrect = false;
				}
				
				if (isCorrect == true) {
					selected = members.getMember(numberInt);
					//use selected member to display member info into text area
					String mName, mAddress, mCity, mState, dispStatus = null;
					int mZip, mNumb;
					boolean mStatus;
					
					mName = selected.getName();
					mNumb = selected.getNumber();
					mAddress = selected.getAddress();
					mCity = selected.getCity();
					mState = selected.getState();
					mZip = selected.getZipCode();
					mNumb = selected.getNumber();
					mStatus = selected.getStatus();
					
					if (mStatus == true)
						dispStatus = "Validated";
					else if (mStatus == false)
						dispStatus = "Suspended";
					
					display.setText("--Member Information--\nName: " + mName + "\nNumber: " + String.valueOf(mNumb) + "\nAddress: " + mAddress +
							"\nCity: " + mCity + "\nState: " + mState + "\nZipCode: " + String.valueOf(mZip) + "\nStatus: " + dispStatus);
			
					//reveal delete button
					choose.setVisible(false);
					verify.setForeground(Color.RED);
					verify.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "One or More of the inputs was incorrect, Please check inputs and try again.");
			}
		});
		
		verify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				members.delete(selected.getNumber());
				handle.updateMembers(members);
				delMem.setVisible(false);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delMem.setVisible(false);
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(mNumberLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(mNumber, gbc);
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
