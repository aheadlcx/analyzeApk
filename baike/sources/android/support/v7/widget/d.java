package android.support.v7.widget;

import android.support.v7.view.ActionMode;
import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ ActionMode a;
    final /* synthetic */ ActionBarContextView b;

    d(ActionBarContextView actionBarContextView, ActionMode actionMode) {
        this.b = actionBarContextView;
        this.a = actionMode;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
