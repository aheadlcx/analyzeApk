package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0007\u001a\u00020\b2\u000b\u0010\t\u001a\u00078\u0000¢\u0006\u0002\b\nH\u0002¢\u0006\u0002\u0010\u000bJ\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"kotlin/collections/AbstractMap$values$1", "Lkotlin/collections/AbstractCollection;", "(Lkotlin/collections/AbstractMap;)V", "size", "", "getSize", "()I", "contains", "", "element", "Lkotlin/UnsafeVariance;", "(Ljava/lang/Object;)Z", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class AbstractMap$values$1 extends AbstractCollection<V> {
    final /* synthetic */ AbstractMap a;

    AbstractMap$values$1(AbstractMap abstractMap) {
        this.a = abstractMap;
    }

    public boolean contains(Object obj) {
        return this.a.containsValue(obj);
    }

    @NotNull
    public Iterator<V> iterator() {
        return new AbstractMap$values$1$iterator$1(this.a.entrySet().iterator());
    }

    public int getSize() {
        return this.a.size();
    }
}
