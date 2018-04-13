package com.umeng.update.net;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.umeng.update.net.a.a;
import com.umeng.update.util.DeltaUpdate;
import java.io.File;
import u.upd.b;
import u.upd.k;
import u.upd.m;

class c$c extends AsyncTask<String, Void, Integer> {
    public int a;
    public String b;
    final /* synthetic */ c c;
    private a d;
    private Context e;
    private NotificationManager f = ((NotificationManager) this.e.getSystemService("notification"));

    protected /* synthetic */ Object doInBackground(Object... objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Integer) obj);
    }

    public c$c(c cVar, Context context, int i, a aVar, String str) {
        this.c = cVar;
        this.e = context.getApplicationContext();
        this.a = i;
        this.d = aVar;
        this.b = str;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Integer a(String... strArr) {
        int a = DeltaUpdate.a(strArr[0], strArr[1], strArr[2]) + 1;
        new File(strArr[2]).delete();
        if (a != 1) {
            b.a(c.a(), "file patch error");
        } else if (m.a(new File(strArr[1])).equalsIgnoreCase(this.d.e)) {
            b.a(c.a(), "file patch success");
        } else {
            b.a(c.a(), "file patch error");
            return Integer.valueOf(0);
        }
        return Integer.valueOf(a);
    }

    protected void a(Integer num) {
        Message obtain;
        if (num.intValue() == 1) {
            j.a(this.b, 39, -1, -1);
            Notification notification = new Notification(17301634, this.e.getString(k.l(this.e)), System.currentTimeMillis());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setDataAndType(Uri.fromFile(new File(this.b)), "application/vnd.android.package-archive");
            notification.setLatestEventInfo(this.e, u.upd.a.j(this.e), this.e.getString(k.l(this.e)), PendingIntent.getActivity(this.e, 0, intent, 134217728));
            notification.flags = 16;
            this.f.notify(this.a + 1, notification);
            if (this.c.a(this.e) && !this.d.h) {
                this.f.cancel(this.a + 1);
                this.e.startActivity(intent);
            }
            Bundle bundle = new Bundle();
            bundle.putString("filename", this.b);
            obtain = Message.obtain();
            obtain.what = 5;
            obtain.arg1 = 1;
            obtain.arg2 = this.a;
            obtain.setData(bundle);
            try {
                if (c.a(this.c).get(this.d) != null) {
                    ((Messenger) c.a(this.c).get(this.d)).send(obtain);
                }
                this.c.b(this.e, this.a);
                return;
            } catch (RemoteException e) {
                this.c.b(this.e, this.a);
                return;
            }
        }
        this.f.cancel(this.a + 1);
        bundle = new Bundle();
        bundle.putString("filename", this.b);
        obtain = Message.obtain();
        obtain.what = 5;
        obtain.arg1 = 3;
        obtain.arg2 = this.a;
        obtain.setData(bundle);
        try {
            if (c.a(this.c).get(this.d) != null) {
                ((Messenger) c.a(this.c).get(this.d)).send(obtain);
            }
            this.c.b(this.e, this.a);
        } catch (RemoteException e2) {
            this.c.b(this.e, this.a);
        }
    }
}
