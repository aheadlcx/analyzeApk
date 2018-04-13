package com.budejie.www.activity.a;

import android.app.Activity;
import android.text.TextUtils;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.c;
import com.budejie.www.adapter.d;
import com.budejie.www.adapter.d.h;
import com.budejie.www.adapter.e.a;
import com.budejie.www.adapter.g.b;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.an;

public class e<T extends c> extends c<c> {
    private Activity a;
    private a b;
    private a c;
    private boolean e;
    private com.budejie.www.adapter.g.c f;
    private com.budejie.www.adapter.g.c g;
    private com.budejie.www.adapter.g.c h;
    private com.budejie.www.adapter.g.c i;
    private com.budejie.www.adapter.g.c j = new com.budejie.www.adapter.g.c.a().a(5).b(8).i(false).a();
    private com.budejie.www.adapter.g.c k = new com.budejie.www.adapter.g.c.a().a(1).b(2).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c l = new com.budejie.www.adapter.g.c.a().a(1).b(7).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c m = new com.budejie.www.adapter.g.c.a().a(1).b(6).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c n = new com.budejie.www.adapter.g.c.a().a(1).b(5).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c o = new com.budejie.www.adapter.g.c.a().a(this.k).f(true).a();
    private com.budejie.www.adapter.g.c p = new com.budejie.www.adapter.g.c.a().a(this.l).f(true).a();
    private com.budejie.www.adapter.g.c q = new com.budejie.www.adapter.g.c.a().a(this.m).f(true).a();
    private com.budejie.www.adapter.g.c r = new com.budejie.www.adapter.g.c.a().a(this.n).f(true).a();

    public boolean a() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
        if (z) {
            this.f = new com.budejie.www.adapter.g.c.a().a(2).b(2).h(true).a(true).b(true).c(true).a();
            this.h = new com.budejie.www.adapter.g.c.a().a(2).b(6).h(true).a(true).b(true).c(true).a();
            this.g = new com.budejie.www.adapter.g.c.a().a(2).b(7).h(true).a(true).b(true).c(true).a();
            this.i = new com.budejie.www.adapter.g.c.a().a(2).b(5).h(true).a(true).b(true).c(true).a();
            return;
        }
        this.f = this.k;
        this.g = this.l;
        this.h = this.m;
        this.i = this.n;
    }

    public e(Activity activity, a aVar, a aVar2) {
        this.a = activity;
        this.b = aVar;
        this.c = aVar2;
    }

    protected d a(c cVar, int i) {
        a aVar;
        com.budejie.www.adapter.g.c cVar2;
        com.budejie.www.adapter.g.c cVar3;
        com.budejie.www.adapter.g.c cVar4;
        Object obj;
        int i2;
        ListItemObject listItemObject;
        com.budejie.www.adapter.g.c cVar5 = this.f;
        com.budejie.www.adapter.g.c cVar6 = this.g;
        com.budejie.www.adapter.g.c cVar7 = this.h;
        com.budejie.www.adapter.g.c cVar8 = this.i;
        a aVar2 = this.c;
        if (cVar instanceof com.budejie.www.activity.mycomment.d) {
            cVar = an.a((com.budejie.www.activity.mycomment.d) cVar);
            cVar6 = this.j;
            cVar7 = this.j;
            cVar8 = this.j;
            com.budejie.www.adapter.g.c cVar9 = this.j;
            aVar = this.b;
            cVar2 = cVar8;
            cVar3 = cVar7;
            cVar4 = cVar6;
            obj = null;
            Object obj2 = null;
            i2 = 13;
            cVar5 = cVar9;
            listItemObject = cVar;
        } else {
            ListItemObject listItemObject2 = (ListItemObject) cVar;
            int i3;
            if (listItemObject2.getShareInfo() != null) {
                cVar6 = this.o;
                cVar7 = this.p;
                cVar8 = this.q;
                cVar5 = this.r;
                cVar3 = cVar7;
                cVar4 = cVar6;
                i3 = 1;
                i2 = 12;
                cVar2 = cVar8;
                int i4 = 1;
                aVar = aVar2;
                listItemObject = listItemObject2;
            } else {
                cVar3 = cVar6;
                cVar4 = cVar5;
                cVar5 = cVar8;
                i2 = 12;
                cVar2 = cVar7;
                obj = null;
                i3 = 1;
                aVar = aVar2;
                listItemObject = listItemObject2;
            }
        }
        RowType rowType;
        if ("61".equals(listItemObject.getType())) {
            rowType = obj2 != null ? obj != null ? RowType.SHARE_REPOST_ROW : RowType.REPOST_ROW : RowType.COMMENT_REPORT_ROW;
            return new h(this.a, cVar3, new b(aVar, listItemObject, i, rowType, i2));
        } else if ("51".equals(listItemObject.getType()) && listItemObject.getRichObject() != null) {
            rowType = obj2 != null ? obj != null ? RowType.SHARE_RICH_ROW : RowType.RICH_ROW : RowType.COMMENT_RICH_TXT_ROW;
            return new h(this.a, cVar2, new b(aVar, listItemObject, i, rowType, i2));
        } else if ("29".equals(listItemObject.getType()) || (TextUtils.isEmpty(listItemObject.getVoiceUri()) && TextUtils.isEmpty(listItemObject.getVideouri()) && !"1".equals(listItemObject.getIs_gif()) && TextUtils.isEmpty(listItemObject.getImgUrl()))) {
            rowType = obj2 != null ? obj != null ? RowType.SHARE_TXT_ROW : RowType.TXT_ROW : RowType.COMMENT_TXT_ROW;
            return new h(this.a, cVar4, new b(aVar, listItemObject, i, rowType, i2));
        } else {
            rowType = obj2 != null ? obj != null ? RowType.SHARE_IMAGE_VOICE_VIDEO_ROW : RowType.IMAGE_VOICE_VIDEO_ROW : RowType.COMMENT_SOUND_ROW;
            return new h(this.a, cVar5, new b(aVar, listItemObject, i, rowType, i2));
        }
    }
}
