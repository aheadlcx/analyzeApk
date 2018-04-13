package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.fragments.NearByGroupFragment;
import qsbk.app.utils.UIHelper;

public class NearByGroupActivity extends BaseTabActivity {
    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        if (QsbkApp.checkLogin(context, true, true)) {
            context.startActivity(new Intent(context, NearByGroupActivity.class));
        }
    }

    protected String[] c() {
        return new String[]{"附近的群", "群天梯"};
    }

    protected Fragment[] e() {
        return new Fragment[]{new NearByGroupFragment(), GroupRankFragment.newInstance()};
    }

    protected String f() {
        return "群";
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.action_search);
        if (findItem != null) {
            findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
        }
        MenuItem findItem2 = menu.findItem(R.id.action_create);
        if (findItem2 != null) {
            findItem2.setIcon(UIHelper.getNewSubmitMenuIcon());
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_search:
                SearchGroupActivity.launch(this);
                return true;
            case R.id.action_create:
                startActivity(new Intent(this, CreateNewGroupActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
