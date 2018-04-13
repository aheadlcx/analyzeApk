package org.greenrobot.eventbus;

final class g {
    private f a;
    private f b;

    g() {
    }

    synchronized void a(f fVar) {
        if (fVar == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        if (this.b != null) {
            this.b.c = fVar;
            this.b = fVar;
        } else if (this.a == null) {
            this.b = fVar;
            this.a = fVar;
        } else {
            throw new IllegalStateException("Head present, but no tail");
        }
        notifyAll();
    }

    synchronized f a() {
        f fVar;
        fVar = this.a;
        if (this.a != null) {
            this.a = this.a.c;
            if (this.a == null) {
                this.b = null;
            }
        }
        return fVar;
    }

    synchronized f a(int i) throws InterruptedException {
        if (this.a == null) {
            wait((long) i);
        }
        return a();
    }
}
