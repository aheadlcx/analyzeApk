package com.budejie.www.activity.plate;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;

public class PlateDedailActivity extends BaseActvityWithLoadDailog {
    private String a;
    private String b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plate_detail);
        d(R.id.navigation_bar);
        a();
        b();
    }

    private void a() {
        Intent intent = getIntent();
        this.a = intent.getStringExtra("plate_id");
        this.b = intent.getStringExtra("plate_name");
    }

    private void b() {
        a(null);
        if (!TextUtils.isEmpty(this.b)) {
            CharSequence charSequence = this.b;
            if (this.b.length() > 5) {
                charSequence = this.b.substring(0, 5) + "...";
            }
            setTitle(charSequence);
        }
    }
}
