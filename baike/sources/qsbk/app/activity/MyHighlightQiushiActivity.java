package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.dialog.UserInfoDialog;
import qsbk.app.fragments.IArticleList;
import qsbk.app.fragments.IPageableFragment;
import qsbk.app.fragments.MyHighLightQiushiFragment;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;

public class MyHighlightQiushiActivity extends BaseActionBarActivity {
    private static final String a = MyHighlightQiushiActivity.class.getSimpleName();
    private ArrayList<Fragment> b = new ArrayList();
    private ViewPager c;
    private QiushiPagerAdapter d;
    private int e;
    private UserInfo f;
    private UserInfoDialog g;
    private Window h;
    private Handler i = new Handler();

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

    class a implements Runnable {
        final /* synthetic */ MyHighlightQiushiActivity a;

        a(MyHighlightQiushiActivity myHighlightQiushiActivity) {
            this.a = myHighlightQiushiActivity;
        }

        public void run() {
            if (this.a.g != null) {
                this.a.g.dismiss();
            }
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        if (QsbkApp.currentUser != null) {
            launch(context, QsbkApp.currentUser);
        }
    }

    public static void launch(Context context, UserInfo userInfo) {
        Intent intent = new Intent(context, MyHighlightQiushiActivity.class);
        intent.putExtra("user", userInfo);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, String str) {
        Intent intent = new Intent(context, MyHighlightQiushiActivity.class);
        Serializable userInfo = new UserInfo();
        userInfo.userId = str;
        intent.putExtra("user", userInfo);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hot_comment_pager, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.user_remind:
                showUserRemindDialog();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void showUserRemindDialog() {
        this.g = new UserInfoDialog(this, R.style.user_info_dialog);
        this.h = this.g.getWindow();
        LayoutParams attributes = this.h.getAttributes();
        this.h.setGravity(53);
        int height = getSupportActionBar().getHeight();
        attributes.x = UIHelper.dip2px(this, 10.0f);
        attributes.y = height + UIHelper.dip2px(this, 4.0f);
        this.h.setAttributes(attributes);
        this.g.setCanceledOnTouchOutside(true);
        this.g.show();
        this.i.postDelayed(new a(this), 4000);
        TextView textView = (TextView) this.g.findViewById(R.id.user_info_textView);
        textView.setText("若精选帖子被删除，则帖子在当前列表不可见");
        textView.setOnClickListener(new uk(this));
    }

    protected String f() {
        if (QsbkApp.currentUser == null || this.f == null || !TextUtils.equals(QsbkApp.currentUser.userId, this.f.userId)) {
            return "ta的糗事精选";
        }
        return "我的糗事精选";
    }

    protected int a() {
        return R.layout.activity_my_highlight_activity;
    }

    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.f = (UserInfo) intent.getSerializableExtra("user");
        }
        if (this.f == null) {
            finish();
        } else {
            super.onCreate(bundle);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        g();
        i();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.i.removeCallbacksAndMessages(null);
    }

    private void g() {
        this.b.add(MyHighLightQiushiFragment.newInstance(this.f));
    }

    protected void onStop() {
        super.onStop();
        Fragment fragment = (Fragment) this.b.get(this.e);
        if (fragment instanceof IPageableFragment) {
            ((IPageableFragment) fragment).doStop();
        }
    }

    protected void onResume() {
        super.onResume();
        Fragment fragment = (Fragment) this.b.get(this.e);
        if (fragment instanceof IPageableFragment) {
            ((IPageableFragment) fragment).doResume();
        }
    }

    private void i() {
        this.c = (ViewPager) findViewById(R.id.pager);
        this.d = new QiushiPagerAdapter(getSupportFragmentManager(), this.b);
        this.c.setAdapter(this.d);
        this.c.setOffscreenPageLimit(2);
        this.e = 0;
        this.c.setCurrentItem(this.e);
        this.c.getViewTreeObserver().addOnPreDrawListener(new ul(this));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.c != null && (this.d.getItem(this.c.getCurrentItem()) instanceof IArticleList)) {
            z = ((IArticleList) this.d.getItem(this.c.getCurrentItem())).onKeyDown(i, keyEvent);
        }
        if (z) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
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
                    ((Fragment) this.d.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 100:
                    ((Fragment) this.d.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 101:
                    ((Fragment) this.d.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 102:
                    ((Fragment) this.d.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 9999:
                    ((Fragment) this.d.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                case 10000:
                    ((Fragment) this.d.b.get(0)).onActivityResult(i, i2, intent);
                    return;
                default:
                    return;
            }
        }
    }
}
