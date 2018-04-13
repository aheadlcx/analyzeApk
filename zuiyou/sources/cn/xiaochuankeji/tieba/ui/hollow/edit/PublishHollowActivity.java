package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.PlayerStatus;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioRecorder;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioRecorder.RecorderStatus;
import cn.xiaochuankeji.tieba.ui.hollow.widget.RecordAnimView;
import cn.xiaochuankeji.tieba.ui.hollow.widget.TouchListenerLayout;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;
import rx.j;

public class PublishHollowActivity extends h implements OnClickListener {
    private static final a t = null;
    private RecordAnimView d;
    private NavigationBar e;
    private ImageView f;
    private TextView g;
    private View h;
    private View i;
    private TextView j;
    private View k;
    private View l;
    private View m;
    private PlayerStatus n;
    private IAudioPlayer o;
    private boolean p;
    private RecorderStatus q;
    private IAudioRecorder r;
    private long s;

    static {
        E();
    }

    private static void E() {
        b bVar = new b("PublishHollowActivity.java", PublishHollowActivity.class);
        t = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.hollow.edit.PublishHollowActivity", "android.os.Bundle", "savedInstance", "", "void"), 70);
    }

    static final void a(PublishHollowActivity publishHollowActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(t, this, this, bundle);
        c.c().a(new c(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onResume() {
        super.onResume();
    }

    protected int a() {
        return R.layout.activity_edit_start;
    }

    protected void i_() {
        j();
        k();
        v();
        z();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            finish();
        }
    }

    public boolean h() {
        return false;
    }

    private void j() {
        this.e = (NavigationBar) findViewById(R.id.navBar);
        this.e.a(-1285, 0, 0);
        this.e.b();
        this.e.c();
        this.e.setOptionText("下一步");
        this.e.getOptionText().setTextColor(-686198);
        f(false);
    }

    private void k() {
        this.k = findViewById(R.id.hollow_edit_ll_welcome);
        this.m = findViewById(R.id.hollow_edit_text_layout);
        this.l = findViewById(R.id.hollow_edit_ll_time);
        this.j = (TextView) findViewById(R.id.hollow_edit_time);
        this.f = (ImageView) findViewById(R.id.hollow_edit_bt_main);
        this.h = findViewById(R.id.hollow_edit_bt_reset);
        this.i = findViewById(R.id.hollow_edit_bt_play);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.d = (RecordAnimView) findViewById(R.id.hollow_edit_anim);
        this.g = (TextView) findViewById(R.id.hollow_edit_tx_play);
        findViewById(R.id.hollow_edit_text_next).setOnClickListener(this);
        ((TouchListenerLayout) findViewById(R.id.hollow_edit_listener_layout)).setOnPressListener(new cn.xiaochuankeji.tieba.ui.hollow.widget.b(this) {
            final /* synthetic */ PublishHollowActivity a;

            {
                this.a = r1;
            }

            public void b() {
                super.b();
                if (this.a.q != null && this.a.q.equals(RecorderStatus.PREPARE)) {
                    EditHollowActivity.a(this.a, null, 0);
                }
            }
        });
    }

    private void v() {
        this.r = new cn.xiaochuankeji.tieba.ui.hollow.util.b();
        this.r.a(new IAudioRecorder.a(this) {
            final /* synthetic */ PublishHollowActivity a;

            {
                this.a = r1;
            }

            public void a(RecorderStatus recorderStatus) {
                this.a.q = recorderStatus;
                switch (recorderStatus) {
                    case PREPARE:
                        this.a.w();
                        return;
                    case RECORDING:
                        this.a.x();
                        return;
                    case FILLED:
                        g.a("录制时长已满");
                        this.a.s();
                        return;
                    case PAUSE:
                        this.a.y();
                        return;
                    default:
                        return;
                }
            }

            public void a(long j) {
                this.a.j.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.b(j));
                this.a.s = j;
            }
        });
        this.r.a(90);
        this.s = 0;
        this.j.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.b(0));
    }

