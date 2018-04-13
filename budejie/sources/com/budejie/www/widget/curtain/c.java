package com.budejie.www.widget.curtain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.CommendDetail;
import com.budejie.www.activity.detail.PostDetailActivity;
import com.budejie.www.activity.video.VideoTextureView;
import com.budejie.www.activity.video.k;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.util.ao;
import com.umeng.analytics.MobclickAgent;
import java.text.SimpleDateFormat;

public class c implements OnClickListener {
    private Activity a;
    private RelativeLayout b;
    private EditText c;
    private ImageView d;
    private String e;
    private SharedPreferences f;
    private AlertDialog g;
    private a h;
    private boolean i;
    private DialogInterface.OnClickListener j = new DialogInterface.OnClickListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        @SuppressLint({"NewApi"})
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case -2:
                    this.a.g.dismiss();
                    return;
                case -1:
                    this.a.g.dismiss();
                    k.a(null).b.k();
                    i.a(this.a.a.getString(R.string.track_event_open_or_close_barrage), "关|关闭弹幕|左下角按钮");
                    MobclickAgent.onEvent(this.a.a, "E06_A12", "左下角关闭");
                    return;
                default:
                    return;
            }
        }
    };

    public interface a {
        void a(String str, String str2);
    }

    public c(a aVar) {
        this.h = aVar;
    }

    public void a(Activity activity, FloatVideoRootLayout floatVideoRootLayout, final FloatVideoLayout floatVideoLayout) {
        this.a = activity;
        this.f = activity.getSharedPreferences("weiboprefer", 0);
        this.b = (RelativeLayout) floatVideoLayout.findViewById(R.id.full_screen_write_barrage);
        this.c = (EditText) floatVideoLayout.findViewById(R.id.write_barrage_edit_text);
        this.d = (ImageView) floatVideoLayout.findViewById(R.id.write_barrage_send);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        floatVideoRootLayout.a(floatVideoLayout.b, this.c, new MutilKeyboardListenerRelativeLayout$a(this) {
            final /* synthetic */ c b;

            public void a(boolean z) {
                if (!this.b.i) {
                    return;
                }
                if (z) {
                    int currentPosition;
                    k.a(this.b.a).k();
                    VideoTextureView e = k.a(this.b.a).e();
                    if (e != null) {
                        currentPosition = e.getCurrentPosition();
                    } else {
                        currentPosition = 0;
                    }
                    this.b.e = (currentPosition / 1000) + "";
                    this.b.c.setHint(new SimpleDateFormat("mm:ss").format(Integer.valueOf(currentPosition)) + " 发弹幕");
                    this.b.b.setVisibility(0);
                    MobclickAgent.onEvent(this.b.a, "E06_A12", "发弹幕");
                    return;
                }
                this.b.i = false;
                if (!floatVideoLayout.o) {
                    k.a(this.b.a).q();
                }
                if ((this.b.a instanceof CommendDetail) && this.b.a.getRequestedOrientation() != 0) {
                    ((CommendDetail) this.b.a).g();
                }
                if ((this.b.a instanceof PostDetailActivity) && this.b.a.getRequestedOrientation() != 0) {
                    ((PostDetailActivity) this.b.a).b();
                }
                this.b.b.setVisibility(4);
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.full_screen_write_barrage:
                ao.a(this.a);
                return;
            case R.id.write_barrage_send:
                if (an.a(this.f)) {
                    String replace = this.c.getText().toString().trim().replace("\n", "").replace("\t", "");
                    if (TextUtils.isEmpty(replace)) {
                        an.a(this.a, "不能发送空弹幕哦", -1).show();
                        return;
                    }
                    ao.a(this.a);
                    BudejieApplication.a.a(RequstMethod.POST, "http://d.api.budejie.com/danmu/create/", j.e(this.a, replace, this.e, k.a(this.a).b.getWid()), null);
                    if (this.h != null) {
                        this.h.a(an.g(this.a).getProfile(), replace);
                        this.c.setText("");
                        return;
                    }
                    return;
                }
                an.a(this.a, 0, null, null, 0);
                return;
            default:
                return;
        }
    }

    void a() {
        ViewParent parent = k.a(this.a).d().c.getParent();
        if (parent != null) {
            parent = parent.getParent();
            if (parent != null) {
                View findViewById = ((View) parent).findViewById(R.id.forwardCount);
                if (findViewById != null) {
                    findViewById.performClick();
                }
            }
        }
        MobclickAgent.onEvent(this.a, "E06_A12", "分享按钮");
    }

    public void b() {
        if (this.c != null) {
            ao.a(this.a);
        }
    }

    void c() {
        if (this.g != null) {
            this.g.dismiss();
        }
    }

    public void d() {
        this.i = true;
        this.c.requestFocus();
        ((InputMethodManager) this.c.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
    }
}
