package cn.v6.sixrooms.login.manager;

final class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void run() {
        RegisterManager.a(this.a.a, true);
        if (RegisterManager.b(this.a.a) != null) {
            RegisterManager.b(this.a.a).startRunCountdown();
        }
    }
}
