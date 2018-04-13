package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader.ForceLoadContentObserver;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader extends AsyncTaskLoader<Cursor> {
    final ForceLoadContentObserver f = new ForceLoadContentObserver(this);
    Uri g;
    String[] h;
    String i;
    String[] j;
    String k;
    Cursor l;
    CancellationSignal m;

    public Cursor loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            this.m = new CancellationSignal();
        }
        Cursor query;
        try {
            query = ContentResolverCompat.query(getContext().getContentResolver(), this.g, this.h, this.i, this.j, this.k, this.m);
            if (query != null) {
                query.getCount();
                query.registerContentObserver(this.f);
            }
            synchronized (this) {
                this.m = null;
            }
            return query;
        } catch (RuntimeException e) {
            query.close();
            throw e;
        } catch (Throwable th) {
            synchronized (this) {
                this.m = null;
            }
        }
    }

    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            if (this.m != null) {
                this.m.cancel();
            }
        }
    }

    public void deliverResult(Cursor cursor) {
        if (!isReset()) {
            Cursor cursor2 = this.l;
            this.l = cursor;
            if (isStarted()) {
                super.deliverResult(cursor);
            }
            if (cursor2 != null && cursor2 != cursor && !cursor2.isClosed()) {
                cursor2.close();
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    public CursorLoader(@NonNull Context context) {
        super(context);
    }

    public CursorLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        super(context);
        this.g = uri;
        this.h = strArr;
        this.i = str;
        this.j = strArr2;
        this.k = str2;
    }

    protected void e() {
        if (this.l != null) {
            deliverResult(this.l);
        }
        if (takeContentChanged() || this.l == null) {
            forceLoad();
        }
    }

    protected void f() {
        cancelLoad();
    }

    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    protected void g() {
        super.g();
        f();
        if (!(this.l == null || this.l.isClosed())) {
            this.l.close();
        }
        this.l = null;
    }

    @NonNull
    public Uri getUri() {
        return this.g;
    }

    public void setUri(@NonNull Uri uri) {
        this.g = uri;
    }

    @Nullable
    public String[] getProjection() {
        return this.h;
    }

    public void setProjection(@Nullable String[] strArr) {
        this.h = strArr;
    }

    @Nullable
    public String getSelection() {
        return this.i;
    }

    public void setSelection(@Nullable String str) {
        this.i = str;
    }

    @Nullable
    public String[] getSelectionArgs() {
        return this.j;
    }

    public void setSelectionArgs(@Nullable String[] strArr) {
        this.j = strArr;
    }

    @Nullable
    public String getSortOrder() {
        return this.k;
    }

    public void setSortOrder(@Nullable String str) {
        this.k = str;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.g);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.h));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.i);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.j));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.k);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.l);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.u);
    }
}
