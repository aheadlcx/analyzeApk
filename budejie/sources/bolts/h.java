package bolts;

public class h<TResult> {
    private final g<TResult> a = new g();

    public g<TResult> a() {
        return this.a;
    }

    public boolean b() {
        return this.a.g();
    }

    public boolean a(TResult tResult) {
        return this.a.b((Object) tResult);
    }

    public boolean a(Exception exception) {
        return this.a.b(exception);
    }

    public void c() {
        if (!b()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void b(TResult tResult) {
        if (!a((Object) tResult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public void b(Exception exception) {
        if (!a(exception)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
