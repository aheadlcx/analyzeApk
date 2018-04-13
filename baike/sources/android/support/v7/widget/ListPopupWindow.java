package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.StyleRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    public static final int WRAP_CONTENT = -2;
    private static Method a;
    private static Method b;
    private static Method h;
    private Drawable A;
    private OnItemClickListener B;
    private OnItemSelectedListener C;
    private final d D;
    private final c E;
    private final a F;
    private Runnable G;
    private final Rect H;
    private Rect I;
    private boolean J;
    ak c;
    int d;
    final e e;
    final Handler f;
    PopupWindow g;
    private Context i;
    private ListAdapter j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private boolean u;
    private boolean v;
    private View w;
    private int x;
    private DataSetObserver y;
    private View z;

    private class a implements Runnable {
        final /* synthetic */ ListPopupWindow a;

        a(ListPopupWindow listPopupWindow) {
            this.a = listPopupWindow;
        }

        public void run() {
            this.a.clearListSelection();
        }
    }

    private class b extends DataSetObserver {
        final /* synthetic */ ListPopupWindow a;

        b(ListPopupWindow listPopupWindow) {
            this.a = listPopupWindow;
        }

        public void onChanged() {
            if (this.a.isShowing()) {
                this.a.show();
            }
        }

        public void onInvalidated() {
            this.a.dismiss();
        }
    }

    private class c implements OnScrollListener {
        final /* synthetic */ ListPopupWindow a;

        c(ListPopupWindow listPopupWindow) {
            this.a = listPopupWindow;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !this.a.isInputMethodNotNeeded() && this.a.g.getContentView() != null) {
                this.a.f.removeCallbacks(this.a.e);
                this.a.e.run();
            }
        }
    }

    private class d implements OnTouchListener {
        final /* synthetic */ ListPopupWindow a;

        d(ListPopupWindow listPopupWindow) {
            this.a = listPopupWindow;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && this.a.g != null && this.a.g.isShowing() && x >= 0 && x < this.a.g.getWidth() && y >= 0 && y < this.a.g.getHeight()) {
                this.a.f.postDelayed(this.a.e, 250);
            } else if (action == 1) {
                this.a.f.removeCallbacks(this.a.e);
            }
            return false;
        }
    }

    private class e implements Runnable {
        final /* synthetic */ ListPopupWindow a;

        e(ListPopupWindow listPopupWindow) {
            this.a = listPopupWindow;
        }

        public void run() {
            if (this.a.c != null && ViewCompat.isAttachedToWindow(this.a.c) && this.a.c.getCount() > this.a.c.getChildCount() && this.a.c.getChildCount() <= this.a.d) {
                this.a.g.setInputMethodMode(2);
                this.a.show();
            }
        }
    }

    static {
        try {
            a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException e2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            h = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
        } catch (NoSuchMethodException e3) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(@NonNull Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this.k = -2;
        this.l = -2;
        this.o = 1002;
        this.q = true;
        this.t = 0;
        this.u = false;
        this.v = false;
        this.d = Integer.MAX_VALUE;
        this.x = 0;
        this.e = new e(this);
        this.D = new d(this);
        this.E = new c(this);
        this.F = new a(this);
        this.H = new Rect();
        this.i = context;
        this.f = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i, i2);
        this.m = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.n = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.n != 0) {
            this.p = true;
        }
        obtainStyledAttributes.recycle();
        this.g = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.g.setInputMethodMode(1);
    }

    public void setAdapter(@Nullable ListAdapter listAdapter) {
        if (this.y == null) {
            this.y = new b(this);
        } else if (this.j != null) {
            this.j.unregisterDataSetObserver(this.y);
        }
        this.j = listAdapter;
        if (this.j != null) {
            listAdapter.registerDataSetObserver(this.y);
        }
        if (this.c != null) {
            this.c.setAdapter(this.j);
        }
    }

    public void setPromptPosition(int i) {
        this.x = i;
    }

    public int getPromptPosition() {
        return this.x;
    }

    public void setModal(boolean z) {
        this.J = z;
        this.g.setFocusable(z);
    }

    public boolean isModal() {
        return this.J;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setForceIgnoreOutsideTouch(boolean z) {
        this.v = z;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setDropDownAlwaysVisible(boolean z) {
        this.u = z;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean isDropDownAlwaysVisible() {
        return this.u;
    }

    public void setSoftInputMode(int i) {
        this.g.setSoftInputMode(i);
    }

    public int getSoftInputMode() {
        return this.g.getSoftInputMode();
    }

    public void setListSelector(Drawable drawable) {
        this.A = drawable;
    }

    @Nullable
    public Drawable getBackground() {
        return this.g.getBackground();
    }

    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        this.g.setBackgroundDrawable(drawable);
    }

    public void setAnimationStyle(@StyleRes int i) {
        this.g.setAnimationStyle(i);
    }

    @StyleRes
    public int getAnimationStyle() {
        return this.g.getAnimationStyle();
    }

    @Nullable
    public View getAnchorView() {
        return this.z;
    }

    public void setAnchorView(@Nullable View view) {
        this.z = view;
    }

    public int getHorizontalOffset() {
        return this.m;
    }

    public void setHorizontalOffset(int i) {
        this.m = i;
    }

    public int getVerticalOffset() {
        if (this.p) {
            return this.n;
        }
        return 0;
    }

    public void setVerticalOffset(int i) {
        this.n = i;
        this.p = true;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setEpicenterBounds(Rect rect) {
        this.I = rect;
    }

    public void setDropDownGravity(int i) {
        this.t = i;
    }

    public int getWidth() {
        return this.l;
    }

    public void setWidth(int i) {
        this.l = i;
    }

    public void setContentWidth(int i) {
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            this.l = (this.H.left + this.H.right) + i;
            return;
        }
        setWidth(i);
    }

    public int getHeight() {
        return this.k;
    }

    public void setHeight(int i) {
        if (i >= 0 || -2 == i || -1 == i) {
            this.k = i;
            return;
        }
        throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
    }

    public void setWindowLayoutType(int i) {
        this.o = i;
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.B = onItemClickListener;
    }

    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener onItemSelectedListener) {
        this.C = onItemSelectedListener;
    }

    public void setPromptView(@Nullable View view) {
        boolean isShowing = isShowing();
        if (isShowing) {
            a();
        }
        this.w = view;
        if (isShowing) {
            show();
        }
    }

    public void postShow() {
        this.f.post(this.G);
    }

    public void show() {
        boolean z = true;
        boolean z2 = false;
        int i = -1;
        int b = b();
        boolean isInputMethodNotNeeded = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.g, this.o);
        int i2;
        PopupWindow popupWindow;
        if (!this.g.isShowing()) {
            if (this.l == -1) {
                i2 = -1;
            } else if (this.l == -2) {
                i2 = getAnchorView().getWidth();
            } else {
                i2 = this.l;
            }
            if (this.k == -1) {
                b = -1;
            } else if (this.k != -2) {
                b = this.k;
            }
            this.g.setWidth(i2);
            this.g.setHeight(b);
            a(true);
            popupWindow = this.g;
            if (this.v || this.u) {
                z = false;
            }
            popupWindow.setOutsideTouchable(z);
            this.g.setTouchInterceptor(this.D);
            if (this.s) {
                PopupWindowCompat.setOverlapAnchor(this.g, this.r);
            }
            if (h != null) {
                try {
                    h.invoke(this.g, new Object[]{this.I});
                } catch (Throwable e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
            PopupWindowCompat.showAsDropDown(this.g, getAnchorView(), this.m, this.n, this.t);
            this.c.setSelection(-1);
            if (!this.J || this.c.isInTouchMode()) {
                clearListSelection();
            }
            if (!this.J) {
                this.f.post(this.F);
            }
        } else if (ViewCompat.isAttachedToWindow(getAnchorView())) {
            int i3;
            int i4;
            if (this.l == -1) {
                i3 = -1;
            } else if (this.l == -2) {
                i3 = getAnchorView().getWidth();
            } else {
                i3 = this.l;
            }
            if (this.k == -1) {
                if (!isInputMethodNotNeeded) {
                    b = -1;
                }
                PopupWindow popupWindow2;
                if (isInputMethodNotNeeded) {
                    popupWindow2 = this.g;
                    if (this.l == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow2.setWidth(i2);
                    this.g.setHeight(0);
                    i4 = b;
                } else {
                    popupWindow2 = this.g;
                    if (this.l == -1) {
                        i2 = -1;
                    } else {
                        i2 = 0;
                    }
                    popupWindow2.setWidth(i2);
                    this.g.setHeight(-1);
                    i4 = b;
                }
            } else if (this.k == -2) {
                i4 = b;
            } else {
                i4 = this.k;
            }
            popupWindow = this.g;
            if (!(this.v || this.u)) {
                z2 = true;
            }
            popupWindow.setOutsideTouchable(z2);
            popupWindow = this.g;
            View anchorView = getAnchorView();
            b = this.m;
            int i5 = this.n;
            if (i3 < 0) {
                i3 = -1;
            }
            if (i4 >= 0) {
                i = i4;
            }
            popupWindow.update(anchorView, b, i5, i3, i);
        }
    }

    public void dismiss() {
        this.g.dismiss();
        a();
        this.g.setContentView(null);
        this.c = null;
        this.f.removeCallbacks(this.e);
    }

    public void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        this.g.setOnDismissListener(onDismissListener);
    }

    private void a() {
        if (this.w != null) {
            ViewParent parent = this.w.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.w);
            }
        }
    }

    public void setInputMethodMode(int i) {
        this.g.setInputMethodMode(i);
    }

    public int getInputMethodMode() {
        return this.g.getInputMethodMode();
    }

    public void setSelection(int i) {
        ak akVar = this.c;
        if (isShowing() && akVar != null) {
            akVar.setListSelectionHidden(false);
            akVar.setSelection(i);
            if (akVar.getChoiceMode() != 0) {
                akVar.setItemChecked(i, true);
            }
        }
    }

    public void clearListSelection() {
        ak akVar = this.c;
        if (akVar != null) {
            akVar.setListSelectionHidden(true);
            akVar.requestLayout();
        }
    }

    public boolean isShowing() {
        return this.g.isShowing();
    }

    public boolean isInputMethodNotNeeded() {
        return this.g.getInputMethodMode() == 2;
    }

    public boolean performItemClick(int i) {
        if (!isShowing()) {
            return false;
        }
        if (this.B != null) {
            AdapterView adapterView = this.c;
            View childAt = adapterView.getChildAt(i - adapterView.getFirstVisiblePosition());
            ListAdapter adapter = adapterView.getAdapter();
            this.B.onItemClick(adapterView, childAt, i, adapter.getItemId(i));
        }
        return true;
    }

    @Nullable
    public Object getSelectedItem() {
        if (isShowing()) {
            return this.c.getSelectedItem();
        }
        return null;
    }

    public int getSelectedItemPosition() {
        if (isShowing()) {
            return this.c.getSelectedItemPosition();
        }
        return -1;
    }

    public long getSelectedItemId() {
        if (isShowing()) {
            return this.c.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    @Nullable
    public View getSelectedView() {
        if (isShowing()) {
            return this.c.getSelectedView();
        }
        return null;
    }

    @Nullable
    public ListView getListView() {
        return this.c;
    }

    @NonNull
    ak a(Context context, boolean z) {
        return new ak(context, z);
    }

    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        if (isShowing() && i != 62 && (this.c.getSelectedItemPosition() >= 0 || !a(i))) {
            int selectedItemPosition = this.c.getSelectedItemPosition();
            boolean z = !this.g.isAboveAnchor();
            ListAdapter listAdapter = this.j;
            int i2 = Integer.MAX_VALUE;
            int i3 = Integer.MIN_VALUE;
            if (listAdapter != null) {
                boolean areAllItemsEnabled = listAdapter.areAllItemsEnabled();
                if (areAllItemsEnabled) {
                    i2 = 0;
                } else {
                    i2 = this.c.lookForSelectablePosition(0, true);
                }
                if (areAllItemsEnabled) {
                    i3 = listAdapter.getCount() - 1;
                } else {
                    i3 = this.c.lookForSelectablePosition(listAdapter.getCount() - 1, false);
                }
            }
            if (!(z && i == 19 && selectedItemPosition <= r4) && (z || i != 20 || selectedItemPosition < i3)) {
                this.c.setListSelectionHidden(false);
                if (this.c.onKeyDown(i, keyEvent)) {
                    this.g.setInputMethodMode(2);
                    this.c.requestFocusFromTouch();
                    show();
                    switch (i) {
                        case 19:
                        case 20:
                        case 23:
                        case 66:
                            return true;
                    }
                } else if (z && i == 20) {
                    if (selectedItemPosition == i3) {
                        return true;
                    }
                } else if (!z && i == 19 && selectedItemPosition == r4) {
                    return true;
                }
            }
            clearListSelection();
            this.g.setInputMethodMode(1);
            show();
            return true;
        }
        return false;
    }

    public boolean onKeyUp(int i, @NonNull KeyEvent keyEvent) {
        if (!isShowing() || this.c.getSelectedItemPosition() < 0) {
            return false;
        }
        boolean onKeyUp = this.c.onKeyUp(i, keyEvent);
        if (!onKeyUp || !a(i)) {
            return onKeyUp;
        }
        dismiss();
        return onKeyUp;
    }

    public boolean onKeyPreIme(int i, @NonNull KeyEvent keyEvent) {
        if (i == 4 && isShowing()) {
            View view = this.z;
            DispatcherState keyDispatcherState;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                keyDispatcherState = view.getKeyDispatcherState();
                if (keyDispatcherState == null) {
                    return true;
                }
                keyDispatcherState.startTracking(keyEvent, this);
                return true;
            } else if (keyEvent.getAction() == 1) {
                keyDispatcherState = view.getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    dismiss();
                    return true;
                }
            }
        }
        return false;
    }

    public OnTouchListener createDragToOpenListener(View view) {
        return new bc(this, view);
    }

    private int b() {
        int i;
        int i2;
        int i3;
        int i4;
        boolean z = true;
        LayoutParams layoutParams;
        View view;
        if (this.c == null) {
            Context context = this.i;
            this.G = new bd(this);
            this.c = a(context, !this.J);
            if (this.A != null) {
                this.c.setSelector(this.A);
            }
            this.c.setAdapter(this.j);
            this.c.setOnItemClickListener(this.B);
            this.c.setFocusable(true);
            this.c.setFocusableInTouchMode(true);
            this.c.setOnItemSelectedListener(new be(this));
            this.c.setOnScrollListener(this.E);
            if (this.C != null) {
                this.c.setOnItemSelectedListener(this.C);
            }
            View view2 = this.c;
            View view3 = this.w;
            if (view3 != null) {
                View linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, 0, 1.0f);
                switch (this.x) {
                    case 0:
                        linearLayout.addView(view3);
                        linearLayout.addView(view2, layoutParams2);
                        break;
                    case 1:
                        linearLayout.addView(view2, layoutParams2);
                        linearLayout.addView(view3);
                        break;
                    default:
                        Log.e("ListPopupWindow", "Invalid hint position " + this.x);
                        break;
                }
                if (this.l >= 0) {
                    i = this.l;
                    i2 = Integer.MIN_VALUE;
                } else {
                    i2 = 0;
                    i = 0;
                }
                view3.measure(MeasureSpec.makeMeasureSpec(i, i2), 0);
                layoutParams = (LayoutParams) view3.getLayoutParams();
                i2 = layoutParams.bottomMargin + (view3.getMeasuredHeight() + layoutParams.topMargin);
                view = linearLayout;
            } else {
                view = view2;
                i2 = 0;
            }
            this.g.setContentView(view);
            i3 = i2;
        } else {
            ViewGroup viewGroup = (ViewGroup) this.g.getContentView();
            view = this.w;
            if (view != null) {
                layoutParams = (LayoutParams) view.getLayoutParams();
                i3 = layoutParams.bottomMargin + (view.getMeasuredHeight() + layoutParams.topMargin);
            } else {
                i3 = 0;
            }
        }
        Drawable background = this.g.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            i2 = this.H.top + this.H.bottom;
            if (this.p) {
                i4 = i2;
            } else {
                this.n = -this.H.top;
                i4 = i2;
            }
        } else {
            this.H.setEmpty();
            i4 = 0;
        }
        if (this.g.getInputMethodMode() != 2) {
            z = false;
        }
        i = a(getAnchorView(), this.n, z);
        if (this.u || this.k == -1) {
            return i + i4;
        }
        int makeMeasureSpec;
        switch (this.l) {
            case -2:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), Integer.MIN_VALUE);
                break;
            case -1:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.i.getResources().getDisplayMetrics().widthPixels - (this.H.left + this.H.right), 1073741824);
                break;
            default:
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(this.l, 1073741824);
                break;
        }
        i2 = this.c.measureHeightOfChildrenCompat(makeMeasureSpec, 0, -1, i - i3, -1);
        if (i2 > 0) {
            i3 += (this.c.getPaddingTop() + this.c.getPaddingBottom()) + i4;
        }
        return i2 + i3;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setOverlapAnchor(boolean z) {
        this.s = true;
        this.r = z;
    }

    private static boolean a(int i) {
        return i == 66 || i == 23;
    }

    private void a(boolean z) {
        if (a != null) {
            try {
                a.invoke(this.g, new Object[]{Boolean.valueOf(z)});
            } catch (Exception e) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int a(View view, int i, boolean z) {
        if (b != null) {
            try {
                return ((Integer) b.invoke(this.g, new Object[]{view, Integer.valueOf(i), Boolean.valueOf(z)})).intValue();
            } catch (Exception e) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.g.getMaxAvailableHeight(view, i);
    }
}
