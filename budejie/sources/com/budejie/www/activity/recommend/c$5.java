package com.budejie.www.activity.recommend;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class c$5 extends a<String> {
    final /* synthetic */ SuggestedFollowsListItem a;
    final /* synthetic */ c b;

    c$5(c cVar, SuggestedFollowsListItem suggestedFollowsListItem) {
        this.b = cVar;
        this.a = suggestedFollowsListItem;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
        super.onStart();
        c.g(this.b).f();
    }

    public void a(String str) {
        if (!this.b.isDetached()) {
            try {
                c.g(this.b).e();
                ResultBean s = z.s(str);
                if (s != null) {
                    CharSequence code = s.getCode();
                    Object msg = s.getMsg();
                    if (TextUtils.isEmpty(msg)) {
                        an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                    } else {
                        an.a(this.b.getActivity(), msg, -1).show();
                    }
                    if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                        this.a.is_follow = 1;
                        c.e(this.b).notifyDataSetChanged();
                        c.h(this.b).a(new Fans(this.a));
                        as.b().a(this.a);
                        return;
                    }
                    return;
                }
                an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
            } catch (Exception e) {
                e.printStackTrace();
                an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
            }
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.b.isDetached()) {
            c.g(this.b).e();
            an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
        }
    }
}
