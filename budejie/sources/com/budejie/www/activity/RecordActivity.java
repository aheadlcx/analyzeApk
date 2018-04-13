package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.budejie.www.R;
import com.budejie.www.activity.view.ShowStepView;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.util.ad;
import com.budejie.www.util.ai;
import com.budejie.www.util.i;
import com.budejie.www.util.j;
import com.tencent.connect.common.Constants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;
import java.util.Timer;

public class RecordActivity extends OauthWeiboBaseAct implements OnClickListener {
    private static int B = 0;
    private ad A;
    private int C = 120;
    private int D = 10;
    private long E = 0;
    private Timer F = new Timer();
    private com.kubility.demo.a G;
    private SharedPreferences H;
    private String I;
    private boolean J = true;
    private boolean K = true;
    private Bundle L;
    Handler a = new Handler(this) {
        final /* synthetic */ RecordActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int h;
            TextView l;
            switch (message.what) {
                case 1:
                    this.a.n.setBackgroundResource(R.drawable.bg_record_state_continue_factory);
                    this.a.p.setText("按住继续录音");
                    this.a.o.setVisibility(8);
                    this.a.q.setImageResource(R.drawable.ic_play_white);
                    this.a.q.setVisibility(0);
                    this.a.r.setVisibility(0);
                    this.a.v.setVisibility(0);
                    this.a.w.setVisibility(0);
                    if (RecordActivity.B <= this.a.C) {
                        h = this.a.C - RecordActivity.B;
                        return;
                    }
                    return;
                case 2:
                    this.a.x.setVisibility(0);
                    this.a.s.setVisibility(0);
                    this.a.x.setMax(this.a.A.j());
                    aa.a("record", "-->" + this.a.A.j());
                    l = this.a.u;
                    this.a.A;
                    l.setText(ad.a((long) this.a.A.j()));
                    l = this.a.t;
                    this.a.A;
                    l.setText(ad.a((long) this.a.A.i()));
                    this.a.a.sendEmptyMessage(3);
                    return;
                case 3:
                    l = this.a.t;
                    this.a.A;
                    l.setText(ad.a((long) this.a.A.i()));
                    this.a.x.setProgress(this.a.A.i());
                    this.a.a.sendEmptyMessage(3);
                    return;
                case 4:
                    l = this.a.t;
                    this.a.A;
                    l.setText(ad.a((long) this.a.A.i()));
                    this.a.x.setProgress(this.a.A.i());
                    return;
                case 5:
                    this.a.q.setVisibility(0);
                    this.a.r.setVisibility(0);
                    this.a.q.setImageResource(R.drawable.ic_play_white);
                    this.a.x.setProgress(0);
                    l = this.a.t;
                    this.a.A;
                    l.setText(ad.a(0));
                    this.a.x.setVisibility(8);
                    this.a.s.setVisibility(4);
                    this.a.a.removeMessages(3);
                    return;
                case 6:
                    this.a.n.setBackgroundResource(j.aJ);
                    this.a.p.setText("按住录音");
                    this.a.p.setTextColor(this.a.getResources().getColor(R.color.text_gray));
                    this.a.o.setVisibility(0);
                    this.a.o.setImageResource(j.aK);
                    this.a.q.setVisibility(8);
                    this.a.r.setText("00:00");
                    this.a.r.setVisibility(8);
                    this.a.x.setProgress(0);
                    this.a.x.setVisibility(8);
                    this.a.s.setVisibility(4);
                    this.a.v.setVisibility(8);
                    this.a.w.setVisibility(8);
                    return;
                case 8:
                    this.a.y.cancel();
                    this.a.n.setBackgroundResource(R.drawable.bg_record_state_continue_factory);
                    this.a.p.setText("录音完成");
                    this.a.o.setVisibility(8);
                    this.a.q.setImageResource(R.drawable.ic_play_white);
                    this.a.q.setVisibility(0);
                    this.a.v.setVisibility(0);
                    this.a.w.setVisibility(0);
                    if (RecordActivity.B <= this.a.C) {
                        h = this.a.C - RecordActivity.B;
                        return;
                    }
                    return;
                case 10:
                    h = ((Integer) message.obj).intValue();
                    if (h <= 0) {
                        this.a.K = false;
                    } else {
                        this.a.K = true;
                    }
                    RecordActivity.B = h / 1000;
                    TextView e = this.a.r;
                    this.a.A;
                    e.setText(ad.a((long) h));
                    this.a.r.setVisibility(0);
                    ai.b(this.a, RecordActivity.B);
                    return;
                case 21:
                    RecordActivity.d();
                    if (RecordActivity.B <= this.a.C) {
                        h = this.a.C - RecordActivity.B;
                    }
                    if (RecordActivity.B < this.a.C) {
                        this.a.a.sendEmptyMessageDelayed(21, 1000);
                    } else {
                        this.a.a.sendEmptyMessage(8);
                    }
                    aa.a(AppLinkConstants.TIME, "时间-->" + RecordActivity.B);
                    return;
                case 22:
                    this.a.a.removeMessages(21);
                    this.a.A.a(this.a.G.a().getAbsolutePath(), this.a.a);
                    ai.b(this.a, RecordActivity.B);
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
    private Button i;
    private RelativeLayout j;
    private RelativeLayout k;
    private TextView l;
    private ImageView m;
    private LinearLayout n;
    private ImageView o;
    private TextView p;
    private ImageView q;
    private TextView r;
    private RelativeLayout s;
    private TextView t;
    private TextView u;
    private ImageView v;
    private ImageView w;
    private SeekBar x;
    private com.budejie.www.activity.view.a y;
    private ShowStepView z;

    class a implements com.budejie.www.f.b {
        final /* synthetic */ RecordActivity a;

        a(RecordActivity recordActivity) {
            this.a = recordActivity;
        }

        public void a() {
        }

        public void b() {
        }

        public void a(int i) {
            this.a.a.sendEmptyMessage(5);
        }
    }

    class b implements OnTouchListener {
        final /* synthetic */ RecordActivity a;

        b(RecordActivity recordActivity) {
            this.a = recordActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.a.A.c() || this.a.A.b()) {
                this.a.A.g();
            }
            if (ac.a(this.a).c()) {
                ac.a(this.a).g();
            }
            if (RecordActivity.B < this.a.C) {
                if (motionEvent.getAction() == 0) {
                    if (this.a.y.c()) {
                        this.a.E = System.currentTimeMillis();
                        this.a.J = true;
                        this.a.n.setBackgroundResource(R.drawable.bg_record_state_press_factory);
                        this.a.p.setText("松手结束");
                        this.a.p.setTextColor(this.a.getResources().getColor(R.color.white));
                        this.a.o.setVisibility(0);
                        this.a.r.setVisibility(8);
                        this.a.o.setImageResource(R.drawable.ic_mic_white);
                        this.a.y.a();
                    }
                } else if (motionEvent.getAction() == 1) {
                    this.a.y.cancel();
                    this.a.n.setBackgroundResource(j.aJ);
                    this.a.p.setText("按住录音");
                    this.a.a.sendEmptyMessage(1);
                }
            } else if (this.a.J) {
                this.a.J = false;
                Toast.makeText(this.a, "录音时间已用完!", 0).show();
                this.a.A.a(this.a.G.a().getAbsolutePath(), this.a.a);
            }
            return true;
        }
    }

    class c implements OnSeekBarChangeListener {
        final /* synthetic */ RecordActivity a;
        private int b;
        private boolean c = false;

        c(RecordActivity recordActivity) {
            this.a = recordActivity;
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
                this.a.A.a(this.b);
            }
        }
    }

