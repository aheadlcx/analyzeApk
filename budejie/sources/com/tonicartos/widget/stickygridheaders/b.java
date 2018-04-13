package com.tonicartos.widget.stickygridheaders;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class b extends BaseAdapter {
    private final Context a;
    private int b;
    private boolean c = false;
    private DataSetObserver d = new f(this);
    private final a e;
    private StickyGridHeadersGridView f;
    private View g;
    private View h;
    private int i = 1;

    protected class a extends View {
        final /* synthetic */ b a;
        private View b;

        public a(b bVar, Context context) {
            this.a = bVar;
            super(context);
        }

        public void setMeasureTarget(View view) {
            this.b = view;
        }

        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(this.b.getMeasuredHeight(), 1073741824));
        }
    }

    protected class b extends FrameLayout {
        final /* synthetic */ b a;
        private int b;

        public b(b bVar, Context context) {
            this.a = bVar;
            super(context);
        }

        public int getHeaderId() {
            return this.b;
        }

        public void setHeaderId(int i) {
            this.b = i;
        }

        protected LayoutParams generateDefaultLayoutParams() {
            return new LayoutParams(-1, -1);
        }

        protected void onMeasure(int i, int i2) {
            View view = (View) getTag();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (view.getVisibility() != 8) {
                view.measure(getChildMeasureSpec(MeasureSpec.makeMeasureSpec(this.a.f.getWidth(), 1073741824), 0, layoutParams.width), getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
            }
            setMeasuredDimension(MeasureSpec.getSize(i), view.getMeasuredHeight());
        }
    }

    protected class c {
        protected int a;
        protected int b;
        final /* synthetic */ b c;

        protected c(b bVar, int i, int i2) {
            this.c = bVar;
            this.b = i;
            this.a = i2;
        }
    }

    public b(Context context, StickyGridHeadersGridView stickyGridHeadersGridView, a aVar) {
        this.a = context;
        this.e = aVar;
        this.f = stickyGridHeadersGridView;
        aVar.registerDataSetObserver(this.d);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public int getCount() {
        int i = 0;
        if (this.c) {
            return this.b;
        }
        this.b = 0;
        int a = this.e.a();
        if (a == 0) {
            this.b = this.e.getCount();
            this.c = true;
            return this.b;
        }
        while (i < a) {
            this.b += (this.e.a(i) + d(i)) + this.i;
            i++;
        }
        this.c = true;
        return this.b;
    }

    public Object getItem(int i) throws ArrayIndexOutOfBoundsException {
        c c = c(i);
        if (c.b == -1 || c.b == -2) {
            return null;
        }
        return this.e.getItem(c.b);
    }

    public long getItemId(int i) {
        c c = c(i);
        if (c.b == -2) {
            return -1;
        }
        if (c.b == -1) {
            return -2;
        }
        if (c.b == -3) {
            return -3;
        }
        return this.e.getItemId(c.b);
    }

    public int getItemViewType(int i) {
        c c = c(i);
        if (c.b == -2) {
            return 1;
        }
        if (c.b == -1) {
            return 0;
        }
        if (c.b == -3) {
            return 2;
        }
        int itemViewType = this.e.getItemViewType(c.b);
        return itemViewType != -1 ? itemViewType + 3 : itemViewType;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        c c = c(i);
        if (c.b == -2) {
            view = (b) view;
            if (view == null) {
                view = new b(this, this.a);
            }
            View a = this.e.a(c.a, (View) view.getTag(), viewGroup);
            this.f.b((View) view.getTag());
            view.setTag(a);
            this.f.a(a);
            this.g = view;
            view.forceLayout();
            return view;
        } else if (c.b == -3) {
            view = a(view, this.g);
            view.forceLayout();
            return view;
        } else if (c.b == -1) {
            return a(view, this.h);
        } else {
            view = this.e.getView(c.b, view, viewGroup);
            this.h = view;
            return view;
        }
    }

    public int getViewTypeCount() {
        return this.e.getViewTypeCount() + 3;
    }

    public boolean hasStableIds() {
        return this.e.hasStableIds();
    }

    public boolean isEmpty() {
        return this.e.isEmpty();
    }

    public boolean isEnabled(int i) {
        c c = c(i);
        return (c.b == -1 || c.b == -2 || !this.e.isEnabled(c.b)) ? false : true;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        super.registerDataSetObserver(dataSetObserver);
        this.e.registerDataSetObserver(dataSetObserver);
    }

    public void a(int i) {
        this.i = i;
        this.c = false;
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        super.unregisterDataSetObserver(dataSetObserver);
        this.e.unregisterDataSetObserver(dataSetObserver);
    }

    private a a(View view, View view2) {
        view = (a) view;
        if (view == null) {
            view = new a(this, this.a);
        }
        view.setMeasureTarget(view2);
        return view;
    }

    private int d(int i) {
        if (this.i == 0) {
            return 0;
        }
        int a = this.e.a(i) % this.i;
        if (a != 0) {
            return this.i - a;
        }
        return 0;
    }

    protected long b(int i) {
        return (long) c(i).a;
    }

    protected View a(int i, View view, ViewGroup viewGroup) {
        if (this.e.a() == 0) {
            return null;
        }
        return this.e.a(c(i).a, view, viewGroup);
    }

    protected c c(int i) {
        int i2 = 0;
        int a = this.e.a();
        if (a != 0) {
            int i3 = i;
            while (i2 < a) {
                int a2 = this.e.a(i2);
                if (i3 == 0) {
                    return new c(this, -2, i2);
                }
                i3 -= this.i;
                if (i3 < 0) {
                    return new c(this, -3, i2);
                }
                int i4 = i - this.i;
                if (i3 < a2) {
                    return new c(this, i4, i2);
                }
                int d = d(i2);
                i = i4 - d;
                a2 = i3 - (a2 + d);
                if (a2 < 0) {
                    return new c(this, -1, i2);
                }
                i2++;
                i3 = a2;
            }
            return new c(this, -1, i2);
        } else if (i >= this.e.getCount()) {
            return new c(this, -1, 0);
        } else {
            return new c(this, i, 0);
        }
    }

    protected void a() {
        int i = 0;
        this.b = 0;
        int a = this.e.a();
        if (a == 0) {
            this.b = this.e.getCount();
            this.c = true;
            return;
        }
        while (i < a) {
            this.b += this.e.a(i) + this.i;
            i++;
        }
        this.c = true;
    }
}
