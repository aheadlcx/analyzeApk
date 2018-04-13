package qsbk.app.im;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.support.v4.util.LongSparseArray;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.commonsdk.proguard.g;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseEmotionActivity;
import qsbk.app.im.CollectEmotion.CollectionViewPager;
import qsbk.app.im.datastore.ContactListItemStore;
import qsbk.app.im.emotion.EmotionViewPager;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.widget.DotView;

public abstract class IMChatBaseActivity extends BaseEmotionActivity implements SensorEventListener, MessageCountManager$UnreadCountListener, MsgStateChangeObserver {
    public static final String GROUP_ID = "group_id";
    public static final int MAX_CONTENT_LEN = 3500;
    public static final String TO_ICON = "to_icon";
    public static final String TO_ID = "to_id";
    public static final String TO_NICK = "to_nick";
    public static final String USER_ID = "user_id";
    public static final String USER_TYPE = "user_type";
    private static float a = Float.MIN_VALUE;
    private static long[] b = new long[]{100, 300};
    ConversationTitleBar A;
    MessageCountManager B = null;
    private Sensor C;
    private SensorManager D;
    private AudioManager E;
    private final BroadcastReceiver F = new fj(this);
    private PowerManager Q;
    private WakeLock R;
    private int S = 1;
    private Vibrator T;
    private LayoutInflater U;
    private final a c = new a(this, this.y);
    protected ScrollTopListView d;
    protected LinearLayout e;
    protected ImageView f;
    protected ChatListAdapter g;
    protected TextView h;
    protected GridView i;
    protected View j = null;
    protected View k;
    protected EmotionViewPager l;
    protected DotView m;
    protected View n;
    protected CollectionViewPager o;
    protected DotView p;
    protected ImageButton q;
    protected ImageButton r;
    protected IMChatMsgSource s = null;
    protected float t = 1.7f;
    protected float u = 0.4f;
    protected long v = 100;
    protected ContactListItemStore w = null;
    protected VoiceUIHelper x;
    protected Handler y = new Handler();
    UserChatManager z = null;

    private final class a implements Runnable {
        LongSparseArray<Integer> a = new LongSparseArray();
        Handler b;
        final /* synthetic */ IMChatBaseActivity c;

        public a(IMChatBaseActivity iMChatBaseActivity, Handler handler) {
            this.c = iMChatBaseActivity;
            this.b = handler;
        }

        a a(long j, int i) {
            Logger.getInstance().debug(IMChatBaseActivity.class.getSimpleName(), "add", String.format("OnMsgStateChange dbid: %s, status: %s, curTime: %s", new Object[]{Long.valueOf(j), Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())}));
            this.a.put(j, Integer.valueOf(i));
            return this;
        }

        void a() {
            this.b.removeCallbacks(this);
        }

        void a(long j) {
            a();
            if (j <= 0) {
                this.b.post(this);
            } else {
                this.b.postDelayed(this, j);
            }
        }

