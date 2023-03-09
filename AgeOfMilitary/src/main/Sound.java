package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;
    private URL soundURL [] = new URL[30];

    public Sound() 
    {
    	try {
        //filepaths for sound
        soundURL[0] = getClass().getResource("/sound/background_2.wav"); 
        soundURL[1] = getClass().getResource("/sound/game_start.wav");
        soundURL[2] = getClass().getResource("/sound/bullet.wav");
        soundURL[3] = getClass().getResource("/sound/cannon.wav");
        soundURL[4] = getClass().getResource("/sound/laser.wav");
        soundURL[5] = getClass().getResource("/sound/crate_destory.wav");
        soundURL[6] = getClass().getResource("/sound/hit_player.wav");
        soundURL[7] = getClass().getResource("/sound/heart.wav");
        soundURL[8] = getClass().getResource("/sound/powerup.wav");
        soundURL[9] = getClass().getResource("/sound/boost.wav");
        soundURL[10] = getClass().getResource("/sound/shield.wav");
        soundURL[11] = getClass().getResource("/sound/victory.wav");
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void setFile(int i)
    {
        try 
        {
            // open audio file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){
        }
    }

    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        clip.stop();
        clip.close();
    }
    
}
