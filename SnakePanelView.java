import java.awt.*;
import javax.swing.*;


public class SnakePanelView extends JPanel {

	public SnakeModel snakeModel;
	public Color gridColor;
	boolean showGrid = false;

	public final int dWidth, dHeight;

	public SnakePanelView(SnakeModel sm, Color gridCol, int width, int height) {
		this.snakeModel = sm;
		this.gridColor = gridCol;

		dWidth = width / sm.boardWidth;
		dHeight = height / sm.boardHeight;

		int actualWidth = dWidth * sm.boardWidth, actualHeight = dHeight * sm.boardHeight;
		Dimension panelDimension = new Dimension(actualWidth, actualHeight);

		// try all size related function
		super.setMaximumSize(panelDimension);
		super.setPreferredSize(panelDimension);
		super.setSize(panelDimension);

	}

	public SnakePanelView() {
		this(new SnakeModel(), Color.BLACK, 420, 380);
	}

	public Point getCoordOfCell(int x, int y) {

		return new Point(dWidth * x, dHeight * y);

	}

	public void showGrid(Color col) {
		showGrid = true;
		setBorder(BorderFactory.createLineBorder(col));
	}

	public void showGrid() {
		showGrid(Color.GRAY);
	}

	public void hideGrid() {
		showGrid = false;
		setBorder(null);
	}

	// prohibits size manupulation in order to keep dWidth and dHeigth constant
	@Override
	public void setMaximumSize(Dimension maximumSize) {

	}

	@Override
	public void setMinimumSize(Dimension minimumSize) {

	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {

	}

	@Override
	public void setSize(Dimension d) {

	}

	@Override
	public void setSize(int width, int height) {

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (showGrid) {
			g.setColor(gridColor);
			int currWidthIndex = -1;

			while (currWidthIndex < getWidth()) {
				currWidthIndex += dWidth;
				g.drawLine(currWidthIndex, 0, currWidthIndex, getHeight());
			}

			int currHeightIndex = -1;

			while (currHeightIndex < getHeight()) {
				currHeightIndex += dHeight;
				g.drawLine(0, currHeightIndex, getWidth(), currHeightIndex);

			}
		}

		for (int x = 0; x < snakeModel.boardWidth; x++) {
			for (int y = 0; y < snakeModel.boardHeight; y++) {

				Point p = new Point(x, y);
				Point coord = getCoordOfCell(x, y);

				if (p.equals(snakeModel.getFoodCell())) {
					g.setColor(Color.ORANGE);

				} else if (snakeModel.emptyCells.contains(p)) {
					g.setColor(this.getBackground());
				} else {
					g.setColor(Color.BLACK);
				}

				g.fillRect(coord.x, coord.y, dWidth - 1, dHeight - 1);

			}

		}

	}

	
}