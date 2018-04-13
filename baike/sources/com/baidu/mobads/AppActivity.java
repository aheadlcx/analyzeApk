package com.baidu.mobads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.baidu.mobads.g.b;
import com.baidu.mobads.g.g;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.utils.l;
import dalvik.system.DexClassLoader;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppActivity extends Activity {
    private static ActionBarColorTheme a = ActionBarColorTheme.ACTION_BAR_WHITE_THEME;
    private static boolean b = false;
    private static Class<?> d;
    private static AtomicBoolean f = new AtomicBoolean(false);
    private Object c;
    private Method[] e = null;

    public static class ActionBarColorTheme {
        public static final ActionBarColorTheme ACTION_BAR_BLACK_THEME = new ActionBarColorTheme(-1, -1, -12510, -13749450);
        public static final ActionBarColorTheme ACTION_BAR_BLUE_THEME = new ActionBarColorTheme(-1, -1, -12510, -13870424);
        public static final ActionBarColorTheme ACTION_BAR_COFFEE_THEME = new ActionBarColorTheme(-1, -1, -12510, -11255230);
        public static final ActionBarColorTheme ACTION_BAR_GREEN_THEME = new ActionBarColorTheme(-1, -1, -11113262, -14303071);
        public static final ActionBarColorTheme ACTION_BAR_NAVYBLUE_THEME = new ActionBarColorTheme(-1, -1, 16764706, -14210226);
        public static final ActionBarColorTheme ACTION_BAR_RED_THEME = new ActionBarColorTheme(-1, -1, -12510, -1294276);
        public static final ActionBarColorTheme ACTION_BAR_WHITE_THEME = new ActionBarColorTheme(-5987164, -6842473, -11113262, -328966);
        private int a;
        private int b;
        private int c;
        private int d;

        public ActionBarColorTheme(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public ActionBarColorTheme(ActionBarColorTheme actionBarColorTheme) {
            this.a = actionBarColorTheme.a;
            this.b = actionBarColorTheme.b;
            this.c = actionBarColorTheme.c;
            this.d = actionBarColorTheme.d;
        }

        public int getCloseColor() {
            return this.a;
        }

        public void setCloseColor(int i) {
            this.a = i;
        }

        public int getTitleColor() {
            return this.b;
        }

        public void setTitleColor(int i) {
            this.b = i;
        }

        public int getProgressColor() {
            return this.c;
        }

        public void setProgressColor(int i) {
            this.c = i;
        }

        public int getBackgroundColor() {
            return this.d;
        }

        public void setBackgroundColor(int i) {
            this.d = i;
        }

        public boolean equals(Object obj) {
            ActionBarColorTheme actionBarColorTheme = (ActionBarColorTheme) obj;
            return this.d == actionBarColorTheme.d && this.b == actionBarColorTheme.b && this.a == actionBarColorTheme.a && this.c == actionBarColorTheme.c;
        }
    }

    public static ActionBarColorTheme getActionBarColorTheme() {
        return a;
    }

    public static void setActionBarColorTheme(ActionBarColorTheme actionBarColorTheme) {
        if (actionBarColorTheme != null) {
            a = new ActionBarColorTheme(actionBarColorTheme);
        }
    }

    public static void canLpShowWhenLocked(boolean z) {
        b = z;
    }

    public static boolean isAppActivityOpening() {
        return f.get();
    }

    private Method a(String str) {
        if (this.e == null) {
            return null;
        }
        for (Method method : this.e) {
            if (method.getName().equals(str)) {
                method.setAccessible(true);
                return method;
            }
        }
        return null;
    }

    private void a(String str, Object... objArr) {
        int i = 0;
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            if (objArr != null) {
                i = objArr.length;
            }
            objArr2[1] = Integer.valueOf(i);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method a2 = a(str);
            if (a2 == null) {
                return;
            }
            if (objArr == null || objArr.length == 0) {
                a2.invoke(null, new Object[0]);
            } else {
                a2.invoke(null, objArr);
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
    }

    private void b(String str, Object... objArr) {
        int i = 0;
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            if (objArr != null) {
                i = objArr.length;
            }
            objArr2[1] = Integer.valueOf(i);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method a2 = a(str);
            if (a2 == null) {
                return;
            }
            if (objArr == null || objArr.length == 0) {
                a2.invoke(this.c, new Object[0]);
            } else {
                a2.invoke(this.c, objArr);
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
    }

    private boolean c(String str, Object... objArr) {
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            objArr2[1] = Integer.valueOf(objArr != null ? objArr.length : 0);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method a2 = a(str);
            if (a2 != null) {
                if (objArr == null || objArr.length == 0) {
                    return ((Boolean) a2.invoke(this.c, new Object[0])).booleanValue();
                }
                return ((Boolean) a2.invoke(this.c, objArr)).booleanValue();
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
        return false;
    }

    private Object d(String str, Object... objArr) {
        int i = 0;
        try {
            l a = l.a();
            Object[] objArr2 = new Object[3];
            objArr2[0] = str;
            if (objArr != null) {
                i = objArr.length;
            }
            objArr2[1] = Integer.valueOf(i);
            objArr2[2] = objArr;
            a.d(objArr2);
            Method a2 = a(str);
            if (a2 != null) {
                if (objArr == null || objArr.length == 0) {
                    return a2.invoke(this.c, new Object[0]);
                }
                return a2.invoke(this.c, objArr);
            }
        } catch (Throwable e) {
            l.a().d(e);
        }
        return null;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (c("dispatchKeyEvent", keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (c("dispatchTouchEvent", motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        if (c("dispatchTrackballEvent", motionEvent)) {
            return true;
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    protected void finalize() {
        b("finalize", new Object[0]);
        super.finalize();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        b("onActivityResult", Integer.valueOf(i), Integer.valueOf(i2), intent);
        super.onActivityResult(i, i2, intent);
    }

    protected void onApplyThemeResource(Theme theme, int i, boolean z) {
        b("onApplyThemeResource", theme, Integer.valueOf(i), Boolean.valueOf(z));
        super.onApplyThemeResource(theme, i, z);
    }

    protected void onChildTitleChanged(Activity activity, CharSequence charSequence) {
        b("onChildTitleChanged", activity, charSequence);
        super.onChildTitleChanged(activity, charSequence);
    }

    public void onConfigurationChanged(Configuration configuration) {
        b("onConfigurationChanged", configuration);
        super.onConfigurationChanged(configuration);
    }

    public void onContentChanged() {
        b("onContentChanged", new Object[0]);
        super.onContentChanged();
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        if (c("onContextItemSelected", menuItem)) {
            return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    public void onContextMenuClosed(Menu menu) {
        b("onContextMenuClosed", menu);
        super.onContextMenuClosed(menu);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            f.set(true);
            String str = "com.baidu.mobads.container.landingpage.App2Activity";
            ClassLoader d = b.d();
            if (d == null) {
                d = loadLocalApk(str);
            } else {
                d = Class.forName(str, true, d);
            }
            this.e = d.getDeclaredMethods();
            this.c = d.getConstructor(new Class[]{Activity.class}).newInstance(new Object[]{this});
            a("canLpShowWhenLocked", Boolean.valueOf(b));
            a("setActionBarColor", Integer.valueOf(a.a), Integer.valueOf(a.b), Integer.valueOf(a.c), Integer.valueOf(a.d));
            l.a().d(str, d, this.c);
        } catch (Throwable e) {
            l.a().e(e);
        }
        b("onCreate", bundle);
    }

    public Class<?> loadLocalApk(String str) {
        Class<?> cls = null;
        IXAdLogger a = l.a();
        try {
            cls = Class.forName(str, true, new DexClassLoader(g.a((Context) this), getFilesDir().getAbsolutePath(), null, getClass().getClassLoader()));
        } catch (Throwable e) {
            a.e(e);
        }
        a.i("jar.path=, clz=" + cls);
        return cls;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        b("onCreateContextMenu", contextMenu, view, contextMenuInfo);
    }

    public CharSequence onCreateDescription() {
        CharSequence charSequence = (CharSequence) d("onCreateDescription", new Object[0]);
        return charSequence != null ? charSequence : super.onCreateDescription();
    }

    protected Dialog onCreateDialog(int i) {
        Dialog dialog = (Dialog) d("onCreateDialog", Integer.valueOf(i));
        return dialog != null ? dialog : super.onCreateDialog(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (c("onCreateOptionsMenu", menu)) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (c("onCreatePanelMenu", Integer.valueOf(i), menu)) {
            return true;
        }
        return super.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i) {
        View view = (View) d("onCreatePanelView", Integer.valueOf(i));
        return view != null ? view : super.onCreatePanelView(i);
    }

    public boolean onCreateThumbnail(Bitmap bitmap, Canvas canvas) {
        if (c("onCreateThumbnail", bitmap, canvas)) {
            return true;
        }
        return super.onCreateThumbnail(bitmap, canvas);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View view = (View) d("onCreateView", str, context, attributeSet);
        return view != null ? view : super.onCreateView(str, context, attributeSet);
    }

    protected void onDestroy() {
        f.set(false);
        b("onDestroy", new Object[0]);
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (c("onKeyDown", Integer.valueOf(i), keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        if (c("onKeyMultiple", Integer.valueOf(i), Integer.valueOf(i2), keyEvent)) {
            return true;
        }
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (c("onKeyUp", Integer.valueOf(i), keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void onLowMemory() {
        b("onLowMemory", new Object[0]);
        super.onLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (c("onMenuItemSelected", Integer.valueOf(i), menuItem)) {
            return true;
        }
        return super.onMenuItemSelected(i, menuItem);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        if (c("onMenuOpened", Integer.valueOf(i), menu)) {
            return true;
        }
        return super.onMenuOpened(i, menu);
    }

    protected void onNewIntent(Intent intent) {
        b("onNewIntent", intent);
        super.onNewIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (c("onOptionsItemSelected", menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onOptionsMenuClosed(Menu menu) {
        b("onOptionsMenuClosed", menu);
        super.onOptionsMenuClosed(menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        b("onPanelClosed", Integer.valueOf(i), menu);
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        b("onPause", new Object[0]);
        super.onPause();
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        b("onPostCreate", bundle);
    }

    protected void onPostResume() {
        super.onPostResume();
        b("onPostResume", new Object[0]);
    }

    protected void onPrepareDialog(int i, Dialog dialog) {
        super.onPrepareDialog(i, dialog);
        b("onPrepareDialog", Integer.valueOf(i), dialog);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (c("onPrepareOptionsMenu", menu)) {
            return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (c("onPreparePanel", Integer.valueOf(i), view, menu)) {
            return true;
        }
        return super.onPreparePanel(i, view, menu);
    }

    protected void onRestart() {
        super.onRestart();
        b("onRestart", new Object[0]);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        b("onRestoreInstanceState", bundle);
    }

    protected void onResume() {
        super.onResume();
        b("onResume", new Object[0]);
    }

    public Object onRetainNonConfigurationInstance() {
        Object d = d("onRetainNonConfigurationInstance", new Object[0]);
        return d != null ? d : super.onRetainNonConfigurationInstance();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b("onSaveInstanceState", bundle);
    }

    public boolean onSearchRequested() {
        if (c("onSearchRequested", new Object[0])) {
            return true;
        }
        return super.onSearchRequested();
    }

    protected void onStart() {
        super.onStart();
        b("onStart", new Object[0]);
    }

    protected void onStop() {
        b("onStop", new Object[0]);
        super.onStop();
    }

    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        b("onTitleChanged", charSequence, Integer.valueOf(i));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (c("onTouchEvent", motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (c("onTrackballEvent", motionEvent)) {
            return true;
        }
        return super.onTrackballEvent(motionEvent);
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        b("onUserInteraction", new Object[0]);
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        b("onUserLeaveHint", new Object[0]);
    }

    public void onWindowAttributesChanged(LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
        b("onWindowAttributesChanged", layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        b("onWindowFocusChanged", Boolean.valueOf(z));
    }
}
