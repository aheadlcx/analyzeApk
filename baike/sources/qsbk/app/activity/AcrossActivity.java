package qsbk.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.AcrossFragment;
import qsbk.app.fragments.AcrossFragment.ChangeDateListener;
import qsbk.app.utils.UIHelper;

public class AcrossActivity extends BaseActionBarActivity implements ChangeDateListener {
    private static final String a = AcrossActivity.class.getSimpleName();
    private Fragment b;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return b();
    }

    protected String b() {
        return "穿越";
    }

    public void setTitle(String str) {
        getSupportActionBar().setTitle(str);
    }

    protected int a() {
        return R.layout.activity_nearby;
    }

    protected void a(Bundle bundle) {
        if (UIHelper.isNightTheme()) {
            findViewById(R.id.layerMask).setVisibility(0);
        } else {
            findViewById(R.id.layerMask).setVisibility(8);
        }
        setActionbarBackable();
        if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            this.b = AcrossFragment.newInstance();
            beginTransaction.replace(R.id.container, this.b);
            beginTransaction.commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1, 1, "换一天"), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 1:
                if (this.b != null && (this.b instanceof AcrossFragment)) {
                    ((AcrossFragment) this.b).onOtherDay();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void changeDate(String str) {
        setTitle(str);
    }
}
