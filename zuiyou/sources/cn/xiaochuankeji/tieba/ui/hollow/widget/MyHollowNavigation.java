package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class MyHollowNavigation extends FrameLayout {
    private a a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private View f;
    private int g;
    private int h;

    public interface a {
        void a(int i);
    }

    public MyHollowNavigation(@NonNull Context context) {
        super(context);
        a();
    }

    public MyHollowNavigation(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public MyHollowNavigation(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_my_hollow_navigation, this);
        b();
        c();
    }

    private void b() {
        this.b = (TextView) findViewById(R.id.my_hollow_nav_hollow);
        this.c = (TextView) findViewById(R.id.my_hollow_nav_reply);
        this.d = (TextView) findViewById(R.id.my_hollow_nav_discovery);
        this.e = (ImageView) findViewById(R.id.nav_back_icon);
        this.f = findViewById(R.id.my_hollow_nav_slide);
        findViewById(R.id.fun_view_left).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyHollowNavigation a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a.a(0);
            }
        });
        findViewById(R.id.fun_view_middle).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyHollowNavigation a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a.a(1);
            }
        });
        findViewById(R.id.nav_publish_icon).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyHollowNavigation a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a.a(2);
            }
        });
        findViewById(R.id.fun_view_right).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyHollowNavigation a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a.a(3);
            }
        });
    }

    private void c() {
        findViewById(R.id.my_hollow_nav_root).setPadding(0, (int) getResources().getDimension(R.dimen.status_bar_height), 0, 0);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        d();
    }

    private void d() {
        View findViewById = findViewById(R.id.fun_view_middle);
        View findViewById2 = findViewById(R.id.fun_view_empty);
        View findViewById3 = findViewById(R.id.fun_view_left);
        this.h = findViewById3.getLeft() + findViewById2.getLeft();
        LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
        layoutParams.setMargins(this.h, findViewById2.getTop(), findViewById3.getLeft() + this.f.getRight(), findViewById2.getBottom());
        this.f.setLayoutParams(layoutParams);
        this.g = findViewById.getLeft() - findViewById3.getLeft();
    }

    public void setSelectedPosition(int i) {
        float f;
        float f2 = 1.0f;
        this.f.setX((float) ((this.g * i) + this.h));
        TextView textView = this.b;
        if (i == 0) {
            f = 1.0f;
        } else {
            f = 0.8f;
        }
        textView.setAlpha(f);
        textView = this.c;
        if (i == 1) {
            f = 1.0f;
        } else {
            f = 0.8f;
        }
        textView.setAlpha(f);
        TextView textView2 = this.d;
        if (i != 2) {
            f2 = 0.8f;
        }
        textView2.setAlpha(f2);
    }

    public void a(int i, OnClickListener onClickListener) {
        this.e.setImageResource(i);
        this.e.setOnClickListener(onClickListener);
    }

    public void setOnTabClickListener(a aVar) {
        this.a = aVar;
    }
}
