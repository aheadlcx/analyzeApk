package com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import com.marshalchen.ultimaterecyclerview.b.a;
import com.marshalchen.ultimaterecyclerview.b.e;

public class AddFloatingActionButton extends a {
    protected int a;

    public AddFloatingActionButton(Context context) {
        this(context, null);
    }

    public AddFloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AddFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.AddFloatingActionButton, 0, 0);
            if (obtainStyledAttributes != null) {
                try {
                    this.a = obtainStyledAttributes.getColor(e.AddFloatingActionButton_plusIconColor, a(17170443));
                } finally {
                    obtainStyledAttributes.recycle();
                }
            }
        } else {
            this.a = a(17170443);
        }
        super.a(context, attributeSet);
    }

    protected Drawable getIconDrawable() {
        final float b = b(a.fab_icon_size);
        final float f = b / 2.0f;
        final float b2 = b(a.fab_plus_icon_stroke) / 2.0f;
        final float b3 = (b - b(a.fab_plus_icon_size)) / 2.0f;
        Drawable shapeDrawable = new ShapeDrawable(new Shape(this) {
            final /* synthetic */ AddFloatingActionButton e;

            public void draw(Canvas canvas, Paint paint) {
                canvas.drawRect(b3, f - b2, b - b3, b2 + f, paint);
                canvas.drawRect(f - b2, b3, b2 + f, b - b3, paint);
            }
        });
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(this.a);
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        return shapeDrawable;
    }
}
