package cn.xiaochuankeji.tieba.widget.record;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.chat.a.c;
import cn.xiaochuankeji.tieba.ui.voice.c.b;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ChatRecordLayout extends RelativeLayout implements OnTouchListener {
    private a a;
    private AppCompatTextView b;
    private AppCompatTextView c;
    private View d;
    private b e;
    private File f;
    private a g;
    private View h;
    private float i;
    private RelativeLayout j;
    private boolean k;
    private c l;
    private long m;
    private long n;

    private class a extends CountDownTimer {
        final /* synthetic */ ChatRecordLayout a;

        a(ChatRecordLayout chatRecordLayout, long j, long j2) {
            this.a = chatRecordLayout;
            super(j, j2);
        }

        public void onTick(long j) {
            com.izuiyou.a.a.b.c(Long.valueOf(j));
            if (j >= 990 && j < 6000 && !this.a.j.isShown()) {
                this.a.d.setVisibility(0);
                this.a.b.setVisibility(0);
                this.a.b.setText(String.valueOf((int) (j / 1000)));
            }
        }

        public void onFinish() {
            this.a.d.setVisibility(0);
            this.a.c.setVisibility(0);
            this.a.b.setVisibility(8);
            rx.a.b.a.a().a().a(new rx.b.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void call() {
                    this.a.a.g();
                }
            }, 500, TimeUnit.MILLISECONDS);
        }
    }

    public ChatRecordLayout(Context context) {
        super(context);
        d();
    }

    public ChatRecordLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public ChatRecordLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    private void d() {
        View.inflate(getContext(), R.layout.view_voice_layout, this);
        this.a = new a(this, 60000, 950);
        this.d = findViewById(R.id.status_container);
        this.b = (AppCompatTextView) findViewById(R.id.time_status);
        this.c = (AppCompatTextView) findViewById(R.id.record_status);
        this.j = (RelativeLayout) findViewById(R.id.move_up_notify);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    private void e() {
        if (ContextCompat.checkSelfPermission(getContext(), "android.permission.RECORD_AUDIO") != 0) {
            PermissionItem permissionItem = new PermissionItem(new String[]{"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"});
            permissionItem.runIgnorePermission = false;
            permissionItem.settingText = "设置";
            permissionItem.deniedMessage = "开启以下权限才能正常发布语音内容";
            permissionItem.needGotoSetting = true;
            cn.xiaochuankeji.aop.permission.a.a(getContext()).a(permissionItem, new e(this) {
                final /* synthetic */ ChatRecordLayout a;

                {
                    this.a = r1;
                }

                public void permissionGranted() {
                }

                public void permissionDenied() {
                    g.a("开启以下权限才能正常发布语音内容");
                }
            });
            return;
        }
        a();
    }

    public void a() {
        if (this.k) {
            setVisibility(0);
            this.b.setVisibility(8);
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            if (this.e == null) {
                this.e = new b(new cn.xiaochuankeji.tieba.common.medialib.a(16000, 1, 2));
                this.e.a(new cn.xiaochuankeji.tieba.common.medialib.b.b(this) {
                    final /* synthetic */ ChatRecordLayout a;

                    {
                        this.a = r1;
                    }

                    public void a(Throwable th) {
                        if (th instanceof SecurityException) {
                            g.a("无法录音，请检查权限");
                        } else {
                            g.a(th.getMessage());
                        }
                        this.a.c();
                    }
                });
                this.e.a(new cn.xiaochuankeji.tieba.ui.voice.c.b.a(this) {
                    final /* synthetic */ ChatRecordLayout a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                    }

                    public void a(long j) {
                    }
                });
            }
            if (this.l != null) {
                this.l.b();
            }
            if (this.a != null) {
                this.a.cancel();
                this.a.start();
            }
            if (this.g != null) {
                this.g.a();
            }
            this.f = new File(cn.xiaochuankeji.tieba.background.a.e().x(), System.currentTimeMillis() + ".wav");
            this.e.a(0);
            this.e.a(this.f.getAbsolutePath(), 1.0f, new cn.xiaochuankeji.tieba.common.medialib.b.a(this) {
                final /* synthetic */ ChatRecordLayout a;

                {
                    this.a = r1;
                }

                public long a() {
                    return 0;
                }
            });
            return;
        }
        com.izuiyou.a.a.b.e("finger has left voiceControl");
        c();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.e != null) {
            this.e.a(0);
            this.e.b();
        }
        if (this.l != null) {
            this.l = null;
        }
    }

    public void b() {
        setVisibility(8);
        this.d.setVisibility(8);
        this.j.setVisibility(8);
        this.a.cancel();
    }

    public void c() {
        b();
        if (this.e != null) {
            this.e.a(0);
        }
        if (this.f != null) {
            if (this.f.exists()) {
                this.f.delete();
            }
            this.f = null;
        }
        if (this.g != null) {
            this.g.b();
        }
    }

    private void f() {
        if (this.e != null) {
            this.e.a(0);
        }
    }

    private void g() {
        b();
        if (this.e == null || this.e.a()) {
            f();
            if (!(this.g == null || this.f == null)) {
                this.g.a(this.f.getAbsolutePath());
            }
            this.f = null;
            return;
        }
        com.izuiyou.a.a.b.e("没有开启录音");
    }

    public void setOnOnRecordListener(a aVar) {
        this.g = aVar;
    }

    public void a(View view) {
        this.h = view;
        this.h.setOnTouchListener(this);
    }

    public void a(c cVar) {
        this.l = cVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float top = (float) (this.h.getTop() + (this.h.getHeight() / 2));
        switch (motionEvent.getAction()) {
            case 0:
                this.k = true;
                this.m = System.currentTimeMillis();
                this.i = motionEvent.getY();
                e();
                break;
            case 1:
                this.k = false;
                this.n = System.currentTimeMillis();
                if (this.n - this.m >= 200) {
                    if ((-(motionEvent.getY() - this.i)) <= top) {
                        g();
                        break;
                    }
                    motionEvent.setAction(3);
                    return onTouch(this, motionEvent);
                }
                motionEvent.setAction(3);
                return onTouch(this, motionEvent);
            case 2:
                if ((-(motionEvent.getY() - this.i)) <= top) {
                    i();
                    break;
                }
                h();
                break;
            case 3:
                this.k = false;
                c();
                return false;
        }
        return true;
    }

    private void h() {
        if (this.j.getVisibility() != 0) {
            this.b.setVisibility(4);
            this.c.setVisibility(4);
            this.d.setVisibility(0);
            this.j.setVisibility(0);
            this.j.invalidate();
        }
    }

    private void i() {
        if (this.j.getVisibility() == 0) {
            this.d.setVisibility(4);
            this.j.setVisibility(4);
            this.j.invalidate();
        }
    }
}
