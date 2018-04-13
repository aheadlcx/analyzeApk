package qsbk.app.compat;

import android.os.Build.VERSION;
import qsbk.app.R;

public final class ThemeCompat {
    public static int getSimpleListItem() {
        if (preHoneycomb()) {
            return R.layout.text_item;
        }
        return 17367043;
    }

    public static boolean preHoneycomb() {
        return VERSION.SDK_INT < 11;
    }
}
