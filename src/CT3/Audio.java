package CT3;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import CT.Map.Screen;


public class Audio {
	
	int delay_move =0;
	public void Tank_move()
	{

//		if(this.delay_move == 0)
//		{	
//			try {
//				Screen.audio("Battle City di.wav");
//			} catch (UnsupportedAudioFileException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (LineUnavailableException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			this.delay_move = 50;
//			
//		}
//		else
//			this.delay_move --;

	}
	
	public void Tank_shoot()
	{
		
		try {
			Screen.audio("Battle City ban.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Tank_destroy()
	{
		
		try {
			Screen.audio("Battle City no.wav");
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
