package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class NavigationBar extends FrameLayout implements OnClickListener {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private View j;
    private RelativeLayout k;
    private a l;
    private b m;

    public interface a {
        void r();

        void s();

        void t();

        void u();
    }

    public interface b {
        void a();
    }

    public NavigationBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.view_navigation_bar, this);
        getViews();
        if (attributeSet != null) {
            b(context, attributeSet);
            f();
        }
    }

    private void getViews() {
        this.a = (ImageView) findViewById(R.id.ivBack);
        this.b = (ImageView) findViewById(R.id.ivOption);
        this.c = (ImageView) findViewById(R.id.ivExtraOption);
        this.d = (ImageView) findViewById(R.id.ivNavCrumb);
        this.j = findViewById(R.id.ivBottomDivide);
        this.g = (TextView) findViewById(R.id.tvTitle);
        this.h = (TextView) findViewById(R.id.tvOptionText);
        this.e = (ImageView) findViewById(R.id.ivSecondOption);
        this.f = (ImageView) findViewById(R.id.ivSecondOptionCrumb);
        this.i = (TextView) findViewById(R.id.ivSecondOptionCrumbNum);
        this.k = (RelativeLayout) findViewById(R.id.rlParent);
    }

    public void a(@ColorInt int i, @ColorInt int i2, @ColorInt int i3) {
        this.k.setBackgroundColor(i);
        this.g.setTextColor(i3);
        this.j.setBackgroundColor(i2);
    }

    private void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.navigationBar);
        CharSequence string = obtainStyledAttributes.getString(0);
        CharSequence string2 = obtainStyledAttributes.getString(1);
        int resourceId = obtainStyledAttributes.getResourceId(2, -1);
        int resourceId2 = obtainStyledAttributes.getResourceId(3, -1);
        int resourceId3 = obtainStyledAttributes.getResourceId(4, -1);
        int resourceId4 = obtainStyledAttributes.getResourceId(5, -1);
        boolean z = obtainStyledAttributes.getBoolean(6, true);
        boolean z2 = obtainStyledAttributes.getBoolean(7, false);
        if (string != null) {
            this.g.setText(string);
        }
        if (!z) {
            this.a.setVisibility(8);
        } else if (-1 != resourceId2) {
            this.a.setImageResource(resourceId2);
        }
        if (string2 != null) {
            this.h.setText(string2);
            this.h.setVisibility(0);
        } else {
            if (-1 != resourceId) {
                this.b.setVisibility(0);
                this.b.setImageResource(resourceId);
            }
            if (-1 != resourceId3) {
                this.c.setVisibility(0);
                this.c.setImageResource(resourceId3);
            }
            if (resourceId4 > 0 && -1 != resourceId4) {
                this.e.setImageResource(resourceId4);
                this.e.setVisibility(0);
            }
        }
        if (!z2) {
            this.j.setVisibility(8);
        }
        obtainStyledAttributes.recycle();
    }

    private void f() {
        this.a.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.g.setOnClickListener(this);
    }

    public void setListener(a aVar) {
        this.l = aVar;
    }

    public void setTitleClickListener(b bVar) {
        this.m = bVar;
    }

    public void setTitle(String str) {
        this.g.setText(str);
    }

    public void setParentBackgroundResId(int i) {
        this.k.setBackgroundResource(i);
    }

    public void setOptionImg(int i) {
        this.b.setImageResource(i);
        this.b.setVisibility(0);
    }

    public void a(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }

    public void a(boolean z, String str) {
        this.i.setVisibility(z ? 0 : 8);
        this.i.setText(str);
    }

    public void setOptionText(String str) {
        this.h.setVisibility(0);
        this.h.setText(str);
    }

    public TextView getOptionText() {
        return this.h;
    }

    public void setExtraOptionImg(int i) {
        this.c.setImageResource(i);
        a();
    }

    public ImageView getIvExtraOption() {
        return this.c;
    }

    public void a() {
        this.c.setVisibility(0);
    }

    public void b() {
        this.c.setVisibility(8);
    }

    public void setTitleRightDrawable(int i) {
        this.g.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    public void b(boolean z) {
        if (z) {
            if (this.j.getVisibility() != 0) {
                this.j.setVisibility(0);
            }
        } else if (this.j.getVisibility() != 8) {
            this.j.setVisibility(8);
        }
    }

    public ImageView getOptionImageView() {
        return this.b;
    }

    public View getBackView() {
        return this.a;
    }

    public void setOptionTxtVisibility(int i) {
        this.h.setVisibility(i);
    }

    public void setCrumbVisibility(int i) {
        this.d.setVisibility(i);
    }

    protected boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                if (this.l != null) {
                    this.l.r();
                    return;
                }
                return;
            case R.id.tvTitle:
                if (this.m != null) {
                    this.m.a();
                    return;
                }
                return;
            case R.id.tvOptionText:
                if (this.l != null) {
                    this.l.s();
                    return;
                }
                return;
            case R.id.ivOption:
                if (this.l != null) {
                    this.l.s();
                    return;
                }
                return;
            case R.id.ivSecondOption:
                if (this.l != null) {
                    this.l.u();
                    return;
                }
                return;
            case R.id.ivExtraOption:
                if (this.l != null) {
                    this.l.t();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void c() {
        this.e.setVisibility(8);
    }

    public void d() {
        this.e.setVisibility(0);
    }

    public void setSecondOptionIcon(@DrawableRes int i) {
        this.e.setImageResource(i);
    }

    public void e() {
        if (this.b != null) {
            this.b.setVisibility(8);
        }
    }

    public void setTitleLength(int i) {
        if (this.g != null) {
            this.g.setFilters(new InputFilter[]{new LengthFilter(i)});
        }
    }
}
