package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.widget.BaseGroupDialog;

class dy extends BaseGroupDialog {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ CheckInActivity c;

    dy(CheckInActivity checkInActivity, Context context, int i, int i2) {
        this.c = checkInActivity;
        this.a = i;
        this.b = i2;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.circle_toast_dialog);
        TextView textView = (TextView) findViewById(R.id.msg);
        if (this.a <= 20) {
            str = "运气不错";
        } else if (this.a <= 50) {
            str = "坚持签到好运来";
        } else if (this.a <= 80) {
            str = "天上掉馅饼啦";
        } else {
            str = "人品大爆棚";
        }
        textView.setText(Html.fromHtml(str + "，获得 " + this.a + " 积分！<br/>已连续签到 <font color=#fb634a>" + this.b + "</font> 天"));
        textView.postDelayed(new dz(this), 2000);
    }
}
