package com.budejie.www.activity.video;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import java.util.List;

public class n extends BaseAdapter {
    private List<ListItemObject> a;
    private Context b;
    private LayoutInflater c = null;

    private class a {
        TextView a;
        AsyncImageView b;
        final /* synthetic */ n c;

        private a(n nVar) {
            this.c = nVar;
            this.a = null;
            this.b = null;
        }
    }

    public n(Context context, List<ListItemObject> list) {
        this.b = context;
        this.a = list;
        this.c = LayoutInflater.from(context);
        Log.d("RecommendVideoFragmentAdapter", "mRecommendDatas.size()" + list.size());
    }

    public void a(List<ListItemObject> list) {
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.c.inflate(R.layout.video_recommend_item, null);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(R.id.video_recommend_item_title);
            aVar.b = (AsyncImageView) view.findViewById(R.id.video_recommend_item_image);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        ListItemObject listItemObject = (ListItemObject) this.a.get(i);
        aVar.a.setText(listItemObject.getContent());
        aVar.b.setAsyncCacheImage(listItemObject.getImgUrl());
        return view;
    }
}
