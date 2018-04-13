package qsbk.app.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.CheckBox;
import android.widget.SeekBar;
import com.alipay.sdk.sys.a;
import qsbk.app.QsbkApp;
import qsbk.app.R;

@Deprecated
public class BrightnessSetting {
    private final Activity a;
    private SeekBar b;
    private CheckBox c;
    private View d;
    private int e;

    public BrightnessSetting(Activity activity) {
        Object sharePreferencesValue;
        this.a = activity;
        String str = "";
        if (UIHelper.isNightTheme()) {
            sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("brightness_night");
        } else {
            sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("brightness");
        }
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            this.e = QsbkApp.brightness;
        } else {
            this.e = Integer.valueOf(sharePreferencesValue).intValue();
        }
    }

    public static void CacheSystemScreenBrightness(Context context) {
        initOrgBrightness(context);
    }

    public static void setBrightness(Activity activity) {
        String str = "";
        if (UIHelper.isNightTheme()) {
            str = SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem_night");
        } else {
            str = SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem");
        }
        if (DebugUtil.DEBUG) {
            Log.d("BrightnessSetting", "是否跟随系统:" + str);
        }
        if (str.equals("") || str.equals("true")) {
            System.putInt(activity.getContentResolver(), "screen_brightness_mode", QsbkApp.screenMode.intValue());
            LayoutParams attributes = activity.getWindow().getAttributes();
            if (DebugUtil.DEBUG) {
                Log.d("BrightnessSetting", "系统亮度：" + QsbkApp.brightness);
            }
            if (QsbkApp.brightness >= 0 && QsbkApp.brightness <= 255) {
                attributes.screenBrightness = ((float) QsbkApp.brightness) / 255.0f;
            }
            activity.getWindow().setAttributes(attributes);
            return;
        }
        Object sharePreferencesValue;
        int i;
        str = "";
        if (UIHelper.isNightTheme()) {
            sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("brightness_night");
        } else {
            sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("brightness");
        }
        if (DebugUtil.DEBUG) {
            Log.d("BrightnessSetting", "App缓存亮度：" + sharePreferencesValue);
        }
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            try {
                i = System.getInt(activity.getContentResolver(), "screen_brightness");
            } catch (SettingNotFoundException e) {
                i = 50;
            }
        } else {
            i = Integer.valueOf(sharePreferencesValue).intValue();
        }
        if (DebugUtil.DEBUG) {
            Log.d("BrightnessSetting", "当前APP要显示的屏幕亮度：" + i + "");
        }
        try {
            if (System.getInt(activity.getContentResolver(), "screen_brightness_mode") == 1) {
                System.putInt(activity.getContentResolver(), "screen_brightness_mode", 0);
            }
            if (i < 15) {
                i = 15;
            }
            LayoutParams attributes2 = activity.getWindow().getAttributes();
            if (i >= 0 && i <= 255) {
                attributes2.screenBrightness = ((float) i) / 255.0f;
            }
            activity.getWindow().setAttributes(attributes2);
        } catch (SettingNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public static void initOrgBrightness(Context context) {
        try {
            QsbkApp.screenMode = Integer.valueOf(System.getInt(context.getContentResolver(), "screen_brightness_mode"));
            if (Build.MODEL.startsWith("M0")) {
                QsbkApp.brightness = System.getInt(context.getContentResolver(), "screen_brightness");
            }
            if (QsbkApp.screenMode.intValue() != 1) {
                QsbkApp.brightness = System.getInt(context.getContentResolver(), "screen_brightness");
            }
        } catch (SettingNotFoundException e) {
            QsbkApp.screenMode = Integer.valueOf(1);
        }
    }

    public final Builder createDialog() {
        Builder title = new Builder(this.a).setTitle("亮度");
        title.setPositiveButton("确定", new b(this)).setNegativeButton("取消", new a(this));
        if (this.d != null && this.d.getParent() == null) {
            return title.setView(this.d);
        }
        this.d = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(R.layout.brightness_dialog, null);
        this.b = (SeekBar) this.d.findViewById(R.id.brightness_dialog_seek_bar);
        this.b.setOnSeekBarChangeListener(new BrightnessSetting$b(this));
        this.c = (CheckBox) this.d.findViewById(R.id.brightness_dialog_auto);
        String str = "";
        if (UIHelper.isNightTheme()) {
            str = SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem_night");
        } else {
            str = SharePreferenceUtils.getSharePreferencesValue("isFlollowSystem");
        }
        if (str.equals("") || str.equals("true")) {
            this.c.setChecked(true);
            this.b.setEnabled(false);
        } else {
            a();
            this.c.setChecked(false);
            this.b.setEnabled(true);
        }
        this.c.setOnCheckedChangeListener(new BrightnessSetting$a(this));
        View view = this.d;
        this.b.setProgress(this.e);
        return title.setView(view);
    }

    public void cacheScreenBrightness(float f) {
        if (UIHelper.isNightTheme()) {
            this.a.getSharedPreferences(a.j, 0).edit().putFloat("brightness_night", f).commit();
        } else {
            this.a.getSharedPreferences(a.j, 0).edit().putFloat("brightness", f).commit();
        }
    }

    private void a() {
        System.putInt(this.a.getContentResolver(), "screen_brightness_mode", 0);
    }

    private void a(int i) {
        LayoutParams attributes = this.a.getWindow().getAttributes();
        if (i < 15) {
            i = 15;
        }
        if (i >= 0 && i <= 255) {
            attributes.screenBrightness = ((float) i) / 255.0f;
        }
        this.a.getWindow().setAttributes(attributes);
    }
}
