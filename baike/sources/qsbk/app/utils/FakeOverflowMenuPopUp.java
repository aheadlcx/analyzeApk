package qsbk.app.utils;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.comm.ArrayUtils;

public class FakeOverflowMenuPopUp implements OnKeyListener, OnItemClickListener {
    private static final String a = FakeOverflowMenuPopUp.class.getSimpleName();
    private OnMenuItemClickListener b;
    private ListPopupWindow c;
    private OverflowMenuAdapter d;

    public static class Item {
        public int id;
        public String imageUrl;
        public String title;

        public static Item newInstance(int i, String str, String str2) {
            Item item = new Item();
            item.id = i;
            item.title = str;
            item.imageUrl = str2;
            return item;
        }

        public boolean equals(Object obj) {
            if ((obj instanceof Item) && ((Item) obj).id == this.id) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Integer.valueOf(this.id).hashCode();
        }

        public String toString() {
            return "Item [title=" + this.title + ", imageUrl=" + this.imageUrl + ", id=" + this.id + "]";
        }
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(Item item);
    }

    private static class OverflowMenuAdapter extends BaseAdapter {
        static final int a = UIHelper.getDefaultAvatarIconInOverflow();
        List<Item> b;
        LayoutInflater c;
        b d = new b();

        public static final class ViewType {
        }

        static final class a {
            TextView a;
            ImageView b;

            public a(View view) {
                this.a = (TextView) view.findViewById(R.id.title);
                this.b = (ImageView) view.findViewById(R.id.icon);
                view.setTag(this);
            }

            public static a get(View view) {
                Object tag = view.getTag();
                if (tag == null || !(tag instanceof a)) {
                    return new a(view);
                }
                return (a) tag;
            }
        }

        OverflowMenuAdapter(LayoutInflater layoutInflater, List<Item> list) {
            this.b = list;
            ArrayUtils.sort(this.b, this.d);
            this.c = layoutInflater;
        }

        public boolean addItem(Item item, boolean z) {
            if (item == null) {
                return false;
            }
            boolean update = update(item, z);
            if (update) {
                return update;
            }
            update = this.b.add(item);
            ArrayUtils.sort(this.b, this.d);
            return update;
        }

        public Item findItemById(int i) {
            for (Item item : this.b) {
                if (item.id == i) {
                    return item;
                }
            }
            return null;
        }

        public Item removeItemById(int i, boolean z) {
            Item findItemById = findItemById(i);
            if (findItemById != null) {
                this.b.remove(findItemById);
            }
            if (z) {
                notifyDataSetChanged();
            }
            return findItemById;
        }

        public boolean update(Item item, boolean z) {
            boolean z2 = false;
            if (item != null) {
                Item findItemById = findItemById(item.id);
                if (findItemById != null) {
                    z2 = true;
                    findItemById.imageUrl = item.imageUrl;
                    findItemById.title = item.title;
                }
            }
            if (z2 && z) {
                notifyDataSetChanged();
            }
            return z2;
        }

        public int getCount() {
            return this.b != null ? this.b.size() : 0;
        }

        public Object getItem(int i) {
            return this.b != null ? (Item) this.b.get(i) : null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int i) {
            return i == 0 ? 0 : 1;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            Item item = (Item) this.b.get(i);
            switch (itemViewType) {
                case 0:
                    if (view == null) {
                        view = this.c.inflate(R.layout.fake_overflow_popup_menu_head_item_layout, viewGroup, false);
                        break;
                    }
                    break;
                default:
                    if (view == null) {
                        view = this.c.inflate(R.layout.fake_overflow_popup_menu_item_layout, viewGroup, false);
                        break;
                    }
                    break;
            }
            a aVar = a.get(view);
            aVar.a.setText(item.title);
            if (itemViewType == 0) {
                FrescoImageloader.displayAvatar(aVar.b, item.imageUrl, a);
            } else {
                FrescoImageloader.displayAvatar(aVar.b, item.imageUrl);
            }
            if (UIHelper.isNightTheme()) {
                view.setBackgroundResource(R.drawable.popupmenu_item_bg_dark);
            } else {
                view.setBackgroundResource(R.drawable.popupmenu_item_bg_day);
            }
            return view;
        }
    }

    private static class a extends OverflowMenuAdapter {
        a(LayoutInflater layoutInflater, List<Item> list) {
            super(layoutInflater, list);
        }

        public int getItemViewType(int i) {
            return 1;
        }
    }

    private static class b implements Comparator<Item> {
        private b() {
        }

        public int compare(Item item, Item item2) {
            return item.id - item2.id;
        }
    }

    public FakeOverflowMenuPopUp(Context context, List<Item> list, View view) {
        if (haveHead()) {
            this.d = new OverflowMenuAdapter(LayoutInflater.from(context), list);
        } else {
            this.d = new a(LayoutInflater.from(context), list);
        }
        this.c = new ListPopupWindow(context);
        this.c.setAnchorView(view);
        this.c.setContentWidth((int) (context.getResources().getDisplayMetrics().density * 196.0f));
        this.c.setAdapter(this.d);
        this.c.setOnItemClickListener(this);
        this.c.setModal(true);
    }

    public static void test(Context context, View view) {
        List arrayList = new ArrayList();
        Item item = new Item();
        item.id = 3;
        item.title = "我去。.。";
        arrayList.add(item);
        item = new Item();
        item.id = 2;
        item.title = "我不去。.。";
        arrayList.add(item);
        item = new Item();
        item.id = 4;
        item.title = "不去罢了";
        arrayList.add(item);
        new FakeOverflowMenuPopUp(context, arrayList, view).toggle();
    }

    public boolean haveHead() {
        return true;
    }

    public void onDestroy() {
        if (this.c != null) {
            if (this.c.getListView() != null) {
                this.c.getListView().setOnKeyListener(null);
            }
            this.c.setAdapter(null);
            this.c.setOnItemClickListener(null);
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.b = onMenuItemClickListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.c.setOnDismissListener(onDismissListener);
    }

    public void dismiss() {
        this.c.dismiss();
    }

    public boolean isShowing() {
        return this.c.isShowing();
    }

    public void toggle() {
        if (isShowing()) {
            dismiss();
        } else {
            show();
        }
    }

    public void notifyDataSetChanged() {
        this.d.notifyDataSetChanged();
    }

    public void show() {
        if (!isShowing()) {
            this.c.show();
            if (this.c.getListView() != null) {
                this.c.getListView().setOnKeyListener(this);
            }
        }
    }

    public void postShow() {
        if (!isShowing()) {
            this.c.postShow();
        }
    }

    public boolean update(Item item, boolean z) {
        return this.d.update(item, z);
    }

    public Item findItemById(int i) {
        return this.d.findItemById(i);
    }

    public Item getItem(int i) {
        return (Item) this.d.getItem(i);
    }

    public Item removeItemById(int i, boolean z) {
        return this.d.removeItemById(i, z);
    }

    public boolean addItem(Item item, boolean z) {
        return this.d.addItem(item, z);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.b != null) {
            Item item = getItem(i);
            if (item != null) {
                this.b.onMenuItemClick(item);
            }
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((i != 1 && i != 82) || !isShowing()) {
            return false;
        }
        dismiss();
        return true;
    }
}
