package framework;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * ClipLoader loads clips for the game. These clips are used as music 
 * and sound effects during gameplay.
 * 
 * @author Allison Johnson
 * @version March 23rd, 2016
 */
public class ClipLoader
{	
	//The clip to be loaded nulled out
	private Clip clip = null;

	
	public Clip loadClip(String path)
	{
		try{
			AudioInputStream audioIn = AudioSystem.getAudioInputStream
					(getClass().getResource(path));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return clip;
	}
	
	public void playClip(Clip clip)
	{ 
		stopClip(clip);
		clip.start();
	}
	
	public void stopClip(Clip clip)
	{
		if(clip.isRunning()){
			clip.stop();
		}
		clip.setFramePosition(0);
	}
}
