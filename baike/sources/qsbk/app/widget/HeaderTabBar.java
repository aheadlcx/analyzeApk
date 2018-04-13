package qsbk.app.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class HeaderTabBar extends HorizontalScrollView implements OnClickListener, Recyclable {
    private static int a;
    private LinearLayout b;
    private OnTabClickListener c;
    private int d;
    private int e;
    private HeaderTabBar f;
    private ArrayList<a> g = new ArrayList(2);
    private ColorStateList h = null;

    public interface OnTabClickListener {
        void onTabClick(int i);
    }

    private final class a {
        final /* synthetic */ HeaderTabBar a;
        public int id;
        public Drawable select;
        public TextView textView;
        public Drawable unSelect;

        private a(HeaderTabBar headerTabBar) {
            this.a = headerTabBar;
        }
    }

    public HeaderTabBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public HeaderTabBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HeaderTabBar(Context context) {
        super(context);
        a();
    }

    private void a() {
        if (a == 0) {
            a = getResources().getDimensionPixelSize(R.dimen.profile_header_tab_fading_edge_length);
        }
    }

    public final void setSelectedTab(int i) {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            aVar.textView.setPadding(this.d, 0, this.e, 0);
            if (aVar.id == i) {
                aVar.textView.setTextColor(UIHelper.getSelectedTabTextColor());
                aVar.textView.setBackgroundDrawable(aVar.select);
            } else {
                aVar.textView.setTextColor(this.h);
                aVar.textView.setBackgroundDrawable(aVar.unSelect);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public final void initialFeature() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalFadingEdgeEnabled(true);
        setFadingEdgeLength(a);
        setFillViewport(true);
        this.b = new LinearLayout(getContext());
        this.b.setOrientation(0);
        addView(this.b, generateDefaultLayoutParams());
    }

    public final void setRelativeHeaderTabBar(HeaderTabBar headerTabBar) {
        this.f = headerTabBar;
        if (headerTabBar != null) {
            scrollTo(headerTabBar.getScrollX(), headerTabBar.getScrollY());
        }
    }

    public final void addTab(int i, String str, int i2) {
        if (this.g.size() != 0) {
            View.inflate(getContext(), R.layout.divider_vertical_medium, this.b);
        }
        a aVar = new a();
        aVar.id = i2;
        aVar.textView = (TextView) inflate(getContext(), i, null);
        aVar.textView.setText(str);
        aVar.textView.setTag(Integer.valueOf(i2));
        aVar.textView.setOnClickListener(this);
        this.h = aVar.textView.getTextColors();
        if (this.d == 0) {
            this.d = aVar.textView.getPaddingLeft();
            this.e = aVar.textView.getPaddingRight();
        }
        Resources resources = getResources();
        aVar.select = resources.getDrawable(UIHelper.getProfileSelectedTabSelector());
        aVar.unSelect = resources.getDrawable(UIHelper.getProfileUnselectedTabSelector());
        this.g.add(aVar);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        this.b.addView(aVar.textView, layoutParams);
    }

    public void onClick(View view) {
        if (this.c != null) {
            this.c.onTabClick(((Integer) view.getTag()).intValue());
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        initialFeature();
    }

    public void recycle() {
        this.b.removeAllViews();
        this.g.clear();
        this.c = null;
        this.d = 0;
        this.e = 0;
        if (this.f != null) {
            HeaderTabBar headerTabBar = this.f;
            if (headerTabBar.f == this) {
                headerTabBar.f = null;
            }
            this.f = null;
        }
    }

    public final void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.c = onTabClickListener;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.f != null) {
            this.f.scrollTo(i, i2);
        }
    }
}
