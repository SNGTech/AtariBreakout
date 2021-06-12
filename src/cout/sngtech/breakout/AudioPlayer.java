/*
package cout.sngtech.breakout;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    enum Audio {
        BRICK_BREAK(0), BALL_BOUNCE(1), LOSE_LIFE(2), DEFLECTOR_HIT(3);

        public final int id;

        Audio(int id) {
            this.id = id;
        }
    }

    private static final List<Clip> audioList = new ArrayList<>();

    public static void initAudio() {
        //MUST FOLLOW IN ORDER OF AUDIO ENUMERATION
        audioList.add(getAudio(Audio.BRICK_BREAK));
        audioList.add(getAudio(Audio.BALL_BOUNCE));
        audioList.add(getAudio(Audio.LOSE_LIFE));
        audioList.add(getAudio(Audio.DEFLECTOR_HIT));
    }

    public static void play(Audio audio, double gain) {
        Clip clip = audioList.get(audio.id);
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        control.setValue(dB);
        clip.setFramePosition(0);
        clip.start();
    }

    private static Clip getAudio(Audio audio) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("assets/sound/" + audio.name().toLowerCase() + ".wav")));
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
}
*/
