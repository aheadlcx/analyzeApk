package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "K", "", "it", "invoke", "(Ljava/lang/Object;)I"}, k = 3, mv = {1, 1, 6})
public final class CollectionsKt__CollectionsKt$binarySearchBy$1 extends Lambda implements Function1<T, Integer> {
    final /* synthetic */ Function1 a;
    final /* synthetic */ Comparable b;

    public CollectionsKt__CollectionsKt$binarySearchBy$1(Function1 function1, Comparable comparable) {
        this.a = function1;
        this.b = comparable;
        super(1);
    }

    /* renamed from: invoke */
    public /* synthetic */ Object m3invoke(Object obj) {
        return Integer.valueOf(invoke(obj));
    }

    public final int invoke(T t) {
        return a.compareValues((Comparable) this.a.invoke(t), this.b);
    }
}
