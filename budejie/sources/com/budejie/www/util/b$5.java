package com.budejie.www.util;

import android.app.Activity;
import android.text.TextUtils;
import com.budejie.www.bean.VotedData;
import com.budejie.www.c.l;
import net.tsz.afinal.a.a;

class b$5 extends a<String> {
    final /* synthetic */ Activity a;

    b$5(Activity activity) {
        this.a = activity;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!TextUtils.isEmpty(str)) {
            VotedData votedData = (VotedData) z.a(str, VotedData.class);
            if (votedData != null && votedData.info != null && votedData.info.code == 0 && votedData.list != null && votedData.list.size() > 0) {
                new l(this.a).a(votedData.list);
            }
        }
    }
}
