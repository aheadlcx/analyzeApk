package com.nostra13.universalimageloader.core.assist.deque;

class LinkedBlockingDeque$c extends LinkedBlockingDeque$a {
    final /* synthetic */ LinkedBlockingDeque d;

    private LinkedBlockingDeque$c(LinkedBlockingDeque linkedBlockingDeque) {
        this.d = linkedBlockingDeque;
        super(linkedBlockingDeque);
    }

    LinkedBlockingDeque$d<E> a() {
        return this.d.first;
    }

    LinkedBlockingDeque$d<E> a(LinkedBlockingDeque$d<E> linkedBlockingDeque$d) {
        return linkedBlockingDeque$d.c;
    }
}
