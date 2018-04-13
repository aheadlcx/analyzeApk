package cn.v6.sixrooms.view.interfaces;

public interface IRoomPlayerContral {
    boolean isPlayOver();

    boolean isPlayPause();

    void playOver();

    void playPause();

    void playPrepare();

    void playStart();

    void playing();
}
