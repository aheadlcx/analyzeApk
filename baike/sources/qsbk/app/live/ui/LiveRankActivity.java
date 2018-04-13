package qsbk.app.live.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.r0adkll.slidr.Slidr;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;
import qsbk.app.live.model.GiftRankData;
import qsbk.app.live.ui.fragment.GiftRankFragment;

public class LiveRankActivity extends BaseActivity implements OnClickListener {
    private LinearLayout a;
    private LinearLayout b;
    private ViewPager c;
    private GiftRankFragment d;
    private GiftRankFragment e;
    private GiftRankData f;
    public ImageView iv_up;

    private class a extends FragmentStatePagerAdapter implements OnPageChangeListener {
        final /* synthetic */ LiveRankActivity a;

        public a(LiveRankActivity liveRankActivity, FragmentManager fragmentManager) {
            this.a = liveRankActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    if (this.a.d == null) {
                        this.a.d = (GiftRankFragment) GiftRankFragment.newInstance(1, null, false, 2, null);
                    }
                    return this.a.d;
                case 1:
                    if (this.a.e == null) {
                        this.a.e = (GiftRankFragment) GiftRankFragment.newInstance(2, null, false, 2, this.a.f);
                    }
                    return this.a.e;
                default:
                    return null;
            }
        }

        public int getCount() {
            return 2;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            this.a.a(i);
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected int getLayoutId() {
        return R.layout.live_rank_activity;
    }

    protected void initView() {
        this.iv_up = (ImageView) findViewById(R.id.iv_up);
        this.iv_up.setOnClickListener(new fe(this));
        this.a = (LinearLayout) $(R.id.ll_tab_gift_send);
        this.b = (LinearLayout) $(R.id.ll_tab_gift_receive);
        Object aVar = new a(this, getSupportFragmentManager());
        this.c = (ViewPager) findViewById(R.id.pager);
        this.c.setAdapter(aVar);
        this.c.setOffscreenPageLimit(2);
        this.c.addOnPageChangeListener(aVar);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    protected void initData() {
        int i = 0;
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra(ParamKey.PAGE, 0);
            this.f = (GiftRankData) intent.getSerializableExtra("anchor");
            i = intExtra;
        }
        this.c.setCurrentItem(i);
        a(i);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ll_tab_gift_send) {
            this.c.setCurrentItem(0);
        } else if (view.getId() == R.id.ll_tab_gift_receive) {
            this.c.setCurrentItem(1);
        }
    }

    private void a(int i) {
        this.a.setSelected(false);
        this.b.setSelected(false);
        switch (i) {
            case 0:
                this.a.setSelected(true);
                return;
            case 1:
                this.b.setSelected(true);
                return;
            default:
                return;
        }
    }
}
