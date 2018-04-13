package qsbk.app.activity;

import android.os.Bundle;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SettingItem;

public class PersonalPrivacySettingActivity extends BaseActionBarActivity {
    private SettingItem a;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected int a() {
        return R.layout.activity_personal_privacy_setting;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Setting_Night);
        } else {
            setTheme(R.style.Setting);
        }
    }

    protected String f() {
        return "隐私";
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        initWidget();
        initListener();
    }

    public void initWidget() {
        this.a = (SettingItem) findViewById(R.id.black_list_settingitem);
    }

    public void initListener() {
        this.a.setOnClickListener(new zw(this));
    }
}
