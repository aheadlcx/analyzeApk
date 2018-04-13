package io.reactivex.processors;

import java.util.concurrent.atomic.AtomicReference;

final class ReplayProcessor$Node<T> extends AtomicReference<ReplayProcessor$Node<T>> {
    private static final long serialVersionUID = 6404226426336033100L;
    final T value;

    ReplayProcessor$Node(T t) {
        this.value = t;
    }
}
