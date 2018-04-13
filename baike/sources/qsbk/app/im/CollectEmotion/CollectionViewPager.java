package qsbk.app.im.CollectEmotion;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.im.CollectEmotion.CollectionGridView.OnCollectionItemClickListener;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.widget.CustomViewPager;
import qsbk.app.widget.DotView;

public class CollectionViewPager extends CustomViewPager {
    List<LatestUsedCollectionData> a;
    private List<Integer> b;
    private List<View> c = new ArrayList();
    private PagerAdapter d;
    private LayoutInflater e;
    private OnCollectionItemClickListener f;
    private DotView g;

    private class a extends PagerAdapter {
        final /* synthetic */ CollectionViewPager a;

        private a(CollectionViewPager collectionViewPager) {
            this.a = collectionViewPager;
        }

        public int getCount() {
            return this.a.c.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = (View) this.a.c.get(i);
            viewGroup.addView(view);
            return view;
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (dataSetObserver != null) {
                super.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
            if (i < this.a.c.size()) {
            }
        }
    }

    private class b implements OnPageChangeListener {
        final /* synthetic */ CollectionViewPager a;

        private b(CollectionViewPager collectionViewPager) {
            this.a = collectionViewPager;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (this.a.g != null) {
                int size = this.a.b.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += ((Integer) this.a.b.get(i3)).intValue();
                    if (i < i2) {
                        this.a.g.setDotCount(((Integer) this.a.b.get(i3)).intValue());
                        this.a.g.setSelectedDot(((Integer) this.a.b.get(i3)).intValue() + (i - i2));
                        return;
                    }
                }
            }
        }
    }

    public CollectionViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = LayoutInflater.from(context);
    }

    public CollectionViewPager(Context context) {
        super(context);
        this.e = LayoutInflater.from(context);
    }

    public void setDatas(List<LatestUsedCollectionData> list) {
        this.a = list;
        a();
    }

    public void setOnCollectionClickListener(OnCollectionItemClickListener onCollectionItemClickListener) {
        this.f = onCollectionItemClickListener;
    }

    public void setDotView(DotView dotView) {
        this.g = dotView;
        this.g.setDotCount(((Integer) this.b.get(getCurrentItem())).intValue());
        this.g.setSelectedDot(0);
    }

    public void setNewDate(CollectImageDomain collectImageDomain) {
        this.a = CollectionManager.getInstance(QsbkApp.currentUser.userId).feachAll();
        a();
    }

    public void setNewDate() {
        this.a = CollectionManager.getInstance(QsbkApp.currentUser.userId).feachAll();
        a();
    }

    private void a() {
        int i = 0;
        if (this.a != null && !this.a.isEmpty()) {
            if (this.b != null) {
                this.b.clear();
            }
            this.b = new ArrayList();
            this.c.clear();
            int size = this.a.size();
            int i2 = 0;
            while (i2 < size) {
                int i3 = i + 1;
                i = i2 + 8;
                if (i > size) {
                    i = size;
                }
                this.c.add(a(this.a.subList(i2, i)));
                i2 = i;
                i = i3;
            }
            this.b.add(Integer.valueOf(i));
            this.d = new a();
            setAdapter(this.d);
            setOnPageChangeListener(new b());
        }
    }

    private View a(List<LatestUsedCollectionData> list) {
        View inflate = this.e.inflate(R.layout.collect_grid, null);
        CollectionGridView collectionGridView = (CollectionGridView) inflate.findViewById(R.id.collect_grid);
        collectionGridView.setData(list);
        if (this.f == null && (getContext() instanceof OnCollectionItemClickListener)) {
            this.f = (OnCollectionItemClickListener) getContext();
        }
        collectionGridView.setOnCollectionItemClickListener(this.f);
        return inflate;
    }
}
