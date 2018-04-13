package com.yalantis.ucrop;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.a.a.d;
import cn.xiaochuankeji.a.a.e;
import cn.xiaochuankeji.aop.permission.c;
import com.yalantis.ucrop.UCrop.Options;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.AspectRatio;
import com.yalantis.ucrop.util.SelectedStateListDrawable;
import com.yalantis.ucrop.view.GestureCropImageView;
import com.yalantis.ucrop.view.OverlayView;
import com.yalantis.ucrop.view.TransformImageView.TransformImageListener;
import com.yalantis.ucrop.view.UCropView;
import com.yalantis.ucrop.view.widget.AspectRatioTextView;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView;
import com.yalantis.ucrop.view.widget.HorizontalProgressWheelView.ScrollingListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class UCropActivity extends AppCompatActivity {
    public static final int ALL = 3;
    public static final CompressFormat DEFAULT_COMPRESS_FORMAT = CompressFormat.JPEG;
    public static final int DEFAULT_COMPRESS_QUALITY = 90;
    public static final int NONE = 0;
    public static final int ROTATE = 2;
    private static final int ROTATE_WIDGET_SENSITIVITY_COEFFICIENT = 42;
    public static final int SCALE = 1;
    private static final int SCALE_WIDGET_SENSITIVITY_COEFFICIENT = 15000;
    private static final int TABS_COUNT = 3;
    private static final a ajc$tjp_0 = null;
    private int mActiveWidgetColor;
    private int[] mAllowedGestures = new int[]{3, 3, 3};
    private CompressFormat mCompressFormat = DEFAULT_COMPRESS_FORMAT;
    private int mCompressQuality = 90;
    private List<ViewGroup> mCropAspectRatioViews = new ArrayList();
    private GestureCropImageView mGestureCropImageView;
    private TransformImageListener mImageListener = new TransformImageListener() {
        public void onRotate(float f) {
            UCropActivity.this.setAngleText(f);
        }

        public void onScale(float f) {
            UCropActivity.this.setScaleText(f);
        }

        public void onLoadComplete() {
            UCropActivity.this.mUCropView.animate().alpha(1.0f).setDuration(300).setInterpolator(new AccelerateInterpolator());
            UCropActivity.this.supportInvalidateOptionsMenu();
        }

        public void onLoadFailure(@NonNull Exception exception) {
            UCropActivity.this.setResultError(exception);
            UCropActivity.this.finish();
        }
    };
    private ViewGroup mLayoutRotate;
    private ViewGroup mLayoutScale;
    private int mLogoColor;
    private OverlayView mOverlayView;
    @ColorInt
    private int mRootViewBackgroundColor;
    private boolean mShowBottomControls;
    private final OnClickListener mStateClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (!view.isSelected()) {
                UCropActivity.this.setWidgetState(view.getId());
            }
        }
    };
    private TextView mTextViewRotateAngle;
    private TextView mTextViewScalePercent;
    private String mToolbarTitle;
    private UCropView mUCropView;
    private ViewGroup mWrapperStateRotate;
    private ViewGroup mWrapperStateScale;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            UCropActivity.onCreate_aroundBody0((UCropActivity) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GestureTypes {
    }

    private static void ajc$preClinit() {
        b bVar = new b("UCropActivity.java", UCropActivity.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("1", "onCreate", "com.yalantis.ucrop.UCropActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 89);
    }

    static {
        ajc$preClinit();
    }

    static final void onCreate_aroundBody0(UCropActivity uCropActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        uCropActivity.setContentView(d.ucrop_activity_photobox);
        Intent intent = uCropActivity.getIntent();
        uCropActivity.setupViews(intent);
        uCropActivity.setImageData(intent);
        uCropActivity.setInitialState();
    }

    public void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void onStop() {
        super.onStop();
        if (this.mGestureCropImageView != null) {
            this.mGestureCropImageView.cancelAllAnimations();
        }
    }

    private void setImageData(@NonNull Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra(UCrop.EXTRA_INPUT_URI);
        Uri uri2 = (Uri) intent.getParcelableExtra(UCrop.EXTRA_OUTPUT_URI);
        processOptions(intent);
        if (uri == null || uri2 == null) {
            setResultError(new NullPointerException(getString(e.ucrop_error_input_data_is_absent)));
            finish();
            return;
        }
        try {
            this.mGestureCropImageView.setImageUri(uri, uri2);
        } catch (Throwable e) {
            setResultError(e);
            finish();
        }
    }

    private void processOptions(@NonNull Intent intent) {
        Object stringExtra = intent.getStringExtra(Options.EXTRA_COMPRESSION_FORMAT_NAME);
        CompressFormat compressFormat = null;
        if (!TextUtils.isEmpty(stringExtra)) {
            compressFormat = CompressFormat.valueOf(stringExtra);
        }
        if (compressFormat == null) {
            compressFormat = DEFAULT_COMPRESS_FORMAT;
        }
        this.mCompressFormat = compressFormat;
        this.mCompressQuality = intent.getIntExtra(Options.EXTRA_COMPRESSION_QUALITY, 90);
        int[] intArrayExtra = intent.getIntArrayExtra(Options.EXTRA_ALLOWED_GESTURES);
        if (intArrayExtra != null && intArrayExtra.length == 3) {
            this.mAllowedGestures = intArrayExtra;
        }
        this.mGestureCropImageView.setMaxBitmapSize(intent.getIntExtra(Options.EXTRA_MAX_BITMAP_SIZE, 0));
        this.mGestureCropImageView.setMaxScaleMultiplier(intent.getFloatExtra(Options.EXTRA_MAX_SCALE_MULTIPLIER, 10.0f));
        this.mGestureCropImageView.setImageToWrapCropBoundsAnimDuration((long) intent.getIntExtra(Options.EXTRA_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 500));
        this.mOverlayView.setFreestyleCropEnabled(intent.getBooleanExtra(Options.EXTRA_FREE_STYLE_CROP, false));
        this.mOverlayView.setDimmedColor(intent.getIntExtra(Options.EXTRA_DIMMED_LAYER_COLOR, getResources().getColor(cn.xiaochuankeji.a.a.a.ucrop_color_default_dimmed)));
        this.mOverlayView.setCircleDimmedLayer(intent.getBooleanExtra(Options.EXTRA_CIRCLE_DIMMED_LAYER, false));
        this.mOverlayView.setShowCropFrame(intent.getBooleanExtra(Options.EXTRA_SHOW_CROP_FRAME, true));
        this.mOverlayView.setCropFrameColor(intent.getIntExtra(Options.EXTRA_CROP_FRAME_COLOR, getResources().getColor(cn.xiaochuankeji.a.a.a.ucrop_color_default_crop_frame)));
        this.mOverlayView.setCropFrameStrokeWidth(intent.getIntExtra(Options.EXTRA_CROP_FRAME_STROKE_WIDTH, getResources().getDimensionPixelSize(cn.xiaochuankeji.a.a.b.ucrop_default_crop_frame_stoke_width)));
        this.mOverlayView.setShowCropGrid(intent.getBooleanExtra(Options.EXTRA_SHOW_CROP_GRID, true));
        this.mOverlayView.setCropGridRowCount(intent.getIntExtra(Options.EXTRA_CROP_GRID_ROW_COUNT, 2));
        this.mOverlayView.setCropGridColumnCount(intent.getIntExtra(Options.EXTRA_CROP_GRID_COLUMN_COUNT, 2));
        this.mOverlayView.setCropGridColor(intent.getIntExtra(Options.EXTRA_CROP_GRID_COLOR, getResources().getColor(cn.xiaochuankeji.a.a.a.ucrop_color_default_crop_grid)));
        this.mOverlayView.setCropGridStrokeWidth(intent.getIntExtra(Options.EXTRA_CROP_GRID_STROKE_WIDTH, getResources().getDimensionPixelSize(cn.xiaochuankeji.a.a.b.ucrop_default_crop_grid_stoke_width)));
        float floatExtra = intent.getFloatExtra(UCrop.EXTRA_ASPECT_RATIO_X, 0.0f);
        float floatExtra2 = intent.getFloatExtra(UCrop.EXTRA_ASPECT_RATIO_Y, 0.0f);
        int intExtra = intent.getIntExtra(Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (floatExtra > 0.0f && floatExtra2 > 0.0f) {
            this.mGestureCropImageView.setTargetAspectRatio(floatExtra / floatExtra2);
        } else if (parcelableArrayListExtra == null || intExtra >= parcelableArrayListExtra.size()) {
            this.mGestureCropImageView.setTargetAspectRatio(0.0f);
        } else {
            this.mGestureCropImageView.setTargetAspectRatio(((AspectRatio) parcelableArrayListExtra.get(intExtra)).getAspectRatioX() / ((AspectRatio) parcelableArrayListExtra.get(intExtra)).getAspectRatioY());
        }
        int intExtra2 = intent.getIntExtra(UCrop.EXTRA_MAX_SIZE_X, 0);
        int intExtra3 = intent.getIntExtra(UCrop.EXTRA_MAX_SIZE_Y, 0);
        if (intExtra2 > 0 && intExtra3 > 0) {
            this.mGestureCropImageView.setMaxResultImageSizeX(intExtra2);
            this.mGestureCropImageView.setMaxResultImageSizeY(intExtra3);
        }
    }

    private void setupViews(@NonNull Intent intent) {
        String str;
        boolean z;
        this.mActiveWidgetColor = intent.getIntExtra(Options.EXTRA_UCROP_COLOR_WIDGET_ACTIVE, ContextCompat.getColor(this, cn.xiaochuankeji.a.a.a.ucrop_color_widget_active));
        this.mToolbarTitle = intent.getStringExtra(Options.EXTRA_UCROP_TITLE_TEXT_TOOLBAR);
        if (this.mToolbarTitle != null) {
            str = this.mToolbarTitle;
        } else {
            str = getResources().getString(e.ucrop_label_edit_photo);
        }
        this.mToolbarTitle = str;
        this.mLogoColor = intent.getIntExtra(Options.EXTRA_UCROP_LOGO_COLOR, ContextCompat.getColor(this, cn.xiaochuankeji.a.a.a.ucrop_color_default_logo));
        if (intent.getBooleanExtra(Options.EXTRA_HIDE_BOTTOM_CONTROLS, false)) {
            z = false;
        } else {
            z = true;
        }
        this.mShowBottomControls = z;
        this.mRootViewBackgroundColor = intent.getIntExtra(Options.EXTRA_UCROP_ROOT_VIEW_BACKGROUND_COLOR, ContextCompat.getColor(this, cn.xiaochuankeji.a.a.a.ucrop_color_crop_background));
        initiateRootViews();
        if (this.mShowBottomControls) {
            this.mWrapperStateRotate = (ViewGroup) findViewById(cn.xiaochuankeji.a.a.c.state_rotate);
            this.mWrapperStateRotate.setOnClickListener(this.mStateClickListener);
            this.mWrapperStateScale = (ViewGroup) findViewById(cn.xiaochuankeji.a.a.c.state_scale);
            this.mWrapperStateScale.setOnClickListener(this.mStateClickListener);
            this.mLayoutRotate = (ViewGroup) findViewById(cn.xiaochuankeji.a.a.c.layout_rotate_wheel);
            this.mLayoutScale = (ViewGroup) findViewById(cn.xiaochuankeji.a.a.c.layout_scale_wheel);
            setupAspectRatioWidget(intent);
            setupRotateWidget();
            setupScaleWidget();
            setupStatesWrapper();
        }
        findViewById(cn.xiaochuankeji.a.a.c.reset).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UCropActivity.this.resetRotation();
                if (UCropActivity.this.mGestureCropImageView != null) {
                    UCropActivity.this.mGestureCropImageView.zoomInImage(1.0f);
                }
            }
        });
        findViewById(cn.xiaochuankeji.a.a.c.crop).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UCropActivity.this.cropAndSaveImage();
            }
        });
        findViewById(cn.xiaochuankeji.a.a.c.wrapper_reset_rotate).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UCropActivity.this.resetRotation();
            }
        });
        findViewById(cn.xiaochuankeji.a.a.c.wrapper_rotate_by_angle).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UCropActivity.this.rotateByAngle(90);
            }
        });
    }

    private void initiateRootViews() {
        this.mUCropView = (UCropView) findViewById(cn.xiaochuankeji.a.a.c.ucrop);
        this.mGestureCropImageView = this.mUCropView.getCropImageView();
        this.mOverlayView = this.mUCropView.getOverlayView();
        this.mGestureCropImageView.setTransformImageListener(this.mImageListener);
        ((ImageView) findViewById(cn.xiaochuankeji.a.a.c.image_view_logo)).setColorFilter(this.mLogoColor, Mode.SRC_ATOP);
        findViewById(cn.xiaochuankeji.a.a.c.ucrop_frame).setBackgroundColor(this.mRootViewBackgroundColor);
    }

    private void setupStatesWrapper() {
        ImageView imageView = (ImageView) findViewById(cn.xiaochuankeji.a.a.c.image_view_state_scale);
        ImageView imageView2 = (ImageView) findViewById(cn.xiaochuankeji.a.a.c.image_view_state_rotate);
        imageView.setImageDrawable(new SelectedStateListDrawable(imageView.getDrawable(), this.mActiveWidgetColor));
        imageView2.setImageDrawable(new SelectedStateListDrawable(imageView2.getDrawable(), this.mActiveWidgetColor));
    }

    @TargetApi(21)
    private void setStatusBarColor(@ColorInt int i) {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            if (window != null) {
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(i);
            }
        }
    }

    private void setupAspectRatioWidget(@NonNull Intent intent) {
        int intExtra = intent.getIntExtra(Options.EXTRA_ASPECT_RATIO_SELECTED_BY_DEFAULT, 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(Options.EXTRA_ASPECT_RATIO_OPTIONS);
        if (parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
            intExtra = 2;
            parcelableArrayListExtra = new ArrayList();
            parcelableArrayListExtra.add(new AspectRatio(null, 1.0f, 1.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 4.0f));
            parcelableArrayListExtra.add(new AspectRatio(getString(e.ucrop_label_original).toUpperCase(), 0.0f, 0.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 3.0f, 2.0f));
            parcelableArrayListExtra.add(new AspectRatio(null, 16.0f, 9.0f));
        }
        int i = intExtra;
        LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
        layoutParams.weight = 1.0f;
        Iterator it = parcelableArrayListExtra.iterator();
        while (it.hasNext()) {
            AspectRatio aspectRatio = (AspectRatio) it.next();
            FrameLayout frameLayout = (FrameLayout) getLayoutInflater().inflate(d.ucrop_aspect_ratio, null);
            frameLayout.setLayoutParams(layoutParams);
            AspectRatioTextView aspectRatioTextView = (AspectRatioTextView) frameLayout.getChildAt(0);
            aspectRatioTextView.setActiveColor(this.mActiveWidgetColor);
            aspectRatioTextView.setAspectRatio(aspectRatio);
            this.mCropAspectRatioViews.add(frameLayout);
        }
        ((ViewGroup) this.mCropAspectRatioViews.get(i)).setSelected(true);
        for (ViewGroup onClickListener : this.mCropAspectRatioViews) {
            onClickListener.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    UCropActivity.this.mGestureCropImageView.setTargetAspectRatio(((AspectRatioTextView) ((ViewGroup) view).getChildAt(0)).getAspectRatio(view.isSelected()));
                    UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
                    if (!view.isSelected()) {
                        for (View view2 : UCropActivity.this.mCropAspectRatioViews) {
                            view2.setSelected(view2 == view);
                        }
                    }
                }
            });
        }
    }

    private void setupRotateWidget() {
        this.mTextViewRotateAngle = (TextView) findViewById(cn.xiaochuankeji.a.a.c.text_view_rotate);
        ((HorizontalProgressWheelView) findViewById(cn.xiaochuankeji.a.a.c.rotate_scroll_wheel)).setScrollingListener(new ScrollingListener() {
            public void onScroll(float f, float f2) {
                UCropActivity.this.mGestureCropImageView.postRotate(f / 42.0f);
            }

            public void onScrollEnd() {
                UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
            }

            public void onScrollStart() {
                UCropActivity.this.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView) findViewById(cn.xiaochuankeji.a.a.c.rotate_scroll_wheel)).setMiddleLineColor(this.mActiveWidgetColor);
    }

    private void setupScaleWidget() {
        this.mTextViewScalePercent = (TextView) findViewById(cn.xiaochuankeji.a.a.c.text_view_scale);
        ((HorizontalProgressWheelView) findViewById(cn.xiaochuankeji.a.a.c.scale_scroll_wheel)).setScrollingListener(new ScrollingListener() {
            public void onScroll(float f, float f2) {
                if (f > 0.0f) {
                    UCropActivity.this.mGestureCropImageView.zoomInImage(UCropActivity.this.mGestureCropImageView.getCurrentScale() + (((UCropActivity.this.mGestureCropImageView.getMaxScale() - UCropActivity.this.mGestureCropImageView.getMinScale()) / 15000.0f) * f));
                } else {
                    UCropActivity.this.mGestureCropImageView.zoomOutImage(UCropActivity.this.mGestureCropImageView.getCurrentScale() + (((UCropActivity.this.mGestureCropImageView.getMaxScale() - UCropActivity.this.mGestureCropImageView.getMinScale()) / 15000.0f) * f));
                }
            }

            public void onScrollEnd() {
                UCropActivity.this.mGestureCropImageView.setImageToWrapCropBounds();
            }

            public void onScrollStart() {
                UCropActivity.this.mGestureCropImageView.cancelAllAnimations();
            }
        });
        ((HorizontalProgressWheelView) findViewById(cn.xiaochuankeji.a.a.c.scale_scroll_wheel)).setMiddleLineColor(this.mActiveWidgetColor);
    }

    private void setAngleText(float f) {
        if (this.mTextViewRotateAngle != null) {
            this.mTextViewRotateAngle.setText(String.format(Locale.getDefault(), "旋转 %.1f°", new Object[]{Float.valueOf(f)}));
        }
    }

    private void setScaleText(float f) {
        if (this.mTextViewScalePercent != null) {
            this.mTextViewScalePercent.setText(String.format(Locale.getDefault(), "缩放 %d%%", new Object[]{Integer.valueOf((int) (100.0f * f))}));
        }
    }

    private void resetRotation() {
        this.mGestureCropImageView.postRotate(-this.mGestureCropImageView.getCurrentAngle());
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }

    private void rotateByAngle(int i) {
        this.mGestureCropImageView.postRotate((float) i);
        this.mGestureCropImageView.setImageToWrapCropBounds();
    }

    private void setInitialState() {
        if (this.mShowBottomControls) {
            setWidgetState(cn.xiaochuankeji.a.a.c.state_scale);
        } else {
            setAllowedGestures(0);
        }
    }

    private void setWidgetState(@IdRes int i) {
        int i2 = 8;
        if (this.mShowBottomControls) {
            boolean z;
            int i3;
            this.mWrapperStateRotate.setSelected(i == cn.xiaochuankeji.a.a.c.state_rotate);
            ViewGroup viewGroup = this.mWrapperStateScale;
            if (i == cn.xiaochuankeji.a.a.c.state_scale) {
                z = true;
            } else {
                z = false;
            }
            viewGroup.setSelected(z);
            viewGroup = this.mLayoutRotate;
            if (i == cn.xiaochuankeji.a.a.c.state_rotate) {
                i3 = 0;
            } else {
                i3 = 8;
            }
            viewGroup.setVisibility(i3);
            ViewGroup viewGroup2 = this.mLayoutScale;
            if (i == cn.xiaochuankeji.a.a.c.state_scale) {
                i2 = 0;
            }
            viewGroup2.setVisibility(i2);
            if (i == cn.xiaochuankeji.a.a.c.state_scale) {
                setAllowedGestures(0);
            } else if (i == cn.xiaochuankeji.a.a.c.state_rotate) {
                setAllowedGestures(1);
            } else {
                setAllowedGestures(2);
            }
        }
    }

    private void setAllowedGestures(int i) {
        boolean z;
        boolean z2 = false;
        GestureCropImageView gestureCropImageView = this.mGestureCropImageView;
        if (this.mAllowedGestures[i] == 3 || this.mAllowedGestures[i] == 1) {
            z = true;
        } else {
            z = false;
        }
        gestureCropImageView.setScaleEnabled(z);
        GestureCropImageView gestureCropImageView2 = this.mGestureCropImageView;
        if (this.mAllowedGestures[i] == 3 || this.mAllowedGestures[i] == 2) {
            z2 = true;
        }
        gestureCropImageView2.setRotateEnabled(z2);
    }

    protected void cropAndSaveImage() {
        final cn.xiaochuan.image.a.c a = cn.xiaochuan.image.a.c.a();
        a.a(getSupportFragmentManager());
        this.mGestureCropImageView.cropAndSaveImage(this.mCompressFormat, this.mCompressQuality, new BitmapCropCallback() {
            public void onBitmapCropped(@NonNull Uri uri, int i, int i2, int i3, int i4) {
                a.dismiss();
                UCropActivity.this.setResultUri(uri, UCropActivity.this.mGestureCropImageView.getTargetAspectRatio(), i, i2, i3, i4);
                UCropActivity.this.finish();
            }

            public void onCropFailure(@NonNull Throwable th) {
                a.dismiss();
                UCropActivity.this.setResultError(th);
                UCropActivity.this.finish();
            }
        });
    }

    protected void setResultUri(Uri uri, float f, int i, int i2, int i3, int i4) {
        setResult(-1, new Intent().putExtra(UCrop.EXTRA_OUTPUT_URI, uri).putExtra(UCrop.EXTRA_OUTPUT_CROP_ASPECT_RATIO, f).putExtra(UCrop.EXTRA_OUTPUT_IMAGE_WIDTH, i3).putExtra(UCrop.EXTRA_OUTPUT_IMAGE_HEIGHT, i4).putExtra(UCrop.EXTRA_OUTPUT_OFFSET_X, i).putExtra(UCrop.EXTRA_OUTPUT_OFFSET_Y, i2));
    }

    protected void setResultError(Throwable th) {
        setResult(96, new Intent().putExtra(UCrop.EXTRA_ERROR, th));
    }
}
