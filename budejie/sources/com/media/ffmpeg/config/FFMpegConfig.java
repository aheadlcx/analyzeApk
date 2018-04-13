package com.media.ffmpeg.config;

public class FFMpegConfig {
    public static final String ASPECT = " -aspect ";
    public static final String AUDIOCHANNELS = " -ac ";
    public static final String AUDIORATE = " -ab ";
    public static final String BITRATE = " -b ";
    public static final String BITRATE_HIGH = "1024000";
    public static final String BITRATE_LOW = "128000";
    public static final String BITRATE_MEDIUM = "512000";
    public static final String CODEC_MPEG4 = "mpeg4";
    public static final String FILTER_V = " -filter:v ";
    public static final String FPS = " -r ";
    public static final String INPUT = " i ";
    public static final int[] RATIO_3_2 = new int[]{3, 2};
    public static final int[] RATIO_4_3 = new int[]{4, 3};
    public static final String VCODEC = " -vcodec ";
    public int audioChannels = 2;
    public int audioRate = 44000;
    public String bitrate = BITRATE_LOW;
    public String codec = CODEC_MPEG4;
    public int frameRate = 24;
    public int[] ratio = RATIO_4_3;
    public int[] resolution = new int[]{800, 600};
}
