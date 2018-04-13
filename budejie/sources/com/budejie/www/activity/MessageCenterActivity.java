package com.budejie.www.activity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.noticesetting.NotificationSettingsActivity;
import com.budejie.www.util.an;

public class MessageCenterActivity extends ActivityGroup implements OnClickListener {
    public static FrameLayout a;
    private LocalActivityManager b;
    private TextView c;
    private TextView d;
    private Intent e;
    private Intent f;
    private View g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        an.f((Activity) this);
        setContentView(R.layout.message_center_layout);
        this.f = getIntent();
        a();
    }

    private void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        an.f((Activity) this);
        this.b = getLocalActivityManager();
        a = (FrameLayout) findViewById(R.id.container);
        this.c = (TextView) findViewById(R.id.back);
        this.d = (TextView) findViewById(R.id.set_btn);
        this.e = new Intent(this, MyNewsActivity.class);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.g = this.b.startActivity("notice_id", this.e).getDecorView();
        a.addView(this.g);
        this.d.setVisibility(0);
        this.g.setVisibility(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                return;
            case R.id.set_btn:
                startActivity(new Intent(this, NotificationSettingsActivity.class));
                return;
            default:
                return;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        finish();
        return true;
    }
}
