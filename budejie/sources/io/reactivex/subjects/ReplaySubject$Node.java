package io.reactivex.subjects;

import java.util.concurrent.atomic.AtomicReference;

final class ReplaySubject$Node<T> extends AtomicReference<ReplaySubject$Node<T>> {
    private static final long serialVersionUID = 6404226426336033100L;
    final T value;

    ReplaySubject$Node(T t) {
        this.value = t;
    }
}
