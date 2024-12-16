package at.htl.adventskalenderabercool;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AudioController {
    MediaPlayer mp;
    AtomicInteger currentSong = new AtomicInteger(0);
    AtomicBoolean isPlaying = new AtomicBoolean(false);

    double currentVolume = 0.5;
    List<URL> songs = new ArrayList<>();

    public AudioController(){
        addSong("/at/htl/adventskalenderabercool/sandaru-sathsara.mp3");
        addSong("/at/htl/adventskalenderabercool/last-christmas.mp3");

        if (!songs.isEmpty()) {
            mp = new MediaPlayer(new Media(songs.get(currentSong.get()).toString()));
        }
    }


    private void addSong(String filePath) {
        URL url = getClass().getResource(filePath);
        if (url != null) {
            songs.add(url);
        } else {
            System.err.println("File not found: " + filePath);
        }
    }

    public void play(){
        new Thread(() -> {
            while (true) {
                System.out.println("Current song: " + songs.get(currentSong.get()).getFile());
                if (!isPlaying.get()) {
                    isPlaying.set(true);
                    // Stopping the last MediaPlayer, otherwise songs won't switch
                    if (mp != null) {
                        mp.stop();
                        mp.dispose();
                    }
                    mp = new MediaPlayer(new Media(songs.get(currentSong.get()).toString()));
                    mp.setVolume(currentVolume);
                    mp.setOnEndOfMedia(() -> {
                        System.out.println("Song: " + songs.get(currentSong.get()).getFile() + " beendet, nächster Song: " + songs.get((currentSong.get() + 1) % songs.size()).getFile());
                        currentSong.set((currentSong.get() + 1) % songs.size());
                        isPlaying.set(false);
                    });
                    mp.play();
                }
            }
        }).start();
    }

    public void pause(){
        mp.pause();
    }

    public void resume(){
        if (mp.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            System.out.println("Audio is already playing");
            return;
        }
        mp.play();
    }

    public void reset(){
        mp.stop();
        mp.dispose();
        mp = new MediaPlayer(new Media(songs.get(currentSong.get()).toString()));
        currentSong.set(0);
        isPlaying.set(false);
        play();
    }

    public void addSong(URL url){
        songs.add(0, url);
        reset();
    }

    public void setVolume(double volume){
        mp.setVolume(volume / 100);
        currentVolume = volume / 100;
    }

    public double getVolume(){
        return mp.getVolume() * 100;
    }

    public void setMute(boolean isMute){
        mp.setMute(isMute);
    }

    public boolean isMute(){
        return mp.isMute();
    }
}
