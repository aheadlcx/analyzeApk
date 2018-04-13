package com.zxt.download2;

import android.text.TextUtils;
import android.webkit.URLUtil;

public class f {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private int g;
    private int h;
    private int i;
    private volatile DownloadState j;

    public String a() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String b() {
        return this.c;
    }

    public f(String str, String str2, String str3, String str4, String str5) {
        if (!URLUtil.isHttpUrl(str)) {
            throw new IllegalArgumentException("invalid url,nust start with http://");
        } else if (TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("invalid fileName");
        } else {
            this.a = str;
            this.b = str3;
            this.c = str4;
            this.d = str5;
            this.e = str2;
        }
    }

    public String getUrl() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public int e() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int f() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public int g() {
        return this.h;
    }

    public void c(int i) {
        this.h = i;
    }

    public int h() {
        return this.i;
    }

    public void d(int i) {
        this.i = i;
    }

    public DownloadState i() {
        return this.j;
    }

    public void a(DownloadState downloadState) {
        this.j = downloadState;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.e == null ? 0 : this.e.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + 31) * 31)) * 31;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        if (this.b == null) {
            if (fVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(fVar.b)) {
            return false;
        }
        if (this.e == null) {
            if (fVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(fVar.e)) {
            return false;
        }
        if (this.a == null) {
            if (fVar.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(fVar.a)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "DownloadTask [url=" + this.a + ", finishedSize=" + this.f + ", totalSize=" + this.g + ", dlPercent=" + this.h + ", downloadState=" + this.j + ", fileName=" + this.b + ", title=" + this.c + "]";
    }
}
