package qsbk.app.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class TabPanel extends HorizontalScrollView implements OnClickListener, Recyclable {
    public static final String TAB_UNKNOW = "UNKNOW";
    private static int a;
    private LinearLayout b;
    private OnTabClickListener c;
    private boolean d = true;
    private String e = TAB_UNKNOW;
    private ArrayList<TabBarItem> f = new ArrayList(2);

    public interface OnTabClickListener {
        void onTabClick(String str);
    }

    public static final class TabBarItem {
        private String a;
        private int b;
        private int c;
        private HighlightableImageView d;
        private TextView e;
        private FrameLayout f;
        private ImageView g;
        private TextView h;
        private String i;

        public TabBarItem(String str, int i, int i2, String str2) {
            this.a = str;
            this.b = i2;
            this.c = i;
            this.i = str2;
        }

        public String getId() {
            return this.a;
        }
    }

    public TabPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public TabPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TabPanel(Context context) {
        super(context);
        a();
    }

    private void a() {
        if (a == 0) {
            a = getResources().getDimensionPixelSize(R.dimen.profile_header_tab_fading_edge_length);
        }
    }

    public final void setHighlightedTab(String str, boolean z) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            TabBarItem tabBarItem = (TabBarItem) it.next();
            if (tabBarItem.a.equalsIgnoreCase(str)) {
                tabBarItem.d.setHighlighted(z);
            } else if (z) {
                tabBarItem.d.setHighlighted(!z);
            }
        }
    }

    public final void setTips(String str, String str2) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            TabBarItem tabBarItem = (TabBarItem) it.next();
            if (tabBarItem.a.equalsIgnoreCase(str)) {
                tabBarItem.g.setVisibility(4);
                tabBarItem.e.setVisibility(0);
                if (!TextUtils.isEmpty(str2)) {
                    tabBarItem.e.setText(str2);
                }
            }
        }
    }

    public final void setSmallTips(String str) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            TabBarItem tabBarItem = (TabBarItem) it.next();
            if (tabBarItem.a.equalsIgnoreCase(str)) {
                tabBarItem.e.setVisibility(4);
                tabBarItem.g.setVisibility(0);
            }
        }
    }

    public final void hideSmallTips(String str) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            TabBarItem tabBarItem = (TabBarItem) it.next();
            if (tabBarItem.a.equalsIgnoreCase(str)) {
                tabBarItem.g.setVisibility(4);
            }
        }
    }

    public final void hideTips(String str) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            TabBarItem tabBarItem = (TabBarItem) it.next();
            if (tabBarItem.a.equalsIgnoreCase(str)) {
                tabBarItem.e.setText("");
                tabBarItem.e.setVisibility(4);
            }
        }
    }

    public final String getSelectedTab() {
        return this.e;
    }

    public final void setSelectedTab(String str) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            TabBarItem tabBarItem = (TabBarItem) it.next();
            if (tabBarItem.a.equalsIgnoreCase(str)) {
                this.e = str;
                tabBarItem.d.setSelected(true);
                tabBarItem.f.setSelected(true);
                tabBarItem.h.setSelected(true);
                if (this.d && tabBarItem.e != null) {
                    tabBarItem.e.setVisibility(4);
                }
            } else {
                tabBarItem.d.setSelected(false);
                tabBarItem.f.setSelected(false);
                tabBarItem.h.setSelected(false);
            }
        }
        if (this.c != null) {
            this.c.onTabClick(str);
        }
    }

    public final void setWillAutoCancelTipsWhenSelected(boolean z) {
        this.d = z;
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

    public final void addTab(int i, int i2, String str, String str2) {
        addTab(new TabBarItem(str, i, i2, str2));
    }

    public final void addTab(TabBarItem tabBarItem) {
        Resources resources = getResources();
        float f = resources.getDisplayMetrics().density;
        tabBarItem.f = new FrameLayout(getContext());
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        tabBarItem.d = new HighlightableImageView(getContext());
        tabBarItem.d.setScaleType(ScaleType.CENTER);
        tabBarItem.d.setImageDrawable(resources.getDrawable(tabBarItem.b));
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        linearLayout.addView(tabBarItem.d, layoutParams);
        tabBarItem.h = new TextView(getContext());
        tabBarItem.h.setText(tabBarItem.i);
        tabBarItem.h.setTextSize(10.0f);
        tabBarItem.h.setTextColor(resources.getColorStateList(UIHelper.getTabDescriptionTextColor()));
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        layoutParams.topMargin = -UIHelper.dip2px(getContext(), 3.0f);
        layoutParams.bottomMargin = UIHelper.dip2px(getContext(), 1.0f);
        linearLayout.addView(tabBarItem.h, layoutParams);
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        tabBarItem.f.addView(linearLayout, layoutParams);
        tabBarItem.e = new TextView(getContext());
        tabBarItem.e.setBackgroundResource(UIHelper.getNewMessageTips());
        tabBarItem.e.setTextSize(9.0f);
        tabBarItem.e.setPadding((int) (2.0f * f), 0, (int) (2.0f * f), 0);
        tabBarItem.e.setGravity(17);
        tabBarItem.e.setTextColor(resources.getColor(UIHelper.getNewMessageTipsTextColor()));
        tabBarItem.e.setVisibility(4);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, (int) (f * 15.0f));
        layoutParams2.gravity = 49;
        layoutParams2.topMargin = resources.getDimensionPixelSize(R.dimen.actionbar_height) / 8;
        layoutParams2.leftMargin = resources.getDimensionPixelSize(R.dimen.actionbar_height) / 4;
        tabBarItem.f.addView(tabBarItem.e, layoutParams2);
        tabBarItem.g = new ImageView(getContext());
        tabBarItem.g.setScaleType(ScaleType.CENTER);
        tabBarItem.g.setImageResource(UIHelper.isNightTheme() ? R.drawable.found_item_tips_night : R.drawable.found_item_tips);
        tabBarItem.g.setVisibility(4);
        LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 49;
        layoutParams3.topMargin = (resources.getDimensionPixelSize(R.dimen.actionbar_height) / 6) + UIHelper.dip2px(getContext(), 1.1f);
        layoutParams3.leftMargin = (resources.getDimensionPixelSize(R.dimen.actionbar_height) / 4) + UIHelper.dip2px(getContext(), 3.5f);
        tabBarItem.f.setTag(tabBarItem.a);
        tabBarItem.f.setDescendantFocusability(393216);
        tabBarItem.f.setOnClickListener(this);
        tabBarItem.f.addView(tabBarItem.g, layoutParams3);
        if (tabBarItem.c > 0) {
            tabBarItem.f.setBackgroundDrawable(resources.getDrawable(tabBarItem.c));
        }
        this.f.add(tabBarItem);
        this.b.addView(tabBarItem.f, getTabLayoutParams());
    }

    public LayoutParams getTabLayoutParams() {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.weight = 1.0f;
        return layoutParams;
    }

    public void onClick(View view) {
        if (this.c != null) {
            setSelectedTab(String.valueOf(view.getTag()));
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        initialFeature();
    }

    public void recycle() {
        this.b.removeAllViews();
        this.f.clear();
        this.c = null;
    }

    public final void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.c = onTabClickListener;
    }
}
