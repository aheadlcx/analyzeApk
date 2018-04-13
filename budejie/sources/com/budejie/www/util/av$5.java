package com.budejie.www.util;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import java.util.List;
import java.util.Map;

class av$5 implements OnShowListener {
    final /* synthetic */ Map a;
    final /* synthetic */ OnClickListener b;
    final /* synthetic */ List c;
    final /* synthetic */ Handler d;
    final /* synthetic */ av e;

    av$5(av avVar, Map map, OnClickListener onClickListener, List list, Handler handler) {
        this.e = avVar;
        this.a = map;
        this.b = onClickListener;
        this.c = list;
        this.d = handler;
    }

    public void onShow(DialogInterface dialogInterface) {
        if (av.c(this.e) == 0) {
            av.a(this.e, (av.d(this.e) - (((View) this.a.get(Integer.valueOf(0))).getWidth() * 3)) / 4);
        }
        ((View) this.a.get(Integer.valueOf(0))).setOnClickListener(this.b);
        for (int i = 1; i < this.c.size(); i++) {
            LayoutParams layoutParams = (LayoutParams) ((View) this.a.get(Integer.valueOf(i))).getLayoutParams();
            if (i == 3) {
                layoutParams.topMargin = av.c(this.e);
            } else {
                layoutParams.leftMargin = av.c(this.e);
            }
            ((View) this.a.get(Integer.valueOf(i))).setLayoutParams(layoutParams);
            ((View) this.a.get(Integer.valueOf(i))).setOnClickListener(this.b);
        }
        this.d.sendEmptyMessageDelayed(0, 50);
        this.d.sendEmptyMessageDelayed(1, 100);
        this.d.sendEmptyMessageDelayed(2, 80);
        this.d.sendEmptyMessageDelayed(3, 0);
        this.d.sendEmptyMessageDelayed(4, 30);
        this.d.sendEmptyMessageDelayed(5, 20);
        this.d.sendEmptyMessageDelayed(6, 120);
    }
}
