package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u00012\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/CharSequence;"}, k = 3, mv = {1, 1, 6})
final class a extends Lambda implements Function1<E, CharSequence> {
    final /* synthetic */ AbstractCollection a;

    a(AbstractCollection abstractCollection) {
        this.a = abstractCollection;
        super(1);
    }

    @NotNull
    public final CharSequence invoke(E e) {
        return e == this.a ? "(this Collection)" : String.valueOf(e);
    }
}
