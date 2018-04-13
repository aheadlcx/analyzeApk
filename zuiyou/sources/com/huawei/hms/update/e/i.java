package com.huawei.hms.update.e;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import com.huawei.a.a.a.a.c;

public class i extends b {
    protected AlertDialog a() {
        int i = c.hms_update_message_new;
        int i2 = c.hms_install;
        Builder builder = new Builder(f(), g());
        builder.setMessage(f().getString(i, new Object[]{f().getString(c.hms_update_title)}));
        builder.setPositiveButton(i2, new j(this));
        builder.setNegativeButton(c.hms_cancel, new k(this));
        return builder.create();
    }
}
