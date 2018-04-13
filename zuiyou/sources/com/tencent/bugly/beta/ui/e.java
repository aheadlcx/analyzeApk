package com.tencent.bugly.beta.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.global.b;
import com.tencent.bugly.proguard.an;

public class e extends a {
    protected TextView n;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.l = com.tencent.bugly.beta.global.e.E.j;
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.l == 0) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            this.n = new TextView(this.a);
            this.n.setLayoutParams(layoutParams);
            TextView textView = this.n;
            this.j.getClass();
            textView.setTextColor(Color.parseColor("#757575"));
            this.n.setTextSize(16.0f);
            this.n.setTag(Beta.TAG_TIP_MESSAGE);
            this.i.addView(this.n);
        } else if (onCreateView != null) {
            this.n = (TextView) onCreateView.findViewWithTag(Beta.TAG_TIP_MESSAGE);
        }
        try {
            this.n.setText("检测到当前版本需要重启，是否重启应用？");
            this.f.setText("更新提示");
            a("取消", new b(8, this), "重启应用", new b(7, this));
        } catch (Throwable e) {
            if (this.l != 0) {
                an.e("please confirm your argument: [Beta.tipsDialogLayoutId] is correct", new Object[0]);
            }
            if (!an.b(e)) {
                e.printStackTrace();
            }
        }
        return onCreateView;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }
}
