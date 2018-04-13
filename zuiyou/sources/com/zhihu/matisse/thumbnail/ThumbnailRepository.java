package com.zhihu.matisse.thumbnail;

import android.content.Context;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import com.zhihu.matisse.MediasDatabase;
import com.zhihu.matisse.ThumbnailHelper;
import com.zhihu.matisse.thumbnail.greendao.ThumbnailEntity;
import java.io.File;
import java.util.List;
import rx.d;
import rx.d$a;
import rx.f.a;
import rx.j;

class ThumbnailRepository {
    private final Object mLock = new Object();

    ThumbnailRepository(Context context) {
    }

    d<List<ThumbnailEntity>> loadThumbnailEntities() {
        return d.b(new d$a<List<ThumbnailEntity>>() {
            public void call(j<? super List<ThumbnailEntity>> jVar) {
                List loadAll;
                synchronized (ThumbnailRepository.this.mLock) {
                    loadAll = ThumbnailHelper.loadAll();
                }
                jVar.onNext(loadAll);
                jVar.onCompleted();
            }
        }).b(a.c()).a(rx.a.b.a.a());
    }

    void deleteThumbnails(final List<ThumbnailEntity> list) {
        d.b(new d$a<Void>() {
            public void call(j<? super Void> jVar) {
                synchronized (ThumbnailRepository.this.mLock) {
                    ThumbnailHelper.delete(list);
                }
                for (ThumbnailEntity thumbnailPath : list) {
                    Object thumbnailPath2 = thumbnailPath.getThumbnailPath();
                    if (!TextUtils.isEmpty(thumbnailPath2)) {
                        File file = new File(thumbnailPath2);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }).b(a.c()).a(rx.a.b.a.a()).g();
    }

    d<Boolean> insertOrUpdateThumbnail(final ThumbnailEntity thumbnailEntity) {
        return d.b(new d$a<Boolean>() {
            public void call(j<? super Boolean> jVar) {
                boolean z;
                synchronized (ThumbnailRepository.this.mLock) {
                    z = ThumbnailHelper.save(thumbnailEntity) > 0;
                }
                jVar.onNext(Boolean.valueOf(z));
                jVar.onCompleted();
            }
        }).b(a.c()).a(rx.a.b.a.a());
    }

    @UiThread
    void destroy() {
        d.b(new d$a<Void>() {
            public void call(j<? super Void> jVar) {
                synchronized (ThumbnailRepository.this.mLock) {
                    MediasDatabase.close();
                }
            }
        }).b(a.c()).a(rx.a.b.a.a()).g();
    }
}
