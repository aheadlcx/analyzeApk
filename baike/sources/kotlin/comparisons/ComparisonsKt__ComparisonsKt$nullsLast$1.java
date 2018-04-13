package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001j\n\u0012\u0006\u0012\u0004\u0018\u00018\u0000`\u0002B\u0005¢\u0006\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00018\u00002\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"kotlin/comparisons/ComparisonsKt__ComparisonsKt$nullsLast$1", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Comparator;)V", "compare", "", "a", "b", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class ComparisonsKt__ComparisonsKt$nullsLast$1 implements Comparator<T> {
    final /* synthetic */ Comparator a;

    ComparisonsKt__ComparisonsKt$nullsLast$1(Comparator comparator) {
        this.a = comparator;
    }

    public int compare(@Nullable T t, @Nullable T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        return this.a.compare(t, t2);
    }
}
