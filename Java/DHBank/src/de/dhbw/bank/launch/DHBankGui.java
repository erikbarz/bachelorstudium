package de.dhbw.bank.launch;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import de.dhbw.bank.gui.*;
import de.dhbw.bank.model.*;

/**
 * This class ...
 * @author Rainer Buesch
 */
public class DHBankGui extends JFrame {

	public DHBankGui() throws Exception {
		super("BorderLayout");
		DHBank bank = new DHBank();
		AccountList accList = new AccountList(bank);
		accList.load();
		
		InfoTextArea infoArea = new InfoTextArea(bank, accList);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(infoArea, BorderLayout.NORTH);
		add(new JScrollPane(accList), BorderLayout.CENTER);

		add(new ButtonPanel(accList), BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private class ButtonPanel extends JPanel {
		ButtonPanel(AccountList accList) {
			setBorder(new BevelBorder(BevelBorder.RAISED));
			AmountField amountField = new AmountField();
			
			add(new JLabel("Amount"));
			add(amountField);
			add(Box.createHorizontalStrut(10));
			add(new DepositButton(accList, amountField));
			add(new WithdrawButton(accList, amountField));
			add(Box.createHorizontalStrut(20));
			add(new DeleteButton(accList));
			add(new DumpButton(accList));
		}
	}

	public static void main(String[] args) throws Exception {
		new DHBankGui();
	}

}
