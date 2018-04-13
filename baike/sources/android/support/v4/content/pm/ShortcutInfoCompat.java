package android.support.v4.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;

public class ShortcutInfoCompat {
    private Context a;
    private String b;
    private Intent[] c;
    private ComponentName d;
    private CharSequence e;
    private CharSequence f;
    private CharSequence g;
    private IconCompat h;

    public static class Builder {
        private final ShortcutInfoCompat a = new ShortcutInfoCompat();

        public Builder(@NonNull Context context, @NonNull String str) {
            this.a.a = context;
            this.a.b = str;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence charSequence) {
            this.a.e = charSequence;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence charSequence) {
            this.a.g = charSequence;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intentArr) {
            this.a.c = intentArr;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat iconCompat) {
            this.a.h = iconCompat;
            return this;
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName componentName) {
            this.a.d = componentName;
            return this;
        }

        @NonNull
        public ShortcutInfoCompat build() {
            if (TextUtils.isEmpty(this.a.e)) {
                throw new IllegalArgumentException("Shortcut much have a non-empty label");
            } else if (this.a.c != null && this.a.c.length != 0) {
                return this.a;
            } else {
                throw new IllegalArgumentException("Shortcut much have an intent");
            }
        }
    }

    private ShortcutInfoCompat() {
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        android.content.pm.ShortcutInfo.Builder intents = new android.content.pm.ShortcutInfo.Builder(this.a, this.b).setShortLabel(this.e).setIntents(this.c);
        if (this.h != null) {
            intents.setIcon(this.h.toIcon());
        }
        if (!TextUtils.isEmpty(this.f)) {
            intents.setLongLabel(this.f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            intents.setDisabledMessage(this.g);
        }
        if (this.d != null) {
            intents.setActivity(this.d);
        }
        return intents.build();
    }

    Intent a(Intent intent) {
        intent.putExtra("android.intent.extra.shortcut.INTENT", this.c[this.c.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.e.toString());
        if (this.h != null) {
            this.h.addToShortcutIntent(intent);
        }
        return intent;
    }

    @NonNull
    public String getId() {
        return this.b;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.d;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.e;
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.f;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.g;
    }

    @NonNull
    public Intent getIntent() {
        return this.c[this.c.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        return (Intent[]) Arrays.copyOf(this.c, this.c.length);
    }
}
