package qsbk.app.utils;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import qsbk.app.model.QiushiTopic;

public class QiushiTopicBus {
    static LinkedList<WeakReference<QiushiTopicUpdateListener>> a = new LinkedList();

    public interface QiushiTopicUpdateListener {
        void onQiushiTopicUpdate(QiushiTopic qiushiTopic, Object obj);
    }

    public static void register(QiushiTopicUpdateListener qiushiTopicUpdateListener) {
        a.add(new WeakReference(qiushiTopicUpdateListener));
    }

    public static void unregister(QiushiTopicUpdateListener qiushiTopicUpdateListener) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference == null || weakReference.get() == null) {
                it.remove();
            } else if (qiushiTopicUpdateListener == ((QiushiTopicUpdateListener) weakReference.get()) || qiushiTopicUpdateListener == null) {
                it.remove();
            }
        }
    }

    public static void updateQiushiTopic(QiushiTopic qiushiTopic, Object obj) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference == null || weakReference.get() == null) {
                it.remove();
            } else {
                QiushiTopicUpdateListener qiushiTopicUpdateListener = (QiushiTopicUpdateListener) weakReference.get();
                if (qiushiTopicUpdateListener != null) {
                    qiushiTopicUpdateListener.onQiushiTopicUpdate(qiushiTopic, obj);
                }
            }
        }
    }
}
