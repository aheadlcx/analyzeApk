package a.a.a;

public class a {
    public static final boolean a(byte b, int i) {
        return a((int) b, i);
    }

    public static final boolean a(int i, int i2) {
        return ((1 << i2) & i) != 0;
    }

    public static final byte b(byte b, int i) {
        return (byte) b((int) b, i);
    }

    public static final int b(int i, int i2) {
        return ((1 << i2) ^ -1) & i;
    }

    public static final byte a(byte b, int i, boolean z) {
        return (byte) a((int) b, i, z);
    }

    public static final int a(int i, int i2, boolean z) {
        if (z) {
            return (1 << i2) | i;
        }
        return b(i, i2);
    }
}
