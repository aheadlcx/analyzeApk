package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u0012\u0012\u0004\u0012\u00028\u00000\u0001j\b\u0012\u0004\u0012\u00028\u0000`\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"kotlin/comparisons/ComparisonsKt__ComparisonsKt$thenDescending$1", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Comparator;Ljava/util/Comparator;)V", "compare", "", "a", "b", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class ComparisonsKt__ComparisonsKt$thenDescending$1 implements Comparator<T> {
    final /* synthetic */ Comparator a;
    final /* synthetic */ Comparator b;

    ComparisonsKt__ComparisonsKt$thenDescending$1(Comparator<T> comparator, Comparator comparator2) {
        this.a = comparator;
        this.b = comparator2;
    }

    public int compare(T t, T t2) {
        int compare = this.a.compare(t, t2);
        return compare != 0 ? compare : this.b.compare(t2, t);
    }
}
