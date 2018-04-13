package org.greenrobot.eventbus;

class a implements Runnable {
    private final g a = new g();
    private final EventBus b;

    a(EventBus eventBus) {
        this.b = eventBus;
    }

    public void enqueue(i iVar, Object obj) {
        this.a.a(f.a(iVar, obj));
        this.b.a().execute(this);
    }

    public void run() {
        f a = this.a.a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.a(a);
    }
}
