package com.zhihu.matisse.internal.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.a.a.c;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.model.SelectedItemCollection;
import com.zhihu.matisse.internal.ui.adapter.PreviewPagerAdapter;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public abstract class BasePreviewActivity extends AppCompatActivity implements OnPageChangeListener, OnClickListener {
    public static final String EXTRA_DEFAULT_BUNDLE = "extra_default_bundle";
    public static final String EXTRA_RESULT_APPLY = "extra_result_apply";
    public static final String EXTRA_RESULT_BUNDLE = "extra_result_bundle";
    private static final a ajc$tjp_0 = null;
    protected PreviewPagerAdapter mAdapter;
    protected ImageView mButtonBack;
    protected TextView mButtonDone;
    protected ImageView mCheckView;
    protected ViewPager mPager;
    protected int mPreviousPos = -1;
    protected final SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    protected TextView mSelectedCount;
    protected SelectionSpec mSpec;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            BasePreviewActivity.onCreate_aroundBody0((BasePreviewActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    static {
        ajc$preClinit();
    }

    private static void ajc$preClinit() {
        b bVar = new b("BasePreviewActivity.java", BasePreviewActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.zhihu.matisse.internal.ui.BasePreviewActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 63);
    }

    static final void onCreate_aroundBody0(BasePreviewActivity basePreviewActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        basePreviewActivity.mSpec = SelectionSpec.getInstance();
        if (basePreviewActivity.mSpec.needOrientationRestriction()) {
            basePreviewActivity.setRequestedOrientation(basePreviewActivity.mSpec.orientation);
        }
        basePreviewActivity.setTheme(basePreviewActivity.mSpec.themeId);
        c.a((Activity) basePreviewActivity, ContextCompat.getColor(basePreviewActivity.getApplicationContext(), R.color.black), false);
        basePreviewActivity.setContentView(R.layout.activity_media_preview);
        if (bundle == null) {
            basePreviewActivity.mSelectedCollection.onCreate(basePreviewActivity.getIntent().getBundleExtra(EXTRA_DEFAULT_BUNDLE));
        } else {
            basePreviewActivity.mSelectedCollection.onCreate(bundle);
        }
        basePreviewActivity.mButtonBack = (ImageView) basePreviewActivity.findViewById(R.id.button_back);
        basePreviewActivity.mCheckView = (ImageView) basePreviewActivity.findViewById(R.id.check_view);
        basePreviewActivity.mButtonBack.setOnClickListener(basePreviewActivity);
        basePreviewActivity.mCheckView.setOnClickListener(basePreviewActivity);
        basePreviewActivity.mPager = (ViewPager) basePreviewActivity.findViewById(R.id.pager);
        basePreviewActivity.mPager.addOnPageChangeListener(basePreviewActivity);
        basePreviewActivity.mAdapter = new PreviewPagerAdapter(basePreviewActivity.getSupportFragmentManager(), null);
        basePreviewActivity.mPager.setAdapter(basePreviewActivity.mAdapter);
        basePreviewActivity.mButtonDone = (TextView) basePreviewActivity.findViewById(R.id.button_done);
        basePreviewActivity.mSelectedCount = (TextView) basePreviewActivity.findViewById(R.id.selected_count);
        basePreviewActivity.mButtonDone.setOnClickListener(basePreviewActivity);
        basePreviewActivity.updateDoneButton();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onSaveInstanceState(Bundle bundle) {
        this.mSelectedCollection.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void onBackPressed() {
        sendBackResult(false);
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_back) {
            onBackPressed();
        } else if (view.getId() == R.id.check_view) {
            onCheckPressed();
        } else if (view.getId() == R.id.button_done) {
            sendBackResult(true);
            finish();
        }
    }

    private void onCheckPressed() {
        Item mediaItem = this.mAdapter.getMediaItem(this.mPager.getCurrentItem());
        if (this.mSelectedCollection.isSelected(mediaItem)) {
            this.mSelectedCollection.remove(mediaItem);
            this.mCheckView.setSelected(false);
        } else if (assertAddSelection(mediaItem)) {
            this.mSelectedCollection.add(mediaItem);
            this.mCheckView.setSelected(true);
        }
        updateDoneButton();
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.mPager.getAdapter();
        if (!(this.mPreviousPos == -1 || this.mPreviousPos == i)) {
            Item mediaItem = previewPagerAdapter.getMediaItem(i);
            if (this.mSpec.countable) {
                this.mSelectedCollection.checkedNumOf(mediaItem);
            }
            this.mCheckView.setSelected(this.mSelectedCollection.isSelected(mediaItem));
        }
        this.mPreviousPos = i;
    }

    public void onPageScrollStateChanged(int i) {
    }

    private void updateDoneButton() {
        int count = this.mSelectedCollection.count();
        if (count == 0) {
            this.mButtonDone.setEnabled(false);
        } else if (count == 1 && this.mSpec.singleSelectionModeEnabled()) {
            this.mButtonDone.setEnabled(true);
        } else {
            this.mButtonDone.setEnabled(true);
        }
        this.mSelectedCount.setText(String.valueOf(count));
    }

    protected void sendBackResult(boolean z) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT_BUNDLE, this.mSelectedCollection.getDataWithBundle());
        intent.putExtra(EXTRA_RESULT_APPLY, z);
        setResult(-1, intent);
    }

    private boolean assertAddSelection(Item item) {
        IncapableCause isAcceptable = this.mSelectedCollection.isAcceptable(item);
        IncapableCause.handleCause(this, isAcceptable);
        return isAcceptable == null;
    }
}
