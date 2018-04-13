package kotlin.coroutines.experimental;

import com.umeng.analytics.pro.b;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0000H\u0002J\u0013\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J5\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u0002H\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016J(\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\b\b\u0000\u0010\u0018*\u00020\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0002¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0014\u0010\u001e\u001a\u00020\u00012\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016J\u0011\u0010\u001f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\"H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006#"}, d2 = {"Lkotlin/coroutines/experimental/CombinedContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "left", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/coroutines/experimental/CoroutineContext$Element;)V", "getElement", "()Lkotlin/coroutines/experimental/CoroutineContext$Element;", "getLeft", "()Lkotlin/coroutines/experimental/CoroutineContext;", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)Lkotlin/coroutines/experimental/CoroutineContext$Element;", "hashCode", "", "minusKey", "plus", "size", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
final class a implements CoroutineContext {
    @NotNull
    private final CoroutineContext a;
    @NotNull
    private final Element b;

    public a(@NotNull CoroutineContext coroutineContext, @NotNull Element element) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "left");
        Intrinsics.checkParameterIsNotNull(element, "element");
        this.a = coroutineContext;
        this.b = element;
    }

    @NotNull
    public final Element getElement() {
        return this.b;
    }

    @NotNull
    public final CoroutineContext getLeft() {
        return this.a;
    }

    @Nullable
    public <E extends Element> E get(@NotNull Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, PayPWDUniversalActivity.KEY);
        this = this;
        while (true) {
            E e = this.b.get(key);
            if (e != null) {
                return e;
            }
            CoroutineContext coroutineContext = this.a;
            if (!(coroutineContext instanceof a)) {
                return coroutineContext.get(key);
            }
            this = (a) coroutineContext;
        }
    }

    public <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        return function2.invoke(this.a.fold(r, function2), this.b);
    }

    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, b.M);
        return CoroutineContextImplKt.a(this, coroutineContext);
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, PayPWDUniversalActivity.KEY);
        if (this.b.get(key) != null) {
            return this.a;
        }
        CoroutineContext minusKey = this.a.minusKey(key);
        if (minusKey == this.a) {
            return this;
        }
        if (minusKey == EmptyCoroutineContext.INSTANCE) {
            return this.b;
        }
        return new a(minusKey, this.b);
    }

    private final int a() {
        return this.a instanceof a ? ((a) this.a).a() + 1 : 2;
    }

    private final boolean a(Element element) {
        return Intrinsics.areEqual(get(element.getKey()), element);
    }

    private final boolean a(a aVar) {
        while (a(aVar.b)) {
            CoroutineContext coroutineContext = aVar.a;
            if (coroutineContext instanceof a) {
                aVar = (a) coroutineContext;
            } else if (coroutineContext != null) {
                return a((Element) coroutineContext);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.CoroutineContext.Element");
            }
        }
        return false;
    }

    public boolean equals(@Nullable Object obj) {
        return ((a) this) == obj || ((obj instanceof a) && ((a) obj).a() == a() && ((a) obj).a(this));
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.hashCode();
    }

    @NotNull
    public String toString() {
        return "[" + ((String) fold("", b.INSTANCE)) + "]";
    }
}
