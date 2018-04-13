package cn.v6.sixrooms.avsolution.player;

import cn.v6.sixrooms.avsolution.common.Event;
import cn.v6.sixrooms.avsolution.common.IAVSolution;

public interface IPlayerManager extends IAVSolution {
    public static final int PlayerBuffering = 9;
    public static final int PlayerBufferingEnd = 10;
    public static final int PlayerDisplaying = 13;
    public static final int PlayerErrorBusy = -30004;
    public static final int PlayerErrorEOF = -30005;
    public static final int PlayerErrorFailed = -1;
    public static final int PlayerErrorMemory = -20001;
    public static final int PlayerErrorMutex = -20002;
    public static final int PlayerErrorParameter = -30002;
    public static final int PlayerErrorPointer = -30001;
    public static final int PlayerErrorReport = 14;
    public static final int PlayerErrorThread = -20003;
    public static final int PlayerErrorTimeout = -30003;
    public static final int PlayerOpening = 1;
    public static final int PlayerPause = 5;
    public static final int PlayerPlaying = 2;
    public static final int PlayerPlayingEnd = 3;
    public static final int PlayerResume = 6;
    public static final int PlayerSeeking = 7;
    public static final int PlayerSeekingEnd = 8;
    public static final int PlayerStopped = 0;
    public static final int PlayerStopping = 4;
    public static final int PlayerUpdateDuration = 12;
    public static final int PlayerUpdateTime = 11;

    void reportError(Event event);

    void reportEvent(Event event);
}
