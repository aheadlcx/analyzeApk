package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.fragments.NearbyUsersFragment;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.utils.UIHelper;

public class NearByActivity extends BaseActionBarActivity {
    private static final String a = NearByActivity.class.getSimpleName();
    private Fragment b;

    public interface OnOptionMenuSelectedCallBack {
        void onClearLocation();

        void onSelectUser();
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, NearByActivity.class));
    }

    private static void a(Activity activity) {
        activity.startActivity(new Intent(activity, RandomDoorActivity.class));
    }

    protected String f() {
        return "附近的糗友";
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
            new LocationHelper((Context) this).startLocate(null);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            this.b = NearbyUsersFragment.newInstance();
            beginTransaction.replace(R.id.container, this.b);
            beginTransaction.commit();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_clear_location).setIcon(UIHelper.isNightTheme() ? R.drawable.bottombar_cleanlocal_night : R.drawable.bottombar_cleanlocal);
        menu.findItem(R.id.action_select).setIcon(UIHelper.isNightTheme() ? R.drawable.bottombar_shaixuan_night : R.drawable.bottombar_shaixuan);
        menu.findItem(R.id.action_randomdoor).setIcon(UIHelper.isNightTheme() ? R.drawable.found_random_door_night : R.drawable.found_random_door);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nearby, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_randomdoor:
                a((Activity) this);
                return true;
            case R.id.action_select:
                if (this.b != null && (this.b instanceof OnOptionMenuSelectedCallBack)) {
                    ((OnOptionMenuSelectedCallBack) this.b).onSelectUser();
                }
                return true;
            case R.id.action_clear_location:
                if (this.b != null && (this.b instanceof OnOptionMenuSelectedCallBack)) {
                    ((OnOptionMenuSelectedCallBack) this.b).onClearLocation();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
