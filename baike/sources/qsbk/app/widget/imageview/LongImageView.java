package qsbk.app.widget.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import qsbk.app.widget.imageview.TouchImageView.OnTouchLongImageViewListener;

public class LongImageView extends TouchImageView {
    private static final int MULTIPLE = 3;
    private static final String TAG = LongImageView.class.getSimpleName();
    private static final Options mDecodeOptions = new Options();
    private float currY;
    private int distance;
    private boolean isRegionDecoderInited;
    private Rect lastRect;
    private float lastY;
    private int mImageHeight;
    private int mImageWidth;
    private Rect mRect;
    private BitmapRegionDecoder mRegionDecoder;

    private class a implements OnTouchLongImageViewListener {
        final /* synthetic */ LongImageView a;

        private a(LongImageView longImageView) {
            this.a = longImageView;
        }

        public void onMove(View view, MotionEvent motionEvent) {
            Log.e(LongImageView.TAG, "onMove");
            switch (motionEvent.getAction()) {
                case 0:
                    this.a.currY = a(motionEvent);
                    this.a.lastY = this.a.currY;
                    return;
                case 1:
                case 2:
                    this.a.currY = a(motionEvent);
                    this.a.mRect.offset(0, (int) (this.a.lastY - this.a.currY));
                    this.a.lastY = this.a.currY;
                    a();
                    return;
                default:
                    return;
            }
        }

        private void a() {
            c();
            BitmapDrawable bitmapDrawable;
            if (this.a.lastRect.top < this.a.mRect.top && this.a.lastRect.bottom > this.a.lastRect.top + ((this.a.distance * 2) / 3)) {
                bitmapDrawable = (BitmapDrawable) this.a.getDrawable();
                if (!bitmapDrawable.getBitmap().isRecycled()) {
                    bitmapDrawable.getBitmap().recycle();
                }
                this.a.setImageBitmap(this.a.mRegionDecoder.decodeRegion(this.a.mRect, LongImageView.mDecodeOptions));
                this.a.requestLayout();
                this.a.invalidate();
                b();
            } else if (this.a.lastRect.top > this.a.mRect.top && this.a.mRect.top < this.a.lastRect.top + (this.a.distance / 3)) {
                bitmapDrawable = (BitmapDrawable) this.a.getDrawable();
                if (!bitmapDrawable.getBitmap().isRecycled()) {
                    bitmapDrawable.getBitmap().recycle();
                }
                this.a.setImageBitmap(this.a.mRegionDecoder.decodeRegion(this.a.mRect, LongImageView.mDecodeOptions));
                this.a.requestLayout();
                this.a.invalidate();
                b();
            }
        }

        private void b() {
            this.a.lastRect.left = this.a.mRect.left;
            this.a.lastRect.top = this.a.mRect.top;
            this.a.lastRect.right = this.a.mRect.right;
            this.a.lastRect.bottom = this.a.mRect.bottom;
        }

        private float a(MotionEvent motionEvent) {
            int pointerCount = motionEvent.getPointerCount();
            float f = 0.0f;
            for (int i = 0; i < pointerCount; i++) {
                f += motionEvent.getY(i);
            }
            return f / ((float) pointerCount);
        }

        private void c() {
            if (this.a.mRect.top < 0) {
                this.a.mRect.top = 0;
                this.a.mRect.bottom = this.a.distance;
            }
            if (this.a.mRect.bottom > this.a.mImageHeight) {
                this.a.mRect.bottom = this.a.mImageHeight;
                this.a.mRect.top = this.a.mRect.bottom - this.a.distance;
            }
        }
    }

    public LongImageView(Context context) {
        super(context);
    }

    public LongImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LongImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static byte[] getBytes(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (this.isRegionDecoderInited) {
            super.setImageBitmap(bitmap);
        } else {
            setImageBytes(bitmapToBytes(bitmap));
        }
    }

    private byte[] bitmapToBytes(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setImageBytes(byte[] bArr) {
        try {
            mDecodeOptions.inPreferredConfig = Config.RGB_565;
            mDecodeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, mDecodeOptions);
            this.mImageWidth = mDecodeOptions.outWidth;
            this.mImageHeight = mDecodeOptions.outHeight;
            this.mRegionDecoder = BitmapRegionDecoder.newInstance(bArr, 0, bArr.length, false);
            this.isRegionDecoderInited = true;
            commonSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImageInputStream(InputStream inputStream) {
        byte[] bArr = null;
        try {
            mDecodeOptions.inPreferredConfig = Config.RGB_565;
            mDecodeOptions.inJustDecodeBounds = true;
            try {
                bArr = getBytes(inputStream);
                BitmapFactory.decodeByteArray(bArr, 0, bArr.length, mDecodeOptions);
            } catch (Exception e) {
                BitmapFactory.decodeStream(inputStream, null, mDecodeOptions);
            }
            this.mImageWidth = mDecodeOptions.outWidth;
            this.mImageHeight = mDecodeOptions.outHeight;
            if (bArr != null) {
                this.mRegionDecoder = BitmapRegionDecoder.newInstance(bArr, 0, bArr.length, false);
                this.isRegionDecoderInited = true;
            }
            commonSet();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (IOException e22) {
            e22.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public void setImageInputStream(InputStream inputStream, int i, int i2) {
        try {
            this.mRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            this.isRegionDecoderInited = true;
            mDecodeOptions.inPreferredConfig = Config.RGB_565;
            this.mImageWidth = i;
            this.mImageHeight = i2;
            commonSet();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public void commonSet() {
        if (this.mImageHeight * this.mImageWidth < 921600) {
            this.mRect = new Rect(0, 0, this.mImageWidth, this.mImageHeight);
        } else {
            int i = 3;
            while (this.mImageWidth * i < getHeight() && this.mImageWidth * i < this.mImageHeight) {
                i++;
            }
            if (this.mImageHeight < this.mImageWidth * i) {
                i = this.mImageHeight;
            } else {
                i *= this.mImageWidth;
            }
            this.mRect = new Rect(0, 0, this.mImageWidth, i);
        }
        Log.e(TAG, this.mRect.toString());
        setImageBitmap(this.mRegionDecoder.decodeRegion(this.mRect, mDecodeOptions));
        requestLayout();
        invalidate();
        this.lastRect = new Rect(this.mRect.left, this.mRect.top, this.mRect.right, this.mRect.bottom);
        this.distance = this.mRect.bottom - this.mRect.top;
        if ((this.distance < this.mImageHeight ? 1 : 0) != 0) {
            super.setTouchLongImageViewListener(new a());
        } else {
            super.setTouchLongImageViewListener(null);
        }
    }

    public int[] getOriginSize(int[] iArr) {
        if (iArr == null || iArr.length != 2) {
            iArr = new int[2];
        }
        iArr[0] = this.mImageWidth;
        iArr[1] = this.mImageHeight;
        return iArr;
    }
}
