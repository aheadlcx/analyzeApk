package com.budejie.www.util;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.kubility.demo.MP3Recorder;
import com.kubility.demo.a;
import com.tencent.connect.common.Constants;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;

public class aw {
    private int a = 60;
    private int b;
    private Context c;
    private MP3Recorder d;
    private a e;
    private Handler f;
    private boolean g = true;
    private Handler h = new Handler(this) {
        final /* synthetic */ aw a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            Message message2;
            switch (message.what) {
                case -5:
                    Toast.makeText(this.a.c, "录音失败,请重新尝试", 0).show();
                    return;
                case -4:
                    Toast.makeText(this.a.c, "初始化失败,采样率手机不支持", 0).show();
                    return;
                case -3:
                    Toast.makeText(this.a.c, "录音失败,请重新尝试", 0).show();
                    return;
                case -1:
                    Toast.makeText(this.a.c, "初始化失败,采样率手机不支持", 0).show();
                    return;
                case 1:
                    if (this.a.f != null) {
                        this.a.f.sendEmptyMessage(21);
                        return;
                    }
                    return;
                case 2:
                    if (this.a.f != null) {
                        this.a.f.removeMessages(21);
                        this.a.f.sendEmptyMessage(22);
                    }
                    this.a.g = true;
                    return;
                case 5:
                    aa.b("VoiceRecoderUtil", "msg.obj=" + message.obj);
                    if (this.a.f != null) {
                        message2 = new Message();
                        message2.what = 23;
                        message2.obj = message.obj;
                        this.a.f.sendMessage(message2);
                        return;
                    }
                    return;
                case 10:
                    this.a.b = this.a.b + 1;
                    aa.b("VoiceRecoderUtil", "msg.obj=" + message.obj);
                    if (this.a.f != null) {
                        message2 = new Message();
                        message2.what = 24;
                        message2.obj = Integer.valueOf(this.a.b);
                        this.a.f.sendMessage(message2);
                    }
                    if (this.a.b < this.a.a) {
                        this.a.h.sendEmptyMessageDelayed(10, 1000);
                        return;
                    }
                    this.a.b();
                    Toast.makeText(this.a.c, "本次录音时间" + this.a.a + "s已用完", 1).show();
                    return;
                default:
                    return;
            }
        }
    };

    public aw(Context context) {
        this.c = context;
        f();
    }

    private void f() {
        int i = 2;
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(this.c, "maxtime");
        if (TextUtils.isEmpty(configParams)) {
            configParams = "60";
        }
        this.a = Integer.parseInt(configParams);
        this.e = a.a(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "android/data/com.budejie.www/temp_record_cache/"), "temp_record.mp3");
        configParams = OnlineConfigAgent.getInstance().getConfigParams(this.c, "sample_rate");
        CharSequence configParams2 = OnlineConfigAgent.getInstance().getConfigParams(this.c, "encoding_pcm_bit");
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
            this.d.a(this.h);
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
                this.g = false;
                this.d.a(this.h);
                this.d.b();
                this.b = 0;
                this.h.sendEmptyMessageDelayed(10, 1000);
            }
        }
    }

    public void b() {
        this.h.removeMessages(10);
        if (this.d != null) {
            this.d.c();
        }
    }

    public a c() {
        return this.e;
    }

    public int d() {
        return this.b;
    }

    public void a(Handler handler) {
        this.f = handler;
    }

    public boolean e() {
        return this.g;
    }
}
