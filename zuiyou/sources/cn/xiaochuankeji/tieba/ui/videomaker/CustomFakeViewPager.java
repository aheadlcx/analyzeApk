package cn.xiaochuankeji.tieba.ui.videomaker;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import java.util.ArrayList;

public class CustomFakeViewPager extends TBViewPager {
    private LayoutInflater a;
    private ArrayList<View> b = new ArrayList();
    private View c;
    private View d;
    private b e;

    class a extends PagerAdapter {
        final /* synthetic */ CustomFakeViewPager a;

        a(CustomFakeViewPager customFakeViewPager) {
            this.a = customFakeViewPager;
        }

        public int getCount() {
            return this.a.b.size() + 2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View c;
            if (i == 0) {
                c = this.a.c;
            } else if (getCount() - 1 == i) {
                c = this.a.d;
            } else {
                c = (View) this.a.b.get(i - 1);
            }
            viewGroup.addView(c);
            return c;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (i == 0) {
                viewGroup.removeView(this.a.c);
            } else if (getCount() - 1 == i) {
                viewGroup.removeView(this.a.d);
            } else {
                viewGroup.removeView((View) this.a.b.get(i - 1));
            }
        }
    }

    public interface b {
        void a(int i);

        void a(int i, float f);
    }

    public CustomFakeViewPager(Context context) {
        super(context);
        c();
    }

    public CustomFakeViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    private void c() {
        this.a = LayoutInflater.from(getContext());
        for (int i = 0; i < g.a.size(); i++) {
            this.b.add(this.a.inflate(R.layout.video_filter_fake_container, null));
        }
        this.c = this.a.inflate(R.layout.video_filter_fake_container, null);
        this.d = this.a.inflate(R.layout.video_filter_fake_container, null);
        setAdapter(new a(this));
        setCurrentItem(1);
        setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ CustomFakeViewPager a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
                if (i == 0) {
                    i = this.a.b.size();
                } else if (this.a.b.size() + 1 == i) {
                    i = 1;
                }
                if (this.a.e != null) {
                    this.a.e.a(i - 1, f);
                }
            }

            public void onPageSelected(int i) {
                if (i == 0) {
                    i = this.a.b.size();
                    this.a.setCurrentItem(i, false);
                } else if (this.a.b.size() + 1 == i) {
                    this.a.setCurrentItem(1, false);
                    i = 1;
                }
                if (this.a.e != null) {
                    this.a.e.a(i - 1);
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void setVideoFilterListener(b bVar) {
        this.e = bVar;
    }

    public void setCustomCurrentItem(int i) {
        super.setCurrentItem(i + 1, false);
    }
}
