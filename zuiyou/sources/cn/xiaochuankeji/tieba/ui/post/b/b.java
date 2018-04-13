package cn.xiaochuankeji.tieba.ui.post.b;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.j.d;
import cn.xiaochuankeji.tieba.background.utils.e;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundNewVisualView;
import cn.xiaochuankeji.tieba.ui.danmaku.VoiceInputAnimationView;

public class b extends j implements Callback, OnClickListener {
    private RelativeLayout a;
    private RelativeLayout b;
    private ImageView c;
    private TextView d;
    private VoiceInputAnimationView e;
    private RelativeLayout f;
    private RelativeLayout g;
    private TextView h;
    private TextView i;
    private SoundNewVisualView j;
    private boolean k = false;
    private long l;
    private String m;
    private boolean n = false;
    private Handler o;
    private boolean p = false;
    private String q;
    private long r;
    private boolean s = false;
    private a t;

    public interface a {
        void a();

        void a(String str);

        void b();

        void c();
    }

    protected b(Context context, a aVar) {
        super(context);
        this.t = aVar;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 100) {
            l();
            if (!this.p) {
                this.n = true;
            }
        } else if (message.what == 101) {
            int currentTimeMillis = (int) ((System.currentTimeMillis() - this.l) / 1000);
            if (currentTimeMillis > 0) {
                this.d.setText(String.valueOf(currentTimeMillis) + "''");
            }
            this.o.sendEmptyMessageDelayed(101, 200);
        } else if (message.what == 102) {
            long c = d.a().c();
            if (c < 0) {
                this.o.removeMessages(102);
            }
            if (this.j != null) {
                this.j.b((int) c, (int) this.r);
            }
        }
        return true;
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.new_view_input_comment_voice, null);
    }

    protected void a(View view) {
        this.a = (RelativeLayout) view.findViewById(R.id.rootViewVoice);
        this.b = (RelativeLayout) view.findViewById(R.id.rlVoiceInput);
        this.c = (ImageView) view.findViewById(R.id.ivVoiceInput);
        this.d = (TextView) view.findViewById(R.id.ivVoiceTimeCounter);
        this.e = (VoiceInputAnimationView) view.findViewById(R.id.ivVoiceAnimation);
        this.f = (RelativeLayout) view.findViewById(R.id.rlVoiceInputFinished);
        this.h = (TextView) view.findViewById(R.id.label_recognizer_result);
        this.i = (TextView) view.findViewById(R.id.btn_voice_rerecord);
        this.j = (SoundNewVisualView) view.findViewById(R.id.ivVoiceInputPlay);
        this.g = (RelativeLayout) view.findViewById(R.id.rlPlayVoice);
        h();
        i();
    }

    private void h() {
        this.o = new Handler(this);
    }

    private void i() {
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.c.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (this.a.c.getParent() != null && (this.a.c.getParent() instanceof ViewGroup)) {
                    ((ViewGroup) this.a.c.getParent()).requestDisallowInterceptTouchEvent(true);
                }
                if (motionEvent.getAction() == 0) {
                    this.a.n();
                    this.a.l = System.currentTimeMillis();
                    this.a.j();
                } else if (motionEvent.getAction() == 1) {
                    if (this.a.n) {
                        this.a.n = false;
                    } else {
                        this.a.l();
                    }
                } else if (motionEvent.getAction() == 3) {
                    this.a.c.requestFocusFromTouch();
                    motionEvent.setAction(2);
                    this.a.c.onTouchEvent(motionEvent);
                }
                return true;
            }
        });
    }

    private void j() {
        k();
        if (e.a().a(new cn.xiaochuankeji.tieba.background.utils.e.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(String str, String str2) {
                if (!(this.a.r < 1000)) {
                    this.a.k = true;
                }
                this.a.m = str;
                if (TextUtils.isEmpty(str2)) {
                    this.a.a(null);
                    return;
                }
                this.a.q = str2;
                this.a.p = false;
                this.a.h.setText(null);
                if (this.a.t != null) {
                    this.a.t.a(this.a.q);
                }
            }

            public void a(int i, String str) {
                this.a.k = false;
                this.a.m = null;
                this.a.a(str);
            }
        })) {
            this.p = false;
            this.h.setText("语音正在转为文字...");
            this.o.removeMessages(100);
            this.o.sendEmptyMessageDelayed(100, 60000);
            this.o.sendEmptyMessage(101);
            return;
        }
        this.p = true;
        g.a("录音模块初始化失败");
    }

    private void k() {
        Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        e_().sendBroadcast(intent);
    }

    private void a(String str) {
        this.q = "";
        this.p = true;
        this.h.setText(null);
        if (TextUtils.isEmpty(str)) {
            this.h.setText("未能识别，请重录");
            return;
        }
        this.h.setText(null);
        g.a(str);
    }

    private void l() {
        e.a().b();
        this.o.removeMessages(100);
        this.o.removeMessages(101);
        this.r = System.currentTimeMillis() - this.l;
        boolean z = this.r < 1000;
        if (z) {
            g.a("说话时间太短啦");
            this.m = null;
            c();
            this.k = false;
            this.t.c();
        }
        this.d.setVisibility(4);
        this.e.b();
        if (!this.p && !z) {
            m();
        }
    }

    private void m() {
        this.e.setVisibility(8);
        this.b.setVisibility(8);
        this.f.setVisibility(0);
        this.j.setSoundTime((int) (this.r / 1000));
        this.j.a();
        this.t.b();
    }

    private void n() {
        this.c.setSelected(true);
        this.d.setVisibility(0);
        this.d.setText("");
        this.e.setVisibility(0);
        this.e.a();
    }

    public void onClick(View view) {
        if (view.getId() != R.id.ivVoiceInputPlay) {
            d.a().b();
        }
        switch (view.getId()) {
            case R.id.ivVoiceInputPlay:
                if (!TextUtils.isEmpty(this.m)) {
                    if (this.s) {
                        d.a().b();
                        this.s = false;
                        this.j.a();
                        this.o.removeMessages(102);
                        return;
                    }
                    this.j.a(true, 0, this.r / 1000);
                    d.a().a(this.m, new cn.xiaochuankeji.tieba.background.j.d.a(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public void a(String str) {
                            this.a.s = false;
                            this.a.j.a();
                            this.a.o.removeMessages(102);
                        }
                    });
                    this.s = true;
                    this.o.sendEmptyMessageDelayed(102, 0);
                    return;
                }
                return;
            case R.id.btn_voice_rerecord:
                c();
                if (this.t != null) {
                    this.t.a();
                    this.t.b();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void c() {
        this.j.a();
        this.f.setVisibility(8);
        this.b.setVisibility(0);
        this.s = false;
        this.m = null;
        this.k = false;
    }

    public boolean d() {
        return this.k;
    }

    public void e() {
        this.b.setVisibility(8);
        this.f.setVisibility(8);
    }

    public void f() {
        if (this.k) {
            this.f.setVisibility(0);
            this.b.setVisibility(8);
            return;
        }
        this.f.setVisibility(8);
        this.b.setVisibility(0);
    }

    public cn.xiaochuankeji.tieba.background.post.a g() {
        if (!this.k) {
            return null;
        }
        cn.xiaochuankeji.tieba.background.post.a aVar = new cn.xiaochuankeji.tieba.background.post.a();
        aVar.a = this.m;
        aVar.d = (int) (this.r / 1000);
        aVar.b = "wav";
        return aVar;
    }
}
