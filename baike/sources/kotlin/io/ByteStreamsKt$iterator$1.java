package kotlin.io;

import java.io.BufferedInputStream;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ByteIterator;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0012\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0016"}, d2 = {"kotlin/io/ByteStreamsKt$iterator$1", "Lkotlin/collections/ByteIterator;", "(Ljava/io/BufferedInputStream;)V", "finished", "", "getFinished", "()Z", "setFinished", "(Z)V", "nextByte", "", "getNextByte", "()I", "setNextByte", "(I)V", "nextPrepared", "getNextPrepared", "setNextPrepared", "hasNext", "", "prepareNext", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class ByteStreamsKt$iterator$1 extends ByteIterator {
    final /* synthetic */ BufferedInputStream a;
    private int b = -1;
    private boolean c;
    private boolean d;

    ByteStreamsKt$iterator$1(BufferedInputStream bufferedInputStream) {
        this.a = bufferedInputStream;
    }

    public final int getNextByte() {
        return this.b;
    }

    public final void setNextByte(int i) {
        this.b = i;
    }

    public final boolean getNextPrepared() {
        return this.c;
    }

    public final void setNextPrepared(boolean z) {
        this.c = z;
    }

    public final boolean getFinished() {
        return this.d;
    }

    public final void setFinished(boolean z) {
        this.d = z;
    }

    private final void a() {
        boolean z = true;
        if (!this.c && !this.d) {
            this.b = this.a.read();
            this.c = true;
            if (this.b != -1) {
                z = false;
            }
            this.d = z;
        }
    }

    public boolean hasNext() {
        a();
        return !this.d;
    }

    public byte nextByte() {
        a();
        if (this.d) {
            throw new NoSuchElementException("Input stream is over.");
        }
        byte b = (byte) this.b;
        this.c = false;
        return b;
    }
}
