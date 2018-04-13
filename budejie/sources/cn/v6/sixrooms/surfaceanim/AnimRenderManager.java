package cn.v6.sixrooms.surfaceanim;

import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import cn.v6.sixrooms.surfaceanim.animinterface.IOnAnimDrawListener;
import cn.v6.sixrooms.surfaceanim.animinterface.IOnDrawListener;
import cn.v6.sixrooms.surfaceanim.animinterface.IRoomParameter;
import cn.v6.sixrooms.surfaceanim.animinterface.ISurfaceChangedListener;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.RenderRect;
import java.util.ArrayList;
import java.util.List;

public abstract class AnimRenderManager implements IOnDrawListener {
    public static final int ANIM_RENDER_PAUSE = 3;
    public static final int ANIM_RENDER_SIZE_CHANGED = 4;
    public static final int ANIM_RENDER_START = 1;
    public static final int ANIM_RENDER_STOP = 2;
    private int a = 24;
    private int b = (1000 / this.a);
    private long c = 0;
    private boolean d;
    private Object e = new Object();
    private SparseArray<AnimSceneFrame> f = new SparseArray();
    private RenderRect g = new RenderRect();
    private int h;
    private int i;
    private int j;
    private List<IOnAnimDrawListener> k;
    private boolean l;
    private Handler m = new Handler(Looper.getMainLooper());
    protected Handler mRenderHandler;
    private IRoomParameter n;

    public SparseArray<AnimSceneFrame> getAnimSceneFrames() {
        return this.f;
    }

    public void setOffset(int i, int i2) {
        this.i = i;
        this.j = i2;
    }

    private void a(int i) {
        if (this.k != null) {
            for (IOnAnimDrawListener bVar : this.k) {
                this.m.post(new b(this, bVar, i));
            }
        }
    }

    public void addAnimDrawListener(IOnAnimDrawListener iOnAnimDrawListener) {
        if (this.k == null) {
            this.k = new ArrayList(1);
        }
        this.k.add(iOnAnimDrawListener);
    }

    public void setAnimRoomPrameter(IRoomParameter iRoomParameter) {
        this.n = iRoomParameter;
    }

    public IRoomParameter getAnimRoomParameterable() {
        return this.n;
    }

    protected void setRenderRect(int i, int i2) {
        this.g.setWidth(i);
        this.g.setHeight(i2);
    }

    protected void initRenderHandler() {
        this.mRenderHandler = new c(this);
    }

    public void setFPS(int i) {
        this.a = i;
        this.b = 1000 / i;
    }

    protected void renderStop() {
        a(false);
        this.mRenderHandler.getLooper().quit();
    }

