package com.budejie.www.adapter.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.ac;
import com.budejie.www.util.ai;
import com.budejie.www.util.at;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class n extends BaseAdapter {
    private Activity a;
    private List<ListItemObject> b = new ArrayList();
    private boolean c;
    private String d = "";

    public class a implements OnClickListener {
        final /* synthetic */ n a;
        private Context b;
        private ListItemObject c;

        private a(n nVar, ListItemObject listItemObject, Context context) {
            this.a = nVar;
            this.b = context;
            this.c = listItemObject;
        }

        public void onClick(View view) {
            com.budejie.www.util.a.a((Activity) this.b, this.c, "", false);
        }
    }

    class b {
        TextView a;
        TextView b;
        TextView c;
        AsyncImageView d;
        ImageView e;
        final /* synthetic */ n f;

        b(n nVar) {
            this.f = nVar;
        }
    }

    public void a(String str) {
        this.d = str;
    }

    public n(Activity activity) {
        this.a = activity;
        this.c = ai.a(activity) == 0;
    }

    public void a(List<ListItemObject> list) {
        if (this.b != null) {
            this.b.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void b(List<ListItemObject> list) {
        this.b.clear();
        if (this.b != null) {
            this.b.addAll(list);
        }
        notifyDataSetInvalidated();
    }

    public void a() {
        if (this.b != null) {
            this.b.clear();
        }
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = this.a.getLayoutInflater().inflate(R.layout.search_post_result_layout, null);
            bVar = new b(this);
            bVar.a = (TextView) view.findViewById(R.id.PostContentTextView);
            bVar.b = (TextView) view.findViewById(R.id.SearchPostInfoTextView);
            bVar.c = (TextView) view.findViewById(R.id.VideoTimTextView);
            bVar.e = (ImageView) view.findViewById(R.id.VideoPlayImageView);
            bVar.d = (AsyncImageView) view.findViewById(R.id.PostImageView);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        ListItemObject listItemObject = (ListItemObject) this.b.get(i);
        a(listItemObject, bVar);
        listItemObject.getWid();
        view.setOnClickListener(new a(listItemObject, this.a));
        return view;
    }

    private void a(ListItemObject listItemObject, b bVar) {
        if (listItemObject.getVideotime() != null) {
            bVar.c.setText(ac.a((long) (Integer.parseInt(listItemObject.getVideotime()) * 1000)));
        }
        bVar.b.setText(listItemObject.getName() + "  " + listItemObject.getPasstime());
        bVar.a.setText(at.a(this.a, at.a(listItemObject.getContent(), this.d), this.d, this.c));
        String type = listItemObject.getType();
        if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type)) {
            bVar.e.setVisibility(8);
            bVar.c.setVisibility(8);
            bVar.d.setAsyncCacheImage(listItemObject.getImgUrl());
            if (listItemObject.getHeight() > listItemObject.getWidth()) {
                bVar.d.setScaleType(ScaleType.CENTER_CROP);
            } else {
                bVar.d.setScaleType(ScaleType.FIT_XY);
            }
        } else if ("29".equals(type)) {
            bVar.e.setVisibility(8);
            bVar.c.setVisibility(8);
            bVar.d.setScaleType(ScaleType.FIT_XY);
            bVar.d.setAsyncCacheImage("", R.drawable.search_duanzi_bg);
            bVar.d.setImageResource(R.drawable.search_duanzi_bg);
        } else {
            bVar.e.setVisibility(0);
            bVar.c.setVisibility(0);
            bVar.d.setAsyncCacheImage(listItemObject.getImgUrl(), R.drawable.likelist_defaut_bg);
            if (listItemObject.getHeight() > listItemObject.getWidth()) {
                bVar.d.setScaleType(ScaleType.CENTER_CROP);
            } else {
                bVar.d.setScaleType(ScaleType.FIT_XY);
            }
        }
    }

    public boolean isEmpty() {
        return this.b == null || this.b.isEmpty();
    }
}
