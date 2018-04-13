package qsbk.app.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.alipay.sdk.cons.b;
import com.baidu.mobstat.Config;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.ImageFragmentPagerAdapter;
import qsbk.app.core.Arrays;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.ui.base.BaseSystemBarTintActivity;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.fragments.BrowseBaseFragment;
import qsbk.app.fragments.BrowseBaseFragment.MediaClickListener;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.TransitionDraweeView;

public class ImageViewer extends BaseSystemBarTintActivity implements OnPageChangeListener, OnClickListener, MediaClickListener {
    public static final float[] BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7f, 0.0f};
    public static final String KEY_DELETE_MODE = "isDeleteMode";
    public static final String KEY_GROUP_ID = "groupId";
    public static final String KEY_IMAGE_DATA = "image_data";
    public static final String KEY_IMAGE_POSITION = "image_position";
    public static final String KEY_IMG_LOCATION = "image_location";
    protected boolean a = false;
    protected int b = 0;
    protected ImageView c;
    protected ViewPager d;
    protected ImageFragmentPagerAdapter e;
    protected List<ImageInfo> f;
    ScaleType g = ScaleType.FIT_CENTER;
    ScaleType h = ScaleType.CENTER_CROP;
    private View i;
    private View j;
    private TransitionDraweeView k;
    private Rect[] l;
    private Rect m;
    private boolean n;
    private boolean o;
    private int p;
    private MediaScannerConnection q = null;

    public static void launch(Context context, int i, ArrayList<? extends ImageInfo> arrayList, Rect[] rectArr, boolean z, int i2) {
        Intent intent = new Intent(context, ImageViewer.class);
        intent.putExtra("image_position", i);
        intent.putExtra("image_data", arrayList);
        intent.putExtra("image_location", rectArr);
        intent.putExtra(KEY_DELETE_MODE, z);
        intent.putExtra(KEY_GROUP_ID, i2);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }

    public static void launch(Context context, int i, ArrayList<? extends ImageInfo> arrayList, Rect[] rectArr) {
        launch(context, null, i, arrayList, rectArr);
    }

    public static void launch(Context context, ImageInfo imageInfo, Rect rect) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(imageInfo);
        launch(context, 0, arrayList, new Rect[]{rect});
    }

    public static void launch(Context context, String str, int i, ArrayList<? extends ImageInfo> arrayList, Rect[] rectArr) {
        Intent intent = new Intent(context, ImageViewer.class);
        intent.putExtra("image_position", i);
        intent.putExtra("image_data", arrayList);
        intent.putExtra("image_location", rectArr);
        intent.putExtra(KEY_GROUP_ID, str);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }

    protected boolean isNeedImmersiveStatusBar() {
        return true;
    }

    protected boolean isNeedImmersiveNavigationBar() {
        return true;
    }

    protected void onCreate(Bundle bundle) {
        Uri parse;
        Uri uri;
        Uri parse2;
        Exception e;
        ImageInfo imageInfo;
        Object obj;
        Exception exception;
        ImageInfo imageInfo2 = null;
        super.onCreate(bundle);
        setContentView(a());
        a(getIntent());
        i();
        c();
        b();
        try {
            if (this.f.size() > this.b) {
                ImageInfo imageInfo3 = (ImageInfo) this.f.get(this.b);
                try {
                    parse = Uri.parse(imageInfo3.getImageUrl());
                    try {
                        uri = parse;
                        imageInfo2 = imageInfo3;
                        parse2 = Uri.parse(imageInfo3.getBigImageUrl());
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            uri = parse;
                            imageInfo = imageInfo2;
                            imageInfo2 = imageInfo3;
                            obj = imageInfo;
                            parse2 = uri;
                        } catch (Exception e3) {
                            imageInfo2 = imageInfo3;
                            exception = e3;
                            exception.printStackTrace();
                            parse2 = parse;
                            if (imageInfo2 == null) {
                                finish();
                                return;
                            }
                            this.k.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(parse2))).setOldController(this.k.getController())).build());
                            this.k.setAfterScaleType(imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
                            this.k.setPreScaleType(this.h);
                            this.k.getViewTreeObserver().addOnPreDrawListener(new oq(this));
                        }
                        if (imageInfo2 == null) {
                            finish();
                            return;
                        }
                        this.k.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(parse2))).setOldController(this.k.getController())).build());
                        if (imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG) {
                        }
                        this.k.setAfterScaleType(imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
                        this.k.setPreScaleType(this.h);
                        this.k.getViewTreeObserver().addOnPreDrawListener(new oq(this));
                    }
                } catch (Exception e4) {
                    e3 = e4;
                    parse = imageInfo2;
                    e3.printStackTrace();
                    uri = parse;
                    imageInfo = imageInfo2;
                    imageInfo2 = imageInfo3;
                    obj = imageInfo;
                    parse2 = uri;
                    if (imageInfo2 == null) {
                        this.k.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(parse2))).setOldController(this.k.getController())).build());
                        if (imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG) {
                        }
                        this.k.setAfterScaleType(imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
                        this.k.setPreScaleType(this.h);
                        this.k.getViewTreeObserver().addOnPreDrawListener(new oq(this));
                    }
                    finish();
                    return;
                }
            }
            obj = imageInfo2;
            Object obj2 = imageInfo2;
            if (uri != null || r0 == null) {
                parse2 = uri;
            }
        } catch (Exception e5) {
            exception = e5;
            parse = imageInfo2;
            exception.printStackTrace();
            parse2 = parse;
            if (imageInfo2 == null) {
                this.k.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(parse2))).setOldController(this.k.getController())).build());
                if (imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG) {
                }
                this.k.setAfterScaleType(imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
                this.k.setPreScaleType(this.h);
                this.k.getViewTreeObserver().addOnPreDrawListener(new oq(this));
            }
            finish();
            return;
        }
        if (imageInfo2 == null) {
            finish();
            return;
        }
        this.k.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(parse2))).setOldController(this.k.getController())).build());
        if (imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG) {
        }
        this.k.setAfterScaleType(imageInfo2.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
        this.k.setPreScaleType(this.h);
        this.k.getViewTreeObserver().addOnPreDrawListener(new oq(this));
    }

    protected int a() {
        return R.layout.layout_imageviewer;
    }

    protected void a(Intent intent) {
        this.f = (List) getIntent().getSerializableExtra("image_data");
        this.b = getIntent().getIntExtra("image_position", 0);
        if (this.f == null) {
            finish();
            return;
        }
        if (this.b >= this.f.size()) {
            this.b = 0;
        }
        Parcelable[] parcelableArrayExtra = getIntent().getParcelableArrayExtra("image_location");
        if (parcelableArrayExtra != null) {
            List asList = Arrays.asList(parcelableArrayExtra);
            this.l = (Rect[]) asList.toArray(new Rect[asList.size()]);
        }
        if (this.l == null) {
            this.l = new Rect[this.f.size()];
        }
        this.o = intent.getBooleanExtra(KEY_DELETE_MODE, false);
        if (this.o) {
            this.p = intent.getIntExtra(KEY_GROUP_ID, -1);
        }
    }

    private void i() {
    }

    protected void b() {
        this.c.setOnClickListener(this);
    }

    protected void c() {
        this.c = (ImageView) findViewById(R.id.saveBtn);
        if (this.o) {
            this.c.setImageResource(R.drawable.delete);
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        this.i = findViewById(R.id.container);
        this.k = (TransitionDraweeView) findViewById(R.id.transition_img);
        this.j = findViewById(R.id.btn_container);
        if (isNeedImmersiveNavigationBar() && UIHelper.hasSoftNavigationBar(this) && this.j != null) {
            int navigationBarHeight = WindowUtils.getNavigationBarHeight();
            if (navigationBarHeight > 0) {
                this.j.setPadding(this.j.getPaddingLeft(), this.j.getPaddingTop(), this.j.getPaddingRight(), navigationBarHeight + this.j.getPaddingBottom());
            }
        }
        d();
    }

    protected void d() {
        this.d = (ViewPager) findViewById(R.id.view_pager);
        this.e = new ImageFragmentPagerAdapter(getSupportFragmentManager(), this.f);
        this.d.setAdapter(this.e);
        this.d.setOnPageChangeListener(this);
        this.d.setCurrentItem(this.b);
    }

    protected void e() {
        this.k.setBackgroundColor(getResources().getColor(R.color.transparent));
        int[] iArr = new int[2];
        Rect rect = this.l[this.b];
        this.k.getLocationOnScreen(iArr);
        this.m = new Rect(iArr[0], iArr[1], iArr[0] + this.k.getWidth(), iArr[1] + this.k.getHeight());
        this.k.setPreBounds(rect);
        this.k.setAfterBounds(this.m);
        this.k.setOnEnterAnimListener(new or(this));
        this.d.setVisibility(4);
        this.k.startEnterAnim();
    }

    public void runExitAnimation(Runnable runnable) {
        boolean z;
        ObjectAnimator ofObject;
        Rect rect = null;
        if (this.d.getCurrentItem() < this.l.length) {
            rect = this.l[this.d.getCurrentItem()];
            if (rect != null && rect.width() > 0 && rect.height() > 0) {
                z = false;
                if (z) {
                    rect = new Rect(this.m);
                    rect.inset((this.m.width() - UIHelper.dip2px(this, 60.0f)) / 2, (this.m.height() - UIHelper.dip2px(this, 60.0f)) / 2);
                    this.k.setPreBounds(rect);
                }
                this.k.setBackgroundColor(getResources().getColor(R.color.black));
                this.k.setVisibility(0);
                this.k.setPreBounds(rect);
                this.k.setOnExitAnimListener(new os(this, runnable));
                this.k.setFadeOut(z);
                this.k.startExitAnim();
                ofObject = ObjectAnimator.ofObject(this.k, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(-16777216), Integer.valueOf(0)});
                ofObject.setDuration((long) this.k.getAnimDuration());
                ofObject.start();
                this.d.setVisibility(4);
            }
        }
        z = true;
        if (z) {
            rect = new Rect(this.m);
            rect.inset((this.m.width() - UIHelper.dip2px(this, 60.0f)) / 2, (this.m.height() - UIHelper.dip2px(this, 60.0f)) / 2);
            this.k.setPreBounds(rect);
        }
        this.k.setBackgroundColor(getResources().getColor(R.color.black));
        this.k.setVisibility(0);
        this.k.setPreBounds(rect);
        this.k.setOnExitAnimListener(new os(this, runnable));
        this.k.setFadeOut(z);
        this.k.startExitAnim();
        ofObject = ObjectAnimator.ofObject(this.k, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(-16777216), Integer.valueOf(0)});
        ofObject.setDuration((long) this.k.getAnimDuration());
        ofObject.start();
        this.d.setVisibility(4);
    }

    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(0, 0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                if (this.o) {
                    int currentItem = this.d.getCurrentItem();
                    if (currentItem == 0) {
                        new Builder(this).setMessage("群头像不能删除，你可以更换群头像。").setPositiveButton("更换群头像", new ot(this, currentItem)).create().show();
                        return;
                    } else {
                        f();
                        return;
                    }
                }
                if (TextUtils.isEmpty(DeviceUtils.getSDPath())) {
                    this.a = true;
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现SD卡,保存失败！", Integer.valueOf(0)).show();
                    view.setVisibility(4);
                } else {
                    g();
                }
                this.c.setImageResource(R.drawable.icon_save_active);
                return;
            default:
                return;
        }
    }

    protected void f() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String format = String.format(Constants.URL_GROUP_DETAIL, new Object[]{String.valueOf(this.p)});
        Map hashMap = new HashMap();
        int currentItem = this.d.getCurrentItem();
        hashMap.put("pic_urls", currentItem + Config.TRACE_TODAY_VISIT_SPLIT);
        hashMap.put(b.c, String.valueOf(this.p));
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new ou(this, progressDialog, currentItem));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void g() {
        this.e.getCurrentFragment().saveMedia();
    }

    public void finish() {
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        h();
    }

    protected void h() {
        if (!this.n) {
            this.n = true;
            runExitAnimation(new ov(this));
        }
    }

    public void onMediaClick() {
        h();
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        ImageInfo imageInfo = (ImageInfo) this.f.get(i);
        String bigImageUrl = imageInfo.getBigImageUrl();
        if (!FrescoImageloader.isInMemoryCache(bigImageUrl)) {
            bigImageUrl = null;
        }
        DraweeController build = ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(bigImageUrl))).setOldController(this.k.getController())).build();
        this.k.setAfterScaleType(imageInfo.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
        this.k.setPreScaleType(imageInfo.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.CENTER_CROP);
        this.k.setController(build);
        if (!this.o) {
            Fragment fragmentAt = this.e.getFragmentAt(i);
            if (fragmentAt != null && (fragmentAt instanceof BrowseBaseFragment)) {
                this.c.setImageResource(((BrowseBaseFragment) fragmentAt).isMediaSaved() ? R.drawable.icon_save_active : R.drawable.icon_save_selector);
                ImageView imageView = this.c;
                int i2 = (MediaFormat.IMAGE_GIF.equals(imageInfo.mediaFormat) || !TextUtils.isEmpty(DeviceUtils.getSDPath())) ? 0 : 4;
                imageView.setVisibility(i2);
            }
        }
    }
}
