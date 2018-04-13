package cn.xiaochuankeji.tieba.ui.publish;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class e {
    int a = 0;
    private Activity b;
    private View c;
    private TextView d;
    private ProgressBar e;
    private ImageView f;
    private Boolean g = Boolean.valueOf(false);
    private a h;
    private Handler i;
    private Runnable j;

    public interface a {
        void l_();
    }

    public interface b {
        void a();
    }

    public e(Activity activity, a aVar) {
        this.b = activity;
        this.h = aVar;
        this.i = new Handler();
        d();
        e();
    }

    private void d() {
        this.c = LayoutInflater.from(this.b).inflate(R.layout.view_uploadmedia_progress_dialog, null);
        this.d = (TextView) this.c.findViewById(R.id.tvUploadTitle);
        this.e = (ProgressBar) this.c.findViewById(R.id.pBarUpload);
        this.f = (ImageView) this.c.findViewById(R.id.ivCancel);
    }

    private void e() {
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h.l_();
            }
        });
        this.c.setOnClickListener(null);
    }

    public void a() {
        if (!this.b.isFinishing()) {
            if (c()) {
                b();
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13, -1);
            ((RelativeLayout) this.b.findViewById(R.id.rootView)).addView(this.c, layoutParams);
            this.g = Boolean.valueOf(true);
        }
    }

    public void b() {
        if (!this.b.isFinishing()) {
            ((RelativeLayout) this.b.findViewById(R.id.rootView)).removeView(this.c);
            this.g = Boolean.valueOf(false);
        }
    }

    public boolean c() {
        return this.g.booleanValue();
    }

    public void a(boolean z) {
        this.f.setEnabled(z);
    }

    public void a(String str, int i, int i2) {
        if (str != null) {
            this.d.setText(str);
        }
        this.e.setMax(i);
        this.e.setProgress(i2);
    }

    public void a(final b bVar) {
        this.a = 0;
        this.j = new Runnable(this) {
            final /* synthetic */ e b;

            public void run() {
                e eVar = this.b;
                eVar.a++;
                if (this.b.a <= 30) {
                    this.b.e.setMax(30);
                    this.b.e.setProgress(this.b.a);
                    this.b.i.post(this.b.j);
                    return;
                }
                bVar.a();
            }
        };
        this.i.post(this.j);
    }
}
