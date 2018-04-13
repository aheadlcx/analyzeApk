package antistatic.spinnerwheel;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;

public class WheelRecycler {
    private static final String a = WheelRecycler.class.getName();
    private List<View> b;
    private List<View> c;
    private AbstractWheel d;

    public WheelRecycler(AbstractWheel abstractWheel) {
        this.d = abstractWheel;
    }

    public int recycleItems(LinearLayout linearLayout, int i, ItemsRange itemsRange) {
        int i2 = 0;
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (itemsRange.contains(i)) {
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
        return a(this.b);
    }

    public View getEmptyItem() {
        return a(this.c);
    }

    public void clearAll() {
        if (this.b != null) {
            this.b.clear();
        }
        if (this.c != null) {
            this.c.clear();
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
        int itemsCount = this.d.getViewAdapter().getItemsCount();
        if ((i < 0 || i >= itemsCount) && !this.d.isCyclic()) {
            this.c = a(view, this.c);
            return;
        }
        while (i < 0) {
            i += itemsCount;
        }
        itemsCount = i % itemsCount;
        this.b = a(view, this.b);
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
