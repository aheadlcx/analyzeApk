package com.lt;

import android.text.TextUtils;
import com.budejie.www.util.an;
import net.tsz.afinal.a.a;

class a$4 extends a<String> {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            an.a(a.a(this.a), a.b(this.a), str);
            a.d(this.a, str);
        }
    }
}
