/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gui;

/*
 * Menu example snippet: create a bar and pull down menu (accelerators, mnemonics)
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import game.Game;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import playingfield.InvalidCoordinateException;
import saveGame.GamePersistor;
import saveGame.GameState;

public class BattleShipGui {

	static Display display = new Display();
	static Shell shell = new Shell(display);
	static List list1 = new List(shell, SWT.SINGLE | SWT.H_SCROLL);

	public static void main(String[] args) {

		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("&Spiel");
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);
		MenuItem item = new MenuItem(submenu, SWT.PUSH);
		MenuItem itemHighScore = new MenuItem(submenu, SWT.PUSH);
		MenuItem itemLoadGame = new MenuItem(submenu, SWT.PUSH);
		MenuItem itemAuthor = new MenuItem(submenu, SWT.PUSH);
		MenuItem itemQuit = new MenuItem(submenu, SWT.PUSH);

		item.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Neues Spiel starten");
				//Game spiel = new Game();
				Text ta = new Text(shell, SWT.BORDER | SWT.H_SCROLL);
				ta.setBounds(100, 100, 824, 614);
				ta.setText(System.out.toString());
			}
		});

		itemHighScore.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Highscores");
			}
		});

		itemLoadGame.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Load Game");

				final GamePersistor g = new GamePersistor();

				Label labelLoad1 = new Label(shell, SWT.BORDER);
				labelLoad1.setBounds(122, 100, 400, 20);
				labelLoad1.setBackground(new Color(display, 255, 255, 255));
				labelLoad1.setText("Bitte wählen Sie einen Spielstand aus:");

				g.readFromDisk();

				if (g.getListSize() > 0) {
					System.out.println(g.getOutput());

					for (int i = 0; i < g.getListSize(); i++) {
						String s = g.loadGame(i).toString();
						list1.setData(s);
					}

					list1.setBounds(122, 120, 400, 500);
					list1.redraw();
					list1.addMouseListener(new MouseAdapter() {
						public void mouseDown(MouseEvent e) {
							if (list1.getItemCount() > 0) {
								Game f = new Game();

								GameState gs = g.loadGame(
										list1.getSelectionIndex())
										.getGameState();
								GameState.getInstance().setCurrentPlayer(
										gs.getCurrentPlayer());
								GameState.getInstance().setNextPlayer(
										gs.getNextPlayer());
								GameState.getInstance().setGameMode(
										gs.getGameMode());
								GameState.getInstance().setNumRounds(
										gs.getNumRounds());

								try {
									f.playSavedGame();
								} catch (InvalidCoordinateException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					});
					list1.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event e) {
							String string = "";
							int[] selection = list1.getSelectionIndices();
							for (int i = 0; i < selection.length; i++)
								string += selection[i] + " ";
							System.out.println("Selection={" + string + "}");
						}
					});
					list1.addListener(SWT.DefaultSelection, new Listener() {
						public void handleEvent(Event e) {
							String string = "";
							int[] selection = list1.getSelectionIndices();
							for (int i = 0; i < selection.length; i++)
								string += selection[i] + " ";
							System.out.println("DefaultSelection={" + string
									+ "}");
						}
					});

				}
			}
		});

		itemAuthor.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Autor");
				Label labelAuthor = new Label(shell, SWT.BORDER);
				labelAuthor.setBounds(400, 300, 300, 200);
				labelAuthor.setBackground(new Color(display, 255, 255, 255));
				labelAuthor
						.setText("Ein Spiel von Erik Barz\nGUI Version 1.0"
								+ "\n\n\n\n\n\n\nGeschrieben im Auftrag der \nAllianz Deutschland AG");
			}
		});

		itemQuit.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("Programm beenden");

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					System.out.println("interrupted exception" + e1);
					e1.printStackTrace();
				}

				System.exit(0);
			}
		});

		item.setText("Neues Spiel starten");
		item.setAccelerator(SWT.MOD1 + 'N');

		itemHighScore.setText("Highscores anzeigen");
		itemHighScore.setAccelerator(SWT.MOD1 + 'H');

		itemLoadGame.setText("Spiel laden");
		itemLoadGame.setAccelerator(SWT.MOD1 + 'L');

		itemAuthor.setText("Autor");
		itemAuthor.setAccelerator(SWT.MOD1 + 'A');

		itemQuit.setText("Beenden");
		itemQuit.setAccelerator(SWT.MOD1 + 'B');

		shell.setSize(1024, 814);
		shell.setText("Schiffe Versenken");
		shell.setBackgroundImage(new Image(display,
				"C:\\Users\\Erik\\Studium\\2. Semester\\Java\\BattleShipGame\\src\\gui\\bg.jpg"));
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
