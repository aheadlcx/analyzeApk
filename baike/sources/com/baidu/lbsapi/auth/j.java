package com.baidu.lbsapi.auth;

import java.util.Hashtable;

class j implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ Hashtable e;
    final /* synthetic */ LBSAuthManager f;

    j(LBSAuthManager lBSAuthManager, int i, boolean z, String str, String str2, Hashtable hashtable) {
        this.f = lBSAuthManager;
        this.a = i;
        this.b = z;
        this.c = str;
        this.d = str2;
        this.e = hashtable;
    }

    public void run() {
        if (b.a) {
            b.a("status = " + this.a + "; forced = " + this.b + "checkAK = " + this.f.b(this.c));
        }
        if (this.a == 601 || this.b || this.a == -1 || this.f.b(this.c)) {
            if (b.a) {
                b.a("authenticate sendAuthRequest");
            }
            String[] b = c.b(LBSAuthManager.a);
            if (b.a) {
                b.a("authStrings.length:" + b.length);
            }
            if (b == null || b.length <= 1) {
                this.f.a(this.b, this.d, this.e, this.c);
                return;
            }
            if (b.a) {
                b.a("more sha1 auth");
            }
            this.f.a(this.b, this.d, this.e, b, this.c);
        } else if (602 == this.a) {
            if (b.a) {
                b.a("authenticate wait  ");
            }
            LBSAuthManager.d.b();
            this.f.a(null, this.c);
        } else {
            if (b.a) {
                b.a("authenticate else  ");
            }
            this.f.a(null, this.c);
        }
    }
}