    protected void renderSizeChanged(int i, int i2) {
        AnimSceneResManager.getInstance().surfaceChanged();
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            AnimSceneFrame animSceneFrame = (AnimSceneFrame) this.f.get(this.f.keyAt(i3));
            if (animSceneFrame != null && (animSceneFrame instanceof ISurfaceChangedListener)) {
                ((ISurfaceChangedListener) animSceneFrame).surfaceChanged(this.g);
            }
        }
    }

    protected void renderPause() {
        for (int i = 0; i < this.f.size(); i++) {
            ((AnimSceneFrame) this.f.get(this.f.keyAt(i))).clearAllAnimScene();
        }
        a(false);
        this.l = false;
        a(3);
    }

    protected void render() {
        if (this.h != 2) {
            a(true);
        }
    }

    public void onDraw(Canvas canvas) {
        if (a()) {
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = false;
            for (int i = 0; i < this.f.size(); i++) {
                AnimSceneFrame animSceneFrame = (AnimSceneFrame) this.f.get(this.f.keyAt(i));
                animSceneFrame.setOffset(this.i, this.j);
                z = !animSceneFrame.render(canvas) || z;
            }
            long currentTimeMillis2 = ((long) this.b) - (System.currentTimeMillis() - currentTimeMillis);
            if (currentTimeMillis2 < 0) {
                currentTimeMillis2 = 0;
            }
            if (z) {
                if (!this.l) {
                    this.l = true;
                    a(1);
                }
                this.mRenderHandler.sendEmptyMessageDelayed(1, currentTimeMillis2);
                return;
            }
            a(2);
            this.l = false;
            a(false);
            this.c = 0;
            canvas.drawColor(0, Mode.CLEAR);
        }
    }

    public void addAnimScenes(AnimScene[] animSceneArr) {
        for (AnimScene addAnimScene : animSceneArr) {
            addAnimScene(addAnimScene);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addAnimScene(cn.v6.sixrooms.surfaceanim.AnimScene r8) {
        /*
        r7 = this;
        r5 = 2;
        r4 = 1;
        r0 = r7.h;
        if (r0 != r5) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r2 = r7.e;
        monitor-enter(r2);
        r0 = r7.f;	 Catch:{ all -> 0x0010 }
        if (r0 != 0) goto L_0x0013;
    L_0x000e:
        monitor-exit(r2);	 Catch:{ all -> 0x0010 }
        goto L_0x0006;
    L_0x0010:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0010 }
        throw r0;
    L_0x0013:
        r0 = r7.a;	 Catch:{ all -> 0x0010 }
        r8.setFPS(r0);	 Catch:{ all -> 0x0010 }
        r0 = cn.v6.sixrooms.surfaceanim.util.AnimFrameBuilder.getAnimFrameKey(r8);	 Catch:{ all -> 0x0010 }
        r1 = r7.f;	 Catch:{ all -> 0x0010 }
        r1 = r1.indexOfKey(r0);	 Catch:{ all -> 0x0010 }
        if (r1 >= 0) goto L_0x008b;
    L_0x0024:
        r1 = r7.getAnimRoomParameterable();	 Catch:{ all -> 0x0010 }
        r1 = cn.v6.sixrooms.surfaceanim.util.AnimFrameBuilder.createAnimFrame(r8, r1);	 Catch:{ all -> 0x0010 }
        if (r1 != 0) goto L_0x0097;
    L_0x002e:
        r0 = r8.getClass();	 Catch:{ all -> 0x0010 }
        r1 = cn.v6.sixrooms.surfaceanim.annotation.SceneFrameOwner.class;
        r0 = r0.getAnnotation(r1);	 Catch:{ all -> 0x0010 }
        r0 = (cn.v6.sixrooms.surfaceanim.annotation.SceneFrameOwner) r0;	 Catch:{ all -> 0x0010 }
        r0 = r0.value();	 Catch:{ Exception -> 0x0054 }
        r1 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x0054 }
        r0 = r1.newInstance();	 Catch:{ Exception -> 0x0054 }
        r0 = (cn.v6.sixrooms.surfaceanim.AnimSceneFrame) r0;	 Catch:{ Exception -> 0x0054 }
        r1 = r1.hashCode();	 Catch:{ Exception -> 0x0054 }
    L_0x004c:
        if (r0 != 0) goto L_0x005f;
    L_0x004e:
        r0 = new cn.v6.sixrooms.surfaceanim.exception.AnimSceneFrameRegisterException;	 Catch:{ all -> 0x0010 }
        r0.<init>();	 Catch:{ all -> 0x0010 }
        throw r0;	 Catch:{ all -> 0x0010 }
    L_0x0054:
        r0 = move-exception;
        r1 = new cn.v6.sixrooms.surfaceanim.exception.AnimSceneFrameRegisterException;	 Catch:{ all -> 0x0010 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0010 }
        r1.<init>(r0);	 Catch:{ all -> 0x0010 }
        throw r1;	 Catch:{ all -> 0x0010 }
    L_0x005f:
        r0.addAnimScene(r8);	 Catch:{ all -> 0x0010 }
        r3 = r7.f;	 Catch:{ all -> 0x0010 }
        r3.put(r1, r0);	 Catch:{ all -> 0x0010 }
        r1 = r0 instanceof cn.v6.sixrooms.surfaceanim.animinterface.IAnimFrameAddListener;	 Catch:{ all -> 0x0010 }
        if (r1 == 0) goto L_0x0072;
    L_0x006b:
        r0 = (cn.v6.sixrooms.surfaceanim.animinterface.IAnimFrameAddListener) r0;	 Catch:{ all -> 0x0010 }
        r1 = r7.g;	 Catch:{ all -> 0x0010 }
        r0.onAnimFrameAdd(r1);	 Catch:{ all -> 0x0010 }
    L_0x0072:
        monitor-exit(r2);	 Catch:{ all -> 0x0010 }
        r0 = r7.a();
        if (r0 != 0) goto L_0x0006;
    L_0x0079:
        r0 = r7.mRenderHandler;
        if (r0 == 0) goto L_0x0006;
    L_0x007d:
        r0 = r7.h;
        if (r0 == r5) goto L_0x0006;
    L_0x0081:
        r7.a(r4);
        r0 = r7.mRenderHandler;
        r0.sendEmptyMessage(r4);
        goto L_0x0006;
    L_0x008b:
        r1 = r7.f;	 Catch:{ all -> 0x0010 }
        r0 = r1.get(r0);	 Catch:{ all -> 0x0010 }
        r0 = (cn.v6.sixrooms.surfaceanim.AnimSceneFrame) r0;	 Catch:{ all -> 0x0010 }
        r0.addAnimScene(r8);	 Catch:{ all -> 0x0010 }
        goto L_0x0072;
    L_0x0097:
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x004c;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.surfaceanim.AnimRenderManager.addAnimScene(cn.v6.sixrooms.surfaceanim.AnimScene):void");
    }

    private synchronized boolean a() {
        return this.d;
    }

    private synchronized void a(boolean z) {
        this.d = z;
    }

    public void release() {
        synchronized (this.e) {
            this.f.clear();
            this.f = null;
            if (this.k != null) {
                this.k.clear();
                this.k = null;
            }
        }
    }

    public void resetRender() {
        if (this.mRenderHandler != null) {
            this.mRenderHandler.removeMessages(1);
            this.mRenderHandler.sendEmptyMessage(3);
        }
    }

    public void onDrawSizeChanged(int i, int i2) {
        setRenderRect(i, i2);
        renderSizeChanged(i, i2);
    }
}
