package in.srain.cube.views.ptr;

public abstract class d implements Runnable {
    private Runnable a;
    private byte b;

    public void a() {
        a(null);
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            this.a = runnable;
        }
        switch (this.b) {
            case (byte) 0:
                this.b = (byte) 1;
                run();
                return;
            case (byte) 2:
                c();
                return;
            default:
                return;
        }
    }

    public void b() {
        this.b = (byte) 0;
    }

    public void c() {
        if (this.a != null) {
            this.a.run();
        }
        this.b = (byte) 2;
    }

    public void b(Runnable runnable) {
        this.a = runnable;
    }
}
