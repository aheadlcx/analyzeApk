package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.ArrayList;
import java.util.List;

public class MultipleImgFrameLayoutContainer extends FrameLayout {
    private boolean a = false;
    private int b;
    private List<d> c;

    public interface a {
        void a(int i);
    }

    public MultipleImgFrameLayoutContainer(Context context) {
        super(context);
        b();
    }

    public MultipleImgFrameLayoutContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public MultipleImgFrameLayoutContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        this.b = e.a(4.0f);
        this.c = new ArrayList();
    }

    public void a() {
        this.a = true;
    }

    public void setData(ArrayList<ServerImage> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i < getChildCount()) {
                View childAt = getChildAt(i);
                d dVar = (d) childAt.getTag();
                childAt.setVisibility(0);
                dVar.a((ServerImage) arrayList.get(i));
            } else {
                d dVar2 = new d(getContext());
                dVar2.a((ServerImage) arrayList.get(i));
                View f_ = dVar2.f_();
                f_.setTag(dVar2);
                this.c.add(dVar2);
                addView(f_);
            }
        }
        for (int i2 = size; i2 < getChildCount(); i2++) {
            getChildAt(i2).setVisibility(8);
        }
    }

    public void setOnContainerClickListener(final a aVar) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MultipleImgFrameLayoutContainer c;

                public void onClick(View view) {
                    aVar.a(i);
                }
            });
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < getChildCount() && getChildAt(i6).getVisibility() == 0) {
            i7++;
            i6++;
        }
        int size = MeasureSpec.getSize(i);
        if (this.a) {
            i3 = 3;
        } else {
            i6 = (i7 == 2 || i7 == 4) ? 2 : 3;
            i3 = i6;
        }
        i6 = i3 == 2 ? (size - this.b) / 2 : (size - (this.b * 2)) / 3;
        for (i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            if (i4 < i7) {
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i6, 1073741824);
                childAt.measure(makeMeasureSpec, makeMeasureSpec);
            }
        }
        i4 = i7 / i3;
        if (i7 % i3 != 0) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        i7 += i4;
        if (i7 != 1) {
            i5 = (this.b + i6) * (i7 - 1);
        }
        setMeasuredDimension(size, i6 + i5);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 3;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i7 < getChildCount() && getChildAt(i7).getVisibility() == 0) {
            i8++;
            i7++;
        }
        int i9 = i3 - i;
        if (!this.a) {
            i7 = (i8 == 2 || i8 == 4) ? 2 : 3;
            i5 = i7;
        }
        if (i5 == 2) {
            i7 = (i9 - this.b) / 2;
        } else {
            i7 = (i9 - (this.b * 2)) / 3;
        }
        while (i6 < i8) {
            i9 = (i6 % i5) * (this.b + i7);
            int i10 = (i6 / i5) * (this.b + i7);
            getChildAt(i6).layout(i9, i10, i9 + i7, i10 + i7);
            i6++;
        }
    }

    public void setColorFilter(@ColorInt int i) {
        for (d dVar : this.c) {
            if (dVar != null) {
                dVar.b(i);
            }
        }
    }

    public void setImagePlaceHolder(@ColorInt int i) {
        for (d dVar : this.c) {
            if (dVar != null) {
                dVar.a(i);
            }
        }
    }
}
