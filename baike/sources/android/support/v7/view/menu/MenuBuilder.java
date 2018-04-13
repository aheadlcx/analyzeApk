package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v7.appcompat.R;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MenuBuilder implements SupportMenu {
    private static final int[] d = new int[]{1, 4, 5, 3, 2, 0};
    CharSequence a;
    Drawable b;
    View c;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private Callback i;
    private ArrayList<MenuItemImpl> j;
    private ArrayList<MenuItemImpl> k;
    private boolean l;
    private ArrayList<MenuItemImpl> m;
    private ArrayList<MenuItemImpl> n;
    private boolean o;
    private int p = 0;
    private ContextMenuInfo q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private ArrayList<MenuItemImpl> w = new ArrayList();
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> x = new CopyOnWriteArrayList();
    private MenuItemImpl y;
    private boolean z;

    @RestrictTo({Scope.LIBRARY_GROUP})
    public interface Callback {
        boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem);

        void onMenuModeChange(MenuBuilder menuBuilder);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public interface ItemInvoker {
        boolean invokeItem(MenuItemImpl menuItemImpl);
    }

    public MenuBuilder(Context context) {
        this.e = context;
        this.f = context.getResources();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = true;
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = true;
        b(true);
    }

    public MenuBuilder setDefaultShowAsAction(int i) {
        this.p = i;
        return this;
    }

    public void addMenuPresenter(MenuPresenter menuPresenter) {
        addMenuPresenter(menuPresenter, this.e);
    }

    public void addMenuPresenter(MenuPresenter menuPresenter, Context context) {
        this.x.add(new WeakReference(menuPresenter));
        menuPresenter.initForMenu(context, this);
        this.o = true;
    }

    public void removeMenuPresenter(MenuPresenter menuPresenter) {
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) weakReference.get();
            if (menuPresenter2 == null || menuPresenter2 == menuPresenter) {
                this.x.remove(weakReference);
            }
        }
    }

    private void a(boolean z) {
        if (!this.x.isEmpty()) {
            stopDispatchingItemsChanged();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.x.remove(weakReference);
                } else {
                    menuPresenter.updateMenuView(z);
                }
            }
            startDispatchingItemsChanged();
        }
    }

    private boolean a(SubMenuBuilder subMenuBuilder, MenuPresenter menuPresenter) {
        boolean z = false;
        if (this.x.isEmpty()) {
            return false;
        }
        if (menuPresenter != null) {
            z = menuPresenter.onSubMenuSelected(subMenuBuilder);
        }
        Iterator it = this.x.iterator();
        boolean z2 = z;
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) weakReference.get();
            if (menuPresenter2 == null) {
                this.x.remove(weakReference);
                z = z2;
            } else if (z2) {
                z = z2;
            } else {
                z = menuPresenter2.onSubMenuSelected(subMenuBuilder);
            }
            z2 = z;
        }
        return z2;
    }

    private void a(Bundle bundle) {
        if (!this.x.isEmpty()) {
            SparseArray sparseArray = new SparseArray();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.x.remove(weakReference);
                } else {
                    int id = menuPresenter.getId();
                    if (id > 0) {
                        Parcelable onSaveInstanceState = menuPresenter.onSaveInstanceState();
                        if (onSaveInstanceState != null) {
                            sparseArray.put(id, onSaveInstanceState);
                        }
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
        }
    }

    private void b(Bundle bundle) {
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null && !this.x.isEmpty()) {
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.x.remove(weakReference);
                } else {
                    int id = menuPresenter.getId();
                    if (id > 0) {
                        Parcelable parcelable = (Parcelable) sparseParcelableArray.get(id);
                        if (parcelable != null) {
                            menuPresenter.onRestoreInstanceState(parcelable);
                        }
                    }
                }
            }
        }
    }

    public void savePresenterStates(Bundle bundle) {
        a(bundle);
    }

    public void restorePresenterStates(Bundle bundle) {
        b(bundle);
    }

    public void saveActionViewStates(Bundle bundle) {
        int size = size();
        int i = 0;
        SparseArray sparseArray = null;
        while (i < size) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            SparseArray sparseArray2 = sparseArray;
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).saveActionViewStates(bundle);
            }
            i++;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(getActionViewStatesKey(), sparseArray);
        }
    }

    public void restoreActionViewStates(Bundle bundle) {
        if (bundle != null) {
            MenuItem item;
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(getActionViewStatesKey());
            int size = size();
            for (int i = 0; i < size; i++) {
                item = getItem(i);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuBuilder) item.getSubMenu()).restoreActionViewStates(bundle);
                }
            }
            int i2 = bundle.getInt("android:menu:expandedactionview");
            if (i2 > 0) {
                item = findItem(i2);
                if (item != null) {
                    item.expandActionView();
                }
            }
        }
    }

    protected String getActionViewStatesKey() {
        return "android:menu:actionviewstates";
    }

    public void setCallback(Callback callback) {
        this.i = callback;
    }

    protected MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int c = c(i3);
        MenuItem a = a(i, i2, i3, c, charSequence, this.p);
        if (this.q != null) {
            a.a(this.q);
        }
        this.j.add(a(this.j, c), a);
        onItemsChanged(true);
        return a;
    }

    private MenuItemImpl a(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new MenuItemImpl(this, i, i2, i3, i4, charSequence, i5);
    }

    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    public MenuItem add(int i) {
        return a(0, 0, 0, this.f.getString(i));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.f.getString(i4));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f.getString(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) a(i, i2, i3, charSequence);
        SubMenu subMenuBuilder = new SubMenuBuilder(this.e, this, menuItemImpl);
        menuItemImpl.setSubMenu(subMenuBuilder);
        return subMenuBuilder;
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f.getString(i4));
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.e.getPackageManager();
        List queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i5 = 0; i5 < size; i5++) {
            Intent intent2;
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivityOptions.get(i5);
            if (resolveInfo.specificIndex < 0) {
                intent2 = intent;
            } else {
                intent2 = intentArr[resolveInfo.specificIndex];
            }
            Intent intent3 = new Intent(intent2);
            intent3.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent4 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent3);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent4;
            }
        }
        return size;
    }

    public void removeItem(int i) {
        a(findItemIndex(i), true);
    }

    public void removeGroup(int i) {
        int findGroupIndex = findGroupIndex(i);
        if (findGroupIndex >= 0) {
            int size = this.j.size() - findGroupIndex;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || ((MenuItemImpl) this.j.get(findGroupIndex)).getGroupId() != i) {
                    onItemsChanged(true);
                } else {
                    a(findGroupIndex, false);
                    i2 = i3;
                }
            }
            onItemsChanged(true);
        }
    }

    private void a(int i, boolean z) {
        if (i >= 0 && i < this.j.size()) {
            this.j.remove(i);
            if (z) {
                onItemsChanged(true);
            }
        }
    }

    public void removeItemAt(int i) {
        a(i, true);
    }

    public void clearAll() {
        this.r = true;
        clear();
        clearHeader();
        this.r = false;
        this.s = false;
        this.t = false;
        onItemsChanged(true);
    }

    public void clear() {
        if (this.y != null) {
            collapseItemActionView(this.y);
        }
        this.j.clear();
        onItemsChanged(true);
    }

    void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.j.size();
        stopDispatchingItemsChanged();
        for (int i = 0; i < size; i++) {
            MenuItem menuItem2 = (MenuItemImpl) this.j.get(i);
            if (menuItem2.getGroupId() == groupId && menuItem2.isExclusiveCheckable() && menuItem2.isCheckable()) {
                menuItem2.a(menuItem2 == menuItem);
            }
        }
        startDispatchingItemsChanged();
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i2);
            if (menuItemImpl.getGroupId() == i) {
                menuItemImpl.setExclusiveCheckable(z2);
                menuItemImpl.setCheckable(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int size = this.j.size();
        int i2 = 0;
        boolean z2 = false;
        while (i2 < size) {
            boolean z3;
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i2);
            if (menuItemImpl.getGroupId() == i && menuItemImpl.b(z)) {
                z3 = true;
            } else {
                z3 = z2;
            }
            i2++;
            z2 = z3;
        }
        if (z2) {
            onItemsChanged(true);
        }
    }

    public void setGroupEnabled(int i, boolean z) {
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i2);
            if (menuItemImpl.getGroupId() == i) {
                menuItemImpl.setEnabled(z);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.z) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (((MenuItemImpl) this.j.get(i)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i2);
            if (menuItemImpl.getItemId() == i) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu()) {
                MenuItem findItem = menuItemImpl.getSubMenu().findItem(i);
                if (findItem != null) {
                    return findItem;
                }
            }
        }
        return null;
    }

    public int findItemIndex(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((MenuItemImpl) this.j.get(i2)).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public int findGroupIndex(int i) {
        return findGroupIndex(i, 0);
    }

    public int findGroupIndex(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        for (int i3 = i2; i3 < size; i3++) {
            if (((MenuItemImpl) this.j.get(i3)).getGroupId() == i) {
                return i3;
            }
        }
        return -1;
    }

    public int size() {
        return this.j.size();
    }

    public MenuItem getItem(int i) {
        return (MenuItem) this.j.get(i);
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return a(i, keyEvent) != null;
    }

    public void setQwertyMode(boolean z) {
        this.g = z;
        onItemsChanged(false);
    }

    private static int c(int i) {
        int i2 = (-65536 & i) >> 16;
        if (i2 >= 0 && i2 < d.length) {
            return (d[i2] << 16) | (SupportMenu.USER_MASK & i);
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    boolean isQwertyMode() {
        return this.g;
    }

    public void setShortcutsVisible(boolean z) {
        if (this.h != z) {
            b(z);
            onItemsChanged(false);
        }
    }

    private void b(boolean z) {
        boolean z2 = true;
        if (!(z && this.f.getConfiguration().keyboard != 1 && this.f.getBoolean(R.bool.abc_config_showMenuShortcutsWhenKeyboardPresent))) {
            z2 = false;
        }
        this.h = z2;
    }

    public boolean isShortcutsVisible() {
        return this.h;
    }

    Resources a() {
        return this.f;
    }

    public Context getContext() {
        return this.e;
    }

    boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.i != null && this.i.onMenuItemSelected(menuBuilder, menuItem);
    }

    public void changeMenuMode() {
        if (this.i != null) {
            this.i.onMenuModeChange(this);
        }
    }

    private static int a(ArrayList<MenuItemImpl> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((MenuItemImpl) arrayList.get(size)).getOrdering() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        MenuItem a = a(i, keyEvent);
        boolean z = false;
        if (a != null) {
            z = performItemAction(a, i2);
        }
        if ((i2 & 2) != 0) {
            close(true);
        }
        return z;
    }

    void a(List<MenuItemImpl> list, int i, KeyEvent keyEvent) {
        boolean isQwertyMode = isQwertyMode();
        int modifiers = keyEvent.getModifiers();
        KeyData keyData = new KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.j.size();
            for (int i2 = 0; i2 < size; i2++) {
                int i3;
                MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i2);
                if (menuItemImpl.hasSubMenu()) {
                    ((MenuBuilder) menuItemImpl.getSubMenu()).a(list, i, keyEvent);
                }
                char alphabeticShortcut = isQwertyMode ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
                if ((modifiers & SupportMenu.SUPPORTED_MODIFIERS_MASK) == ((isQwertyMode ? menuItemImpl.getAlphabeticModifiers() : menuItemImpl.getNumericModifiers()) & SupportMenu.SUPPORTED_MODIFIERS_MASK)) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                if (!(i3 == 0 || alphabeticShortcut == '\u0000' || ((alphabeticShortcut != keyData.meta[0] && alphabeticShortcut != keyData.meta[2] && (!isQwertyMode || alphabeticShortcut != '\b' || i != 67)) || !menuItemImpl.isEnabled()))) {
                    list.add(menuItemImpl);
                }
            }
        }
    }

    MenuItemImpl a(int i, KeyEvent keyEvent) {
        ArrayList arrayList = this.w;
        arrayList.clear();
        a(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (MenuItemImpl) arrayList.get(0);
        }
        boolean isQwertyMode = isQwertyMode();
        for (int i2 = 0; i2 < size; i2++) {
            char alphabeticShortcut;
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i2);
            if (isQwertyMode) {
                alphabeticShortcut = menuItemImpl.getAlphabeticShortcut();
            } else {
                alphabeticShortcut = menuItemImpl.getNumericShortcut();
            }
            if (alphabeticShortcut == keyData.meta[0] && (metaState & 2) == 0) {
                return menuItemImpl;
            }
            if (alphabeticShortcut == keyData.meta[2] && (metaState & 2) != 0) {
                return menuItemImpl;
            }
            if (isQwertyMode && alphabeticShortcut == '\b' && i == 67) {
                return menuItemImpl;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int i, int i2) {
        return performItemAction(findItem(i), i2);
    }

    public boolean performItemAction(MenuItem menuItem, int i) {
        return performItemAction(menuItem, null, i);
    }

    public boolean performItemAction(MenuItem menuItem, MenuPresenter menuPresenter, int i) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
        if (menuItemImpl == null || !menuItemImpl.isEnabled()) {
            return false;
        }
        boolean z;
        boolean invoke = menuItemImpl.invoke();
        ActionProvider supportActionProvider = menuItemImpl.getSupportActionProvider();
        if (supportActionProvider == null || !supportActionProvider.hasSubMenu()) {
            z = false;
        } else {
            z = true;
        }
        boolean expandActionView;
        if (menuItemImpl.hasCollapsibleActionView()) {
            expandActionView = menuItemImpl.expandActionView() | invoke;
            if (!expandActionView) {
                return expandActionView;
            }
            close(true);
            return expandActionView;
        } else if (menuItemImpl.hasSubMenu() || z) {
            if ((i & 4) == 0) {
                close(false);
            }
            if (!menuItemImpl.hasSubMenu()) {
                menuItemImpl.setSubMenu(new SubMenuBuilder(getContext(), this, menuItemImpl));
            }
            SubMenuBuilder subMenuBuilder = (SubMenuBuilder) menuItemImpl.getSubMenu();
            if (z) {
                supportActionProvider.onPrepareSubMenu(subMenuBuilder);
            }
            expandActionView = a(subMenuBuilder, menuPresenter) | invoke;
            if (expandActionView) {
                return expandActionView;
            }
            close(true);
            return expandActionView;
        } else {
            if ((i & 1) == 0) {
                close(true);
            }
            return invoke;
        }
    }

    public final void close(boolean z) {
        if (!this.v) {
            this.v = true;
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.x.remove(weakReference);
                } else {
                    menuPresenter.onCloseMenu(this, z);
                }
            }
            this.v = false;
        }
    }

    public void close() {
        close(true);
    }

    public void onItemsChanged(boolean z) {
        if (this.r) {
            this.s = true;
            if (z) {
                this.t = true;
                return;
            }
            return;
        }
        if (z) {
            this.l = true;
            this.o = true;
        }
        a(z);
    }

    public void stopDispatchingItemsChanged() {
        if (!this.r) {
            this.r = true;
            this.s = false;
            this.t = false;
        }
    }

    public void startDispatchingItemsChanged() {
        this.r = false;
        if (this.s) {
            this.s = false;
            onItemsChanged(this.t);
        }
    }

    void a(MenuItemImpl menuItemImpl) {
        this.l = true;
        onItemsChanged(true);
    }

    void b(MenuItemImpl menuItemImpl) {
        this.o = true;
        onItemsChanged(true);
    }

    @NonNull
    public ArrayList<MenuItemImpl> getVisibleItems() {
        if (!this.l) {
            return this.k;
        }
        this.k.clear();
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.j.get(i);
            if (menuItemImpl.isVisible()) {
                this.k.add(menuItemImpl);
            }
        }
        this.l = false;
        this.o = true;
        return this.k;
    }

    public void flagActionItems() {
        ArrayList visibleItems = getVisibleItems();
        if (this.o) {
            Iterator it = this.x.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2;
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.x.remove(weakReference);
                    i2 = i;
                } else {
                    i2 = menuPresenter.flagActionItems() | i;
                }
                i = i2;
            }
            if (i != 0) {
                this.m.clear();
                this.n.clear();
                i = visibleItems.size();
                for (int i3 = 0; i3 < i; i3++) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) visibleItems.get(i3);
                    if (menuItemImpl.isActionButton()) {
                        this.m.add(menuItemImpl);
                    } else {
                        this.n.add(menuItemImpl);
                    }
                }
            } else {
                this.m.clear();
                this.n.clear();
                this.n.addAll(getVisibleItems());
            }
            this.o = false;
        }
    }

    public ArrayList<MenuItemImpl> getActionItems() {
        flagActionItems();
        return this.m;
    }

    public ArrayList<MenuItemImpl> getNonActionItems() {
        flagActionItems();
        return this.n;
    }

    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        onItemsChanged(false);
    }

    private void a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources a = a();
        if (view != null) {
            this.c = view;
            this.a = null;
            this.b = null;
        } else {
            if (i > 0) {
                this.a = a.getText(i);
            } else if (charSequence != null) {
                this.a = charSequence;
            }
            if (i2 > 0) {
                this.b = ContextCompat.getDrawable(getContext(), i2);
            } else if (drawable != null) {
                this.b = drawable;
            }
            this.c = null;
        }
        onItemsChanged(false);
    }

    protected MenuBuilder a(CharSequence charSequence) {
        a(0, charSequence, 0, null, null);
        return this;
    }

    protected MenuBuilder a(int i) {
        a(i, null, 0, null, null);
        return this;
    }

    protected MenuBuilder a(Drawable drawable) {
        a(0, null, 0, drawable, null);
        return this;
    }

    protected MenuBuilder b(int i) {
        a(0, null, i, null, null);
        return this;
    }

    protected MenuBuilder a(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    public CharSequence getHeaderTitle() {
        return this.a;
    }

    public Drawable getHeaderIcon() {
        return this.b;
    }

    public View getHeaderView() {
        return this.c;
    }

    public MenuBuilder getRootMenu() {
        return this;
    }

    public void setCurrentMenuInfo(ContextMenuInfo contextMenuInfo) {
        this.q = contextMenuInfo;
    }

    public void setOptionalIconsVisible(boolean z) {
        this.u = z;
    }

    boolean b() {
        return this.u;
    }

    public boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (!this.x.isEmpty()) {
            stopDispatchingItemsChanged();
            Iterator it = this.x.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.x.remove(weakReference);
                    z = z2;
                } else {
                    z = menuPresenter.expandItemActionView(this, menuItemImpl);
                    if (z) {
                        break;
                    }
                }
                z2 = z;
            }
            z = z2;
            startDispatchingItemsChanged();
            if (z) {
                this.y = menuItemImpl;
            }
        }
        return z;
    }

    public boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (!this.x.isEmpty() && this.y == menuItemImpl) {
            stopDispatchingItemsChanged();
            Iterator it = this.x.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.x.remove(weakReference);
                    z = z2;
                } else {
                    z = menuPresenter.collapseItemActionView(this, menuItemImpl);
                    if (z) {
                        break;
                    }
                }
                z2 = z;
            }
            z = z2;
            startDispatchingItemsChanged();
            if (z) {
                this.y = null;
            }
        }
        return z;
    }

    public MenuItemImpl getExpandedItem() {
        return this.y;
    }

    public void setOverrideVisibleItems(boolean z) {
        this.z = z;
    }
}
