package kotlin.jvm.internal;

import com.alipay.sdk.util.j;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0004H\u0004J\u001d\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0004*\u00028\u0000H$¢\u0006\u0002\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000b8\u0002X\u0004¢\u0006\n\n\u0002\u0010\u000e\u0012\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", "", "size", "", "(I)V", "position", "getPosition", "()I", "setPosition", "spreads", "", "spreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
public abstract class PrimitiveSpreadBuilder<T> {
    private int a;
    private final T[] b = new Object[this.c];
    private final int c;

    protected abstract int getSize(@NotNull T t);

    public PrimitiveSpreadBuilder(int i) {
        this.c = i;
    }

    protected final int a() {
        return this.a;
    }

    protected final void a(int i) {
        this.a = i;
    }

    public final void addSpread(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "spreadArgument");
        Object[] objArr = this.b;
        int i = this.a;
        this.a = i + 1;
        objArr[i] = t;
    }

    protected final int b() {
        int i = 0;
        int i2 = this.c - 1;
        if (0 > i2) {
            return 0;
        }
        int i3 = 0;
        while (true) {
            Object obj = this.b[i];
            i3 += obj != null ? getSize(obj) : 1;
            if (i == i2) {
                return i3;
            }
            i++;
        }
    }

    @NotNull
    protected final T a(@NotNull T t, @NotNull T t2) {
        int i;
        int i2;
        Intrinsics.checkParameterIsNotNull(t, "values");
        Intrinsics.checkParameterIsNotNull(t2, j.c);
        int i3 = this.c - 1;
        if (0 <= i3) {
            int i4 = 0;
            i = 0;
            i2 = 0;
            while (true) {
                Object obj = this.b[i4];
                if (obj != null) {
                    if (i < i4) {
                        System.arraycopy(t, i, t2, i2, i4 - i);
                        i2 += i4 - i;
                    }
                    i = getSize(obj);
                    System.arraycopy(obj, 0, t2, i2, i);
                    i2 += i;
                    i = i4 + 1;
                }
                if (i4 == i3) {
                    break;
                }
                i4++;
            }
        } else {
            i = 0;
            i2 = 0;
        }
        if (i < this.c) {
            System.arraycopy(t, i, t2, i2, this.c - i);
        }
        return t2;
    }
}
