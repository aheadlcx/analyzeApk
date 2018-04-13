package cn.xiaochuankeji.tieba.ui.danmaku;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;

public class d implements OnClickListener, cn.xiaochuankeji.tieba.ui.videomaker.d.a {
    private Context a;
    private boolean b;
    private a c;
    private final boolean d;
    private cn.xiaochuankeji.tieba.ui.videomaker.d e;
    private boolean f;
    private FrameLayout g;
    private View h;
    private EditText i;
    private Button j;
    private FrameLayout k;

    public interface a {
        void a(String str, String str2, long j);

        void g_();
    }

    public d(Context context, a aVar) {
        this(context, aVar, false);
    }

    public d(Context context, a aVar, boolean z) {
        this.b = false;
        this.a = context;
        this.c = aVar;
        this.d = z;
        if (this.d) {
            this.e = new cn.xiaochuankeji.tieba.ui.videomaker.d();
            this.e.a((cn.xiaochuankeji.tieba.ui.videomaker.d.a) this);
        }
        e();
        f();
    }

    private void e() {
        LayoutInflater from = LayoutInflater.from(this.a);
        this.g = (FrameLayout) ((Activity) this.a).findViewById(R.id.rootView);
        this.h = from.inflate(R.layout.view_input_danmaku_text, null);
        this.i = (EditText) this.h.findViewById(R.id.etInput);
        if (this.d) {
            this.i.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (((LayoutParams) this.a.h.getLayoutParams()).bottomMargin != 0) {
                        return this.a.i.onTouchEvent(motionEvent);
                    }
                    int inputType = this.a.i.getInputType();
                    this.a.i.setInputType(0);
                    this.a.i.onTouchEvent(motionEvent);
                    this.a.i.setInputType(inputType);
                    cn.htjyb.c.a.a(this.a.i, this.a.a);
                    this.a.i.setVisibility(4);
                    return true;
                }
            });
        }
        this.j = (Button) this.h.findViewById(R.id.btnSendComment);
        this.k = new FrameLayout(this.a);
        this.k.setLayoutParams(new LayoutParams(-1, -1));
    }

    private void f() {
        this.k.setOnClickListener(this);
        this.h.setOnClickListener(null);
        this.j.setOnClickListener(this);
    }

    public void a() {
        if (this.e != null && (this.a instanceof Activity)) {
            this.e.a((Activity) this.a);
        }
        g();
        cn.htjyb.c.a.a(this.i, this.a);
        if (this.d) {
            this.i.setVisibility(4);
        }
        this.b = true;
    }

    private void g() {
        this.k.removeAllViews();
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        if (cn.htjyb.c.a.h(this.a)) {
            layoutParams.gravity = 80;
        } else {
            layoutParams.gravity = 48;
        }
        this.h.setLayoutParams(layoutParams);
        if (this.k.getChildCount() == 0) {
            this.k.addView(this.h);
        }
        if (this.k.getParent() == null) {
            this.g.addView(this.k);
        }
    }

    private void h() {
        this.g.removeView(this.k);
        this.b = false;
        if (this.e != null) {
            this.e.a();
        }
    }

    public void b() {
        h();
    }

    public boolean c() {
        if (!this.b) {
            return false;
        }
        h();
        this.c.g_();
        return true;
    }

    public void onClick(View view) {
        if (view != this.k) {
            switch (view.getId()) {
                case R.id.btnSendComment:
                    String trim = this.i.getText().toString().trim();
                    if (trim.equals("")) {
                        g.a("还没有输入文字");
                        return;
                    } else {
                        this.c.a(trim, null, 0);
                        return;
                    }
                default:
                    return;
            }
        } else if (!cn.htjyb.c.a.h(this.a)) {
            cn.htjyb.c.a.a(this.a, this.i);
            h();
            this.c.g_();
        } else if (((h) this.a).q() || (this.d && this.f)) {
            cn.htjyb.c.a.a(this.a, this.i);
        } else {
            h();
            this.c.g_();
        }
    }

    public void d() {
        this.i.setText("");
        cn.htjyb.c.a.a(this.a, this.i);
        h();
    }

    public void a(boolean z, int i, int i2) {
        this.f = z;
        if (this.h != null && this.h.getLayoutParams() != null) {
            LayoutParams layoutParams = (LayoutParams) this.h.getLayoutParams();
            layoutParams.bottomMargin = i;
            this.h.setLayoutParams(layoutParams);
            this.i.setVisibility(0);
        }
    }
}
