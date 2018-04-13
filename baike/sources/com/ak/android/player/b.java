package com.ak.android.player;

import android.view.View;
import com.ak.android.bridge.DynamicObject;
import com.ak.android.bridge.d;

public final class b implements VideoAdPlayer {
    protected final DynamicObject a;

    public b(DynamicObject dynamicObject) {
        this.a = dynamicObject;
    }

    public final View getUI() {
        if (this.a != null) {
            return (View) this.a.invoke(d.ad, new Object[0]);
        }
        return null;
    }

    public final void setListener(VideoAdPlayerListener videoAdPlayerListener) {
        if (this.a != null) {
            this.a.invoke(d.ae, new a(videoAdPlayerListener));
        }
    }

    public final void prepare() {
        if (this.a != null) {
            this.a.invoke(d.af, new Object[0]);
        }
    }

    public final void play(boolean z) {
        if (this.a != null) {
            this.a.invoke(d.ag, Boolean.valueOf(z));
        }
    }

    public final void pause() {
        if (this.a != null) {
            this.a.invoke(d.ah, new Object[0]);
        }
    }

    public final void continuePlay() {
        if (this.a != null) {
            this.a.invoke(d.ai, new Object[0]);
        }
    }

    public final void stop() {
        if (this.a != null) {
            this.a.invoke(d.aj, new Object[0]);
        }
    }

    public final void full() {
        if (this.a != null) {
            this.a.invoke(d.ak, new Object[0]);
        }
    }

    public final void restore() {
        if (this.a != null) {
            this.a.invoke(d.al, new Object[0]);
        }
    }

    public final void destroy() {
        if (this.a != null) {
            this.a.invoke(d.am, new Object[0]);
        }
    }
}
