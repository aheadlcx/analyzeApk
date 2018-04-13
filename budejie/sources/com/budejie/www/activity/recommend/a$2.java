package com.budejie.www.activity.recommend;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.phonenumber.PhoneNumberLoginActivity;
import com.budejie.www.util.an;

class a$2 implements OnClickListener {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (((Boolean) view.getTag()).booleanValue()) {
            if (view == a.b(this.a)) {
                a.a(this.a, "0");
                a.a(this.a).d();
                a.f(this.a).b();
                HomeGroup.l -= HomeGroup.m;
                HomeGroup.m = 0;
                a.c(this.a).sendBroadcast(new Intent("android.budejie.SUB_SINA_RECOMMEND_UPDATE"));
                a.c(this.a).sendBroadcast(new Intent("android.budejie.more.RECOMMEND_UPDATE"));
            } else if (view == a.d(this.a)) {
                a.a(this.a, "1");
                a.a(this.a).d();
                a.g(this.a).b();
                HomeGroup.l -= HomeGroup.n;
                HomeGroup.n = 0;
                a.c(this.a).sendBroadcast(new Intent("android.budejie.SUB_SINA_RECOMMEND_UPDATE"));
                a.c(this.a).sendBroadcast(new Intent("android.budejie.more.RECOMMEND_UPDATE"));
            } else if (view == a.e(this.a)) {
                a.a(this.a, "3");
                a.a(this.a).d();
            }
        } else if (view == a.b(this.a)) {
            an.a(a.c(this.a), 1, "friends_recommend", "0", 1);
        } else if (view == a.d(this.a)) {
            an.a(a.c(this.a), 1, "friends_recommend", "1", 1);
        } else if (view == a.e(this.a)) {
            this.a.startActivity(new Intent(this.a.getActivity(), PhoneNumberLoginActivity.class));
        }
    }
}
