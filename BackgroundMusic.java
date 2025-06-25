
import java.io.IOException;

import javax.sound.sampled.*;

public class BackgroundMusic {

	public void playBackgroundMusic() {
		try {
			AudioInputStream auInput = AudioSystem.getAudioInputStream(
					getClass().getResource("/res/sound/vxcs.wav"));

			Clip clip = AudioSystem.getClip();
			clip.open(auInput);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			

			FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(3.0f);

			clip.start(); // parallel execution

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}