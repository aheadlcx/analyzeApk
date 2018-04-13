package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.view.ShowStepView;
import com.budejie.www.util.ad;
import com.budejie.www.util.ai;
import com.budejie.www.util.g;
import com.budejie.www.util.i;
import com.budejie.www.util.j;
import java.util.ArrayList;
import java.util.List;

public class BeautifyRecordActivity extends OauthWeiboBaseAct implements OnClickListener {
    private Bundle A;
    Handler a = new Handler(this) {
        final /* synthetic */ BeautifyRecordActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            TextView d;
            switch (message.what) {
                case 2:
                    this.a.p.setVisibility(0);
                    this.a.m.setVisibility(0);
                    this.a.p.setMax(this.a.s.j());
                    d = this.a.o;
                    this.a.s;
                    d.setText(ad.a((long) this.a.s.j()));
                    d = this.a.n;
                    this.a.s;
                    d.setText(ad.a((long) this.a.s.i()));
                    this.a.a.sendEmptyMessage(3);
                    return;
                case 3:
                    d = this.a.n;
                    this.a.s;
                    d.setText(ad.a((long) this.a.s.i()));
                    this.a.p.setProgress(this.a.s.i());
                    this.a.a.sendEmptyMessage(3);
                    return;
                case 4:
                    d = this.a.n;
                    this.a.s;
                    d.setText(ad.a((long) this.a.s.i()));
                    this.a.p.setProgress(this.a.s.i());
                    return;
                case 5:
                    this.a.k.setVisibility(0);
                    this.a.k.setImageResource(R.drawable.ic_play_white);
                    this.a.l.setVisibility(0);
                    d = this.a.l;
                    this.a.s;
                    d.setText(ad.a((long) this.a.s.j()));
                    this.a.p.setProgress(0);
                    d = this.a.n;
                    this.a.s;
                    d.setText(ad.a(0));
                    this.a.p.setVisibility(8);
                    this.a.m.setVisibility(4);
                    this.a.a.removeMessages(3);
                    return;
                default:
                    return;
            }
        }
    };
    private RelativeLayout b;
    private LinearLayout c;
    private Button d;
    private TextView e;
    private RelativeLayout f;
    private Button g;
    private TextView h;
    private RelativeLayout i;
    private ImageView j;
    private ImageView k;
    private TextView l;
    private RelativeLayout m;
    private TextView n;
    private TextView o;
    private SeekBar p;
    private ShowStepView q;
    private LinearLayout r;
    private ad s;
    private ad t;
    private HorizontalScrollView u;
    private int v = 0;
    private String w;
    private String x;
    private List<com.budejie.www.util.g.a> y;
    private com.budejie.www.util.g.a z;

    class a implements com.budejie.www.f.b {
        final /* synthetic */ BeautifyRecordActivity a;

        a(BeautifyRecordActivity beautifyRecordActivity) {
            this.a = beautifyRecordActivity;
        }

        public void a() {
        }

        public void b() {
        }

        public void a(int i) {
            this.a.a.sendEmptyMessage(5);
            this.a.t.h();
        }
    }

    class b implements com.budejie.www.f.b {
        final /* synthetic */ BeautifyRecordActivity a;

        b(BeautifyRecordActivity beautifyRecordActivity) {
            this.a = beautifyRecordActivity;
        }

        public void a() {
        }

        public void b() {
        }

        public void a(int i) {
            if (this.a.z != null && this.a.z.d != null && this.a.z.d.exists()) {
                this.a.t.a(this.a.z.d.getAbsolutePath());
            }
        }
    }

    class c implements OnSeekBarChangeListener {
        final /* synthetic */ BeautifyRecordActivity a;
        private int b;
        private boolean c = false;

        c(BeautifyRecordActivity beautifyRecordActivity) {
            this.a = beautifyRecordActivity;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.b = i;
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            this.c = true;
            this.a.a.sendEmptyMessage(4);
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.c) {
                this.c = false;
                this.a.a.sendEmptyMessage(4);
                this.a.s.a(this.b);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        this.z = null;
        super.onCreate(bundle);
        i.a().a((Activity) this);
        setContentView(R.layout.activity_record_beautify);
        this.w = getIntent().getStringExtra("record_path");
        this.x = getIntent().getStringExtra("img_path");
        this.A = getIntent().getBundleExtra("theme_data");
        this.v = ai.e(this);
        b();
        this.j.setImageDrawable(Drawable.createFromPath(this.x));
        this.s = ad.a();
        this.s.a((Context) this);
        this.s.a(new a(this));
        this.t = ad.a();
        this.t.a((Context) this);
        this.t.a(new b(this));
        c();
    }

    protected void a() {
        if (this.s != null && this.s.c()) {
            this.s.g();
        }
        if (this.t != null && this.t.c()) {
            this.t.h();
        }
    }

    protected void onStop() {
        a();
        super.onStop();
    }

    protected void onPause() {
        a();
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        g.a((Context) this).c();
        g.a((Context) this).a();
        i.a().b((Activity) this);
    }

    private void b() {
        this.b = (RelativeLayout) findViewById(R.id.title);
        this.c = (LinearLayout) findViewById(R.id.left_layout);
        this.d = (Button) findViewById(R.id.title_left_btn);
        this.d.setVisibility(0);
        this.d.setOnClickListener(this);
        this.e = (TextView) findViewById(R.id.title_center_txt);
        this.e.setText("声音");
        this.f = (RelativeLayout) findViewById(R.id.title_refresh_layout);
        this.f.setVisibility(0);
        this.g = (Button) findViewById(R.id.refresh_btn);
        this.g.setVisibility(8);
        this.h = (TextView) findViewById(R.id.title_right_btn);
        this.h.setVisibility(0);
        this.h.setOnClickListener(this);
        this.c.setVisibility(0);
        this.i = (RelativeLayout) findViewById(R.id.record_beautify_layout);
        this.j = (ImageView) findViewById(R.id.recordPhoto);
        this.k = (ImageView) findViewById(R.id.recordPlayBtn);
        this.l = (TextView) findViewById(R.id.recordTime);
        this.m = (RelativeLayout) findViewById(R.id.layoutTime);
        this.n = (TextView) findViewById(R.id.recordPlayCurrentTime);
        this.o = (TextView) findViewById(R.id.recordPlayDurationTime);
        this.p = (SeekBar) findViewById(R.id.mSeekBar);
        this.q = (ShowStepView) findViewById(R.id.recordStepView);
        this.q.setCurrentLine(3);
        this.k.setOnClickListener(this);
        this.p.setOnSeekBarChangeListener(new c(this));
        this.j.setOnClickListener(this);
        this.r = (LinearLayout) findViewById(R.id.voiceBottomContent);
        this.u = (HorizontalScrollView) findViewById(R.id.beautifyBottomFrame);
    }

    private void c() {
        TextView textView = this.l;
        ad adVar = this.s;
        textView.setText(ad.a((long) (this.v * 1000)));
        d();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_btn:
                finish();
                return;
            case R.id.recordPhoto:
                a(false);
                return;
            case R.id.recordPlayBtn:
                a(false);
                return;
            case R.id.title_right_btn:
                a();
                Intent intent = new Intent(this, VoiceCommentActivity.class);
                intent.putExtra("img_path", this.x);
                intent.putExtra("record_path", this.w);
                intent.putExtra("bg_music_index", this.z.a);
                intent.putExtra("bg_voice_len", this.v);
                intent.putExtra("h5_reserve", getIntent().getStringExtra("h5_reserve"));
                intent.putExtra("theme_data", this.A);
                startActivity(intent);
                com.budejie.www.http.i.a(getString(R.string.track_action_send_voice), getString(R.string.track_event_voice_beautify_ready));
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        if (z) {
            this.s.a(this.w);
            this.a.sendEmptyMessage(2);
            if (this.z.d != null && this.z.d.exists()) {
                this.t.a(this.z.d.getAbsolutePath());
            }
        }
        if (this.s.c()) {
            this.k.setVisibility(0);
            this.k.setImageResource(R.drawable.ic_play_white);
            this.s.e();
        } else {
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            if (this.s.b()) {
                this.s.d();
            } else {
                if (i.a().c() != null) {
                    i.a().c().g();
                }
                this.s.a(this.w);
                this.a.sendEmptyMessage(2);
            }
        }
        if (this.z.d == null || !this.z.d.exists()) {
            if (this.t.c()) {
                this.t.g();
            }
        } else if (this.t.c()) {
            this.t.e();
        } else if (this.t.b()) {
            this.t.d();
        } else {
            this.t.a(this.z.d.getAbsolutePath());
        }
    }

    private void d() {
        this.y = g.a((Context) this).b();
        a(this.y);
    }

    private void a(List<com.budejie.www.util.g.a> list) {
        List arrayList;
        if (list == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = new ArrayList(list);
        }
        int a = (i.a().a((Context) this) - 12) / 6;
        com.budejie.www.util.g.a aVar = new com.budejie.www.util.g.a();
        aVar.a = 0;
        aVar.b = "原声";
        r0.add(0, aVar);
        View view = null;
        if (r0 == null || r0.isEmpty()) {
            this.u.setVisibility(4);
            return;
        }
        for (com.budejie.www.util.g.a aVar2 : r0) {
            View view2;
            View textView = new TextView(this);
            textView.setText(aVar2.b);
            textView.setTextColor(getResources().getColor(R.color.text_gray));
            textView.setGravity(17);
            textView.setTextSize(16.0f);
            textView.setSingleLine(true);
            textView.setPadding(0, 8, 0, 8);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(a, -2);
            layoutParams.setMargins(0, 12, 0, 12);
            textView.setLayoutParams(layoutParams);
            textView.setTag(aVar2);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BeautifyRecordActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.budejie.www.util.g.a aVar = (com.budejie.www.util.g.a) view.getTag();
                    TextView textView = (TextView) this.a.r.findViewWithTag(aVar);
                    textView.setTextColor(this.a.getResources().getColor(R.color.red));
                    textView.setPadding(8, 8, 8, 8);
                    if (this.a.z != aVar) {
                        textView = (TextView) this.a.r.findViewWithTag(this.a.z);
                        if (textView != null) {
                            textView.setTextColor(this.a.getResources().getColor(R.color.text_gray));
                        }
                    }
                    this.a.z = aVar;
                    this.a.a(true);
                }
            });
            this.r.addView(textView);
            if (view == null) {
                this.z = (com.budejie.www.util.g.a) textView.getTag();
                textView.setTextColor(getResources().getColor(R.color.red));
                textView.setPadding(8, 8, 8, 8);
                view2 = textView;
            } else {
                view2 = view;
            }
            view = view2;
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.b.setBackgroundResource(j.a);
        this.e.setTextColor(getResources().getColor(j.b));
        this.i.setBackgroundResource(j.u);
        this.u.setBackgroundResource(j.aA);
        this.q.setBackgroundResource(j.u);
        onRefreshTitleFontTheme(this.d, true);
        onRefreshTitleFontTheme(this.h, false);
    }
}
