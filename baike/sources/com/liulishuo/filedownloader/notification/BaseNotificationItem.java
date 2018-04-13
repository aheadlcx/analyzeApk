package com.liulishuo.filedownloader.notification;

import android.app.NotificationManager;
import com.liulishuo.filedownloader.util.FileDownloadHelper;

public abstract class BaseNotificationItem {
    private int a;
    private int b;
    private int c;
    private String d;
    private String e;
    private int f = 0;
    private int g = 0;
    private NotificationManager h;

    public abstract void show(boolean z, int i, boolean z2);

    public BaseNotificationItem(int i, String str, String str2) {
        this.a = i;
        this.d = str;
        this.e = str2;
    }

    public void show(boolean z) {
        show(isChanged(), getStatus(), z);
    }

    public void update(int i, int i2) {
        this.b = i;
        this.c = i2;
        show(true);
    }

    public void updateStatus(int i) {
        this.f = i;
    }

    public void cancel() {
        a().cancel(this.a);
    }

    protected NotificationManager a() {
        if (this.h == null) {
            this.h = (NotificationManager) FileDownloadHelper.getAppContext().getSystemService("notification");
        }
        return this.h;
    }

    public int getId() {
        return this.a;
    }

    public void setId(int i) {
        this.a = i;
    }

    public int getSofar() {
        return this.b;
    }

    public void setSofar(int i) {
        this.b = i;
    }

    public int getTotal() {
        return this.c;
    }

    public void setTotal(int i) {
        this.c = i;
    }

    public String getTitle() {
        return this.d;
    }

    public void setTitle(String str) {
        this.d = str;
    }

    public String getDesc() {
        return this.e;
    }

    public void setDesc(String str) {
        this.e = str;
    }

    public int getStatus() {
        this.g = this.f;
        return this.f;
    }

    public void setStatus(int i) {
        this.f = i;
    }

    public int getLastStatus() {
        return this.g;
    }

    public boolean isChanged() {
        return this.g != this.f;
    }
}
