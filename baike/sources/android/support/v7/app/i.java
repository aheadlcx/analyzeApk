package android.support.v7.app;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertController.AlertParams;
import android.support.v7.app.AlertController.RecycleListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;

class i extends CursorAdapter {
    final /* synthetic */ RecycleListView a;
    final /* synthetic */ AlertController b;
    final /* synthetic */ AlertParams c;
    private final int d;
    private final int e;

    i(AlertParams alertParams, Context context, Cursor cursor, boolean z, RecycleListView recycleListView, AlertController alertController) {
        this.c = alertParams;
        this.a = recycleListView;
        this.b = alertController;
        super(context, cursor, z);
        Cursor cursor2 = getCursor();
        this.d = cursor2.getColumnIndexOrThrow(this.c.mLabelColumn);
        this.e = cursor2.getColumnIndexOrThrow(this.c.mIsCheckedColumn);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.d));
        this.a.setItemChecked(cursor.getPosition(), cursor.getInt(this.e) == 1);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.c.mInflater.inflate(this.b.m, viewGroup, false);
    }
}
