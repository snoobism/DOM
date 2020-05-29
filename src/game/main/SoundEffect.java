package game.main;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
   
/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */

public enum SoundEffect {
   MUSIC("sounds/music.mid"),  
   HIT("sounds/div_hit.wav"),        
   DEAD("sounds/div_dead.wav"),
   BULLET("sounds/shoot.wav"),
   PAIN("sounds/pain.wav"),
   BODY("sounds/body.wav"),
   WEAPON("sounds/weapon.wav"),
   SHOTGUN("sounds/shotgun.wav"),
   FLY_HIT("sounds/flyPain.wav"),
   FLY_DEAD("sounds/flyDeath.wav");
   
   // Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }
   
   public static Volume volume = Volume.LOW;
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;
   
   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String soundFileName) {
      try {
    	 File audioFile = new File(soundFileName);
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
	   

      if (volume != Volume.MUTE) {
    	 System.out.println(volume);
    	 if (volume == Volume.LOW) {
    		 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    		 gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.
    	 }
    	 if (volume == Volume.MEDIUM) {
	   		 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   		 gainControl.setValue(-10.0f);
    	 }
    	 if (volume == Volume.HIGH) {
    		 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    		 gainControl.setValue(-0.0f);
      	 }
         if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start();     // Start playing
      }
   }
   
   public void stop() {	
	   clip.setFramePosition(0);
	   clip.stop();
   }
   
   // Optional static method to pre-load all the sound files.
   static void init() {
      values(); // calls the constructor for all the elements
   }
}