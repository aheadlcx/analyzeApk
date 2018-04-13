package kotlin.internal;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a \u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0000\u001a \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0002¨\u0006\u000b"}, d2 = {"differenceModulo", "", "a", "b", "c", "", "getProgressionLastElement", "start", "end", "step", "mod", "kotlin-runtime"}, k = 2, mv = {1, 1, 6})
public final class ProgressionUtilKt {
    private static final int a(int i, int i2) {
        int i3 = i % i2;
        return i3 >= 0 ? i3 : i3 + i2;
    }

    private static final long a(long j, long j2) {
        long j3 = j % j2;
        return j3 >= ((long) 0) ? j3 : j3 + j2;
    }

    private static final int a(int i, int i2, int i3) {
        return a(a(i, i3) - a(i2, i3), i3);
    }

    private static final long a(long j, long j2, long j3) {
        return a(a(j, j3) - a(j2, j3), j3);
    }

    public static final int getProgressionLastElement(int i, int i2, int i3) {
        if (i3 > 0) {
            return i2 - a(i2, i, i3);
        }
        if (i3 < 0) {
            return a(i, i2, -i3) + i2;
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    public static final long getProgressionLastElement(long j, long j2, long j3) {
        if (j3 > ((long) null)) {
            return j2 - a(j2, j, j3);
        }
        if (j3 < ((long) null)) {
            return a(j, j2, -j3) + j2;
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
