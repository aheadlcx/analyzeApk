package cn.tatagou.sdk.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.view.b;

public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ttg_single_activity);
        setStatusBar();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, createFragment()).commit();
        }
    }

    protected void setStatusBar() {
        b.onSetStatusBarColor(this);
    }

    protected void onResume() {
        if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
        }
        super.onResume();
    }
}
