package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;

public interface ThemedSpinnerAdapter extends SpinnerAdapter {

    public static final class Helper {
        private final Context a;
        private final LayoutInflater b;
        private LayoutInflater c;

        public Helper(@NonNull Context context) {
            this.a = context;
            this.b = LayoutInflater.from(context);
        }

        public void setDropDownViewTheme(@Nullable Theme theme) {
            if (theme == null) {
                this.c = null;
            } else if (theme == this.a.getTheme()) {
                this.c = this.b;
            } else {
                this.c = LayoutInflater.from(new ContextThemeWrapper(this.a, theme));
            }
        }

        @Nullable
        public Theme getDropDownViewTheme() {
            return this.c == null ? null : this.c.getContext().getTheme();
        }

        @NonNull
        public LayoutInflater getDropDownViewInflater() {
            return this.c != null ? this.c : this.b;
        }
    }

    @Nullable
    Theme getDropDownViewTheme();

    void setDropDownViewTheme(@Nullable Theme theme);
}
