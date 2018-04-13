package com.huawei.hms.update.e;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public final class m {

    private static abstract class a extends b {
        protected abstract int h();

        private a() {
        }

        public AlertDialog a() {
            Builder builder = new Builder(f(), g());
            builder.setMessage(h());
            builder.setPositiveButton(i(), new n(this));
            return builder.create();
        }

        protected int i() {
            return com.huawei.a.a.a.a.c.hms_confirm;
        }
    }

    public static class b extends a {
        public b() {
            super();
        }

        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        protected int h() {
            return com.huawei.a.a.a.a.c.hms_check_failure;
        }
    }

    public static class c extends a {
        public c() {
            super();
        }

        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        protected int h() {
            return com.huawei.a.a.a.a.c.hms_download_failure;
        }
    }

    public static class d extends a {
        public d() {
            super();
        }

        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        protected int h() {
            return com.huawei.a.a.a.a.c.hms_download_no_space;
        }
    }
}
