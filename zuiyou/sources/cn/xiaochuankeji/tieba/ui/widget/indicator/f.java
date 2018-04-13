package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.utils.i;
import java.util.ArrayList;

public class f extends c {
    private ViewPager a;
    private ArrayList<NavigatorTag> b = new ArrayList();

    public void a(ArrayList<NavigatorTag> arrayList) {
        this.b.clear();
        this.b.addAll(arrayList);
        b();
    }

    public void a(ViewPager viewPager) {
        this.a = viewPager;
    }

    public void c() {
        this.a = null;
    }

    public int a() {
        return this.b.size();
    }

    public j a(Context context, final int i) {
        NavigatorTag navigatorTag = (NavigatorTag) this.b.get(i);
        Object eVar = new e(context);
        eVar.setText(navigatorTag.name);
        eVar.setNormalColor(a.a().a((int) R.color.CT_2));
        eVar.setSelectedColor(a.a().a((int) R.color.CM));
        if (this.a == null || this.a.getCurrentItem() != i) {
            eVar.b(i, a());
        } else {
            eVar.a(i, a());
        }
        eVar.setCrumbCount(cn.xiaochuankeji.tieba.background.a.q().b(navigatorTag.id));
        eVar.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f b;

            public void onClick(View view) {
                if (this.b.a != null) {
                    try {
                        this.b.a.setCurrentItem(i, false);
                    } catch (Throwable e) {
                        cn.xiaochuankeji.tieba.analyse.a.a(e);
                    }
                }
            }
        });
        return eVar;
    }

    public h a(Context context) {
        h lVar = new l(context);
        lVar.setMode(2);
        lVar.setColors(Integer.valueOf(a.a().a((int) R.color.CM)));
        float a = i.a(context.getResources(), 2.6f);
        lVar.setLineWidth(i.a(context.getResources(), 7.6f) * 1.0f);
        lVar.setLineHeight(a);
        lVar.setRoundRadius(a);
        return lVar;
    }

    public float a(int i) {
        return 1.0f;
    }
}
