package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.appcompat.R;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnTouchListener;

public class PopupMenu {
    final MenuPopupHelper a;
    OnMenuItemClickListener b;
    OnDismissListener c;
    private final Context d;
    private final MenuBuilder e;
    private final View f;
    private OnTouchListener g;

    public interface OnDismissListener {
        void onDismiss(PopupMenu popupMenu);
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public PopupMenu(@NonNull Context context, @NonNull View view) {
        this(context, view, 0);
    }

    public PopupMenu(@NonNull Context context, @NonNull View view, int i) {
        this(context, view, i, R.attr.popupMenuStyle, 0);
    }

    public PopupMenu(@NonNull Context context, @NonNull View view, int i, @AttrRes int i2, @StyleRes int i3) {
        this.d = context;
        this.f = view;
        this.e = new MenuBuilder(context);
        this.e.setCallback(new bj(this));
        this.a = new MenuPopupHelper(context, this.e, view, false, i2, i3);
        this.a.setGravity(i);
        this.a.setOnDismissListener(new bk(this));
    }

    public void setGravity(int i) {
        this.a.setGravity(i);
    }

    public int getGravity() {
        return this.a.getGravity();
    }

    @NonNull
    public OnTouchListener getDragToOpenListener() {
        if (this.g == null) {
            this.g = new bl(this, this.f);
        }
        return this.g;
    }

    @NonNull
    public Menu getMenu() {
        return this.e;
    }

    @NonNull
    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.d);
    }

    public void inflate(@MenuRes int i) {
        getMenuInflater().inflate(i, this.e);
    }

    public void show() {
        this.a.show();
    }

    public void dismiss() {
        this.a.dismiss();
    }

    public void setOnMenuItemClickListener(@Nullable OnMenuItemClickListener onMenuItemClickListener) {
        this.b = onMenuItemClickListener;
    }

    public void setOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        this.c = onDismissListener;
    }
}
