package com.sprite.ads;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.sprite.ads.internal.a.c;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.download.ClearReceiver;
import com.sprite.ads.internal.download.DownReceiver;
import com.sprite.ads.internal.download.DownTask;
import com.sprite.ads.internal.download.a;
import com.sprite.ads.internal.download.b;
import com.sprite.ads.internal.download.d;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.third.ThirdApiReporter;
import com.sprite.ads.third.ThirdApiReporter2;
import com.sprite.ads.third.ThirdReportItem;

public class SpriteADService extends Service {
    DownReceiver a = new DownReceiver();
    ClearReceiver b = new ClearReceiver();

    public enum Command {
        DOWNLOAD,
        INSTALL_APK
    }

    private void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ad.stop.down");
        registerReceiver(this.a, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        registerReceiver(this.b, intentFilter);
    }

    private void a(Intent intent) {
        a.a(this, intent.getStringExtra("apk_path"), intent.getStringExtra("apk_name"));
    }

    private void b(Intent intent) {
        String stringExtra = intent.getStringExtra("download_path");
        String stringExtra2 = intent.getStringExtra("url");
        final AdItem adItem = (AdItem) intent.getSerializableExtra("aditem");
        d bVar = new b(this);
        bVar.a(stringExtra2);
        DownTask downTask = new DownTask(bVar);
        if (adItem.getIsDownListener()) {
            downTask.a(new DownTask.a(this) {
                final /* synthetic */ SpriteADService b;

                public void a(String str) {
                    DataSourceType dataSourceType = adItem.getDataSourceType();
                    if (dataSourceType == DataSourceType.API_GDT) {
                        ThirdReportItem thirdReportItem = (ThirdReportItem) adItem;
                        if (thirdReportItem.getIsNewApiVersion()) {
                            ThirdApiReporter2.EventReport(thirdReportItem.getDownloadStartReport());
                        } else {
                            ThirdApiReporter.downloadStartReport(thirdReportItem.getGdt_targetid(), thirdReportItem.getClickid());
                        }
                    } else if (dataSourceType == DataSourceType.SELF) {
                        c.a().a(adItem);
                    }
                }

                public void b(String str) {
                    DataSourceType dataSourceType = adItem.getDataSourceType();
                    if (dataSourceType == DataSourceType.API_GDT) {
                        ThirdReportItem thirdReportItem = (ThirdReportItem) adItem;
                        if (thirdReportItem.getIsNewApiVersion()) {
                            ThirdApiReporter2.EventReport(thirdReportItem.getDownloadCompleteReport());
                        } else {
                            ThirdApiReporter.downloadEndReport(thirdReportItem.getGdt_targetid(), thirdReportItem.getClickid());
                        }
                    } else if (dataSourceType == DataSourceType.SELF) {
                        c.a().b(adItem);
                    }
                }

                public void c(String str) {
                    DataSourceType dataSourceType = adItem.getDataSourceType();
                    if (dataSourceType == DataSourceType.API_GDT) {
                        ThirdReportItem thirdReportItem = (ThirdReportItem) adItem;
                        if (thirdReportItem.getIsNewApiVersion()) {
                            ThirdApiReporter2.EventReport(thirdReportItem.getDownloadInstalledReport());
                        } else {
                            ThirdApiReporter.installReport(thirdReportItem.getGdt_targetid(), thirdReportItem.getClickid());
                        }
                    } else if (dataSourceType == DataSourceType.SELF) {
                        c.a().c(adItem);
                    }
                }
            });
        }
        downTask.a(stringExtra2);
        downTask.b(stringExtra);
        com.sprite.ads.internal.download.c.a().a(this, downTask);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        ADLog.d("SpriteADService onCreate");
        a();
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.a);
        unregisterReceiver(this.b);
        ADLog.d("SpriteADService onDestroy");
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
}
