package qsbk.app.imagepicker;

import android.database.Cursor;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;

class a implements OnLoadCompleteListener<Cursor> {
    final /* synthetic */ ImagePickerManager a;

    a(ImagePickerManager imagePickerManager) {
        this.a = imagePickerManager;
    }

    public void onLoadComplete(Loader<Cursor> loader, Cursor cursor) {
        new b(this, cursor).start();
    }
}
