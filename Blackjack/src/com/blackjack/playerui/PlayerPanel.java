package com.blackjack.playerui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.blackjack.cards.Hand;
import com.blackjack.config.IConfigLoader;
import com.blackjack.gamecontrollers.Drill;
import com.blackjack.player.IPlayerPanel;
import com.blackjack.player.Play;
import com.blackjack.player.PlayerView;

public class PlayerPanel extends JPanel implements ActionListener, IPlayerPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	PlayerView playerView;
	JLabel dealerCardImage, playerCard1Image, playerCard2Image;
	IConfigLoader configLoader;

	private JButton[] buttons = new JButton[5];

	private PlayerPanel(IConfigLoader configLoader) {
		// show the panel
		initPlayerPanel();
		// attach a view to the panel
		setPlayerView(PlayerView.createPlayerView(this, configLoader));
		// start the drill
		playerView.startPlay();
	}

	private void initPlayerPanel() {
		// Set up the content pane.
		addComponentsToPane(this);
		// don't let them do anything yet
		//showJokers();
		//disableAllButtons();
	}

	private void setPlayerView(PlayerView playerView) {
		this.playerView = playerView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Figure out the action from the button that got pressed
		String command = e.getActionCommand();

		Play playCommand = Play.action(command);
		Drill drillCommand = Drill.drill(command);

		if (!playCommand.toString().toUpperCase().equals("NONE"))
			playerView.buttonPressed(playCommand);
		else {
			if (!drillCommand.toString().toUpperCase().equals("NONE"))
				playerView.drillChange(drillCommand);
		}
	}

	/* (non-Javadoc)
	 * @see com.blackjack.playerui.IPlayerPanel#showCards(com.blackjack.cards.Hand, com.blackjack.cards.Hand)
	 */
	@Override
	public void showCards(Hand playerHand, Hand dealerHand) {
		ImageIcon cardImage;

		cardImage = CardImage.getCardIcon(dealerHand.getHand().get(0));
		dealerCardImage.setIcon(cardImage);

		cardImage = CardImage.getCardIcon(playerHand.getHand().get(0));
		playerCard1Image.setIcon(cardImage);

		cardImage = CardImage.getCardIcon(playerHand.getHand().get(1));
		playerCard2Image.setIcon(cardImage);
	}

	/* (non-Javadoc)
	 * @see com.blackjack.playerui.IPlayerPanel#clearCards()
	 */
	@Override
	public void clearCards() {
		// Set cards back to jokers and clear hands
		showJokers();
		disableAllButtons();
	}

	/* (non-Javadoc)
	 * @see com.blackjack.playerui.IPlayerPanel#disableButton(com.blackjack.player.Play)
	 */
	@Override
	public void disableButton(Play disableAction) {
		// disable button having selected Action
		buttons[disableAction.index()].setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see com.blackjack.playerui.IPlayerPanel#enableButton(com.blackjack.player.Play)
	 */
	@Override
	public void enableButton(Play enableAction) {
		buttons[enableAction.index()].setEnabled(true);
	}

	/* (non-Javadoc)
	 * @see com.blackjack.playerui.IPlayerPanel#disableAllButtons()
	 */
	@Override
	public void disableAllButtons() {
		for (JButton b : buttons) {
			b.setEnabled(false);
		}
	}

	/* (non-Javadoc)
	 * @see com.blackjack.playerui.IPlayerPanel#enableAllButtons()
	 */
	@Override
	public void enableAllButtons() {
		for (JButton b : buttons) {
			b.setEnabled(true);
		}
	}

	private static GridBagConstraints defaultConstraints() {
		final boolean shouldFill = true;
		final boolean shouldWeightX = true;

		GridBagConstraints constraints = new GridBagConstraints();
		if (shouldFill) {
			// natural height, maximum width
			constraints.fill = GridBagConstraints.HORIZONTAL;
		}
		if (shouldWeightX) {
			constraints.weightx = 0.5;
		}
		return constraints;
	}

	public void showJokers() {
		dealerCardImage.setIcon(CardImage.getJokerIcon());
		playerCard1Image.setIcon(CardImage.getJokerIcon());
		playerCard2Image.setIcon(CardImage.getJokerIcon());
	}

	private void addComponentsToPane(Container pane) {
		JButton button;
		JLabel label;
		GridBagConstraints constraints;

		pane.setLayout(new GridBagLayout());

		label = new JLabel("Dealer:");
		constraints = defaultConstraints();
		constraints.insets = new Insets(10, 10, 0, 0); // top padding
		constraints.gridx = 0;
		constraints.gridy = 0;
		pane.add(label, constraints);

		dealerCardImage = new JLabel();
		constraints = defaultConstraints();
		constraints.insets = new Insets(10, 0, 0, 0); // top padding
		constraints.gridx = 1;
		constraints.gridy = 0;
		pane.add(dealerCardImage, constraints);

		label = new JLabel("Player:");
		constraints = defaultConstraints();
		constraints.insets = new Insets(10, 10, 0, 0); // top padding
		constraints.gridx = 0;
		constraints.gridy = 1;
		pane.add(label, constraints);

		playerCard1Image = new JLabel();
		constraints = defaultConstraints();
		constraints.insets = new Insets(10, 0, 0, 0); // top padding
		constraints.gridx = 1;
		constraints.gridy = 1;
		pane.add(playerCard1Image, constraints);

		playerCard2Image = new JLabel();
		constraints = defaultConstraints();
		constraints.insets = new Insets(10, 0, 0, 0); // top padding
		constraints.gridx = 2;
		constraints.gridy = 1;
		pane.add(playerCard2Image, constraints);

		// new panel for the radiobuttons
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridBagLayout());
		constraints = defaultConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(40, 10, 0, 0); // top padding
		pane.add(radioPanel, constraints);

		// Create the radio buttons.
		constraints = defaultConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;

		JRadioButton radPairsOnly = new JRadioButton("Pairs Only");
		radPairsOnly.setMnemonic(KeyEvent.VK_P);
		radPairsOnly.setActionCommand(Drill.PAIRS.toString());
		radPairsOnly.setSelected(true);
		radioPanel.add(radPairsOnly, constraints);

		JRadioButton radSoftOnly = new JRadioButton("Soft Hands Only (one Ace)");
		radSoftOnly.setMnemonic(KeyEvent.VK_S);
		radSoftOnly.setActionCommand(Drill.SOFT.toString());
		constraints.gridy = 1;
		radioPanel.add(radSoftOnly, constraints);

		JRadioButton radHardOnly = new JRadioButton("Hard Hands Only (no Aces)");
		radHardOnly.setMnemonic(KeyEvent.VK_H);
		radHardOnly.setActionCommand(Drill.HARD.toString());
		constraints.gridy = 2;
		radioPanel.add(radHardOnly, constraints);

		JRadioButton radAllTypes = new JRadioButton("All Hand Types");
		radAllTypes.setMnemonic(KeyEvent.VK_A);
		radAllTypes.setActionCommand(Drill.ALL.toString());
		constraints.gridy = 3;
		radioPanel.add(radAllTypes, constraints);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(radPairsOnly);
		group.add(radSoftOnly);
		group.add(radHardOnly);
		group.add(radAllTypes);

		radPairsOnly.addActionListener(this);
		radSoftOnly.addActionListener(this);
		radHardOnly.addActionListener(this);
		radAllTypes.addActionListener(this);

		// Now the action buttons
		JPanel buttonPanel = new JPanel();
		constraints = defaultConstraints();
		constraints.gridy = 6;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(10, 0, 0, 0); // top padding
		pane.add(buttonPanel, constraints);

		button = new JButton(Play.DEAL.toString());
		button.setPreferredSize(new Dimension(200, 30));
		buttons[Play.DEAL.index()] = button;
		button.addActionListener(this);
		constraints = defaultConstraints();
		constraints.gridx = 0; // aligned with button 2
		constraints.gridy = 0; // third row
		buttonPanel.add(button, constraints);

		JPanel buttonPanel2 = new JPanel();
		constraints = defaultConstraints();
		constraints.insets = new Insets(20, 0, 0, 0); // top padding
		constraints.gridy = 5;
		constraints.gridwidth = 4;
		pane.add(buttonPanel2, constraints);

		button = new JButton("Hit");
		button = new JButton(Play.HIT.toString());
		buttons[Play.HIT.index()] = button;
		button.addActionListener(this);
		constraints = defaultConstraints();
		button.setPreferredSize(null);
		constraints.gridx = 0;
		constraints.gridy = 1;
		buttonPanel2.add(button, constraints);

		button = new JButton("Stand");
		button = new JButton(Play.STAND.toString());
		buttons[Play.STAND.index()] = button;
		button.addActionListener(this);
		constraints = defaultConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		buttonPanel2.add(button, constraints);

		button = new JButton("Split");
		button = new JButton(Play.SPLIT.toString());
		buttons[Play.SPLIT.index()] = button;
		button.addActionListener(this);
		constraints = defaultConstraints();
		constraints.gridx = 2;
		constraints.gridy = 5;
		buttonPanel2.add(button, constraints);

		button = new JButton("Double");
		button = new JButton(Play.DOUBLE.toString());
		buttons[Play.DOUBLE.index()] = button;
		button.addActionListener(this);
		constraints = defaultConstraints();
		constraints.gridx = 3;
		constraints.gridy = 5;
		buttonPanel2.add(button, constraints);
	}

	private static void createAndShowGUI(IConfigLoader configLoader) {
		// Create and set up the window.
		JFrame frame = new JFrame("Tutor21");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 200);

		// Create and set up the content pane.
		JComponent newContentPane = new PlayerPanel(configLoader);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 * @param configLoader 
	 */
	public static void createPlayerPanel(final IConfigLoader configLoader) {
		// TODO Auto-generated method stub
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// PlayerPanel playerPanel = new PlayerPanel();
				// playerPanel.setVisible(true);
				createAndShowGUI(configLoader);
			}
		});
	}

	/*
	public static IPlayerPanel testCreatePlayerPanel() {
		// TODO Auto-generated method stub

		//final PlayerPanel playerPanel = new PlayerPanel();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// PlayerPanel playerPanel = new PlayerPanel();
				playerPanel.setVisible(true);
			}
		});

		return playerPanel;
	}
	*/	
}