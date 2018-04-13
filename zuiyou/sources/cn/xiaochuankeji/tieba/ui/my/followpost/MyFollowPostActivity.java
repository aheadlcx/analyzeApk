package cn.xiaochuankeji.tieba.ui.my.followpost;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.g;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.b;
import cn.xiaochuankeji.tieba.ui.widget.indicator.i;
import cn.xiaochuankeji.tieba.ui.widget.indicator.o;

public class MyFollowPostActivity extends cn.xiaochuankeji.tieba.ui.base.a implements OnPageChangeListener, OnClickListener {
    private TBViewPager a;
    private MagicIndicator b;
    private o c;
    private String[] d;

    private class a extends g {
        final /* synthetic */ MyFollowPostActivity a;

        a(MyFollowPostActivity myFollowPostActivity, FragmentManager fragmentManager) {
            this.a = myFollowPostActivity;
            super(fragmentManager);
        }

        public int getCount() {
            return this.a.d.length;
        }

        public Fragment getItem(int i) {
            if (i == 0) {
                return d.b();
            }
            if (1 == i) {
                return b.b();
            }
            if (2 == i) {
                return a.b();
            }
            return null;
        }
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, MyFollowPostActivity.class));
    }

    protected int a() {
        return R.layout.activity_follow_post;
    }

    protected void i_() {
        this.d = new String[]{getString(R.string.follow_post_subject), getString(R.string.follow_post), getString(R.string.follow_post_fav)};
        this.b = (MagicIndicator) findViewById(R.id.indicator);
        this.a = (TBViewPager) findViewById(R.id.viewpager);
        this.c = new o(this.d);
        i bVar = new b(this);
        bVar.setAdjustMode(true);
        bVar.setAdapter(this.c);
        this.b.setNavigator(bVar);
        this.a.setAdapter(new a(this, getSupportFragmentManager()));
        findViewById(R.id.back).setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        this.a.addOnPageChangeListener(this);
        if (this.c != null) {
            this.c.a(this.a);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.c != null) {
            this.c.c();
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.b != null) {
            this.b.a(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.b != null) {
            this.b.a(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.b != null) {
            this.b.b(i);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                return;
            default:
                return;
        }
    }
}
