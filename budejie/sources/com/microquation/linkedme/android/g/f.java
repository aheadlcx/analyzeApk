package com.microquation.linkedme.android.g;

import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class f {

    public static final class a {
        private static a a;
        private final c b;

        private interface c {
            void a(@NonNull Editor editor);
        }

        private static class a implements c {
            private a() {
            }

            public void a(@NonNull Editor editor) {
                d.a(editor);
            }
        }

        private static class b implements c {
            private b() {
            }

            public void a(@NonNull Editor editor) {
                editor.commit();
            }
        }

        private a() {
            if (VERSION.SDK_INT >= 9) {
                this.b = new a();
            } else {
                this.b = new b();
            }
        }

        public static a a() {
            if (a == null) {
                a = new a();
            }
            return a;
        }

        public void a(@NonNull Editor editor) {
            this.b.a(editor);
        }
    }
}
