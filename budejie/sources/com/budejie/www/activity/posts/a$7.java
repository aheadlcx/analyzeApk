package com.budejie.www.activity.posts;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;

class a$7 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    a$7(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public void run() {
        if (!a.b(this.b).getBudejieSettings().a.a().booleanValue()) {
            return;
        }
        if (TextUtils.isEmpty(this.a)) {
            a.i(this.b).setSelection(0);
            return;
        }
        int i = 0;
        while (i < a.E(this.b).size()) {
            ListItemObject listItemObject = (ListItemObject) a.E(this.b).get(i);
            if (listItemObject == null || a.i(this.b) == null) {
                a.i(this.b).setSelection(0);
                return;
            }
            try {
                if (listItemObject.getWid().equals(this.a)) {
                    aa.b("PostsFeaturesFragment", "initCacheData() i + mXListView.getHeaderViewsCount() =" + (a.i(this.b).getHeaderViewsCount() + i));
                    if (VERSION.SDK_INT >= 21) {
                        a.i(this.b).setSelectionFromTop(a.i(this.b).getHeaderViewsCount() + i, an.a(a.b(this.b), 40));
                        return;
                    } else {
                        a.i(this.b).setSelection(a.i(this.b).getHeaderViewsCount() + i);
                        return;
                    }
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
