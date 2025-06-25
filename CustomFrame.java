import java.awt.*;
import javax.swing.*;

public class CustomFrame extends JFrame {

	ImageIcon backgroundImage;

	public CustomFrame(int width, int height, int locX, int locY, String title,
			LayoutManager lm, Color bgColor, boolean resize, int defaultCloseOp) {

		setBounds(locX, locY, width, height);
		JLabel windowJLabel = new JLabel() {

			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);

				if (backgroundImage != null) {
					g.drawImage(backgroundImage.getImage(), 0, 0, width, height, this);
				}
			}

		};
		windowJLabel.setOpaque(true);
		windowJLabel.setBounds(0, 0, getWidth(), getHeight());
		setContentPane(windowJLabel);

		setResizable(resize);
		setTitle(title);
		setDefaultCloseOperation(defaultCloseOp);

		getContentPane().setBackground(bgColor);
		getContentPane().setLayout(lm);

	}

	public CustomFrame(int width, int height) {
		this(width, height, width, height, "A custom frame", null, Color.WHITE, true, JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setSize(d.width / 2, d.height / 2);
	}

	public CustomFrame() {
		this(500, 500);

	}

	public void showFrame() {
		setVisible(true);
	}

	public void hideFrame() {
		setVisible(false);
	}

	public void addFavicon(String imgAddr) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imgAddr));
		ImageIcon resizedImage = new ImageIcon(
				icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));

		setIconImage(resizedImage.getImage());

	}

	public void addBackgroundImage(String imgAddr) {
		ImageIcon bgIcon = new ImageIcon(getClass().getResource(imgAddr));
		backgroundImage = bgIcon;

	}

}