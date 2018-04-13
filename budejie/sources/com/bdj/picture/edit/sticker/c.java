package com.bdj.picture.edit.sticker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.widget.MyGridView;
import java.util.List;

public class c extends BaseAdapter {
    private List<List<StickerItem>> a;
    private LayoutInflater b;
    private Context c;

    private static class a {
        public ImageView a;
        public TextView b;
        public MyGridView c;

        private a() {
        }
    }

    public c(List<List<StickerItem>> list, Context context) {
        this.a = list;
        this.b = LayoutInflater.from(context);
        this.c = context;
    }

    public void a(List<List<StickerItem>> list) {
        this.a = list;
        notifyDataSetChanged();
    }

    public void b(List<List<StickerItem>> list) {
        if (this.a == null || this.a.size() <= 0) {
            this.a = list;
        } else {
            this.a.addAll(list);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        a aVar2;
        List list = (List) this.a.get(i);
        if (view == null) {
            view = this.b.inflate(e.sticker_listview_item, null);
            aVar = null;
        } else {
            aVar = (a) view.getTag();
        }
        if (aVar == null) {
            aVar2 = new a();
            aVar2.c = (MyGridView) view.findViewById(d.sticker_listview_item_gridview);
            aVar2.a = (ImageView) view.findViewById(d.img_sub_heads);
            aVar2.b = (TextView) view.findViewById(d.txt_sub_heads);
            view.setTag(aVar2);
        } else {
            aVar2 = aVar;
        }
        String str = ((StickerItem) list.get(0)).sub_category_name;
        if ("最近使用".equalsIgnoreCase(str)) {
            aVar2.a.setVisibility(0);
            aVar2.a.setImageDrawable(this.c.getResources().getDrawable(com.bdj.picture.edit.a.c.icon_clock));
        } else if ("最近热门".equalsIgnoreCase(str) || "最新热门".equalsIgnoreCase(str)) {
            aVar2.a.setVisibility(0);
            aVar2.a.setImageDrawable(this.c.getResources().getDrawable(com.bdj.picture.edit.a.c.icon_hot));
        } else if ("最新上架".equalsIgnoreCase(str)) {
            aVar2.a.setVisibility(0);
            aVar2.a.setImageDrawable(this.c.getResources().getDrawable(com.bdj.picture.edit.a.c.icon_star));
        } else if ("人气排行".equalsIgnoreCase(str)) {
            aVar2.a.setVisibility(0);
            aVar2.a.setImageDrawable(this.c.getResources().getDrawable(com.bdj.picture.edit.a.c.icon_heart));
        } else {
            aVar2.a.setVisibility(8);
        }
        aVar2.b.setText(str);
        aVar2.c.setAdapter(new b(list, this.c));
        return view;
    }
}
