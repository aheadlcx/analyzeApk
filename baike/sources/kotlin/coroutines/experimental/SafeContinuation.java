package kotlin.coroutines.experimental;

import com.umeng.analytics.pro.b;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@PublishedApi
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0001\u0018\u0000 \u0015*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u0015\u0016B\u0015\b\u0011\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004B\u001f\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0001J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\b\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "delegate", "(Lkotlin/coroutines/experimental/Continuation;)V", "initialResult", "", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "result", "getResult", "resume", "", "value", "(Ljava/lang/Object;)V", "resumeWithException", "exception", "", "Companion", "Fail", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class SafeContinuation<T> implements Continuation<T> {
    public static final Companion Companion = new Companion();
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> e = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "a");
    private volatile Object a;
    private final Continuation<T> b;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R`\u0010\u0003\u001aF\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001 \u0006*\"\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00040\u00048\u0002X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation$Companion;", "", "()V", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/experimental/SafeContinuation;", "kotlin.jvm.PlatformType", "RESULT$annotations", "getRESULT", "()Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "RESUMED", "getRESUMED", "()Ljava/lang/Object;", "UNDECIDED", "getUNDECIDED", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
    public static final class Companion {
        private Companion() {
        }

        private final Object a() {
            return SafeContinuation.c;
        }

        private final Object b() {
            return SafeContinuation.d;
        }

        private final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> c() {
            return SafeContinuation.e;
        }
    }

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation$Fail;", "", "exception", "", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
    private static final class a {
        @NotNull
        private final Throwable a;

        public a(@NotNull Throwable th) {
            Intrinsics.checkParameterIsNotNull(th, b.ao);
            this.a = th;
        }

        @NotNull
        public final Throwable getException() {
            return this.a;
        }
    }

    public SafeContinuation(@NotNull Continuation<? super T> continuation, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(continuation, "delegate");
        this.b = continuation;
        this.a = obj;
    }

    @PublishedApi
    public SafeContinuation(@NotNull Continuation<? super T> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "delegate");
        this(continuation, Companion.a());
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.b.getContext();
    }

    public void resume(T t) {
        while (true) {
            Object obj = this.a;
            if (obj == Companion.a()) {
                if (Companion.c().compareAndSet(this, Companion.a(), t)) {
                    return;
                }
            } else if (obj != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                throw new IllegalStateException("Already resumed");
            } else if (Companion.c().compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), Companion.b())) {
                this.b.resume(t);
                return;
            }
        }
    }

    public void resumeWithException(@NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, b.ao);
        while (true) {
            Object obj = this.a;
            if (obj == Companion.a()) {
                if (Companion.c().compareAndSet(this, Companion.a(), new a(th))) {
                    return;
                }
            } else if (obj != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                throw new IllegalStateException("Already resumed");
            } else if (Companion.c().compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), Companion.b())) {
                this.b.resumeWithException(th);
                return;
            }
        }
    }

    @Nullable
    @PublishedApi
    public final Object getResult() {
        Object obj = this.a;
        if (obj == Companion.a()) {
            if (Companion.c().compareAndSet(this, Companion.a(), IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            obj = this.a;
        }
        if (obj == Companion.b()) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
        }
        if (!(obj instanceof a)) {
            return obj;
        }
        throw ((a) obj).getException();
    }
}
