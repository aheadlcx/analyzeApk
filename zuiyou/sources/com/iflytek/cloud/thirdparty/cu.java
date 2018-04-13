package com.iflytek.cloud.thirdparty;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class cu extends Dialog {
    protected cv a = null;
    private a b = new a(this) {
        final /* synthetic */ cu a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.dismiss();
        }
    };

    public interface a {
        void a();
    }

    public cu(Context context) {
        super(context);
    }

    public boolean destroy() {
        if (isShowing()) {
            return false;
        }
        boolean d = this.a.d();
        this.a = null;
        return d;
    }

    public void dismiss() {
        this.a.c();
        super.dismiss();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(this.a);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    public void show() {
        setCanceledOnTouchOutside(true);
        this.a.setExitCallBack(this.b);
        this.a.b();
        super.show();
    }
}
