package com.budejie.www.activity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.R$styleable;

public class ShowStepView extends FrameLayout {
    private Context a;
    private LinearLayout b;
    private ImageView[] c;
    private LinearLayout[] d;
    private TextView[] e;
    private ImageView[] f;
    private int g = 1;

    public ShowStepView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.stepview);
        setCurrentLine(obtainStyledAttributes.getInt(0, 1));
        obtainStyledAttributes.recycle();
    }

    public void setCurrentLine(int i) {
        if (i < 1 || i > 5) {
            i = 1;
        }
        this.g = i;
        a(this.g);
    }

    public Rect a(View view) {
        Rect rect = new Rect();
        rect.left = view.getLeft();
        rect.top = view.getTop();
        rect.right = view.getRight();
        rect.bottom = view.getBottom();
        return rect;
    }

    protected void a(int i) {
        int i2 = 0;
        if (this.b == null) {
            this.b = (LinearLayout) findViewById(R.id.lier);
            this.f = new ImageView[]{(ImageView) findViewById(R.id.line1), (ImageView) findViewById(R.id.line2), (ImageView) findViewById(R.id.line3)};
            this.d = new LinearLayout[]{(LinearLayout) findViewById(R.id.item1), (LinearLayout) findViewById(R.id.item2), (LinearLayout) findViewById(R.id.item3), (LinearLayout) findViewById(R.id.item4)};
            this.c = new ImageView[]{(ImageView) findViewById(R.id.point1), (ImageView) findViewById(R.id.point2), (ImageView) findViewById(R.id.point3), (ImageView) findViewById(R.id.point4)};
            this.e = new TextView[]{(TextView) findViewById(R.id.desc1), (TextView) findViewById(R.id.desc2), (TextView) findViewById(R.id.desc3), (TextView) findViewById(R.id.desc4)};
        }
        if (this.b != null) {
            Drawable drawable;
            Drawable drawable2 = this.a.getResources().getDrawable(R.drawable.red_point);
            Drawable drawable3 = this.a.getResources().getDrawable(R.drawable.gray_point);
            int color = this.a.getResources().getColor(R.color.text_enable);
            int color2 = this.a.getResources().getColor(R.color.text_gray);
            for (int i3 = 0; i3 < this.c.length; i3++) {
                int i4;
                ImageView imageView = this.c[i3];
                if (i3 < i) {
                    drawable = drawable2;
                } else {
                    drawable = drawable3;
                }
                imageView.setBackgroundDrawable(drawable);
                TextView textView = this.e[i3];
                if (i3 < i) {
                    i4 = color;
                } else {
                    i4 = color2;
                }
                textView.setTextColor(i4);
            }
            drawable = this.a.getResources().getDrawable(R.drawable.red_line);
            drawable2 = this.a.getResources().getDrawable(R.drawable.gray_line);
            while (i2 < this.f.length) {
                Drawable drawable4;
                View view = this.f[i2];
                if (i - 1 > i2) {
                    drawable4 = drawable;
                } else {
                    drawable4 = drawable2;
                }
                view.setBackgroundDrawable(drawable4);
                Rect b = b(i2);
                Rect b2 = b(i2 + 1);
                Rect a = a(view);
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.x = b.left + b.width();
                layoutParams.y = ((b.height() - a.height()) / 2) + b.top;
                layoutParams.width = b2.left - b.right;
                view.setLayoutParams(layoutParams);
                i2++;
            }
        }
    }

    protected Rect b(int i) {
        Rect a = a(this.c[i]);
        Rect a2 = a(this.d[i]);
        Rect a3 = a(this.b);
        Rect rect = new Rect(a);
        rect.offset(a2.left, a2.top);
        rect.offset(a3.left, a3.top);
        return rect;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(this.g);
    }
}