        public void run() {
            this.c.g.onMsgStateChange(this.a);
            this.a.clear();
            Logger.getInstance().debug(IMChatBaseActivity.class.getSimpleName(), "run", "OnMsgStateChange notify done.");
        }
    }

    public abstract ContactListItem newContactItem();

    protected static final String a(String str, int i, String str2) {
        String[] split = str.split(str2);
        if (split.length > i) {
            return split[i];
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private static void a(WakeLock wakeLock) {
        Exception exception = null;
        try {
            wakeLock.release(1);
        } catch (Exception e) {
            exception = e;
        }
        if (exception != null) {
            try {
                Method method = wakeLock.getClass().getMethod("release", new Class[]{Integer.TYPE});
                method.setAccessible(true);
                method.invoke(wakeLock, new Object[]{Integer.valueOf(1)});
            } catch (NoSuchMethodException e2) {
                exception = e2;
            } catch (InvocationTargetException e3) {
                exception = e3;
            } catch (IllegalAccessException e4) {
                exception = e4;
            }
            if (exception != null) {
                wakeLock.release();
            }
        }
    }

    public String getContent() {
        return this.G.getText().toString().trim();
    }

    public int getUserType() {
        return getIntent().getIntExtra(USER_TYPE, 0);
    }

    public String getUserId() {
        return getIntent().getStringExtra("user_id");
    }

    public String getToId() {
        return getIntent().getStringExtra("to_id");
    }

    public String getToIcon() {
        return getIntent().getStringExtra(TO_ICON);
    }

    public String getToNick() {
        return getIntent().getStringExtra(TO_NICK);
    }

    public int getOwnerId() {
        return getIntent().getIntExtra("groupOwnerId", 0);
    }

    protected void a(Bundle bundle) {
        this.s = j();
    }

    protected IMChatMsgSource j() {
        Object stringExtra = getIntent().getStringExtra("source");
        if (!TextUtils.isEmpty(stringExtra)) {
            IMChatMsgSource iMChatMsgSource = new IMChatMsgSource();
            try {
                iMChatMsgSource.parseFromJSONObject(new JSONObject(stringExtra));
                return iMChatMsgSource;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public LayoutInflater getInflater() {
        if (this.U == null) {
            this.U = (LayoutInflater) getSystemService("layout_inflater");
        }
        return this.U;
    }

    public void onMsgStateChange(long j, int i) {
        if (j > 0) {
            this.c.a(j, i).a(50);
        }
    }

    protected boolean a(String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Object a;
        if (str.startsWith("//voice_max_ratio")) {
            a = a(str, 1, Config.TRACE_TODAY_VISIT_SPLIT);
            if (TextUtils.isEmpty(a)) {
                z = false;
            } else {
                try {
                    this.t = Float.valueOf(a).floatValue();
                    this.t = Math.max(1.2f, this.t);
                } catch (Exception e) {
                    z = false;
                }
            }
            return z;
        } else if (str.startsWith("//voice_ratio")) {
            a = a(str, 1, Config.TRACE_TODAY_VISIT_SPLIT);
            if (TextUtils.isEmpty(a)) {
                return false;
            }
            try {
                this.u = Float.valueOf(a).floatValue();
                this.u = Math.max(0.0f, this.u);
                return true;
            } catch (Exception e2) {
                return false;
            }
        } else if (!str.startsWith("//voice_animation_frequence")) {
            return false;
        } else {
            a = a(str, 1, Config.TRACE_TODAY_VISIT_SPLIT);
            if (TextUtils.isEmpty(a)) {
                return false;
            }
            try {
                this.v = Long.valueOf(a).longValue();
                this.v = Math.max(16, this.v);
                return true;
            } catch (Exception e3) {
                return false;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.D = (SensorManager) getSystemService(g.aa);
        this.C = this.D.getDefaultSensor(8);
        this.E = (AudioManager) getSystemService("audio");
        this.E.setMicrophoneMute(false);
        this.Q = (PowerManager) getSystemService("power");
        this.R = this.Q.newWakeLock(32, "QsbkChat");
        this.R.setReferenceCounted(false);
        this.T = (Vibrator) getSystemService("vibrator");
    }

    protected void onResume() {
        super.onResume();
        this.D.registerListener(this, this.C, 3);
        k().addUnreadCountListener(this);
        c(k().getUnreadCount());
        registerReceiver(this.F, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        if (this.x != null) {
            this.x.onResume();
        }
    }

    protected MessageCountManager k() {
        if (this.B == null && QsbkApp.currentUser != null) {
            this.B = MessageCountManager.getMessageCountManager(QsbkApp.currentUser.userId);
        }
        return this.B;
    }

    protected void onPause() {
        super.onPause();
        a(this.R);
        this.D.unregisterListener(this);
        k().removeUnreadCountListener(this);
        Log.e(IMChatBaseActivity.class.getSimpleName(), this.S + " : " + v());
        if ((this.g != null && this.g.isVoicePlaying()) || v()) {
            this.g.stopVoice();
        }
        unregisterReceiver(this.F);
    }

    protected void u() {
        if (this.T != null) {
            this.T.vibrate(b, -1);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        Logger instance = Logger.getInstance();
        String simpleName = IMChatBaseActivity.class.getSimpleName();
        String str = "onSensorChanged";
        String str2 = "event value: %s, sensor.getMaximumRange(): %s, customMaxValue: %s, voice playing: %s, audioManager.isWiredHeadsetOn():%s ";
        Object[] objArr = new Object[5];
        objArr[0] = Float.valueOf(sensorEvent.values[0]);
        objArr[1] = Float.valueOf(this.C.getMaximumRange());
        objArr[2] = Float.valueOf(a);
        objArr[3] = this.g != null ? Boolean.valueOf(this.g.isVoicePlaying()) : "adapter null.";
        objArr[4] = Boolean.valueOf(this.E.isWiredHeadsetOn());
        instance.debug(simpleName, str, String.format(str2, objArr));
        float f = sensorEvent.values[0];
        if (a == Float.MIN_VALUE) {
            a = f;
        }
        if (a < f) {
            a = f;
        }
        if (this.g != null && this.g.isVoicePlaying()) {
            if (f != a || this.E.isWiredHeadsetOn()) {
                b(2);
            } else {
                b(1);
            }
        }
    }

    @SuppressLint({"NewApi"})
    protected void b(int i) {
        if (this.S != i) {
            this.S = i;
            if (i == 1) {
                this.E.setSpeakerphoneOn(true);
                setVolumeControlStream(Integer.MIN_VALUE);
                this.E.setMode(0);
                if (!this.R.isHeld()) {
                    this.R.acquire();
                    return;
                }
                return;
            }
            this.E.setSpeakerphoneOn(false);
            setVolumeControlStream(0);
            this.E.setMode(2);
            this.R.acquire();
            if (this.R.isHeld()) {
                a(this.R);
            }
        }
    }

    protected boolean v() {
        return this.S == 1;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    void c(int i) {
        this.y.post(new fk(this, i));
    }

    public void unread(int i, int i2) {
        c(i);
    }

    @SuppressLint({"NewApi"})
    protected void h() {
        boolean z = false;
        if (VERSION.SDK_INT >= 19) {
            int i;
            if (SystemBarTintManager.sPostLollipop) {
                b(false);
            } else {
                b(true);
            }
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarDarkMode(UIHelper.isNightTheme(), this);
            try {
                TypedArray obtainStyledAttributes = obtainStyledAttributes(new int[]{16843470});
                TypedValue typedValue = new TypedValue();
                if (obtainStyledAttributes.length() > 0) {
                    obtainStyledAttributes.getValue(0, typedValue);
                    obtainStyledAttributes = obtainStyledAttributes(typedValue.data, new int[]{16842964});
                    z = obtainStyledAttributes.getColor(0, 0);
                }
                obtainStyledAttributes.recycle();
                i = z;
            } catch (Exception e) {
                i = 0;
            }
            if (i == 0) {
                getResources().getColor(UIHelper.isNightTheme() ? R.color.actionbar_dark : R.color.actionbar_day);
            }
            systemBarTintManager.setStatusBarTintColor(i);
        }
    }
}
