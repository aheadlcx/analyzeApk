package cn.v6.sixrooms.surfaceanim;

import android.graphics.Canvas;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimRender;
import cn.v6.sixrooms.surfaceanim.animinterface.IFrameRailManager;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.protocol.SceneBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

public abstract class AnimSceneFrame implements IAnimRender {
    private int a = 1;
    private List<AnimScene> b;
    private List<AnimScene> c;
    private List<AnimScene> d;
    private volatile AnimScene e;
    private int f;
    private int g;
    private SceneBean h;
    private volatile int i;
    private ExecutorService j;
    protected IFrameRailManager mRailManager;

    protected abstract IFrameRailManager initRailManager(IRoomParameter iRoomParameter);

    protected abstract int initVisibleSceneCounts();

    public abstract void sceneRenderFinish(AnimScene animScene);

    public abstract void sceneRenderPre(AnimScene animScene);

    public AnimSceneFrame(IRoomParameter iRoomParameter) {
        a(iRoomParameter);
    }

    public AnimSceneFrame() {
        a(null);
    }

    private void a(IRoomParameter iRoomParameter) {
        this.a = initVisibleSceneCounts();
        this.b = Collections.synchronizedList(new ArrayList());
        this.c = new ArrayList(this.a);
        this.d = new ArrayList(this.a);
        this.mRailManager = initRailManager(iRoomParameter);
    }

    public void setSceneBean(SceneBean sceneBean) {
        this.h = sceneBean;
    }

    public int getVisibleSceneCounts() {
        return this.a;
    }

    public void addAnimScene(AnimScene animScene) {
        this.b.add(animScene);
    }

    public void removeAnimScene(AnimScene animScene) {
        this.b.remove(animScene);
    }

    public void setOffset(int i, int i2) {
        this.f = i;
        this.g = i2;
    }

    public boolean render(Canvas canvas) {
        if (this.b == null) {
            return true;
        }
        if (this.b.size() == 0 && this.i == 0) {
            return true;
        }
        try {
            if (this.i <= this.a && this.b.size() > 0) {
                if (this.j == null) {
                    this.j = CachedThreadPoolManager.getInstance().getThreadPool();
                }
                if (!(this.j == null || this.j.isShutdown())) {
                    this.j.submit(new d(this));
                }
            }
        } catch (OutOfMemoryError e) {
            System.gc();
        }
        int i = 0;
        while (i < this.a && i < this.i) {
            AnimScene animScene = (AnimScene) this.c.get(i);
            animScene.setOffset(this.f, this.g);
            if (animScene.getRenderStatus() == 0) {
                sceneRenderPre(animScene);
            }
            if (animScene.render(canvas)) {
                boolean z;
                sceneRenderFinish(animScene);
                for (AnimScene animScene2 : this.c) {
                    if (animScene2 != animScene && animScene2.getClass() == animScene.getClass()) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (this.e != null && this.e.getClass() == animScene.getClass()) {
                    z = false;
                }
                if (z) {
                    animScene.releaseResources();
                }
                this.d.add(animScene);
            }
            i++;
        }
        if (this.d.size() > 0) {
            this.c.removeAll(this.d);
            this.i -= this.d.size();
            this.d.clear();
        }
        if (this.i == 0 && this.b.size() == 0) {
            return true;
        }
        return false;
    }

    public void clearAllAnimScene() {
        synchronized (this.c) {
            this.b.clear();
            this.c.clear();
            this.d.clear();
            this.e = null;
            if (this.mRailManager != null) {
                this.mRailManager.resetRail();
            }
            this.i = 0;
        }
    }
}
