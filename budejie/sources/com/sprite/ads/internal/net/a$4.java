package com.sprite.ads.internal.net;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.sprite.ads.internal.log.ADLog;
import java.io.IOException;

class a$4 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ SharedPreferences b;

    a$4(String str, SharedPreferences sharedPreferences) {
        this.a = str;
        this.b = sharedPreferences;
    }

    @SuppressLint({"CommitPrefEdits"})
    public void run() {
        try {
            ADLog.d("market:" + this.a);
            this.b.edit().putString("market", this.a).commit();
            ADLog.d("清除ad缓存");
            if (a.d() != null) {
                a.d().b();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
