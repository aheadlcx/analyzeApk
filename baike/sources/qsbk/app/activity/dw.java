package qsbk.app.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseGroupDialog;

class dw extends BaseGroupDialog {
    final /* synthetic */ e a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ CheckInActivity d;

    dw(CheckInActivity checkInActivity, Context context, e eVar, int i, int i2) {
        this.d = checkInActivity;
        this.a = eVar;
        this.b = i;
        this.c = i2;
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(null);
            window.setBackgroundDrawable(new ColorDrawable(UIHelper.getColor(R.color.transparent)));
        }
        setContentView(R.layout.circle_toast_dialog_new);
        FrescoImageloader.displayImage((ImageView) findViewById(R.id.sign_img), this.a.link, UIHelper.getDrawable(R.drawable.daji));
        TextView textView = (TextView) findViewById(R.id.sign_content);
        if (!TextUtils.isEmpty(this.a.content)) {
            textView.setText(this.a.content);
        }
        textView = (TextView) findViewById(R.id.sign_desc);
        if (!TextUtils.isEmpty(this.a.desc)) {
            textView.setText(this.a.desc);
        }
        textView = (TextView) findViewById(R.id.msg);
        if (this.b <= 20) {
            str = "运气不错";
        } else if (this.b <= 50) {
            str = "坚持签到好运来";
        } else if (this.b <= 80) {
            str = "天上掉馅饼啦";
        } else {
            str = "人品大爆棚";
        }
        textView.setText(Html.fromHtml(str + "，获得 " + this.b + " 积分！<br/>已连续签到 <font color=#fb634a>" + this.c + "</font> 天"));
        ImageView imageView = (ImageView) findViewById(R.id.close);
        if (imageView != null) {
            imageView.setOnClickListener(new dx(this));
        }
    }
}
