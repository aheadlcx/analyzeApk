package in.srain.cube.views.ptr;

public abstract class PtrUIHandlerHook implements Runnable {
    private Runnable a;
    private byte b = (byte) 0;

    public void takeOver() {
        takeOver(null);
    }

    public void takeOver(Runnable runnable) {
        if (runnable != null) {
            this.a = runnable;
        }
        switch (this.b) {
            case (byte) 0:
                this.b = (byte) 1;
                run();
                return;
            case (byte) 2:
                resume();
                return;
            default:
                return;
        }
    }

    public void reset() {
        this.b = (byte) 0;
    }

    public void resume() {
        if (this.a != null) {
            this.a.run();
        }
        this.b = (byte) 2;
    }

    public void setResumeAction(Runnable runnable) {
        this.a = runnable;
    }
}
