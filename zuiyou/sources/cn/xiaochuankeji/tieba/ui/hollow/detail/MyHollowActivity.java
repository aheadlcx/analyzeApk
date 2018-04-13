package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.edit.CreateHollowActivity;
import cn.xiaochuankeji.tieba.ui.hollow.widget.MyHollowNavigation;
import com.c.a.c;
import com.c.a.e;

public class MyHollowActivity extends cn.xiaochuankeji.tieba.ui.base.a {
    private FragmentMyHollow a;
    private FragmentMyReply b;
    private FragmentDiscovery c;
    private int d;

    private class a extends FragmentStatePagerAdapter {
        final /* synthetic */ MyHollowActivity a;

        a(MyHollowActivity myHollowActivity, FragmentManager fragmentManager) {
            this.a = myHollowActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return this.a.a;
                case 1:
                    return this.a.b;
                case 2:
                    return this.a.c;
                default:
                    return this.a.a;
            }
        }

        public int getCount() {
            return 3;
        }
    }

    public static void a(Activity activity) {
        activity.startActivity(new Intent(activity, MyHollowActivity.class));
    }

    protected int a() {
        return R.layout.activity_my_hollow;
    }

    public boolean h() {
        return true;
    }

    protected void onRestart() {
        boolean z;
        boolean z2 = true;
        super.onRestart();
        FragmentMyHollow fragmentMyHollow = this.a;
        if (this.d == 0) {
            z = true;
        } else {
            z = false;
        }
        fragmentMyHollow.b(z);
        FragmentMyReply fragmentMyReply = this.b;
        if (this.d == 1) {
            z = true;
        } else {
            z = false;
        }
        fragmentMyReply.b(z);
        FragmentDiscovery fragmentDiscovery = this.c;
        if (this.d != 2) {
            z2 = false;
        }
        fragmentDiscovery.b(z2);
    }

    protected void onPause() {
        super.onPause();
        this.a.b(false);
        this.b.b(false);
        this.c.b(false);
    }

    protected void i_() {
        this.a = new FragmentMyHollow();
        this.b = new FragmentMyReply();
        this.c = new FragmentDiscovery();
        final ViewPager viewPager = (ViewPager) findViewById(R.id.my_hollow_view_pager);
        viewPager.setAdapter(new a(this, getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        this.d = 0;
        final MyHollowNavigation myHollowNavigation = (MyHollowNavigation) findViewById(R.id.my_hollow_nav);
        viewPager.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ MyHollowActivity b;

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                boolean z;
                myHollowNavigation.setSelectedPosition(i);
                this.b.d = i;
                this.b.a.b(i == 0);
                FragmentMyReply b = this.b.b;
                if (i == 1) {
                    z = true;
                } else {
                    z = false;
                }
                b.b(z);
                FragmentDiscovery c = this.b.c;
                if (i == 2) {
                    z = true;
                } else {
                    z = false;
                }
                c.b(z);
                if (this.b.g()) {
                    e b2 = c.b(this.b);
                    if (i == 0) {
                        b2.b(true);
                    } else {
                        b2.b(false);
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        myHollowNavigation.setOnTabClickListener(new cn.xiaochuankeji.tieba.ui.hollow.widget.MyHollowNavigation.a(this) {
            final /* synthetic */ MyHollowActivity b;

            public void a(int i) {
                switch (i) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        return;
                    case 1:
                        viewPager.setCurrentItem(1);
                        return;
                    case 2:
                        CreateHollowActivity.a(this.b);
                        return;
                    case 3:
                        viewPager.setCurrentItem(2);
                        return;
                    default:
                        return;
                }
            }
        });
        myHollowNavigation.a(R.drawable.ic_arrow_left_white, new OnClickListener(this) {
            final /* synthetic */ MyHollowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }
}
