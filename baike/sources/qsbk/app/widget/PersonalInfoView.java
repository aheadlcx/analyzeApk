package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;

public class PersonalInfoView extends RelativeLayout {
    public static final int VIEW_NORMAL = 0;
    public static final int VIEW_SMALL = 1;
    private TextView A;
    private TextView B;
    private FrameLayout C;
    private UserInfo D;
    private int a;
    private Context b;
    private SimpleDraweeView c;
    private ImageView d;
    private ImageView e;
    private TextView f;
    private ImageView g;
    private TextView h;
    private LinearLayout i;
    private TextView j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private TextView n;
    private LinearLayout o;
    private LinearLayout p;
    private TextView q;
    private LinearLayout r;
    private TextView s;
    private LinearLayout t;
    private TextView u;
    private LinearLayout v;
    private TextView w;
    private LinearLayout x;
    private ProgressBar y;
    private TextView z;

    public interface OnReloadingInfoClicekListener {
        void onReloadingInfoClickListener();
    }

    public PersonalInfoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PersonalInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 1;
        this.b = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PersonalInfoView, i, 0);
        this.a = obtainStyledAttributes.getInt(0, -1);
        if (this.a == -1) {
            this.a = 1;
        }
        obtainStyledAttributes.recycle();
        b();
        a();
    }

    private void a() {
    }

    private void b() {
        View inflate;
        switch (this.a) {
            case 1:
                inflate = LayoutInflater.from(this.b).inflate(R.layout.layout_personal_info_small, this);
                break;
            default:
                inflate = LayoutInflater.from(this.b).inflate(R.layout.layout_personal_info_normal, this);
                break;
        }
        this.c = (SimpleDraweeView) inflate.findViewById(R.id.background);
        this.d = (ImageView) inflate.findViewById(R.id.fuzzy_bg);
        this.e = (ImageView) inflate.findViewById(R.id.avatar);
        this.f = (TextView) inflate.findViewById(R.id.name);
        this.g = (ImageView) inflate.findViewById(R.id.gender);
        this.h = (TextView) inflate.findViewById(R.id.age);
        this.i = (LinearLayout) inflate.findViewById(R.id.gender_age);
        this.j = (TextView) inflate.findViewById(R.id.astrology);
        this.k = (LinearLayout) inflate.findViewById(R.id.qiushi);
        this.l = (TextView) inflate.findViewById(R.id.qiushi_count);
        this.m = (TextView) inflate.findViewById(R.id.smile_count);
        this.n = (TextView) inflate.findViewById(R.id.haunt);
        this.o = (LinearLayout) inflate.findViewById(R.id.haunt_layout);
        this.p = (LinearLayout) inflate.findViewById(R.id.ll_qiubai_age);
        this.q = (TextView) inflate.findViewById(R.id.qiubai_age);
        this.r = (LinearLayout) inflate.findViewById(R.id.ll_mobile_brand);
        this.s = (TextView) inflate.findViewById(R.id.mobile_brand);
        this.t = (LinearLayout) inflate.findViewById(R.id.ll_hometown);
        this.u = (TextView) inflate.findViewById(R.id.hometown);
        this.v = (LinearLayout) inflate.findViewById(R.id.ll_job);
        this.w = (TextView) inflate.findViewById(R.id.job);
        this.x = (LinearLayout) findViewById(R.id.loading);
        this.y = (ProgressBar) findViewById(R.id.bigcover_loading);
        this.z = (TextView) findViewById(R.id.bigcover_load_failed);
        if (this.a == 0) {
            this.B = (TextView) findViewById(R.id.info_load_failed);
            this.A = (TextView) findViewById(R.id.bigcover_set);
            this.C = (FrameLayout) findViewById(R.id.bigcover_set_layout);
        }
    }

    public void setBasicView(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            this.e.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            String absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(str3, str);
            FrescoImageloader.displayAvatar(this.e, absoluteUrlOfMediumUserIcon);
            if (!(absoluteUrlOfMediumUserIcon == null || absoluteUrlOfMediumUserIcon.contains("nopic"))) {
                this.e.setOnClickListener(new cv(this, absoluteUrlOfMediumUserIcon));
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            this.f.setText(str2);
        }
    }

    public void setView(UserInfo userInfo, int i) {
        String absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(userInfo.userIcon, userInfo.userId);
        this.D = userInfo;
        a(i);
        if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
            this.e.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(this.e, absoluteUrlOfMediumUserIcon);
        }
        this.f.setText(userInfo.userName);
        if (!(absoluteUrlOfMediumUserIcon == null || absoluteUrlOfMediumUserIcon.contains("nopic"))) {
            this.e.setOnClickListener(new cw(this, absoluteUrlOfMediumUserIcon));
        }
        if (userInfo.age == 0) {
            this.i.setVisibility(4);
            this.j.setVisibility(4);
        } else {
            this.i.setVisibility(0);
            this.j.setVisibility(0);
            if ("F".equalsIgnoreCase(userInfo.gender)) {
                this.g.setImageResource(R.drawable.pinfo_female);
                this.i.setBackgroundResource(R.drawable.pinfo_female_bg);
                this.j.setBackgroundResource(R.drawable.pinfo_female_bg);
            } else {
                this.g.setImageResource(R.drawable.pinfo_male);
                this.i.setBackgroundResource(R.drawable.pinfo_man_bg);
                this.j.setBackgroundResource(R.drawable.pinfo_man_bg);
            }
            this.h.setText(userInfo.age + "");
            this.j.setText(userInfo.astrology);
            this.j.setPadding(this.b.getResources().getDimensionPixelSize(R.dimen.per_info_astrology_textview_padding), this.j.getPaddingTop(), this.b.getResources().getDimensionPixelSize(R.dimen.per_info_astrology_textview_padding), this.j.getPaddingBottom());
        }
        if (userInfo.qiubaiAge == 0) {
            this.p.setVisibility(8);
        } else {
            this.p.setVisibility(0);
            if (userInfo.qiubaiAge >= 365) {
                this.q.setText(((userInfo.qiubaiAge % 365 > 200 ? 1 : 0) + (userInfo.qiubaiAge / 365)) + "年");
            } else {
                this.q.setText((userInfo.qiubaiAge / 30 > 0 ? userInfo.qiubaiAge / 30 : 1) + "个月");
            }
        }
        if (TextUtils.isEmpty(userInfo.mobileBrand)) {
            this.r.setVisibility(8);
        } else {
            this.r.setVisibility(0);
            this.s.setText(userInfo.mobileBrand);
        }
        if (TextUtils.isEmpty(userInfo.hometown) || TextUtils.isEmpty(userInfo.job)) {
            this.t.setVisibility(8);
            this.v.setVisibility(8);
        } else {
            this.t.setVisibility(0);
            this.v.setVisibility(0);
            this.u.setText(userInfo.hometown.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
            this.w.setText(userInfo.job);
        }
        if (TextUtils.isEmpty(userInfo.haunt)) {
            this.o.setVisibility(8);
        } else {
            absoluteUrlOfMediumUserIcon = userInfo.haunt.trim();
            if (absoluteUrlOfMediumUserIcon.length() > 9) {
                String[] split = absoluteUrlOfMediumUserIcon.split("·", 2);
                this.n.setText((split.length > 1 ? split[1] : split[0]).replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
            } else {
                this.n.setText(absoluteUrlOfMediumUserIcon.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
            }
            this.o.setVisibility(0);
        }
        if (userInfo.qsCount == 0) {
            this.k.setVisibility(8);
            return;
        }
        this.k.setVisibility(0);
        if (userInfo.qsCount > 1000) {
            this.l.setText((userInfo.qsCount / 1000) + "K");
        } else {
            this.l.setText(userInfo.qsCount + "");
        }
        if (userInfo.smileCount > 1000) {
            this.m.setText((userInfo.smileCount / 1000) + "K");
        } else {
            this.m.setText(userInfo.smileCount + "");
        }
        this.k.setOnClickListener(new cx(this, userInfo));
    }

    public void setBigCoverListener(OnClickListener onClickListener) {
        if (onClickListener != null && this.C != null) {
            this.C.setOnClickListener(onClickListener);
        }
    }

    public void setLoadingView() {
        if (this.x != null && this.y != null) {
            this.x.setVisibility(0);
            this.y.setVisibility(0);
        }
    }

    private void c() {
        if (this.y != null && this.x != null) {
            this.y.setVisibility(8);
            this.x.setVisibility(8);
        }
    }

    public void onInfoLoadingFailed(OnReloadingInfoClicekListener onReloadingInfoClicekListener) {
        this.B.setVisibility(0);
        this.B.setOnClickListener(new cy(this, onReloadingInfoClicekListener));
    }

    public void onBigCoverLoadingFailed() {
        if (this.x != null) {
            this.x.setVisibility(8);
            this.z.setVisibility(0);
            this.z.setOnClickListener(new cz(this));
        }
    }

    private void a(int i) {
        LogUtil.d("==> load big cover:" + this.D.userName);
        if (this.D == null) {
            LogUtil.d("<== user info null");
        } else if (i == 1) {
            LogUtil.d("loading big cover:" + this.D.bigCover);
            if (TextUtils.isEmpty(this.D.bigCover)) {
                c();
                this.c.setImageResource(R.drawable.pinfo_big_bg);
                if (this.A != null) {
                    String str = this.D.userId;
                    QsbkApp.getInstance();
                    if (str.equalsIgnoreCase(QsbkApp.currentUser.userId)) {
                        this.A.setVisibility(0);
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.A != null) {
                this.A.setVisibility(4);
            }
            setLoadingView();
            if (FrescoImageloader.isInMemoryCache(this.D.bigCover)) {
                this.d.setVisibility(8);
                c();
                FrescoImageloader.displayImage(this.c, this.D.bigCover, R.drawable.pinfo_big_bg);
                return;
            }
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(this.D.bigCover.replace("w/500/q/80", "w/8/q/80")), getContext().getApplicationContext()).subscribe(new da(this), UiThreadImmediateExecutorService.getInstance());
            this.c.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(this.D.bigCover).setOldController(this.c.getController())).setControllerListener(new db(this))).build());
            FrescoImageloader.displayImage(this.c, this.D.bigCover, R.drawable.pinfo_big_bg);
        } else if (i == 0) {
            this.c.setImageBitmap(null);
            setBackgroundColor(this.b.getResources().getColor(R.color.transparent));
        } else if (i != 2) {
        } else {
            if (TextUtils.isEmpty(this.D.bigCover)) {
                c();
                this.c.setImageResource(R.drawable.pinfo_big_bg);
                return;
            }
            setLoadingView();
            if (FrescoImageloader.isInMemoryCache(this.D.bigCover)) {
                c();
                this.d.setVisibility(8);
                FrescoImageloader.displayImage(this.c, this.D.bigCover, R.drawable.pinfo_big_bg);
                return;
            }
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(this.D.bigCover.replace("w/500/q/80", "w/8/q/80")), getContext().getApplicationContext()).subscribe(new dd(this), UiThreadImmediateExecutorService.getInstance());
        }
    }
}
