package com.budejie.www.download;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.budejie.www.download.a.a;
import com.sprite.ads.internal.log.ADLog;

public class BDJDownloadService extends Service {
    BDJDownReceiver a = new BDJDownReceiver();

    public enum Command {
        DOWNLOAD,
        INSTALL_APK
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        ADLog.d("BDJDownloadService onCreate");
        a();
    }

    private void a() {
        IntentFilter intentFilter = new IntentFilter();
        BDJDownReceiver bDJDownReceiver = this.a;
        intentFilter.addAction("stop.down");
        registerReceiver(this.a, intentFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.a);
        ADLog.d("BDJDownloadService onDestroy");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 3;
        }
        switch ((Command) intent.getSerializableExtra("command")) {
            case DOWNLOAD:
                b(intent);
                break;
            case INSTALL_APK:
                a(intent);
                break;
        }
        return super.onStartCommand(intent, i, i2);
    }

    private void a(Intent intent) {
        d.a(this, intent.getStringExtra("apk_path"), intent.getStringExtra("apk_name"));
    }

    private void b(Intent intent) {
        String stringExtra = intent.getStringExtra("download_path");
        String stringExtra2 = intent.getStringExtra("url");
        h eVar = new e(this);
        eVar.a(stringExtra2);
        a aVar = new a(eVar);
        aVar.a(new a(this) {
            final /* synthetic */ BDJDownloadService a;

            {
                this.a = r1;
            }

            public void a(String str) {
            }

            public void b(String str) {
            }
        });
        aVar.a(stringExtra2);
        aVar.b(stringExtra);
        b.a().a(this, aVar);
    }
}
