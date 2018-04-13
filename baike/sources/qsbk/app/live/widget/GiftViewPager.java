package qsbk.app.live.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import qsbk.app.core.model.GiftData;
import qsbk.app.live.R;
import qsbk.app.live.widget.GiftGridView.OnGiftItemClickListener;

public class GiftViewPager extends ViewPager {
    private Map<String, List<GiftData>> a;
    private List<Integer> b;
    private int c;
    private DotView d;
    private List<View> e;
    private LayoutInflater f;
    private PagerAdapter g;
    private OnGiftItemClickListener h;
    private OnGiftItemClickListener i;

    private class a extends PagerAdapter {
        final /* synthetic */ GiftViewPager a;

        private a(GiftViewPager giftViewPager) {
            this.a = giftViewPager;
        }

        public int getCount() {
            return this.a.e.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = (View) this.a.e.get(i);
            viewGroup.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) this.a.e.get(i));
        }
    }

    private class b implements OnPageChangeListener {
        final /* synthetic */ GiftViewPager a;

        private b(GiftViewPager giftViewPager) {
            this.a = giftViewPager;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (this.a.d != null) {
                int size = this.a.b.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += ((Integer) this.a.b.get(i3)).intValue();
                    if (i < i2) {
                        this.a.d.setDotCount(((Integer) this.a.b.get(i3)).intValue());
                        this.a.d.setSelectedDot(((Integer) this.a.b.get(i3)).intValue() + (i - i2));
                        return;
                    }
                }
            }
        }
    }

    public GiftViewPager(Context context) {
        this(context, null);
    }

    public GiftViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 8;
        this.e = new ArrayList();
        this.f = LayoutInflater.from(context);
    }

    public void setDatas(Map<String, List<GiftData>> map) {
        this.a = map;
        a();
    }

    private void a() {
        if (this.a != null && !this.a.isEmpty()) {
            if (this.b == null) {
                this.b = new ArrayList();
            } else {
                this.b.clear();
            }
            if (this.e.size() > 0) {
                this.e.clear();
            }
            this.i = new ec(this);
            int i = 0;
            for (List list : this.a.values()) {
                int size = list.size();
                int i2 = 0;
                while (i2 < size) {
                    int i3 = i + 1;
                    i = this.c + i2;
                    if (i > size) {
                        i = size;
                    }
                    this.e.add(a(list.subList(i2, i)));
                    i2 = i;
                    i = i3;
                }
                this.b.add(Integer.valueOf(i));
            }
            this.g = new a();
            setAdapter(this.g);
            addOnPageChangeListener(new b());
            this.g.notifyDataSetChanged();
        }
    }

    private View a(List<GiftData> list) {
        View inflate = this.f.inflate(R.layout.live_grid_gift, null);
        GiftGridView giftGridView = (GiftGridView) inflate.findViewById(R.id.live_grid);
        giftGridView.setData(list);
        if (this.h == null && (getContext() instanceof OnGiftItemClickListener)) {
            this.h = (OnGiftItemClickListener) getContext();
        }
        giftGridView.addOnGiftItemClickListener(this.h);
        giftGridView.addOnGiftItemClickListener(this.i);
        return inflate;
    }

    public void clearGiftCheck() {
        for (int i = 0; i < this.e.size(); i++) {
            ((GiftGridView) ((View) this.e.get(i)).findViewById(R.id.live_grid)).clearGiftCheck();
        }
    }

    public void setPerPageCount(int i) {
        this.c = i;
    }

    public void setOnGiftItemClickListener(OnGiftItemClickListener onGiftItemClickListener) {
        this.h = onGiftItemClickListener;
    }

    public void setDotView(DotView dotView) {
        this.d = dotView;
        this.d.setDotCount(((Integer) this.b.get(getCurrentItem())).intValue());
        this.d.setSelectedDot(0);
    }
}
