package cn.xiaochuankeji.tieba.ui.voice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.aop.permission.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.screen.Observer;
import cn.xiaochuankeji.tieba.background.screen.Observer.ScreenStatus;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.hollow.util.c;
import cn.xiaochuankeji.tieba.ui.hollow.widget.TouchListenerLayout;
import cn.xiaochuankeji.tieba.ui.voice.widget.AudioWaveView;
import cn.xiaochuankeji.tieba.ui.widget.f;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.d;
import rx.d$a;
import rx.j;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnPreparedListener;

@b(a = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, b = "正常录音需要录音和写文件权限", d = "确认", f = "拒绝该权限后无法正常录音", h = "拒绝", j = "设置权限", l = true, m = false)
public class VoiceCreateActivity extends a implements Observer, cn.xiaochuankeji.tieba.ui.voice.c.b.a, IMediaPlayer$OnCompletionListener, IMediaPlayer$OnPreparedListener {
    private cn.xiaochuankeji.tieba.ui.voice.c.b a;
    private cn.xiaochuankeji.tieba.common.d.a b;
    private List<cn.xiaochuankeji.tieba.ui.voice.c.a> c = new ArrayList();
    private cn.xiaochuankeji.tieba.ui.voice.c.a d;
    private String e = "无法录音，请检查权限";
    @BindView
    EditText et_content;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    @BindView
    ImageView iv_delete;
    @BindView
    ImageView iv_play;
    @BindView
    ImageView iv_record;
    private volatile boolean j;
    private long k;
    private long l;
    private long m;
    @BindView
    AudioWaveView mWaveView;
    private KeyListener n;
    private long o;
    private boolean p = false;
    private long q = 0;
    private int r = 0;
    @SuppressLint({"HandlerLeak"})
    private Handler s = new Handler(this) {
        final /* synthetic */ VoiceCreateActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.m = (this.a.l + System.currentTimeMillis()) - this.a.k;
            this.a.tv_time.setText(c.a((int) (this.a.m / 1000)));
            if (this.a.m >= 300000) {
                this.a.j();
            } else {
                this.a.s.sendMessageDelayed(this.a.s.obtainMessage(0), 1000);
            }
        }
    };
    @SuppressLint({"HandlerLeak"})
    private Handler t = new Handler(this) {
        final /* synthetic */ VoiceCreateActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (this.a.i) {
                this.a.r = this.a.r + 1;
                long currentTimeMillis = System.currentTimeMillis() - this.a.k;
                if (this.a.r >= 25) {
                    this.a.tv_time.setText(c.a(((int) (this.a.q + currentTimeMillis)) / 1000));
                    this.a.r = 0;
                }
                this.a.mWaveView.setProgress(((float) (currentTimeMillis + this.a.q)) / ((float) this.a.l));
                this.a.t.sendMessageDelayed(this.a.t.obtainMessage(1), 40);
            }
        }
    };
    @BindView
    TouchListenerLayout touchListenerLayout;
    @BindView
    TextView tv_next;
    @BindView
    TextView tv_time;
    @BindView
    TextView tv_tip;

    public static void a(Context context) {
        context.startActivity(new Intent(context, VoiceCreateActivity.class));
    }

    public static void a(Context context, String str, long j) {
        Intent intent = new Intent(context, VoiceCreateActivity.class);
        intent.putExtra("topicName", str);
        intent.putExtra("topicId", j);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_voice_record;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        getWindow().setFlags(128, 128);
        this.et_content.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VoiceCreateActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.et_content.setCursorVisible(true);
            }
        });
        this.n = this.et_content.getKeyListener();
    }

    protected boolean a(Bundle bundle) {
        this.a = new cn.xiaochuankeji.tieba.ui.voice.c.b(new cn.xiaochuankeji.tieba.common.medialib.a(16000, 1, 2));
        this.a.a(new cn.xiaochuankeji.tieba.common.medialib.b.b(this) {
            final /* synthetic */ VoiceCreateActivity a;

            {
                this.a = r1;
            }

            public void a(Throwable th) {
                if (th instanceof SecurityException) {
                    g.a(this.a.e);
                    this.a.e();
                    return;
                }
                g.a(th.getMessage());
            }
        });
        this.f = cn.xiaochuankeji.tieba.background.a.e().w();
        this.a.a((cn.xiaochuankeji.tieba.ui.voice.c.b.a) this);
        this.b = new cn.xiaochuankeji.tieba.common.d.a(this);
        this.b.a((IMediaPlayer$OnPreparedListener) this);
        this.b.a((IMediaPlayer$OnCompletionListener) this);
        cn.xiaochuankeji.tieba.background.screen.a.a().a((Observer) this, (Context) this);
        return true;
    }

    private void e() {
        int currentTimeMillis = (int) (System.currentTimeMillis() - this.k);
        this.a.b(currentTimeMillis);
        this.l = 0;
        this.tv_time.setText(c.a(0));
        this.m = this.l;
        this.d.b = (long) currentTimeMillis;
        this.h = false;
        w();
        this.c.add(this.d);
        this.mWaveView.b();
        this.s.removeMessages(0);
        this.b.g();
        this.k = 0;
        this.c.clear();
        this.mWaveView.d();
        u();
    }

    @l(a = ThreadMode.MAIN)
    public void finishAcivity(cn.xiaochuankeji.tieba.ui.voice.a.c cVar) {
        if (cVar != null) {
            finish();
        }
    }

    @OnClick
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (!z()) {
                    finish();
                    return;
                }
                return;
            case R.id.tv_time:
                cn.htjyb.c.a.a((Activity) this);
                return;
            case R.id.iv_play:
                k();
                return;
            case R.id.tv_next:
                p();
                return;
            case R.id.iv_delete:
                if (!this.c.isEmpty()) {
                    r();
                    return;
                }
                return;
            case R.id.iv_record:
                j();
                return;
            default:
                return;
        }
    }

    private void j() {
        if (this.l >= 300000) {
            g.a("录制时间最长5分钟");
            return;
        }
        int currentTimeMillis;
        if (this.p) {
            this.mWaveView.setProgress(1.0f);
            this.p = false;
            this.tv_time.setText(c.a((int) (this.m / 1000)));
        }
        if (this.h) {
            currentTimeMillis = (int) (System.currentTimeMillis() - this.k);
            this.a.a(currentTimeMillis);
            this.l += (long) currentTimeMillis;
            this.tv_time.setText(c.a(((int) this.l) / 1000));
            this.m = this.l;
            this.d.b = (long) currentTimeMillis;
            this.h = false;
            w();
            this.c.add(this.d);
            this.mWaveView.b();
            this.s.removeMessages(0);
            this.b.g();
        } else {
            this.d = new cn.xiaochuankeji.tieba.ui.voice.c.a();
            this.d.a = this.f + System.currentTimeMillis() + ".wav";
            v();
            this.k = System.currentTimeMillis();
            this.a.a(this.d.a, 1.0f, new cn.xiaochuankeji.tieba.common.medialib.b.a(this) {
                final /* synthetic */ VoiceCreateActivity a;

                {
                    this.a = r1;
                }

                public long a() {
                    return 0;
                }
            });
            this.h = true;
            this.j = false;
        }
        TextView textView = this.tv_next;
        if (this.h) {
            currentTimeMillis = getResources().getColor(R.color.CT_6);
        } else {
            currentTimeMillis = getResources().getColor(R.color.CM);
        }
        textView.setTextColor(currentTimeMillis);
        this.et_content.setKeyListener(this.h ? null : this.n);
        this.r = 0;
        this.q = 0;
    }

    private void k() {
        if (this.i) {
            this.i = false;
            y();
            this.b.pause();
            this.t.removeMessages(1);
            this.q = (System.currentTimeMillis() - this.k) + this.q;
        } else {
            this.i = true;
            if (this.c.size() == 1) {
                this.b.a(((cn.xiaochuankeji.tieba.ui.voice.c.a) this.c.get(0)).a);
                this.b.start();
                this.t.sendMessageDelayed(this.t.obtainMessage(1), 40);
                x();
            } else if (this.c.size() > 1) {
                if (this.j) {
                    this.b.a(this.g);
                    this.b.start();
                    this.mWaveView.a();
                    this.t.sendMessageDelayed(this.t.obtainMessage(1), 40);
                    x();
                } else {
                    s();
                }
            }
            this.k = System.currentTimeMillis();
            if (this.q == 0) {
                this.tv_time.setText(c.a(0));
            }
        }
        this.p = true;
    }

    private void p() {
        if (!this.h) {
            if (this.l < 5000) {
                g.a("录音时间要大于5秒哦");
                return;
            }
            final String obj = this.et_content.getText().toString();
            if (obj.length() < 3) {
                g.a("不能少于三个字");
            } else if (this.h) {
                g.a("正在录音！");
            } else if (this.c.size() == 0) {
                g.a("没有录音");
            } else if (this.j) {
                String str = this.g;
                if (this.i) {
                    k();
                }
                VoicePublishV2Activity.a(this, obj, str, this.l, getIntent().getLongExtra("topicId", 0), getIntent().getStringExtra("topicName"), this.o);
            } else {
                cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
                t().b(new j<String>(this) {
                    final /* synthetic */ VoiceCreateActivity b;

                    public /* synthetic */ void onNext(Object obj) {
                        a((String) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.b);
                    }

                    public void a(String str) {
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.b);
                        if (this.b.i) {
                            this.b.k();
                        }
                        VoicePublishV2Activity.a(this.b, obj, this.b.g, this.b.l, this.b.getIntent().getLongExtra("topicId", 0), this.b.getIntent().getStringExtra("topicName"));
                    }
                });
            }
        }
    }

    private void q() {
        if (this.c != null && !this.c.isEmpty()) {
            for (cn.xiaochuankeji.tieba.ui.voice.c.a aVar : this.c) {
                new File(aVar.a).delete();
            }
            this.c.clear();
        }
    }

    private void r() {
        if (this.p) {
            this.mWaveView.setProgress(1.0f);
            this.p = false;
        }
        f.a("删除", "确认删除上一段音频？", this, new f.a(this) {
            final /* synthetic */ VoiceCreateActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    cn.xiaochuankeji.tieba.ui.voice.c.a aVar = (cn.xiaochuankeji.tieba.ui.voice.c.a) this.a.c.get(this.a.c.size() - 1);
                    this.a.m = this.a.m - aVar.b;
                    this.a.l = this.a.l - aVar.b;
                    if (this.a.m < 0) {
                        this.a.m = 0;
                    }
                    this.a.tv_time.setText(c.a((int) (this.a.m / 1000)));
                    this.a.c.remove(aVar);
                    this.a.j = false;
                    this.a.mWaveView.c();
                    this.a.b.g();
                    if (this.a.c.isEmpty()) {
                        this.a.u();
                    }
                    this.a.q = 0;
                    this.a.r = 0;
                }
            }
        }, true);
    }

    private void s() {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        t().b(new j<String>(this) {
            final /* synthetic */ VoiceCreateActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((String) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
            }

            public void a(String str) {
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                this.a.b.a(str);
                this.a.b.start();
                this.a.mWaveView.a();
                this.a.t.sendMessageDelayed(this.a.t.obtainMessage(1), 40);
                this.a.x();
            }
        });
    }

    private d<String> t() {
        this.g = this.f + "output.wav";
        return d.b(new d$a<String>(this) {
            final /* synthetic */ VoiceCreateActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super String> jVar) {
                Collection arrayList = new ArrayList();
                for (cn.xiaochuankeji.tieba.ui.voice.c.a aVar : this.a.c) {
                    arrayList.add(aVar.a);
                }
                FFmpegMainCaller.concatMedia(new ArrayList(arrayList), this.a.g, this.a.f + "/mux.txt");
                jVar.onNext(this.a.g);
                this.a.j = true;
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a());
    }

    private void u() {
        this.iv_play.setVisibility(4);
        this.iv_delete.setVisibility(4);
        this.iv_record.setImageResource(R.drawable.voice_record);
        this.tv_tip.setVisibility(0);
        this.tv_tip.setText("点击录音");
    }

    private void v() {
        this.iv_play.setVisibility(4);
        this.iv_delete.setVisibility(4);
        this.iv_record.setImageResource(R.drawable.voice_record_stop);
        this.tv_tip.setVisibility(0);
        this.tv_tip.setText("点击暂停");
    }

    private void w() {
        if (this.c == null || this.c.size() == 0) {
            u();
        }
        this.iv_play.setVisibility(0);
        this.iv_delete.setVisibility(0);
        this.iv_play.setImageResource(R.drawable.voice_record_play);
        this.iv_delete.setImageResource(R.drawable.voice_record_delete);
        this.iv_record.setImageResource(R.drawable.voice_record);
        this.iv_delete.setClickable(true);
        this.iv_record.setClickable(true);
        this.tv_tip.setVisibility(0);
        this.tv_tip.setText("继续录音");
    }

    private void x() {
        this.iv_play.setVisibility(0);
        this.iv_delete.setVisibility(0);
        this.iv_play.setImageResource(R.drawable.voice_record_pause);
        this.iv_delete.setImageResource(R.drawable.voice_record_delete_disable);
        this.iv_record.setImageResource(R.drawable.voice_record_disabled);
        this.iv_delete.setClickable(false);
        this.iv_record.setClickable(false);
        this.tv_tip.setVisibility(4);
    }

    private void y() {
        this.iv_play.setVisibility(0);
        this.iv_delete.setVisibility(0);
        this.iv_play.setImageResource(R.drawable.voice_record_play);
        this.iv_delete.setImageResource(R.drawable.voice_record_delete);
        this.iv_record.setImageResource(R.drawable.voice_record);
        this.iv_delete.setClickable(true);
        this.iv_record.setClickable(true);
        this.tv_tip.setVisibility(0);
        this.tv_tip.setText("继续录音");
    }

    public void a(int i) {
        this.mWaveView.a(i);
    }

    public void a(long j) {
        this.k = j;
        this.s.sendMessageDelayed(this.s.obtainMessage(0), 1000);
    }

    protected void onPause() {
        super.onPause();
        if (this.h) {
            j();
        }
        if (this.i) {
            k();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a.a()) {
            if (this.k == 0) {
                this.a.a(0);
            } else {
                this.a.a((int) (System.currentTimeMillis() - this.k));
            }
        }
        this.s.removeMessages(0);
        this.t.removeMessages(1);
        this.b.g();
        this.a.b();
        cn.xiaochuankeji.tieba.background.screen.a.a().b(this, this);
    }

    public void onPrepared(IMediaPlayer iMediaPlayer) {
        this.t.sendMessageDelayed(this.t.obtainMessage(1), 40);
        this.b.start();
    }

    public void onCompletion(IMediaPlayer iMediaPlayer) {
        this.mWaveView.setProgress(1.0f);
        this.t.removeMessages(1);
        this.tv_time.setText(c.a(((int) this.l) / 1000));
        this.q = 0;
        this.r = 0;
        this.i = false;
        w();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 26) {
            if (this.h) {
                j();
            }
            if (this.i) {
                k();
            }
            return super.onKeyDown(i, keyEvent);
        } else if (i == 4) {
            return z() || super.onKeyDown(i, keyEvent);
        } else {
            return super.onKeyDown(i, keyEvent);
        }
    }

    public boolean h() {
        return false;
    }

    private boolean z() {
        if (this.h) {
            j();
        }
        if (this.i) {
            k();
        }
        if (this.l <= 0 && TextUtils.isEmpty(this.et_content.getText())) {
            return false;
        }
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(this, "提示", "你要放弃发表吗？").b("继续编辑", null).a("放弃", new OnClickListener(this) {
            final /* synthetic */ VoiceCreateActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.q();
                this.a.finish();
            }
        }).a();
        return true;
    }

    @l(a = ThreadMode.MAIN)
    public void addPost(cn.xiaochuankeji.tieba.ui.voice.a.c cVar) {
        q();
    }

    @l
    public void saveTempCoverUrl(cn.xiaochuankeji.tieba.ui.voice.a.d dVar) {
        this.o = dVar.b;
    }

    public void a(ScreenStatus screenStatus) {
        switch (screenStatus) {
            case SCREEN_OFF:
                if (this.h) {
                    j();
                }
                if (this.i) {
                    k();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
