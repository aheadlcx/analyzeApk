package qsbk.app.activity;

import android.os.Bundle;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.SettingItem;

public class SmallPaperSettingActivity extends BaseActionBarActivity {
    private SettingItem a;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Setting_Night);
        } else {
            setTheme(R.style.Setting);
        }
    }

    protected String f() {
        return "小纸条";
    }

    protected int a() {
        return R.layout.activity_small_paper_setting;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        initWidget();
        initListener();
    }

    public void initWidget() {
        this.a = (SettingItem) findViewById(R.id.group_temporary_note_settingitem);
    }

    public void initListener() {
        this.a.setOnClickListener(new acu(this));
    }
}
