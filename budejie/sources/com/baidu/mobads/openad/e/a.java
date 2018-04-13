package com.baidu.mobads.openad.e;

import com.baidu.mobads.openad.interfaces.utils.IOAdTimer;
import com.baidu.mobads.openad.interfaces.utils.IOAdTimer.EventHandler;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.umeng.update.net.f;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class a implements IOAdTimer {
    private static String c = "OAdTimer";
    protected int a;
    private EventHandler b;
    private int d;
    private int e;
    private int f;
    private Timer g;
    private AtomicInteger h;

    public a(int i) {
        this(i, 300);
    }

    public a(int i, int i2) {
        this.a = 300;
        this.a = i2;
        int i3 = i / this.a;
        XAdSDKFoundationFacade.getInstance().getAdLogger().i(c, "RendererTimer(duration=" + i3 + ")");
        this.d = i3;
        this.e = i3;
        this.g = new Timer();
        this.h = new AtomicInteger(-1);
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.b = eventHandler;
    }

    public void start() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().i(c, "start");
        this.h.set(0);
        this.g.scheduleAtFixedRate(new b(this), 0, (long) this.a);
    }

    public void stop() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().i(c, "stop");
        this.h.set(2);
        synchronized (this) {
            if (this.g != null) {
                this.g.purge();
                this.g.cancel();
                this.g = null;
            }
        }
    }

    public void pause() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().i(c, f.a);
        this.h.set(1);
    }

    public void resume() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().i(c, "resume");
        this.h.set(0);
    }

    public int getCurrentCount() {
        return this.f;
    }

    public int getRepeatCount() {
        return this.d;
    }

    public void reset() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().i(c, "reset");
        this.h.set(-1);
        this.e = this.d;
    }
}
