package cn.xiaochuankeji.tieba.ui.widget.updown;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class ViewUpDown extends RelativeLayout {
    private Context a;
    private int b;
    private int c;
    private a d;
    private UpArrowView e = ((UpArrowView) findViewById(R.id.ivUpArrow));
    private DownArrowView f;
    private TextView g = ((TextView) findViewById(R.id.tvUpCount));

    public interface a {
        void a(int i, int i2, boolean z);

        void a(boolean z);
    }

    public ViewUpDown(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        LayoutInflater.from(context).inflate(R.layout.view_up_and_down, this);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ViewUpDown a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        this.f = (DownArrowView) findViewById(R.id.ivDownArrow);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ViewUpDown a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
            }
        });
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        MeasureSpec.makeMeasureSpec(e.b() / 3, 1073741824);
    }

    public void a(int i, int i2, a aVar) {
        this.c = i;
        this.b = i2;
        this.d = aVar;
        setLikeViewState(false);
    }

    private void setLikeViewState(boolean z) {
        Resources resources = getContext().getResources();
        if (this.c == 1) {
            this.g.setTextColor(resources.getColor(R.color.CM));
            this.e.a(true, z);
            this.f.setSelected(false);
        } else if (this.c == -1) {
            this.g.setTextColor(resources.getColor(R.color.CH));
            this.e.setSelected(false);
            this.f.a(true, z);
        } else {
            this.g.setTextColor(resources.getColor(R.color.CT_5));
            this.e.setSelected(false);
            this.f.setSelected(false);
        }
        this.g.setText(d.b(this.b));
    }

    private void a() {
        if (!cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, "media_browser", this.c == 0 ? 12 : -12)) {
            return;
        }
        if (this.c == 0) {
            this.c = 1;
            this.b++;
            setLikeViewState(false);
            if (this.d != null) {
                this.d.a(this.c, this.b, true);
            }
        } else if (this.d != null) {
            this.d.a(this.c == 1);
        }
    }

    private void b() {
        if (!cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.a, "media_browser", this.c == 0 ? 14 : -14)) {
            return;
        }
        if (this.c == 0) {
            this.c = -1;
            this.b--;
            setLikeViewState(false);
            if (this.d != null) {
                this.d.a(this.c, this.b, true);
            }
        } else if (this.d != null) {
            this.d.a(this.c == 1);
        }
    }
}
