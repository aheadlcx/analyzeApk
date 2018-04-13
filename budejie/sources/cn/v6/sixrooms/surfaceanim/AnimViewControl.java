package cn.v6.sixrooms.surfaceanim;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import android.view.View;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneFactory;
import cn.v6.sixrooms.surfaceanim.animinterface.IOnAnimDrawListener;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.exception.NoAnimSceneFactoryException;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.view.AnimSurfaceView;
import cn.v6.sixrooms.surfaceanim.view.AnimView;
import cn.v6.sixrooms.surfaceanim.view.AnimWindowManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimViewControl {
    public static final int TYPE_SURFACE_VIEW = 0;
    public static final int TYPE_VIEW = 0;
    private AnimRenderManager a;
    private IAnimSceneFactory b;
    private Context c;
    private AnimViewControl$b d;
    private AnimWindowManager e;
    private ExecutorService f;

    public AnimViewControl(AnimSurfaceView animSurfaceView, AnimRenderConfig animRenderConfig) {
        this.b = animRenderConfig.getAnimSceneFactory();
        if (this.b == null) {
            throw new NoAnimSceneFactoryException();
        }
        Object surfaceViewAnimRenderManager = new SurfaceViewAnimRenderManager();
        surfaceViewAnimRenderManager.setFPS(animRenderConfig.getFPS());
        animSurfaceView.getHolder().addCallback(surfaceViewAnimRenderManager);
        this.a = surfaceViewAnimRenderManager;
        AnimSceneResManager.getInstance().setContext(animSurfaceView.getContext().getApplicationContext());
        this.f = Executors.newSingleThreadExecutor();
    }

    public AnimViewControl(AnimSurfaceView animSurfaceView, IAnimSceneFactory iAnimSceneFactory, IRoomParameter iRoomParameter) {
        this(animSurfaceView, iRoomParameter);
        this.b = iAnimSceneFactory;
    }

    public AnimViewControl(AnimSurfaceView animSurfaceView, IRoomParameter iRoomParameter) {
        Object surfaceViewAnimRenderManager = new SurfaceViewAnimRenderManager();
        animSurfaceView.getHolder().addCallback(surfaceViewAnimRenderManager);
        this.a = surfaceViewAnimRenderManager;
        this.a.setAnimRoomPrameter(iRoomParameter);
        AnimSceneResManager.getInstance().setContext(animSurfaceView.getContext().getApplicationContext());
        this.f = Executors.newSingleThreadExecutor();
    }

    @TargetApi(14)
    public AnimViewControl(Context context, IAnimSceneFactory iAnimSceneFactory, int i) {
        this.c = context;
        this.b = iAnimSceneFactory;
        this.e = new AnimWindowManager(context);
        View view = null;
        Object viewAnimRenderManager;
        if (i == 0) {
            view = new AnimView(this.c);
            viewAnimRenderManager = new ViewAnimRenderManager(view);
            view.setOnDrawListener(viewAnimRenderManager);
            this.a = viewAnimRenderManager;
        } else if (i == 0) {
            viewAnimRenderManager = new SurfaceViewAnimRenderManager();
            view = new AnimSurfaceView(this.c);
            view.getHolder().addCallback(viewAnimRenderManager);
            this.a = viewAnimRenderManager;
        }
        this.e.createWindow(view);
        AnimSceneResManager.getInstance().setContext(context.getApplicationContext());
        this.f = Executors.newSingleThreadExecutor();
        this.d = new AnimViewControl$b(this);
        this.c.registerComponentCallbacks(this.d);
    }

    public AnimRenderManager getRenderManager() {
        return this.a;
    }

    public void addAnimScene(Object obj, IAnimSceneFactory iAnimSceneFactory) {
        if (this.f != null && !this.f.isShutdown()) {
            this.f.submit(new AnimViewControl$a(this, obj, iAnimSceneFactory));
        }
    }

    public void addAnimScene(Object obj) {
        if (this.f != null && !this.f.isShutdown()) {
            this.f.submit(new AnimViewControl$a(this, obj));
        }
    }

    public void resetAnimFrame() {
        if (this.a != null) {
            this.a.resetRender();
        }
    }

    public void addAnimDrawListener(IOnAnimDrawListener iOnAnimDrawListener) {
        if (this.a != null) {
            this.a.addAnimDrawListener(iOnAnimDrawListener);
        }
    }

    @TargetApi(14)
    public void release() {
        AnimSceneResManager.getInstance().release();
        if (this.a != null) {
            this.a.release();
        }
        if (this.c != null) {
            this.c.unregisterComponentCallbacks(this.d);
        }
        if (this.f != null) {
            this.f.shutdownNow();
        }
    }

    public void setOffset(int i, int i2) {
        if (this.a != null) {
            Log.e("onLayout", "y=" + i2);
            this.a.setOffset(i, i2);
        }
    }
}
