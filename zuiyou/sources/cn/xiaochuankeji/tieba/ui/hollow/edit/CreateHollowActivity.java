package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import com.c.a.c;
import com.c.a.e;

public class CreateHollowActivity extends h implements b {
    private FragmentCreateAudioHollow d;
    private FragmentCreateTextHollow e;
    private NavigationBar f;
    private TBViewPager g;
    private TextView h;
    private TextView i;
    private View j;
    private View k;
    private View l;

    enum OptionType {
        INIT,
        RECORD_NEXT,
        RECORD_PLAYING,
        TEXT_PUBLISH
    }

    private class a extends FragmentStatePagerAdapter {
        final /* synthetic */ CreateHollowActivity a;

        a(CreateHollowActivity createHollowActivity, FragmentManager fragmentManager) {
            this.a = createHollowActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return this.a.d;
                case 1:
                    return this.a.e;
                default:
                    return this.a.d;
            }
        }

        public int getCount() {
            return 2;
        }
    }

    public static void a(Activity activity) {
        activity.startActivity(new Intent(activity, CreateHollowActivity.class));
    }

    protected int a() {
        return R.layout.activity_create_hollow;
    }

    public void a(OptionType optionType) {
        b(optionType);
        if (optionType.equals(OptionType.RECORD_NEXT) || optionType.equals(OptionType.RECORD_PLAYING)) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
        }
    }

    public boolean h() {
        return true;
    }

    protected void i_() {
        j();
        k();
        v();
        b(OptionType.INIT);
        a(0);
    }

    public void s() {
        switch (this.g.getCurrentItem()) {
            case 0:
                this.d.i();
                return;
            case 1:
                this.e.i();
                return;
            default:
                return;
        }
    }

    private void j() {
        this.f = (NavigationBar) findViewById(R.id.navBar);
        this.f.a(-1285, 0, 0);
        this.f.getOptionText().setTextColor(-686198);
    }

    private void k() {
        this.h = (TextView) findViewById(R.id.hollow_create_audio_text);
        this.i = (TextView) findViewById(R.id.hollow_create_text_text);
        this.k = findViewById(R.id.hollow_create_audio_view);
        this.l = findViewById(R.id.hollow_create_text_view);
        this.j = findViewById(R.id.layout_hollow_indicator);
        this.d = new FragmentCreateAudioHollow();
        this.e = new FragmentCreateTextHollow();
        this.d.a((b) this);
        this.e.a((b) this);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CreateHollowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g.setCurrentItem(0);
            }
        });
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CreateHollowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.g.setCurrentItem(1);
            }
        });
    }

    private void v() {
        this.g = (TBViewPager) findViewById(R.id.hollow_create_view_pager);
        this.g.setAdapter(new a(this, getSupportFragmentManager()));
        this.g.setOffscreenPageLimit(1);
        this.g.setCurrentItem(0);
        this.g.b();
        this.g.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ CreateHollowActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                boolean z;
                this.a.a(i);
                this.a.d.b(i == 0);
                FragmentCreateTextHollow c = this.a.e;
                if (i == 1) {
                    z = true;
                } else {
                    z = false;
                }
                c.b(z);
                if (this.a.g()) {
                    e b = c.b(this.a);
                    if (i == 0) {
                        b.b(true);
                    } else {
                        b.b(false);
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void b(OptionType optionType) {
        switch (optionType) {
            case TEXT_PUBLISH:
                this.f.setOptionText("发表");
                return;
            case RECORD_NEXT:
                this.f.setOptionText("下一步");
                return;
            default:
                this.f.setOptionText("");
                return;
        }
    }

    private void a(int i) {
        int i2;
        int i3 = 0;
        int i4 = -686198;
        TextView textView = this.h;
        if (i == 0) {
            i2 = -686198;
        } else {
            i2 = -8618362;
        }
        textView.setTextColor(i2);
        TextView textView2 = this.i;
        if (i != 1) {
            i4 = -8618362;
        }
        textView2.setTextColor(i4);
        View view = this.k;
        if (i == 0) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        view.setVisibility(i2);
        View view2 = this.l;
        if (i != 1) {
            i3 = 4;
        }
        view2.setVisibility(i3);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            finish();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return w() || super.onKeyDown(i, keyEvent);
        } else {
            return super.onKeyDown(i, keyEvent);
        }
    }

    public void r() {
        if (!w()) {
            super.r();
        }
    }

    private boolean w() {
        switch (this.g.getCurrentItem()) {
            case 0:
                return this.d.j();
            case 1:
                return this.e.j();
            default:
                return false;
        }
    }
}
