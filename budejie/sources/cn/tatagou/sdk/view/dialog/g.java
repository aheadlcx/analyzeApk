package cn.tatagou.sdk.view.dialog;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;

public class g {
    private List<View> a;
    private List<View> b;
    private WheelView c;

    public g(WheelView wheelView) {
        this.c = wheelView;
    }

    public int recycleItems(LinearLayout linearLayout, int i, c cVar) {
        int i2 = 0;
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (cVar.contains(i)) {
                i2++;
            } else {
                a(linearLayout.getChildAt(i2), i);
                linearLayout.removeViewAt(i2);
                if (i2 == 0) {
                    i3++;
                }
            }
            i++;
        }
        return i3;
    }

    public View getItem() {
        return a(this.a);
    }

    public View getEmptyItem() {
        return a(this.b);
    }

    public void clearAll() {
        if (this.a != null) {
            this.a.clear();
        }
        if (this.b != null) {
            this.b.clear();
        }
    }

    private List<View> a(View view, List<View> list) {
        if (list == null) {
            list = new LinkedList();
        }
        list.add(view);
        return list;
    }

    private void a(View view, int i) {
        int itemsCount = this.c.getViewAdapter().getItemsCount();
        if ((i < 0 || i >= itemsCount) && !this.c.isCyclic()) {
            this.b = a(view, this.b);
            return;
        }
        while (i < 0) {
            i += itemsCount;
        }
        itemsCount = i % itemsCount;
        this.a = a(view, this.a);
    }

    private View a(List<View> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        View view = (View) list.get(0);
        list.remove(0);
        return view;
    }
}
