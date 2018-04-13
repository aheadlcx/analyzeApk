package com.androidexlib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import com.androidexlib.widget.asyncimage.AsyncImageView;

public class FilletAsyncImageView extends AsyncImageView {
    public FilletAsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FilletAsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            Bitmap createScaledBitmap;
            Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            if (bitmap.getWidth() != bitmap.getHeight()) {
                int height;
                if (bitmap.getWidth() > bitmap.getHeight()) {
                    height = bitmap.getHeight();
                } else {
                    height = bitmap.getWidth();
                }
                createScaledBitmap = Bitmap.createScaledBitmap(bitmap, height, height, true);
            } else {
                createScaledBitmap = bitmap;
            }
            Shader bitmapShader = new BitmapShader(createScaledBitmap, TileMode.CLAMP, TileMode.CLAMP);
            RectF rectF = new RectF(((float) getPaddingLeft()) + 0.0f, ((float) getPaddingLeft()) + 0.0f, (float) getWidth(), (float) getHeight());
            RectF rectF2 = new RectF(0.0f, 0.0f, (float) createScaledBitmap.getWidth(), (float) createScaledBitmap.getHeight());
            Matrix matrix = new Matrix();
            matrix.setRectToRect(rectF2, rectF, ScaleToFit.CENTER);
            bitmapShader.setLocalMatrix(matrix);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);
            canvas.drawRoundRect(rectF, 10.0f, 10.0f, paint);
            return;
        }
        super.onDraw(canvas);
    }
}
