package de.dhbw.demo.layout;

import java.awt.*;

import javax.swing.*;

public class ViewBorderLayout extends JFrame {
	
	public ViewBorderLayout() {
		super("BorderLayout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		add(new JButton("North"),  BorderLayout.NORTH);
		add(new JButton("West"),   BorderLayout.WEST);
		add(new JButton("East"),   BorderLayout.EAST);
		add(new JButton("Center"), BorderLayout.CENTER);
//		add(new JButton("South"),  BorderLayout.SOUTH);
		
		add(new ButtonPanel(),  BorderLayout.SOUTH);
				
		pack();		
		setLocationRelativeTo(null);	
		setVisible(true);
	}
	
	private class ButtonPanel extends JPanel {
		ButtonPanel() {
			setLayout(new FlowLayout(FlowLayout.CENTER));
			add(new JButton("Clear"));
			add(new JButton("Exit"));
		}
	}
	
	public static void main(String[] args) {
		new ViewBorderLayout();
	}
}


