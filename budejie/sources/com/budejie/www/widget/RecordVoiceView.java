package com.budejie.www.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.CircleProgressBar;
import com.budejie.www.busevent.DetailAction;
import com.budejie.www.f.b;
import com.budejie.www.h.c;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.util.aw;
import com.budejie.www.util.e;
import com.budejie.www.util.i;
import com.umeng.onlineconfig.OnlineConfigAgent;
import de.greenrobot.event.EventBus;

public class RecordVoiceView extends RelativeLayout implements OnClickListener {
    private long A = 0;
    private String B;
    private int C;
    private String D;
    private int E;
    private String F;
    private boolean G = false;
    private e H;
    private long I = 0;
    private int J = 5;
    private Handler K = new RecordVoiceView$3(this);
    private b L = new RecordVoiceView$4(this);
    private Handler M = new RecordVoiceView$5(this);
    private final String a = "RecordVoiceView";
    private final int b = 0;
    private final int c = 1;
    private final int d = 2;
    private final int e = 3;
    private final int f = 1;
    private final int g = 2;
    private Context h;
    private int i = 0;
    private ImageView j;
    private TextView k;
    private TextView l;
    private VoiceLineView m;
    private VoiceLineView n;
    private LinearLayout o;
    private TextView p;
    private TextView q;
    private CircleProgressBar r;
    private ImageView s;
    private aw t;
    private ac u;
    private int v;
    private View w;
    private Handler x;
    private View y;
    private long z = 0;

    public RecordVoiceView(Context context) {
        super(context);
        this.h = context;
        a();
    }

