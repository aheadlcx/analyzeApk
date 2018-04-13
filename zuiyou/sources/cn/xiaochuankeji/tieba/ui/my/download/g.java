package cn.xiaochuankeji.tieba.ui.my.download;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import java.lang.ref.WeakReference;

public class g implements LoaderCallbacks<Cursor> {
    private WeakReference<Context> a;
    private LoaderManager b;
    private a c;

    public interface a {
        void a(Cursor cursor);

        void e();
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (Cursor) obj);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Context context = (Context) this.a.get();
        if (context == null) {
            return null;
        }
        return h.a(context);
    }

    public void a(Loader<Cursor> loader, Cursor cursor) {
        this.c.a(cursor);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.c.e();
    }

    public void a(@NonNull FragmentActivity fragmentActivity, @NonNull a aVar) {
        this.a = new WeakReference(fragmentActivity);
        this.b = fragmentActivity.getSupportLoaderManager();
        this.c = aVar;
    }

    public void a() {
        if (this.b != null) {
            this.b.destroyLoader(2);
        }
        this.c = null;
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public void b() {
        this.b.initLoader(2, null, this);
    }

    public void c() {
        this.b.restartLoader(2, null, this);
    }
}
