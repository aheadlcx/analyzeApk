package com.budejie.www.activity.image;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.image.BitmapCache.a;
import java.util.List;

public class e extends BaseAdapter {
    final String a = getClass().getSimpleName();
    Activity b;
    List<d> c;
    BitmapCache d;
    a e = new e$1(this);

    public e(Activity activity, List<d> list) {
        this.b = activity;
        this.c = list;
        this.d = new BitmapCache();
    }

    public int getCount() {
        if (this.c != null) {
            return this.c.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        e$a e_a;
        if (view == null) {
            e$a e_a2 = new e$a(this);
            view = View.inflate(this.b, R.layout.item_image_bucket, null);
            e$a.a(e_a2, (ImageView) view.findViewById(R.id.image));
            e$a.a(e_a2, (TextView) view.findViewById(R.id.name));
            e$a.b(e_a2, (TextView) view.findViewById(R.id.count));
            view.setTag(e_a2);
            e_a = e_a2;
        } else {
            e_a = (e$a) view.getTag();
        }
        d dVar = (d) this.c.get(i);
        e$a.a(e_a).setText("(" + dVar.a + ")");
        e$a.b(e_a).setText(dVar.b);
        if (dVar.c == null || dVar.c.size() <= 0) {
            e$a.c(e_a).setImageBitmap(null);
        } else {
            String str = ((ImageItem) dVar.c.get(0)).thumbnailPath;
            String str2 = ((ImageItem) dVar.c.get(0)).imagePath;
            e$a.c(e_a).setTag(str2);
            this.d.a(e$a.c(e_a), str, str2, this.e);
        }
        return view;
    }
}
