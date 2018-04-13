package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.htjyb.netlib.NetworkMonitor;
import cn.htjyb.netlib.h;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class CustomEmptyView extends FrameLayout {
    private LinearLayout a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private String e = "空空如也";
    private int f = R.drawable.ic_topic_empty_post;

    public CustomEmptyView(@NonNull Context context) {
        super(context);
        a(false);
    }

    public CustomEmptyView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context.obtainStyledAttributes(attributeSet, R.styleable.emptyView).getBoolean(0, false));
    }

    public CustomEmptyView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        a(context.obtainStyledAttributes(attributeSet, R.styleable.emptyView).getBoolean(0, false));
    }

    public CustomEmptyView(@NonNull Context context, boolean z) {
        super(context);
        a(z);
    }

    private void a(boolean z) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_empty_view, this);
        this.a = (LinearLayout) findViewById(R.id.vTipContainer);
        this.b = (ImageView) findViewById(R.id.vTipImage);
        this.c = (TextView) findViewById(R.id.tvTip);
        this.d = (TextView) findViewById(R.id.tvGoToSetNet);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CustomEmptyView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                h.b(this.a.getContext());
            }
        });
        if (z) {
            LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
            layoutParams.gravity = 49;
            layoutParams.topMargin = e.a(40.0f);
            this.a.setLayoutParams(layoutParams);
        }
        setVisibility(8);
    }

    public void a(int i, String str) {
        if (i != 0) {
            this.f = i;
        }
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
        }
    }

    public void b(int i, String str) {
        if (i != 0) {
            this.b.setImageResource(i);
        }
        if (!TextUtils.isEmpty(str)) {
            this.c.setText(str);
        }
    }

    public void setCustomText(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
        }
    }

    public void a() {
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.bottomMargin = 0;
        layoutParams.bottomMargin = e.a(45.0f);
        this.a.setLayoutParams(layoutParams);
    }

    public void b() {
        setVisibility(0);
        if (NetworkMonitor.a()) {
            this.c.setText(this.e);
            this.b.setImageResource(this.f);
            this.d.setVisibility(4);
            return;
        }
        this.c.setText("网络不给力哦~");
        this.b.setImageResource(R.drawable.ic_network_error);
        this.d.setVisibility(0);
    }

    public void c() {
        setVisibility(8);
    }
}
