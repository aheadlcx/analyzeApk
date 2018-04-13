package com.budejie.www.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.util.an;
import com.budejie.www.util.j;
import com.budejie.www.widget.AddVoteView;
import com.budejie.www.widget.a;

public class AddVoteActivity extends SensorBaseActivity implements OnClickListener {
    private final String a = "AddVoteActivity";
    private TextView b;
    private LinearLayout c;
    private LinearLayout d;
    private TextView e;
    private TextView f;
    private AddVoteView g;
    private String h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_vote);
        a.a((Activity) this);
        a();
        b();
        d();
    }

    private void a() {
        this.h = getIntent().getStringExtra("vote_data_key");
    }

    private void b() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        c();
        this.g = (AddVoteView) findViewById(R.id.add_vote_view);
        this.g.setEnableListener(new AddVoteView.a(this) {
            final /* synthetic */ AddVoteActivity a;

            {
                this.a = r1;
            }

            public void a(Boolean bool) {
                this.a.f.setEnabled(bool.booleanValue());
            }
        });
        this.g.setInitData(this.h);
    }

    private void c() {
        this.c = (LinearLayout) findViewById(R.id.left_layout);
        this.d = (LinearLayout) findViewById(R.id.right_layout);
        this.c.setVisibility(0);
        this.d.setVisibility(0);
        this.c.setOnClickListener(this);
        this.b = (TextView) findViewById(R.id.title_center_txt);
        this.b.setText("投票");
        this.e = (TextView) findViewById(R.id.title_left_btn);
        this.f = (TextView) findViewById(R.id.title_right_btn);
        this.e.setText(R.string.cancel);
        this.f.setText("");
        this.f.setOnClickListener(this);
        this.f.setEnabled(false);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                finish();
                return;
            case R.id.title_right_btn:
                Intent intent = new Intent();
                intent.putExtra("vote_data_key", this.g.getVoteResult());
                setResult(10, intent);
                finish();
                return;
            default:
                return;
        }
    }

    private void d() {
        super.onrefreshTheme();
        this.b.setTextColor(getResources().getColor(j.b));
        onRefreshTitleFontTheme(this.e, false);
        this.f.setBackgroundResource(j.bm);
    }
}
