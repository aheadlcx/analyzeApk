package com.umeng.update;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.facebook.common.util.UriUtil;
import java.io.File;
import u.upd.a;
import u.upd.c;

public class UpdateDialogActivity extends Activity {
    int a = 6;
    UpdateResponse b;
    boolean c = false;
    File d = null;

    protected void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(c.a((Context) this).c("umeng_update_dialog"));
        this.b = (UpdateResponse) getIntent().getExtras().getSerializable("response");
        String string = getIntent().getExtras().getString(UriUtil.LOCAL_FILE_SCHEME);
        boolean z2 = getIntent().getExtras().getBoolean("force");
        if (string == null) {
            z = false;
        }
        if (z) {
            this.d = new File(string);
        }
        int a = c.a((Context) this).a("umeng_update_content");
        int a2 = c.a((Context) this).a("umeng_update_wifi_indicator");
        final int a3 = c.a((Context) this).a("umeng_update_id_ok");
        int a4 = c.a((Context) this).a("umeng_update_id_cancel");
        final int a5 = c.a((Context) this).a("umeng_update_id_ignore");
        int a6 = c.a((Context) this).a("umeng_update_id_close");
        int a7 = c.a((Context) this).a("umeng_update_id_check");
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ UpdateDialogActivity a;

            public void onClick(View view) {
                if (a3 == view.getId()) {
                    this.a.a = 5;
                } else if (a5 == view.getId()) {
                    this.a.a = 7;
                } else if (this.a.c) {
                    this.a.a = 7;
                }
                this.a.finish();
            }
        };
        OnCheckedChangeListener anonymousClass2 = new OnCheckedChangeListener(this) {
            final /* synthetic */ UpdateDialogActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.c = z;
            }
        };
        if (a2 > 0) {
            int i;
            if (a.d(this)) {
                i = 8;
            } else {
                i = 0;
            }
            findViewById(a2).setVisibility(i);
        }
        if (z2) {
            findViewById(a7).setVisibility(8);
        }
        findViewById(a3).setOnClickListener(anonymousClass1);
        findViewById(a4).setOnClickListener(anonymousClass1);
        findViewById(a5).setOnClickListener(anonymousClass1);
        findViewById(a6).setOnClickListener(anonymousClass1);
        ((CheckBox) findViewById(a7)).setOnCheckedChangeListener(anonymousClass2);
        CharSequence a8 = this.b.a(this, z);
        TextView textView = (TextView) findViewById(a);
        textView.requestFocus();
        textView.setText(a8);
    }

    protected void onDestroy() {
        super.onDestroy();
        UmengUpdateAgent.a(this.a, this, this.b, this.d);
    }
}
