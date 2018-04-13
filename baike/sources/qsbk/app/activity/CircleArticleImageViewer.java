package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.ImageFragmentPagerAdapter;
import qsbk.app.core.Arrays;
import qsbk.app.core.ui.base.BaseSystemBarTintActivity;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.fragments.BrowseBaseFragment;
import qsbk.app.fragments.BrowseBaseFragment.MediaClickListener;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.PicUrl;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ArticleMoreOperationbar;
import qsbk.app.widget.TransitionDraweeView;

public class CircleArticleImageViewer extends BaseSystemBarTintActivity implements OnPageChangeListener, MediaClickListener, ShareUtils$OnCircleShareStartListener, OnArticleUpdateListener {
    public static final float[] BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7f, 0.0f};
    public static final String KEY_CIRCLE_ARTICLE = "circleArticle";
    public static final String KEY_FROM_CIRCLE = "from_circle";
    public static final String KEY_IMAGE_CONTENT_ID = "contentId";
    public static final String KEY_IMAGE_DATA = "image_data";
    public static final String KEY_IMAGE_POSITION = "image_position";
    public static final String KEY_IMG_LOCATION = "image_location";
    public static final String TAG = CircleArticleImageViewer.class.getSimpleName();
    protected ImageView a;
    protected ViewPager b;
    protected ImageFragmentPagerAdapter c;
    protected int d = 0;
    protected HashMap<String, String> e = new HashMap();
    protected HashMap<String, Boolean> f = new HashMap();
    ScaleType g = ScaleType.CENTER_CROP;
    ScaleType h = ScaleType.FIT_CENTER;
    private CircleArticle i;
    private boolean j;
    private View k;
    private View l;
    private TickerView m;
    private ImageView n;
    private TextView o;
    private TextView p;
    private View q;
    private View r;
    private TextView s;
    private TransitionDraweeView t;
    private String u;
    private Rect[] v;
    private Rect w;
    private boolean x;

    public static void launch(Context context, View view, Rect[] rectArr, CircleArticle circleArticle, String str, int i) {
        Intent intent = new Intent(context, CircleArticleImageViewer.class);
        intent.putExtra("contentId", circleArticle.id);
        intent.putExtra("image_position", i);
        intent.putExtra(KEY_FROM_CIRCLE, true);
        intent.putExtra("image_location", rectArr);
        intent.putExtra("circleArticle", circleArticle);
        intent.putExtra("current_url", str);
        if (context instanceof Activity) {
            context.startActivity(intent);
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            ((Activity) context).overridePendingTransition(0, 0);
            return;
        }
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (a(getIntent())) {
            setContentView(f());
            d();
            e();
            PicUrl picUrl = (PicUrl) this.i.picUrls.get(this.d);
            this.t.setAfterScaleType(picUrl.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
            this.t.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(picUrl.getImageUrl()))).setOldController(this.t.getController())).build());
            this.t.setPreScaleType(this.g);
            this.t.getViewTreeObserver().addOnPreDrawListener(new gp(this));
            CircleArticleBus.register(this);
            return;
        }
        finish();
    }

    protected boolean isNeedImmersiveStatusBar() {
        return true;
    }

    protected boolean isNeedImmersiveNavigationBar() {
        return true;
    }

    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(0, 0);
    }

    protected void a() {
        this.b = (ViewPager) findViewById(R.id.view_pager);
        ViewPager viewPager = this.b;
        ViewPager viewPager2 = this.b;
        viewPager.setVisibility(4);
        this.b.setOffscreenPageLimit(1);
        this.c = new ImageFragmentPagerAdapter(getSupportFragmentManager(), this.i.picUrls);
        this.b.setAdapter(this.c);
        this.b.setOnPageChangeListener(this);
        this.b.setCurrentItem(this.d);
    }

    protected void b() {
        this.l.setVisibility(0);
        this.l.setAlpha(0.0f);
        this.t.setBackgroundColor(getResources().getColor(R.color.transparent));
        int[] iArr = new int[2];
        Rect rect = this.v[this.d];
        this.t.getLocationOnScreen(iArr);
        this.w = new Rect(iArr[0], iArr[1], iArr[0] + this.t.getWidth(), iArr[1] + this.t.getHeight());
        this.t.setPreBounds(rect);
        this.t.setAfterBounds(this.w);
        this.t.setOnEnterAnimListener(new gq(this));
        this.t.startEnterAnim();
        this.b.setVisibility(4);
    }

    public void runExitAnimation(Runnable runnable) {
        boolean z = true;
        Rect rect = null;
        if (this.b.getCurrentItem() < this.v.length) {
            rect = this.v[this.b.getCurrentItem()];
            if (rect != null && rect.width() > 0 && rect.height() > 0) {
                z = false;
            }
        }
        if (z) {
            rect = new Rect(this.w);
            rect.inset((this.w.width() - UIHelper.dip2px(this, 60.0f)) / 2, (this.w.height() - UIHelper.dip2px(this, 60.0f)) / 2);
            this.t.setPreBounds(rect);
        }
        this.t.setBackgroundColor(getResources().getColor(R.color.black));
        this.t.setVisibility(0);
        this.t.setPreBounds(rect);
        this.t.setOnExitAnimListener(new gr(this, runnable));
        this.t.setFadeOut(z);
        this.t.startExitAnim();
        this.b.setVisibility(4);
    }

    protected void c() {
        if (!this.x) {
            this.x = true;
            this.l.setVisibility(8);
            runExitAnimation(new gs(this));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        CircleArticleBus.unregister(this);
    }

    protected void d() {
        this.k = findViewById(R.id.root_container);
        this.l = findViewById(R.id.ext_container);
        this.l.setVisibility(8);
        this.m = (TickerView) findViewById(R.id.like_count);
        this.m.setCharacterList(UIHelper.getDefaultNumberList(true));
        this.n = (ImageView) findViewById(R.id.like_count_bt);
        this.o = (TextView) findViewById(R.id.comment_count);
        this.r = findViewById(R.id.comment_bt);
        this.p = (TextView) findViewById(R.id.distance);
        this.q = findViewById(R.id.share_btn);
        this.s = (TextView) findViewById(R.id.indicator);
        this.t = (TransitionDraweeView) findViewById(R.id.transition_img);
        this.m.setTypeface(this.o.getTypeface());
        this.a = (ImageView) findViewById(R.id.saveBtn);
        if (this.j) {
            this.a.setImageResource(R.drawable.delete);
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(4);
        }
        if (isNeedImmersiveNavigationBar() && UIHelper.hasSoftNavigationBar(this)) {
            View findViewById = findViewById(R.id.btn_container);
            if (findViewById != null) {
                int navigationBarHeight = WindowUtils.getNavigationBarHeight();
                if (navigationBarHeight > 0) {
                    findViewById.setPadding(findViewById.getPaddingLeft(), findViewById.getPaddingTop(), findViewById.getPaddingRight(), navigationBarHeight + findViewById.getPaddingBottom());
                }
            }
        }
        this.k = findViewById(R.id.container);
        this.t = (TransitionDraweeView) findViewById(R.id.transition_img);
        a();
        h();
    }

    protected void e() {
        OnClickListener loginPermissionClickDelegate = new LoginPermissionClickDelegate(new gt(this), null);
        this.m.setOnClickListener(loginPermissionClickDelegate);
        this.n.setOnClickListener(loginPermissionClickDelegate);
        loginPermissionClickDelegate = new gv(this);
        this.o.setOnClickListener(loginPermissionClickDelegate);
        this.r.setOnClickListener(loginPermissionClickDelegate);
        this.a.setOnClickListener(new gw(this));
        this.q.setOnClickListener(new LoginPermissionClickDelegate(new gx(this), null));
    }

    private void h() {
        boolean z = false;
        this.o.setText(String.valueOf(this.i.commentCount));
        this.p.setText(this.i.distance);
        this.m.setTypeface(this.o.getTypeface());
        this.m.setText(String.valueOf(this.i.likeCount), false);
        this.m.setEnabled(!this.i.liked);
        ImageView imageView = this.n;
        if (!this.i.liked) {
            z = true;
        }
        imageView.setEnabled(z);
        if (this.c.getCount() > 1) {
            this.s.setText((this.d + 1) + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.c.getCount());
        }
    }

    protected boolean a(Intent intent) {
        this.d = getIntent().getIntExtra("image_position", 0);
        this.i = (CircleArticle) intent.getSerializableExtra("circleArticle");
        if (this.i == null || this.i.picUrls == null || this.i.picUrls.size() == 0) {
            return false;
        }
        if (this.d >= this.i.picUrls.size()) {
            this.d = 0;
        }
        this.u = getIntent().getStringExtra("current_url");
        List asList = Arrays.asList(getIntent().getParcelableArrayExtra("image_location"));
        this.v = (Rect[]) asList.toArray(new Rect[asList.size()]);
        if (this.v == null) {
            this.v = new Rect[this.i.picUrls.size()];
        }
        return true;
    }

    protected int f() {
        return R.layout.layout_imageviewer_circle;
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        ShareUtils.openShareDialog(this, 1, circleArticle);
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        ShareUtils.openShareDialog(this, 1, circleArticle, str);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && intent != null) {
            CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
            if (circleArticle != null) {
                if (i2 != 12) {
                    ArticleMoreOperationbar.handleShare(i2, (Activity) this, circleArticle);
                } else if (TextUtils.isEmpty(DeviceUtils.getSDPath())) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "未发现SD卡,保存失败！", Integer.valueOf(0)).show();
                    this.a.setVisibility(4);
                } else {
                    g();
                }
            }
        }
    }

    protected void g() {
        BrowseBaseFragment currentFragment = this.c.getCurrentFragment();
        if (currentFragment != null) {
            currentFragment.saveMedia();
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onBackPressed() {
        c();
    }

    public void onPageSelected(int i) {
        a(i);
        if (this.c == null || this.c.getCount() <= 0) {
            this.s.setText("");
        } else {
            this.s.setText((i + 1) + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.c.getCount());
        }
    }

    private void a(int i) {
        if (i < this.i.picUrls.size()) {
            PicUrl picUrl = (PicUrl) this.i.picUrls.get(i);
            if (this.d != i) {
                this.t.setAfterScaleType(picUrl.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
                this.t.setPreScaleType(picUrl.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.CENTER_CROP);
                FrescoImageloader.displayImage(this.t, picUrl.getImageUrl());
            }
            Fragment fragmentAt = this.c.getFragmentAt(i);
            if (fragmentAt != null && (fragmentAt instanceof BrowseBaseFragment)) {
                this.a.setImageResource(((BrowseBaseFragment) fragmentAt).isMediaSaved() ? R.drawable.icon_save_circle : R.drawable.qiuyou_circle_download);
            }
            this.a.setVisibility(!TextUtils.isEmpty(DeviceUtils.getSDPath()) ? 0 : 4);
            this.d = i;
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onArticleCreate(CircleArticle circleArticle) {
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.i != null && this.i.equals(circleArticle)) {
            this.i.updateWith(circleArticle);
            h();
        }
    }

    public void onArticleDelete(CircleArticle circleArticle) {
    }

    public void onMediaClick() {
        c();
    }
}
