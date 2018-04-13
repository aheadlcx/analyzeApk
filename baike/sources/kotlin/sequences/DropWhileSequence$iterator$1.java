package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\t\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"kotlin/sequences/DropWhileSequence$iterator$1", "", "(Lkotlin/sequences/DropWhileSequence;)V", "dropState", "", "getDropState", "()I", "setDropState", "(I)V", "iterator", "getIterator", "()Ljava/util/Iterator;", "nextItem", "getNextItem", "()Ljava/lang/Object;", "setNextItem", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "drop", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class DropWhileSequence$iterator$1 implements Iterator<T>, KMappedMarker {
    final /* synthetic */ DropWhileSequence a;
    @NotNull
    private final Iterator<T> b;
    private int c = -1;
    @Nullable
    private T d;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    DropWhileSequence$iterator$1(DropWhileSequence dropWhileSequence) {
        this.a = dropWhileSequence;
        this.b = dropWhileSequence.a.iterator();
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.b;
    }

    public final int getDropState() {
        return this.c;
    }

    public final void setDropState(int i) {
        this.c = i;
    }

    @Nullable
    public final T getNextItem() {
        return this.d;
    }

    public final void setNextItem(@Nullable T t) {
        this.d = t;
    }

    private final void a() {
        while (this.b.hasNext()) {
            Object next = this.b.next();
            if (!((Boolean) this.a.b.invoke(next)).booleanValue()) {
                this.d = next;
                this.c = 1;
                return;
            }
        }
        this.c = 0;
    }

    public T next() {
        if (this.c == -1) {
            a();
        }
        if (this.c != 1) {
            return this.b.next();
        }
        T t = this.d;
        this.d = null;
        this.c = 0;
        return t;
    }

    public boolean hasNext() {
        if (this.c == -1) {
            a();
        }
        if (this.c == 1 || this.b.hasNext()) {
            return true;
        }
        return false;
    }
}
