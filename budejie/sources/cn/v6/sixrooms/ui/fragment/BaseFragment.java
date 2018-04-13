package cn.v6.sixrooms.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;

public class BaseFragment extends Fragment {
    private View a;
    private TextView b;
    private TextView c;
    private FrameLayout d;
    private TextView e;
    private FrameLayout f;
    private TextView g;
    private int h = BaseFragment$a.a;
    private boolean i = false;
    private SparseArray<View> j = new SparseArray();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.h = BaseFragment$a.c;
    }

    public void onResume() {
        super.onResume();
        this.h = BaseFragment$a.g;
        if (getUserVisibleHint()) {
            onVisible(false);
        }
    }

    public void onPause() {
        super.onPause();
        this.h = BaseFragment$a.h;
        if (getUserVisibleHint()) {
            onInVisible();
        }
    }

    public void initDefaultTitleBar(String str, Drawable drawable, String str2, Drawable drawable2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2) {
        View view = getView();
        this.a = view.findViewById(R.id.titlebar_default);
        this.c = (TextView) view.findViewById(R.id.titlebar_left);
        this.e = (TextView) view.findViewById(R.id.titlebar_right);
        this.g = (TextView) view.findViewById(R.id.titlebar_title);
        this.b = (TextView) view.findViewById(R.id.titlebar_title_plus);
        this.d = (FrameLayout) view.findViewById(R.id.titlebar_left_frame);
        this.f = (FrameLayout) view.findViewById(R.id.titlebar_right_frame);
        if (str == null && drawable == null) {
            this.d.setVisibility(4);
        }
        if (str2 == null && drawable2 == null) {
            this.f.setVisibility(4);
        }
        this.g.setText(str3);
        if (TextUtils.isEmpty(str)) {
            this.c.setText("");
        } else {
            this.c.setText(str);
        }
        if (drawable == null) {
            this.c.setCompoundDrawables(null, null, null, null);
        } else {
            drawable.setBounds(1, 2, drawable.getMinimumWidth() - 1, drawable.getMinimumHeight() - 2);
            this.c.setCompoundDrawables(drawable, null, null, null);
        }
        if (str == null && drawable == null) {
            this.d.setVisibility(4);
        }
        if (onClickListener != null) {
            this.d.setOnClickListener(onClickListener);
        }
        if (TextUtils.isEmpty(str2)) {
            this.e.setText("");
        } else {
            this.e.setText(str2);
        }
        if (drawable2 == null) {
            this.e.setCompoundDrawables(null, null, null, null);
        } else {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.e.setCompoundDrawables(drawable2, null, null, null);
        }
        if (str2 == null && drawable2 == null) {
            this.f.setVisibility(4);
        }
        if (onClickListener2 != null) {
            this.f.setOnClickListener(onClickListener2);
        }
    }

    public void initDefaultTitleBar(String str, Drawable drawable, String str2, Drawable drawable2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2, boolean z) {
        initDefaultTitleBar(str, drawable, str2, drawable2, str3, onClickListener, onClickListener2);
        if (this.c == null) {
            return;
        }
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }

    public void initDefaultTitleBar(String str, Drawable drawable, String str2, Drawable drawable2, String str3, Drawable drawable3, OnClickListener onClickListener, OnClickListener onClickListener2, OnClickListener onClickListener3) {
        initDefaultTitleBar(str, drawable, str2, drawable2, str3, onClickListener, onClickListener2);
        if (drawable3 == null) {
            this.g.setCompoundDrawables(null, null, null, null);
        } else {
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            this.g.setCompoundDrawables(null, null, drawable3, null);
        }
        if (onClickListener3 != null) {
            this.g.setOnClickListener(onClickListener3);
        }
    }

    public void hideTitleBar() {
        if (this.a != null) {
            this.a.setVisibility(8);
        }
    }

    public void setTitleText(String str) {
        this.g.setText(str);
    }

    public void setTitlePlusText(String str) {
        this.b.setText(str);
        if (TextUtils.isEmpty(str)) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.h = BaseFragment$a.b;
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.h = BaseFragment$a.d;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.h = BaseFragment$a.e;
        if (!this.i && getUserVisibleHint()) {
            onVisible(true);
            this.i = true;
        }
    }

    public void onStart() {
        super.onStart();
        this.h = BaseFragment$a.f;
    }

    public void onStop() {
        super.onStop();
        this.h = BaseFragment$a.i;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.h = BaseFragment$a.j;
        if (getUserVisibleHint()) {
            onInVisible();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.h = BaseFragment$a.k;
    }

    public void onDetach() {
        super.onDetach();
        this.h = BaseFragment$a.l;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.h != BaseFragment$a.a && this.h != BaseFragment$a.b && this.h != BaseFragment$a.c && this.h != BaseFragment$a.d) {
            if (!z) {
                onInVisible();
            } else if (this.i) {
                onVisible(false);
            } else {
                onVisible(true);
                this.i = true;
            }
        }
    }

    public int getState$d51c5d6() {
        return this.h;
    }

    protected void onVisible(boolean z) {
    }

    protected void onInVisible() {
    }
}
