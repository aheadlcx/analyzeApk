package qsbk.app.utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import qsbk.app.model.CircleArticle;

public class CircleArticleBus {
    public static ArrayList<WeakReference<OnArticleUpdateListener>> listeners;

    public interface OnArticleUpdateListener {
        void onArticleCreate(CircleArticle circleArticle);

        void onArticleDelete(CircleArticle circleArticle);

        void onArticleUpdate(CircleArticle circleArticle);
    }

    public static void register(OnArticleUpdateListener onArticleUpdateListener) {
        if (listeners == null) {
            listeners = new ArrayList();
        }
        listeners.add(new WeakReference(onArticleUpdateListener));
    }

    public static void unregister(OnArticleUpdateListener onArticleUpdateListener) {
        if (listeners != null) {
            int i = 0;
            while (i < listeners.size()) {
                WeakReference weakReference = (WeakReference) listeners.get(i);
                if (weakReference == null || weakReference.get() == null || weakReference.get() == onArticleUpdateListener) {
                    listeners.remove(i);
                    i--;
                }
                i++;
            }
            if (listeners.size() == 0) {
                listeners = null;
            }
        }
    }

    public static void newArticle(CircleArticle circleArticle) {
        if (listeners != null) {
            int i = 0;
            while (i < listeners.size()) {
                WeakReference weakReference = (WeakReference) listeners.get(i);
                if (weakReference == null || weakReference.get() == null) {
                    listeners.remove(i);
                    i--;
                } else {
                    ((OnArticleUpdateListener) weakReference.get()).onArticleCreate(circleArticle);
                }
                i++;
            }
        }
    }

    public static void updateArticle(CircleArticle circleArticle, Object obj) {
        if (listeners != null) {
            int i = 0;
            while (i < listeners.size()) {
                WeakReference weakReference = (WeakReference) listeners.get(i);
                if (weakReference == null || weakReference.get() == null) {
                    listeners.remove(i);
                    i--;
                } else if (weakReference.get() != obj) {
                    ((OnArticleUpdateListener) weakReference.get()).onArticleUpdate(circleArticle);
                }
                i++;
            }
        }
    }

    public static void deleteArticle(CircleArticle circleArticle, Object obj) {
        if (listeners != null) {
            int i = 0;
            while (i < listeners.size()) {
                WeakReference weakReference = (WeakReference) listeners.get(i);
                if (weakReference == null || weakReference.get() == null) {
                    listeners.remove(i);
                    i--;
                } else if (weakReference.get() != obj) {
                    ((OnArticleUpdateListener) weakReference.get()).onArticleDelete(circleArticle);
                }
                i++;
            }
        }
    }
}
