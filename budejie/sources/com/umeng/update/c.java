package com.umeng.update;

import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.facebook.common.util.UriUtil;
import com.umeng.update.net.d;
import com.umeng.update.util.b;
import java.io.File;

public class c implements d {
    private final String a = "delta_update";
    private final String b = "update_normal";
    private UmengDownloadListener c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private Context h = null;
    private String i = null;
    private com.umeng.update.net.a j = null;
    private boolean k = false;

    class a extends b {
        final /* synthetic */ c a;
        private String e = "";
        private String f = "";

        public a(c cVar, Context context) {
            this.a = cVar;
            super(context);
        }

        public a a(CharSequence charSequence) {
            if (VERSION.SDK_INT >= 14) {
                this.d.setContentText(charSequence);
            }
            this.f = charSequence.toString();
            return this;
        }

        public a b(CharSequence charSequence) {
            if (VERSION.SDK_INT >= 14) {
                this.d.setContentTitle(charSequence);
            }
            this.e = charSequence.toString();
            return this;
        }

        public a c(CharSequence charSequence) {
            if (VERSION.SDK_INT >= 16) {
                this.d.setStyle(new BigTextStyle().bigText(charSequence));
            }
            return this;
        }

        public Notification a() {
            if (VERSION.SDK_INT >= 16) {
                return this.d.build();
            }
            if (VERSION.SDK_INT >= 14) {
                return this.d.getNotification();
            }
            this.c.setLatestEventInfo(this.b, this.e, this.f, this.c.contentIntent);
            return this.c;
        }
    }

    public void a(Context context, String str, String str2, String str3, String str4, UmengDownloadListener umengDownloadListener) {
        this.h = context;
        this.i = u.upd.a.j(context);
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.c = umengDownloadListener;
    }

    public boolean a() {
        return this.k;
    }

    public void b() {
        this.j = new com.umeng.update.net.a(this.h, this.a, this.i, this.f, this);
        this.j.a(this.g);
        this.j.b(this.e);
        a(this.j);
    }

    public void c() {
        this.j = new com.umeng.update.net.a(this.h, this.b, this.i, this.d, this);
        this.j.a(this.e);
        this.j.b(this.e);
        a(this.j);
    }

    private void a(com.umeng.update.net.a aVar) {
        int i = 0;
        try {
            i = this.h.getPackageManager().getPackageInfo(this.h.getPackageName(), 0).applicationInfo.targetSdkVersion;
        } catch (Exception e) {
        }
        if (VERSION.SDK_INT >= 16 && r0 >= 14 && UpdateConfig.isRichNotification() && !UpdateConfig.isSilentDownload()) {
            aVar.a(true);
        }
        aVar.b(UpdateConfig.isSilentDownload());
        aVar.c(UpdateConfig.isSilentDownload());
        aVar.a();
    }

    public void d() {
        this.k = true;
        if (this.c != null) {
            this.c.OnDownloadStart();
        }
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.OnDownloadUpdate(i);
        }
    }

    public void a(int i, int i2, String str) {
        switch (i) {
            case 3:
                c();
                break;
        }
        this.k = false;
        if (this.c != null) {
            this.c.OnDownloadEnd(i, str);
        }
    }

    public void b(int i) {
        switch (i) {
            case 2:
            case 7:
                this.k = true;
                return;
            case 6:
                this.k = false;
                return;
            default:
                return;
        }
    }

    public void a(Context context, UpdateResponse updateResponse, boolean z, File file) {
        Intent intent = new Intent(context, UpdateDialogActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("response", updateResponse);
        if (z) {
            bundle.putString(UriUtil.LOCAL_FILE_SCHEME, file.getAbsolutePath());
        } else {
            bundle.putString(UriUtil.LOCAL_FILE_SCHEME, null);
        }
        bundle.putBoolean("force", UpdateConfig.isUpdateForce());
        intent.putExtras(bundle);
        intent.addFlags(335544320);
        context.startActivity(intent);
    }

    public a b(Context context, UpdateResponse updateResponse, boolean z, File file) {
        CharSequence string;
        Intent intent;
        CharSequence j = u.upd.a.j(context);
        CharSequence a = updateResponse.a(context, z);
        if (z) {
            string = context.getString(u.upd.c.a(context).d("UMDialog_InstallAPK"));
            intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        } else {
            string = context.getString(u.upd.c.a(context).d("UMUpdateTitle"));
            intent = new Intent(context, UpdateDialogActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("response", updateResponse);
            bundle.putString(UriUtil.LOCAL_FILE_SCHEME, null);
            bundle.putBoolean("force", UpdateConfig.isUpdateForce());
            intent.putExtras(bundle);
            intent.addFlags(335544320);
        }
        CharSequence stringBuilder = new StringBuilder(String.valueOf(j)).append(string).toString();
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
        a aVar = new a(this, context);
        aVar.c(a).b(j).a(string).d(stringBuilder).a(activity).a(17301629).b(true);
        return aVar;
    }
}
