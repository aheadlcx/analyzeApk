package com.budejie.www.adapter.d;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.f.b;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.ah;

public class c extends a implements OnClickListener, b {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;

    public /* synthetic */ Object d() {
        return a();
    }

    public c(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
    }

    public View b() {
        c$a c_a = new c$a(this);
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.post_detail_head_plate_layout, null);
        c$a.a(c_a, (LinearLayout) viewGroup.findViewById(R.id.post_detail_plate));
        c$a.a(c_a, (AsyncImageView) viewGroup.findViewById(R.id.post_detail_plate_header));
        c$a.a(c_a, (TextView) viewGroup.findViewById(R.id.post_detail_plate_name));
        c$a.b(c_a, (TextView) viewGroup.findViewById(R.id.post_detail_plate_sub_num));
        c$a.c(c_a, (TextView) viewGroup.findViewById(R.id.post_detail_plate_post_num));
        c$a.a(c_a, viewGroup.findViewById(R.id.post_detail_plate_sub_post_divider));
        c$a.d(c_a, (TextView) viewGroup.findViewById(R.id.post_detail_plate_info));
        c$a.e(c_a, (TextView) viewGroup.findViewById(R.id.post_detail_plate_btn));
        viewGroup.setTag(c_a);
        return viewGroup;
    }

    public int c() {
        return RowType.POST_DETAIL_HEAD_ROW.ordinal();
    }

    public void a(com.budejie.www.adapter.b bVar) {
        c$a c_a = (c$a) bVar;
        PlateBean plateBean = this.a.getPlateBean(0);
        int a = ah.a(this.b);
        if (plateBean == null || a < plateBean.display_level || TextUtils.isEmpty(plateBean.theme_id)) {
            c$a.a(c_a).setVisibility(8);
            return;
        }
        c$a.a(c_a).setVisibility(0);
        c$a.b(c_a).setAsyncCacheImage(plateBean.image_list, R.drawable.label_default_icon);
        c$a.c(c_a).setText(plateBean.theme_name);
        if (plateBean.sub_number > 0) {
            c$a.d(c_a).setText(ah.a(plateBean));
        } else {
            c$a.e(c_a).setVisibility(8);
        }
        if (plateBean.post_num > 0) {
            c$a.f(c_a).setText(ah.a(plateBean.post_num, this.b.getResources().getString(R.string.million_text)) + "帖子");
        } else {
            c$a.e(c_a).setVisibility(8);
        }
        if (TextUtils.isEmpty(plateBean.info)) {
            c$a.g(c_a).setVisibility(8);
        } else {
            c$a.g(c_a).setText(plateBean.info);
            c$a.g(c_a).setVisibility(0);
        }
        c$a.a(c_a).setOnClickListener(this);
    }

    public ListItemObject a() {
        return this.a;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_detail_plate:
                Intent intent = new Intent(this.b, CommonLabelActivity.class);
                PlateBean plateBean = this.a.getPlateBean(0);
                if (plateBean != null) {
                    intent.putExtra("theme_name", plateBean.theme_name);
                    intent.putExtra("theme_id", plateBean.theme_id);
                    intent.putExtra("colum_set", plateBean.colum_set);
                    this.b.startActivity(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void b_() {
    }
}
