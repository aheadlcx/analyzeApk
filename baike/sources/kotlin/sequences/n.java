package kotlin.sequences;

import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "Lkotlin/collections/IndexedValue;", "invoke"}, k = 3, mv = {1, 1, 6})
final class n extends Lambda implements Function1<IndexedValue<? extends T>, Boolean> {
    final /* synthetic */ Function2 a;

    n(Function2 function2) {
        this.a = function2;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((IndexedValue) obj));
    }

    public final boolean invoke(@NotNull IndexedValue<? extends T> indexedValue) {
        Intrinsics.checkParameterIsNotNull(indexedValue, "it");
        return ((Boolean) this.a.invoke(Integer.valueOf(indexedValue.getIndex()), indexedValue.getValue())).booleanValue();
    }
}
