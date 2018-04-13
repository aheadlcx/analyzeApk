package com.budejie.www.activity.mycomment;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.util.an;
import net.tsz.afinal.a.a;

class a$7 extends a<String> {
    final /* synthetic */ a a;

    a$7(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void a(String str) {
        if (!this.a.isDetached()) {
            if (TextUtils.isEmpty(str)) {
                an.a(this.a.getActivity(), this.a.getActivity().getString(R.string.mycomment_delete_faild_text), -1).show();
            } else if ("0".equals(str)) {
                a.g(this.a).a(a.h(this.a));
                an.a(this.a.getActivity(), this.a.getActivity().getString(R.string.mycomment_delete_success_text), -1).show();
            } else {
                an.a(this.a.getActivity(), this.a.getActivity().getString(R.string.mycomment_delete_faild_text), -1).show();
            }
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.a.isDetached()) {
            an.a(this.a.getActivity(), this.a.getActivity().getString(R.string.mycomment_delete_faild_text), -1).show();
        }
    }
}
