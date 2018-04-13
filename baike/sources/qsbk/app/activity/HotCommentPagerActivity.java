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
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.dialog.UserInfoDialog;
import qsbk.app.fragments.HotCommentCircleFragment;
import qsbk.app.fragments.HotCommentQiuShiFragment;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.LoadingLayout;
import qsbk.app.widget.PagerSlidingTabStrip;

public class HotCommentPagerActivity extends BaseActionBarActivity {
    boolean a;
    Handler b;
    String[] c = new String[]{"糗事", "糗友圈"};
    PagerSlidingTabStrip d;
    ViewPager e;
    LoadingLayout f;
    private UserInfo g;
    private UserInfoDialog h;
    private Window i;

    class a implements Runnable {
        final /* synthetic */ HotCommentPagerActivity a;

        a(HotCommentPagerActivity hotCommentPagerActivity) {
            this.a = hotCommentPagerActivity;
        }

        public void run() {
            if (this.a.h != null) {
                this.a.h.dismiss();
            }
        }
    }

    class b extends FragmentPagerAdapter {
        List<Fragment> a;
        final /* synthetic */ HotCommentPagerActivity b;

        public b(HotCommentPagerActivity hotCommentPagerActivity, List<Fragment> list, FragmentManager fragmentManager) {
            this.b = hotCommentPagerActivity;
            super(fragmentManager);
            this.a = list;
        }

        public Fragment getItem(int i) {
            return (Fragment) this.a.get(i);
        }

        public int getCount() {
            return this.a.size();
        }

        public CharSequence getPageTitle(int i) {
            return this.b.c[i];
        }
    }

    public static void launch(Context context, UserInfo userInfo) {
        Intent intent = new Intent(context, HotCommentPagerActivity.class);
        intent.putExtra("user", userInfo);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, String str) {
        UserInfo userInfo = new UserInfo();
        userInfo.userId = str;
        launch(context, userInfo);
    }

    protected CharSequence getCustomTitle() {
        return this.a ? "我的热评" : "ta的热评";
    }

    protected int a() {
        return R.layout.activity_hot_comment_pager;
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
        this.h = new UserInfoDialog(this, R.style.user_info_dialog);
        this.i = this.h.getWindow();
        LayoutParams attributes = this.i.getAttributes();
        this.i.setGravity(53);
        int height = getSupportActionBar().getHeight();
        attributes.x = UIHelper.dip2px(this, 10.0f);
        attributes.y = height + UIHelper.dip2px(this, 4.0f);
        this.i.setAttributes(attributes);
        this.h.setCanceledOnTouchOutside(true);
        this.h.show();
        this.b.postDelayed(new a(this), 4000);
        TextView textView = (TextView) this.h.findViewById(R.id.user_info_textView);
        textView.setText("若评论的帖子已被删除，则看不到评论内容");
        textView.setOnClickListener(new oj(this));
    }

    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.g = (UserInfo) intent.getSerializableExtra("user");
            boolean z = QsbkApp.currentUser != null && TextUtils.equals(QsbkApp.currentUser.userId, this.g.userId);
            this.a = z;
        }
        if (this.g == null) {
            finish();
        } else {
            super.onCreate(bundle);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.b = new Handler();
        this.f = (LoadingLayout) findViewById(R.id.loading_layout);
        this.d = (PagerSlidingTabStrip) findViewById(R.id.tab);
        this.d.setTextSize(UIHelper.dip2px(this, 12.0f));
        this.d.setIndicatorHeight(0);
        this.d.setShouldExpand(true);
        this.d.setSelectedTabTextColor(UIHelper.isNightTheme() ? -4359412 : -17664);
        this.e = (ViewPager) findViewById(R.id.viewpager);
        f();
        this.e.getViewTreeObserver().addOnPreDrawListener(new ok(this));
    }

    protected void onDestroy() {
        super.onDestroy();
        this.b.removeCallbacksAndMessages(null);
    }

    private void f() {
        this.f.hide();
        List arrayList = new ArrayList();
        arrayList.add(HotCommentQiuShiFragment.newInstance(this.g));
        arrayList.add(HotCommentCircleFragment.newInstance(this.g));
        this.e.setAdapter(new b(this, arrayList, getSupportFragmentManager()));
        this.d.setViewPager(this.e, new ol(this));
    }
}
