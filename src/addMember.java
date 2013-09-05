import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addMember extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel name, address, zipCode, state, city, status;
	private JTextField na, ad, zip, st, cit, stat;
	private JButton cancel, enter;
	private memberList members;
	private addMember thisMem;
		
	public addMember(memberList mem, final MainMenu handle) {
		super("Add Member");
		members = mem;
		thisMem = this;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		this.setBounds(200, 150, 500, 500);
		
		name = new JLabel("Name (max of 25 characters):");
		address = new JLabel("Address (max of 25 characters):");
		zipCode = new JLabel("Zipcode (5 digits):");
		state = new JLabel("State (2 characters):");
		city = new JLabel("City (max of 14 characters):");
		status = new JLabel("Member Status (y/n):");
		
		na = new JTextField(10);
		ad = new JTextField(10);
		zip = new JTextField(10);
		st = new JTextField(10);
		cit = new JTextField(10);
		stat = new JTextField(10);
		
		cancel = new JButton("Cancel");
		enter = new JButton("Finish");
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thisMem.setVisible(false);
			}
		});
		
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String mName, mAddress, mCity, mState, tempStatus;
				int mZip;
				boolean mStatus, isCorrect;
				
				isCorrect = true;
				try {
				mName = na.getText();
				mAddress = ad.getText();
				mCity = cit.getText();
				mState = st.getText();
				mZip = Integer.parseInt(zip.getText());
				tempStatus = stat.getText();
				
				if(mName.length() > 25 || mAddress.length() > 25 || mCity.length() > 14 || mState.length() > 2 || tempStatus.length() > 1 || mZip > 99999 || mZip < 10000)
					isCorrect = false;
				if (tempStatus == "y" || tempStatus == "Y")
					mStatus = true;
				else if(tempStatus == "n" || tempStatus == "N")
					mStatus = false;
				else 
					mStatus = true;
				
				if(isCorrect == true) {
					members.add(mName, mAddress, mCity, mState, mZip, 0, mStatus);
					handle.updateMembers(members);
					thisMem.setVisible(false);
				}
				}
				catch(RuntimeException e1) {
					isCorrect = false;
				}
				if (isCorrect == false) {
					JOptionPane.showMessageDialog(null, "One or More of the inputs was incorrect, Please check inputs and try again.");
				}	
			}
		});
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(name, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(na, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(address, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(ad, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		p.add(zipCode, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		p.add(zip, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		p.add(city, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		p.add(cit, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		p.add(state, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		p.add(st, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		p.add(status, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		p.add(stat, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		p.add(cancel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		p.add(enter, gbc);
		
	
	}
	
	public memberList getUpdated() {
		return members;
	}
}
