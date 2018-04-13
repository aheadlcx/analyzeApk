package com.baidu.mobads.openad.b;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.baidu.mobads.AppActivity;
import com.baidu.mobads.command.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils.ApkInfo;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.d;
import com.baidu.mobads.utils.l;
import com.baidu.mobads.utils.o;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class b implements Observer {
    private static NotificationManager a;
    private static int b = 10091;
    private static HashMap<String, b> g = new HashMap();
    private a c = null;
    private Context d;
    private com.baidu.mobads.b.b e = null;
    private String f = "";
    private Handler h = new Handler(Looper.getMainLooper());

    public a a() {
        return this.c;
    }

    public static synchronized b a(String str) {
        b bVar;
        synchronized (b.class) {
            bVar = (b) g.get(str);
        }
        return bVar;
    }

    public static synchronized b b(String str) {
        b bVar;
        synchronized (b.class) {
            bVar = (b) g.remove(str);
        }
        return bVar;
    }

    public static synchronized void a(String str, b bVar) {
        synchronized (b.class) {
            g.put(str, bVar);
        }
    }

    public static synchronized int c(String str) {
        int i;
        synchronized (b.class) {
            b bVar = (b) g.get(str);
            if (bVar == null || bVar.a() == null) {
                i = b;
                b = i + 1;
            } else {
                i = bVar.a().f;
            }
        }
        return i;
    }

    public b(Context context, a aVar) {
        XAdSDKFoundationFacade.getInstance().getAdLogger().d("OAdApkDownloaderObserver", "observer created");
        if (a == null) {
            a = (NotificationManager) context.getSystemService("notification");
        }
        this.d = context.getApplicationContext();
        this.c = aVar;
        a(this.c.i, this);
    }

    public void update(Observable observable, Object obj) {
        IOAdDownloader iOAdDownloader = (IOAdDownloader) observable;
        this.c.g = iOAdDownloader.getState();
        String fileName = XAdSDKFoundationFacade.getInstance().getURIUitls().getFileName(iOAdDownloader.getOutputPath());
        if (!this.c.b.equals(fileName)) {
            this.c.b = fileName;
        }
        if (this.c.g == DownloadStatus.DOWNLOADING) {
            if (this.c.d < 0) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().d("OAdApkDownloaderObserver", "download update---mExtraInfo.contentLength < 0");
                this.c.d = (long) iOAdDownloader.getFileSize();
                this.c.k = iOAdDownloader.getTargetURL();
                this.c.a(this.d);
                this.f = String.format(Locale.CHINA, "%.1fM", new Object[]{Float.valueOf(((float) this.c.d) / 1048576.0f)});
            }
            if (iOAdDownloader.getProgress() > 0.0f) {
                int progress = (int) iOAdDownloader.getProgress();
                if (progress > this.c.e) {
                    this.c.e = progress;
                    if (this.c.m) {
                        a(iOAdDownloader);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (this.c.g == DownloadStatus.COMPLETED) {
            d commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            commonUtils.sendDownloadAdLog(this.d, 528, com.baidu.mobads.openad.c.b.COMPLETE, this.c.q, this.c.i, commonUtils.getAppId(this.d), "", ((o) XAdSDKFoundationFacade.getInstance().getSystemUtils()).getPhoneOSBrand(), Build.MODEL, VERSION.RELEASE, VERSION.SDK_INT);
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("OAdApkDownloaderObserver", "download success-->>" + iOAdDownloader.getOutputPath());
            boolean z = this.c.l;
            if (z) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().d("OAdApkDownloaderObserver", "launch installing .............");
                String str = this.c.c + this.c.b;
                if (!this.c.i.contains(".")) {
                    ApkInfo localApkFileInfo = XAdSDKFoundationFacade.getInstance().getPackageUtils().getLocalApkFileInfo(this.d, str);
                    this.c.i = localApkFileInfo.packageName;
                }
                if (this.e == null) {
                    BroadcastReceiver aVar = new com.baidu.mobads.b.a(this.c);
                    this.e = new com.baidu.mobads.b.b(this.d, this.c.i, new File(str), z);
                    this.e.a(aVar);
                    this.e.a();
                }
            }
            com.baidu.mobads.c.a.a().a(this.d, this.c);
            IXAppInfo a = com.baidu.mobads.command.a.a.a(this.c);
            if (a != null) {
                com.baidu.mobads.production.a.d().getXMonitorActivation(this.d, l.a()).addAppInfoForMonitor(a);
            }
        } else if (this.c.g == DownloadStatus.ERROR) {
            this.c.k = iOAdDownloader.getTargetURL();
            XAdSDKFoundationFacade.getInstance().getAdLogger().e("OAdApkDownloaderObserver", "download failed-->>" + iOAdDownloader.getOutputPath());
            com.baidu.mobads.c.a.a().a(this.c);
        } else if (iOAdDownloader.getState() == DownloadStatus.INITING) {
            a aVar2 = this.c;
            aVar2.r++;
        }
        if (this.c.m) {
            a(iOAdDownloader);
        }
        this.c.a(this.d);
    }

    public void a(IOAdDownloader iOAdDownloader) {
        this.h.post(new c(this, iOAdDownloader));
    }

    private void d(String str) {
        Toast.makeText(this.d, str, 0).show();
    }

    @TargetApi(16)
    private Notification b(IOAdDownloader iOAdDownloader) {
        int i = 17301634;
        CharSequence charSequence = this.c.a;
        CharSequence charSequence2 = "正在下载 " + this.c.a;
        CharSequence charSequence3 = "";
        if (this.c.g == DownloadStatus.COMPLETED) {
            charSequence = this.c.g.getMessage() + ": " + charSequence;
            charSequence3 = " 点击这里安装应用";
        } else if (this.c.g == DownloadStatus.PAUSED) {
            String str;
            String str2 = this.c.g.getMessage() + ": " + charSequence;
            String str3 = "已为您暂停下载， 点击通知栏继续下载";
            if (iOAdDownloader.isPausedManually()) {
                str = "点击这里继续下载";
            } else {
                str = "目前不在wifi网络下， 点击这里继续下载";
            }
            r4 = str2;
            Object obj = str3;
            r2 = str;
        } else if (this.c.g == DownloadStatus.ERROR) {
            r4 = this.c.g.getMessage() + ": " + charSequence;
            r2 = " 稍后点击这里重新下载";
        } else if (this.c.g == DownloadStatus.DOWNLOADING) {
            r4 = this.c.g.getMessage() + ": " + charSequence;
            r2 = "下载进度: " + this.c.e + "%  应用大小: " + this.f;
            i = 17301633;
        } else if (this.c.g == DownloadStatus.INITING) {
            r4 = this.c.g.getMessage() + ": " + charSequence;
            r2 = this.c.g.getMessage();
            i = 17301633;
        } else {
            i = 17301633;
        }
        Intent intent = new Intent(this.d, AppActivity.class);
        intent.putExtra("dealWithDownload", true);
        intent.putExtra("pausedManually", iOAdDownloader.isPausedManually());
        intent.putExtra("status", this.c.g.getCode());
        intent.putExtra(IXAdRequestInfo.PACKAGE, this.c.i);
        intent.putExtra("localApkPath", this.c.c + this.c.b);
        intent.putExtra("title", charSequence);
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent activity = PendingIntent.getActivity(this.d, this.c.f, intent, 134217728);
        if (VERSION.SDK_INT >= 16) {
            if (this.c.h == null) {
                this.c.h = new Builder(this.d);
            }
            return ((Builder) this.c.h).setContentTitle(charSequence).setContentText(charSequence3).setTicker(charSequence2).setSmallIcon(i).setContentIntent(activity).setAutoCancel(true).setProgress(100, this.c.e, false).build();
        }
        if (this.c.h == null) {
            this.c.h = new Notification();
        }
        Notification notification = (Notification) this.c.h;
        notification.icon = i;
        notification.flags |= 16;
        notification.tickerText = charSequence2;
        notification.contentIntent = activity;
        try {
            notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{this.d, charSequence, charSequence3, activity});
            return notification;
        } catch (NoSuchMethodException e) {
            return notification;
        } catch (IllegalAccessException e2) {
            return notification;
        } catch (IllegalArgumentException e3) {
            return notification;
        } catch (InvocationTargetException e4) {
            return notification;
        }
    }
}
