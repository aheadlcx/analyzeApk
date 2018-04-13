package cn.xiaochuankeji.tieba.ui.videomaker;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.a;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.lang.reflect.Field;

public class VideoMakerConfigActivity extends a {
    private static Field b;
    private static Field c;
    private static Field d;
    private static Field e;
    private static Field f;
    private static Field g;
    private i a;

    protected int a() {
        return R.layout.activity_videomaker_config;
    }

    protected boolean a(Bundle bundle) {
        this.a = j.b();
        if (b == null) {
            try {
                b = i.class.getDeclaredField("a");
                b.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (c == null) {
            try {
                c = i.class.getDeclaredField("b");
                c.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
        if (d == null) {
            try {
                d = i.class.getDeclaredField("c");
                d.setAccessible(true);
            } catch (NoSuchFieldException e22) {
                e22.printStackTrace();
            }
        }
        if (e == null) {
            try {
                e = i.class.getDeclaredField("d");
                e.setAccessible(true);
            } catch (NoSuchFieldException e222) {
                e222.printStackTrace();
            }
        }
        if (f == null) {
            try {
                f = i.class.getDeclaredField(Parameters.EVENT);
                f.setAccessible(true);
            } catch (NoSuchFieldException e2222) {
                e2222.printStackTrace();
            }
        }
        if (g == null) {
            try {
                g = i.class.getDeclaredField("f");
                g.setAccessible(true);
            } catch (NoSuchFieldException e22222) {
                e22222.printStackTrace();
            }
        }
        return true;
    }

    protected void i_() {
        ((TextView) findViewById(R.id.label_phone_info)).setText("厂商：" + Build.MANUFACTURER + "，机型：" + Build.MODEL);
        ((TextView) findViewById(R.id.label_current_config)).setText(e());
        findViewById(R.id.btn_hwrecoder_540p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.d, Integer.valueOf(540));
            }
        });
        findViewById(R.id.btn_hwrecoder_432p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.d, Integer.valueOf(432));
            }
        });
        findViewById(R.id.btn_hwrecoder_360p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.d, Integer.valueOf(com.umeng.analytics.a.p));
            }
        });
        findViewById(R.id.btn_softrecoder_540p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.f, Integer.valueOf(540));
            }
        });
        findViewById(R.id.btn_softrecoder_432p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.f, Integer.valueOf(432));
            }
        });
        findViewById(R.id.btn_softrecoder_360p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.f, Integer.valueOf(com.umeng.analytics.a.p));
            }
        });
        findViewById(R.id.btn_preview_720p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.c, Integer.valueOf(720));
            }
        });
        findViewById(R.id.btn_preview_540p).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.c, Integer.valueOf(540));
            }
        });
        findViewById(R.id.btn_videomaker_enabled).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.b, Boolean.TRUE);
            }
        });
        findViewById(R.id.btn_videomaker_disabled).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.b, Boolean.FALSE);
            }
        });
        findViewById(R.id.btn_hwrecoder_enabled).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.e, Boolean.TRUE);
            }
        });
        findViewById(R.id.btn_hwrecoder_disabled).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.e, Boolean.FALSE);
            }
        });
        findViewById(R.id.btn_hwprocesser_enabled).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.g, Boolean.TRUE);
            }
        });
        findViewById(R.id.btn_hwprocesser_disabled).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoMakerConfigActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(VideoMakerConfigActivity.g, Boolean.FALSE);
            }
        });
    }

    public String e() {
        return "videoMakerEnabled=" + this.a.a + "\npreviewWidth=" + this.a.b + "\nhwrecoderWidth=" + this.a.c + "\nhwrecoderEnabled=" + this.a.d + "\nsoftrecoderWidth=" + this.a.e + "\nhwprocesserEnabled=" + this.a.f;
    }

    private void a(Field field, Object obj) {
        try {
            field.set(this.a, obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        finish();
    }
}
