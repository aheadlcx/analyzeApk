package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@NotThreadSafe
final class a<E> {
    private final LinkedList<E> a = new LinkedList();
    private final Map<Class<?>, E> b = new HashMap();

    private void a(E e) {
        Object remove = this.b.remove(e.getClass());
        if (remove != null) {
            this.a.remove(remove);
        }
        this.b.put(e.getClass(), e);
    }

    public a<E> addFirst(E e) {
        if (e != null) {
            a(e);
            this.a.addFirst(e);
        }
        return this;
    }

    public a<E> addLast(E e) {
        if (e != null) {
            a(e);
            this.a.addLast(e);
        }
        return this;
    }

    public a<E> addAllFirst(Collection<E> collection) {
        if (collection != null) {
            for (E addFirst : collection) {
                addFirst(addFirst);
            }
        }
        return this;
    }

    public a<E> addAllFirst(E... eArr) {
        if (eArr != null) {
            for (Object addFirst : eArr) {
                addFirst(addFirst);
            }
        }
        return this;
    }

    public a<E> addAllLast(Collection<E> collection) {
        if (collection != null) {
            for (E addLast : collection) {
                addLast(addLast);
            }
        }
        return this;
    }

    public a<E> addAllLast(E... eArr) {
        if (eArr != null) {
            for (Object addLast : eArr) {
                addLast(addLast);
            }
        }
        return this;
    }

    public LinkedList<E> build() {
        return new LinkedList(this.a);
    }
}
