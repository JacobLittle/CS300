import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verifyMember extends JFrame {
	private static final long serialVersionUID = 1L;
	private verifyMember thisVer;
	JLabel numberLabel;
	JTextField number;
	JTextArea response;
	JButton cancel, finish;
	private static memberList members;
	
	public verifyMember(memberList mem) {
		super("Verify Member Status");
		thisVer = this;
		members = mem;
		this.setBounds(200, 150, 600, 300);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JPanel p = new JPanel(new GridBagLayout());
		this.add(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		number = new JTextField(10);
		numberLabel = new JLabel("Member Number:");
		finish = new JButton("Verify");
		cancel = new JButton("Done");
		response = new JTextArea(1, 10);
		
		finish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String mNumber;
				int isMember, mNumberInt = 0;
				boolean isCorrect;
				
				isCorrect = true;
				try {
					mNumber = number.getText();
					mNumberInt = Integer.parseInt(mNumber);
					if (mNumber.length() > 999999999)
						isCorrect = false;
				}
				catch (RuntimeException e1) {
					isCorrect = false;
				}
				
				if (isCorrect == true) {
					isMember = members.verifyMember(mNumberInt);
					if (isMember == 0)
						response.setText("Invalid: Member Suspended.");
					else if (isMember == 1)
						response.setText("Validated.");
					else if (isMember == 3)
						response.setText("Invalid: Member Doesn't Exist.");
				}
				else
					JOptionPane.showMessageDialog(null, "An Error Occurred. Please Re-enter member number.");
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thisVer.setVisible(false);
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(numberLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(number, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		p.add(finish, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		p.add(cancel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(response, gbc);
		
		
	}
}
