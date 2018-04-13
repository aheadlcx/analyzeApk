package com.sprite.ads.internal.utils;

import android.content.Context;
import android.support.annotation.NonNull;

public final class ResUtil {

    public enum ResType {
        ID,
        LAYOUT,
        DRAWABLE,
        STRING,
        COLOR
    }

    public static int a(Context context, ResType resType, String str) {
        String packageName = context.getPackageName();
        return context.getResources().getIdentifier(str, a(resType), packageName);
    }

    @NonNull
    private static String a(ResType resType) {
        switch (resType) {
            case ID:
                return "id";
            case LAYOUT:
                return "layout";
            case DRAWABLE:
                return "drawable";
            case STRING:
                return "string";
            case COLOR:
                return "color";
            default:
                return "";
        }
    }
}
