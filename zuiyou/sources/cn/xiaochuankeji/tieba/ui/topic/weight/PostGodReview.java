package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.recommend.CommentBean;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.utils.e;
import com.izuiyou.a.a.b;
import java.util.LinkedList;
import java.util.List;

public class PostGodReview extends RelativeLayout {
    private IndicatorView a;
    private StableViewPager b;
    private List<b> c;

    private static class a extends PagerAdapter {
        private List<b> a;

        a(List<b> list) {
            this.a = list;
        }

        public int getCount() {
            return this.a == null ? 0 : this.a.size();
        }

        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
        }

        @NonNull
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            viewGroup.addView((View) this.a.get(i));
            return this.a.get(i);
        }
    }

    public PostGodReview(@NonNull Context context) {
        super(context);
        a();
    }

    public PostGodReview(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PostGodReview(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_god_review, this);
        this.c = new LinkedList();
        this.a = (IndicatorView) findViewById(R.id.review_indicator);
        this.b = (StableViewPager) findViewById(R.id.review_view_pager);
        this.b.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ PostGodReview a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.a.a(i);
                this.a.a(i);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void a(final int i) {
        final LayoutParams layoutParams = this.b.getLayoutParams();
        layoutParams.height = e.c();
        this.b.setLayoutParams(layoutParams);
        ((b) this.c.get(i)).a(new a(this) {
            final /* synthetic */ PostGodReview c;

            public void a(int i) {
                b.c("Refresh HashCode : " + ((b) this.c.c.get(i)).hashCode() + "Height : " + i);
                layoutParams.height = i;
                this.c.b.setLayoutParams(layoutParams);
            }
        });
    }

    public void a(PostDataBean postDataBean, int i, int i2) {
        if (postDataBean.godReviews.size() > 1) {
            this.a.a(postDataBean.godReviews.size(), i, i2);
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(8);
        }
        this.c.clear();
        for (CommentBean commentBean : postDataBean.godReviews) {
            b bVar = new b(getContext());
            bVar.a(commentBean, postDataBean);
            this.c.add(bVar);
        }
        b();
    }

    private void b() {
        this.b.setAdapter(new a(this.c));
        a(0);
        this.b.setCurrentItem(0);
    }
}
