package de.dhbw.demo.layout;

import java.awt.*;

import javax.swing.*;

public class ViewGridLayout extends JFrame {
	
	public ViewGridLayout() {
		super("GridLayout");
		getContentPane().setLayout(new GridLayout(0, 2));
		add(new JButton("One"));
		add(new JButton("Two"));
		add(new JButton("Three"));
		add(new JButton("Four"));
		add(new JButton("Five"));
		add(new JButton("Six"));
		
		pack();
		setLocation(150,100);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ViewGridLayout();
	}
}