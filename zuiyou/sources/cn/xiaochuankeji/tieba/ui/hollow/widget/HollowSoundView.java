package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.dreamtobe.kpswitch.widget.KPSwitchFSPanelLinearLayout;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.e;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.hollow.data.MemberDataBean;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.PlayerStatus;
import cn.xiaochuankeji.tieba.ui.hollow.util.c;
import cn.xiaochuankeji.tieba.ui.hollow.widget.a.a;
import com.izuiyou.a.a.b;

public class HollowSoundView extends LinearLayout implements a {
    private Activity A;
    private Handler B;
    private boolean C;
    private boolean D;
    private boolean E;
    private KPSwitchFSPanelLinearLayout a;
    private PressListenerView b;
    private RecordAnimView c;
    private AudioPlayView d;
    private TextView e;
    private TextView f;
    private View g;
    private View h;
    private View i;
    private View j;
    private ImageView k;
    private TextView l;
    private EditText m;
    private Button n;
    private View o;
    private boolean p;
    private a q;
    private e r;
    private String s;
    private PlayerStatus t;
    private IAudioPlayer u;
    private String v;
    private boolean w;
    private long x;
    private long y;
    private boolean z;

    public HollowSoundView(Context context) {
        super(context);
        d();
    }

    public HollowSoundView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public HollowSoundView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    private void d() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_hollow_sound_view, this);
        e();
        f();
        g();
    }

    private void e() {
        this.a = (KPSwitchFSPanelLinearLayout) findViewById(R.id.hollow_reply_switch_ll);
        this.b = (PressListenerView) findViewById(R.id.hollow_reply_record);
        this.d = (AudioPlayView) findViewById(R.id.hollow_reply_play_view);
        this.c = (RecordAnimView) findViewById(R.id.hollow_reply_anim);
        this.e = (TextView) findViewById(R.id.hollow_reply_time);
        this.f = (TextView) findViewById(R.id.hollow_reply_audio_transform);
        this.g = findViewById(R.id.hollow_reply_record_view);
        this.i = findViewById(R.id.hollow_reply_record_info);
        this.h = findViewById(R.id.hollow_reply_reset);
        this.j = findViewById(R.id.hollow_reply_audio_sign);
        this.k = (ImageView) findViewById(R.id.hollow_reply_switch_icon);
        this.l = (TextView) findViewById(R.id.hollow_reply_name);
        this.m = (EditText) findViewById(R.id.hollow_reply_edit);
        this.n = (Button) findViewById(R.id.hollow_reply_send);
        this.o = findViewById(R.id.hollow_reply_fun_name);
    }

    @SuppressLint({"HandlerLeak"})
    private void f() {
        this.u = new cn.xiaochuankeji.tieba.ui.hollow.util.a(getContext());
        this.r = e.a();
        this.B = new Handler(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            @SuppressLint({"SetTextI18n"})
            public void handleMessage(Message message) {
                if (!this.a.z) {
                    this.a.y = System.currentTimeMillis() - this.a.x;
                    b.b("recordDuration : " + this.a.y);
                    if (this.a.y / 1000 >= 60) {
                        this.a.e.setText(c.b(60));
                        g.a("录制时长已满");
                        this.a.i();
                        return;
                    }
                    this.a.e.setText(c.b(this.a.y / 1000));
                    if (this.a.B != null) {
                        this.a.B.sendMessageDelayed(this.a.B.obtainMessage(0), 1000);
                    }
                }
            }
        };
        this.C = true;
        this.z = false;
        this.w = true;
        this.A = (Activity) getContext();
        this.v = null;
        this.y = 0;
        this.D = false;
        this.E = true;
    }

    private void g() {
        this.b.setOnPressListener(new PressListenerView.a(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            public void a() {
                if (cn.xiaochuankeji.tieba.background.utils.c.a()) {
                    this.a.h();
                    this.a.C = true;
                    return;
                }
                g.a("录音失败，请检查权限是否开启");
                this.a.C = false;
            }

            public void b() {
                if (this.a.C) {
                    this.a.i();
                }
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.v = this.a.r.c();
                if (this.a.w) {
                    this.a.j();
                } else {
                    this.a.k();
                }
            }
        });
        this.d.a(true, 1.0f, 0.7f);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.htjyb.c.a.b.a(this.a.r.c());
                this.a.l();
                if (this.a.u != null) {
                    this.a.u.d();
                }
            }
        });
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.v = this.a.r.c();
                this.a.m();
            }
        });
        this.m.requestFocus();
    }

    @SuppressLint({"SetTextI18n"})
    private void h() {
        this.x = System.currentTimeMillis();
        this.z = false;
        this.D = true;
        this.E = false;
        this.s = this.m.getText().toString();
        this.r.a(new e.a(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            public void a(String str, String str2) {
                if (str2 == null || this.a.E) {
                    this.a.m.setText(this.a.s);
                    this.a.f.setVisibility(0);
                    this.a.f.setText("未能识别到文字");
                    return;
                }
                this.a.m.setText(this.a.s + str2);
                this.a.f.setVisibility(8);
            }

            public void a(int i, String str) {
                this.a.f.setVisibility(0);
                this.a.f.setText("未能识别到文字");
            }
        });
        if (this.B != null) {
            this.B.sendMessageDelayed(this.B.obtainMessage(0), 1000);
        }
        if (this.q != null) {
            this.q.a(true);
        }
        this.i.setVisibility(8);
        a(true);
    }

    private void i() {
        a(false);
        this.r.b();
        this.g.setVisibility(8);
        this.e.setVisibility(4);
        this.f.setVisibility(0);
        this.h.setVisibility(0);
        this.d.setVisibility(0);
        this.h.setEnabled(true);
        b.b("setPlayDuration : " + (this.y / 1000));
        this.d.setPlayDuration(c.a(this.y));
        this.D = false;
        this.z = true;
        if (this.q != null) {
            this.q.a(false);
        }
        this.v = this.r.c();
    }

    private void a(boolean z) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), z ? R.anim.alpha_in : R.anim.alpha_out);
        this.c.setVisibility(z ? 0 : 4);
        this.c.startAnimation(loadAnimation);
        if (z) {
            this.c.a();
        } else {
            this.c.b();
        }
    }

    private void j() {
        if (this.v == null) {
            g.a("语音回复生成中，请稍等...");
            return;
        }
        this.u.a(this.v, c.a(this.y), new IAudioPlayer.a(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            public void a(PlayerStatus playerStatus) {
                this.a.t = playerStatus;
                switch (playerStatus) {
                    case LOADING:
                        this.a.d.a();
                        return;
                    case PLAYING:
                        this.a.d.b();
                        return;
                    case PAUSE:
                        this.a.d.d();
                        return;
                    case END:
                        this.a.d.c();
                        return;
                    default:
                        return;
                }
            }

            public void a(final long j) {
                this.a.A.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 b;

                    public void run() {
                        b.b("refreshPlayTime -> remainTime : " + j);
                        this.b.a.d.a(j);
                    }
                });
            }
        });
        this.w = false;
        k();
    }

    private void k() {
        if (this.t != null) {
            switch (this.t) {
                case LOADING:
                case PLAYING:
                    this.u.b();
                    return;
                case PAUSE:
                    this.u.c();
                    return;
                case END:
                case PREPARE:
                    this.u.a();
                    return;
                default:
                    return;
            }
        }
    }

    private void l() {
        this.g.setVisibility(0);
        this.i.setVisibility(0);
        this.e.setVisibility(0);
        this.h.setVisibility(4);
        this.h.setEnabled(false);
        this.f.setVisibility(8);
        this.d.setVisibility(8);
        this.m.setText(this.s);
        this.e.setText("");
        this.y = 0;
        this.v = null;
        this.E = true;
        this.w = true;
    }

    private void m() {
        String obj = this.m.getText().toString();
        if ((this.y == 0 && TextUtils.isEmpty(obj)) || (this.y == 0 && a(obj))) {
            String str;
            if (this.E) {
                str = "还没输入任何内容";
            } else {
                str = "语音回复生成中，请稍等...";
            }
            g.a(str);
        } else if (this.q != null) {
            this.E = true;
            if (this.y == 0) {
                this.q.a(obj, null, 0, new a.b(this) {
                    final /* synthetic */ HollowSoundView a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.m.setText("");
                        this.a.l();
                        this.a.n();
                    }

                    public void b() {
                        this.a.n();
                    }
                });
            } else if (c.a(this.y) < 1) {
                g.a("音频录制时间太短");
            } else {
                this.q.a(obj, this.v, c.a(this.y), new a.b(this) {
                    final /* synthetic */ HollowSoundView a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.m.setText("");
                        this.a.l();
                        this.a.n();
                    }

                    public void b() {
                        this.a.n();
                    }
                });
            }
        }
    }

    private boolean a(String str) {
        for (char c : str.toCharArray()) {
            if (c != ' ') {
                return false;
            }
        }
        return true;
    }

    private void b(boolean z) {
        int i = 8;
        if (this.p) {
            View view = this.o;
            if (z) {
                i = 0;
            }
            view.setVisibility(i);
            return;
        }
        this.o.setVisibility(8);
    }

    private void c(boolean z) {
        this.k.setImageResource(z ? R.drawable.ic_keyboard : R.drawable.ic_record_voice);
        View view = this.j;
        int i = z ? 8 : this.y > 0 ? 0 : 8;
        view.setVisibility(i);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || this.a.getVisibility() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        n();
        return true;
    }

    private void n() {
        cn.dreamtobe.kpswitch.b.a.b(this.a);
        b(false);
        c(false);
        this.m.requestFocus();
    }

    public void a(Activity activity) {
        b.b("hideSoftInput");
        cn.htjyb.c.a.a(activity);
        n();
    }

    public void b(final Activity activity) {
        cn.dreamtobe.kpswitch.b.c.a(activity, this.a, new cn.dreamtobe.kpswitch.b.c.b(this) {
            final /* synthetic */ HollowSoundView a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                boolean z2;
                boolean z3 = true;
                HollowSoundView hollowSoundView = this.a;
                if (z || this.a.a.getVisibility() == 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                hollowSoundView.b(z2);
                HollowSoundView hollowSoundView2 = this.a;
                if (z || this.a.a.getVisibility() != 0) {
                    z3 = false;
                }
                hollowSoundView2.c(z3);
            }
        });
        cn.dreamtobe.kpswitch.b.a.a(this.a, this.k, this.m, new cn.dreamtobe.kpswitch.b.a.a(this) {
            final /* synthetic */ HollowSoundView b;

            public void a(boolean z) {
                if (z) {
                    PermissionItem permissionItem = new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO");
                    permissionItem.runIgnorePermission = false;
                    permissionItem.settingText = "设置";
                    permissionItem.deniedMessage = "开启以下权限才能正常发布语音内容";
                    permissionItem.needGotoSetting = true;
                    cn.xiaochuankeji.aop.permission.a.a(activity).a(permissionItem, new cn.xiaochuankeji.aop.permission.e(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void permissionGranted() {
                            this.a.b.b(true);
                            this.a.b.c(true);
                            this.a.b.m.clearFocus();
                        }

                        public void permissionDenied() {
                            g.a("开启以下权限才能正常发布语音内容");
                            this.a.b.n();
                        }
                    });
                    return;
                }
                this.b.m.requestFocus();
            }
        });
    }

    @SuppressLint({"SetTextI18n"})
    public void setUserData(MemberDataBean memberDataBean) {
        if (memberDataBean != null) {
            this.l.setText("花名：" + memberDataBean.name);
            this.p = true;
            return;
        }
        this.p = false;
    }

    public void a() {
        if (this.t != null && this.t.equals(PlayerStatus.PLAYING)) {
            this.u.b();
        }
        if (this.D) {
            i();
        }
    }

    public void b() {
        a(false);
        this.u.e();
        this.B.removeMessages(0);
        this.B = null;
    }

    public void setOnSendClickListener(a aVar) {
        this.q = aVar;
    }

    public void c() {
        if (this.u != null) {
            this.u.e();
        }
    }
}
