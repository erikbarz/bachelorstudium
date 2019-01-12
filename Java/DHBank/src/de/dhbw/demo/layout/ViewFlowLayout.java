package de.dhbw.demo.layout;

import java.awt.*;

import javax.swing.*;

public class ViewFlowLayout extends JFrame {
	
	public ViewFlowLayout() {
		super("FlowLayout");
		getContentPane().setLayout(new FlowLayout());
		add(new JButton("One"));
		add(new JButton("Two"));
		add(new JButton("Three"));
		add(new Button("Four"));
		add(new JButton("Five"));
		add(new JButton("Six"));
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ViewFlowLayout();
	}
}