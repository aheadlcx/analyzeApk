package qsbk.app.widget;

import android.support.v4.view.LiveBannerViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import qsbk.app.R;
import qsbk.app.widget.qiuyoucircle.UnknownCell.EmptyView;

public abstract class LoopBannerCell<T> extends BaseCell {
    List<T> a;
    LiveBannerViewPager b;
    a c;
    IndicatorView d;
    private boolean e;

    class a extends PagerAdapter {
        final /* synthetic */ LoopBannerCell a;

        a(LoopBannerCell loopBannerCell) {
            this.a = loopBannerCell;
        }

        public int getItemPosition(Object obj) {
            if (this.a.e) {
                return -2;
            }
            return super.getItemPosition(obj);
        }

        public int getCount() {
            return Integer.MAX_VALUE;
        }

        public int getIndex(int i) {
            return i % this.a.a.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            Object obj;
            View view = null;
            int index = getIndex(i);
            if (this.a.a.size() > index) {
                obj = this.a.a.get(index);
            } else {
                obj = null;
            }
            if (obj != null) {
                view = this.a.createView(viewGroup, obj, index);
            }
            if (view == null) {
                view = new EmptyView(viewGroup.getContext());
            }
            viewGroup.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            this.a.e = false;
        }
    }

    public abstract View createView(ViewGroup viewGroup, T t, int i);

    public abstract int getLayoutId();

    public LoopBannerCell(List<T> list) {
        this.a = list;
    }

    public void onCreate() {
        setCellView(getLayoutId());
        this.d = (IndicatorView) findViewById(R.id.indicator);
        this.b = (LiveBannerViewPager) findViewById(R.id.pager);
        this.c = new a(this);
        this.b.setAdapter(this.c);
        this.b.setCurrentItem(1073741823);
        this.d.setCount(this.a.size());
        this.b.addOnPageChangeListener(new co(this));
    }

    public void onUpdate() {
        boolean z;
        int i = 0;
        LiveBannerViewPager liveBannerViewPager = this.b;
        if (this.a.size() > 1) {
            z = true;
        } else {
            z = false;
        }
        liveBannerViewPager.setLoopEnable(z);
        IndicatorView indicatorView = this.d;
        if (this.a.size() <= 1) {
            i = 8;
        }
        indicatorView.setVisibility(i);
        this.d.setCount(this.a.size());
        this.c.notifyDataSetChanged();
    }

    public void requestDataChange() {
        this.e = true;
    }
}
