package com.androidex.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import com.androidex.widget.asyncimage.AsyncImageView;

public class RoundAsyncImageView extends AsyncImageView {
    private int defaultColor = -1;
    private int defaultHeight = 0;
    private int defaultWidth = 0;
    private int mBorderInsideColor = 0;
    private int mBorderOutsideColor = 0;
    private int mBorderThickness = 0;
    private Context mContext;

    public void setBorderThickness(int i) {
        this.mBorderThickness = i;
    }

    public void setBorderOutsideColor(int i) {
        this.mBorderOutsideColor = i;
    }

    public void setBorderInsideColor(int i) {
        this.mBorderInsideColor = i;
    }

    public RoundAsyncImageView(Context context) {
        super(context);
        this.mContext = context;
    }

    public RoundAsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setCustomAttributes(attributeSet);
    }

    public RoundAsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        setCustomAttributes(attributeSet);
    }

    private void setCustomAttributes(AttributeSet attributeSet) {
    }

    private void drawRoundBitmap(Canvas canvas, BitmapDrawable bitmapDrawable) {
        try {
            int i;
            Bitmap copy = bitmapDrawable.getBitmap().copy(Config.ARGB_8888, true);
            if (this.defaultWidth == 0) {
                this.defaultWidth = getWidth();
            }
            if (this.defaultHeight == 0) {
                this.defaultHeight = getHeight();
            }
            if (this.mBorderInsideColor != this.defaultColor && this.mBorderOutsideColor != this.defaultColor) {
                i = ((this.defaultWidth < this.defaultHeight ? this.defaultWidth : this.defaultHeight) / 2) - (this.mBorderThickness * 2);
                drawCircleBorder(canvas, (this.mBorderThickness / 2) + i, this.mBorderInsideColor);
                drawCircleBorder(canvas, (this.mBorderThickness + i) + (this.mBorderThickness / 2), this.mBorderOutsideColor);
            } else if (this.mBorderInsideColor != this.defaultColor && this.mBorderOutsideColor == this.defaultColor) {
                i = ((this.defaultWidth < this.defaultHeight ? this.defaultWidth : this.defaultHeight) / 2) - this.mBorderThickness;
                drawCircleBorder(canvas, (this.mBorderThickness / 2) + i, this.mBorderInsideColor);
            } else if (this.mBorderInsideColor != this.defaultColor || this.mBorderOutsideColor == this.defaultColor) {
                i = (this.defaultWidth < this.defaultHeight ? this.defaultWidth : this.defaultHeight) / 2;
            } else {
                i = ((this.defaultWidth < this.defaultHeight ? this.defaultWidth : this.defaultHeight) / 2) - this.mBorderThickness;
                drawCircleBorder(canvas, (this.mBorderThickness / 2) + i, this.mBorderOutsideColor);
            }
            copy = getCroppedRoundBitmap(copy, i);
            if (copy != null) {
                canvas.drawBitmap(copy, (float) ((this.defaultWidth / 2) - i), (float) ((this.defaultHeight / 2) - i), null);
            }
        } catch (OutOfMemoryError e) {
        } catch (Exception e2) {
        }
    }

    public Bitmap getCroppedRoundBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        int i2 = i * 2;
        try {
            Bitmap bitmap2;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap createBitmap = height > width ? Bitmap.createBitmap(bitmap, 0, (height - width) / 2, width, width) : height < width ? Bitmap.createBitmap(bitmap, (width - height) / 2, 0, height, height) : bitmap;
            if (createBitmap.getWidth() == i2 && createBitmap.getHeight() == i2) {
                bitmap2 = createBitmap;
            } else {
                bitmap2 = Bitmap.createScaledBitmap(createBitmap, i2, i2, true);
            }
            createBitmap = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawCircle((float) (bitmap2.getWidth() / 2), (float) (bitmap2.getHeight() / 2), (float) (bitmap2.getWidth() / 2), paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap2, rect, rect, paint);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private void drawCircleBorder(Canvas canvas, int i, int i2) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(i2);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth((float) this.mBorderThickness);
        canvas.drawCircle((float) (this.defaultWidth / 2), (float) (this.defaultHeight / 2), (float) i, paint);
    }
}
