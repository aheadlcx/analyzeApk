package com.huawei.hms.update.e;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import com.huawei.a.a.a.a.c;

public class d extends b {
    public AlertDialog a() {
        AlertDialog progressDialog = new ProgressDialog(f(), g());
        progressDialog.setMessage(f().getString(c.hms_checking));
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
