package com.budejie.www.activity.a;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.util.an;
import net.tsz.afinal.a.a;

class f$6 extends a<String> {
    final /* synthetic */ f a;

    f$6(f fVar) {
        this.a = fVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            an.a(f.d(this.a), f.d(this.a).getString(R.string.mycomment_delete_faild_text), -1).show();
        } else if ("0".equals(str)) {
            this.a.a();
            an.a(f.d(this.a), f.d(this.a).getString(R.string.mycomment_delete_success_text), -1).show();
        } else {
            an.a(f.d(this.a), f.d(this.a).getString(R.string.mycomment_delete_faild_text), -1).show();
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        an.a(f.d(this.a), f.d(this.a).getString(R.string.mycomment_delete_faild_text), -1).show();
    }
}
