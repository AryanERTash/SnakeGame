
// import items
import java.awt.*;

import java.util.LinkedList;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

class SnakeException extends RuntimeException {

	SnakeException() {
		super("The given snake size is invalid");
	}

	SnakeException(int size) {
		//
	}
}

public class SnakeModel {

	public LinkedList<Point> snakeBodyCells; // points of snake body, alternate can be to store vector of line of snake
												// body
	public Point foodCell;
	public Set<Point> emptyCells;
	public int snakeDirection = SnakeConstants.RIGHT;
	int score;

	int boardWidth, boardHeight, initX, initY, initSize;

	public SnakeModel() {
		this(12, 7, 3, 16, 8);
	}

	public SnakeModel(int boardWidth_, int boardHeight_) {
		this(2, boardWidth_ / 2, boardHeight_ / 2, boardWidth_, boardHeight_);

	}

	/**
	 * 
	 * @param initialSize  the initial length of the snake
	 * @param initX        Initital positionx of the snake
	 * @param initY        Initital y postion of the snake
	 * @param boardWidth_  the width of the board
	 * @param boardHeight_ The height of the board
	 */

	public SnakeModel(int initialSize, int initX, int initY, int boardWidth_, int boardHeight_) {

		this.boardHeight = boardHeight_;
		this.boardWidth = boardWidth_;

		this.initX = initX;
		this.initY = initY;
		this.initSize = initialSize;

		if (initialSize <= 1 || !isValidPoint(initX, initY)) {

			throw new RuntimeException("An exception has occured, kindly use correct values");

		}
		score = 0;

		snakeBodyCells = new LinkedList<>();
		emptyCells = new HashSet<>();
		foodCell = null;
		/*
		 * Initialize snake as a linked list, empty cell as a set and foodcell as null
		 */

		setInititalState();

	}

	public void resetGame() {


		setInititalState();

	}

	private void setInititalState() {
		foodCell = null;
		score = 0;
		emptyCells.clear();

		snakeBodyCells.clear();

		snakeDirection = SnakeConstants.RIGHT;

		for (int x = 0; x < boardWidth; x++) {
			for (int y = 0; y < boardHeight; y++) {

				emptyCells.add(new Point(x, y)); // add all cells as empty cells
			}
		}
		int currX = initX, currY = initY, remainingSnakeBody = initSize;
		boolean rightToLeft = true;

		/* Add snake to the linked list */

		while (remainingSnakeBody > 0 && currX >= 0 && currY >= 0) {

			Point currPoint = new Point(currX, currY);

			snakeBodyCells.addFirst(currPoint); // the new nodes are at first while the head is at last
			emptyCells.remove(currPoint);
			remainingSnakeBody--;

			if (rightToLeft) {
				currX--;
			} else {
				currX++;
			}

			if (currX < 0) {
				currX = 0;
				currY--;
				rightToLeft = false;
			} else if (currX >= boardWidth) {

				currX = boardWidth - 1;
				currY--;
				rightToLeft = true;

			}

		}
	}

	/**
	 * Returns the next position of head
	 * However this function dont care about the validity of a move
	 * 
	 * 
	 * @param head the current point for which you want to find the next
	 *             postiion(This passed object is not modified)
	 * @param dir  the direction of movement
	 * @return Point correponding to the movement of head
	 */
	private Point getNextPos(Point p, int dir) {
		// return next head position including invalid head
		Point nextHead = new Point(p);

		switch (dir) {

			case SnakeConstants.LEFT:
				nextHead.x--;
				break;
			case SnakeConstants.RIGHT:
				nextHead.x++;
				break;
			case SnakeConstants.TOP:
				nextHead.y--;
				break;
			case SnakeConstants.DOWN:
				nextHead.y++;
				break;

		}

		return nextHead;

	}

	private Point getNextTailPosAfterEating() {
		Point newTailPoint = new Point(snakeBodyCells.getFirst());

		Point firstTailPoint = newTailPoint; // alias
		Point secondTailPoint = new Point(snakeBodyCells.get(1));

		int dx = secondTailPoint.x - firstTailPoint.x,
				dy = secondTailPoint.y - firstTailPoint.y;

		if (dx == 1) {
			newTailPoint.x--;
		} else if (dx == -1) {
			newTailPoint.x++;
		} else if (dy == 1) {
			newTailPoint.y--;
		} else if (dy == -1) {
			newTailPoint.y++;
		}

		return newTailPoint;

	}

