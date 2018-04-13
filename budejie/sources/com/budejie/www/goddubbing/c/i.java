package com.budejie.www.goddubbing.c;

import com.spriteapp.videoedit.spritefilters.SpriteFiltersClass;
import com.spriteapp.videoedit.spritefilters.SpriteFiltersClass.ProgressListener;
import java.util.ArrayList;

public class i {
    public static int a(String str, String str2, String str3, float f, float f2) {
        return new SpriteFiltersClass().spriteAppAudioMuxer(str, str2, str3, f, f2);
    }

    public static int a(String str, String str2) {
        return new SpriteFiltersClass().spriteAudioConcat(str, str2);
    }

    public static int a(String str, String str2, String str3) {
        return new SpriteFiltersClass().spriteImageToAV(str, str2, str3);
    }

    public static int a(ArrayList<String> arrayList, String str, int i, int i2, ProgressListener progressListener) {
        SpriteFiltersClass spriteFiltersClass = new SpriteFiltersClass();
        spriteFiltersClass.setProgressListener(progressListener);
        return spriteFiltersClass.spriteAppAVContact(arrayList, str, i, i2);
    }

    public static int a(String str, String str2, float f, float f2, ProgressListener progressListener) {
        SpriteFiltersClass spriteFiltersClass = new SpriteFiltersClass();
        spriteFiltersClass.setProgressListener(progressListener);
        return spriteFiltersClass.spriteVideoCutOut(str, str2, f, f2);
    }

    public static int a() {
        return new SpriteFiltersClass().spriteExit();
    }
}
