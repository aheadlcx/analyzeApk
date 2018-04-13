package org.msgpack.jackson.dataformat;

public class Tuple<F, S> {
    private final F first;
    private final S second;

    public Tuple(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public F first() {
        return this.first;
    }

    public S second() {
        return this.second;
    }
}
