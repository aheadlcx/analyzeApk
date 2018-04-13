package com.media.ffmpeg;

import android.util.Log;
import com.media.ffmpeg.config.FFMpegConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FFMpeg {
    public static final String[] EXTENSIONS = new String[]{".mp4", ".flv", ".avi", ".wmv"};
    public static final String[] LIBS = new String[]{"ffmpegutils"};
    private static boolean sLoaded = false;
    private StringBuffer ffmpegConfig = new StringBuffer();
    private boolean mConverting;
    private FFMpegFile mInputFile;
    private IFFMpegListener mListener;
    private FFMpegFile mOutputFile;
    private Thread mThread;

    public interface IFFMpegListener {
        void onConversionCompleted();

        void onConversionProcessing(FFMpegReport fFMpegReport);

        void onConversionStarted();

        void onError(Exception exception);
    }

    private native void native_av_convert() throws RuntimeException;

    private native void native_av_init() throws RuntimeException;

    private native void native_av_parse_options(String str) throws RuntimeException;

    private native void native_av_register_all();

    private native int native_av_release(int i);

    private native void native_avcodec_register_all();

    private native void native_avdevice_register_all();

    private native void native_avfilter_register_all();

    public FFMpeg() throws FFMpegException {
        if (loadLibs()) {
            native_avcodec_register_all();
            native_avfilter_register_all();
            native_avdevice_register_all();
            native_av_register_all();
            this.mConverting = false;
            this.ffmpegConfig.append("ffmpeg");
            return;
        }
        throw new FFMpegException(-1, "Couldn't load native libs");
    }

    private static boolean loadLibs() {
        int i = 0;
        if (sLoaded) {
            return true;
        }
        int i2 = 0;
        while (i < LIBS.length) {
            try {
                System.loadLibrary(LIBS[i]);
            } catch (UnsatisfiedLinkError e) {
                Log.d("FFMpeg", "Couldn't load lib: " + LIBS[i] + " - " + e.getMessage());
                boolean z = true;
            }
            i++;
        }
        if (i2 == 0) {
            sLoaded = true;
        }
        return sLoaded;
    }

    public boolean isConverting() {
        return this.mConverting;
    }

    public void setListener(IFFMpegListener iFFMpegListener) {
        this.mListener = iFFMpegListener;
    }

    public FFMpegFile getOutputFile() {
        return this.mOutputFile;
    }

    public FFMpegFile getInputFile() {
        return this.mInputFile;
    }

    public void setBitrate(String str) {
        this.ffmpegConfig.append(FFMpegConfig.BITRATE + str);
    }

    public void setFrameAspectRatio(int i, int i2) {
        this.ffmpegConfig.append(FFMpegConfig.ASPECT + i + ":" + i2);
    }

    public void setVideoCodec(String str) {
        this.ffmpegConfig.append(FFMpegConfig.VCODEC + str);
    }

    public void setAudioRate(int i) {
        this.ffmpegConfig.append(FFMpegConfig.AUDIORATE + i);
    }

    public void setAudioChannels(int i) {
        this.ffmpegConfig.append(FFMpegConfig.AUDIOCHANNELS + i);
    }

    public void setFrameRate(int i) {
        this.ffmpegConfig.append(FFMpegConfig.FPS + i);
    }

    public void setFrameSize(int i, int i2) {
    }

    public void setInputFile(String str) throws IOException {
        if (new File(str).exists()) {
            this.ffmpegConfig.append(FFMpegConfig.INPUT + str);
            return;
        }
        throw new FileNotFoundException("File: " + str + " doesn't exist");
    }

    public void setOutputFile(String str) throws FileNotFoundException {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        this.ffmpegConfig.append(" " + str);
    }

    public String getFFmpegConfig() {
        return this.ffmpegConfig.toString();
    }

    public void init(String str) throws RuntimeException, IOException, FFMpegException {
        native_av_init();
        native_av_parse_options(str);
    }

    public void convert() throws RuntimeException {
        this.mConverting = true;
        if (this.mListener != null) {
            this.mListener.onConversionStarted();
        }
        native_av_convert();
        this.mConverting = false;
        if (this.mListener != null) {
            this.mListener.onConversionCompleted();
        }
    }

    public void convertAsync() throws RuntimeException {
        this.mThread = new Thread() {
            public void run() {
                try {
                    FFMpeg.this.convert();
                } catch (Exception e) {
                    if (FFMpeg.this.mListener != null) {
                        FFMpeg.this.mListener.onError(e);
                    }
                }
            }
        };
        this.mThread.start();
    }

    public void waitOnEnd() throws InterruptedException {
        if (this.mThread != null) {
            this.mThread.join();
        }
    }

    public void release() {
        native_av_release(0);
    }

    private void onReport(double d, int i, double d2, int i2) {
        if (this.mListener != null) {
            FFMpegReport fFMpegReport = new FFMpegReport();
            fFMpegReport.total_size = d;
            fFMpegReport.time = i;
            fFMpegReport.bitrate = d2;
            fFMpegReport.is_last_report = i2;
            this.mListener.onConversionProcessing(fFMpegReport);
        }
    }

    protected void finalize() throws Throwable {
        Log.d("FFMpeg", "finalizing ffmpeg main class");
        sLoaded = false;
    }
}
