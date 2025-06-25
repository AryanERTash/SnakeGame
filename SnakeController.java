
import java.awt.event.*;

public class SnakeController implements KeyListener, Runnable {

	GameFrame gameFrame;
	boolean registerGameInput = false;

	SnakeController(GameFrame gameFrame_) {

		gameFrame = gameFrame_;

		gameFrame.addKeyListener(this);
	}

	@Override
	public void run() {

		while (true) {

			try {

				registerGameInput = true;

				int status = gameFrame.snakePanelView.snakeModel.move();

				if (status == SnakeConstants.ATE_FOOD) {
					gameFrame.updateScore();

				} else if (status == SnakeConstants.SELF_COLLISION || status == SnakeConstants.WALL_COLLISION) {

					gameFrame.showMessage("You Lost lil bro!");
					Thread.sleep(1000);
					gameFrame.snakePanelView.snakeModel.resetGame();
					gameFrame.snakePanelView.snakeModel.putFoodAtRandom();

					gameFrame.hideMessage();
					gameFrame.updateScore();

				}

				gameFrame.snakePanelView.repaint();

				Thread.sleep(100);

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (!registerGameInput)
			return;

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameFrame.snakePanelView.snakeModel.setSnakeDirection(SnakeConstants.RIGHT);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameFrame.snakePanelView.snakeModel.setSnakeDirection(SnakeConstants.LEFT);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			gameFrame.snakePanelView.snakeModel.setSnakeDirection(SnakeConstants.TOP);

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gameFrame.snakePanelView.snakeModel.setSnakeDirection(SnakeConstants.DOWN);

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothin

	}

	public static void main(String[] args) {

	}
}