package com.budejie.www.activity;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.util.aa;
import java.util.ArrayList;

public class c extends BaseAdapter {
    private ArrayList<b> a;
    private Activity b;

    public c(Activity activity, ArrayList<b> arrayList) {
        this.b = activity;
        this.a = arrayList;
    }

    public void a(ArrayList<b> arrayList) {
        this.a = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.a.isEmpty() ? 0 : this.a.size();
    }

    public Object getItem(int i) {
        return this.a.isEmpty() ? Integer.valueOf(0) : this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar = (b) this.a.get(i);
        if (bVar != null && "http://xt.budejie.com/xuantu/album/new/".equals(bVar.a)) {
            return this.b.getLayoutInflater().inflate(R.layout.music_album_item_add, null);
        }
        c$a c_a;
        c$a c_a2;
        if (view == null) {
            view = this.b.getLayoutInflater().inflate(R.layout.music_album_item, null);
            c_a2 = new c$a(this);
            c_a2.a = (AsyncImageView) view.findViewById(R.id.music_album_cover);
            c_a2.b = (TextView) view.findViewById(R.id.music_album_content);
            view.setTag(c_a2);
            c_a = c_a2;
        } else {
            c_a = (c$a) view.getTag();
            if (c_a == null) {
                view = this.b.getLayoutInflater().inflate(R.layout.music_album_item, null);
                c_a2 = new c$a(this);
                c_a2.a = (AsyncImageView) view.findViewById(R.id.music_album_cover);
                c_a2.b = (TextView) view.findViewById(R.id.music_album_content);
                view.setTag(c_a2);
                c_a = c_a2;
            }
        }
        a(i, c_a);
        return view;
    }

    private void a(int i, c$a c_a) {
        aa.b("MusicAlbumAdapter", "initData() position=" + i);
        b bVar = (b) this.a.get(i);
        if (bVar != null) {
            aa.b("MusicAlbumAdapter", "musicAlbum.getContent()=" + bVar.c);
            c_a.a.setAsyncCacheImage(bVar.b, 0);
            c_a.b.setText(bVar.c);
        }
    }
}
