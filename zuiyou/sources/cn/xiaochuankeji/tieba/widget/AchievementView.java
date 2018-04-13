package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class AchievementView extends RelativeLayout {
    private Context a;
    private TextView b;
    private ImageView c;
    private LinearLayout d;
    private LinearLayout e;
    private boolean f = false;
    private boolean g = false;
    private String h = "";
    @ColorInt
    private int i = -15425793;
    private int j = -1;
    private int k = -1;

    public AchievementView(Context context) {
        super(context);
    }

    public AchievementView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public AchievementView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.a = context;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.archivimentview, 0, 0);
        try {
            this.h = obtainStyledAttributes.getString(0);
            this.i = obtainStyledAttributes.getInt(2, this.i);
            this.j = obtainStyledAttributes.getInt(3, this.j);
            this.k = obtainStyledAttributes.getResourceId(1, -1);
            View.inflate(context, R.layout.archievement_content_ach, this);
            a();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void a() {
        this.c = (ImageView) findViewById(R.id.img_left);
        this.b = (TextView) findViewById(R.id.tv_msg);
        ViewCompat.setAlpha(this, 0.0f);
        this.e = (LinearLayout) findViewById(R.id.ll_content_main);
        this.d = (LinearLayout) findViewById(R.id.ll_content);
        this.b.setText(this.h);
        ((GradientDrawable) this.e.getBackground()).setColor(this.i);
        this.b.setTextColor(this.j);
        if (this.k != -1) {
            this.c.setImageDrawable(this.a.getResources().getDrawable(this.k));
        }
    }
}
