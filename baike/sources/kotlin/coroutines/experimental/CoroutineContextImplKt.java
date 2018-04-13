package kotlin.coroutines.experimental;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¨\u0006\u0003"}, d2 = {"plusImpl", "Lkotlin/coroutines/experimental/CoroutineContext;", "context", "kotlin-stdlib"}, k = 2, mv = {1, 1, 6})
public final class CoroutineContextImplKt {
    private static final CoroutineContext a(@NotNull CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        if (coroutineContext2 == EmptyCoroutineContext.INSTANCE) {
            return coroutineContext;
        }
        return (CoroutineContext) coroutineContext2.fold(coroutineContext, c.INSTANCE);
    }
}