    public RecordVoiceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = context;
        a();
    }

    public RecordVoiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = context;
        a();
    }

    public void a() {
        this.t = new aw(this.h);
        this.t.a(this.K);
        this.u = ac.a(this.h);
        View inflate = LayoutInflater.from(this.h).inflate(R.layout.include_comment_voice_recoder_layout, null);
        addView(inflate, new LayoutParams(-1, (int) (((float) R$styleable.Theme_Custom_top_navigation_middle) * i.a().b(this.h))));
        inflate.setOnTouchListener(new RecordVoiceView$1(this));
        this.j = (ImageView) inflate.findViewById(R.id.voice_recoder_iv);
        this.k = (TextView) inflate.findViewById(R.id.voice_recoder_normal_tip);
        this.l = (TextView) inflate.findViewById(R.id.voice_recoder_starting_time);
        this.m = (VoiceLineView) inflate.findViewById(R.id.voic_line_right_to_left);
        this.n = (VoiceLineView) inflate.findViewById(R.id.voic_line_left_to_right);
        this.o = (LinearLayout) inflate.findViewById(R.id.voice_recoder_complete);
        this.p = (TextView) inflate.findViewById(R.id.voice_recoder_reset);
        this.q = (TextView) inflate.findViewById(R.id.voice_recoder_send);
        this.r = (CircleProgressBar) inflate.findViewById(R.id.voice_recoder_play_progress);
        this.s = (ImageView) inflate.findViewById(R.id.voice_recoder_cancel);
        this.j.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.s.setOnClickListener(this);
        a(this.i);
    }

    public void a(View view, View view2) {
        this.w = view;
        this.y = view2;
        this.w.setOnTouchListener(new RecordVoiceView$2(this));
    }

    private void a(int i) {
        if (i == 0) {
            if (this.w != null) {
                this.w.setVisibility(8);
            }
            if (this.y != null) {
                this.y.setVisibility(0);
            }
        } else {
            if (this.w != null) {
                this.w.setVisibility(0);
            }
            if (this.y != null) {
                this.y.setVisibility(8);
            }
        }
        TextView textView;
        ac acVar;
        switch (i) {
            case 0:
                this.r.setProgress(0.0f);
                this.z = 0;
                this.v = 0;
                this.I = 0;
                this.G = false;
                this.j.setBackgroundResource(c.a().b(R.attr.comment_recoder_start));
                this.k.setVisibility(0);
                this.l.setVisibility(8);
                this.m.setVisibility(8);
                this.n.setVisibility(8);
                this.o.setVisibility(8);
                this.r.setVisibility(8);
                this.s.setVisibility(8);
                return;
            case 1:
                EventBus.getDefault().post(DetailAction.PUB_VIDEO_CLOSE);
                k.a(this.h).k();
                if (ac.a(this.h).c()) {
                    ac.a(this.h).g();
                }
                this.t.a();
                this.j.setBackgroundResource(c.a().b(R.attr.comment_recoder_stop));
                this.k.setVisibility(8);
                this.l.setVisibility(0);
                textView = this.l;
                acVar = this.u;
                textView.setText(ac.a(0));
                this.m.setVisibility(0);
                this.n.setVisibility(0);
                this.o.setVisibility(8);
                this.r.setVisibility(8);
                this.s.setVisibility(8);
                return;
            case 2:
                if (this.u.c()) {
                    this.u.g();
                    this.u.h();
                    this.r.setProgress(0.0f);
                } else if (!this.t.e()) {
                    this.t.b();
                    this.v = this.t.d();
                }
                aa.b("RecordVoiceView", "RECORDER_STOP mediaDuration=" + this.v);
                this.j.setBackgroundResource(c.a().b(R.attr.comment_recoder_play));
                this.k.setVisibility(8);
                this.l.setVisibility(0);
                textView = this.l;
                acVar = this.u;
                textView.setText(ac.a((long) (this.v * 1000)));
                this.m.setVisibility(8);
                this.n.setVisibility(8);
                this.o.setVisibility(0);
                this.r.setVisibility(8);
                this.s.setVisibility(0);
                return;
            case 3:
                this.u.a(this.L);
                this.u.b(this.t.c().a().getAbsolutePath());
                this.j.setBackgroundResource(c.a().b(R.attr.comment_recoder_stop));
                this.k.setVisibility(8);
                this.l.setVisibility(0);
                this.m.setVisibility(8);
                this.n.setVisibility(8);
                this.o.setVisibility(0);
                this.r.setProgress(0.0f);
                this.r.setVisibility(0);
                this.s.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        Message message;
        switch (view.getId()) {
            case R.id.voice_recoder_cancel:
                if (this.x != null) {
                    message = new Message();
                    message.what = 4;
                    this.x.sendMessage(message);
                    this.i = 0;
                    a(this.i);
                }
                if (this.u != null && this.u.c()) {
                    this.u.i();
                    return;
                }
                return;
            case R.id.voice_recoder_iv:
                if (3 == this.i) {
                    this.i = 2;
                } else {
                    this.i++;
                }
                a(this.i);
                return;
            case R.id.voice_recoder_reset:
                this.i = 0;
                a(this.i);
                return;
            case R.id.voice_recoder_send:
                if (this.x != null) {
                    message = new Message();
                    message.what = 3;
                    message.obj = this.t.c().a();
                    message.arg1 = this.v;
                    if (message.obj != null) {
                        this.x.sendMessage(message);
                        this.i = 0;
                        a(this.i);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void b(int i) {
        this.B = OnlineConfigAgent.getInstance().getConfigParams(this.h, "low_voice_value");
        this.C = 5;
        if (!TextUtils.isEmpty(this.B)) {
            this.C = Integer.parseInt(this.B);
        }
        this.D = OnlineConfigAgent.getInstance().getConfigParams(this.h, "low_voice_warn_time");
        this.E = 3;
        if (!TextUtils.isEmpty(this.D)) {
            this.E = Integer.parseInt(this.D);
        }
        if (i >= this.C) {
            this.z = 0;
            this.I = 0;
            this.G = false;
            aa.a("MicDialog2", "第一次时间清空");
        } else if (this.z == 0) {
            this.z = System.currentTimeMillis();
            aa.a("MicDialog2", "第一次时间：" + this.z);
        } else {
            this.A = System.currentTimeMillis();
            aa.a("MicDialog2", "非第一次时间：" + this.A);
            if (this.A - this.z >= ((long) (this.E * 1000))) {
                this.G = true;
                this.F = OnlineConfigAgent.getInstance().getConfigParams(this.h, "low_voice_warn_msg");
                if (TextUtils.isEmpty(this.F)) {
                    this.F = "声音过小哦~";
                }
                aa.a("MicDialog2", this.F);
                if (this.I == 0) {
                    d();
                } else if (System.currentTimeMillis() - this.I > ((long) (this.J * 1000))) {
                    d();
                }
            }
        }
        if (!this.G && this.H != null) {
            this.H.a();
        }
    }

    private void d() {
        this.H = e.a(this.h, this.F);
        this.H.a(this.J);
        this.I = System.currentTimeMillis();
    }

    public void setRecordVoiceHandler(Handler handler) {
        this.x = handler;
    }

    public void setVisibility(int i) {
        if (8 == i && this.w != null) {
            this.w.setVisibility(8);
        }
        super.setVisibility(i);
    }

    public void b() {
        a(false);
    }

    public void a(boolean z) {
        if (this.i == 1) {
            this.i = 2;
            a(this.i);
        }
        if (z && this.i != 0) {
            this.i = 0;
            a(this.i);
        }
    }

    public void c() {
        if (this.t != null) {
            this.t.b();
        }
        if (this.x != null) {
            this.x.removeCallbacksAndMessages(null);
        }
        if (this.u != null && this.u.c()) {
            this.u.g();
            this.u.h();
            this.r.setProgress(0.0f);
        }
    }
}
