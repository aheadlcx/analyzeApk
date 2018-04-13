package com.budejie.www.activity.label;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;

public class PostStandardLookActivity extends BaseActvityWithLoadDailog {
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_post_standard_look);
        d(R.id.navigation_bar);
        TextView textView = (TextView) findViewById(R.id.standard_textView);
        setTitle((CharSequence) "版规");
        a(null);
        Intent intent = getIntent();
        if (intent != null) {
            textView.setText(intent.getStringExtra("standardTextTag"));
        }
    }
}
