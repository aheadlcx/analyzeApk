package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public class AccessibilityRecordCompat {
    private final AccessibilityRecord a;

    @Deprecated
    public AccessibilityRecordCompat(Object obj) {
        this.a = (AccessibilityRecord) obj;
    }

    @Deprecated
    public Object getImpl() {
        return this.a;
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityRecordCompat) {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain(accessibilityRecordCompat.a));
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
    }

    @Deprecated
    public void setSource(View view) {
        this.a.setSource(view);
    }

    @Deprecated
    public void setSource(View view, int i) {
        setSource(this.a, view, i);
    }

    public static void setSource(@NonNull AccessibilityRecord accessibilityRecord, View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            accessibilityRecord.setSource(view, i);
        }
    }

    @Deprecated
    public AccessibilityNodeInfoCompat getSource() {
        return AccessibilityNodeInfoCompat.a(this.a.getSource());
    }

    @Deprecated
    public int getWindowId() {
        return this.a.getWindowId();
    }

    @Deprecated
    public boolean isChecked() {
        return this.a.isChecked();
    }

    @Deprecated
    public void setChecked(boolean z) {
        this.a.setChecked(z);
    }

    @Deprecated
    public boolean isEnabled() {
        return this.a.isEnabled();
    }

    @Deprecated
    public void setEnabled(boolean z) {
        this.a.setEnabled(z);
    }

    @Deprecated
    public boolean isPassword() {
        return this.a.isPassword();
    }

    @Deprecated
    public void setPassword(boolean z) {
        this.a.setPassword(z);
    }

    @Deprecated
    public boolean isFullScreen() {
        return this.a.isFullScreen();
    }

    @Deprecated
    public void setFullScreen(boolean z) {
        this.a.setFullScreen(z);
    }

    @Deprecated
    public boolean isScrollable() {
        return this.a.isScrollable();
    }

    @Deprecated
    public void setScrollable(boolean z) {
        this.a.setScrollable(z);
    }

    @Deprecated
    public int getItemCount() {
        return this.a.getItemCount();
    }

    @Deprecated
    public void setItemCount(int i) {
        this.a.setItemCount(i);
    }

    @Deprecated
    public int getCurrentItemIndex() {
        return this.a.getCurrentItemIndex();
    }

    @Deprecated
    public void setCurrentItemIndex(int i) {
        this.a.setCurrentItemIndex(i);
    }

    @Deprecated
    public int getFromIndex() {
        return this.a.getFromIndex();
    }

    @Deprecated
    public void setFromIndex(int i) {
        this.a.setFromIndex(i);
    }

    @Deprecated
    public int getToIndex() {
        return this.a.getToIndex();
    }

    @Deprecated
    public void setToIndex(int i) {
        this.a.setToIndex(i);
    }

    @Deprecated
    public int getScrollX() {
        return this.a.getScrollX();
    }

    @Deprecated
    public void setScrollX(int i) {
        this.a.setScrollX(i);
    }

    @Deprecated
    public int getScrollY() {
        return this.a.getScrollY();
    }

    @Deprecated
    public void setScrollY(int i) {
        this.a.setScrollY(i);
    }

    @Deprecated
    public int getMaxScrollX() {
        return getMaxScrollX(this.a);
    }

    public static int getMaxScrollX(AccessibilityRecord accessibilityRecord) {
        if (VERSION.SDK_INT >= 15) {
            return accessibilityRecord.getMaxScrollX();
        }
        return 0;
    }

    @Deprecated
    public void setMaxScrollX(int i) {
        setMaxScrollX(this.a, i);
    }

    public static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i) {
        if (VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollX(i);
        }
    }

    @Deprecated
    public int getMaxScrollY() {
        return getMaxScrollY(this.a);
    }

    public static int getMaxScrollY(AccessibilityRecord accessibilityRecord) {
        if (VERSION.SDK_INT >= 15) {
            return accessibilityRecord.getMaxScrollY();
        }
        return 0;
    }

    @Deprecated
    public void setMaxScrollY(int i) {
        setMaxScrollY(this.a, i);
    }

    public static void setMaxScrollY(AccessibilityRecord accessibilityRecord, int i) {
        if (VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollY(i);
        }
    }

    @Deprecated
    public int getAddedCount() {
        return this.a.getAddedCount();
    }

    @Deprecated
    public void setAddedCount(int i) {
        this.a.setAddedCount(i);
    }

    @Deprecated
    public int getRemovedCount() {
        return this.a.getRemovedCount();
    }

    @Deprecated
    public void setRemovedCount(int i) {
        this.a.setRemovedCount(i);
    }

    @Deprecated
    public CharSequence getClassName() {
        return this.a.getClassName();
    }

    @Deprecated
    public void setClassName(CharSequence charSequence) {
        this.a.setClassName(charSequence);
    }

    @Deprecated
    public List<CharSequence> getText() {
        return this.a.getText();
    }

    @Deprecated
    public CharSequence getBeforeText() {
        return this.a.getBeforeText();
    }

    @Deprecated
    public void setBeforeText(CharSequence charSequence) {
        this.a.setBeforeText(charSequence);
    }

    @Deprecated
    public CharSequence getContentDescription() {
        return this.a.getContentDescription();
    }

    @Deprecated
    public void setContentDescription(CharSequence charSequence) {
        this.a.setContentDescription(charSequence);
    }

    @Deprecated
    public Parcelable getParcelableData() {
        return this.a.getParcelableData();
    }

    @Deprecated
    public void setParcelableData(Parcelable parcelable) {
        this.a.setParcelableData(parcelable);
    }

    @Deprecated
    public void recycle() {
        this.a.recycle();
    }

    @Deprecated
    public int hashCode() {
        return this.a == null ? 0 : this.a.hashCode();
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
        if (this.a == null) {
            if (accessibilityRecordCompat.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(accessibilityRecordCompat.a)) {
            return true;
        } else {
            return false;
        }
    }
}
