package android.support.v7.widget;

import android.support.v7.widget.SearchView.SearchAutoComplete;

class ck implements Runnable {
    final /* synthetic */ SearchAutoComplete a;

    ck(SearchAutoComplete searchAutoComplete) {
        this.a = searchAutoComplete;
    }

    public void run() {
        this.a.b();
    }
}
