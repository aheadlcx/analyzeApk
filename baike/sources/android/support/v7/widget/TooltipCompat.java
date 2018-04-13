package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public class TooltipCompat {
    private static final c a;

    private interface c {
        void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence);
    }

    @TargetApi(26)
    private static class a implements c {
        private a() {
        }

        public void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    private static class b implements c {
        private b() {
        }

        public void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
            dc.setTooltipText(view, charSequence);
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        a.setTooltipText(view, charSequence);
    }

    private TooltipCompat() {
    }
}
