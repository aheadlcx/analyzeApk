package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class GeneralDataShowActivity extends Activity {
    private TextView tv;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.tv = new TextView(this);
        this.tv.setText(getIntent().getExtras().getString("data"));
        this.tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        setContentView(this.tv);
    }
}
