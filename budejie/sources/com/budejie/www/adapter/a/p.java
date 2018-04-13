package com.budejie.www.adapter.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.view.SideBar;
import com.budejie.www.bean.Fans;
import java.util.HashMap;
import java.util.List;

public class p extends BaseAdapter {
    HashMap<Integer, Integer> a = new HashMap();
    private Context b;
    private List<Fans> c;

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public p(Context context, List<Fans> list) {
        this.b = context;
        this.c = list;
        b((List) list);
    }

    public void a(List<Fans> list) {
        this.c = list;
        b((List) list);
        notifyDataSetChanged();
    }

    private void b(List<Fans> list) {
        this.a.clear();
        char c = '￿';
        for (int i = 0; i < list.size(); i++) {
            char charAt = ((Fans) list.get(i)).getSortLetters().toUpperCase().charAt(0);
            if (c != charAt) {
                this.a.put(Integer.valueOf(charAt), Integer.valueOf(i));
                c = charAt;
            }
        }
    }

    public int getCount() {
        return this.c.size();
    }

    public Fans a(int i) {
        return (Fans) this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return SideBar.a[0].equals(((Fans) this.c.get(i)).getSortLetters()) ? 0 : 1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (b(i)) {
            return view == null ? LayoutInflater.from(this.b).inflate(R.layout.choose_contact_search_item_layout, viewGroup, false) : view;
        } else {
            p$a p_a;
            if (view == null) {
                p_a = new p$a(this);
                view = LayoutInflater.from(this.b).inflate(R.layout.selector_contact_item_layout, null);
                p_a.b = (AsyncImageView) view.findViewById(R.id.contact_icon);
                p_a.a = (TextView) view.findViewById(R.id.contact_name);
                p_a.c = (TextView) view.findViewById(R.id.title);
                view.setTag(p_a);
            } else {
                p_a = (p$a) view.getTag();
            }
            if (d(i)) {
                p_a.c.setVisibility(0);
                CharSequence sortLetters = ((Fans) this.c.get(i)).getSortLetters();
                TextView textView = p_a.c;
                if (SideBar.a[1].equals(sortLetters)) {
                    sortLetters = this.b.getString(R.string.choose_contact_recent_contacts);
                }
                textView.setText(sortLetters);
            } else {
                p_a.c.setVisibility(8);
            }
            p_a.a.setText(((Fans) this.c.get(i)).getUsername());
            p_a.b.setAsyncCacheImage(((Fans) this.c.get(i)).getUserPic(), R.drawable.head_portrait);
            return view;
        }
    }

    private boolean d(int i) {
        if (i == 0) {
            return true;
        }
        return !((Fans) this.c.get(i)).getSortLetters().equals(((Fans) this.c.get(i + -1)).getSortLetters());
    }

    public boolean b(int i) {
        return getItemViewType(i) == 0;
    }

    public int c(int i) {
        Integer num = (Integer) this.a.get(Integer.valueOf(i));
        return num != null ? num.intValue() : -1;
    }
}
