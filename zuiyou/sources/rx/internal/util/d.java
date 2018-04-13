package rx.internal.util;

public final class d {
    private static final int a = c();
    private static final boolean b = (a != 0);

    public static boolean a() {
        return b;
    }

    public static int b() {
        return a;
    }

    private static int c() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }
}
