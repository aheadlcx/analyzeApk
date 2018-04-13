package com.budejie.www.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.a;
import com.budejie.www.util.ac;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class e extends Adapter<e$a> {
    private List<ListItemObject> a = new ArrayList();
    private Context b;
    private final int c;
    private final int d;
    private final int e;
    private int f;
    private ColorDrawable g;
    private int h;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        a((e$a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return a(viewGroup, i);
    }

    public e(Context context, List<ListItemObject> list) {
        int color;
        this.b = context;
        this.a = list;
        this.c = an.x(this.b) / 2;
        this.d = this.c;
        this.e = an.a(this.b, 319);
        this.f = ai.a(this.b);
        this.g = new ColorDrawable();
        ColorDrawable colorDrawable = this.g;
        if (this.f == 0) {
            color = this.b.getResources().getColor(R.color.recyclerview_item_load_day_background);
        } else {
            color = this.b.getResources().getColor(R.color.recyclerview_item_load_night_background);
        }
        colorDrawable.setColor(color);
    }

    public void a(int i) {
        if (i >= 0) {
            this.h = i;
        }
    }

    public void a(List<ListItemObject> list) {
        if (!a.a(list) && this.a != null) {
            int size = this.a.size();
            if (size > 0) {
                this.a.addAll(list);
                notifyItemRangeInserted(size + this.h, list.size());
                return;
            }
            this.a.addAll(list);
            notifyDataSetChanged();
        }
    }

    public e$a a(ViewGroup viewGroup, int i) {
        return new e$a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.staggered_list_item, viewGroup, false));
    }

    public void a(e$a e_a, int i) {
        ListItemObject listItemObject = (ListItemObject) this.a.get(i);
        if (listItemObject != null && !TextUtils.isEmpty(listItemObject.getWid())) {
            int width = listItemObject.getWidth();
            int height = listItemObject.getHeight();
            if (width != 0) {
                CharSequence type = listItemObject.getType();
                if (!TextUtils.isEmpty(type)) {
                    Object obj;
                    int i2;
                    String str;
                    boolean equals = "1".equals(listItemObject.getIs_gif());
                    int i3 = (height * this.c) / width;
                    e_a.f.setVisibility(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type) ? 0 : 8);
                    if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type)) {
                        e_a.e.setVisibility(i3 >= this.e ? 8 : 8);
                        CharSequence content = listItemObject.getContent();
                        if (TextUtils.isEmpty(content)) {
                            e_a.f.setText("");
                        } else {
                            e_a.f.setText(content);
                        }
                    } else {
                        e_a.e.setVisibility(8);
                    }
                    Object obj2;
                    if ("41".equals(type)) {
                        if (i3 <= this.d) {
                            i3 = this.c;
                            obj = 1;
                            obj2 = null;
                        }
                        obj = null;
                        obj2 = null;
                    } else {
                        if (i3 >= this.e) {
                            i3 = this.e;
                            obj = null;
                            i2 = 1;
                        }
                        obj = null;
                        obj2 = null;
                    }
                    LayoutParams layoutParams = (LayoutParams) e_a.a.getLayoutParams();
                    layoutParams.height = i3;
                    layoutParams.width = this.c;
                    e_a.a.setLayoutParams(layoutParams);
                    String imgUrl = listItemObject.getImgUrl();
                    String cnd_img = listItemObject.getCnd_img();
                    if (!equals || r1 == null) {
                        str = imgUrl;
                    } else {
                        str = listItemObject.getGifFistFrame();
                    }
                    if (obj != null) {
                        i.b(this.b).a(com.lt.a.a(this.b).a(str)).a(this.g).b(this.g).d().a(DiskCacheStrategy.SOURCE).a(this.c, i3).a().a(e_a.a);
                    } else {
                        e_a.a.setStaggeredFoldImage(str, cnd_img, this.c, i3, this.g, null, this.f);
                    }
                    i2 = this.c;
                    if (obj != null) {
                        width = i3 / 2;
                    } else {
                        width = i3;
                    }
                    a(e_a, listItemObject, i2, width);
                    if ("0".equals(listItemObject.getComment()) || Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(listItemObject.getType())) {
                        e_a.c.setVisibility(8);
                    } else {
                        e_a.c.setVisibility(0);
                        width = this.c;
                        if (obj != null) {
                            i3 /= 2;
                        }
                        b(e_a, listItemObject, width, i3);
                        e_a.b.setText(listItemObject.getComment());
                    }
                    long j = 0;
                    Object videotime = listItemObject.getVideotime();
                    if (!TextUtils.isEmpty(videotime)) {
                        try {
                            j = Long.parseLong(videotime) * 1000;
                        } catch (NumberFormatException e) {
                            j = 0;
                        }
                    }
                    if (j == 0 || Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(listItemObject.getType())) {
                        e_a.d.setVisibility(8);
                        return;
                    }
                    e_a.d.setVisibility(0);
                    e_a.g.setText(ac.a(j));
                }
            }
        }
    }

    private void a(e$a e_a, ListItemObject listItemObject, int i, int i2) {
        if (e_a != null && listItemObject != null && this.b != null) {
            e_a.a.setOnClickListener(new e$1(this, listItemObject));
        }
    }

    private void b(e$a e_a, ListItemObject listItemObject, int i, int i2) {
        if (e_a != null && listItemObject != null && this.b != null) {
            e_a.c.setOnClickListener(new e$2(this, listItemObject));
        }
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public int getItemCount() {
        return a.a(this.a) ? 0 : this.a.size();
    }
}
