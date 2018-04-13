package com.spriteapp.videoedit.spritefilters;

import android.util.Log;
import com.budejie.www.goddubbing.b.a;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SpriteFiltersClass {
    private static final int SPRITE_CURRENT_TIME_MESSAGE = 3;
    public static final int SPRITE_ERROR_MESSAGE = -1;
    private static final int SPRITE_LOG_MESSAGE = 2;
    private static final int SPRITE_SUCCESS_MESSAGE = 0;
    private static final int SPRITE_WARNING_MESSAGE = 1;
    private ProgressListener mProgressListener;

    public interface ProgressListener {
        void sendMessage(String str);
    }

    public native void NativeInitJNI();

    public native int spriteAVConcateJNI(SpriteFiltersConcateFileStructure spriteFiltersConcateFileStructure, ArrayList<String> arrayList);

    public native int spriteAudioConcatJNI(SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge);

    public native int spriteAudioMergeJNI(SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge);

    public native int spriteAudioMuxerJNI(SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge);

    public native int spriteDrawTextJNI(SpriteFiltersConcateFileStructure spriteFiltersConcateFileStructure);

    public native int spriteExitJNI();

    public native int spriteGetImageJNI(SpriteFiltersGetImageStructure spriteFiltersGetImageStructure);

    public native int spriteImageToAVJNI(SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge);

    public native int spriteOneImageToVideoJNI(SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge);

    public native int spriteTranscoding(SpriteFiltersInfoStructure spriteFiltersInfoStructure);

    public native int spriteVideoCutOutJNI(SpriteFiltersInfoStructure spriteFiltersInfoStructure);

    public native String stringFromJNI();

    public native String stringFromTestObjJNI(SpriteFiltersInfoStructure spriteFiltersInfoStructure);

    static {
        System.loadLibrary("SpriteVideoEdit-jni");
        System.loadLibrary("ffmpeg");
    }

    public void onSpriteCallBack(int i, String str) {
        Log.e("SpriteFiltersClass", "count=" + i);
        switch (i) {
            case 3:
                if (this.mProgressListener != null) {
                    this.mProgressListener.sendMessage(str);
                }
                Log.e("SpriteFiltersClass", "SPRITE_CURRENT_TIME_MESSAGE:" + str);
                return;
            default:
                return;
        }
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.mProgressListener = progressListener;
    }

    public final String spriteGetVer() {
        NativeInitJNI();
        String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())) + ".mp4";
        SpriteFiltersInfoStructure spriteFiltersInfoStructure = new SpriteFiltersInfoStructure();
        spriteFiltersInfoStructure.spriteSetInputVideoFileNamePath("/mnt/sdcard2/9.mp4");
        spriteFiltersInfoStructure.spriteSetInputAudioFileNamePath("/mnt/sdcard2/6.mp3");
        spriteFiltersInfoStructure.spriteSetInputSRTFileNamePath("/mnt/sdcard2/1.srt");
        spriteFiltersInfoStructure.spriteSetOutputFileNamePath("/mnt/sdcard2/" + str);
        spriteFiltersInfoStructure.spriteSetVideoIsCrop(1);
        spriteFiltersInfoStructure.spriteSetVideoCropWidth(480);
        spriteFiltersInfoStructure.spriteSetVideoCropHeight(480);
        spriteFiltersInfoStructure.spriteSetVideoCropX(0);
        spriteFiltersInfoStructure.spriteSetVideoCropY(0);
        spriteFiltersInfoStructure.spriteSetVideoTranspose(3);
        spriteFiltersInfoStructure.spriteSetOutputVideoWidth(480);
        spriteFiltersInfoStructure.spriteSetOutputVideoHeight(480);
        spriteFiltersInfoStructure.spriteSetIsWater(1);
        spriteFiltersInfoStructure.spriteSetWaterFileNamePath("/mnt/sdcard2/water.png");
        spriteFiltersInfoStructure.spriteSetWaterX(22);
        spriteFiltersInfoStructure.spriteSetWaterY(22);
        spriteTranscoding(spriteFiltersInfoStructure);
        return "test";
    }

    public int spriteAppDrawText() {
        NativeInitJNI();
        return spriteDrawTextJNI(null);
    }

    public int spriteAppGetImage(String str, String str2, float f) {
        NativeInitJNI();
        SpriteFiltersGetImageStructure spriteFiltersGetImageStructure = new SpriteFiltersGetImageStructure();
        spriteFiltersGetImageStructure.spriteSetInputVideFileNamePath(str);
        spriteFiltersGetImageStructure.spriteSetOutputImagePath(str2 + "/");
        spriteFiltersGetImageStructure.spriteSetOutImageName(a.c);
        spriteFiltersGetImageStructure.spriteSetOutImageCount(20);
        spriteFiltersGetImageStructure.spriteSetTotalTime(f);
        return spriteGetImageJNI(spriteFiltersGetImageStructure);
    }

    public int spriteAppAudioMuxer(String str, String str2, String str3, float f, float f2) {
        NativeInitJNI();
        SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge = new SpriteFiltersAudioMuxerAndMerge();
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAVFileNamePath(str);
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAudioFileNamePath(str2);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputAVFileNamePath(str3);
        spriteFiltersAudioMuxerAndMerge.spriteSetVCodec("libx264");
        spriteFiltersAudioMuxerAndMerge.spriteSetACodec("libfaac");
        spriteFiltersAudioMuxerAndMerge.spriteSetIsWater(0);
        spriteFiltersAudioMuxerAndMerge.spriteSetWaterX(10);
        spriteFiltersAudioMuxerAndMerge.spriteSetWaterY(10);
        spriteFiltersAudioMuxerAndMerge.spriteSetStartTime(f);
        spriteFiltersAudioMuxerAndMerge.spriteSetEndTime(f2);
        return spriteAudioMuxerJNI(spriteFiltersAudioMuxerAndMerge);
    }

    public int spriteAppAudioMerge() {
        NativeInitJNI();
        SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge = new SpriteFiltersAudioMuxerAndMerge();
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAVFileNamePath("/mnt/sdcard/wangfeng.mp4");
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAudioFileNamePath("/mnt/sdcard/1.mp3");
        spriteFiltersAudioMuxerAndMerge.spriteSetWaterFileNamePath("/mnt/sdcard/xtdm.png");
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputAVFileNamePath("/mnt/sdcard/ghl-Merge12.mp4");
        spriteFiltersAudioMuxerAndMerge.spriteSetVCodec("libx264");
        spriteFiltersAudioMuxerAndMerge.spriteSetACodec("libfaac");
        spriteFiltersAudioMuxerAndMerge.spriteSetIsWater(1);
        spriteFiltersAudioMuxerAndMerge.spriteSetWaterX(10);
        spriteFiltersAudioMuxerAndMerge.spriteSetWaterY(10);
        return spriteAudioMergeJNI(spriteFiltersAudioMuxerAndMerge);
    }

    public int spriteAudioConcat(String str, String str2) {
        NativeInitJNI();
        SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge = new SpriteFiltersAudioMuxerAndMerge();
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAudioFileNamePath(str);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputAVFileNamePath(str2);
        spriteFiltersAudioMuxerAndMerge.spriteSetACodec("libfaac");
        return spriteAudioConcatJNI(spriteFiltersAudioMuxerAndMerge);
    }

    public int spriteImageToAV(String str, String str2, String str3) {
        NativeInitJNI();
        SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge = new SpriteFiltersAudioMuxerAndMerge();
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAVFileNamePath(str);
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAudioFileNamePath(str3);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputAVFileNamePath(str2);
        spriteFiltersAudioMuxerAndMerge.spriteSetVCodec("libx264");
        spriteFiltersAudioMuxerAndMerge.spriteSetACodec("libfaac");
        spriteFiltersAudioMuxerAndMerge.spriteSetFrameRate(10);
        spriteFiltersAudioMuxerAndMerge.spriteSetIsWater(0);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputVideoWidth(480);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputVideoHeight(480);
        spriteFiltersAudioMuxerAndMerge.spriteSetWaterX(10);
        spriteFiltersAudioMuxerAndMerge.spriteSetWaterY(10);
        return spriteImageToAVJNI(spriteFiltersAudioMuxerAndMerge);
    }

    public int spriteAppAVContact(ArrayList<String> arrayList, String str, int i, int i2) {
        int i3 = 0;
        String str2 = "";
        NativeInitJNI();
        SpriteFiltersConcateFileStructure spriteFiltersConcateFileStructure = new SpriteFiltersConcateFileStructure();
        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())) + ".mp4";
        spriteFiltersConcateFileStructure.spriteSetOutputVideoWidth(i);
        spriteFiltersConcateFileStructure.spriteSetOutputVideoHeight(i2);
        spriteFiltersConcateFileStructure.spriteSetOutputAudioFlag(0);
        String str3;
        int i4;
        if (spriteFiltersConcateFileStructure.spriteGetOutputAudioFlag() == 1) {
            str3 = str2;
            for (i4 = 0; i4 < arrayList.size(); i4++) {
                str3 = str3 + "[" + i4 + ":v] setdar=dar=0:0,setsar=sar=0:0, scale=" + spriteFiltersConcateFileStructure.spriteGetOutputVideoWidth() + ":" + spriteFiltersConcateFileStructure.spriteGetOutputVideoHeight() + " [v" + i4 + "];";
            }
            while (i3 < arrayList.size()) {
                str3 = str3 + "[v" + i3 + "]  [" + i3 + ":a]";
                i3++;
            }
            str2 = str3 + " concat=n=" + arrayList.size() + ":v=1:a=1 [v] [a]";
        } else {
            str3 = str2;
            for (i4 = 0; i4 < arrayList.size(); i4++) {
                str3 = str3 + "[" + i4 + ":v] setdar=dar=0:0,setsar=sar=0:0, scale=" + spriteFiltersConcateFileStructure.spriteGetOutputVideoWidth() + ":" + spriteFiltersConcateFileStructure.spriteGetOutputVideoHeight() + " [v" + i4 + "];";
            }
            while (i3 < arrayList.size()) {
                str3 = str3 + "[v" + i3 + "]";
                i3++;
            }
            str2 = str3 + " concat=n=" + arrayList.size() + ":v=1 [v]";
        }
        Log.e("SpriteFiltersClass", "strTmp=" + str2);
        spriteFiltersConcateFileStructure.spriteSetInputAV1FileNamePath(str2);
        spriteFiltersConcateFileStructure.spriteSetOutputFileNamePath(str);
        return spriteAVConcateJNI(spriteFiltersConcateFileStructure, arrayList);
    }

    public int spriteOneImageToAV(String str, String str2) {
        NativeInitJNI();
        SpriteFiltersAudioMuxerAndMerge spriteFiltersAudioMuxerAndMerge = new SpriteFiltersAudioMuxerAndMerge();
        spriteFiltersAudioMuxerAndMerge.spriteSetInputAVFileNamePath(str);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputAVFileNamePath(str2);
        spriteFiltersAudioMuxerAndMerge.spriteSetVCodec("libx264");
        spriteFiltersAudioMuxerAndMerge.spriteSetFrameRate(10);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputVideoWidth(480);
        spriteFiltersAudioMuxerAndMerge.spriteSetOutputVideoHeight(480);
        spriteFiltersAudioMuxerAndMerge.spriteSetEndTime(10.0f);
        return spriteOneImageToVideoJNI(spriteFiltersAudioMuxerAndMerge);
    }

    public int spriteVideoCutOut(String str, String str2, float f, float f2) {
        NativeInitJNI();
        SpriteFiltersInfoStructure spriteFiltersInfoStructure = new SpriteFiltersInfoStructure();
        spriteFiltersInfoStructure.spriteSetInputVideoFileNamePath(str);
        spriteFiltersInfoStructure.spriteSetOutputFileNamePath(str2);
        spriteFiltersInfoStructure.spriteSetIsAudio(1);
        spriteFiltersInfoStructure.spriteSetStartTime(f);
        spriteFiltersInfoStructure.spriteSetEndTime(f2);
        return spriteVideoCutOutJNI(spriteFiltersInfoStructure);
    }

    public int spriteExit() {
        return spriteExitJNI();
    }
}
