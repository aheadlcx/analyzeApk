package com.baidu.location.c;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import com.baidu.location.LLSInterface;
import com.baidu.location.a.c;
import com.baidu.location.a.h;
import com.baidu.location.a.i;
import com.baidu.location.a.l;
import com.baidu.location.a.r;
import com.baidu.location.a.s;
import com.baidu.location.b.b;
import com.baidu.location.b.d;
import com.baidu.location.f;
import com.baidu.mobstat.Config;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

public class a extends Service implements LLSInterface {
    static a a = null;
    private static long f = 0;
    Messenger b = null;
    private Looper c;
    private HandlerThread d;
    private boolean e = false;

    public class a extends Handler {
        final /* synthetic */ a a;

        public a(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            if (f.isServing) {
                switch (message.what) {
                    case 11:
                        this.a.a(message);
                        break;
                    case 12:
                        this.a.b(message);
                        break;
                    case 15:
                        this.a.c(message);
                        break;
                    case 22:
                        i.c().b(message);
                        break;
                    case 41:
                        i.c().h();
                        break;
                    case 401:
                        try {
                            message.getData();
                            break;
                        } catch (Exception e) {
                            break;
                        }
                    case 405:
                        byte[] byteArray = message.getData().getByteArray("errorid");
                        if (byteArray != null && byteArray.length > 0) {
                            String str = new String(byteArray);
                            break;
                        }
                }
            }
            if (message.what == 1) {
                this.a.c();
            }
            if (message.what == 0) {
                this.a.b();
            }
            super.handleMessage(message);
        }
    }

    public static Handler a() {
        return a;
    }

    private void a(Message message) {
        Log.d("baidu_location_service", "baidu location service register ...");
        com.baidu.location.a.a.a().a(message);
        l.b().c();
    }

    private void b() {
        h.a().a(f.getServiceContext());
        d.a().b();
        b.a().b();
        com.baidu.location.b.h.a().b();
        com.baidu.location.d.b.a();
        i.c().d();
    }

    private void b(Message message) {
        com.baidu.location.a.a.a().b(message);
    }

    private void c() {
        com.baidu.location.b.h.a().c();
        d.a().e();
        b.a().c();
        i.c().e();
        s.e();
        com.baidu.location.a.a.a().b();
        c.a().b();
        try {
            if (a != null) {
                a.removeCallbacksAndMessages(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        a = null;
        Log.d("baidu_location_service", "baidu location service has stoped ...");
        if (!this.e) {
            Process.killProcess(Process.myPid());
        }
    }

    private void c(Message message) {
        com.baidu.location.a.a.a().c(message);
    }

    public double getVersion() {
        return 7.019999980926514d;
    }

    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        boolean z = false;
        if (extras != null) {
            com.baidu.location.d.b.g = extras.getString(PayPWDUniversalActivity.KEY);
            com.baidu.location.d.b.f = extras.getString(Config.SIGN);
            this.e = extras.getBoolean("kill_process");
            z = extras.getBoolean("cache_exception");
        }
        if (z) {
        }
        return this.b.getBinder();
    }

    public void onCreate(Context context) {
        f = System.currentTimeMillis();
        this.d = r.a();
        this.c = this.d.getLooper();
        a = new a(this, this.c);
        this.b = new Messenger(a);
        a.sendEmptyMessage(0);
        Log.d("baidu_location_service", "baidu location service start1 ..." + Process.myPid());
    }

    public void onDestroy() {
        try {
            a.sendEmptyMessage(1);
        } catch (Exception e) {
            Log.d("baidu_location_service", "baidu location service stop exception...");
            Process.killProcess(Process.myPid());
        }
        Log.d("baidu_location_service", "baidu location service stop ...");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public boolean onUnBind(Intent intent) {
        return false;
    }
}
