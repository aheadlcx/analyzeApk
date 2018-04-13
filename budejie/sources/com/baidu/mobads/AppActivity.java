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
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.baidu.mobads.utils.l;

public class AppActivity extends Activity {
    public static String activityName = null;
    private static ActionBarColorTheme b = ActionBarColorTheme.ACTION_BAR_WHITE_THEME;
    private AppActivityImp a = new AppActivityImp();

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

    public static void setActivityName(String str) {
        activityName = str;
    }

    public static boolean isAnti() {
        return !TextUtils.isEmpty(activityName);
    }

    public static Class<?> getActivityClass() {
        Class<?> cls = AppActivity.class;
        if (!TextUtils.isEmpty(activityName)) {
            try {
                cls = Class.forName(activityName);
            } catch (Throwable e) {
                l.a().e(e);
            }
        }
        return cls;
    }

    public static ActionBarColorTheme getActionBarColorTheme() {
        return b;
    }

    public static void setActionBarColorTheme(ActionBarColorTheme actionBarColorTheme) {
        if (actionBarColorTheme != null) {
            b = new ActionBarColorTheme(actionBarColorTheme);
        }
    }

    public static void canLpShowWhenLocked(boolean z) {
        AppActivityImp.canLpShowWhenLocked(z);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.a.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a.dispatchTouchEvent(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        if (this.a.dispatchTrackballEvent(motionEvent)) {
            return true;
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.a.onActivityResult(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    protected void onApplyThemeResource(Theme theme, int i, boolean z) {
        this.a.onApplyThemeResource(theme, i, z);
        super.onApplyThemeResource(theme, i, z);
    }

    protected void onChildTitleChanged(Activity activity, CharSequence charSequence) {
        this.a.onChildTitleChanged(activity, charSequence);
        super.onChildTitleChanged(activity, charSequence);
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.a.onConfigurationChanged(configuration);
        super.onConfigurationChanged(configuration);
    }

    public void onContentChanged() {
        this.a.onContentChanged();
        super.onContentChanged();
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.a.onContextItemSelected(menuItem)) {
            return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    public void onContextMenuClosed(Menu menu) {
        this.a.onContextMenuClosed(menu);
        super.onContextMenuClosed(menu);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.a.setActivity(this);
            this.a.invokeRemoteStatic("setActionBarColor", Integer.valueOf(b.a), Integer.valueOf(b.b), Integer.valueOf(b.c), Integer.valueOf(b.d));
            this.a.onCreate(bundle);
        } catch (Throwable e) {
            l.a().e(e);
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        this.a.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public CharSequence onCreateDescription() {
        CharSequence onCreateDescription = this.a.onCreateDescription();
        return onCreateDescription != null ? onCreateDescription : super.onCreateDescription();
    }

    protected Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = this.a.onCreateDialog(i);
        return onCreateDialog != null ? onCreateDialog : super.onCreateDialog(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.a.onCreateOptionsMenu(menu)) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (this.a.onCreatePanelMenu(i, menu)) {
            return true;
        }
        return super.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i) {
        View onCreatePanelView = this.a.onCreatePanelView(i);
        return onCreatePanelView != null ? onCreatePanelView : super.onCreatePanelView(i);
    }

    public boolean onCreateThumbnail(Bitmap bitmap, Canvas canvas) {
        if (this.a.onCreateThumbnail(bitmap, canvas)) {
            return true;
        }
        return super.onCreateThumbnail(bitmap, canvas);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View onCreateView = this.a.onCreateView(str, context, attributeSet);
        return onCreateView != null ? onCreateView : super.onCreateView(str, context, attributeSet);
    }

    protected void onDestroy() {
        this.a.onDestroy();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.a.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        if (this.a.onKeyMultiple(i, i2, keyEvent)) {
            return true;
        }
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.a.onKeyUp(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void onLowMemory() {
        this.a.onLowMemory();
        super.onLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (this.a.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        return super.onMenuItemSelected(i, menuItem);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        if (this.a.onMenuOpened(i, menu)) {
            return true;
        }
        return super.onMenuOpened(i, menu);
    }

    protected void onNewIntent(Intent intent) {
        this.a.onNewIntent(intent);
        super.onNewIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.a.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onOptionsMenuClosed(Menu menu) {
        this.a.onOptionsMenuClosed(menu);
        super.onOptionsMenuClosed(menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        this.a.onPanelClosed(i, menu);
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        this.a.onPause();
        super.onPause();
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.a.onPostCreate(bundle);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.a.onPostResume();
    }

    protected void onPrepareDialog(int i, Dialog dialog) {
        super.onPrepareDialog(i, dialog);
        this.a.onPrepareDialog(i, dialog);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.a.onPrepareOptionsMenu(menu)) {
            return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (this.a.onPreparePanel(i, view, menu)) {
            return true;
        }
        return super.onPreparePanel(i, view, menu);
    }

    protected void onRestart() {
        super.onRestart();
        this.a.onRestart();
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.a.onRestoreInstanceState(bundle);
    }

    protected void onResume() {
        super.onResume();
        this.a.onResume();
    }

    public Object onRetainNonConfigurationInstance() {
        Object onRetainNonConfigurationInstance = this.a.onRetainNonConfigurationInstance();
        return onRetainNonConfigurationInstance != null ? onRetainNonConfigurationInstance : super.onRetainNonConfigurationInstance();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.a.onSaveInstanceState(bundle);
    }

    public boolean onSearchRequested() {
        if (this.a.onSearchRequested()) {
            return true;
        }
        return super.onSearchRequested();
    }

    protected void onStart() {
        super.onStart();
        this.a.onStart();
    }

    protected void onStop() {
        this.a.onStop();
        super.onStop();
    }

    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        this.a.onTitleChanged(charSequence, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.a.onTouchEvent(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.a.onTrackballEvent(motionEvent)) {
            return true;
        }
        return super.onTrackballEvent(motionEvent);
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        this.a.onUserInteraction();
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        this.a.onUserLeaveHint();
    }

    public void onWindowAttributesChanged(LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
        this.a.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.a.onWindowFocusChanged(z);
    }
}
