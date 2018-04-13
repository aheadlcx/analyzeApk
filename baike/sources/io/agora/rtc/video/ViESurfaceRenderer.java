package io.agora.rtc.video;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Process;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ViESurfaceRenderer implements Callback {
    private static final String TAG = "ViESurfaceRenderer";
    private Bitmap bitmap = null;
    private float bottomScale = 1.0f;
    private ByteBuffer byteBuffer = null;
    private Rect dest = new Rect();
    private float leftScale = 0.0f;
    private float rightScale = 1.0f;
    private Rect source = new Rect();
    private SurfaceHolder surfaceHolder;
    private float topScale = 0.0f;

    public ViESurfaceRenderer(SurfaceView surfaceView) {
        Log.i(TAG, "surface view " + surfaceView);
        this.surfaceHolder = surfaceView.getHolder();
        if (this.surfaceHolder != null) {
            this.surfaceHolder.addCallback(this);
            surfaceCreated(this.surfaceHolder);
        }
    }

    private void changeDestRect(int i, int i2) {
        this.dest.right = (int) (((float) this.dest.left) + (Math.abs(this.leftScale - this.rightScale) * ((float) i)));
        this.dest.bottom = (int) (((float) this.dest.top) + (Math.abs(this.topScale - this.bottomScale) * ((float) i2)));
        Log.i(TAG, "ViESurfaceRender::surfaceChanged in_width:" + i + " in_height:" + i2 + " source.left:" + this.source.left + " source.top:" + this.source.top + " source.dest:" + this.source.right + " source.bottom:" + this.source.bottom + " dest.left:" + this.dest.left + " dest.top:" + this.dest.top + " dest.dest:" + this.dest.right + " dest.bottom:" + this.dest.bottom + " dest scale " + this.rightScale + " bottom scale " + this.bottomScale);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        changeDestRect(i2, i3);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas lockCanvas = this.surfaceHolder.lockCanvas();
        if (lockCanvas != null) {
            Rect surfaceFrame = this.surfaceHolder.getSurfaceFrame();
            if (surfaceFrame != null) {
                changeDestRect(surfaceFrame.right - surfaceFrame.left, surfaceFrame.bottom - surfaceFrame.top);
                Log.i(TAG, "ViESurfaceRender::surfaceCreated dst.left:" + surfaceFrame.left + " dst.top:" + surfaceFrame.top + " dst.dest:" + surfaceFrame.right + " dst.bottom:" + surfaceFrame.bottom + " source.left:" + this.source.left + " source.top:" + this.source.top + " source.dest:" + this.source.right + " source.bottom:" + this.source.bottom + " dest.left:" + this.dest.left + " dest.top:" + this.dest.top + " dest.dest:" + this.dest.right + " dest.bottom:" + this.dest.bottom);
            }
            this.surfaceHolder.unlockCanvasAndPost(lockCanvas);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "ViESurfaceRenderer::surfaceDestroyed");
        this.bitmap = null;
        this.byteBuffer = null;
    }

    public Bitmap CreateBitmap(int i, int i2) {
        Log.d(TAG, "CreateByteBitmap " + i + Config.TRACE_TODAY_VISIT_SPLIT + i2);
        if (this.bitmap == null) {
            try {
                Process.setThreadPriority(-4);
            } catch (Exception e) {
            }
        }
        changeDestRect(i, i2);
        this.bitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
        this.source.left = 0;
        this.source.top = 0;
        this.source.bottom = i2;
        this.source.right = i;
        return this.bitmap;
    }

    public ByteBuffer CreateByteBuffer(int i, int i2) {
        Log.i(TAG, "CreateByteBuffer " + i + " * " + i2);
        if (this.bitmap == null) {
            this.bitmap = CreateBitmap(i, i2);
            this.byteBuffer = ByteBuffer.allocateDirect((i * i2) * 2);
        }
        return this.byteBuffer;
    }

    public void SetCoordinates(float f, float f2, float f3, float f4) {
        Log.i(TAG, "SetCoordinates " + f + Constants.ACCEPT_TIME_SEPARATOR_SP + f2 + " : " + f3 + Constants.ACCEPT_TIME_SEPARATOR_SP + f4);
        this.leftScale = f;
        this.topScale = f2;
        this.rightScale = f3;
        this.bottomScale = f4;
    }

    private void saveBitmapToJPEG(int i, int i2) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(String.format("/sdcard/render_%d.jpg", new Object[]{Long.valueOf(System.currentTimeMillis())}));
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            Log.i(TAG, "saved jpg " + fileOutputStream.toString());
        } catch (Throwable e) {
            Log.w(TAG, "save jpg failed", e);
        }
    }

    public void DrawByteBuffer() {
        if (this.byteBuffer == null) {
            Log.w(TAG, "DrawByteBuffer null");
            return;
        }
        this.byteBuffer.rewind();
        this.bitmap.copyPixelsFromBuffer(this.byteBuffer);
        DrawBitmap();
    }

    public void DrawBitmap() {
        if (this.bitmap != null) {
            Canvas lockCanvas = this.surfaceHolder.lockCanvas();
            if (lockCanvas != null) {
                lockCanvas.drawBitmap(this.bitmap, this.source, this.dest, null);
                this.surfaceHolder.unlockCanvasAndPost(lockCanvas);
            }
        }
    }
}
