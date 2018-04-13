package cn.xiaochuan.media.av;

public class VideoMediaType {
    public static final int VIDEO_TYPE_BGRA = 5;
    public static final int VIDEO_TYPE_H264 = 101;
    public static final int VIDEO_TYPE_MPEG4 = 100;
    public static final int VIDEO_TYPE_NV21 = 4;
    public static final int VIDEO_TYPE_RGB565 = 1;
    public static final int VIDEO_TYPE_RGB888 = 0;
    public static final int VIDEO_TYPE_YUV420P = 2;
    public static final int VIDEO_TYPE_YV12 = 3;
    private long codec_context;
    private String mFormat;
    private float mFrameRate;
    private int mHeight;
    private int mType;
    private int mWidth;

    public VideoMediaType() {
        this.mWidth = 0;
        this.mHeight = 0;
        this.mFrameRate = 10.0f;
        this.mType = 0;
        this.mFormat = "";
        this.codec_context = 0;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mFrameRate = 0.0f;
        this.mType = 0;
        this.mFormat = "";
        this.codec_context = 0;
    }

    public VideoMediaType(VideoMediaType videoMediaType) {
        this.mWidth = 0;
        this.mHeight = 0;
        this.mFrameRate = 10.0f;
        this.mType = 0;
        this.mFormat = "";
        this.codec_context = 0;
        this.mWidth = videoMediaType.mWidth;
        this.mHeight = videoMediaType.mHeight;
        this.mFrameRate = videoMediaType.mFrameRate;
        this.mType = videoMediaType.mType;
        this.mFormat = videoMediaType.mFormat;
        this.codec_context = videoMediaType.codec_context;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public float getFrameRate() {
        return this.mFrameRate;
    }

    public void setFrameRate(float f) {
        this.mFrameRate = f;
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

    public void setFormat(String str) {
        this.mFormat = str;
    }
}
