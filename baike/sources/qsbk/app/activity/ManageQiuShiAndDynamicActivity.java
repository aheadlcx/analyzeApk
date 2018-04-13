package qsbk.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.ManageQiushiAndDynamicFragment;

public class ManageQiuShiAndDynamicActivity extends BaseActionBarActivity {
    private static final String a = ManageQiuShiAndDynamicActivity.class.getSimpleName();
    private ManageQiushiAndDynamicFragment b;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "管理糗事和动态";
    }

    protected int a() {
        return R.layout.activity_manage_qiushi_and_dynamic;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.b = new ManageQiushiAndDynamicFragment();
        beginTransaction.replace(R.id.manage, this.b);
        beginTransaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return false;
    }
}
