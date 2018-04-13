package com.liulishuo.filedownloader.notification;

import android.util.SparseArray;

public class FileDownloadNotificationHelper<T extends BaseNotificationItem> {
    private final SparseArray<T> a = new SparseArray();

    public T get(int i) {
        return (BaseNotificationItem) this.a.get(i);
    }

    public boolean contains(int i) {
        return get(i) != null;
    }

    public T remove(int i) {
        T t = get(i);
        if (t == null) {
            return null;
        }
        this.a.remove(i);
        return t;
    }

    public void add(T t) {
        this.a.remove(t.getId());
        this.a.put(t.getId(), t);
    }

    public void showProgress(int i, int i2, int i3) {
        BaseNotificationItem baseNotificationItem = get(i);
        if (baseNotificationItem != null) {
            baseNotificationItem.updateStatus(3);
            baseNotificationItem.update(i2, i3);
        }
    }

    public void showIndeterminate(int i, int i2) {
        BaseNotificationItem baseNotificationItem = get(i);
        if (baseNotificationItem != null) {
            baseNotificationItem.updateStatus(i2);
            baseNotificationItem.show(false);
        }
    }

    public void cancel(int i) {
        BaseNotificationItem remove = remove(i);
        if (remove != null) {
            remove.cancel();
        }
    }

    public void clear() {
        SparseArray clone = this.a.clone();
        this.a.clear();
        for (int i = 0; i < clone.size(); i++) {
            ((BaseNotificationItem) clone.get(clone.keyAt(i))).cancel();
        }
    }
}
