package mycalc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CalcFrame extends JFrame{
		
	public CalcFrame() {
		setTitle("Magic machine");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);
		
		JPanel panel = new JPanel();
		JPanel errors = new JPanel();
		
		final JTextField problem = new JTextField(30);
		final JTextField result = new JTextField(10);
		
		final JLabel error = new JLabel ("");
		JButton ok = new JButton("=");
		ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				result.setText(MyCalculator.eval(problem.getText()).toString()); 
				error.setText(MyCalculator.getError());
				if (!error.getText().equals("")) {
					problem.requestFocus();
					problem.setCaretPosition(MyCalculator.getCursor());
				}
			}
		});
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/*panel.setLayout(new GridLayout(1, 3, 5, 5));*/
		errors.add(error);
		
		panel.add(problem);
		panel.add(ok);
		panel.add(result);
		
		add(panel, BorderLayout.NORTH);
		add(errors, BorderLayout.SOUTH);
	}
	 
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 110;
		
}
