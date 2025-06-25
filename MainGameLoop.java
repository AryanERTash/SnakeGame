public class MainGameLoop {

	public static void main(String[] args) {
		HomeFrame hf = new HomeFrame();

		hf.showFrame();

		BackgroundMusic bm = new BackgroundMusic();
		bm.playBackgroundMusic();
	}
}