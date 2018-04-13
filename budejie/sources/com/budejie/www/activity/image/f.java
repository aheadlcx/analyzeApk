package com.budejie.www.activity.image;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.bdj.picture.edit.util.d;
import com.budejie.www.R;
import com.budejie.www.activity.image.BitmapCache.a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class f extends BaseAdapter {
    final String a = getClass().getSimpleName();
    Activity b;
    List<ImageItem> c;
    Map<String, String> d = new HashMap();
    BitmapCache e;
    boolean f = true;
    a g = new f$1(this);

    public f(Activity activity, boolean z) {
        this.b = activity;
        this.f = z;
        this.e = new BitmapCache();
    }

    public void a(List<ImageItem> list) {
        this.c = list;
    }

    public int getCount() {
        int i = 0;
        if (this.c != null) {
            i = this.c.size();
        }
        if (this.f) {
            return i + 1;
        }
        return i;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        f$a f_a;
        if (view == null) {
            f_a = new f$a();
            view = View.inflate(this.b, R.layout.item_image_grid, null);
            f_a.a = (ImageView) view.findViewById(R.id.image);
            f_a.b = (RelativeLayout) view.findViewById(R.id.select_image_video_mark);
            f_a.c = (RelativeLayout) view.findViewById(R.id.select_image_num_layout);
            f_a.d = (TextView) view.findViewById(R.id.select_image_num);
            LayoutParams layoutParams = (LayoutParams) f_a.a.getLayoutParams();
            int a = (com.budejie.www.adapter.b.a.a - d.a(this.b, 8.0f)) / 3;
            layoutParams.width = a;
            layoutParams.height = a;
            f_a.a.setLayoutParams(layoutParams);
            view.setTag(f_a);
        } else {
            f_a = (f$a) view.getTag();
        }
        f_a.c.setVisibility(8);
        if (!this.f) {
            a(f_a, (ImageItem) this.c.get(i));
        } else if (i == 0) {
            f_a.a.setImageResource(R.drawable.item_carema_state);
        } else {
            a(f_a, (ImageItem) this.c.get(i - 1));
        }
        return view;
    }

    private void a(f$a f_a, ImageItem imageItem) {
        f_a.a.setTag(imageItem.imagePath);
        this.e.a(f_a.a, imageItem.thumbnailPath, imageItem.imagePath, this.g);
        f_a.c.setTag(imageItem);
        if ("video/mp4".equals(imageItem.mimeType)) {
            f_a.b.setVisibility(0);
        } else if (!"image/gif".equals(imageItem.mimeType)) {
            f_a.b.setVisibility(8);
            f_a.c.setVisibility(0);
            if (imageItem.isSelected) {
                f_a.c.setSelected(true);
                f_a.d.setText(imageItem.selectedNum);
                return;
            }
            f_a.c.setSelected(false);
            f_a.d.setText(imageItem.selectedNum);
        }
    }
}
