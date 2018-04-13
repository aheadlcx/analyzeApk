package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.dialog.UserInfoDialog;
import qsbk.app.activity.publish.PublishActivity;
import qsbk.app.fragments.IArticleList;
import qsbk.app.fragments.IPageableFragment;
import qsbk.app.fragments.ManangeMyContentsFragment;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;

public class ManageQiuShiNewActivity extends BaseActionBarActivity {
    private static final String d = ManageQiuShiNewActivity.class.getSimpleName();
    boolean a;
    UserInfoDialog b;
    Window c;
    private ArrayList<Fragment> e = new ArrayList();
    private ViewPager f;
    private QiushiPagerAdapter g;
    private int h;

    public static final class QiushiPagerAdapter extends FragmentPagerAdapter {
        private static final String[] a = new String[]{"我的糗事"};
        private ArrayList<Fragment> b = new ArrayList();

        public QiushiPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
            super(fragmentManager);
            this.b = arrayList;
        }

        public CharSequence getPageTitle(int i) {
            return a[i];
        }

        public Fragment getItem(int i) {
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return a.length;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "管理我的糗事";
    }

    protected int a() {
        return R.layout.activity_manage_qiushi_new;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        g();
        i();
        this.a = SharePreferenceUtils.getSharePreferencesBoolValue("user_remind_first_in");
    }

    private void g() {
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("isFromPublishQiushi", false) && SharePreferenceUtils.getSharePreferencesBoolValue(PublishActivity.FIRST_PUBLISH, true)) {
            new Builder(this).setMessage("发表的糗事将经过违禁内容和相同内容过滤，通过后再由糗友进行好笑度评审～\n 审核都通过后，糗事将立即发布！").setPositiveButton("我知道了", null).create().show();
            SharePreferenceUtils.setSharePreferencesValue(PublishActivity.FIRST_PUBLISH, false);
        }
        this.e.add(new ManangeMyContentsFragment());
    }

    protected void onStop() {
        super.onStop();
        Fragment fragment = (Fragment) this.e.get(this.h);
        if (fragment instanceof IPageableFragment) {
            ((IPageableFragment) fragment).doStop();
        }
    }

    protected void onResume() {
        super.onResume();
        Fragment fragment = (Fragment) this.e.get(this.h);
        if (fragment instanceof IPageableFragment) {
            ((IPageableFragment) fragment).doResume();
        }
    }

    private void i() {
        this.f = (ViewPager) findViewById(R.id.pager);
        this.g = new QiushiPagerAdapter(getSupportFragmentManager(), this.e);
        this.f.setAdapter(this.g);
        this.f.setOffscreenPageLimit(2);
        this.h = 0;
        this.f.setCurrentItem(this.h);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.f != null && (this.g.getItem(this.f.getCurrentItem()) instanceof IArticleList)) {
            z = ((IArticleList) this.g.getItem(this.f.getCurrentItem())).onKeyDown(i, keyEvent);
        }
        if (z) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (QsbkApp.getInstance().isMeizuVersion()) {
            LogUtil.d("系统是魅族系统，重新定义，未完待续!");
        } else {
            getMenuInflater().inflate(R.menu.manage_qiushi_user_remind, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                LogUtil.d("on home click");
                finish();
                break;
            case R.id.qiushi_notification:
                QiushiNotificationListActivity.gotoQiushi(this, 0);
                break;
            case R.id.user_remind:
                showUserRemindDialog();
                break;
        }
        return false;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (QsbkApp.getInstance().isMeizuVersion()) {
            LogUtil.d("系统是魅族系统，重新定义，未完待续!");
        } else {
            menu.findItem(R.id.user_remind).setIcon(UIHelper.getUserRemind());
            if (!this.a) {
                showUserRemindDialog();
                this.a = true;
                SharePreferenceUtils.setSharePreferencesValue("user_remind_first_in", true);
            }
            MenuItem findItem = menu.findItem(R.id.qiushi_notification);
            if (findItem != null) {
                findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.ic_bell_night : R.drawable.ic_bell);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void showUserRemindDialog() {
        this.b = new UserInfoDialog(this, R.style.user_info_dialog);
        this.c = this.b.getWindow();
        LayoutParams attributes = this.c.getAttributes();
        this.c.setGravity(53);
        float density = getDensity();
        int height = getSupportActionBar().getHeight();
        attributes.x = (int) (10.0f * density);
        attributes.y = (int) ((density * 4.0f) + ((float) height));
        this.c.setAttributes(attributes);
        this.b.setCanceledOnTouchOutside(true);
        this.b.show();
        ((TextView) this.b.findViewById(R.id.user_info_textView)).setOnClickListener(new tb(this));
    }

    public float getDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            switch (i2) {
                case 0:
                    LogUtil.d("申诉失败");
                    return;
                case 1:
                    ((Fragment) this.g.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 100:
                    ((Fragment) this.g.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 101:
                    ((Fragment) this.g.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 102:
                    ((Fragment) this.g.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 9999:
                    ((Fragment) this.g.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 10000:
                    ((Fragment) this.g.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                default:
                    return;
            }
        }
    }
}
