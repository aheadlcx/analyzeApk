package com.budejie.www.adapter.g.b;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.budejie.www.R;
import com.budejie.www.adapter.a.e;
import java.util.ArrayList;
import java.util.List;

class b$1 extends PagerAdapter {
    final /* synthetic */ int a;
    final /* synthetic */ b b;
    private SparseArray<View> c = new SparseArray();

    b$1(b bVar, int i) {
        this.b = bVar;
        this.a = i;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int getCount() {
        return this.a;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.c.get(i));
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.c.get(i);
        if (view == null) {
            View inflate = View.inflate(this.b.b, R.layout.multi_page_layout, null);
            this.c.put(i, inflate);
            GridView gridView = (GridView) inflate.findViewById(R.id.SquareGridView);
            List arrayList = new ArrayList();
            int i2 = i * 10;
            while (i2 < (i + 1) * 10 && i2 < this.b.c.size()) {
                arrayList.add(this.b.c.get(i2));
                i2++;
            }
            gridView.setAdapter(new e(arrayList, this.b.b));
            view = inflate;
        }
        viewGroup.addView(view);
        return view;
    }
}
