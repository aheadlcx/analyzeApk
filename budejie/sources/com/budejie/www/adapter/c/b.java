package com.budejie.www.adapter.c;

import android.app.Activity;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.c;
import com.budejie.www.adapter.d;
import com.budejie.www.adapter.d.e;
import com.budejie.www.adapter.d.f;
import com.budejie.www.adapter.d.g;
import com.budejie.www.adapter.d.h;
import com.budejie.www.adapter.d.i;
import com.budejie.www.adapter.d.j;
import com.budejie.www.adapter.d.k;
import com.budejie.www.adapter.d.l;
import com.budejie.www.adapter.d.m;
import com.budejie.www.adapter.e.a;
import com.budejie.www.bean.ListItemObject;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.MobclickAgent;
import java.lang.ref.WeakReference;

public class b extends c<ListItemObject> {
    private Activity a;
    private a b;
    private com.budejie.www.adapter.g.a.a c;
    private com.budejie.www.adapter.g.a.a e;
    private int f;
    private com.budejie.www.adapter.g.c g = new com.budejie.www.adapter.g.c.a().a(1).b(1).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c h = new com.budejie.www.adapter.g.c.a().a(1).b(2).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c i = new com.budejie.www.adapter.g.c.a().a(1).b(6).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c j = new com.budejie.www.adapter.g.c.a().a(1).b(7).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c k = new com.budejie.www.adapter.g.c.a().a(1).b(3).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c l = new com.budejie.www.adapter.g.c.a().a(1).b(4).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c m = new com.budejie.www.adapter.g.c.a().a(1).b(5).h(true).a(false).b(true).a();
    private com.budejie.www.adapter.g.c n = new com.budejie.www.adapter.g.c.a().a(0).b(5).d(true).e(true).a();
    private com.budejie.www.adapter.g.c o = new com.budejie.www.adapter.g.c.a().a(0).b(10).i(false).a();
    private SparseArray<WeakReference<View>> p = new SparseArray();
    private SparseBooleanArray q = new SparseBooleanArray();

    public b(Activity activity, a aVar, int i) {
        this.a = activity;
        this.b = aVar;
        this.f = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        if (view == null) {
            int c = ((d) this.d.get(i)).c();
            if (!this.q.get(c)) {
                view2 = (View) ((WeakReference) this.p.get(c)).get();
                if (view2 != null) {
                    this.q.put(c, true);
                }
                return super.getView(i, view2, viewGroup);
            }
        }
        view2 = view;
        return super.getView(i, view2, viewGroup);
    }

