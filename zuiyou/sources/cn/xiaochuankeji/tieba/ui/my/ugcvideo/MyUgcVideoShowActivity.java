package cn.xiaochuankeji.tieba.ui.my.ugcvideo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;
import cn.xiaochuankeji.tieba.ui.widget.indicator.i;
import cn.xiaochuankeji.tieba.ui.widget.indicator.o;
import com.c.a.e;
import org.aspectj.a.b.b;

public class MyUgcVideoShowActivity extends h {
    private static final String[] d = new String[]{"发布", "喜欢"};
    private static final org.aspectj.lang.a.a k = null;
    private long e;
    private a f;
    private View g;
    private ViewPager h;
    private MagicIndicator i;
    private o j;

    class a extends g {
        final /* synthetic */ MyUgcVideoShowActivity a;

        public a(MyUgcVideoShowActivity myUgcVideoShowActivity, FragmentManager fragmentManager) {
            this.a = myUgcVideoShowActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            if (i == 0) {
                return f.a(this.a.e);
            }
            if (1 == i) {
                return g.a(this.a.e);
            }
            return null;
        }

        public int getCount() {
            return 2;
        }
    }

    private static void j() {
        b bVar = new b("MyUgcVideoShowActivity.java", MyUgcVideoShowActivity.class);
        k = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.my.ugcvideo.MyUgcVideoShowActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 44);
    }

    static {
        j();
    }

    public static void a(Context context, long j) {
        Intent intent = new Intent(context, MyUgcVideoShowActivity.class);
        intent.putExtra("s_key_uid", j);
        context.startActivity(intent);
    }

    static final void a(MyUgcVideoShowActivity myUgcVideoShowActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        myUgcVideoShowActivity.e = myUgcVideoShowActivity.getIntent().getLongExtra("s_key_uid", 0);
        if (0 == myUgcVideoShowActivity.e) {
            myUgcVideoShowActivity.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(k, this, this, bundle);
        c.c().a(new d(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_my_create_like_ugcvideo;
    }

    protected void c() {
        super.c();
        this.g = findViewById(R.id.ivBack);
        this.h = (ViewPager) findViewById(R.id.viewPager);
        this.i = (MagicIndicator) findViewById(R.id.indicator);
    }

    protected void i_() {
        this.f = new a(this, getSupportFragmentManager());
        this.h.setAdapter(this.f);
        this.h.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ MyUgcVideoShowActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (this.a.i != null) {
                    this.a.i.a(i, f, i2);
                }
            }

            public void onPageSelected(int i) {
                if (this.a.i != null) {
                    this.a.i.a(i);
                }
                if (this.a.g()) {
                    e b = com.c.a.c.b(this.a);
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
        i bVar = new cn.xiaochuankeji.tieba.ui.widget.indicator.b(this);
        bVar.setAdjustMode(true);
        this.j = new o(d);
        bVar.setAdapter(this.j);
        this.i.setNavigator(bVar);
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyUgcVideoShowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
    }

    public void a(int i) {
        this.j.a(0, "发布 " + i);
    }

    public void b(int i) {
        this.j.a(1, "喜欢 " + i);
    }

    protected void onResume() {
        super.onResume();
        if (this.j != null) {
            this.j.a(this.h);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.j != null) {
            this.j.c();
        }
    }
}
