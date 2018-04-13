package cn.xiaochuankeji.tieba.ui.discovery.moretopic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.topic.Category;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicInitModel;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicInitModel.CallBack;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import java.util.ArrayList;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class MoreTopicRecommendActivity extends h {
    private static final a j = null;
    private RecyclerView d;
    private ViewPager e;
    private RecommendTopicInitModel f;
    private ArrayList<Category> g;
    private long h;
    private cn.xiaochuankeji.tieba.ui.discovery.a.a i;

    static {
        w();
    }

    private static void w() {
        b bVar = new b("MoreTopicRecommendActivity.java", MoreTopicRecommendActivity.class);
        j = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.discovery.moretopic.MoreTopicRecommendActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 47);
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, MoreTopicRecommendActivity.class));
    }

    static final void a(MoreTopicRecommendActivity moreTopicRecommendActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        moreTopicRecommendActivity.h = moreTopicRecommendActivity.getIntent().getLongExtra("current_id", -1);
        moreTopicRecommendActivity.f = RecommendTopicInitModel.getInstance();
        if (moreTopicRecommendActivity.f.hasData()) {
            moreTopicRecommendActivity.j();
        } else {
            moreTopicRecommendActivity.f.query(new CallBack(moreTopicRecommendActivity) {
                final /* synthetic */ MoreTopicRecommendActivity a;

                {
                    this.a = r1;
                }

                public void queryFinish(boolean z, String str) {
                    if (z) {
                        this.a.j();
                    } else {
                        g.a(str);
                    }
                }
            });
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(j, this, this, bundle);
        c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private void j() {
        if (-1 == this.h) {
            this.h = this.f.getCurrentCategoryId();
        }
        this.g = this.f.getTopicCategorys();
        k();
        v();
    }

    protected int a() {
        return R.layout.activity_topic_all_recommend;
    }

    protected void c() {
        super.c();
        this.d = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        this.e = (ViewPager) findViewById(R.id.viewPager);
    }

    protected void i_() {
    }

    private void k() {
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.d.setLayoutManager(linearLayoutManager);
        this.d.addItemDecoration(new cn.xiaochuankeji.tieba.ui.discovery.a.c());
        this.i = new cn.xiaochuankeji.tieba.ui.discovery.a.a(this, this.g, this.h);
        this.d.setAdapter(this.i);
        this.i.a(new cn.xiaochuankeji.tieba.ui.discovery.a.a.a(this) {
            final /* synthetic */ MoreTopicRecommendActivity a;

            {
                this.a = r1;
            }

            public void a(View view, long j) {
                int i = 0;
                for (int i2 = 0; i2 < this.a.g.size(); i2++) {
                    if (((Category) this.a.g.get(i2)).categoryId == j) {
                        i = i2;
                    }
                }
                this.a.e.setCurrentItem(i);
            }
        });
        for (int i = 0; i < this.g.size(); i++) {
            if (this.h == ((Category) this.g.get(i)).categoryId) {
                this.d.scrollToPosition(i);
                return;
            }
        }
    }

    private void v() {
        this.e.setAdapter(new c(getSupportFragmentManager(), this.g));
        for (int i = 0; i < this.g.size(); i++) {
            if (this.h == ((Category) this.g.get(i)).categoryId) {
                this.e.setCurrentItem(i);
                break;
            }
        }
        this.e.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ MoreTopicRecommendActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.i.a(((Category) this.a.g.get(i)).categoryId);
                this.a.d.scrollToPosition(i);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
