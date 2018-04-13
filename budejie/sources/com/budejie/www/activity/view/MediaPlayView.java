package com.budejie.www.activity.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.video.o;
import com.budejie.www.f.b;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.budejie.www.util.i;
import com.budejie.www.util.v;
import com.facebook.common.util.UriUtil;
import com.lt.a;
import com.tencent.smtt.sdk.WebView;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaPlayView extends RelativeLayout implements OnClickListener, OnSeekBarChangeListener {
    private int A;
    private b B;
    private String C;
    BudejieApplication a;
    Handler b = new Handler(this) {
        final /* synthetic */ MediaPlayView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            TextView e;
            switch (message.what) {
                case 1:
                    if (this.a.x != null) {
                        this.a.x.a();
                    }
                    int k = this.a.q.k();
                    if (k == 0) {
                        k = this.a.getServerTime();
                    }
                    this.a.j.setMax(k);
                    TextView d = this.a.p;
                    this.a.q;
                    d.setText(ac.a((long) k));
                    e = this.a.o;
                    this.a.q;
                    e.setText(ac.a((long) this.a.q.j()));
                    this.a.b.sendEmptyMessage(2);
                    return;
                case 2:
                    e = this.a.o;
                    this.a.q;
                    e.setText(ac.a((long) this.a.q.j()));
                    this.a.j.setProgress(this.a.q.j());
                    this.a.b.sendEmptyMessage(2);
                    return;
                case 3:
                    this.a.k.setVisibility(8);
                    this.a.z.setVisibility(0);
                    this.a.m.setVisibility(0);
                    this.a.j.setVisibility(0);
                    this.a.n.setImageResource(R.drawable.ic_pause);
                    this.a.n.setPadding(0, 0, 0, 0);
                    return;
                case 4:
                    if (this.a.x != null) {
                        this.a.x.b();
                    }
                    this.a.b.removeMessages(2);
                    this.a.k.setVisibility(0);
                    this.a.z.setVisibility(4);
                    this.a.m.setVisibility(4);
                    this.a.j.setVisibility(8);
                    this.a.l.setImageResource(R.drawable.ic_play);
                    this.a.l.setVisibility(0);
                    this.a.u.setVisibility(8);
                    this.a.n.setImageResource(R.drawable.ic_play);
                    this.a.n.setPadding(5, 0, 0, 0);
                    this.a.j.setProgress(0);
                    e = this.a.o;
                    this.a.q;
                    e.setText(ac.a(0));
                    return;
                case 5:
                    if (this.a.x != null) {
                        this.a.x.a();
                        return;
                    }
                    return;
                case 6:
                    if (this.a.x != null) {
                        this.a.x.a();
                    }
                    this.a.j.setMax(this.a.getServerTime());
                    this.a.p.setText(ac.a((long) this.a.getServerTime()));
                    e = this.a.o;
                    this.a.q;
                    e.setText(ac.a((long) this.a.q.j()));
                    this.a.b.sendEmptyMessage(2);
                    return;
                case 7:
                    this.a.l.setImageResource(R.drawable.ic_pause);
                    this.a.l.setVisibility(8);
                    this.a.u.setVisibility(0);
                    return;
                default:
                    return;
            }
        }
    };
    private final int c = 1;
    private final int d = 2;
    private final int e = 3;
    private final int f = 4;
    private final int g = 5;
    private final int h = 7;
    private Context i;
    private SeekBar j;
    private LinearLayout k;
    private ImageView l;
    private LinearLayout m;
    private ImageView n;
    private TextView o;
    private TextView p;
    private ac q;
    private boolean r = false;
    private int s;
    private String t;
    private ProgressBar u;
    private String v;
    private SharedPreferences w;
    private MediaPlayView$a x;
    private net.tsz.afinal.b y;
    private RelativeLayout z;

    public MediaPlayView(Context context) {
        super(context);
        this.i = context;
        f();
    }

    public MediaPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = context;
        f();
    }

    public String getPlayPath() {
        return a.a(this.i).a(this.C);
    }

    public void setPlayPath(String str) {
        this.C = str;
    }

    public String getDataId() {
        return this.v;
    }

    public void setDataId(String str) {
        this.v = str;
    }

    public void setPlayingListener(MediaPlayView$a mediaPlayView$a) {
        this.x = mediaPlayView$a;
    }

    public MediaPlayView$a getPlayingListener() {
        return this.x;
    }

    public void setServerTime(int i) {
        this.A = i;
    }

    public int getServerTime() {
        return this.A;
    }

    public b getOnMediaPlayerStateListener() {
        return this.B;
    }

    public void setOnMediaPlayerStateListener(b bVar) {
        this.B = bVar;
    }

    public void a() {
        this.l.setImageResource(R.drawable.ic_pause);
        this.l.setVisibility(8);
        this.u.setVisibility(0);
        this.q.c(getPlayPath());
        if (this.q.a()) {
            this.q.a(getOnMediaPlayerStateListener());
            this.b.sendEmptyMessage(5);
            this.q.a(true);
            this.j.setMax(this.q.o());
            this.j.setProgress(this.q.n());
            TextView textView = this.p;
            ac acVar = this.q;
            textView.setText(ac.a((long) this.q.o()));
            textView = this.o;
            acVar = this.q;
            textView.setText(ac.a((long) this.q.n()));
            this.k.setVisibility(8);
            this.z.setVisibility(0);
            this.m.setVisibility(0);
            this.j.setVisibility(0);
            this.n.setImageResource(R.drawable.ic_play);
            this.n.setPadding(5, 0, 0, 0);
            this.b.sendEmptyMessage(2);
            return;
        }
        this.q.d();
        this.q.a(getOnMediaPlayerStateListener());
        this.b.sendEmptyMessage(1);
        this.k.setVisibility(8);
        this.z.setVisibility(0);
        this.m.setVisibility(0);
        this.j.setVisibility(0);
        this.n.setImageResource(R.drawable.ic_pause);
        this.n.setPadding(0, 0, 0, 0);
    }

    public void setSyncPlay(boolean z) {
        this.l.setImageResource(R.drawable.ic_pause);
        this.l.setVisibility(8);
        this.u.setVisibility(0);
        this.q.c(getPlayPath());
        if (this.q.a()) {
            this.q.a(getOnMediaPlayerStateListener());
            this.b.sendEmptyMessage(5);
            this.q.a(true);
            this.j.setMax(this.q.o());
            this.j.setProgress(this.q.n());
            TextView textView = this.p;
            ac acVar = this.q;
            textView.setText(ac.a((long) this.q.o()));
            textView = this.o;
            acVar = this.q;
            textView.setText(ac.a((long) this.q.n()));
            this.k.setVisibility(8);
            this.z.setVisibility(0);
            this.m.setVisibility(0);
            this.j.setVisibility(0);
            this.n.setImageResource(R.drawable.ic_play);
            this.n.setPadding(5, 0, 0, 0);
            this.b.sendEmptyMessage(2);
            return;
        }
        this.q.d();
        this.q.a(getOnMediaPlayerStateListener());
        this.b.sendEmptyMessage(1);
        this.k.setVisibility(8);
        this.z.setVisibility(0);
        this.m.setVisibility(0);
        this.j.setVisibility(0);
        this.n.setImageResource(R.drawable.ic_pause);
        this.n.setPadding(0, 0, 0, 0);
    }

    public void b() {
        this.k.setVisibility(0);
        this.l.setImageResource(R.drawable.ic_pause);
        this.l.setVisibility(8);
        this.u.setVisibility(0);
        this.q.c(getPlayPath());
        this.q.a(getOnMediaPlayerStateListener());
    }

    private void f() {
        this.a = BudejieApplication.b();
        this.w = this.i.getSharedPreferences("weiboprefer", 0);
        this.y = new net.tsz.afinal.b(this.i.getApplicationContext(), new v(this.i));
        this.y.a("User-Agent", new WebView(this.i).getSettings().getUserAgentString() + NetWorkUtil.a());
        this.y.a(NetWorkUtil.a(this.i));
        this.q = ac.a(this.i);
        addView(LayoutInflater.from(this.i).inflate(R.layout.media_progress_view, null));
        this.j = (SeekBar) findViewById(R.id.mSeekBar);
        this.k = (LinearLayout) findViewById(R.id.playbutton_layout);
        this.l = (ImageView) findViewById(R.id.play_iv);
        this.l.setVisibility(0);
        this.m = (LinearLayout) findViewById(R.id.layout_playHandler);
        this.n = (ImageView) findViewById(R.id.image_playState);
        this.o = (TextView) findViewById(R.id.mCurrentTime);
        this.p = (TextView) findViewById(R.id.mDurationTime);
        this.u = (ProgressBar) findViewById(R.id.playProgressBar);
        this.z = (RelativeLayout) findViewById(R.id.timeLayout);
        this.u.setVisibility(8);
        this.k.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.j.setOnSeekBarChangeListener(this);
        this.z.setOnClickListener(this);
        this.j.setLayoutParams(getSeekbarParams());
        g();
    }

    public void onClick(View view) {
        if (o.a(this.i) || !getPlayPath().startsWith(UriUtil.HTTP_SCHEME)) {
            switch (view.getId()) {
                case R.id.playbutton_layout:
                    aa.a("MediaPlayView", "控件中的地址-->" + getPlayPath() + "播放器中的地址-->" + this.q.l());
                    if (this.q.l() != getPlayPath()) {
                        this.b.sendEmptyMessage(7);
                        if (getPlayPath().startsWith(UriUtil.HTTP_SCHEME)) {
                            this.t = getPlayPath();
                            this.q.c(getPlayPath());
                            this.q.a(getPlayPath());
                            this.q.a(getOnMediaPlayerStateListener());
                            return;
                        }
                        this.t = getPlayPath();
                        this.q.c(this.t);
                        this.q.b(getPlayPath());
                        this.q.a(getOnMediaPlayerStateListener());
                        return;
                    } else if (!this.q.c()) {
                        this.b.sendEmptyMessage(7);
                        if (this.q.a()) {
                            this.q.d();
                            return;
                        } else if (getPlayPath().startsWith(UriUtil.HTTP_SCHEME)) {
                            this.t = getPlayPath();
                            this.q.c(getPlayPath());
                            this.q.a(getPlayPath());
                            this.q.a(getOnMediaPlayerStateListener());
                            return;
                        } else {
                            this.t = getPlayPath();
                            this.q.c(this.t);
                            this.q.b(getPlayPath());
                            this.q.a(getOnMediaPlayerStateListener());
                            return;
                        }
                    } else {
                        return;
                    }
                case R.id.layout_playHandler:
                    if (this.q.c()) {
                        this.n.setImageResource(R.drawable.ic_play);
                        this.n.setPadding(5, 0, 0, 0);
                        this.q.e();
                        this.a.a(Status.stop);
                        return;
                    }
                    this.n.setImageResource(R.drawable.ic_pause);
                    this.n.setPadding(0, 0, 0, 0);
                    if (this.q.a()) {
                        this.q.d();
                        return;
                    } else {
                        this.q.a(this.t);
                        return;
                    }
                default:
                    return;
            }
        }
        an.a((Activity) this.i, this.i.getString(R.string.nonet), -1).show();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.s = i;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        this.r = true;
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (this.r) {
            this.r = false;
            this.b.sendEmptyMessage(2);
            this.q.a(this.s);
        }
    }

    public void c() {
        try {
            if (this.q.l().equals(getPlayPath())) {
                this.b.sendEmptyMessage(1);
                this.b.sendEmptyMessage(3);
                a(Integer.valueOf(0));
                this.a.a(Status.start);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d() {
        this.b.removeMessages(2);
        this.k.setVisibility(0);
        this.z.setVisibility(4);
        this.m.setVisibility(4);
        this.j.setVisibility(8);
        this.l.setImageResource(R.drawable.ic_play);
        this.l.setVisibility(0);
        this.u.setVisibility(8);
        this.n.setImageResource(R.drawable.ic_play);
        this.n.setPadding(5, 0, 0, 0);
        this.j.setProgress(0);
        TextView textView = this.o;
        ac acVar = this.q;
        textView.setText(ac.a(0));
    }

    public void a(int i) {
        this.b.sendEmptyMessage(4);
        if (i == 0) {
            a(Integer.valueOf(1));
            this.a.a(Status.end);
        }
    }

    public void e() {
        this.k.setVisibility(0);
        this.u.setVisibility(8);
        this.l.setVisibility(0);
        this.l.setImageResource(R.drawable.ic_play);
        this.z.setVisibility(4);
        this.m.setVisibility(4);
        this.j.setVisibility(8);
        if (this.q != null && !this.q.c() && this.a != null) {
            this.a.a(Status.stop);
        }
    }

    public LayoutParams getParams2() {
        float b = ((float) 10) * i.a().b(this.i);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins((int) b, 0, (int) b, 0);
        layoutParams.gravity = 81;
        return layoutParams;
    }

    public LayoutParams getParams() {
        float b = ((float) 30) - (((float) 10) * i.a().b(this.i));
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.gravity = 81;
        return layoutParams;
    }

    private RelativeLayout.LayoutParams getSeekbarParams() {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.i.getResources(), R.drawable.playbutton_normal_bg);
        int height = decodeResource.getHeight();
        Bitmap decodeResource2 = BitmapFactory.decodeResource(this.i.getResources(), R.drawable.ic_thumb);
        int height2 = decodeResource2.getHeight();
        float b = i.a().b(this.i);
        float f = ((float) 30) - (((float) 10) * b);
        int i = (int) (((float) 60) * b);
        height = (int) ((((float) (height / 2)) - ((((float) 30) * b) / 2.0f)) - ((float) (height2 / 2)));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(i, height, 0, 0);
        decodeResource.recycle();
        decodeResource2.recycle();
        return layoutParams;
    }

    private void g() {
        int b = (int) (((float) 30) * i.a().b(this.i));
        int width = BitmapFactory.decodeResource(this.i.getResources(), R.drawable.playbutton_normal_bg).getWidth();
        int i = width / 2;
        this.z.setPadding(width, 0, 0, 0);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, b);
        layoutParams.setMargins(0, 0, 0, 0);
        layoutParams.addRule(15, -1);
        this.z.setLayoutParams(layoutParams);
    }

    private synchronized void a(Integer num) {
        String string = this.w.getString("id", null);
        Object dataId = getDataId();
        if (!TextUtils.isEmpty(dataId)) {
            net.tsz.afinal.a.b bVar = new net.tsz.afinal.a.b();
            bVar.d("c", "voice");
            bVar.d("a", "stat");
            bVar.d("pid", dataId.toString());
            String str = "userid";
            if (string == null) {
                string = "0";
            }
            bVar.d(str, string);
            bVar.d("flag", num.toString());
            this.y.a("http://api.budejie.com/api/api_open.php", bVar, new net.tsz.afinal.a.a<String>(this) {
                final /* synthetic */ MediaPlayView a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onSuccess(Object obj) {
                    a((String) obj);
                }

                public void a(String str) {
                    if (str != null) {
                        aa.a("success", "播放统计-->" + str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.has(CheckCodeDO.CHECKCODE_USER_INPUT_KEY)) {
                                if (!"0".equals(jSONObject.getString(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
