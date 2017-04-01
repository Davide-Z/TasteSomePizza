package Audio;

import javax.sound.sampled.*;
import javax.sound.sampled.DataLine.Info;
import java.io.File;
import java.io.IOException;

public class Son implements Runnable {
    static String path = "";
    static boolean isSet = false;

    public Son(String p) {
        path = p;
        isSet = true;
    }

    public void run() {
        if(isSet) {
            File fichier = new File(path);

            AudioInputStream AIS;
            try {
                AIS = AudioSystem.getAudioInputStream(fichier);
            } catch (UnsupportedAudioFileException var10) {
                var10.printStackTrace();
                return;
            } catch (IOException var11) {
                var11.printStackTrace();
                return;
            }

            AudioFormat audioFormat = AIS.getFormat();
            Info info = new Info(SourceDataLine.class, audioFormat);

            SourceDataLine line;
            try {
                line = (SourceDataLine)AudioSystem.getLine(info);
            } catch (LineUnavailableException var9) {
                var9.printStackTrace();
                return;
            }

            try {
                line = (SourceDataLine)AudioSystem.getLine(info);
                line.open(audioFormat);
            } catch (LineUnavailableException var8) {
                var8.printStackTrace();
                return;
            }

            line.start();

            try {
                byte[] e = new byte[1000];
                boolean bytesRead = false;

                int bytesRead1;
                while((bytesRead1 = AIS.read(e, 0, e.length)) != -1) {
                    line.write(e, 0, bytesRead1);
                }
            } catch (IOException var12) {
                var12.printStackTrace();
                return;
            }
        }

    }

    public static void main(String[] args) {
        Son son = new Son("C:/Users/ASUS N56VZ/git/TasteSomePizza/src/Audio/Intro.wav");
        son.run();
    }
}
