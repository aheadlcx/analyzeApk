package com.zhihu.matisse.internal.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.loader.AlbumMediaLoader;
import java.lang.ref.WeakReference;

public class AlbumMediaCollection implements LoaderCallbacks<Cursor> {
    private static final String ARGS_ALBUM = "args_album";
    private static final String ARGS_ENABLE_CAPTURE = "args_enable_capture";
    private static final int LOADER_ID = 2;
    private AlbumMediaCallbacks mCallbacks;
    private WeakReference<Context> mContext;
    private LoaderManager mLoaderManager;

    public interface AlbumMediaCallbacks {
        void onAlbumMediaLoad(Cursor cursor);

        void onAlbumMediaReset();
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        boolean z = false;
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return null;
        }
        Album album = (Album) bundle.getParcelable(ARGS_ALBUM);
        if (album == null) {
            return null;
        }
        if (album.isAll() && bundle.getBoolean(ARGS_ENABLE_CAPTURE, false)) {
            z = true;
        }
        return AlbumMediaLoader.newInstance(context, album, z);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (((Context) this.mContext.get()) != null) {
            this.mCallbacks.onAlbumMediaLoad(cursor);
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        if (((Context) this.mContext.get()) != null) {
            this.mCallbacks.onAlbumMediaReset();
        }
    }

    public void onCreate(@NonNull FragmentActivity fragmentActivity, @NonNull AlbumMediaCallbacks albumMediaCallbacks) {
        this.mContext = new WeakReference(fragmentActivity);
        this.mLoaderManager = fragmentActivity.getSupportLoaderManager();
        this.mCallbacks = albumMediaCallbacks;
    }

    public void onDestroy() {
        this.mLoaderManager.destroyLoader(2);
        this.mCallbacks = null;
    }

    public void load(@Nullable Album album) {
        load(album, false);
    }

    public void load(@Nullable Album album, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ALBUM, album);
        bundle.putBoolean(ARGS_ENABLE_CAPTURE, z);
        this.mLoaderManager.initLoader(2, bundle, this);
    }
}
