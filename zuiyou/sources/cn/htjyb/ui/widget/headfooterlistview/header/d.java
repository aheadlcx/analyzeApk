package cn.htjyb.ui.widget.headfooterlistview.header;

import android.content.Context;
import android.widget.LinearLayout;

public class d extends LinearLayout implements a {
    protected State a = State.kStateHide;
    protected String b = "下拉刷新";
    protected String c = "松开刷新";
    protected String d = "加载中...";

    public d(Context context) {
        super(context);
        a(context);
    }

    protected void a(Context context) {
    }

    public void setState(State state) {
        this.a = state;
    }

    public State getState() {
        return this.a;
    }
}
