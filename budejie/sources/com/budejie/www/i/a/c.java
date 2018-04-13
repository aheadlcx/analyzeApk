package com.budejie.www.i.a;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.a.a.a;
import com.budejie.www.a.b;
import com.budejie.www.http.j;
import com.budejie.www.util.au;
import java.io.IOException;
import okhttp3.e;

public class c {
    private com.budejie.www.i.b.c a;
    private e b;

    public void a(com.budejie.www.i.b.c cVar) {
        this.a = cVar;
    }

    public void a() {
        if (this.a != null) {
            this.a = null;
        }
        if (this.b != null) {
            this.b.c();
            this.b = null;
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        if (this.a != null) {
            this.b = b.a().a(j.a(str, "1", str2, str3, str4), new a(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a(e eVar, IOException iOException) {
                    if (this.a.a != null) {
                        this.a.a.a(iOException);
                        this.a.a.g();
                    }
                    this.a.b = null;
                }

                public void a(String str) {
                    Object obj = null;
                    this.a.b = null;
                    if (this.a.a != null) {
                        this.a.a.g();
                    }
                    if (!TextUtils.isEmpty(str)) {
                        if (this.a.a != null) {
                            obj = com.budejie.www.j.a.e(this.a.a.h(), str);
                        }
                        if (obj == null) {
                            au.a(R.string.labeldetails_no_listdata);
                        } else if (this.a.a != null) {
                            this.a.a.a(obj);
                        }
                    }
                }
            });
        }
    }
}
