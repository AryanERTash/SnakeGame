
import java.awt.event.*;

public class SnakeController implements KeyListener, Runnable {

	private GameFrame gameFrame;
	private boolean gamePaused = true;
	private ActionMusic actionMusic;

	SnakeController(GameFrame gameFrame_) {

		gameFrame = gameFrame_;

		gameFrame.addKeyListener(this);

		actionMusic = new ActionMusic("/res/sound/food.wav", "/res/sound/loss.wav");
	}

	@Override
	public void run() {

		while (true) {

			try {

				if (gamePaused) {

					Thread.sleep(10);
					Thread.yield(); // stop loading cpu
					continue;
				}

				int status = gameFrame.snakePanelView.snakeModel.move();

				if (status == SnakeConstants.ATE_FOOD) {
					actionMusic.playScoreMusic();
					gameFrame.updateScore();

				} else if (status == SnakeConstants.SELF_COLLISION || status == SnakeConstants.WALL_COLLISION) {

					actionMusic.playCollisionMusic();
					gameFrame.showMessage("You Lost lil bro!");
					
					
					Thread.sleep(1000);
					
					gameFrame.snakePanelView.snakeModel.resetGame();
					gameFrame.snakePanelView.snakeModel.putFoodAtRandom();
					gameFrame.hideMessage();
					gameFrame.updateScore();


					gamePaused = true;

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

		if (gamePaused) {
			gamePaused = false;
			return;
		}

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

}