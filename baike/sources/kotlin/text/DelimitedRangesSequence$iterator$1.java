package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000'\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\t\u0010\u001a\u001a\u00020\u001bH\u0002J\t\u0010\u001c\u001a\u00020\u0002H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0007\"\u0004\b\u0014\u0010\tR\u001a\u0010\u0015\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0007\"\u0004\b\u0017\u0010\t¨\u0006\u001d"}, d2 = {"kotlin/text/DelimitedRangesSequence$iterator$1", "", "Lkotlin/ranges/IntRange;", "(Lkotlin/text/DelimitedRangesSequence;)V", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextItem", "getNextItem", "()Lkotlin/ranges/IntRange;", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "nextState", "getNextState", "setNextState", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class DelimitedRangesSequence$iterator$1 implements Iterator<IntRange>, KMappedMarker {
    final /* synthetic */ e a;
    private int b = -1;
    private int c;
    private int d;
    @Nullable
    private IntRange e;
    private int f;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    DelimitedRangesSequence$iterator$1(e eVar) {
        this.a = eVar;
        this.c = e.coerceIn(eVar.b, 0, eVar.a.length());
        this.d = this.c;
    }

    public final int getNextState() {
        return this.b;
    }

    public final void setNextState(int i) {
        this.b = i;
    }

    public final int getCurrentStartIndex() {
        return this.c;
    }

    public final void setCurrentStartIndex(int i) {
        this.c = i;
    }

    public final int getNextSearchIndex() {
        return this.d;
    }

    public final void setNextSearchIndex(int i) {
        this.d = i;
    }

    @Nullable
    public final IntRange getNextItem() {
        return this.e;
    }

    public final void setNextItem(@Nullable IntRange intRange) {
        this.e = intRange;
    }

    public final int getCounter() {
        return this.f;
    }

    public final void setCounter(int i) {
        this.f = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a() {
        /*
        r7 = this;
        r2 = 1;
        r3 = 0;
        r5 = -1;
        r0 = r7.d;
        if (r0 >= 0) goto L_0x000f;
    L_0x0007:
        r7.b = r3;
        r0 = 0;
        r0 = (kotlin.ranges.IntRange) r0;
        r7.e = r0;
    L_0x000e:
        return;
    L_0x000f:
        r0 = r7.a;
        r0 = r0.c;
        if (r0 <= 0) goto L_0x0027;
    L_0x0017:
        r0 = r7.f;
        r0 = r0 + 1;
        r7.f = r0;
        r0 = r7.f;
        r1 = r7.a;
        r1 = r1.c;
        if (r0 >= r1) goto L_0x0035;
    L_0x0027:
        r0 = r7.d;
        r1 = r7.a;
        r1 = r1.a;
        r1 = r1.length();
        if (r0 <= r1) goto L_0x004d;
    L_0x0035:
        r0 = r7.c;
        r1 = new kotlin.ranges.IntRange;
        r3 = r7.a;
        r3 = r3.a;
        r3 = kotlin.text.u.getLastIndex(r3);
        r1.<init>(r0, r3);
        r7.e = r1;
        r7.d = r5;
    L_0x004a:
        r7.b = r2;
        goto L_0x000e;
    L_0x004d:
        r0 = r7.a;
        r0 = r0.d;
        r1 = r7.a;
        r1 = r1.a;
        r4 = r7.d;
        r4 = java.lang.Integer.valueOf(r4);
        r0 = r0.invoke(r1, r4);
        r0 = (kotlin.Pair) r0;
        if (r0 != 0) goto L_0x007d;
    L_0x0067:
        r0 = r7.c;
        r1 = new kotlin.ranges.IntRange;
        r3 = r7.a;
        r3 = r3.a;
        r3 = kotlin.text.u.getLastIndex(r3);
        r1.<init>(r0, r3);
        r7.e = r1;
        r7.d = r5;
        goto L_0x004a;
    L_0x007d:
        r1 = r0.component1();
        r1 = (java.lang.Number) r1;
        r1 = r1.intValue();
        r0 = r0.component2();
        r0 = (java.lang.Number) r0;
        r0 = r0.intValue();
        r4 = r7.c;
        r5 = new kotlin.ranges.IntRange;
        r6 = r1 + -1;
        r5.<init>(r4, r6);
        r7.e = r5;
        r1 = r1 + r0;
        r7.c = r1;
        r1 = r7.c;
        if (r0 != 0) goto L_0x00a8;
    L_0x00a3:
        r0 = r2;
    L_0x00a4:
        r0 = r0 + r1;
        r7.d = r0;
        goto L_0x004a;
    L_0x00a8:
        r0 = r3;
        goto L_0x00a4;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.a():void");
    }

    @NotNull
    public IntRange next() {
        if (this.b == -1) {
            a();
        }
        if (this.b == 0) {
            throw new NoSuchElementException();
        }
        IntRange intRange = this.e;
        if (intRange == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
        }
        this.e = (IntRange) null;
        this.b = -1;
        return intRange;
    }

    public boolean hasNext() {
        if (this.b == -1) {
            a();
        }
        if (this.b == 1) {
            return true;
        }
        return false;
    }
}
