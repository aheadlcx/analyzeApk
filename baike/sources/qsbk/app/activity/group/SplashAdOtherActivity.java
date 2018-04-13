package qsbk.app.activity.group;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.baidu.mobads.SplashAd;
import com.qq.e.ads.splash.SplashAD;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MainActivity;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;

public class SplashAdOtherActivity extends Activity {
    private boolean a = false;
    private BaseAd b;

    public static abstract class BaseAd {
        protected Activity a;
        protected ViewGroup b;
        protected Runnable c;

        public abstract void show();

        public BaseAd(Activity activity, ViewGroup viewGroup, Runnable runnable) {
            this.a = activity;
            this.b = viewGroup;
            this.c = runnable;
        }

        public void gotoMain() {
            this.c.run();
        }

        public void gotoNext() {
            if (this.a instanceof SplashAdOtherActivity) {
                ((SplashAdOtherActivity) this.a).a();
            }
        }

        protected void a() {
            if (this.b != null) {
                this.b.removeAllViews();
            }
        }
    }

    public static class BaiduAd extends BaseAd {
        private SplashAd d;

        public BaiduAd(Activity activity, ViewGroup viewGroup, Runnable runnable) {
            super(activity, viewGroup, runnable);
        }

        public void show() {
            this.d = new SplashAd(this.a, this.b, new g(this), "2546374", true);
        }

        protected void a() {
            super.a();
            if (this.d != null) {
                this.d.destroy();
            }
        }
    }

    public static class GDTAd extends BaseAd {
        public GDTAd(Activity activity, ViewGroup viewGroup, Runnable runnable) {
            super(activity, viewGroup, runnable);
        }

        public void show() {
            SplashAD splashAD = new SplashAD(this.a, this.b, "100722332", "8000433159068379", new h(this));
        }
    }

    public static void launch(Context context) {
        launch(context, 0);
    }

    public static void launch(Context context, int i) {
        context.startActivity(new Intent(context, SplashAdOtherActivity.class));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_splash_ad_other);
        a((ViewGroup) findViewById(R.id.splash_ad_container));
    }

    private void a(ViewGroup viewGroup) {
        int optInt;
        int i = 1;
        JSONObject optJSONObject = QsbkApp.indexConfig.optJSONObject("OpenAdConfig");
        if (optJSONObject != null) {
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("ratioConfig");
            if (optJSONObject2 != null) {
                optInt = optJSONObject2.optInt("BaiduMobAD", 0);
                i = optJSONObject2.optInt("GDT", 0);
            } else {
                optInt = 1;
            }
        } else {
            optInt = 1;
        }
        float random = (float) (Math.random() * ((double) (optInt + i)));
        Runnable fVar = new f(this);
        int i2 = i + optInt;
        if (random < ((float) i)) {
            this.b = new GDTAd(this, viewGroup, fVar);
            SplashAdStatUtil.loadingGDT();
            this.b.show();
            LogUtil.d("Select the GDT : " + i + MqttTopic.TOPIC_LEVEL_SEPARATOR + i2);
        } else if (random < ((float) i2)) {
            this.b = new BaiduAd(this, viewGroup, fVar);
            this.b.show();
            SplashAdStatUtil.loadingBaidu();
            LogUtil.d("Select the BaiduMobAD : " + optInt + MqttTopic.TOPIC_LEVEL_SEPARATOR + i2);
        } else {
            gotoMain();
        }
    }

    protected void onPause() {
        super.onPause();
        this.a = false;
    }

    protected void onResume() {
        super.onResume();
        if (this.a) {
            a();
        }
        this.a = true;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.a();
        }
        getWindow().getDecorView().setBackgroundDrawable(null);
    }

    private void a() {
        if (this.a) {
            gotoMain();
        } else {
            this.a = true;
        }
    }

    public void gotoMain() {
        UIHelper.setActivityFullscreen(this, false);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FROM_SPLASH, true);
        startActivity(intent);
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
