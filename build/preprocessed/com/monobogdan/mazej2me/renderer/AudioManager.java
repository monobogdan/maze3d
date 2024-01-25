/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me.renderer;

import java.io.IOException;
import javax.microedition.media.*;
import javax.microedition.media.control.VolumeControl;

/**
 *
 * @author mono
 */
public class AudioManager {
    public static class Stream
    {
        private Player player;
        private VolumeControl volumeCtrl;
        
        public Stream(String fileName)
        {
            // Assuming that Stream accepts only .wav and .mid files
            boolean isWave = fileName.indexOf(".wav") > 0;
            try {
                player = javax.microedition.media.Manager.createPlayer(getClass().getResourceAsStream("/sound/" + fileName), isWave ? "audio/x-wav" : "audio/midi");
                player.realize();
            } catch (IOException ex) {
                Renderer.log("Failed to open audio stream " + fileName);
                
                ex.printStackTrace();
            } catch (MediaException ex) {
                Renderer.log("MediaException occured on audio stream " + fileName);
                
                ex.printStackTrace();
            }
        }
        
        public boolean isPlaying()
        {
            return player.getState() == Player.STARTED;
        }
        
        public void play()
        {
            try {
                if(!isPlaying())
                    player.start();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
        }
        
        public void stop()
        {
            try
            {
                
                player.stop();
                player.setMediaTime(0);
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public int MasterVolume;
    
    public AudioManager()
    {
        
    }
}
