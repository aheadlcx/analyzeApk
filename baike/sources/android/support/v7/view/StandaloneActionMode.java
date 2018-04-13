package android.support.v7.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

@RestrictTo({Scope.LIBRARY_GROUP})
public class StandaloneActionMode extends ActionMode implements Callback {
    private Context a;
    private ActionBarContextView b;
    private ActionMode.Callback c;
    private WeakReference<View> d;
    private boolean e;
    private boolean f;
    private MenuBuilder g;

    public StandaloneActionMode(Context context, ActionBarContextView actionBarContextView, ActionMode.Callback callback, boolean z) {
        this.a = context;
        this.b = actionBarContextView;
        this.c = callback;
        this.g = new MenuBuilder(actionBarContextView.getContext()).setDefaultShowAsAction(1);
        this.g.setCallback(this);
        this.f = z;
    }

    public void setTitle(CharSequence charSequence) {
        this.b.setTitle(charSequence);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.b.setSubtitle(charSequence);
    }

    public void setTitle(int i) {
        setTitle(this.a.getString(i));
    }

    public void setSubtitle(int i) {
        setSubtitle(this.a.getString(i));
    }

    public void setTitleOptionalHint(boolean z) {
        super.setTitleOptionalHint(z);
        this.b.setTitleOptional(z);
    }

    public boolean isTitleOptional() {
        return this.b.isTitleOptional();
    }

    public void setCustomView(View view) {
        this.b.setCustomView(view);
        this.d = view != null ? new WeakReference(view) : null;
    }

    public void invalidate() {
        this.c.onPrepareActionMode(this, this.g);
    }

    public void finish() {
        if (!this.e) {
            this.e = true;
            this.b.sendAccessibilityEvent(32);
            this.c.onDestroyActionMode(this);
        }
    }

    public Menu getMenu() {
        return this.g;
    }

    public CharSequence getTitle() {
        return this.b.getTitle();
    }

    public CharSequence getSubtitle() {
        return this.b.getSubtitle();
    }

    public View getCustomView() {
        return this.d != null ? (View) this.d.get() : null;
    }

    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.b.getContext());
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.c.onActionItemClicked(this, menuItem);
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            new MenuPopupHelper(this.b.getContext(), subMenuBuilder).show();
        }
        return true;
    }

    public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        invalidate();
        this.b.showOverflowMenu();
    }

    public boolean isUiFocusable() {
        return this.f;
    }
}
