

import java.io.IOException;

import javax.sound.sampled.*;

class BackgroundMusic {

	private AudioInputStream backgroundMusic;
	private Clip backgroundMusicClip;

	BackgroundMusic(String bgMusic) {
		try {
			backgroundMusic = AudioSystem.getAudioInputStream(getClass().getResource(bgMusic));

			backgroundMusicClip = AudioSystem.getClip();

			backgroundMusicClip.open(backgroundMusic);
			

			FloatControl fc = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(-4.3f);

		} catch (UnsupportedAudioFileException e) {
			System.err.println("The format of audio file is invalid");
			e.printStackTrace();
		} catch (IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playBGMusic() {
		if (backgroundMusicClip.isRunning())
			backgroundMusicClip.stop();
			
		backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);;

	}

}

class ActionMusic {

	private AudioInputStream pointScoreMusic, collisionMusic;
	private Clip pointScoreClip, collisionMusicClip;

	public ActionMusic(String pointMusic, String collMusic) {

		try {
			pointScoreMusic = AudioSystem.getAudioInputStream(getClass().getResource(pointMusic));
			collisionMusic = AudioSystem.getAudioInputStream(getClass().getResource(collMusic));

			pointScoreClip = AudioSystem.getClip();
			collisionMusicClip = AudioSystem.getClip();

			pointScoreClip.open(pointScoreMusic);
			collisionMusicClip.open(collisionMusic);

			FloatControl fc = (FloatControl) pointScoreClip.getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(3.0f);

			fc = (FloatControl) collisionMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(1.5f);

		} catch (UnsupportedAudioFileException e) {
			System.err.println("The format of audio file is invalid");
			e.printStackTrace();
		} catch (IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

	}

	public void playScoreMusic() {
		// buffering problems may occur if you take food fastly
		if (pointScoreClip.isRunning())
			pointScoreClip.stop();

		pointScoreClip.setFramePosition(0);
		pointScoreClip.start();
	}

	public void playCollisionMusic() {
		if (collisionMusicClip.isActive())
			collisionMusicClip.stop();
		collisionMusicClip.setFramePosition(0);
		collisionMusicClip.start();
	}
}