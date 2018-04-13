package cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class RoundedVideoSurfaceView extends GLSurfaceView {
    private d a;
    private c b;
    private float c;

    public interface a {
        void a(SurfaceTexture surfaceTexture);

        void b(int i, int i2);

        void k();
    }

    public RoundedVideoSurfaceView(Context context) {
        super(context);
        a();
    }

    public RoundedVideoSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setEGLContextClientVersion(2);
        setZOrderOnTop(true);
        getHolder().setFormat(1);
        this.b = new c();
        setEGLConfigChooser(this.b);
        this.a = new d(this, this.b.a());
        setRenderer(this.a);
        setRenderMode(0);
    }

    public void setCallback(a aVar) {
        this.a.a(aVar);
    }

    public void onPause() {
        queueEvent(new Runnable(this) {
            final /* synthetic */ RoundedVideoSurfaceView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a.a();
            }
        });
        super.onPause();
    }

    public void setCornerRadius(float f) {
        a(f, f, f, f);
    }

    public void a(float f, float f2, float f3, float f4) {
        this.a.a(f, f2, f3, f4);
    }

    public void setVideoAspectRatio(float f) {
        if (this.c != f) {
            this.c = f;
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.c != 0.0f) {
            int measuredWidth = getMeasuredWidth();
            super.onMeasure(MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), MeasureSpec.makeMeasureSpec((int) (((float) measuredWidth) / this.c), 1073741824));
        }
    }
}
