package com.budejie.www.activity.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.budejie.www.R;
import com.budejie.www.util.aa;
import com.budejie.www.util.e;
import com.kubility.demo.MP3Recorder;
import com.tencent.connect.common.Constants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;

public class a extends Dialog {
    private static int f;
    private Context a;
    private MICView b;
    private TextView c;
    private MP3Recorder d;
    private com.kubility.demo.a e;
    private int g = 60;
    private long h;
    private Handler i;
    private boolean j = true;
    private long k = 0;
    private long l = 0;
    private String m;
    private int n;
    private String o;
    private int p;
    private String q;
    private boolean r = false;
    private int s;
    private e t;
    private long u = 0;
    private int v = 60;
    private Handler w = new a$1(this);

    static /* synthetic */ int d() {
        int i = f;
        f = i + 1;
        return i;
    }

    private void b(int i) {
        this.m = OnlineConfigAgent.getInstance().getConfigParams(this.a, "low_voice_value");
        this.n = 5;
        if (!TextUtils.isEmpty(this.m)) {
            this.n = Integer.parseInt(this.m);
        }
        this.o = OnlineConfigAgent.getInstance().getConfigParams(this.a, "low_voice_warn_time");
        this.p = 3;
        if (!TextUtils.isEmpty(this.o)) {
            this.p = Integer.parseInt(this.o);
        }
        if (i >= this.n) {
            this.k = 0;
            this.u = 0;
            this.r = false;
            aa.a("MicDialog2", "第一次时间清空");
        } else if (this.k == 0) {
            this.k = System.currentTimeMillis();
            aa.a("MicDialog2", "第一次时间：" + this.k);
        } else {
            this.l = System.currentTimeMillis();
            aa.a("MicDialog2", "非第一次时间：" + this.l);
            if (this.l - this.k >= ((long) (this.p * 1000))) {
                this.r = true;
                this.q = OnlineConfigAgent.getInstance().getConfigParams(this.a, "low_voice_warn_msg");
                if (TextUtils.isEmpty(this.q)) {
                    this.q = "声音过小哦~";
                }
                aa.a("MicDialog2", this.q);
                if (this.u == 0) {
                    f();
                } else if (System.currentTimeMillis() - this.u > ((long) (this.v * 1000))) {
                    f();
                }
            }
        }
        if (!this.r && this.t != null) {
            this.t.a();
        }
    }

    private void f() {
        this.t = e.a(this.a, this.q);
        this.t.a(this.v);
        this.u = System.currentTimeMillis();
    }

    public a(Context context, int i) {
        super(context, R.style.micDialog_style);
        this.a = context;
        this.s = i;
        g();
    }

    private void g() {
        int i = 2;
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.mic_dialog_view, null);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        onWindowAttributesChanged(attributes);
        setCanceledOnTouchOutside(false);
        setContentView(inflate);
        this.b = (MICView) findViewById(R.id.micView);
        this.c = (TextView) findViewById(R.id.txt_tips);
        this.c.setVisibility(8);
        this.e = com.kubility.demo.a.a(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "android/data/com.budejie.www/temp_record_cache/"), "temp_record.mp3");
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this.a, "sample_rate");
        CharSequence configParams2 = OnlineConfigAgent.getInstance().getConfigParams(this.a, "encoding_pcm_bit");
        if (TextUtils.isEmpty(configParams)) {
            configParams = "8000";
        }
        if (!(TextUtils.isEmpty(configParams2) || Constants.VIA_REPORT_TYPE_START_WAP.equals(configParams2) || !"8".equals(configParams2))) {
            i = 3;
        }
        this.e.a(i);
        this.e.b(Integer.parseInt(configParams));
        try {
            MP3Recorder.a().a(this.e);
            this.d = MP3Recorder.a();
            this.d.a(this.w);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        } catch (ExceptionInInitializerError e2) {
            e2.printStackTrace();
        } catch (NoClassDefFoundError e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void a() {
        if (this.d != null) {
            synchronized (this.d) {
                if (!isShowing()) {
                    this.j = false;
                    show();
                    this.d.a(this.w);
                    this.b.setAF(0);
                    this.d.b();
                    f = 0;
                    this.w.sendEmptyMessage(10);
                }
            }
        }
    }

    public void cancel() {
        if (isShowing()) {
            dismiss();
            this.w.removeMessages(10);
        }
        if (this.d != null) {
            this.d.c();
        }
        this.h = System.currentTimeMillis();
        aa.a(AppLinkConstants.TIME, "结束时间-->" + this.h);
    }

    public com.kubility.demo.a b() {
        return this.e;
    }

    public void a(com.kubility.demo.a aVar) {
        this.e = aVar;
        MP3Recorder.a().a(aVar);
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(Handler handler) {
        this.i = handler;
    }

    public boolean c() {
        return this.j;
    }

    public void a(String str) {
        aa.a("MicDialog2", "content:" + str);
        this.c.setText(str);
    }
}
