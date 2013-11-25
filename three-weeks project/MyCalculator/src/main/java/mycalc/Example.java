package mycalc;

import java.awt.*;
import javax.swing.*;

public class Example {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CalcFrame frame = new CalcFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
