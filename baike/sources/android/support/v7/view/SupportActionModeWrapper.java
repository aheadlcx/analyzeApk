package android.support.v7.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SupportActionModeWrapper extends ActionMode {
    final Context a;
    final ActionMode b;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class CallbackWrapper implements Callback {
        final ActionMode.Callback a;
        final Context b;
        final ArrayList<SupportActionModeWrapper> c = new ArrayList();
        final SimpleArrayMap<Menu, Menu> d = new SimpleArrayMap();

        public CallbackWrapper(Context context, ActionMode.Callback callback) {
            this.b = context;
            this.a = callback;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.a.onCreateActionMode(getActionModeWrapper(actionMode), a(menu));
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.a.onPrepareActionMode(getActionModeWrapper(actionMode), a(menu));
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.a.onActionItemClicked(getActionModeWrapper(actionMode), MenuWrapperFactory.wrapSupportMenuItem(this.b, (SupportMenuItem) menuItem));
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.a.onDestroyActionMode(getActionModeWrapper(actionMode));
        }

        private Menu a(Menu menu) {
            Menu menu2 = (Menu) this.d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            menu2 = MenuWrapperFactory.wrapSupportMenu(this.b, (SupportMenu) menu);
            this.d.put(menu, menu2);
            return menu2;
        }

        public ActionMode getActionModeWrapper(ActionMode actionMode) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                SupportActionModeWrapper supportActionModeWrapper = (SupportActionModeWrapper) this.c.get(i);
                if (supportActionModeWrapper != null && supportActionModeWrapper.b == actionMode) {
                    return supportActionModeWrapper;
                }
            }
            ActionMode supportActionModeWrapper2 = new SupportActionModeWrapper(this.b, actionMode);
            this.c.add(supportActionModeWrapper2);
            return supportActionModeWrapper2;
        }
    }

    public SupportActionModeWrapper(Context context, ActionMode actionMode) {
        this.a = context;
        this.b = actionMode;
    }

    public Object getTag() {
        return this.b.getTag();
    }

    public void setTag(Object obj) {
        this.b.setTag(obj);
    }

    public void setTitle(CharSequence charSequence) {
        this.b.setTitle(charSequence);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.b.setSubtitle(charSequence);
    }

    public void invalidate() {
        this.b.invalidate();
    }

    public void finish() {
        this.b.finish();
    }

    public Menu getMenu() {
        return MenuWrapperFactory.wrapSupportMenu(this.a, (SupportMenu) this.b.getMenu());
    }

    public CharSequence getTitle() {
        return this.b.getTitle();
    }

    public void setTitle(int i) {
        this.b.setTitle(i);
    }

    public CharSequence getSubtitle() {
        return this.b.getSubtitle();
    }

    public void setSubtitle(int i) {
        this.b.setSubtitle(i);
    }

    public View getCustomView() {
        return this.b.getCustomView();
    }

    public void setCustomView(View view) {
        this.b.setCustomView(view);
    }

    public MenuInflater getMenuInflater() {
        return this.b.getMenuInflater();
    }

    public boolean getTitleOptionalHint() {
        return this.b.getTitleOptionalHint();
    }

    public void setTitleOptionalHint(boolean z) {
        this.b.setTitleOptionalHint(z);
    }

    public boolean isTitleOptional() {
        return this.b.isTitleOptional();
    }
}
