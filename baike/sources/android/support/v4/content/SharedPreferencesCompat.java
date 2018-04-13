package android.support.v4.content;

import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat {

    @Deprecated
    public static final class EditorCompat {
        private static EditorCompat a;
        private final a b = new a();

        private static class a {
            a() {
            }

            public void apply(@NonNull Editor editor) {
                try {
                    editor.apply();
                } catch (AbstractMethodError e) {
                    editor.commit();
                }
            }
        }

        private EditorCompat() {
        }

        @Deprecated
        public static EditorCompat getInstance() {
            if (a == null) {
                a = new EditorCompat();
            }
            return a;
        }

        @Deprecated
        public void apply(@NonNull Editor editor) {
            this.b.apply(editor);
        }
    }

    private SharedPreferencesCompat() {
    }
}
