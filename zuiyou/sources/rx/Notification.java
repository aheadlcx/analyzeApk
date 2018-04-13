package rx;

public final class Notification<T> {
    private static final Notification<Void> d = new Notification(Kind.OnCompleted, null, null);
    private final Kind a;
    private final Throwable b;
    private final T c;

    public enum Kind {
        OnNext,
        OnError,
        OnCompleted
    }

    private Notification(Kind kind, T t, Throwable th) {
        this.c = t;
        this.b = th;
        this.a = kind;
    }

    public Throwable a() {
        return this.b;
    }

    public T b() {
        return this.c;
    }

    public boolean c() {
        return g() && this.c != null;
    }

    public boolean d() {
        return f() && this.b != null;
    }

    public Kind e() {
        return this.a;
    }

    public boolean f() {
        return e() == Kind.OnError;
    }

    public boolean g() {
        return e() == Kind.OnNext;
    }

    public String toString() {
        StringBuilder append = new StringBuilder(64).append('[').append(super.toString()).append(' ').append(e());
        if (c()) {
            append.append(' ').append(b());
        }
        if (d()) {
            append.append(' ').append(a().getMessage());
        }
        append.append(']');
        return append.toString();
    }

    public int hashCode() {
        int hashCode = e().hashCode();
        if (c()) {
            hashCode = (hashCode * 31) + b().hashCode();
        }
        if (d()) {
            return (hashCode * 31) + a().hashCode();
        }
        return hashCode;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (notification.e() != e() || ((this.c != notification.c && (this.c == null || !this.c.equals(notification.c))) || (this.b != notification.b && (this.b == null || !this.b.equals(notification.b))))) {
            z = false;
        }
        return z;
    }
}
