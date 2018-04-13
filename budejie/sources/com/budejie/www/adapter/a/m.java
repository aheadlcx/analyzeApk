package com.budejie.www.adapter.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.i;
import com.tencent.connect.common.Constants;
import java.util.List;

public class m extends BaseAdapter {
    private Context a;
    private List<ListItemObject> b;
    private LayoutInflater c;
    private LayoutParams d = a();

    public m(Context context) {
        this.a = context;
        this.c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    private LayoutParams a() {
        this.a.getResources().getDimension(R.dimen.horizontal_spacing);
        int a = (i.a().a(this.a) + 0) / 3;
        return new LayoutParams(a, a);
    }

    public void a(List<ListItemObject> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.clear();
            this.b.addAll(list);
        }
        notifyDataSetInvalidated();
    }

    public void b(List<ListItemObject> list) {
        if (this.b == null) {
            this.b = list;
        } else {
            this.b.addAll(list);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b != null) {
            return this.b.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        m$b m_b;
        Object obj;
        if (view == null) {
            m_b = new m$b(this);
            view = this.c.inflate(R.layout.search_like_item, null);
            m$b.a(m_b, (ImageView) view.findViewById(R.id.VideoPlayImageView));
            m$b.a(m_b, (AsyncImageView) view.findViewById(R.id.item_at_img));
            view.setTag(m_b);
        } else {
            m_b = (m$b) view.getTag();
        }
        ListItemObject listItemObject = (ListItemObject) this.b.get(i);
        String str = "";
        if (TextUtils.isEmpty(listItemObject.getIs_gif()) || !"1".equals(listItemObject.getIs_gif())) {
            obj = listItemObject.getsmallImage();
        } else {
            obj = listItemObject.getImgUrl();
        }
        m$b.a(m_b).setLayoutParams(this.d);
        if (!TextUtils.isEmpty(obj)) {
            m$b.a(m_b).setAsyncCacheImage(obj, R.drawable.likelist_defaut_bg);
        }
        if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(listItemObject.getType())) {
            m$b.b(m_b).setVisibility(8);
        } else {
            m$b.b(m_b).setVisibility(0);
        }
        view.setOnClickListener(new m$a(this, listItemObject, this.a, null));
        return view;
    }
}
