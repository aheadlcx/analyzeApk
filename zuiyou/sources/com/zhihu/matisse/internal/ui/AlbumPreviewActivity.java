package com.zhihu.matisse.internal.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import cn.xiaochuankeji.aop.permission.c;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.model.AlbumMediaCollection;
import com.zhihu.matisse.internal.model.AlbumMediaCollection.AlbumMediaCallbacks;
import com.zhihu.matisse.internal.ui.adapter.PreviewPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class AlbumPreviewActivity extends BasePreviewActivity implements AlbumMediaCallbacks {
    public static final String EXTRA_ALBUM = "extra_album";
    public static final String EXTRA_ITEM = "extra_item";
    private static final a ajc$tjp_0 = null;
    private AlbumMediaCollection mCollection = new AlbumMediaCollection();
    private boolean mIsAlreadySetPosition;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            AlbumPreviewActivity.onCreate_aroundBody0((AlbumPreviewActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        b bVar = new b("AlbumPreviewActivity.java", AlbumPreviewActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.zhihu.matisse.internal.ui.AlbumPreviewActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 43);
    }

    static final void onCreate_aroundBody0(AlbumPreviewActivity albumPreviewActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        albumPreviewActivity.mCollection.onCreate(albumPreviewActivity, albumPreviewActivity);
        albumPreviewActivity.mCollection.load((Album) albumPreviewActivity.getIntent().getParcelableExtra("extra_album"));
        albumPreviewActivity.mCheckView.setSelected(albumPreviewActivity.mSelectedCollection.isSelected((Item) albumPreviewActivity.getIntent().getParcelableExtra(EXTRA_ITEM)));
    }

    protected void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mCollection.onDestroy();
    }

    public void onAlbumMediaLoad(Cursor cursor) {
        List arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(Item.valueOf(cursor));
        }
        PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.mPager.getAdapter();
        previewPagerAdapter.addAll(arrayList);
        previewPagerAdapter.notifyDataSetChanged();
        if (!this.mIsAlreadySetPosition) {
            this.mIsAlreadySetPosition = true;
            int indexOf = arrayList.indexOf((Item) getIntent().getParcelableExtra(EXTRA_ITEM));
            this.mPager.setCurrentItem(indexOf, false);
            this.mPreviousPos = indexOf;
        }
    }

    public void onAlbumMediaReset() {
    }
}
