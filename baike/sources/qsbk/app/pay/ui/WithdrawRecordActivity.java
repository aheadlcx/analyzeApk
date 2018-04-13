package qsbk.app.pay.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.r0adkll.slidr.Slidr;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.pay.R;

public class WithdrawRecordActivity extends BaseActivity implements OnClickListener {
    private LinearLayout a;
    private LinearLayout b;
    private ViewPager c;

    private class a extends FragmentStatePagerAdapter implements OnPageChangeListener {
        final /* synthetic */ WithdrawRecordActivity a;

        public a(WithdrawRecordActivity withdrawRecordActivity, FragmentManager fragmentManager) {
            this.a = withdrawRecordActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return WithdrawRecordFragment.newInstance(i);
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

    protected int getLayoutId() {
        return R.layout.pay_withdraw_record_activity;
    }

    protected void initView() {
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
        setUp();
        this.a = (LinearLayout) $(R.id.ll_tab_withdraw);
        this.b = (LinearLayout) $(R.id.ll_tab_exchange);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        Object aVar = new a(this, getSupportFragmentManager());
        this.c = (ViewPager) $(R.id.pager);
        this.c.setAdapter(aVar);
        this.c.addOnPageChangeListener(aVar);
    }

    protected void initData() {
        a(0);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ll_tab_withdraw) {
            this.c.setCurrentItem(0);
        } else if (view.getId() == R.id.ll_tab_exchange) {
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
