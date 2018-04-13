package com.budejie.www.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import com.budejie.www.R;

@Deprecated
public class AppWallActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((ViewGroup) getLayoutInflater().inflate(R.layout.advert_apps, null));
    }
}
