package org.greenrobot.eventbus;

final class j {
    private i a;
    private i b;

    j() {
    }

    synchronized void a(i iVar) {
        if (iVar == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        if (this.b != null) {
            this.b.c = iVar;
            this.b = iVar;
        } else if (this.a == null) {
            this.b = iVar;
            this.a = iVar;
        } else {
            throw new IllegalStateException("Head present, but no tail");
        }
        notifyAll();
    }

    synchronized i a() {
        i iVar;
        iVar = this.a;
        if (this.a != null) {
            this.a = this.a.c;
            if (this.a == null) {
                this.b = null;
            }
        }
        return iVar;
    }

    synchronized i a(int i) throws InterruptedException {
        if (this.a == null) {
            wait((long) i);
        }
        return a();
    }
}
