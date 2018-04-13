package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CharIterator;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lkotlin/ranges/CharProgressionIterator;", "Lkotlin/collections/CharIterator;", "first", "", "last", "step", "", "(CCI)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextChar", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
public final class CharProgressionIterator extends CharIterator {
    private final int a;
    private boolean b;
    private int c;
    private final int d;

    public CharProgressionIterator(char c, char c2, int i) {
        boolean z = true;
        this.d = i;
        this.a = c2;
        if (this.d > 0) {
            if (c > c2) {
                z = false;
            }
        } else if (c < c2) {
            z = false;
        }
        this.b = z;
        if (!this.b) {
            c = this.a;
        }
        this.c = c;
    }

    public final int getStep() {
        return this.d;
    }

    public boolean hasNext() {
        return this.b;
    }

    public char nextChar() {
        int i = this.c;
        if (i != this.a) {
            this.c += this.d;
        } else if (this.b) {
            this.b = false;
        } else {
            throw new NoSuchElementException();
        }
        return (char) i;
    }
}
