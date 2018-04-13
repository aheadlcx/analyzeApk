package com.tencent.wcdb;

import android.database.ContentObserver;
import java.lang.ref.WeakReference;

protected class AbstractCursor$SelfContentObserver extends ContentObserver {
    WeakReference<AbstractCursor> mCursor;

    public AbstractCursor$SelfContentObserver(AbstractCursor abstractCursor) {
        super(null);
        this.mCursor = new WeakReference(abstractCursor);
    }

    public boolean deliverSelfNotifications() {
        return false;
    }

    public void onChange(boolean z) {
        AbstractCursor abstractCursor = (AbstractCursor) this.mCursor.get();
        if (abstractCursor != null) {
            abstractCursor.onChange(false);
        }
    }
}
