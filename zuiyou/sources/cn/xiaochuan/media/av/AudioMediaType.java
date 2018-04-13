package cn.xiaochuan.media.av;

public class AudioMediaType {
    public static final int AUDIO_TYPE_AAC = 1;
    public static final int AUDIO_TYPE_AMR_NB = 3;
    public static final int AUDIO_TYPE_AMR_WB = 4;
    public static final int AUDIO_TYPE_MP3 = 2;
    public static final int AUDIO_TYPE_PCM = 0;
    private long codec_context;
    private int mBitsPerSample;
    private int mChannels;
    private String mFormat;
    private int mSampleRate;
    private int mType;

    public AudioMediaType(AudioMediaType audioMediaType) {
        this.mSampleRate = 0;
        this.mChannels = 0;
        this.mBitsPerSample = 8;
        this.mType = 0;
        this.mFormat = "";
        this.codec_context = 0;
        this.mSampleRate = audioMediaType.mSampleRate;
        this.mChannels = audioMediaType.mChannels;
        this.mBitsPerSample = audioMediaType.mBitsPerSample;
        this.mType = audioMediaType.mType;
        this.mFormat = audioMediaType.mFormat;
        this.codec_context = audioMediaType.codec_context;
    }

    public AudioMediaType() {
        this.mSampleRate = 0;
        this.mChannels = 0;
        this.mBitsPerSample = 8;
        this.mType = 0;
        this.mFormat = "";
        this.codec_context = 0;
        this.mSampleRate = 0;
        this.mChannels = 0;
        this.mBitsPerSample = 0;
        this.mType = 0;
        this.mFormat = "";
        this.codec_context = 0;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public void setSampleRate(int i) {
        this.mSampleRate = i;
    }

    public int getChannels() {
        return this.mChannels;
    }

    public void setChannels(int i) {
        this.mChannels = i;
    }

    public int getBitsPerSample() {
        return this.mBitsPerSample;
    }

    public void setBitsPerSample(int i) {
        this.mBitsPerSample = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String getFormat() {
        return this.mFormat;
    }

    public void setForat(String str) {
        this.mFormat = str;
    }

    public void setContext(long j) {
        this.codec_context = j;
    }
}