    static /* synthetic */ int d() {
        int i = B;
        B = i + 1;
        return i;
    }

    protected void onCreate(Bundle bundle) {
        int i = 2;
        super.onCreate(bundle);
        i.a().a((Activity) this);
        setContentView(R.layout.activity_record_factory);
        e();
        this.H = getSharedPreferences("weiboprefer", 0);
        Intent intent = getIntent();
        this.I = intent.getStringExtra("image_path");
        this.L = intent.getBundleExtra("theme_data");
        this.m.setImageDrawable(Drawable.createFromPath(this.I));
        this.y = new com.budejie.www.activity.view.a(this, 1);
        this.y.a(this.a);
        this.A = ad.a();
        this.A.a((Context) this);
        this.A.a(new a(this));
        String string = this.H.getString("voice_file_cache", null);
        if (string == null) {
            this.G = com.kubility.demo.a.a(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "android/data/com.budejie.www/record_cache/"), i.a().d() + ".mp3", true);
        } else {
            this.G = this.y.b();
            this.G.a(new File(string));
            this.G.a(true);
        }
        string = OnlineConfigAgent.getInstance().getConfigParams(this, "sample_rate");
        CharSequence configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "encoding_pcm_bit");
        if (TextUtils.isEmpty(string)) {
            string = "8000";
        }
        if (!(TextUtils.isEmpty(configParams) || Constants.VIA_REPORT_TYPE_START_WAP.equals(configParams) || !"8".equals(configParams))) {
            i = 3;
        }
        this.G.a(i);
        this.G.b(Integer.parseInt(string));
        this.y.a(this.G);
        this.y.a(this.a);
        B = ai.e(this);
        if (B > 0) {
            this.a.sendEmptyMessage(1);
        }
        Object configParams2 = OnlineConfigAgent.getInstance().getConfigParams(this, "record_min_time");
        if (!TextUtils.isEmpty(configParams2)) {
            this.D = Integer.parseInt(configParams2);
        }
        configParams2 = OnlineConfigAgent.getInstance().getConfigParams(this, "record_max_time");
        if (!TextUtils.isEmpty(configParams2)) {
            this.C = Integer.parseInt(configParams2);
        }
        this.y.a(this.C);
        if (ac.a((Context) this).c()) {
            ac.a((Context) this).g();
        }
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onPause() {
        super.onPause();
        this.H.edit().putString("voice_file_cache", this.G.a().getAbsolutePath()).commit();
        if (this.A != null && this.A.c()) {
            this.A.g();
        }
        if (this.y.isShowing()) {
            this.y.cancel();
            this.n.setBackgroundResource(j.aJ);
            this.p.setText("按住录音");
            this.a.sendEmptyMessage(1);
            this.A.a(this.G.a().getAbsolutePath(), this.a);
        }
    }

    protected void onResume() {
        super.onResume();
        B = ai.e(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        i.a().b((Activity) this);
    }

    private void e() {
        this.i = (Button) findViewById(R.id.title_left_publish_btn);
        this.i.setVisibility(8);
        this.b = (RelativeLayout) findViewById(R.id.title);
        this.c = (LinearLayout) findViewById(R.id.left_layout);
        this.d = (Button) findViewById(R.id.title_left_btn);
        this.d.setVisibility(0);
        this.d.setOnClickListener(this);
        this.e = (TextView) findViewById(R.id.title_center_txt);
        this.e.setText("录音");
        this.f = (RelativeLayout) findViewById(R.id.title_refresh_layout);
        this.f.setVisibility(0);
        this.g = (Button) findViewById(R.id.refresh_btn);
        this.g.setVisibility(8);
        this.h = (TextView) findViewById(R.id.title_right_btn);
        this.h.setVisibility(0);
        this.c.setVisibility(0);
        this.h.setOnClickListener(this);
        this.j = (RelativeLayout) findViewById(R.id.record_layout);
        this.k = (RelativeLayout) findViewById(R.id.recordBottomFrame);
        this.m = (ImageView) findViewById(R.id.recordPhoto);
        this.n = (LinearLayout) findViewById(R.id.recordFrame);
        this.o = (ImageView) findViewById(R.id.recordIcon);
        this.p = (TextView) findViewById(R.id.recordText);
        this.q = (ImageView) findViewById(R.id.recordPlayBtn);
        this.r = (TextView) findViewById(R.id.recordTime);
        this.s = (RelativeLayout) findViewById(R.id.layoutTime);
        this.t = (TextView) findViewById(R.id.recordPlayCurrentTime);
        this.u = (TextView) findViewById(R.id.recordPlayDurationTime);
        this.l = (TextView) findViewById(R.id.recordAvailableTime);
        this.l.setVisibility(8);
        this.v = (ImageView) findViewById(R.id.reordCancel);
        this.w = (ImageView) findViewById(R.id.recordCommit);
        this.x = (SeekBar) findViewById(R.id.mSeekBar);
        this.z = (ShowStepView) findViewById(R.id.recordStepView);
        this.z.setCurrentLine(2);
        this.n.setOnTouchListener(new b(this));
        this.q.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnSeekBarChangeListener(new c(this));
        this.m.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_btn:
                f();
                finish();
                return;
            case R.id.recordPhoto:
                if (this.A.c()) {
                    this.q.setVisibility(0);
                    this.q.setImageResource(R.drawable.ic_play_white);
                    this.A.e();
                    return;
                }
                return;
            case R.id.recordPlayBtn:
                if (!this.K) {
                    return;
                }
                if (this.A.c()) {
                    this.q.setVisibility(0);
                    this.q.setImageResource(R.drawable.ic_play_white);
                    this.A.e();
                    return;
                }
                this.q.setVisibility(8);
                this.r.setVisibility(8);
                if (this.A.b()) {
                    this.A.d();
                    return;
                }
                if (i.a().c() != null) {
                    i.a().c().g();
                }
                this.A.d();
                this.a.sendEmptyMessage(2);
                return;
            case R.id.reordCancel:
                b();
                return;
            case R.id.recordCommit:
                a();
                return;
            case R.id.title_right_btn:
                a();
                return;
            default:
                return;
        }
    }

    public void a() {
        if (B < this.D) {
            Toast.makeText(this, "录音时间太短,你可以尝试继续录音", R.color.black).show();
            return;
        }
        Intent intent = new Intent(this, BeautifyRecordActivity.class);
        intent.putExtra("record_path", this.G.a().getAbsolutePath());
        intent.putExtra("img_path", this.I);
        intent.putExtra("h5_reserve", getIntent().getStringExtra("h5_reserve"));
        intent.putExtra("theme_data", this.L);
        startActivity(intent);
        com.budejie.www.http.i.a(getString(R.string.track_action_send_voice), getString(R.string.track_event_voice_record_ready));
    }

    public void b() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme_CreateUgc);
        OnClickListener anonymousClass2 = new OnClickListener(this) {
            final /* synthetic */ RecordActivity b;

            public void onClick(View view) {
                Object obj = view.getTag() == null ? "" : view.getTag().toString();
                if ("重新录音".equals(obj)) {
                    if (this.b.A.c() || this.b.A.b()) {
                        this.b.A.g();
                    }
                    RecordActivity.B = 0;
                    ai.b(this.b, 0);
                    this.b.a(this.b.G.a());
                    this.b.a.sendEmptyMessage(6);
                } else if ("取消发布".equals(obj)) {
                    this.b.f();
                    this.b.finish();
                }
                dialog.dismiss();
            }
        };
        String[] strArr = new String[]{"重新录音", "取消发布"};
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
        linearLayout.setMinimumWidth(10000);
        ((TextView) linearLayout.findViewById(R.id.message_title)).setVisibility(8);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
        linearLayout2.setBackgroundResource(j.aC);
        Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
        button.setBackgroundResource(j.aC);
        button.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecordActivity b;

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (i.a().b((Context) this) * 45.0f));
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 2);
        for (int i = 0; i < strArr.length; i++) {
            CharSequence charSequence = strArr[i];
            View imageView = new ImageView(this);
            imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
            imageView.setLayoutParams(layoutParams2);
            View button2 = new Button(this);
            button2.setGravity(17);
            button2.setText(charSequence);
            button2.setTextSize(2, 20.0f);
            button2.setBackgroundResource(j.aC);
            button2.setTextColor(getResources().getColor(R.color.text_gray));
            button2.setTag(charSequence);
            button2.setOnClickListener(anonymousClass2);
            button2.setLayoutParams(layoutParams);
            if (i == 0) {
                linearLayout2.addView(button2);
            } else {
                linearLayout2.addView(imageView);
                linearLayout2.addView(button2);
            }
        }
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = -1000;
        attributes.gravity = 80;
        dialog.onWindowAttributesChanged(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(linearLayout);
        dialog.show();
    }

    private void a(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    private void f() {
        String string = this.H.getString("voice_image_cache", "");
        String string2 = this.H.getString("voice_file_cache", "");
        com.budejie.www.util.b.a(new File(string));
        com.budejie.www.util.b.a(new File(string2));
        Editor edit = this.H.edit();
        edit.remove("voice_comment_cache");
        edit.remove("voice_image_cache");
        ai.b((Context) this, 0);
        edit.remove("voice_file_cache");
        edit.remove("bgmusic_file_cache");
        edit.commit();
    }

    public void onBackPressed() {
        f();
        super.onBackPressed();
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.b.setBackgroundResource(j.a);
        this.e.setTextColor(getResources().getColor(j.b));
        this.j.setBackgroundResource(j.u);
        this.k.setBackgroundResource(j.aA);
        this.z.setBackgroundResource(j.u);
        this.o.setImageResource(j.aK);
        if (this.p.getText().toString().equals("按住录音")) {
            this.n.setBackgroundResource(j.aJ);
        }
        onRefreshTitleFontTheme(this.d, true);
        onRefreshTitleFontTheme(this.h, false);
    }
}
