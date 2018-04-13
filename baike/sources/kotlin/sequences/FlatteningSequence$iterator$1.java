package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010(\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\t\u0010\f\u001a\u00020\u000bH\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000eR\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0005¨\u0006\u000f"}, d2 = {"kotlin/sequences/FlatteningSequence$iterator$1", "", "(Lkotlin/sequences/FlatteningSequence;)V", "itemIterator", "getItemIterator", "()Ljava/util/Iterator;", "setItemIterator", "(Ljava/util/Iterator;)V", "iterator", "getIterator", "ensureItemIterator", "", "hasNext", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class FlatteningSequence$iterator$1 implements Iterator<E>, KMappedMarker {
    final /* synthetic */ FlatteningSequence a;
    @NotNull
    private final Iterator<T> b;
    @Nullable
    private Iterator<? extends E> c;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    FlatteningSequence$iterator$1(FlatteningSequence flatteningSequence) {
        this.a = flatteningSequence;
        this.b = flatteningSequence.a.iterator();
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.b;
    }

    @Nullable
    public final Iterator<E> getItemIterator() {
        return this.c;
    }

    public final void setItemIterator(@Nullable Iterator<? extends E> it) {
        this.c = it;
    }

    public E next() {
        if (a()) {
            Iterator it = this.c;
            if (it == null) {
                Intrinsics.throwNpe();
            }
            return it.next();
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        return a();
    }

    private final boolean a() {
        Iterator it = this.c;
        if (Intrinsics.areEqual(it != null ? Boolean.valueOf(it.hasNext()) : null, Boolean.valueOf(false))) {
            this.c = (Iterator) null;
        }
        while (this.c == null) {
            if (!this.b.hasNext()) {
                return false;
            }
            Iterator it2 = (Iterator) this.a.c.invoke(this.a.b.invoke(this.b.next()));
            if (it2.hasNext()) {
                this.c = it2;
                return true;
            }
        }
        return true;
    }
}
