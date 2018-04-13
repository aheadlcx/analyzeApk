package com.budejie.www.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

@Deprecated
public class BaijiajieWithAdvActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new RelativeLayout(this));
    }
}
