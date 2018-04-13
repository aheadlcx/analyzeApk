package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class f {
    private Activity a;
    private View b;
    private TextView c;
    private ProgressBar d;
    private ImageView e;
    private Boolean f = Boolean.valueOf(false);
    private b g;
    private Handler h;
    private Runnable i;
    private ArrayList<LocalMedia> j;
    private boolean k = false;
    private int l = 0;
    private ArrayList<Long> m = new ArrayList();
    private ArrayList<Long> n = new ArrayList();
    private j o;
    private int p = 0;

    public interface a {
        void a();
    }

    public interface b {
        void a(boolean z, ArrayList<Long> arrayList, ArrayList<Long> arrayList2, String str);
    }

    public f(Activity activity, b bVar) {
        this.a = activity;
        this.g = bVar;
        this.h = new Handler();
        d();
        e();
    }

    private void d() {
        this.b = LayoutInflater.from(this.a).inflate(R.layout.view_uploadmedia_progress_dialog, null);
        this.c = (TextView) this.b.findViewById(R.id.tvUploadTitle);
        this.d = (ProgressBar) this.b.findViewById(R.id.pBarUpload);
        this.e = (ImageView) this.b.findViewById(R.id.ivCancel);
    }

    private void e() {
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
                this.a.k = true;
                if (this.a.o != null) {
                    this.a.o.a();
                }
            }
        });
        this.b.setOnClickListener(null);
    }

    public void a() {
        if (c()) {
            b();
        }
        ((RelativeLayout) this.a.findViewById(R.id.rootView)).addView(this.b, new LayoutParams(-1, -1));
        this.f = Boolean.valueOf(true);
    }

    public void b() {
        ((ViewGroup) this.a.findViewById(R.id.rootView)).removeView(this.b);
        this.f = Boolean.valueOf(false);
    }

    public boolean c() {
        return this.f.booleanValue();
    }

    public void a(ArrayList<LocalMedia> arrayList, j jVar) {
        a();
        this.j = arrayList;
        this.o = jVar;
        f();
    }

    private void f() {
        StringBuilder stringBuilder = new StringBuilder("正在上传 ");
        if (((LocalMedia) this.j.get(0)).type == 1) {
            stringBuilder.append("视频");
        } else {
            stringBuilder.append("图片");
        }
        stringBuilder.append((this.l + 1) + "/" + this.j.size());
        a(stringBuilder.toString(), 10, 0);
        this.o.a(this.j, "", new cn.xiaochuankeji.tieba.background.upload.b(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                StringBuilder stringBuilder = new StringBuilder("正在上传 ");
                if (((LocalMedia) this.a.j.get(i)).type == 1) {
                    stringBuilder.append("视频");
                } else {
                    stringBuilder.append("图片");
                }
                stringBuilder.append((i + 1) + "/" + this.a.j.size());
                this.a.a(stringBuilder.toString(), (int) j, (int) j2);
            }
        }, new cn.xiaochuankeji.tieba.background.upload.f(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                this.a.a("正在发评论", 10, 0);
                this.a.g.a(true, (ArrayList) list2, (ArrayList) list, null);
            }

            public void a(String str) {
                this.a.b();
                if (TextUtils.isEmpty(str) || !str.contains("上传视频不能超过15分钟")) {
                    g.a("上传失败");
                } else {
                    g.a("上传视频不能超过15分钟");
                }
            }
        });
    }

    public void a(String str, int i, int i2) {
        if (str != null) {
            this.c.setText(str);
        }
        this.d.setMax(i);
        this.d.setProgress(i2);
    }

    public void a(final a aVar) {
        this.p = 0;
        this.i = new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                this.b.p = this.b.p + 1;
                if (this.b.p <= 30) {
                    this.b.d.setMax(30);
                    this.b.d.setProgress(this.b.p);
                    this.b.h.post(this.b.i);
                    return;
                }
                this.b.b();
                aVar.a();
            }
        };
        this.h.post(this.i);
    }
}
