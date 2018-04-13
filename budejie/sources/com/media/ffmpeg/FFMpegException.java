package com.media.ffmpeg;

public class FFMpegException extends Exception {
    public static final int LEVEL_ERROR = -2;
    public static final int LEVEL_FATAL = -1;
    public static final int LEVEL_WARNING = -3;
    private int mLevel;

    public FFMpegException(int i, String str) {
        super(str);
        this.mLevel = i;
    }

    public int getLevel() {
        return this.mLevel;
    }
}
