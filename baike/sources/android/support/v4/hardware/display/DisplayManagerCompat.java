package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    private static final WeakHashMap<Context, DisplayManagerCompat> a = new WeakHashMap();

    private static class a extends DisplayManagerCompat {
        private final WindowManager a;

        a(Context context) {
            this.a = (WindowManager) context.getSystemService("window");
        }

        public Display getDisplay(int i) {
            Display defaultDisplay = this.a.getDefaultDisplay();
            return defaultDisplay.getDisplayId() == i ? defaultDisplay : null;
        }

        public Display[] getDisplays() {
            return new Display[]{this.a.getDefaultDisplay()};
        }

        public Display[] getDisplays(String str) {
            return str == null ? getDisplays() : new Display[0];
        }
    }

    @RequiresApi(17)
    private static class b extends DisplayManagerCompat {
        private final DisplayManager a;

        b(Context context) {
            this.a = (DisplayManager) context.getSystemService("display");
        }

        public Display getDisplay(int i) {
            return this.a.getDisplay(i);
        }

        public Display[] getDisplays() {
            return this.a.getDisplays();
        }

        public Display[] getDisplays(String str) {
            return this.a.getDisplays(str);
        }
    }

    @Nullable
    public abstract Display getDisplay(int i);

    @NonNull
    public abstract Display[] getDisplays();

    @NonNull
    public abstract Display[] getDisplays(String str);

    DisplayManagerCompat() {
    }

    @NonNull
    public static DisplayManagerCompat getInstance(@NonNull Context context) {
        DisplayManagerCompat displayManagerCompat;
        synchronized (a) {
            displayManagerCompat = (DisplayManagerCompat) a.get(context);
            if (displayManagerCompat == null) {
                if (VERSION.SDK_INT >= 17) {
                    displayManagerCompat = new b(context);
                } else {
                    displayManagerCompat = new a(context);
                }
                a.put(context, displayManagerCompat);
            }
        }
        return displayManagerCompat;
    }
}
