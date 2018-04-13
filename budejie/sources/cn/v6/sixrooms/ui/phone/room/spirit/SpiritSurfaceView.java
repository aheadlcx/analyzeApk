package cn.v6.sixrooms.ui.phone.room.spirit;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.view.interfaces.IOnConfigurationChangedListenter;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SpiritSurfaceView extends SurfaceView implements Callback, ISpiritControl {
    private final int a = 100;
    private final int b = 10000;
    private ExecutorService c;
    private SurfaceHolder d;
    private List<ISpirit> e = new LinkedList();
    private Lock f = new ReentrantLock();
    private Condition g = this.f.newCondition();
    private Lock h = new ReentrantLock();
    private Condition i = this.h.newCondition();
    private ISpirit j;
    private Handler k = new Handler();
    private boolean l;
    private Future<?> m;
    private boolean n;
    private long o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;

    public SpiritSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public SpiritSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SpiritSurfaceView(Context context) {
        super(context);
        a();
    }

    private void a() {
        setVisibility(4);
        this.d = getHolder();
        this.d.addCallback(this);
        setZOrderOnTop(true);
        this.d.setFormat(-3);
        setBackgroundColor(0);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.l = true;
        if (this.c == null || this.c.isShutdown()) {
            this.c = Executors.newCachedThreadPool();
        }
        if (this.m == null || this.m.isCancelled() || this.m.isDone()) {
            this.m = this.c.submit(new SpiritSurfaceView$a(this, (byte) 0));
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        LogUtils.i(AppLinkConstants.TIME, "surfaceDestroyed");
        this.l = false;
        this.c.shutdownNow();
        if (this.e.size() > 0) {
            this.n = true;
            this.o = System.currentTimeMillis();
            this.p = this.e.size();
        }
    }

    private int getSpiritDelayedCount() {
        if (!this.n) {
            return 0;
        }
        int currentTimeMillis = (int) ((System.currentTimeMillis() - this.o) / 10000);
        if (currentTimeMillis > this.p) {
            return this.p;
        }
        return currentTimeMillis;
    }

    public void addSpirit(ISpirit iSpirit) {
        if (this.c == null || this.c.isShutdown()) {
            this.c = Executors.newCachedThreadPool();
        }
        this.c.execute(new a(this, iSpirit));
    }

    public void removeSpirit(ISpirit iSpirit) {
        this.c.execute(new c(this, iSpirit));
    }

    public void clearSapirit() {
        if (this.e.size() > 0) {
            this.e.clear();
        }
        this.k.post(new d(this));
    }

    public void onResume() {
        this.l = true;
        this.r = false;
        if (this.n) {
            this.k.postDelayed(new e(this), 500);
        }
    }

    public void onPause() {
        this.r = true;
    }

    public ISpirit getCurSpirit() {
        return this.j;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.j != null && (this.j instanceof IOnConfigurationChangedListenter)) {
            ((IOnConfigurationChangedListenter) this.j).onConfigurationChanged(configuration);
        }
    }
}
