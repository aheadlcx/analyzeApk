package com.handmark.pulltorefresh.library.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.handmark.pulltorefresh.library.R;

public class SixroomRefreshLoadingView extends View {
    private Bitmap a;

    public SixroomRefreshLoadingView(Context context) {
        super(context);
        a();
    }

    public SixroomRefreshLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.a = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.loading_000));
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(i), (a(i) * this.a.getHeight()) / this.a.getWidth());
    }

    private int a(int i) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        if (mode == 1073741824) {
            return size;
        }
        int width = this.a.getWidth();
        return mode == Integer.MIN_VALUE ? Math.min(width, size) : width;
    }
}
