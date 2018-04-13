package qsbk.app.im.emotion;

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
import java.util.Map;
import qsbk.app.R;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.emotion.EmotionGridView.OnEmotionItemClickListener;
import qsbk.app.widget.CustomViewPager;
import qsbk.app.widget.DotView;

public class EmotionViewPager extends CustomViewPager {
    public static final int PER_PAGE_COUNT = 8;
    private Map<String, List<ChatMsgEmotionData>> a;
    private List<Integer> b;
    private List<View> c = new ArrayList();
    private PagerAdapter d;
    private LayoutInflater e;
    private OnEmotionItemClickListener f;
    private int g = 8;
    private DotView h;
    private int i = 0;
    private int j = 0;

    private class a extends PagerAdapter {
        final /* synthetic */ EmotionViewPager a;

        private a(EmotionViewPager emotionViewPager) {
            this.a = emotionViewPager;
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
            viewGroup.removeView((View) this.a.c.get(i));
        }
    }

    private class b implements OnPageChangeListener {
        final /* synthetic */ EmotionViewPager a;

        private b(EmotionViewPager emotionViewPager) {
            this.a = emotionViewPager;
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (this.a.h != null) {
                int size = this.a.b.size();
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += ((Integer) this.a.b.get(i3)).intValue();
                    if (i < i2) {
                        this.a.h.setDotCount(((Integer) this.a.b.get(i3)).intValue());
                        this.a.h.setSelectedDot(((Integer) this.a.b.get(i3)).intValue() + (i - i2));
                        return;
                    }
                }
            }
        }
    }

    public EmotionViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = LayoutInflater.from(context);
    }

    public EmotionViewPager(Context context) {
        super(context);
        this.e = LayoutInflater.from(context);
    }

    public void setDatas(Map<String, List<ChatMsgEmotionData>> map) {
        this.a = map;
        a();
    }

    public void setPerPageCount(int i) {
        this.g = i;
    }

    public void setRowCount(int i) {
        this.j = i;
    }

    public void setEmotionType(int i) {
        this.i = i;
    }

    public void setOnEmotionClickListener(OnEmotionItemClickListener onEmotionItemClickListener) {
        this.f = onEmotionItemClickListener;
    }

    public void setDotView(DotView dotView) {
        this.h = dotView;
        this.h.setDotCount(((Integer) this.b.get(getCurrentItem())).intValue());
        this.h.setSelectedDot(0);
    }

    public void removeData() {
        this.c.clear();
        this.d.notifyDataSetChanged();
        this.a.clear();
    }

    private void a() {
        if (this.a != null && !this.a.isEmpty()) {
            if (this.b != null) {
                this.b.clear();
            }
            this.c.clear();
            this.b = new ArrayList();
            int i = 0;
            for (List list : this.a.values()) {
                int size = list.size();
                int i2 = 0;
                while (i2 < size) {
                    int i3 = i + 1;
                    i = this.g + i2;
                    if (i > size) {
                        i = size;
                    }
                    this.c.add(a(list.subList(i2, i)));
                    i2 = i;
                    i = i3;
                }
                this.b.add(Integer.valueOf(i));
            }
            this.d = new a();
            setAdapter(this.d);
            setOnPageChangeListener(new b());
        }
    }

    private View a(List<ChatMsgEmotionData> list) {
        View inflate = this.e.inflate(R.layout.emotion_grid, null);
        EmotionGridView emotionGridView = (EmotionGridView) inflate.findViewById(R.id.grid);
        if (this.i == 1 && this.j > 0) {
            emotionGridView.setNumColumns(this.g / this.j);
        }
        emotionGridView.setEmotionType(this.i);
        emotionGridView.setData(list);
        if (this.f == null && (getContext() instanceof OnEmotionItemClickListener)) {
            this.f = (OnEmotionItemClickListener) getContext();
        }
        emotionGridView.setOnEmotionItemClickListener(this.f);
        return inflate;
    }
}
