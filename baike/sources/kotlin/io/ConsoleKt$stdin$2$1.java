package kotlin.io;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import qsbk.app.core.model.CustomButton;

@Metadata(bv = {1, 0, 1}, d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¨\u0006\u0014"}, d2 = {"kotlin/io/ConsoleKt$stdin$2$1", "Ljava/io/InputStream;", "()V", "available", "", "close", "", "mark", "readlimit", "markSupported", "", "read", "b", "", "off", "len", "reset", "skip", "", "n", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class ConsoleKt$stdin$2$1 extends InputStream {
    ConsoleKt$stdin$2$1() {
    }

    public int read() {
        return System.in.read();
    }

    public void reset() {
        System.in.reset();
    }

    public int read(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, CustomButton.POSITION_BOTTOM);
        return System.in.read(bArr);
    }

    public void close() {
        System.in.close();
    }

    public void mark(int i) {
        System.in.mark(i);
    }

    public long skip(long j) {
        return System.in.skip(j);
    }

    public int available() {
        return System.in.available();
    }

    public boolean markSupported() {
        return System.in.markSupported();
    }

    public int read(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, CustomButton.POSITION_BOTTOM);
        return System.in.read(bArr, i, i2);
    }
}
