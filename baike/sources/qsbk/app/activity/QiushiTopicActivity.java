package qsbk.app.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.fragments.QiushiTopicFragment;
import qsbk.app.http.HttpTask;
import qsbk.app.model.QiushiTopic;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.QiushiTopicBus.QiushiTopicUpdateListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PagerSlidingTabStrip;
import qsbk.app.widget.QiushiTopicHeader;
import qsbk.app.widget.QiushiTopicNavLayout;
import qsbk.app.widget.QiushiTopicNavLayout.OnScrollHeaderListener;

public class QiushiTopicActivity extends FragmentActivity implements QiushiTopicUpdateListener, OnScrollHeaderListener {
    public static final String TAG = QiushiTopicActivity.class.getSimpleName();
    private QiushiTopic a;
    private int b;
    private QiushiTopicPagerAdapter c;
    private QiushiTopicHeader d;
    private View e;
    private View f;
    private TextView g;
    private View h;
    private ViewPager i;
    private PagerSlidingTabStrip j;
    private ArrayList<Fragment> k = new ArrayList();
    private ArrayList<String> l = new ArrayList();
    private QiushiTopicNavLayout m;
    private boolean n;
    private HttpTask o;

    public static final class QiushiTopicPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<String> a;
        private ArrayList<Fragment> b;

        public QiushiTopicPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList, ArrayList<String> arrayList2) {
            super(fragmentManager);
            this.b = arrayList;
            this.a = arrayList2;
        }

        public CharSequence getPageTitle(int i) {
            return (CharSequence) this.a.get(i);
        }

        public Fragment getItem(int i) {
            return (Fragment) this.b.get(i);
        }

        public int getCount() {
            return this.a.size();
        }
    }

    public static void Launch(Context context, QiushiTopic qiushiTopic) {
        Intent intent = new Intent();
        intent.setClass(context, QiushiTopicActivity.class);
        intent.putExtra("topic", qiushiTopic);
        context.startActivity(intent);
    }

    public void scrollHeader() {
        if (this.f != null) {
            this.f.setBackgroundColor(-17899);
            if (this.a != null) {
                this.g.setText(this.a.content);
            }
        }
    }

    public void scrollHeaderChange() {
        if (this.f != null) {
            this.f.setBackgroundColor(ViewCompat.MEASURED_SIZE_MASK);
            this.g.setText(null);
        }
    }

    protected void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night.Transparent);
        } else {
            setTheme(R.style.Day.Transparent);
        }
    }

    protected void b() {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else if (VERSION.SDK_INT >= 19) {
            a(true);
        }
    }

    protected void c() {
        if (VERSION.SDK_INT >= 19) {
            int dimensionPixelSize;
            try {
                dimensionPixelSize = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
            } catch (Exception e) {
                dimensionPixelSize = 23;
            }
            this.f.setPadding(0, dimensionPixelSize, 0, 0);
        }
    }

    @TargetApi(19)
    protected void a(boolean z) {
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    private void d() {
        this.a = (QiushiTopic) getIntent().getSerializableExtra("topic");
        if (this.a == null) {
            finish();
        } else {
            this.b = this.a.id;
        }
    }

    public void initWidgets() {
        this.m = (QiushiTopicNavLayout) findViewById(R.id.qiushi_topicNavLayout);
        this.m.setOnScrollHeaderListener(this);
        this.d = (QiushiTopicHeader) findViewById(R.id.header);
        this.i = (ViewPager) findViewById(R.id.id_qiushi_topic_viewpager);
        this.j = (PagerSlidingTabStrip) findViewById(R.id.id_qiushi_topic_indicator);
        this.k.add(QiushiTopicFragment.newInstance(0, this.a));
        this.l.add("默认");
        this.k.add(QiushiTopicFragment.newInstance(1, this.a));
        this.l.add("最新");
        this.c = new QiushiTopicPagerAdapter(getSupportFragmentManager(), this.k, this.l);
        this.i.setAdapter(this.c);
        this.i.setOffscreenPageLimit(1);
        this.j.setViewPager(this.i, new aaf(this));
        this.e = findViewById(R.id.topic_bar);
        this.e.setVisibility(0);
        this.e.setOnClickListener(new LoginPermissionClickDelegate(new aag(this), null));
        this.f = findViewById(R.id.custom_action_bar);
        this.g = (TextView) findViewById(R.id.back);
        this.g.setOnClickListener(new aah(this));
        this.h = findViewById(R.id.actionbar_share);
        this.h.setOnClickListener(new aai(this));
        this.j.setIndicatorHeight(0);
        this.j.setSelectedTabTextColor(-17664);
        update();
        this.d.getTopicSub().setOnClickListener(new aaj(this));
    }

    public void update() {
        if (this.a != null) {
            this.d.setQiushiTopic(this.a);
            if (this.e != null) {
                this.e.setVisibility(this.a.isSystem() ? 8 : 0);
            }
            if (this.a.isSystem()) {
                this.m.setExtraHeight(UIHelper.dip2px(QsbkApp.getInstance(), 48.0f));
            }
            this.m.refreshTopViewHeight();
        }
    }

    public void onCreate(Bundle bundle) {
        a();
        requestWindowFeature(1);
        b();
        setContentView(R.layout.activity_qiushi_topic);
        super.onCreate(bundle);
        e();
        d();
        initWidgets();
        c();
        this.m.setmActionBarHeight(UIHelper.dip2px(this, 48.0f) + this.f.getPaddingTop());
    }

    private void e() {
        QiushiTopicBus.register(this);
    }

    protected void onResume() {
        this.n = true;
        super.onResume();
    }

    protected void onDestroy() {
        this.n = false;
        QiushiTopicBus.unregister(this);
        super.onDestroy();
    }

    public void finish() {
        super.finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && intent != null) {
            ShareUtils.doShare(i2, this, this.a);
        }
    }

    public void onQiushiTopicUpdate(QiushiTopic qiushiTopic, Object obj) {
        if (this.a.equals(qiushiTopic)) {
            if (obj != TAG) {
                this.a = qiushiTopic;
            }
            update();
        }
    }
}
