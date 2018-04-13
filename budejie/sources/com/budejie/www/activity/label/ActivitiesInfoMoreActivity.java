package com.budejie.www.activity.label;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.util.an;

public class ActivitiesInfoMoreActivity extends BaseActvityWithLoadDailog {
    private String a;
    private TextView b;
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ ActivitiesInfoMoreActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_activities_moreinfo);
        this.a = getIntent().getStringExtra("more_key");
        d(R.id.navigation_bar);
        a(null);
        setTitle((CharSequence) "活动简介");
        a();
    }

    private void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.b = (TextView) findViewById(R.id.moreInfo);
        this.b.setText(this.a);
    }
}
