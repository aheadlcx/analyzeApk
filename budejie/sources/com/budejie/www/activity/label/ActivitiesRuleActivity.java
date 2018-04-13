package com.budejie.www.activity.label;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.util.an;

public class ActivitiesRuleActivity extends BaseActvityWithLoadDailog {
    private LabelBean a;
    private TextView b;
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ ActivitiesRuleActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_activities_rule);
        this.a = (LabelBean) getIntent().getSerializableExtra("rule_key");
        d(R.id.navigation_bar);
        a(null);
        a();
    }

    private void c(final OnClickListener onClickListener, String str) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_right, null);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ActivitiesRuleActivity b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.finish();
                }
            }
        });
        if (this.g != null) {
            this.g.setRightView(textView);
        }
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
    }

    private void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.b = (TextView) findViewById(R.id.activitiesResults);
        if (this.a != null) {
            if (!TextUtils.isEmpty(this.a.getTheme_name())) {
                setTitle(h.a().a((Context) this, this.a.getTheme_name()));
            }
            if (this.a.getStatus() == 1) {
                c(this.c, "分享");
            }
            this.b.setText(this.a.getResult());
        }
    }
}
