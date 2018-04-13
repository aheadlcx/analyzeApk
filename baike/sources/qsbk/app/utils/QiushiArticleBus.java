package qsbk.app.utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import qsbk.app.model.Article;

public class QiushiArticleBus {
    public static ArrayList<WeakReference<OnArticleUpdateListener>> listeners;
    public static ArrayList<WeakReference<OnArticleActionListener>> sArticleActionListeners;
    public static ArrayList<WeakReference<OnArticleVoteStateChangeListener>> sVoteStateListeners;

    public interface OnArticleActionListener {
        void onArticleDeleted(OnArticleActionListener onArticleActionListener, Article article);
    }

    public interface OnArticleUpdateListener {
        void onArticleUpdate(Article article);
    }

    public interface OnArticleVoteStateChangeListener {
        public static final int ACTION_DOWN = -1;
        public static final int ACTION_NORMAL = 0;
        public static final int ACTION_UP = 1;

        void onVoteStateChange(Article article, int i, int i2);
    }

    public static void register(OnArticleUpdateListener onArticleUpdateListener) {
        if (listeners == null) {
            listeners = new ArrayList();
        }
        listeners.add(new WeakReference(onArticleUpdateListener));
    }

    public static void registerOnArticleActionListener(OnArticleActionListener onArticleActionListener) {
        if (sArticleActionListeners == null) {
            sArticleActionListeners = new ArrayList();
        }
        sArticleActionListeners.add(new WeakReference(onArticleActionListener));
    }

    public static void unregisterOnArticleActionListener(OnArticleActionListener onArticleActionListener) {
        if (sArticleActionListeners != null) {
            int i = 0;
            while (i < sArticleActionListeners.size()) {
                WeakReference weakReference = (WeakReference) sArticleActionListeners.get(i);
                if (weakReference == null || weakReference.get() == null || weakReference.get() == onArticleActionListener) {
                    sArticleActionListeners.remove(i);
                    i--;
                }
                i++;
            }
            if (sArticleActionListeners.size() == 0) {
                sArticleActionListeners = null;
            }
        }
    }

    public static void onArticleDeleted(Article article) {
        if (sArticleActionListeners != null) {
            int i = 0;
            while (i < sArticleActionListeners.size()) {
                WeakReference weakReference = (WeakReference) sArticleActionListeners.get(i);
                if (weakReference == null || weakReference.get() == null) {
                    sArticleActionListeners.remove(i);
                    i--;
                } else if (weakReference.get() != null) {
                    ((OnArticleActionListener) weakReference.get()).onArticleDeleted((OnArticleActionListener) weakReference.get(), article);
                }
                i++;
            }
        }
    }

    public static void registVoteStateChangeListener(OnArticleVoteStateChangeListener onArticleVoteStateChangeListener) {
        if (sVoteStateListeners == null) {
            sVoteStateListeners = new ArrayList();
        }
        sVoteStateListeners.add(new WeakReference(onArticleVoteStateChangeListener));
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

    public static void unregistVoteStateChangeListener(OnArticleVoteStateChangeListener onArticleVoteStateChangeListener) {
        if (sVoteStateListeners != null) {
            int i = 0;
            while (i < sVoteStateListeners.size()) {
                WeakReference weakReference = (WeakReference) sVoteStateListeners.get(i);
                if (weakReference == null || weakReference.get() == null || weakReference.get() == onArticleVoteStateChangeListener) {
                    sVoteStateListeners.remove(i);
                    i--;
                }
                i++;
            }
            if (sVoteStateListeners.size() == 0) {
                sVoteStateListeners = null;
            }
        }
    }

    public static void clearAll() {
        if (listeners != null) {
            listeners.clear();
        }
        if (sVoteStateListeners != null) {
            sVoteStateListeners.clear();
        }
    }

    public static void updateArticle(Article article, Object obj) {
        if (listeners != null) {
            int i = 0;
            while (i < listeners.size()) {
                WeakReference weakReference = (WeakReference) listeners.get(i);
                if (weakReference == null || weakReference.get() == null) {
                    listeners.remove(i);
                    i--;
                } else if (weakReference.get() != obj) {
                    ((OnArticleUpdateListener) weakReference.get()).onArticleUpdate(article);
                }
                i++;
            }
        }
    }

    public static void updateArticleVoteState(Article article, Object obj, int i, int i2) {
        if (sVoteStateListeners != null) {
            int i3 = 0;
            while (i3 < sVoteStateListeners.size()) {
                WeakReference weakReference = (WeakReference) sVoteStateListeners.get(i3);
                if (weakReference == null || weakReference.get() == null) {
                    sVoteStateListeners.remove(i3);
                    i3--;
                } else if (weakReference.get() != obj) {
                    ((OnArticleVoteStateChangeListener) weakReference.get()).onVoteStateChange(article, i, i2);
                }
                i3++;
            }
        }
    }
}
