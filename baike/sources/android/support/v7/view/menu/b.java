package android.support.v7.view.menu;

class b<T> {
    final T b;

    b(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.b = t;
    }

    public T getWrappedObject() {
        return this.b;
    }
}
