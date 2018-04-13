package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.ConfigManager;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.live.ui.NetworkDiagnosisActivity;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.VideoLoadConfig;
import qsbk.app.widget.NotificationSettingItem;
import qsbk.app.widget.SettingItem;

public class CommonSettingActivity extends BaseActionBarActivity {
    private static final float[] a = new float[]{16.0f, 18.0f, 20.0f, 22.0f, 24.0f};
    private static final String[] b = new String[]{"小号", "默认", "大号", "超大号", "巨大号"};
    private SettingItem c;
    private SettingItem d;
    private SettingItem e;
    private NotificationSettingItem f;
    private List<File> g = new ArrayList();

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected int a() {
        return R.layout.activity_common_setting;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Setting_Night);
        } else {
            setTheme(R.style.Setting);
        }
    }

    protected String e() {
        return "常规";
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        initWidget();
        initListener();
    }

    public void initWidget() {
        this.c = (SettingItem) findViewById(R.id.current_text_size);
        this.d = (SettingItem) findViewById(R.id.video_loadway_settingItem);
        this.e = (SettingItem) findViewById(R.id.image_loadway_settingItem);
        this.f = (NotificationSettingItem) findViewById(R.id.httpsEnable);
        this.f.setChecked(QsbkApp.isHttpsEnable());
        if (!ConfigManager.getInstance().isInTestMode()) {
            this.f.setVisibility(8);
        }
    }

    public void initListener() {
        this.c.setSubTitle(i());
        this.c.setOnClickListener(new jh(this));
        this.d.setSubTitle(VideoLoadConfig.getName());
        this.d.setOnClickListener(new ji(this));
        this.e.setSubTitle(a(SharePreferenceUtils.getSharePreferencesValue("imageLoadWay")));
        this.e.setOnClickListener(new jk(this));
        this.f.setOnCheckedChangeListener(new jl(this));
    }

    private String i() {
        return b[j()];
    }

    private int j() {
        int length = a.length;
        float currentContentTextSize = QsbkApp.getInstance().getCurrentContentTextSize();
        int i = 0;
        while (i < length) {
            if (currentContentTextSize == a[i]) {
                break;
            }
            i++;
        }
        i = 0;
        if (i >= b.length) {
            return b.length - 1;
        }
        return i;
    }

    private Builder k() {
        return new Builder(this).setTitle("请选择字体大小").setSingleChoiceItems(b, j(), new jn(this)).setNegativeButton("取消", new jm(this));
    }

    private Builder l() {
        int i;
        if (SharePreferenceUtils.getSharePreferencesValue("imageLoadWay").equals("wifi")) {
            i = 1;
        } else {
            i = 0;
        }
        return new Builder(this).setTitle("请选择图片加载方式").setSingleChoiceItems(new String[]{"总是自动加载", "仅在WIFI环境中自动加载"}, i, new jp(this)).setNegativeButton("取消", new jo(this));
    }

    private String a(String str) {
        String str2 = "";
        if ("auto".equals(str) || "".equals(str)) {
            str2 = "自动";
        }
        if ("wifi".equals(str)) {
            str2 = NetworkDiagnosisActivity.NETWORKTYPE_WIFI;
        }
        if (BaseImageAdapter.IMAGELOADWAY_TEXTONLY.equals(str)) {
            return "点击";
        }
        return str2;
    }
}
