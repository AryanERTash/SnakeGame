import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class HomeFrame extends CustomFrame implements ActionListener {

	private JButton playButton, exitButton;

	HomeFrame() {
		super(400, 600, 420, 50,
				"Snake Game - Home", null, Color.GREEN, false, JFrame.EXIT_ON_CLOSE);

		addBackgroundImage("/res/images/c.jpeg");

		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(0, getHeight() / 2, getWidth(), getHeight() / 2);
		actionPanel.setOpaque(false);

		BoxLayout boxLayout = new BoxLayout(actionPanel, BoxLayout.Y_AXIS);
		actionPanel.setLayout(boxLayout);

		playButton = new JButton("PLAY");
		exitButton = new JButton("EXIT");

		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		playButton.setFont(SnakeFont.fontOne);
		exitButton.setFont(SnakeFont.fontOne);

		playButton.setMaximumSize(new Dimension(300, 85));
		exitButton.setMaximumSize(new Dimension(300, 85));

		playButton.setBackground(Color.RED);
		exitButton.setBackground(Color.RED);

		playButton.setForeground(Color.BLACK);
		exitButton.setForeground(Color.BLACK);

		playButton.addActionListener(this);
		exitButton.addActionListener(this);

		actionPanel.add(playButton);
		actionPanel.add(Box.createRigidArea(new Dimension(1, 20)));
		actionPanel.add(exitButton);

		getContentPane().add(actionPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == exitButton) {
			int choice = JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Exit?",
					JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else if (e.getSource() == playButton) {

			this.hideFrame();

			new GameFrame().showFrame();

		}

	}

}