package com.zhihu.matisse.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import c.a.c;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.R;
import com.zhihu.matisse.ResultItem;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.AlbumCollection;
import com.zhihu.matisse.internal.model.AlbumCollection.AlbumCallbacks;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import com.zhihu.matisse.internal.ui.AlbumPreviewActivity;
import com.zhihu.matisse.internal.ui.BasePreviewActivity;
import com.zhihu.matisse.internal.ui.MediaSelectionFragment;
import com.zhihu.matisse.internal.ui.MediaSelectionFragment.AssistantProvider;
import com.zhihu.matisse.internal.ui.SelectedPreviewActivity;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter.CheckStateListener;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter.OnMediaClickListener;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter.OnPhotoCapture;
import com.zhihu.matisse.internal.ui.adapter.AlbumsAdapter;
import com.zhihu.matisse.internal.ui.widget.AlbumsSpinner;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;
import com.zhihu.matisse.internal.utils.PathUtils;
import com.zhihu.matisse.thumbnail.ThumbnailManager;
import java.util.ArrayList;
import java.util.Iterator;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class MatisseActivity extends AppCompatActivity implements OnClickListener, OnItemSelectedListener, AlbumCallbacks, AssistantProvider, CheckStateListener, OnMediaClickListener, OnPhotoCapture {
    public static final String EXTRA_RESULT_SELECTION = "extra_result_selection";
    private static final int REQUEST_CODE_CAPTURE = 24;
    private static final int REQUEST_CODE_PREVIEW = 23;
    private static final String SELECTION_SPEC = "selection_spec";
    private static final a ajc$tjp_0 = null;
    private final AlbumCollection mAlbumCollection = new AlbumCollection();
    private AlbumsAdapter mAlbumsAdapter;
    private AlbumsSpinner mAlbumsSpinner;
    private TextView mButtonApply;
    private TextView mButtonConfirm;
    private TextView mButtonPreview;
    private View mContainer;
    private View mEmptyView;
    private MediaStoreCompat mMediaStoreCompat;
    private SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    private SelectionSpec mSpec;
    private ThumbnailManager mThumbnailManager = new ThumbnailManager();

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            MatisseActivity.onCreate_aroundBody0((MatisseActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        b bVar = new b("MatisseActivity.java", MatisseActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.zhihu.matisse.ui.MatisseActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 98);
    }

    static final void onCreate_aroundBody0(MatisseActivity matisseActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        if (bundle != null) {
            SelectionSpec.restoreSavedInstance((SelectionSpec) bundle.getParcelable(SELECTION_SPEC));
        }
        matisseActivity.mSpec = SelectionSpec.getInstance();
        matisseActivity.mSpec.themeId = R.style.Matisse_Zhihu;
        matisseActivity.setTheme(matisseActivity.mSpec.themeId);
        if (c.e() != null) {
            com.android.a.a.c.a(matisseActivity.getWindow(), true);
            com.android.a.a.c.a((Activity) matisseActivity, c.a.d.a.a.a().a(R.color.CB), c.e().d());
        }
        super.onCreate(bundle);
        matisseActivity.setContentView(R.layout.activity_matisse);
        if (matisseActivity.mSpec.needOrientationRestriction()) {
            matisseActivity.setRequestedOrientation(matisseActivity.mSpec.orientation);
        }
        if (matisseActivity.mSpec.capture) {
            matisseActivity.mMediaStoreCompat = new MediaStoreCompat(matisseActivity);
        }
        matisseActivity.setSupportActionBar((Toolbar) matisseActivity.findViewById(R.id.toolbar));
        ActionBar supportActionBar = matisseActivity.getSupportActionBar();
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        matisseActivity.mButtonPreview = (TextView) matisseActivity.findViewById(R.id.button_preview);
        matisseActivity.mButtonApply = (TextView) matisseActivity.findViewById(R.id.button_apply);
        matisseActivity.mButtonConfirm = (TextView) matisseActivity.findViewById(R.id.button_confirm);
        matisseActivity.mButtonPreview.setOnClickListener(matisseActivity);
        matisseActivity.mButtonApply.setOnClickListener(matisseActivity);
        matisseActivity.mButtonConfirm.setOnClickListener(matisseActivity);
        matisseActivity.mContainer = matisseActivity.findViewById(R.id.container);
        matisseActivity.mEmptyView = matisseActivity.findViewById(R.id.empty_view);
        matisseActivity.mSelectedCollection.onCreate(bundle);
        matisseActivity.updateBottomToolbar();
        matisseActivity.mAlbumsAdapter = new AlbumsAdapter((Context) matisseActivity, null, false);
        matisseActivity.mAlbumsSpinner = new AlbumsSpinner(matisseActivity);
        matisseActivity.mAlbumsSpinner.setOnItemSelectedListener(matisseActivity);
        matisseActivity.mAlbumsSpinner.setSelectedTextView((TextView) matisseActivity.findViewById(R.id.selected_album));
        matisseActivity.mAlbumsSpinner.setPopupAnchorView(matisseActivity.findViewById(R.id.toolbar));
        matisseActivity.mAlbumsSpinner.setAdapter(matisseActivity.mAlbumsAdapter);
        matisseActivity.mThumbnailManager.onCreate(matisseActivity);
        matisseActivity.mThumbnailManager.loadThumbnails();
        matisseActivity.mAlbumCollection.onCreate(matisseActivity, matisseActivity);
        matisseActivity.mAlbumCollection.onRestoreInstanceState(bundle);
        matisseActivity.mAlbumCollection.loadAlbums();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mSelectedCollection.onSaveInstanceState(bundle);
        this.mAlbumCollection.onSaveInstanceState(bundle);
        bundle.putParcelable(SELECTION_SPEC, this.mSpec);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mAlbumCollection.onDestroy();
        this.mThumbnailManager.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        setResult(0);
        super.onBackPressed();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            String str;
            if (i == 23) {
                Bundle bundleExtra = intent.getBundleExtra(BasePreviewActivity.EXTRA_RESULT_BUNDLE);
                ArrayList parcelableArrayList = bundleExtra.getParcelableArrayList(SelectedItemCollection.STATE_SELECTION);
                int i3 = bundleExtra.getInt(SelectedItemCollection.STATE_COLLECTION_TYPE, 0);
                if (intent.getBooleanExtra(BasePreviewActivity.EXTRA_RESULT_APPLY, false)) {
                    Intent intent2 = new Intent();
                    ArrayList arrayList = new ArrayList();
                    if (parcelableArrayList != null) {
                        Iterator it = parcelableArrayList.iterator();
                        while (it.hasNext()) {
                            Item item = (Item) it.next();
                            str = item.path;
                            if (str == null) {
                                str = PathUtils.getPath(this, item.getContentUri());
                            }
                            arrayList.add(new ResultItem(item.id, str, item.mimeType, item.videoThumbnail, item.width, item.height));
                        }
                    }
                    intent2.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, arrayList);
                    setResult(-1, intent2);
                    finish();
                    return;
                }
                this.mSelectedCollection.overwrite(parcelableArrayList, i3);
                Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(MediaSelectionFragment.class.getSimpleName());
                if (findFragmentByTag instanceof MediaSelectionFragment) {
                    ((MediaSelectionFragment) findFragmentByTag).refreshMediaGrid();
                }
                updateBottomToolbar();
            } else if (i == 24) {
                Uri currentPhotoUri = this.mMediaStoreCompat.getCurrentPhotoUri();
                str = this.mMediaStoreCompat.getCurrentPhotoPath();
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                int i4 = options.outWidth;
                int i5 = options.outHeight;
                ArrayList arrayList2 = new ArrayList();
                if (this.mSelectedCollection.count() > 0) {
                    arrayList2.addAll(this.mSelectedCollection.asListOfResultItem());
                }
                arrayList2.add(new ResultItem(-1, str, MimeType.JPEG.toString(), null, i4, i5));
                Intent intent3 = new Intent();
                intent3.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, arrayList2);
                setResult(-1, intent3);
                saveImageToGallery(str);
                if (VERSION.SDK_INT < 21 && currentPhotoUri != null) {
                    revokeUriPermission(currentPhotoUri, 3);
                }
                finish();
            }
        }
    }

    private void updateBottomToolbar() {
        int count = this.mSelectedCollection.count();
        if (count == 0) {
            this.mButtonPreview.setEnabled(false);
            this.mButtonApply.setEnabled(false);
            this.mButtonApply.setText(getString(R.string.button_apply_default));
        } else if (count == 1 && this.mSpec.singleSelectionModeEnabled()) {
            this.mButtonPreview.setEnabled(true);
            this.mButtonApply.setText(R.string.button_apply_default);
            this.mButtonApply.setEnabled(true);
            this.mButtonConfirm.setEnabled(true);
        } else {
            this.mButtonPreview.setEnabled(true);
            this.mButtonApply.setEnabled(true);
            this.mButtonApply.setText(getString(R.string.button_apply, new Object[]{Integer.valueOf(count)}));
            this.mButtonConfirm.setEnabled(true);
        }
        this.mButtonConfirm.setText(getString(R.string.button_confirm, new Object[]{Integer.valueOf(count), Integer.valueOf(this.mSpec.maxSelectable)}));
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_preview) {
            Intent intent = new Intent(this, SelectedPreviewActivity.class);
            intent.putExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
            startActivityForResult(intent, 23);
        } else if (view.getId() == R.id.button_apply) {
            r1 = new Intent();
            r1.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, (ArrayList) this.mSelectedCollection.asListOfResultItem());
            setResult(-1, r1);
            finish();
        } else if (view.getId() != R.id.button_confirm) {
        } else {
            if (this.mSelectedCollection.count() <= 0) {
                cn.htjyb.ui.widget.a.a(this, "至少选择一张图片", 0).show();
                return;
            }
            r1 = new Intent();
            r1.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, (ArrayList) this.mSelectedCollection.asListOfResultItem());
            setResult(-1, r1);
            finish();
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.mAlbumCollection.setStateCurrentSelection(i);
        this.mAlbumsAdapter.getCursor().moveToPosition(i);
        Album valueOf = Album.valueOf(this.mAlbumsAdapter.getCursor());
        if (valueOf.isAll() && SelectionSpec.getInstance().capture) {
            valueOf.addCaptureCount();
        }
        onAlbumSelected(valueOf);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        if (this.mSelectedCollection.count() <= 0) {
            cn.htjyb.ui.widget.a.a(this, "至少选择一张图片", 0).show();
        }
    }

    public void onAlbumLoad(final Cursor cursor) {
        this.mAlbumsAdapter.swapCursor(cursor);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                cursor.moveToPosition(MatisseActivity.this.mAlbumCollection.getCurrentSelection());
                MatisseActivity.this.mAlbumsSpinner.setSelection(MatisseActivity.this, MatisseActivity.this.mAlbumCollection.getCurrentSelection());
                Album valueOf = Album.valueOf(cursor);
                if (valueOf.isAll() && SelectionSpec.getInstance().capture) {
                    valueOf.addCaptureCount();
                }
                MatisseActivity.this.onAlbumSelected(valueOf);
            }
        });
    }

    public void onAlbumReset() {
        this.mAlbumsAdapter.swapCursor(null);
    }

    private void onAlbumSelected(Album album) {
        if (album.isAll() && album.isEmpty()) {
            this.mContainer.setVisibility(8);
            this.mEmptyView.setVisibility(0);
            return;
        }
        this.mContainer.setVisibility(0);
        this.mEmptyView.setVisibility(8);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MediaSelectionFragment.newInstance(album), MediaSelectionFragment.class.getSimpleName()).commitAllowingStateLoss();
    }

    public void onUpdate() {
        updateBottomToolbar();
    }

    public void onMediaClick(Album album, Item item, int i) {
        Intent intent = new Intent(this, AlbumPreviewActivity.class);
        intent.putExtra("extra_album", album);
        intent.putExtra(AlbumPreviewActivity.EXTRA_ITEM, item);
        intent.putExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
        startActivityForResult(intent, 23);
    }

    public SelectedItemCollection provideSelectedItemCollection() {
        return this.mSelectedCollection;
    }

    public ThumbnailManager provideThumbnailManager() {
        return this.mThumbnailManager;
    }

    public void capture() {
        if (this.mMediaStoreCompat != null) {
            cn.xiaochuankeji.aop.permission.a.a(this).a(new PermissionItem(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}).settingText("去设置").deniedMessage("开启以下权限才能正常拍摄").runIgnorePermission(false).needGotoSetting(true), new e() {
                public void permissionGranted() {
                    MatisseActivity.this.mMediaStoreCompat.dispatchCaptureIntent(MatisseActivity.this, 24);
                }

                public void permissionDenied() {
                }
            });
        }
    }

    private void saveImageToGallery(String str) {
        sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + str)));
    }
}
