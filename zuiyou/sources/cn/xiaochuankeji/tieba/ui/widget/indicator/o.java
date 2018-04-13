package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import java.util.HashMap;

public class o extends c {
    private ViewPager a;
    private int b;
    private String[] c;
    private HashMap<String, Integer> d = new HashMap();

    public o(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            this.b = 0;
            return;
        }
        this.b = strArr.length;
        this.c = strArr;
    }

    public void a(ViewPager viewPager) {
        this.a = viewPager;
    }

    public void c() {
        this.a = null;
    }

    public int a() {
        return this.b;
    }

    public j a(Context context, final int i) {
        Object kVar = new k(context);
        kVar.setText(this.c[i]);
        kVar.setNormalColor(a.a().a((int) R.color.CT_2));
        kVar.setSelectedColor(a.a().a((int) R.color.CM));
        if (this.a == null || this.a.getCurrentItem() != i) {
            kVar.b(i, a());
        } else {
            kVar.a(i, a());
        }
        Integer num = (Integer) this.d.get(this.c[i]);
        if (num != null) {
            kVar.setCrumbCount(num.intValue());
        }
        kVar.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ o b;

            public void onClick(View view) {
                if (this.b.a != null) {
                    this.b.a.setCurrentItem(i);
                }
            }
        });
        return kVar;
    }

    public h a(Context context) {
        h lVar = new l(context);
        lVar.setMode(2);
        lVar.setColors(Integer.valueOf(a.a().a((int) R.color.CM)));
        float a = i.a(context.getResources(), 3.0f);
        lVar.setLineWidth(i.a(context.getResources(), 9.0f) * 1.0f);
        lVar.setLineHeight(a);
        lVar.setRoundRadius(a);
        return lVar;
    }

    public float a(int i) {
        return 1.0f;
    }

    public void a(String str, int i) {
        this.d.put(str, Integer.valueOf(i));
        b();
    }

    public void a(int i, String str) {
        if (i < this.c.length) {
            this.c[i] = str;
            b();
        }
    }
}
