package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.sensetime.stmobile.STHumanAction;

public class CameraSurfaceView extends GLSurfaceView {
    private b a;
    private a b;

    public interface a {
        void a(int i, int i2);

        void a(EGLContext eGLContext, SurfaceTexture surfaceTexture);

        void m_();
    }

    public interface b {
        void a(String str, long j);
    }

    public interface c {
        void a(int i, int i2, int i3, long j);
    }

    public CameraSurfaceView(Context context) {
        super(context);
        a(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setEGLContextClientVersion(2);
        this.b = new a(context);
        this.a = new b(this.b);
        setRenderer(this.a);
        setRenderMode(0);
    }

    public void setCallback(a aVar) {
        this.a.a(aVar);
    }

    public void setListener(c cVar) {
        this.a.a(cVar);
    }

    public void onPause() {
        queueEvent(new Runnable(this) {
            final /* synthetic */ CameraSurfaceView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a.a();
            }
        });
        super.onPause();
    }

    public void a(boolean z) {
        this.b.a(z);
    }

    public void a(final String str, final b bVar) {
        queueEvent(new Runnable(this) {
            final /* synthetic */ CameraSurfaceView c;

            public void run() {
                this.c.b.a(str, bVar);
            }
        });
    }

    public void b(boolean z) {
        this.b.b(z);
    }

    public void a(int i, float f) {
        this.b.b(i, f);
    }

    public void setVideoFilter(int i) {
        this.b.a(i);
    }

    public void a(final STHumanAction sTHumanAction, final int i, final int i2) {
        queueEvent(new Runnable(this) {
            final /* synthetic */ CameraSurfaceView d;

            public void run() {
                this.d.b.a(sTHumanAction);
                this.d.a.a(i, i2);
                this.d.requestRender();
            }
        });
    }
}
