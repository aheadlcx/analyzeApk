package com.budejie.www.activity.label;

import android.app.Activity;
import android.text.TextUtils;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.c;
import com.budejie.www.adapter.d;
import com.budejie.www.adapter.d.h;
import com.budejie.www.adapter.d.k;
import com.budejie.www.adapter.e.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;

public class l extends c<ListItemObject> {
    Activity a;
    a b;
    private com.budejie.www.adapter.g.c c = new com.budejie.www.adapter.g.c.a().a(1).b(2).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c e = new com.budejie.www.adapter.g.c.a().a(1).b(7).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c f = new com.budejie.www.adapter.g.c.a().a(1).b(6).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c g = new com.budejie.www.adapter.g.c.a().a(1).b(5).h(true).a(false).b(true).a();

    public l(Activity activity, a aVar) {
        this.a = activity;
        this.b = aVar;
    }

    protected d a(ListItemObject listItemObject, int i) {
        if (listItemObject.getPlaceholder() == 1) {
            return new j(this.a, this.b, listItemObject, i);
        }
        if (listItemObject.getPlaceholder() == 2) {
            return new j(this.a, this.b, listItemObject, i);
        }
        if (listItemObject.isInsertRemind()) {
            return new k(this.a, this.b, listItemObject, i);
        }
        if ("61".equals(listItemObject.getType())) {
            return new h(this.a, this.e, new b(this.b, listItemObject, i, RowType.REPOST_ROW, 0));
        } else if ("51".equals(listItemObject.getType()) && listItemObject.getRichObject() != null) {
            return new h(this.a, this.f, new b(this.b, listItemObject, i, RowType.RICH_ROW, 0));
        } else if ("29".equals(listItemObject.getType()) || (TextUtils.isEmpty(listItemObject.getVoiceUri()) && TextUtils.isEmpty(listItemObject.getVideouri()) && !"1".equals(listItemObject.getIs_gif()) && TextUtils.isEmpty(listItemObject.getImgUrl()))) {
            return new h(this.a, this.c, new b(this.b, listItemObject, i, RowType.TXT_ROW, 0));
        } else {
            return new h(this.a, this.g, new b(this.b, listItemObject, i, RowType.IMAGE_VOICE_VIDEO_ROW, 0));
        }
    }
}