    private void w() {
        e(false);
        d(false);
        f(false);
        this.j.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.b(0));
        this.f.setImageResource(R.drawable.record);
        this.s = 0;
    }

    private void x() {
        e(false);
        d(true);
        f(false);
        this.d.a();
    }

    private void y() {
        e(true);
        f(true);
        this.d.b();
    }

    private void z() {
        this.o = new cn.xiaochuankeji.tieba.ui.hollow.util.a(this);
        this.p = true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hollow_edit_bt_reset:
                this.r.c();
                return;
            case R.id.hollow_edit_bt_main:
                A();
                return;
            case R.id.hollow_edit_bt_play:
                B();
                return;
            case R.id.hollow_edit_text_next:
                EditHollowActivity.a(this, null, 0);
                return;
            default:
                return;
        }
    }

    private void A() {
        switch (this.q) {
            case PREPARE:
                PermissionItem permissionItem = new PermissionItem("android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE");
                permissionItem.runIgnorePermission = false;
                permissionItem.settingText = "设置";
                permissionItem.deniedMessage = "开启以下权限才能正常发布语音内容";
                permissionItem.needGotoSetting = true;
                cn.xiaochuankeji.aop.permission.a.a((Context) this).a(permissionItem, new e(this) {
                    final /* synthetic */ PublishHollowActivity a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        this.a.r.a();
                        this.a.p = true;
                    }

                    public void permissionDenied() {
                        g.a("开启以下权限才能正常发布语音内容");
                    }
                });
                return;
            case RECORDING:
                this.r.b();
                return;
            case FILLED:
                g.a("录制时长已满");
                return;
            case PAUSE:
                this.r.a();
                this.p = true;
                return;
            default:
                return;
        }
    }

    private void B() {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        if (this.p) {
            this.r.e().a(rx.a.b.a.a()).b(new j<String>(this) {
                final /* synthetic */ PublishHollowActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((String) obj);
                }

                public void onCompleted() {
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                }

                public void onError(Throwable th) {
                    g.a(th.getMessage());
                    com.izuiyou.a.a.b.e(th);
                }

                public void a(String str) {
                    this.a.a(str);
                }
            });
            return;
        }
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        C();
    }

    private void a(String str) {
        this.o.a(str, this.s, new IAudioPlayer.a(this) {
            final /* synthetic */ PublishHollowActivity a;

            {
                this.a = r1;
            }

            public void a(PlayerStatus playerStatus) {
                this.a.n = playerStatus;
                switch (playerStatus) {
                    case PLAYING:
                        this.a.c(true);
                        this.a.d.a();
                        this.a.f(false);
                        return;
                    case PAUSE:
                        this.a.c(false);
                        this.a.d.b();
                        this.a.f(true);
                        return;
                    case END:
                        this.a.c(false);
                        this.a.d.b();
                        this.a.f(true);
                        this.a.j.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.b(this.a.s));
                        return;
                    default:
                        return;
                }
            }

            public void a(final long j) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 b;

                    public void run() {
                        this.b.a.j.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.b(j));
                    }
                });
            }
        });
        C();
    }

    private void C() {
        switch (this.n) {
            case PLAYING:
            case LOADING:
                this.o.b();
                return;
            case PAUSE:
                this.o.c();
                return;
            case END:
            case PREPARE:
                this.o.a();
                this.p = false;
                return;
            default:
                return;
        }
    }

    private void c(boolean z) {
        boolean z2;
        float f;
        boolean z3 = true;
        float f2 = 0.4f;
        this.g.setText(z ? "暂停" : "试听");
        View view = this.h;
        if (z) {
            z2 = false;
        } else {
            z2 = true;
        }
        view.setEnabled(z2);
        ImageView imageView = this.f;
        if (z) {
            z3 = false;
        }
        imageView.setEnabled(z3);
        View view2 = this.h;
        if (z) {
            f = 0.4f;
        } else {
            f = 1.0f;
        }
        view2.setAlpha(f);
        imageView = this.f;
        if (!z) {
            f2 = 1.0f;
        }
        imageView.setAlpha(f2);
    }

    private void d(boolean z) {
        int i;
        int i2 = 8;
        View view = this.k;
        if (z) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        view = this.l;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.m;
        if (!z) {
            i2 = 0;
        }
        view2.setVisibility(i2);
    }

    private void e(boolean z) {
        int i = 0;
        this.h.setVisibility(z ? 0 : 4);
        View view = this.i;
        if (!z) {
            i = 4;
        }
        view.setVisibility(i);
        this.h.setEnabled(z);
        this.i.setEnabled(z);
        this.f.setImageResource(z ? R.drawable.record : R.drawable.record_pause);
    }

    private void f(boolean z) {
        this.e.setOptionTxtVisibility(z ? 0 : 8);
    }

    public void s() {
        if (this.s < 5) {
            g.a("据说走心的树洞倾诉都大于5秒");
        } else {
            this.r.e().a(new rx.b.b<String>(this) {
                final /* synthetic */ PublishHollowActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((String) obj);
                }

                public void a(String str) {
                    EditHollowActivity.a(this.a, str, this.a.s);
                }
            }, new rx.b.b<Throwable>(this) {
                final /* synthetic */ PublishHollowActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((Throwable) obj);
                }

                public void a(Throwable th) {
                    g.a(th.getMessage());
                }
            });
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.q != null && this.q == RecorderStatus.RECORDING) {
            this.r.b();
        }
        if (this.n != null && this.n.equals(PlayerStatus.PLAYING)) {
            this.o.b();
        }
    }

    protected void onStop() {
        super.onStop();
        this.o.d();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.r.d();
        this.o.e();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return D() || super.onKeyDown(i, keyEvent);
        } else {
            return super.onKeyDown(i, keyEvent);
        }
    }

    public void r() {
        if (!D()) {
            super.r();
        }
    }

    private boolean D() {
        if (this.s <= 0) {
            return false;
        }
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(this, "提示", "你要放弃发表吗？").b("继续编辑", null).a("放弃", new OnClickListener(this) {
            final /* synthetic */ PublishHollowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        }).a();
        return true;
    }
}
