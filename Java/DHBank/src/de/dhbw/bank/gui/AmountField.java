package de.dhbw.bank.gui;

import javax.swing.*;
import javax.swing.border.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class AmountField extends JTextField {
	public AmountField() {
		super(8);
		setHorizontalAlignment(RIGHT);
		setBorder(new BevelBorder(BevelBorder.LOWERED));
	}
	
	public double getAmount() {
		String text = getText();
		if (text == null || text.trim().length() == 0) {
			throw new IllegalArgumentException("Missing input");
		}

		return Double.parseDouble(getText());
	}
}
