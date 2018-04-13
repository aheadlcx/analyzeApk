package qsbk.app.utils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.widget.BaseGroupDialog;

final class s extends BaseGroupDialog {
    ViewGroup a;
    int b = 0;
    final /* synthetic */ SimpleCallBack c;
    final /* synthetic */ int d;
    final /* synthetic */ String e;

    s(Context context, SimpleCallBack simpleCallBack, int i, String str) {
        this.c = simpleCallBack;
        this.d = i;
        this.e = str;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        int i;
        int i2;
        int i3 = -15329253;
        int i4 = -12171438;
        super.onCreate(bundle);
        setContentView(R.layout.dialog_member_mute);
        findViewById(R.id.member_mute_lin).setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
        TextView textView = (TextView) findViewById(R.id.member_mute_remind);
        if (UIHelper.isNightTheme()) {
            i = -9802626;
        } else {
            i = -12894910;
        }
        textView.setTextColor(i);
        textView = (TextView) findViewById(R.id.mute_ten_min);
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -12894910;
        }
        textView.setTextColor(i);
        textView = (TextView) findViewById(R.id.mute_one_hour);
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -12894910;
        }
        textView.setTextColor(i);
        textView = (TextView) findViewById(R.id.mute_twelve_hour);
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -12894910;
        }
        textView.setTextColor(i);
        textView = (TextView) findViewById(R.id.mute_one_day);
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -12894910;
        }
        textView.setTextColor(i);
        textView = (TextView) findViewById(R.id.cancel);
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -12894910;
        }
        textView.setTextColor(i);
        textView = (TextView) findViewById(R.id.submit);
        if (!UIHelper.isNightTheme()) {
            i4 = -12894910;
        }
        textView.setTextColor(i4);
        View findViewById = findViewById(R.id.member_mute_bottom);
        if (UIHelper.isNightTheme()) {
            i2 = -15329253;
        } else {
            i2 = -1184275;
        }
        findViewById.setBackgroundColor(i2);
        findViewById = findViewById(R.id.member_mute_view_middle);
        if (UIHelper.isNightTheme()) {
            i2 = -15329253;
        } else {
            i2 = -1184275;
        }
        findViewById.setBackgroundColor(i2);
        View findViewById2 = findViewById(R.id.member_mute_view);
        if (!UIHelper.isNightTheme()) {
            i3 = -1184275;
        }
        findViewById2.setBackgroundColor(i3);
        this.a = (ViewGroup) findViewById(R.id.time_group);
        OnClickListener tVar = new t(this);
        for (i2 = 0; i2 < this.a.getChildCount(); i2++) {
            this.a.getChildAt(i2).setOnClickListener(tVar);
        }
        findViewById(R.id.cancel).setOnClickListener(new u(this));
        findViewById(R.id.submit).setOnClickListener(new v(this));
    }
}
