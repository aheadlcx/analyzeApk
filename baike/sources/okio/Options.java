package okio;

import java.util.AbstractList;
import java.util.RandomAccess;

public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] a;

    private Options(ByteString[] byteStringArr) {
        this.a = byteStringArr;
    }

    public static Options of(ByteString... byteStringArr) {
        return new Options((ByteString[]) byteStringArr.clone());
    }

    public ByteString get(int i) {
        return this.a[i];
    }

    public int size() {
        return this.a.length;
    }
}
