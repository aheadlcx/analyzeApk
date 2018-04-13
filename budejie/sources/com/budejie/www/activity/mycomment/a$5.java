package com.budejie.www.activity.mycomment;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.util.an;
import java.util.List;
import net.tsz.afinal.a.a;

class a$5 extends a<String> {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void a(String str) {
        if (!this.a.isDetached()) {
            a.a(this.a).b();
            if ("[]".equals(str)) {
                a.a(this.a).setPullLoadEnable(false);
                an.a(this.a.getActivity(), this.a.getActivity().getString(R.string.mycomment_no_comment), -1).show();
            } else if (TextUtils.isEmpty(str)) {
                an.a(this.a.getActivity(), this.a.getActivity().getString(R.string.no_more_data), -1).show();
            } else {
                f f = com.budejie.www.j.a.f(this.a.getActivity(), str);
                if (f != null) {
                    List list = f.a;
                    if (list != null && !list.isEmpty()) {
                        an.b(list, a.d(this.a), a.e(this.a));
                        a.g(this.a).a(list);
                        a.a(this.a, list);
                        a.a(this.a).setPullLoadEnable(true);
                    }
                }
            }
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.a.isDetached()) {
            a.a(this.a).b();
            an.a(this.a.getActivity(), this.a.getActivity().getString(R.string.parse_failed), -1).show();
        }
    }
}
