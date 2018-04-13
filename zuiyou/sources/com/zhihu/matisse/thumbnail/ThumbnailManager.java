package com.zhihu.matisse.thumbnail;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.thumbnail.greendao.ThumbnailEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import rx.b.b;
import rx.j;

public class ThumbnailManager implements Callback {
    private Context mContext;
    private ThumbnailGenerator mGenerator;
    private boolean mOrigAllLoader = false;
    private final Set<String> mOrigMedias = new HashSet();
    private ThumbnailRepository mRepository;
    private boolean mThumbnailLoaded = false;
    private final Map<String, ThumbnailEntity> mThumbnails = new HashMap();

    public void onCreate(Activity activity) {
        this.mContext = activity.getApplicationContext();
        if (this.mRepository == null) {
            this.mRepository = new ThumbnailRepository(this.mContext);
            this.mThumbnailLoaded = false;
        }
    }

    public void loadThumbnails() {
        this.mRepository.loadThumbnailEntities().b(new j<List<ThumbnailEntity>>() {
            public void onCompleted() {
                ThumbnailManager.this.mThumbnailLoaded = true;
                ThumbnailManager.this.syncOrigAndThumbnail();
            }

            public void onError(Throwable th) {
                ThumbnailManager.this.mThumbnailLoaded = false;
            }

            public void onNext(List<ThumbnailEntity> list) {
                if (list != null && !list.isEmpty()) {
                    for (ThumbnailEntity thumbnailEntity : list) {
                        ThumbnailManager.this.mThumbnails.put(ThumbnailManager.generateKey(Long.valueOf(thumbnailEntity.getOrigId()), thumbnailEntity.getOrigPath()), thumbnailEntity);
                    }
                }
            }
        });
    }

    public void onAllMediaItemLoaded(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            do {
                try {
                    Item valueOf = Item.valueOf(cursor);
                    if (valueOf != null && valueOf.id > 0 && valueOf.isVideo()) {
                        this.mOrigMedias.add(generateKey(Long.valueOf(valueOf.id), valueOf.path));
                    }
                } catch (Exception e) {
                }
            } while (cursor.moveToNext());
            cursor.moveToFirst();
        }
        this.mOrigAllLoader = true;
        syncOrigAndThumbnail();
    }

    private void syncOrigAndThumbnail() {
        if (this.mOrigAllLoader && this.mThumbnailLoaded) {
            List arrayList = new ArrayList();
            Iterator it = this.mThumbnails.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (!this.mOrigMedias.contains(entry.getKey())) {
                    arrayList.add(entry.getValue());
                    it.remove();
                }
            }
            if (!arrayList.isEmpty()) {
                this.mRepository.deleteThumbnails(arrayList);
            }
        }
    }

    public String wantThumbnailPath(Item item) {
        String thumbnailPath = getThumbnailPath(item);
        ThumbnailEntity thumbnailEntity = (ThumbnailEntity) this.mThumbnails.get(generateKey(Long.valueOf(item.id), item.path));
        if (TextUtils.isEmpty(thumbnailPath) || !new File(thumbnailPath).exists()) {
            tryGenerateThumbnail(item, thumbnailEntity);
        }
        return thumbnailPath;
    }

    public String getThumbnailPath(Item item) {
        if (!this.mThumbnailLoaded || item == null || !item.isVideo()) {
            return null;
        }
        ThumbnailEntity thumbnailEntity = (ThumbnailEntity) this.mThumbnails.get(generateKey(Long.valueOf(item.id), item.path));
        if (thumbnailEntity != null && thumbnailEntity.getStatus() == 2) {
            String thumbnailPath = thumbnailEntity.getThumbnailPath();
            if (!TextUtils.isEmpty(thumbnailPath) && new File(thumbnailPath).exists()) {
                return thumbnailPath;
            }
        }
        return null;
    }

    private void tryGenerateThumbnail(@NonNull Item item, @Nullable ThumbnailEntity thumbnailEntity) {
        if (thumbnailEntity == null || System.currentTimeMillis() - thumbnailEntity.getLastGenerateTime() >= 300000) {
            ThumbnailEntity thumbnailEntity2;
            if (thumbnailEntity == null) {
                thumbnailEntity2 = new ThumbnailEntity(null, item.id, item.path, null, 1, System.currentTimeMillis());
                this.mThumbnails.put(generateKey(Long.valueOf(item.id), item.path), thumbnailEntity2);
            } else {
                thumbnailEntity.setStatus(1);
                thumbnailEntity.setLastGenerateTime(System.currentTimeMillis());
                thumbnailEntity2 = thumbnailEntity;
            }
            final boolean isVideo = item.isVideo();
            this.mRepository.insertOrUpdateThumbnail(thumbnailEntity2).a(new b<Boolean>() {
                public void call(Boolean bool) {
                    if (bool.booleanValue()) {
                        ThumbnailManager.this.beginGenerateThumbnail(thumbnailEntity2, isVideo);
                    }
                }
            });
        }
    }

    private static String generateKey(Long l, String str) {
        return l + "_" + str.hashCode();
    }

    private void beginGenerateThumbnail(@NonNull ThumbnailEntity thumbnailEntity, boolean z) {
        if (this.mContext != null) {
            if (this.mGenerator == null) {
                this.mGenerator = new ThumbnailGenerator(this.mContext, SelectionSpec.getInstance().thumbnailDir, this);
            }
            this.mGenerator.postGenerationRequest(new ThumbnailGenerationRequest(thumbnailEntity.getOrigId(), thumbnailEntity.getOrigPath(), z));
        }
    }

    public void onThumbnailGenerated(long j, String str, String str2) {
        ThumbnailEntity thumbnailEntity = (ThumbnailEntity) this.mThumbnails.get(generateKey(Long.valueOf(j), str));
        if (thumbnailEntity != null) {
            thumbnailEntity.setThumbnailPath(str2);
            thumbnailEntity.setStatus(TextUtils.isEmpty(str2) ? 3 : 2);
            this.mRepository.insertOrUpdateThumbnail(thumbnailEntity).g();
        } else if (!TextUtils.isEmpty(str2)) {
            File file = new File(str2);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void onDestroy() {
        if (this.mGenerator != null) {
            this.mGenerator.destroy();
        }
        if (this.mRepository != null) {
            this.mRepository.destroy();
        }
        this.mContext = null;
    }
}
