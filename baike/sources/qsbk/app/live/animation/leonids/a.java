package qsbk.app.live.animation.leonids;

import java.util.TimerTask;

class a extends TimerTask {
    final /* synthetic */ ParticleSystem a;

    a(ParticleSystem particleSystem) {
        this.a = particleSystem;
    }

    public void run() {
        this.a.b(this.a.h);
        this.a.h = this.a.h + 50;
    }
}