    public d a(ListItemObject listItemObject, int i) {
        d hVar;
        if (!listItemObject.isIs_ad() || listItemObject.getAdItem() == null) {
            if (listItemObject.isIs_ad()) {
                hVar = new h(this.a, this.n, new com.budejie.www.adapter.g.b(this.c, listItemObject, i, RowType.AD_ROW, this.f));
            } else if (listItemObject.isInsertRemind()) {
                hVar = new k(this.a, this.b, listItemObject, i);
            } else if ("61".equals(listItemObject.getType())) {
                hVar = new h(this.a, this.j, new com.budejie.www.adapter.g.b(this.b, listItemObject, i, RowType.REPOST_ROW, this.f));
            } else if (1 == listItemObject.getNewItemType()) {
                MobclickAgent.onEvent(this.a, "E01-A04", "登录出现次数");
                hVar = new f(this.a, this.b, listItemObject, i);
            } else if (2 == listItemObject.getNewItemType()) {
                MobclickAgent.onEvent(this.a, "E01-A04", "分享app出现次数");
                hVar = new j(this.a, this.b, listItemObject, i);
            } else if (4 == listItemObject.getNewItemType()) {
                MobclickAgent.onEvent(this.a, "E01-A04", "绑定通讯录出现次数");
                hVar = new e(this.a, this.b, listItemObject, i);
            } else if (6 == listItemObject.getNewItemType() || 5 == listItemObject.getNewItemType() || 7 == listItemObject.getNewItemType()) {
                MobclickAgent.onEvent(this.a, "E01-A04", "用户资料收集完善出现次数");
                Object mVar = new m(this.a, this.b, listItemObject, i, listItemObject.getNewItemType());
            } else if ("51".equals(listItemObject.getType()) && listItemObject.getRichObject() != null) {
                hVar = new h(this.a, this.i, new com.budejie.www.adapter.g.b(this.b, listItemObject, i, RowType.RICH_ROW, this.f));
            } else if (8 == listItemObject.getNewItemType()) {
                MobclickAgent.onEvent(this.a, "E01_A08", "运营位展示次数:" + listItemObject.getOperation().order_id);
                hVar = new i(this.a, this.b, listItemObject, i);
            } else if ("29".equals(listItemObject.getType())) {
                hVar = new h(this.a, a(this.h, listItemObject), new com.budejie.www.adapter.g.b(this.b, listItemObject, i, a(RowType.TXT_ROW, listItemObject), this.f));
            } else if ("31".equals(listItemObject.getType())) {
                hVar = new h(this.a, b(this.k, listItemObject), new com.budejie.www.adapter.g.b(this.b, listItemObject, i, b(RowType.SOUND_ROW, listItemObject), this.f));
            } else if ("41".equals(listItemObject.getType())) {
                hVar = new h(this.a, b(this.l, listItemObject), new com.budejie.www.adapter.g.b(this.b, listItemObject, i, b(RowType.VIDEO_ROW, listItemObject), this.f));
            } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(listItemObject.getType())) {
                hVar = new h(this.a, b(this.g, listItemObject), new com.budejie.www.adapter.g.b(this.b, listItemObject, i, b(RowType.IMAGE_ROW, listItemObject), this.f));
            } else {
                hVar = new l(this.a, this.b, listItemObject, i);
            }
        } else if (listItemObject.getAdItem().getResType() == null || !listItemObject.getAdItem().getResType().equals("live")) {
            if (this.e == null) {
                this.e = new b$1(this);
            }
            hVar = new g(this.a, this.n, new com.budejie.www.adapter.g.b(this.e, listItemObject, i, RowType.AD_SDK_ROW, this.f));
        } else {
            hVar = new g(this.a, this.o, new com.budejie.www.adapter.g.b(this.e, listItemObject, i, RowType.AD_LIVE_ROW, this.f));
        }
        int c = hVar.c();
        WeakReference weakReference = (WeakReference) this.p.get(c);
        if (weakReference == null || weakReference.get() == null) {
            this.p.put(c, new WeakReference(((com.budejie.www.adapter.a) hVar).b()));
        }
        return hVar;
    }

    public RowType a(RowType rowType, ListItemObject listItemObject) {
        if (listItemObject.ismHistoryTodayHotPost()) {
            return RowType.HISTORY_HOT_POST_ROW;
        }
        return rowType;
    }

    public RowType b(RowType rowType, ListItemObject listItemObject) {
        if (listItemObject.ismHistoryTodayHotPost()) {
            return RowType.HISTORY_HOT_POST_ROW;
        }
        if (this.f == 10) {
            return RowType.IMAGE_VOICE_VIDEO_ROW;
        }
        return rowType;
    }

    public com.budejie.www.adapter.g.c a(com.budejie.www.adapter.g.c cVar, ListItemObject listItemObject) {
        if (!listItemObject.ismHistoryTodayHotPost()) {
            return cVar;
        }
        if (listItemObject.getmMultiHistoryData() != null) {
            return new com.budejie.www.adapter.g.c.a().a(4).b(9).a();
        }
        return new com.budejie.www.adapter.g.c.a().a(4).b(cVar.b).h(cVar.d).a(cVar.e).b(cVar.f).a();
    }

    public com.budejie.www.adapter.g.c b(com.budejie.www.adapter.g.c cVar, ListItemObject listItemObject) {
        if (listItemObject.ismHistoryTodayHotPost()) {
            return new com.budejie.www.adapter.g.c.a().a(4).b(cVar.b).h(cVar.d).a(cVar.e).b(cVar.f).a();
        }
        if (this.f == 10) {
            return this.m;
        }
        return cVar;
    }
}
