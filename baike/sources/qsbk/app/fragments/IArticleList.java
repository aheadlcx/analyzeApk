package qsbk.app.fragments;

import android.view.KeyEvent;

public interface IArticleList {
    boolean hasNewArticle();

    boolean onKeyDown(int i, KeyEvent keyEvent);

    void refresh();

    void scrollToTop();
}
