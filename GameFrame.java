import java.awt.*;
import javax.swing.*;

public class GameFrame extends CustomFrame {

	JLabel scoreLabel, messageLabel;
	SnakePanelView snakePanelView;

	GameFrame() {
		super(
				430,
				620,
				100,
				70,
				"Snake game",
				null,
				new Color(0x030933),
				false,
				JFrame.EXIT_ON_CLOSE);

		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(boxLayout);

		addBackgroundImage("/res/images/a.jpeg");
		// Snake logo image
		ImageIcon snakeLogo = new ImageIcon(getClass().getResource("/res/images/snake.png"));
		snakeLogo = new ImageIcon(
				snakeLogo.getImage().getScaledInstance(34, 34, Image.SCALE_SMOOTH));

		// Heading label
		JLabel heading = new JLabel("Snake Game", snakeLogo, JLabel.CENTER);
		heading.setBackground(new Color(0x040929));
		heading.setMaximumSize(new Dimension(420, 75));

		heading.setOpaque(true);
		heading.setFont(SnakeFont.fontOne);
		heading.setBorder(BorderFactory.createLineBorder(new Color(0x8f8d96), 1));
		heading.setForeground(Color.WHITE);

		heading.setAlignmentX(Component.CENTER_ALIGNMENT);

		snakePanelView = new SnakePanelView(new SnakeModel(6, 4, 0, 32, 32), Color.GREEN, 420, 380);
		// snakePanelView.hideGrid();
		snakePanelView.showGrid();
		snakePanelView.snakeModel.putFoodAtRandom();
		snakePanelView.setOpaque(true);
		snakePanelView.setBackground(Color.red);

		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(SnakeFont.fontTwo);
		scoreLabel.setOpaque(false);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		messageLabel = new JLabel("You lost!");
		messageLabel.setFont(SnakeFont.fontTwo);
		messageLabel.setOpaque(false);
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		hideMessage();

		SnakeController sc = new SnakeController(this);

		getContentPane().add(Box.createRigidArea(new Dimension(1, 20)));
		getContentPane().add(heading);
		getContentPane().add(Box.createRigidArea(new Dimension(1, 30)));
		getContentPane().add(snakePanelView);
		getContentPane().add(Box.createRigidArea(new Dimension(1, 10)));
		getContentPane().add(scoreLabel);
		getContentPane().add(Box.createRigidArea(new Dimension(1, 5)));
		getContentPane().add(messageLabel);

		Thread gameLoopThread = new Thread(sc);
		gameLoopThread.setPriority(Thread.MIN_PRIORITY);

		gameLoopThread.start();

	}

	public void updateScore() {
		String score = "Score: " + snakePanelView.snakeModel.score;
		scoreLabel.setText(score);
	}

	public void showMessage() {
		messageLabel.setVisible(true);
	}

	public void showMessage(String msf) {
		messageLabel.setText(msf);
		messageLabel.setVisible(true);
	}

	public void hideMessage() {
		messageLabel.setVisible(false);
	}

}
