package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.a.d;
import com.scwang.smartrefresh.layout.a.g;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.d.a;
import com.scwang.smartrefresh.layout.f.b;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

public class HollowFooter extends ConstraintLayout implements d {
    protected SpinnerStyle a = SpinnerStyle.Translate;
    private TextView b;
    private ImageView c;
    private a d;
    private boolean e = false;
    private int f = 500;
    private boolean g;

    /* renamed from: cn.xiaochuankeji.tieba.ui.hollow.widget.HollowFooter$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[RefreshState.values().length];

        static {
            try {
                a[RefreshState.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[RefreshState.PullToUpLoad.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[RefreshState.Loading.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[RefreshState.LoadReleased.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[RefreshState.ReleaseToLoad.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[RefreshState.Refreshing.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public HollowFooter(Context context) {
        super(context);
        a(context);
    }

    public HollowFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public HollowFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        inflate(getContext(), R.layout.hollow_footer, this);
        b bVar = new b();
        this.d = new a();
        this.d.a(-10066330);
        this.b = (TextView) findViewById(R.id.foot_tip);
        this.c = (ImageView) findViewById(R.id.foot_progress);
        this.c.setImageDrawable(this.d);
    }

    public void a(float f, int i, int i2, int i3) {
    }

    public void b(float f, int i, int i2, int i3) {
    }

    public void a(h hVar, int i, int i2) {
        if (!this.e) {
            this.c.setVisibility(0);
            if (this.d != null) {
                this.d.start();
            } else {
                this.c.animate().rotation(36000.0f).setDuration(100000);
            }
        }
    }

    public boolean a(boolean z) {
        if (this.e != z) {
            this.e = z;
            if (z) {
                this.b.setText(ClassicsFooter.h);
            } else {
                this.b.setText(ClassicsFooter.b);
            }
            if (!this.g) {
                this.b.setText("没有更多内容了");
            }
            if (this.d != null) {
                this.d.stop();
            } else {
                this.c.animate().rotation(0.0f).setDuration(300);
            }
            this.c.setVisibility(8);
        }
        return true;
    }

    @NonNull
    public View getView() {
        return this;
    }

    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        return this.a;
    }

    public void setPrimaryColors(int... iArr) {
    }

    public void a(@NonNull g gVar, int i, int i2) {
    }

    public void a(float f, int i, int i2) {
    }

    public void b(@NonNull h hVar, int i, int i2) {
    }

    public int a(@NonNull h hVar, boolean z) {
        if (this.e) {
            return 0;
        }
        if (this.d != null) {
            this.d.stop();
        } else {
            this.c.animate().rotation(0.0f).setDuration(300);
        }
        this.c.setVisibility(8);
        if (z) {
            this.b.setText(ClassicsFooter.f);
        } else {
            this.b.setText(ClassicsFooter.g);
        }
        if (!this.g) {
            this.b.setText("没有更多内容了");
        }
        return this.f;
    }

    public boolean a() {
        return false;
    }

    public void a(h hVar, RefreshState refreshState, RefreshState refreshState2) {
        if (!this.e) {
            switch (AnonymousClass1.a[refreshState2.ordinal()]) {
                case 1:
                case 2:
                    this.b.setText(ClassicsFooter.b);
                    return;
                case 3:
                case 4:
                    this.b.setText(ClassicsFooter.d);
                    return;
                case 5:
                    this.b.setText(ClassicsFooter.c);
                    return;
                case 6:
                    this.b.setText(ClassicsFooter.e);
                    this.c.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    public void setNoDataTip(boolean z) {
        this.g = z;
    }
}
