package org.mozilla.javascript;

public interface RegExpProxy {
    public static final int RA_MATCH = 1;
    public static final int RA_REPLACE = 2;
    public static final int RA_SEARCH = 3;

    Object action(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr, int i);

    Object compileRegExp(Context context, String str, String str2);

    int find_split(Context context, Scriptable scriptable, String str, String str2, Scriptable scriptable2, int[] iArr, int[] iArr2, boolean[] zArr, String[][] strArr);

    boolean isRegExp(Scriptable scriptable);

    Object js_split(Context context, Scriptable scriptable, String str, Object[] objArr);

    Scriptable wrapRegExp(Context context, Scriptable scriptable, Object obj);
}
