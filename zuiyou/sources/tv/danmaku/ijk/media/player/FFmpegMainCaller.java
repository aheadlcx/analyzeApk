package tv.danmaku.ijk.media.player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.io.b;

public class FFmpegMainCaller {
    private FFMpegCallback ffMpegCallback;

    public interface FFMpegCallback {
        void onFrame(int i);
    }

    public static native void aacToWav(String str, String str2, float f);

    public static native void callMain(String str);

    public static native void changeVolume(String str, String str2, float f);

    public static native void concatMedia(String[] strArr, String str, int i);

    public static native void concatMediaMuxer(String str, String str2);

    public static native void mergeAVSource(String str, String str2, String str3, float f, boolean z);

    public static native void mergeAudio(String str, String str2, String str3);

    public static native void wavToAac(String str, String str2);

    public native void overlayPng(String str, int i, int i2, String str2, String str3);

    static {
        System.loadLibrary("ijkffmpeg");
        System.loadLibrary("ffmain");
    }

    public static void concatMedia(ArrayList<String> arrayList, String str, String str2) {
        File file = new File(str2);
        if (file != null && file.exists()) {
            file.delete();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                b.a(file, "file " + ((String) it.next()) + "\n", "UTF-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        concatMediaMuxer(str2, str);
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public void writeFrameIndex(int i) {
        if (this.ffMpegCallback != null) {
            this.ffMpegCallback.onFrame(i);
        }
    }

    public void setFFMpegCallback(FFMpegCallback fFMpegCallback) {
        this.ffMpegCallback = fFMpegCallback;
    }
}
