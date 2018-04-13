package com.nostra13.universalimageloader.core.assist.deque;

class LinkedBlockingDeque$b extends LinkedBlockingDeque$a {
    final /* synthetic */ LinkedBlockingDeque d;

    private LinkedBlockingDeque$b(LinkedBlockingDeque linkedBlockingDeque) {
        this.d = linkedBlockingDeque;
        super(linkedBlockingDeque);
    }

    LinkedBlockingDeque$d<E> a() {
        return this.d.last;
    }

    LinkedBlockingDeque$d<E> a(LinkedBlockingDeque$d<E> linkedBlockingDeque$d) {
        return linkedBlockingDeque$d.b;
    }
}
