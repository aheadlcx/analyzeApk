package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.fragments.MyQiuYouDynamicFragment;
import qsbk.app.utils.UIHelper;

public class MyQiuYouDynamicActivity extends BaseActionBarActivity {
    private Fragment a;
    private String b;
    private boolean c = false;
    private boolean d = false;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return null;
    }

    private void a(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowTitleEnabled(true);
        supportActionBar.setTitle(str);
    }

    protected int a() {
        return R.layout.activity_nearby;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    protected void a(Bundle bundle) {
        if (UIHelper.isNightTheme()) {
            findViewById(R.id.layerMask).setVisibility(0);
        } else {
            findViewById(R.id.layerMask).setVisibility(8);
        }
        this.b = getIntent().getStringExtra("uid");
        this.d = getIntent().getBooleanExtra("fromManage", false);
        if (QsbkApp.currentUser == null || !QsbkApp.currentUser.userId.equals(this.b)) {
            a("TA的动态");
        } else {
            a("我的动态");
            this.c = true;
        }
        setActionbarBackable();
        if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            this.a = new MyQiuYouDynamicFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putString("uid", this.b);
            bundle2.putBoolean("fromManage", this.d);
            this.a.setArguments(bundle2);
            beginTransaction.replace(R.id.container, this.a);
            beginTransaction.commit();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.c) {
            menu.findItem(R.id.add).setIcon(UIHelper.getNewSubmitMenuIcon());
            menu.findItem(R.id.message_list).setIcon(UIHelper.isNightTheme() ? R.drawable.bell_night : R.drawable.bell);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.c) {
            getMenuInflater().inflate(R.menu.my_qiuyou_dynamic, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.add:
                startActivity(new Intent(this, CirclePublishActivity.class));
                break;
            case R.id.message_list:
                QiushiNotificationListActivity.gotoQiuyouquan(this, 0);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
