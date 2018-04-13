package c.a.i;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R;

public class x {
    private static final int[] a = new int[]{R.attr.colorPrimary};
    private static final int[] b = new int[]{R.attr.colorPrimaryDark};
    private static final int[] c = new int[]{R.attr.colorAccent};

    public static int a(Context context) {
        return a(context, a);
    }

    public static int b(Context context) {
        return a(context, b);
    }

    public static int c(Context context) {
        return a(context, new int[]{16842806});
    }

    @RequiresApi(api = 21)
    public static int d(Context context) {
        return a(context, new int[]{16843857});
    }

    public static int e(Context context) {
        return a(context, new int[]{16842836});
    }

    private static int a(Context context, int[] iArr) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        return resourceId;
    }
}
