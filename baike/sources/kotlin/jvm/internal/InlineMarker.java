package kotlin.jvm.internal;

public class InlineMarker {
    public static void mark(int i) {
    }

    public static void mark(String str) {
    }

    public static void beforeInlineCall() {
    }

    public static void afterInlineCall() {
    }

    public static void finallyStart(int i) {
    }

    public static void finallyEnd(int i) {
    }
}
