package qsbk.app.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import cz.msebera.android.httpclient.message.TokenParser;
import java.util.HashMap;
import java.util.List;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
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
import qsbk.app.model.Article;
import qsbk.app.model.ArticleImage;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.Vote;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.service.VoteManager;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.FunnyTextView;
import qsbk.app.widget.ImageControlView;
import qsbk.app.widget.TransitionDraweeView;

public class NewImageViewer extends BaseSystemBarTintActivity implements OnPageChangeListener, MediaClickListener {
    public static final float[] BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7f, 0.0f};
    public static final String KEY_ARTICLE = "article";
    public static final String KEY_IMAGE_CONTENT_ID = "contentId";
    public static final String KEY_IMAGE_POSITION = "image_position";
    public static final String KEY_VOTE_POINT = "vote_point";
    ImageFragmentPagerAdapter a;
    private Article b = null;
    private int c = 0;
    private View d;
    private View e;
    private ViewPager f;
    private FunnyTextView g;
    private TextView h;
    private ImageControlView i;
    private View j;
    private TransitionDraweeView k;
    private String l;
    private Rect[] m;
    private Rect[] n;
    private Rect o;
    private boolean p;
    private String q = null;
    private int r;

    public static void launch(Context context, Rect[] rectArr, Rect[] rectArr2, Article article, int i) {
        Intent intent = new Intent(context, NewImageViewer.class);
        intent.putExtra("contentId", article.id);
        intent.putExtra("article", article);
        intent.putExtra("image_location", rectArr);
        intent.putExtra("image_position", i);
        intent.putExtra("image_visible", rectArr2);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivity(intent);
            activity.overridePendingTransition(0, 0);
            return;
        }
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_imageviewer_new);
        a(getIntent());
        if (this.b == null) {
            finish();
            return;
        }
        b();
        c();
        d();
        a();
    }

    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(0, 0);
    }

    protected boolean isNeedImmersiveStatusBar() {
        return true;
    }

    protected boolean isNeedImmersiveNavigationBar() {
        return true;
    }

    private void a() {
        String str;
        if (this.b.imageInfos.size() > this.c) {
            ArticleImage articleImage = (ArticleImage) this.b.imageInfos.get(this.c);
            String imageUrl = articleImage.getImageUrl();
            this.k.setAfterScaleType(articleImage.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
            str = imageUrl;
        } else {
            str = "";
        }
        FrescoImageloader.displayImage(this.k, str);
        int[] iArr = new int[2];
        this.k.getLocationOnScreen(iArr);
        this.k.setVisibility(0);
        this.d.setVisibility(4);
        this.d.setAlpha(0.0f);
        this.f.setVisibility(4);
        this.k.setBackgroundColor(getResources().getColor(R.color.transparent));
        if (this.n != null && this.n.length > 0) {
            this.k.setPreVisibleBounds(this.n[0]);
        }
        this.k.getViewTreeObserver().addOnPreDrawListener(new xw(this, iArr));
    }

    private void a(Intent intent) {
        this.l = getIntent().getStringExtra("contentId");
        this.b = (Article) getIntent().getSerializableExtra("article");
        this.c = intent.getIntExtra("image_position", 0);
        this.q = intent.getStringExtra(KEY_VOTE_POINT);
        if (TextUtils.isEmpty(this.q)) {
            this.q = "unknown";
        }
        if (this.b == null || this.b.imageInfos == null || this.b.imageInfos.size() == 0) {
            finish();
            return;
        }
        if (this.c >= this.b.imageInfos.size()) {
            this.c = 0;
        }
        Parcelable[] parcelableArrayExtra = getIntent().getParcelableArrayExtra("image_location");
        if (parcelableArrayExtra != null) {
            List asList = Arrays.asList(parcelableArrayExtra);
            this.m = (Rect[]) asList.toArray(new Rect[asList.size()]);
        }
        parcelableArrayExtra = getIntent().getParcelableArrayExtra("image_visible");
        if (parcelableArrayExtra != null) {
            asList = Arrays.asList(parcelableArrayExtra);
            this.n = (Rect[]) asList.toArray(new Rect[asList.size()]);
        }
    }

    private void b() {
        this.d = findViewById(R.id.ext_container);
        this.k = (TransitionDraweeView) findViewById(R.id.transition_img);
        this.e = findViewById(R.id.close);
        this.f = (ViewPager) findViewById(R.id.view_pager);
        this.g = (FunnyTextView) findViewById(R.id.funny_count);
        this.g.requestRightTickerView();
        this.g.setTextColor(-4276546);
        this.g.setTextSize(12.0f);
        this.h = (TextView) findViewById(R.id.comment_and_share_count);
        this.h.setTextColor(-4276546);
        this.h.setTextSize(12.0f);
        this.i = (ImageControlView) findViewById(R.id.image_control);
        Object obj = (QsbkApp.allCollection == null || !QsbkApp.allCollection.contains(this.l)) ? null : 1;
        if (obj != null) {
            this.i.share.setTag("active");
        } else {
            this.i.share.setTag("enable");
        }
        this.a = new ImageFragmentPagerAdapter(getSupportFragmentManager(), this.b.imageInfos);
        this.f.setAdapter(this.a);
        this.f.setCurrentItem(this.c);
        this.f.setOnPageChangeListener(this);
        this.j = findViewById(R.id.loading);
        if (isNeedImmersiveNavigationBar() && UIHelper.hasSoftNavigationBar(this)) {
            View findViewById = findViewById(R.id.btn_container);
            if (findViewById != null) {
                int navigationBarHeight = WindowUtils.getNavigationBarHeight();
                if (navigationBarHeight > 0) {
                    findViewById.setPadding(findViewById.getPaddingLeft(), findViewById.getPaddingTop(), findViewById.getPaddingRight(), navigationBarHeight + findViewById.getPaddingBottom());
                }
            }
        }
    }

    private void a(int i) {
        if (this.b != null && this.b.imageInfos != null && i < this.b.imageInfos.size()) {
            ImageInfo imageInfo = (ImageInfo) this.b.imageInfos.get(i);
            if (this.r != i) {
                this.k.setAfterScaleType(imageInfo.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.FIT_CENTER);
                this.k.setPreScaleType(imageInfo.mediaFormat == MediaFormat.IMAGE_LONG ? FrescoImageloader.SCALE_CENTER_TOP : ScaleType.CENTER_CROP);
                FrescoImageloader.displayImage(this.k, imageInfo.getImageUrl());
            }
            Fragment fragmentAt = this.a.getFragmentAt(i);
            if (fragmentAt != null && (fragmentAt instanceof BrowseBaseFragment)) {
                this.i.save.setImageResource(((BrowseBaseFragment) fragmentAt).isMediaSaved() ? R.drawable.icon_save_active : R.drawable.icon_save_selector);
            }
            this.r = i;
        }
    }

    private void c() {
        int displayLaugth;
        this.i.reset();
        if (VoteManager.getInstance().containsVote(this.l, "up")) {
            this.i.setSupport();
        } else if (VoteManager.getInstance().containsVote(this.l, Config.DEVICE_NAME)) {
            this.i.setUnSupport();
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (this.b.comment_count > 0) {
            stringBuilder.append("  ·  ").append(this.b.comment_count).append(TokenParser.SP).append("评论");
        }
        if (this.b.shareCount > 0) {
            stringBuilder.append("  ·  ").append(this.b.shareCount).append(TokenParser.SP).append("分享");
        }
        if (stringBuilder.length() > 0) {
            this.h.setVisibility(0);
            this.h.setText(stringBuilder.toString());
        } else {
            this.h.setVisibility(8);
        }
        if (this.b.getDisplayLaugth() > 0) {
            displayLaugth = this.b.getDisplayLaugth();
        } else {
            displayLaugth = 0;
        }
        this.g.setTypeface(this.h.getTypeface());
        this.g.setText(displayLaugth + "", false);
        this.g.setBaseText(" 好笑");
    }

    private void d() {
        this.e.setOnClickListener(new xz(this));
        this.i.setOnOperationSelectListener(new ya(this));
    }

    private boolean e() {
        BrowseBaseFragment currentFragment = this.a.getCurrentFragment();
        if (currentFragment != null) {
            currentFragment.saveMedia();
        }
        return true;
    }

    public void closeAfterTransition() {
        if (!this.p) {
            int i;
            ObjectAnimator ofObject;
            Rect rect = null;
            if (this.f.getCurrentItem() < this.m.length) {
                rect = this.m[this.f.getCurrentItem()];
                if (rect != null && rect.width() > 0 && rect.height() > 0) {
                    i = 0;
                    if (i != 0) {
                        rect = new Rect(this.o);
                        rect.inset((this.o.width() - UIHelper.dip2px(this, 60.0f)) / 2, (this.o.height() - UIHelper.dip2px(this, 60.0f)) / 2);
                    }
                    this.p = true;
                    this.k.setVisibility(0);
                    this.k.setPreBounds(rect);
                    this.f.setVisibility(4);
                    this.k.setBackgroundColor(getResources().getColor(R.color.black));
                    this.k.setOnExitAnimListener(new yb(this));
                    this.k.startExitAnim();
                    ofObject = ObjectAnimator.ofObject(this.k, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(-16777216), Integer.valueOf(0)});
                    ofObject.setDuration((long) this.k.getAnimDuration());
                    ofObject.start();
                }
            }
            boolean z = true;
            if (i != 0) {
                rect = new Rect(this.o);
                rect.inset((this.o.width() - UIHelper.dip2px(this, 60.0f)) / 2, (this.o.height() - UIHelper.dip2px(this, 60.0f)) / 2);
            }
            this.p = true;
            this.k.setVisibility(0);
            this.k.setPreBounds(rect);
            this.f.setVisibility(4);
            this.k.setBackgroundColor(getResources().getColor(R.color.black));
            this.k.setOnExitAnimListener(new yb(this));
            this.k.startExitAnim();
            ofObject = ObjectAnimator.ofObject(this.k, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(-16777216), Integer.valueOf(0)});
            ofObject.setDuration((long) this.k.getAnimDuration());
            ofObject.start();
        }
    }

    public void onBackPressed() {
        closeAfterTransition();
    }

    public void finish() {
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        ShareUtils shareUtils = new ShareUtils();
        DebugUtil.debug("NewImageViewer", "onActivityResult, requestCode=" + i + ",resultCode=" + i2 + ",data=" + intent);
        if (i2 >= 1) {
            if (i == 1) {
                ShareUtils.doShare(i2, this, null, this.b, this.i.share);
                if (i2 == 11) {
                    delete(this.b);
                } else if (i2 == 13) {
                    anonymous(this.b);
                } else if (i2 == 14) {
                    forbid(this.b);
                } else if (i2 == 15) {
                    ShareUtils.shareArticle2QiuyouCircle(this, this.b);
                }
            } else if (i == 2) {
                ShareUtils.Share(this, this.b.id, i2);
            } else if (i == 3) {
                ReportArticle.setReportArticle(this.b, i2);
                ReportArticle.reportHandler(true);
            } else if (i == 9) {
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已成功分享！", Integer.valueOf(0)).show();
            }
            super.onActivityResult(i, i2, intent);
        }
    }

    public void onMediaClick() {
        closeAfterTransition();
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        a(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    private boolean a(String str, String str2) {
        Vote vote = new Vote(this.q, str, str2, "1");
        DebugUtil.debug("NewImageViewer", "投票:" + vote.toString());
        return VoteManager.getInstance().vote(vote, str, str2);
    }

    public void delete(Article article) {
        if (article != null) {
            new Builder(this).setTitle("刪除此条糗事？").setItems(new String[]{"立即删除", "取消"}, new yc(this, article)).show();
        }
    }

    private void a(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.DELETE_CREATE, new Object[]{article.id}), new yd(this));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void anonymous(Article article) {
        if (article != null) {
            new Builder(this).setTitle("将此条糗事匿名？").setItems(new String[]{"匿名", "取消"}, new ye(this, article)).show();
        }
    }

    private void b(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.ANONYMOUS_CREATE, new Object[]{article.id}), new yf(this));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void forbid(Article article) {
        if (article != null) {
            new Builder(this).setTitle("删除该糗事并封禁该用户？").setItems(new String[]{"删除并封禁", "取消"}, new yg(this, article)).show();
        }
    }

    private void c(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.FORBID_CREATE, new Object[]{article.id}), new xy(this));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
