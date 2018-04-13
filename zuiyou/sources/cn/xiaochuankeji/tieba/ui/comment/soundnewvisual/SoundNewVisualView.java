package cn.xiaochuankeji.tieba.ui.comment.soundnewvisual;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import c.a.i.u;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.j.c;
import cn.xiaochuankeji.tieba.background.j.c.a;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class SoundNewVisualView extends RelativeLayout implements u, c {
    private int a;
    private long b;
    private long c;
    private long d;
    private a e;
    private ImageView f;
    private SoundWaveView g;
    private View h;
    private TextView i;
    private Handler j = new Handler();
    private Runnable k = new Runnable(this) {
        final /* synthetic */ SoundNewVisualView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d = this.a.d - 1000;
            if (this.a.d < 0) {
                this.a.j.removeCallbacks(this.a.k);
                return;
            }
            this.a.i.setText((this.a.d / 1000) + "''");
            this.a.j.postDelayed(this.a.k, 1000);
        }
    };

    public SoundNewVisualView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_item_sound_new_visual, this);
        setLayoutParams(new LayoutParams(-1, e.a(35.0f)));
        e();
    }

    private void e() {
        this.f = (ImageView) findViewById(R.id.ivPlaySound);
        this.f.setImageDrawable(c.a.d.a.a.a().b(R.drawable.img_play_sound));
        this.g = (SoundWaveView) findViewById(R.id.soundWaveView);
        this.h = findViewById(R.id.vDownloading);
        this.i = (TextView) findViewById(R.id.tvTime);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SoundNewVisualView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                PermissionItem permissionItem = new PermissionItem("android.permission.WRITE_EXTERNAL_STORAGE");
                permissionItem.deniedMessage("正常预览语音需要该权限").needGotoSetting(true).rationalMessage("正常预览语音需要该权限").settingText("去设置").runIgnorePermission(false);
                cn.xiaochuankeji.aop.permission.a.a(this.a.getContext()).a(permissionItem, new cn.xiaochuankeji.aop.permission.e(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        if (this.a.a.e != null) {
                            this.a.a.e.a();
                        }
                    }

                    public void permissionDenied() {
                    }
                });
            }
        });
        d();
    }

    public void c() {
        if (this.f.isSelected()) {
            this.f.setSelected(false);
        } else {
            this.f.setSelected(true);
        }
    }

    public void setSoundTime(int i) {
        this.a = i;
        this.i.setText(this.a + "''");
    }

    public void a() {
        this.c = 0;
        this.f.setSelected(false);
        this.g.b();
        this.j.removeCallbacks(this.k);
        this.i.setText(this.a + "''");
    }

    public void a(int i, int i2) {
        this.b = System.currentTimeMillis();
        this.g.a(i2, this.c);
        this.d = ((long) (this.a * 1000)) - this.c;
        this.i.setText((this.d / 1000) + "''");
        this.j.postDelayed(this.k, 1000);
    }

    public void setViewDownloadState(boolean z) {
        if (z) {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            return;
        }
        this.i.setVisibility(0);
        this.h.setVisibility(8);
    }

    public void b() {
        this.b = System.currentTimeMillis() - this.b;
        this.c += this.b;
        this.g.a();
        this.j.removeCallbacks(this.k);
    }

    public void a(boolean z, long j, long j2) {
        this.f.setSelected(z);
        this.b = System.currentTimeMillis();
        this.c = j;
        this.g.a(z, j2, this.c);
        this.d = ((long) (this.a * 1000)) - this.c;
        this.i.setText((this.d / 1000) + "''");
        if (z) {
            this.j.removeCallbacks(this.k);
            this.j.postDelayed(this.k, 1000);
        }
    }

    public void setOnPlayOrPauseListener(a aVar) {
        this.e = aVar;
    }

    public void setThisVisibility(int i) {
        setVisibility(i);
    }

    public void d() {
        setBackgroundDrawable(c.a.d.a.a.a().b(R.drawable.bg_shape_sound_new_visual));
    }

    public void b(int i, int i2) {
        if (this.g != null) {
            this.g.a(i2, (long) i);
        }
    }
}
