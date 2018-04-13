package com.budejie.www.adapter.c;

import android.app.Activity;
import android.text.TextUtils;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.c;
import com.budejie.www.adapter.d;
import com.budejie.www.adapter.d.h;
import com.budejie.www.adapter.d.l;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.util.an;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;

public class a extends c<ListItemObject> {
    private Activity a;
    private com.budejie.www.adapter.e.a b;
    private int c;
    private com.budejie.www.adapter.g.c e;
    private com.budejie.www.adapter.g.c f;
    private com.budejie.www.adapter.g.c g;
    private com.budejie.www.adapter.g.c h;
    private com.budejie.www.adapter.g.c i;
    private com.budejie.www.adapter.g.c j;

    public a(Activity activity, com.budejie.www.adapter.e.a aVar) {
        this.j = new com.budejie.www.adapter.g.c.a().a(0).b(5).d(true).a();
        this.a = activity;
        this.b = aVar;
        this.e = new com.budejie.www.adapter.g.c.a().a(1).b(2).a(false).b(true).a();
        this.f = new com.budejie.www.adapter.g.c.a().a(1).b(2).a(false).b(true).a();
        this.g = new com.budejie.www.adapter.g.c.a().a(1).b(6).a(false).b(true).a();
        this.h = new com.budejie.www.adapter.g.c.a().a(1).b(7).a(false).b(true).a();
        this.i = new com.budejie.www.adapter.g.c.a().a(1).b(5).a(false).b(true).a();
    }

    public a(Activity activity, com.budejie.www.adapter.e.a aVar, int i) {
        this(activity, aVar);
        this.c = i;
        if (11 == i) {
            this.e = new com.budejie.www.adapter.g.c.a().a(2).b(2).a(false).b(true).c(true).a();
            this.f = new com.budejie.www.adapter.g.c.a().a(2).b(2).a(false).g(true).a();
            this.g = new com.budejie.www.adapter.g.c.a().a(2).b(6).a(false).b(true).c(true).a();
            this.h = new com.budejie.www.adapter.g.c.a().a(2).b(7).a(false).b(true).c(true).a();
            this.i = new com.budejie.www.adapter.g.c.a().a(2).b(5).a(false).b(true).c(true).a();
        }
    }

    public a(Activity activity, com.budejie.www.adapter.e.a aVar, boolean z) {
        this.j = new com.budejie.www.adapter.g.c.a().a(0).b(5).d(true).a();
        this.a = activity;
        this.b = aVar;
        this.e = new com.budejie.www.adapter.g.c.a().a(1).b(2).a(false).b(true).h(z).a();
        this.f = new com.budejie.www.adapter.g.c.a().a(1).b(2).a(false).b(true).h(z).a();
        this.g = new com.budejie.www.adapter.g.c.a().a(1).b(6).a(false).b(true).h(z).a();
        this.h = new com.budejie.www.adapter.g.c.a().a(1).b(7).a(false).b(true).h(z).a();
        this.i = new com.budejie.www.adapter.g.c.a().a(1).b(5).a(false).b(true).h(z).a();
    }

    public void c(List<TouGaoItem> list) {
        a(e(list));
    }

    public void d(List<TouGaoItem> list) {
        b(e(list));
    }

    private List<ListItemObject> e(List<TouGaoItem> list) {
        List<ListItemObject> arrayList = new ArrayList();
        for (TouGaoItem touGaoItem : list) {
            if (touGaoItem.getDraftBean() != null) {
                arrayList.add(a(an.a(touGaoItem.getDraftBean())));
            } else {
                arrayList.add(a(an.a(touGaoItem)));
            }
        }
        return arrayList;
    }

    private ListItemObject a(ListItemObject listItemObject) {
        String imgUrl = listItemObject.getImgUrl();
        if (!TextUtils.isEmpty(imgUrl) && Scheme.ofUri(imgUrl) == Scheme.UNKNOWN) {
            listItemObject.setImgUrl(Scheme.FILE.wrap(imgUrl));
        }
        return listItemObject;
    }

    public d a(ListItemObject listItemObject, int i) {
        if ("61".equals(listItemObject.getType())) {
            return new h(this.a, this.h, new b(this.b, listItemObject, i, RowType.REPOST_ROW, this.c));
        } else if ("51".equals(listItemObject.getType()) && listItemObject.getRichObject() != null) {
            return new h(this.a, this.g, new b(this.b, listItemObject, i, RowType.RICH_ROW, this.c));
        } else if ("29".equals(listItemObject.getType())) {
            return new h(this.a, this.e, new b(this.b, listItemObject, i, RowType.TXT_ROW, this.c));
        } else if ("31".equals(listItemObject.getType()) || "41".equals(listItemObject.getType()) || Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(listItemObject.getType())) {
            return new h(this.a, this.i, new b(this.b, listItemObject, i, RowType.IMAGE_VOICE_VIDEO_ROW, this.c));
        } else if (Constants.VIA_ACT_TYPE_TWENTY_EIGHT.equals(listItemObject.getType())) {
            return new h(this.a, this.f, new b(this.b, listItemObject, i, RowType.TXT_LINK, this.c));
        } else if (TextUtils.isEmpty(listItemObject.getType())) {
            return null;
        } else {
            return new l(this.a, this.b, listItemObject, i);
        }
    }
}
