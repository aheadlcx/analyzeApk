package qsbk.app.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleArticleImageViewer;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.PicUrl;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;

public class CircleImageLayout extends ViewGroup implements OnClickListener {
    CircleArticle a;
    private int b;
    private int c;
    private int d;
    private List<PicUrl> e = new ArrayList();

    public CircleImageLayout(Context context) {
        super(context);
        a();
    }

    public CircleImageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CircleImageLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.b = 3;
        this.c = UIHelper.dip2px(getContext(), 4.0f);
        fillCount(6);
    }

    public void fillCount(int i) {
        if (this.d != i) {
            removeAllViews();
            for (int i2 = 0; i2 < i; i2++) {
                addView(a(i2));
            }
            this.d = i;
        }
    }

    private SimpleDraweeView a(int i) {
        SimpleDraweeView qBImageView = new QBImageView(getContext());
        qBImageView.setScaleType(ScaleType.CENTER_CROP);
        qBImageView.setTag(Integer.valueOf(i));
        qBImageView.setOnClickListener(new LoginPermissionClickDelegate(this, null));
        return qBImageView;
    }

    public void setCircleArticle(CircleArticle circleArticle) {
        if (circleArticle == null) {
            setImages(null);
            return;
        }
        setImages(circleArticle.picUrls);
        this.a = circleArticle;
    }

    private void setImages(List<PicUrl> list) {
        this.e.clear();
        if (list != null && list.size() > 0) {
            this.e.addAll(list);
        }
        for (int i = 0; i < this.d; i++) {
            QBImageView qBImageView = (QBImageView) getChildAt(i);
            if (qBImageView != null) {
                String imageUrl;
                int i2;
                String str = "";
                if (i < this.e.size()) {
                    PicUrl picUrl = (PicUrl) this.e.get(i);
                    int formatTagImage = MediaFormat.getFormatTagImage(picUrl.mediaFormat);
                    qBImageView.setVisibility(0);
                    int i3 = formatTagImage;
                    imageUrl = picUrl.getImageUrl();
                    i2 = i3;
                } else {
                    qBImageView.setVisibility(8);
                    imageUrl = str;
                    i2 = 0;
                }
                FrescoImageloader.displayImage(qBImageView, imageUrl, UIHelper.getShare2IMIcon());
                qBImageView.setTypeImageResouce(i2);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        int size = MeasureSpec.getSize(i);
        if (size == 0) {
            size = 1048576;
        }
        int paddingLeft = ((((size - getPaddingLeft()) - getPaddingRight()) + this.c) / this.b) - this.c;
        int childCount = getChildCount();
        int i4 = this.b - 1;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                i4++;
                if (i4 == this.b) {
                    i5++;
                    i4 = 0;
                }
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
                childAt.measure(makeMeasureSpec, makeMeasureSpec);
            }
        }
        if (i5 != 0) {
            i3 = (((i5 * (this.c + paddingLeft)) - this.c) + getPaddingTop()) + getPaddingBottom();
        }
        setMeasuredDimension(size, resolveSize(i3, i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = (((((i3 - i) - getPaddingLeft()) - getPaddingRight()) + this.c) / this.b) - this.c;
        getChildCount();
        int paddingLeft2 = getPaddingLeft();
        int i5 = 0;
        int i6 = paddingLeft2;
        int paddingTop = getPaddingTop();
        for (int i7 = 0; i7 < this.d; i7++) {
            View childAt = getChildAt(i7);
            if (!(childAt == null || childAt.getVisibility() == 8)) {
                childAt.layout(i6, paddingTop, i6 + paddingLeft, paddingTop + paddingLeft);
                i5++;
                if (i5 == this.b) {
                    paddingTop += this.c + paddingLeft;
                    i5 = 0;
                    i6 = paddingLeft2;
                } else {
                    i6 += this.c + paddingLeft;
                }
            }
        }
    }

    public void onClick(View view) {
        if (this.a != null && this.a.hasImage()) {
            int size = this.a.picUrls.size();
            String[] strArr = new String[size];
            String[] strArr2 = new String[size];
            for (int i = 0; i < size; i++) {
                String absoluteUrlOfCircleWebpImage = QsbkApp.absoluteUrlOfCircleWebpImage(((PicUrl) this.a.picUrls.get(i)).url, this.a.createAt);
                strArr2[i] = absoluteUrlOfCircleWebpImage;
                int indexOf = absoluteUrlOfCircleWebpImage.indexOf("?");
                if (indexOf != -1) {
                    absoluteUrlOfCircleWebpImage = absoluteUrlOfCircleWebpImage.substring(0, indexOf);
                }
                strArr[i] = absoluteUrlOfCircleWebpImage;
            }
            CircleArticleImageViewer.launch(Util.getActivityOrContext(this), view, getImageLocations(), this.a, strArr[((Integer) view.getTag()).intValue()], ((Integer) view.getTag()).intValue());
        }
    }

    private Rect[] getImageLocations() {
        if (this.a == null || this.a.picUrls == null) {
            return null;
        }
        Rect[] rectArr = new Rect[this.a.picUrls.size()];
        int i = 0;
        while (i < this.d) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) getChildAt(i);
            if (simpleDraweeView != null && i < rectArr.length) {
                int[] iArr = new int[2];
                simpleDraweeView.getLocationOnScreen(iArr);
                rectArr[i] = new Rect(iArr[0], iArr[1], iArr[0] + simpleDraweeView.getWidth(), simpleDraweeView.getHeight() + iArr[1]);
            }
            i++;
        }
        return rectArr;
    }
}
