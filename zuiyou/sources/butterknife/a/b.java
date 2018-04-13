package butterknife.a;

import android.support.annotation.IdRes;
import android.util.TypedValue;
import android.view.View;

public final class b {
    private static final TypedValue a = new TypedValue();

    public static <T> T a(View view, @IdRes int i, String str, Class<T> cls) {
        return c(view.findViewById(i), i, str, cls);
    }

    public static View a(View view, @IdRes int i, String str) {
        View findViewById = view.findViewById(i);
        if (findViewById != null) {
            return findViewById;
        }
        throw new IllegalStateException("Required view '" + a(view, i) + "' with ID " + i + " for " + str + " was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.");
    }

    public static <T> T b(View view, @IdRes int i, String str, Class<T> cls) {
        return c(a(view, i, str), i, str, cls);
    }

    public static <T> T c(View view, @IdRes int i, String str, Class<T> cls) {
        try {
            return cls.cast(view);
        } catch (Throwable e) {
            throw new IllegalStateException("View '" + a(view, i) + "' with ID " + i + " for " + str + " was of the wrong type. See cause for more info.", e);
        }
    }

    private static String a(View view, @IdRes int i) {
        if (view.isInEditMode()) {
            return "<unavailable while editing>";
        }
        return view.getContext().getResources().getResourceEntryName(i);
    }
}
