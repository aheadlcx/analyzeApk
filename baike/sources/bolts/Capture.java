package bolts;

public class Capture<T> {
    private T a;

    public Capture(T t) {
        this.a = t;
    }

    public T get() {
        return this.a;
    }

    public void set(T t) {
        this.a = t;
    }
}
