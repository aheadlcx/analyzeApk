package com.budejie.www.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.budejie.www.R;

public class f extends Dialog {
    private Context a;
    private ViewGroup b;
    private ImageView c;
    private ProgressBar d;
    private TextView e;
    private Handler f = new f$1(this);

    public f(Context context, int i) {
        super(context, i);
        this.a = context;
        setCanceledOnTouchOutside(false);
        a();
    }

    private void a() {
        this.b = (ViewGroup) LayoutInflater.from(this.a).inflate(R.layout.send_comment_dialog_layout, null);
        this.c = (ImageView) this.b.findViewById(R.id.send_comment_dialog_iv);
        this.d = (ProgressBar) this.b.findViewById(R.id.send_comment_dialog_progress);
        this.e = (TextView) this.b.findViewById(R.id.send_comment_dialog_content);
        setContentView(this.b);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.e.setText(str);
    }

    public void a(boolean z, String str) {
        a(z, str, 1000);
    }

    public void a(boolean z, String str, long j) {
        this.d.setVisibility(8);
        if (z) {
            this.c.setBackgroundResource(R.drawable.send_comment_sucess);
            this.e.setText(R.string.send_comment_dialog_success);
        } else {
            this.c.setBackgroundResource(R.drawable.send_comment_failed);
            this.e.setText(R.string.send_comment_dialog_faild);
        }
        if (!TextUtils.isEmpty(str)) {
            this.e.setText(str);
        }
        this.c.setVisibility(0);
        this.f.sendEmptyMessageDelayed(1, j);
    }
}
