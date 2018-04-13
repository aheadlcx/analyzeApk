package qsbk.app.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.UIHelper;

public class QiushiImageLayout<T extends View> extends ViewGroup {
    ViewFactory a;
    ArrayList<ImageInfo> b = new ArrayList();
    int c;
    ViewFactory<QBImageView> d = new dn(this);
    private OnChildClickListener e;

    public interface OnChildClickListener {
        void onViewClicked(View view, int i);
    }

    public interface ViewFactory<T extends View> {
        T createView(Context context, int i);

        void onViewBind(T t, ImageInfo imageInfo, int i);
    }

    public QiushiImageLayout(Context context) {
        super(context);
        a(context, null, 0);
    }

    public QiushiImageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public QiushiImageLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    public <T extends View> void setViewfactory(ViewFactory<T> viewFactory) {
        if (this.a == null) {
            this.a = viewFactory;
            for (int i = 0; i < 9; i++) {
                addView(this.a.createView(getContext(), i));
            }
        }
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        this.c = UIHelper.dip2px(context, 7.0f);
    }

    public void setSpaceSize(int i) {
        this.c = i;
        requestLayout();
    }

    public void setImages(List<? extends ImageInfo> list) {
        if (this.a == null) {
            setViewfactory(this.d);
        }
        this.b.clear();
        if (list != null && list.size() > 0) {
            this.b.addAll(list);
        }
        for (int i = 0; i < 9; i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                ImageInfo imageInfo = null;
                if (i < this.b.size()) {
                    imageInfo = (ImageInfo) this.b.get(i);
                }
                this.a.onViewBind(childAt, imageInfo, i);
            }
        }
        requestLayout();
    }

    public Rect[] getImageLocations() {
        if (this.b == null) {
            return null;
        }
        Rect[] rectArr = new Rect[this.b.size()];
        int i = 0;
        while (i < this.b.size()) {
            View childAt = getChildAt(i);
            if (childAt != null && i < rectArr.length) {
                int[] iArr = new int[2];
                childAt.getLocationOnScreen(iArr);
                rectArr[i] = new Rect(iArr[0], iArr[1], iArr[0] + childAt.getWidth(), childAt.getHeight() + iArr[1]);
            }
            i++;
        }
        return rectArr;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    protected void onMeasure(int i, int i2) {
        a(this.b.size(), i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        switch (this.b.size()) {
            case 1:
                a(z, i, i2, i3, i4);
                return;
            case 2:
                b(z, i, i2, i3, i4);
                return;
            case 3:
                c(z, i, i2, i3, i4);
                return;
            case 4:
                d(z, i, i2, i3, i4);
                return;
            case 5:
                e(z, i, i2, i3, i4);
                return;
            case 6:
                f(z, i, i2, i3, i4);
                return;
            case 7:
                g(z, i, i2, i3, i4);
                return;
            case 8:
                h(z, i, i2, i3, i4);
                return;
            case 9:
                i(z, i, i2, i3, i4);
                return;
            default:
                return;
        }
    }

    private void a(int i, int i2, int i3) {
        switch (i) {
            case 1:
                i3 = a(i2, i3);
                break;
            case 2:
                i3 = b(i2, i3);
                break;
            case 3:
                i3 = c(i2, i3);
                break;
            case 4:
                i3 = d(i2, i3);
                break;
            case 5:
                i3 = e(i2, i3);
                break;
            case 6:
                i3 = f(i2, i3);
                break;
            case 7:
                i3 = g(i2, i3);
                break;
            case 8:
                i3 = h(i2, i3);
                break;
            case 9:
                i3 = i(i2, i3);
                break;
        }
        setMeasuredDimension(MeasureSpec.getSize(i2), MeasureSpec.getSize(i3));
    }

    private int a(int i, int i2) {
        int i3;
        ImageInfo imageInfo = (ImageInfo) this.b.get(0);
        int size = MeasureSpec.getSize(i);
        if (imageInfo.mediaFormat == MediaFormat.IMAGE_LONG) {
            i3 = size;
        } else {
            i3 = (int) (((float) size) / imageInfo.getAspectRatio());
        }
        View childAt = getChildAt(0);
        i3 = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        measureChild(childAt, i, i3);
        return i3;
    }

    private int b(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec((MeasureSpec.getSize(i) - this.c) / 2, 1073741824);
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            measureChild(getChildAt(i3), makeMeasureSpec, makeMeasureSpec);
        }
        return makeMeasureSpec;
    }

    private int c(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec((MeasureSpec.getSize(i) - (this.c * 2)) / 3, 1073741824);
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            measureChild(getChildAt(i3), makeMeasureSpec, makeMeasureSpec);
        }
        return makeMeasureSpec;
    }

    private int d(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        int i3 = (size - (this.c * 2)) / 3;
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        for (size = 0; size < this.b.size(); size++) {
            View childAt = getChildAt(size);
            if (size == 0) {
                measureChild(childAt, makeMeasureSpec, MeasureSpec.makeMeasureSpec(i3 * 2, 1073741824));
            } else {
                measureChild(childAt, makeMeasureSpec2, makeMeasureSpec2);
            }
        }
        return MeasureSpec.makeMeasureSpec((i3 * 3) + this.c, 1073741824);
    }

    private int e(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int i3 = (size - this.c) / 2;
        int i4 = (size - (this.c * 2)) / 3;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        for (size = 0; size < this.b.size(); size++) {
            View childAt = getChildAt(size);
            if (size < 2) {
                measureChild(childAt, makeMeasureSpec, makeMeasureSpec);
            } else {
                measureChild(childAt, makeMeasureSpec2, makeMeasureSpec2);
            }
        }
        return MeasureSpec.makeMeasureSpec((i3 + i4) + this.c, 1073741824);
    }

    private int f(int i, int i2) {
        int size = (MeasureSpec.getSize(i) - (this.c * 2)) / 3;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            measureChild(getChildAt(i3), makeMeasureSpec, makeMeasureSpec);
        }
        return MeasureSpec.makeMeasureSpec((size * 2) + this.c, 1073741824);
    }

    private int g(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int i3 = (size - this.c) / 2;
        int i4 = (size - (this.c * 2)) / 3;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        for (size = 0; size < this.b.size(); size++) {
            View childAt = getChildAt(size);
            if (size < 4) {
                measureChild(childAt, makeMeasureSpec, makeMeasureSpec);
            } else {
                measureChild(childAt, makeMeasureSpec2, makeMeasureSpec2);
            }
        }
        return MeasureSpec.makeMeasureSpec(((i3 * 2) + i4) + (this.c * 2), 1073741824);
    }

    private int h(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int i3 = (size - this.c) / 2;
        int i4 = (size - (this.c * 2)) / 3;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i3, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        for (size = 0; size < this.b.size(); size++) {
            View childAt = getChildAt(size);
            if (size < 2) {
                measureChild(childAt, makeMeasureSpec, makeMeasureSpec);
            } else {
                measureChild(childAt, makeMeasureSpec2, makeMeasureSpec2);
            }
        }
        return MeasureSpec.makeMeasureSpec(((i4 * 2) + i3) + (this.c * 2), 1073741824);
    }

    private int i(int i, int i2) {
        int size = (MeasureSpec.getSize(i) - (this.c * 2)) / 3;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            measureChild(getChildAt(i3), makeMeasureSpec, makeMeasureSpec);
        }
        return MeasureSpec.makeMeasureSpec((size * 3) + (this.c * 2), 1073741824);
    }

    private void a(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
    }

    private void b(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < this.b.size(); i6++) {
            View childAt = getChildAt(i6);
            childAt.layout(i5, 0, childAt.getMeasuredWidth() + i5, childAt.getMeasuredHeight() + 0);
            i5 += childAt.getMeasuredWidth() + this.c;
        }
    }

    private void c(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < this.b.size(); i6++) {
            View childAt = getChildAt(i6);
            childAt.layout(i5, 0, childAt.getMeasuredWidth() + i5, childAt.getMeasuredHeight() + 0);
            i5 += childAt.getMeasuredWidth() + this.c;
        }
    }

    private void d(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < this.b.size(); i7++) {
            View childAt = getChildAt(i7);
            childAt.layout(i6, i5, childAt.getMeasuredWidth() + i6, childAt.getMeasuredHeight() + i5);
            if (i7 == 0) {
                i5 = childAt.getMeasuredHeight() + this.c;
                i6 = 0;
            } else {
                i6 += childAt.getMeasuredWidth() + this.c;
            }
        }
    }

    private void e(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < this.b.size(); i7++) {
            View childAt = getChildAt(i7);
            childAt.layout(i6, i5, childAt.getMeasuredWidth() + i6, childAt.getMeasuredHeight() + i5);
            if (i7 == 1) {
                i5 = childAt.getMeasuredHeight() + this.c;
                i6 = 0;
            } else {
                i6 += childAt.getMeasuredWidth() + this.c;
            }
        }
    }

    private void f(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < this.b.size(); i7++) {
            View childAt = getChildAt(i7);
            childAt.layout(i6, i5, childAt.getMeasuredWidth() + i6, childAt.getMeasuredHeight() + i5);
            if (i7 == 2) {
                i5 = childAt.getMeasuredHeight() + this.c;
                i6 = 0;
            } else {
                i6 += childAt.getMeasuredWidth() + this.c;
            }
        }
    }

    private void g(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < this.b.size(); i7++) {
            View childAt = getChildAt(i7);
            childAt.layout(i6, i5, childAt.getMeasuredWidth() + i6, childAt.getMeasuredHeight() + i5);
            if (i7 == 1) {
                i5 = childAt.getMeasuredHeight() + this.c;
                i6 = 0;
            } else if (i7 == 3) {
                i5 = (childAt.getMeasuredHeight() * 2) + (this.c * 2);
                i6 = 0;
            } else {
                i6 += childAt.getMeasuredWidth() + this.c;
            }
        }
    }

    private void h(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < this.b.size(); i8++) {
            View childAt = getChildAt(i8);
            childAt.layout(i7, i6, childAt.getMeasuredWidth() + i7, childAt.getMeasuredHeight() + i6);
            if (i8 == 1) {
                i5 = childAt.getMeasuredHeight();
                i6 = childAt.getMeasuredHeight() + this.c;
                i7 = 0;
            } else if (i8 == 4) {
                i6 = (childAt.getMeasuredHeight() + i5) + (this.c * 2);
                i7 = 0;
            } else {
                i7 += childAt.getMeasuredWidth() + this.c;
            }
        }
    }

    private void i(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < this.b.size(); i7++) {
            View childAt = getChildAt(i7);
            childAt.layout(i6, i5, childAt.getMeasuredWidth() + i6, childAt.getMeasuredHeight() + i5);
            if (i7 == 2) {
                i5 = childAt.getMeasuredHeight() + this.c;
                i6 = 0;
            } else if (i7 == 5) {
                i5 = (childAt.getMeasuredHeight() * 2) + (this.c * 2);
                i6 = 0;
            } else {
                i6 += childAt.getMeasuredWidth() + this.c;
            }
        }
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.e = onChildClickListener;
    }
}