	/**
	 * Set the direction of snake, given the direction is not invalid
	 * 
	 * @param dir The direction of movement
	 */

	public void setSnakeDirection(int dir) {

		Point headPoint = getHead(), prevToHead = snakeBodyCells.get(snakeBodyCells.size() - 2);

		Point newHead = getNextPos(headPoint, dir);

		if (!prevToHead.equals(newHead))
			/*
			 * if position after moving does not eqates to the cell
			 * before head then it is valid to change direction
			 */
			snakeDirection = dir;
	}

	/**
	 * Get the point of head
	 * 
	 * @return Point object with head of the snake
	 */
	public Point getHead() {
		return snakeBodyCells.getLast();
	}

	/**
	 * Get the tail Point of the snake
	 * 
	 * @return Point corresponding to the tail of snake
	 */

	public Point getTail() {
		return snakeBodyCells.getFirst();
	}

	/**
	 * Get direction of snake
	 * 
	 * @return integer corresponding to direction of snake
	 */

	public int getSnakeDirection() {
		return snakeDirection;
	}

	/**
	 * Move the snake in next direction
	 * 
	 * @return integer corresponding ot the status of move(any collision leaves the
	 *         snake in invalid state (resetgame to start new game))
	 */

	public int move() {

		Point headPoint = getHead(), tailPoint = getTail();

		Point newHeadPoint = getNextPos(headPoint, snakeDirection);

		/* Dont move if collison with wall self, or invalid move going back */

		if (!isValidPoint(newHeadPoint.x, newHeadPoint.y))
			return SnakeConstants.WALL_COLLISION;

		else if (isSnakeBody(newHeadPoint.x, newHeadPoint.y))
			return SnakeConstants.SELF_COLLISION;

		// either continuing or path or ate a food
		// Works for single headed snake also
		snakeBodyCells.addLast(newHeadPoint);
		emptyCells.remove(newHeadPoint);

		emptyCells.add(tailPoint);
		snakeBodyCells.removeFirst();

		if (newHeadPoint.equals(foodCell)) {
			// remove food from that point
			foodCell = null;
			Point extraTail = getNextTailPosAfterEating();
			if (!isValidPoint(extraTail.x, extraTail.y)) {
				// collision due to increase in length of snake
				return SnakeConstants.WALL_COLLISION;
			}
			score++;
			// any wall collision does not gurantee consistent state
			emptyCells.remove(extraTail);
			snakeBodyCells.addFirst(extraTail);

			putFoodAtRandom();
			return SnakeConstants.ATE_FOOD;
		}

		return SnakeConstants.SNAKE_NORMAL;
	}

	public void putFoodAt(int x, int y) {
		if (isSnakeBody(x, y) || !isValidPoint(x, y))
			return;
		removeFood();
		foodCell = new Point(x, y);
		emptyCells.remove(foodCell);
	}

	public void removeFood() {
		if (foodCell != null)
			emptyCells.add(foodCell);
		foodCell = null;
	}

	public Point getFoodCell() {
		return foodCell;
	}

	public boolean isSnakeBody(int x, int y) {
		Point p = new Point(x, y);
		if (p.equals(foodCell) || emptyCells.contains(p) || !isValidPoint(x, y)) {
			return false;
		}

		return true;
	}

	/**
	 * Returns whether a point is whithin the bounds of the board
	 * 
	 * @param x x coord
	 * @param y y coord
	 * @return returns true if within bounds else
	 */
	public boolean isValidPoint(int x, int y) {

		return x >= 0 && y >= 0 && x < boardWidth && y < boardHeight;

	}

	public void putFoodAtRandom() {

		removeFood();
		// always move first
		// then remove food if food present
		// then put food at random
		// this ensures consistency of empty cells and snake body
		Object emptyCellArr[] = emptyCells.toArray();
		Random r = new Random();
		int randIndex = r.nextInt(emptyCells.size());

		Point newFoodLocation = (Point) emptyCellArr[randIndex];

		putFoodAt(newFoodLocation.x, newFoodLocation.y);

	}

}
