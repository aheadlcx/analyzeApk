package cn.xiaochuankeji.tieba.ui.widget.indexablerv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import cn.xiaochuankeji.tieba.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

class d extends View {
    private int a;
    private float b;
    private List<String> c = new ArrayList();
    private HashMap<String, Integer> d = new HashMap();
    private ArrayList<b> e;
    private int f;
    private float g;
    private Paint h = new Paint(1);
    private Paint i = new Paint(1);

    public d(Context context) {
        super(context);
    }

    void a(Drawable drawable, int i, int i2, float f, float f2) {
        if (VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
        this.b = f2;
        this.h.setColor(i);
        this.h.setTextAlign(Align.CENTER);
        this.h.setTextSize(f);
        this.i.setTextAlign(Align.CENTER);
        this.i.setTextSize(((float) ((int) TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()))) + f);
        this.i.setColor(i2);
    }

    protected void onMeasure(int i, int i2) {
        MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i2);
        if (this.c.size() > 0) {
            this.a = (int) (((((float) (this.c.size() - 1)) * this.h.getTextSize()) + this.i.getTextSize()) + (((float) (this.c.size() + 1)) * this.b));
        }
        if (this.a > size) {
            this.a = size;
        }
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(this.a, 1073741824));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.c.size() != 0) {
            this.g = ((float) getHeight()) / ((float) this.c.size());
            int i = 0;
            while (i < this.c.size()) {
                canvas.drawText((String) this.c.get(i), (float) (getWidth() / 2), (this.g * ((float) i)) + (this.g * 0.85f), this.f == i ? this.i : this.h);
                i++;
            }
        }
    }

    int a(float f) {
        if (this.c.size() <= 0) {
            return -1;
        }
        int i = (int) (f / this.g);
        if (i < 0) {
            return 0;
        }
        if (i > this.c.size() - 1) {
            return this.c.size() - 1;
        }
        return i;
    }

    int a() {
        return this.f;
    }

    void a(int i) {
        this.f = i;
        invalidate();
    }

    int b() {
        String str = (String) this.c.get(this.f);
        if (this.d.containsKey(str)) {
            return ((Integer) this.d.get(str)).intValue();
        }
        return -1;
    }

    List<String> c() {
        return this.c;
    }

    void a(boolean z, ArrayList<b> arrayList) {
        Collection arrayList2;
        this.e = arrayList;
        this.c.clear();
        this.d.clear();
        if (z) {
            this.c = Arrays.asList(getResources().getStringArray(R.array.index_letter));
            this.c = new ArrayList(this.c);
            arrayList2 = new ArrayList();
        } else {
            arrayList2 = null;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            b bVar = (b) arrayList.get(i);
            if (bVar.g() == 2147483646 || bVar.b() == null) {
                CharSequence a = bVar.a();
                if (!TextUtils.isEmpty(a)) {
                    if (!z) {
                        this.c.add(a);
                    } else if ("#".equals(a)) {
                        this.c.add("#");
                    } else if (this.c.indexOf(a) < 0) {
                        if (bVar.h() == 1 && arrayList2.indexOf(a) < 0) {
                            arrayList2.add(a);
                        } else if (bVar.h() == 2) {
                            this.c.add(a);
                        }
                    }
                    if (!this.d.containsKey(a)) {
                        this.d.put(a, Integer.valueOf(i));
                    }
                }
            }
        }
        if (z) {
            this.c.addAll(0, arrayList2);
        }
        requestLayout();
    }

    void b(int i) {
        if (this.e != null && this.e.size() > i && i >= 0) {
            int indexOf = this.c.indexOf(((b) this.e.get(i)).a());
            if (this.f != indexOf && indexOf >= 0) {
                this.f = indexOf;
                invalidate();
            }
        }
    }
}
