import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music {
    Clip clip;
    AudioInputStream sound;

    public void setFile(File soundFileName) {
        try {
            String fileName = soundFileName.getAbsolutePath();
            File file = new File(fileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
        }
    }
    public void play() {
        clip.start();
    }
    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }

    public void pause() {
        
            clip.stop();
    }

    public void resume() {
            clip.start();
        }
    


    

    public float getVolume() {
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
    return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    
     


    
    
}

    

