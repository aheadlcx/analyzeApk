package cn.xiaochuankeji.tieba.ui.widget.updown;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.d;

public class CommentItemUpDownView extends RelativeLayout implements u {
    private Context a;
    private int b;
    private int c;
    private a d;
    private ImageView e = ((ImageView) findViewById(R.id.ivUpArrow));
    private ImageView f = ((ImageView) findViewById(R.id.ivDownArrow));
    private TextView g = ((TextView) findViewById(R.id.tvUpCount));
    private String h;

    public interface a {
        void a(int i, int i2, boolean z);

        void a(boolean z);
    }

    public CommentItemUpDownView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        LayoutInflater.from(context).inflate(R.layout.comment_view_up_and_down, this);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommentItemUpDownView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c();
            }
        });
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommentItemUpDownView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e();
            }
        });
    }

    public void a(int i, int i2, a aVar) {
        this.c = i;
        this.b = i2;
        this.d = aVar;
        b();
    }

    public void a() {
        if (1 == this.c) {
            this.c = 0;
            this.b--;
        } else if (-1 == this.c) {
            this.c = 0;
            this.b++;
        }
        b();
        if (this.d != null) {
            this.d.a(this.c, this.b, false);
        }
    }

    private void b() {
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
        this.g.setText(d.b(this.b));
        d();
    }

    private void c() {
        if (!cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, this.h, this.c == 0 ? 12 : -12)) {
            return;
        }
        if (this.c == 0) {
            this.c = 1;
            this.b++;
            b();
            if (this.d != null) {
                this.d.a(this.c, this.b, true);
            }
        } else if (this.d != null) {
            this.d.a(this.c == 1);
        }
    }

    private void e() {
        if (!cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, this.h, this.c == 0 ? 14 : -14)) {
            return;
        }
        if (this.c == 0) {
            this.c = -1;
            this.b--;
            b();
            if (this.d != null) {
                this.d.a(this.c, this.b, true);
            }
        } else if (this.d != null) {
            this.d.a(this.c == 1);
        }
    }

    public void d() {
        Drawable b = c.a.d.a.a.a().b(R.drawable.ic_state_godreview_up);
        Drawable b2 = c.a.d.a.a.a().b(R.drawable.ic_state_godreview_down);
        int a = c.a.d.a.a.a().a(R.color.CH);
        int a2 = c.a.d.a.a.a().a(R.color.CM);
        if (this.e != null) {
            this.e.setImageDrawable(b);
        }
        if (this.f != null) {
            this.f.setImageDrawable(b2);
        }
        if (this.g == null) {
            return;
        }
        if (this.c == -1) {
            this.g.setTextColor(a);
        } else {
            this.g.setTextColor(a2);
        }
    }

    public void setRefer(String str) {
        this.h = str;
    }
}
