package cn.xiaochuankeji.tieba.ui.widget.updown;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class PostItemUpDownView extends LinearLayout implements u {
    private Context a;
    private int b;
    private int c;
    private a d;
    private ImageView e = ((ImageView) findViewById(R.id.ivUpArrow));
    private ImageView f = ((ImageView) findViewById(R.id.ivDownArrow));
    private TextView g = ((TextView) findViewById(R.id.tvUpCount));

    public interface a {
        void a(int i, int i2, boolean z);

        void a(boolean z);
    }

    public PostItemUpDownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        LayoutInflater.from(context).inflate(R.layout.postitem_view_up_and_down, this);
        c();
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostItemUpDownView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
            }
        });
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostItemUpDownView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g();
            }
        });
    }

    public static int a() {
        int b = e.b();
        int a = e.a(21.0f);
        int a2 = e.a(23.0f);
        return (((b - (a * 2)) - (a2 * 4)) / 3) + (a2 * 2);
    }

    private void c() {
        setOrientation(0);
        post(new Runnable(this) {
            final /* synthetic */ PostItemUpDownView a;

            {
                this.a = r1;
            }

            public void run() {
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams.addRule(11, -1);
                layoutParams.rightMargin = e.a(0.0f);
                this.a.setLayoutParams(layoutParams);
            }
        });
    }

    public void a(int i, int i2, a aVar) {
        this.c = i;
        this.b = i2;
        this.d = aVar;
        e();
    }

    public void b() {
        if (1 == this.c) {
            this.c = 0;
            this.b--;
        } else if (-1 == this.c) {
            this.c = 0;
            this.b++;
        }
        e();
        if (this.d != null) {
            this.d.a(this.c, this.b, false);
        }
    }

    private void e() {
        d();
        this.g.setText(d.b(this.b));
        if (this.c == 1) {
            this.e.setSelected(true);
            this.f.setSelected(false);
        } else if (this.c == -1) {
            this.e.setSelected(false);
            this.f.setSelected(true);
        } else {
            this.e.setSelected(false);
            this.f.setSelected(false);
        }
    }

    private void f() {
        if (!cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, this.a instanceof PostDetailActivity ? "post_detail" : "post_list", this.c == 0 ? 12 : -12)) {
            return;
        }
        if (this.c == 0) {
            this.c = 1;
            this.b++;
            e();
            if (this.d != null) {
                this.d.a(this.c, this.b, true);
            }
        } else if (this.d != null) {
            this.d.a(this.c == 1);
        }
    }

    private void g() {
        if (!cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, this.a instanceof PostDetailActivity ? "post_detail" : "post_list", this.c == 0 ? 14 : -14)) {
            return;
        }
        if (this.c == 0) {
            this.c = -1;
            this.b--;
            e();
            if (this.d != null) {
                this.d.a(this.c, this.b, true);
            }
        } else if (this.d != null) {
            this.d.a(this.c == 1);
        }
    }

    public void d() {
        Drawable b = c.a.d.a.a.a().b(R.drawable.ic_arrow_up);
        Drawable b2 = c.a.d.a.a.a().b(R.drawable.ic_arrow_down);
        int a = c.a.d.a.a.a().a(R.color.CM);
        int a2 = c.a.d.a.a.a().a(R.color.CT_5);
        int a3 = c.a.d.a.a.a().a(R.color.CH);
        if (this.c == -1) {
            this.g.setTextColor(a3);
        } else if (this.c == 1) {
            this.g.setTextColor(a);
        } else {
            this.g.setTextColor(a2);
        }
        this.e.setImageDrawable(b);
        this.f.setImageDrawable(b2);
    }
}
