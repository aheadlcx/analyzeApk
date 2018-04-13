package qsbk.app.activity;

import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

class in extends SimpleHttpTask {
    final /* synthetic */ CircleTopicActivity a;

    in(CircleTopicActivity circleTopicActivity, String str, SimpleCallBack simpleCallBack) {
        this.a = circleTopicActivity;
        super(str, simpleCallBack);
    }

    protected void a() {
        this.a.S.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        this.a.S.setText("正在打卡...");
        this.a.R.setClickable(false);
        super.a();
    }

    protected void b() {
        super.b();
        this.a.S.setCompoundDrawablesWithIntrinsicBounds(R.drawable.clocked_bottom, 0, 0, 0);
        this.a.S.setText("打卡");
        this.a.R.setClickable(true);
    }
}
