package com.microquation.linkedme.android.g;

import android.annotation.TargetApi;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

class d {
    @TargetApi(9)
    public static void a(@NonNull Editor editor) {
        try {
            editor.apply();
        } catch (AbstractMethodError e) {
            editor.commit();
        }
    }
}
