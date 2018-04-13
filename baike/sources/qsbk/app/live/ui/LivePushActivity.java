package qsbk.app.live.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.GridLayout;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.soundcloud.android.crop.Crop;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.core.adapter.ShareAdapter;
import qsbk.app.core.adapter.ShareAdapter.OnShareItemClickListener;
import qsbk.app.core.map.ILocationCallback;
import qsbk.app.core.map.ILocationManager;
import qsbk.app.core.map.Location;
import qsbk.app.core.map.LocationCache;
import qsbk.app.core.map.NearbyEngine;
import qsbk.app.core.model.RedEnvelopesConfig;
import qsbk.app.core.model.Share;
import qsbk.app.core.model.ShareItem;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.upload.NormalUpload;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.utils.KeyBoardUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.PictureGetHelper;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.PrefrenceKeys;
import qsbk.app.core.utils.ScreenShotUtils;
import qsbk.app.core.utils.TextLengthFilter;
import qsbk.app.core.utils.TimeDelta;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.CommonEditText;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.core.widget.UserPicSelectDialog;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.AdminListDialog;
import qsbk.app.live.widget.LivePushEndDialog;
import qsbk.app.ye.videotools.camera.CameraHelper;
import qsbk.app.ye.videotools.camera.CameraLoader;
import qsbk.app.ye.videotools.camera.CameraRender;
import qsbk.app.ye.videotools.camera.CameraRender.SurfaceListener;
import qsbk.app.ye.videotools.filter.VideoFilter;
import qsbk.app.ye.videotools.live.MediaPublisher;
import qsbk.app.ye.videotools.live.StatisticsInfo;

public class LivePushActivity extends LiveBaseActivity implements OnClickListener, OnTouchListener, OnShareItemClickListener, ILocationCallback, SurfaceListener {
    public static final long INNER = 120000;
    public static final String TAG = LivePushActivity.class.getSimpleName();
    private CameraLoader bA;
    private MediaPublisher bB;
    private List<View> bC = new ArrayList();
    private GridView bD;
    private ShareAdapter bE;
    private RelativeLayout bF;
    private GridLayout bG;
    private ImageButton bH;
    private ImageView bI;
    private ImageView bJ;
    private ImageView bK;
    private SimpleDraweeView bL;
    private EditText bM;
    private TextView bN;
    private LinearLayout bO;
    private LinearLayout bP;
    private TextView bQ;
    private ImageView bR;
    private LinearLayout bS;
    private ImageView bT;
    private ImageView bU;
    private TextView bV;
    private TextView bW;
    private TextView bX;
    private TextView bY;
    private TextView bZ;
    protected ArrayList<ShareItem> bt;
    protected ShareItem bu;
    protected Share bv;
    protected String bw = null;
    protected PictureGetHelper bx;
    private CameraRender by;
    private CameraHelper bz;
    private int cA = 0;
    private float cB = 1.5f;
    private boolean cC = false;
    private TextView cD;
    private TextView cE;
    private boolean cF = false;
    private int cG;
    private UserPicSelectDialog cH;
    private boolean cI = false;
    private ImageView cJ;
    private FrameLayout cK;
    private Runnable cL = null;
    private boolean cM = false;
    private Runnable cN = new eo(this);
    private TextView ca;
    private TextView cb;
    private LinearLayout cc;
    private LinearLayout cd;
    private ImageView ce;
    private TextView cf;
    private LinearLayout cg;
    private String ch;
    private ImageView ci;
    private ImageView cj;
    private LinearLayout ck;
    private ImageView cl;
    private LinearLayout cm;
    private boolean cn = true;
    private int co;
    private boolean cp;
    private int cq = 1;
    private long cr = 0;
    private AdminListDialog cs;
    private ILocationManager ct;
    private Location cu;
    private int cv;
    private LivePushEndDialog cw;
    private TextView cx;
    private boolean cy = false;
    private int cz = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.bx = new PictureGetHelper(this, bundle);
    }

    protected int getLayoutId() {
        return R.layout.live_push_activity;
    }

    protected void initView() {
        super.initView();
        this.g.setSurfaceTextureListener(this.by);
        this.bC.add(findViewById(R.id.tips_share_to_friend_circle));
        this.bC.add(findViewById(R.id.tips_share_to_wechat));
        this.bC.add(findViewById(R.id.tips_share_to_qq));
        this.bC.add(findViewById(R.id.tips_share_to_qzone));
        this.bC.add(findViewById(R.id.tips_share_to_sina_weibo));
        this.bD = (GridView) findViewById(R.id.live_grid);
        this.bF = (RelativeLayout) findViewById(R.id.layout_start_live);
        this.bG = (GridLayout) findViewById(R.id.gl_more_actions);
        this.bG.setClickable(true);
        this.bH = (ImageButton) findViewById(R.id.btn_close_live);
        this.bH.setOnClickListener(this);
        this.bI = (ImageView) findViewById(R.id.iv_mute);
        this.bI.setOnClickListener(this);
        this.bJ = (ImageView) findViewById(R.id.iv_beauty);
        this.bJ.setOnClickListener(this);
        this.bK = (ImageView) findViewById(R.id.iv_switch_camera);
        this.bK.setOnClickListener(this);
        this.bL = (SimpleDraweeView) findViewById(R.id.iv_cover);
        this.bL.setOnClickListener(this);
        this.bM = (EditText) findViewById(R.id.et_title);
        this.bN = (TextView) findViewById(R.id.tv_location);
        this.bO = (LinearLayout) findViewById(R.id.ll_location);
        this.bO.setOnClickListener(this);
        this.bP = (LinearLayout) findViewById(R.id.ll_switch);
        this.bP.setOnClickListener(this);
        this.cg = (LinearLayout) findViewById(R.id.ll_start);
        this.cg.setOnClickListener(this);
        this.ci = (ImageView) findViewById(R.id.btn_admin);
        this.ci.setOnClickListener(this);
        this.ci.setClickable(false);
        this.cj = (ImageView) findViewById(R.id.btn_red_envelopes);
        this.cj.setOnClickListener(this);
        this.bT = (ImageView) findViewById(R.id.btn_fontSize);
        this.bT.setOnClickListener(this);
        this.bU = (ImageView) findViewById(R.id.btn_screeshot);
        this.bU.setOnClickListener(this);
        this.ck = (LinearLayout) findViewById(R.id.ll_more_actions);
        this.cl = (ImageView) findViewById(R.id.iv_start_beauty);
        this.cl.setSelected(true);
        this.cm = (LinearLayout) findViewById(R.id.ll_start_beauty);
        this.cm.setOnClickListener(this);
        this.cb = (TextView) $(R.id.live_game_off);
        this.cb.setOnClickListener(this);
        this.bW = (TextView) $(R.id.hlnb);
        this.bW.setOnClickListener(this);
        this.bV = (TextView) $(R.id.ypdx);
        this.bV.setOnClickListener(this);
        this.bX = (TextView) $(R.id.catanddog);
        this.bX.setOnClickListener(this);
        this.bY = (TextView) $(R.id.rolltable);
        this.bZ = (TextView) $(R.id.guess);
        this.bZ.setOnClickListener(this);
        this.bY.setOnClickListener(this);
        this.ca = (TextView) $(R.id.fanfanle);
        this.ca.setOnClickListener(this);
        this.cD = (TextView) $(R.id.tv_mute);
        this.cE = (TextView) $(R.id.tv_fontSize);
        this.bQ = (TextView) findViewById(R.id.tv_game);
        this.bR = (ImageView) findViewById(R.id.iv_game);
        this.bS = (LinearLayout) findViewById(R.id.ll_game);
        this.bS.setOnClickListener(this);
        this.cc = (LinearLayout) $(R.id.ll_game_open);
        this.bR.setAlpha(0.5f);
        ((CommonEditText) $(R.id.et_title)).setOnTouchListener(new ee(this));
        AppUtils.addSupportForTransparentStatusBarMargin(this.ck);
        this.cd = (LinearLayout) $(R.id.ll_pixel);
        this.ce = (ImageView) $(R.id.iv_start_pixel);
        this.cf = (TextView) $(R.id.tv_pixel);
        this.cd.setOnClickListener(this);
        this.cJ = (ImageView) $(R.id.iv_mask_hint);
        this.cK = (FrameLayout) $(R.id.fl_mask);
        this.cK.setOnClickListener(new eq(this));
        if (PreferenceUtils.instance().getBoolean(PrefrenceKeys.KEY_MASK_HINT, true)) {
            this.cK.setVisibility(0);
            this.cJ.post(new ex(this));
            PreferenceUtils.instance().putBoolean(PrefrenceKeys.KEY_MASK_HINT, false);
            return;
        }
        this.cK.setVisibility(8);
    }

    protected void initData() {
        super.initData();
        this.Y.setPadding(this.Y.getPaddingTop(), this.Y.getPaddingTop(), this.Y.getPaddingRight(), this.Y.getPaddingBottom());
        this.by = new CameraRender(this);
        this.g.setSurfaceTextureListener(this.by);
        this.g.setOnTouchListener(this);
        this.by.setTextureView(this.g, true, 320, 568);
        this.bz = new CameraHelper(this);
        int hasFrontCamera = this.bz.hasFrontCamera();
        if (hasFrontCamera == -1) {
            hasFrontCamera = 0;
        }
        this.bA = new CameraLoader(hasFrontCamera, this.bz, this.by, getMainLooper());
        this.bA.setLiveFlag();
        this.bM.setFilters(new InputFilter[]{new TextLengthFilter(64, R.string.comment_too_long)});
        this.bJ.setSelected(this.cn);
        if (this.ax != null) {
            ak();
            a(this.o, this.ax.headurl);
            AppUtils.getInstance().getImageProvider().loadAvatar(this.bL, this.ax.headurl, WindowUtils.dp2Px(10));
            this.p.setText(this.ax.name);
            this.l.setVisibility(8);
            if (this.ax.nick_id != 0) {
                this.aC.setText(getString(R.string.user_nick_id, new Object[]{String.valueOf(this.ax.nick_id)}));
            } else {
                this.aC.setVisibility(8);
            }
            this.aC.setOnLongClickListener(new ey(this));
            an();
            this.bE = new ShareAdapter(this, this.bt, false, this);
            this.bD.setAdapter(this.bE);
            this.bN.setSelected(true);
            this.cu = LocationCache.getInstance().getLocation(INNER);
            aT();
            ax();
        }
    }

    private void ax() {
        NetRequest.getInstance().get(UrlConstants.LIVE_COVER_DATA, new ez(this));
    }

    private void ay() {
        if (this.ct == null) {
            this.ct = NearbyEngine.instance().getLastLocationManager();
        }
        this.ct.getLocation(this);
    }

    protected void an() {
        boolean z;
        boolean z2 = true;
        this.bt = new ArrayList();
        int i = PreferenceUtils.instance().getInt(PrefrenceKeys.LIVE_PUSH_DEFAULT_SHARE_PLATFORM, -1);
        this.bt.add(new ShareItem(R.drawable.live_start_share_item_wechat_timeline, getString(R.string.share_friend_circle), 2, i == 2));
        ArrayList arrayList = this.bt;
        int i2 = R.drawable.live_start_share_item_wechat;
        String string = getString(R.string.share_wechat_friend);
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        arrayList.add(new ShareItem(i2, string, 1, z));
        arrayList = this.bt;
        i2 = R.drawable.live_start_share_item_qq;
        string = getString(R.string.share_qq_friend);
        if (i == 4) {
            z = true;
        } else {
            z = false;
        }
        arrayList.add(new ShareItem(i2, string, 4, z));
        arrayList = this.bt;
        i2 = R.drawable.live_start_share_item_qzone;
        string = getString(R.string.share_qq_zone);
        if (i == 5) {
            z = true;
        } else {
            z = false;
        }
        arrayList.add(new ShareItem(i2, string, 5, z));
        ArrayList arrayList2 = this.bt;
        int i3 = R.drawable.live_start_share_item_sina;
        String string2 = getString(R.string.share_sina_weibo);
        if (i != 3) {
            z2 = false;
        }
        arrayList2.add(new ShareItem(i3, string2, 3, z2));
        Iterator it = this.bt.iterator();
        while (it.hasNext()) {
            ShareItem shareItem = (ShareItem) it.next();
            if (shareItem.selected) {
                this.bu = shareItem;
                return;
            }
        }
    }

    public void onShareItemClicked(int i) {
        for (int i2 = 0; i2 < this.bt.size(); i2++) {
            View view = (View) this.bC.get(i2);
            view.setVisibility(8);
            ShareItem shareItem = (ShareItem) this.bt.get(i2);
            if (i2 == i) {
                boolean z;
                if (shareItem.selected) {
                    z = false;
                } else {
                    z = true;
                }
                shareItem.selected = z;
                if (shareItem.selected) {
                    view.setVisibility(0);
                    this.cL = new fa(this, view);
                    this.mHandler.removeCallbacks(this.cL);
                    postDelayed(this.cL, 3000);
                }
            } else {
                shareItem.selected = false;
            }
        }
        this.bE.notifyDataSetChanged();
        ShareItem shareItem2 = (ShareItem) this.bt.get(i);
        if (shareItem2.selected) {
            this.bu = shareItem2;
            PreferenceUtils.instance().putInt(PrefrenceKeys.LIVE_PUSH_DEFAULT_SHARE_PLATFORM, shareItem2.resultCode);
            return;
        }
        this.bu = null;
        PreferenceUtils.instance().putInt(PrefrenceKeys.LIVE_PUSH_DEFAULT_SHARE_PLATFORM, -1);
    }

    public void onSurfaceTextureAvailable() {
        LogUtils.d(TAG, "live onSurfaceTextureAvailable");
        if (this.bA != null) {
            try {
                this.bA.setUpCamera();
            } catch (RuntimeException e) {
                e.printStackTrace();
                postDelayed(new fb(this));
            }
        }
        aG();
        postDelayed(new fc(this));
    }

    public void onSurfaceTextureDestroyed() {
        LogUtils.d(TAG, "live onSurfaceTextureDestroyed");
        if (this.bA != null) {
            this.bA.releaseCamera();
        }
        aL();
        ad();
    }

    protected void a(int i, boolean z) {
        if (this.bF.getVisibility() != 0) {
            super.a(i, z);
        } else if (DeviceUtils.getSystemSDKInt() > 18) {
            if (this.co <= 0) {
                this.co = WindowUtils.dp2Px(30);
            }
            LayoutParams layoutParams = (LayoutParams) this.bF.getLayoutParams();
            layoutParams.bottomMargin = (i - this.be.getNavigationHideHeight()) - (!z ? this.co : 0);
            this.bF.setLayoutParams(layoutParams);
        }
    }

    protected void ae() {
        g(false);
    }

    protected void B() {
        u();
    }

    protected void onResume() {
        super.onResume();
        if (!AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            f(false);
        } else if (this.az) {
            if (this.bu != null) {
                this.bu = null;
            }
            J();
            T();
        } else {
            aM();
        }
    }

    protected void onPause() {
        super.onPause();
        if (!AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            f(false);
        }
    }

    protected void onStop() {
        super.onStop();
        aL();
    }

    public void onClick(View view) {
        if (!AppUtils.isFastDoubleClick()) {
            super.onClick(view);
            if (view.getId() == R.id.iv_mute) {
                aQ();
            } else if (view.getId() == R.id.iv_beauty || view.getId() == R.id.ll_start_beauty) {
                aF();
            } else if (view.getId() == R.id.iv_switch_camera || view.getId() == R.id.ll_switch) {
                aE();
            } else if (view.getId() == R.id.ll_start) {
                KeyBoardUtils.hideSoftInput(this);
                if (PermissionChecker.checkSelfPermission(getActivity(), "android.permission.CAMERA") != 0) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.CAMERA"}, 1001);
                } else if (aC() || !this.isOnResume || isFinishing()) {
                    this.az = true;
                    this.cc.setVisibility(8);
                    J();
                    if (this.bw != null) {
                        submitAvatar(this.bw);
                    }
                } else {
                    aD();
                }
            } else if (view.getId() == R.id.btn_close_live) {
                a(Boolean.valueOf(true));
            } else if (view.getId() == R.id.btn_admin) {
                aB();
            } else if (view.getId() == R.id.btn_red_envelopes) {
                G();
            } else if (view.getId() == R.id.ll_location) {
                aA();
            } else if (view.getId() == R.id.iv_cover) {
                aU();
            } else if (view.getId() == R.id.btn_confirm) {
                aq();
            } else if (view.getId() == R.id.btn_more) {
                ao();
            } else if (view.getId() == R.id.btn_fontSize) {
                aO();
            } else if (view.getId() == R.id.btn_screeshot) {
                aP();
            } else if (view.getId() == R.id.ll_game) {
                aS();
            } else if (view.getId() == R.id.hlnb) {
                c(1);
            } else if (view.getId() == R.id.ypdx) {
                c(2);
            } else if (view.getId() == R.id.catanddog) {
                c(3);
            } else if (view.getId() == R.id.fanfanle) {
                c(4);
            } else if (view.getId() == R.id.rolltable) {
                c(5);
            } else if (view.getId() == R.id.guess) {
                c(6);
            } else if (view.getId() == R.id.live_game_off) {
                c(0);
            } else if (view.getId() == R.id.ll_pixel) {
                az();
            }
        }
    }

    private void az() {
        this.cI = !this.cI;
        if (this.cI) {
            this.cf.setText(R.string.live_hd);
            this.ce.setImageResource(R.drawable.ic_hd);
            ToastUtil.Long(R.string.live_hd_hint);
            return;
        }
        this.cf.setText(R.string.live_sd);
        this.ce.setImageResource(R.drawable.ic_sd);
        ToastUtil.Long(R.string.live_sd_hint);
    }

    private void aA() {
        this.bN.setSelected(!this.bN.isSelected());
        if (this.bN.isSelected()) {
            boolean aN = aN();
            if (aN) {
                h(aN);
                return;
            }
            Intent intent;
            try {
                intent = new Intent();
                intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
                intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                startActivity(intent);
                return;
            } catch (Exception e) {
                intent = new Intent();
                intent.setAction("android.settings.SETTINGS");
                intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                startActivity(intent);
                return;
            }
        }
        this.bN.setText(R.string.live_location_off);
    }

    private void aB() {
        if (!isFinishing() && this.isOnResume) {
            aI();
            if (this.cs == null) {
                this.cs = new AdminListDialog(getActivity(), this.aE);
                this.cs.setCanceledOnTouchOutside(true);
            } else {
                this.cs.loadData();
            }
            WindowUtils.setNonTransparentNavigationBar(this);
            this.cs.setOnDismissListener(new fd(this));
            this.cs.show();
        }
    }

    private boolean aC() {
        return this.bA != null && this.bA.isCameraEnable();
    }

    private void e(boolean z) {
        if (PermissionChecker.checkSelfPermission(getActivity(), "android.permission.RECORD_AUDIO") != 0) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.RECORD_AUDIO"}, 1001);
            return;
        }
        LogUtils.d(TAG, "live push without recorder, because Exception: " + z);
        statEvent("live_push_without_recorder", "isException: " + z, "", "", "");
        if (!this.cM) {
            this.cM = true;
            Builder efVar = new ef(this, R.style.SimpleDialog_Fullscreen);
            efVar.message(getString(R.string.video_record_recorder_permission_hint)).positiveAction(getString(R.string.video_record_setting)).negativeAction(getString(R.string.video_record_cancel));
            AppUtils.showDialogFragment(this, efVar);
        }
    }

    private void aD() {
        Builder egVar = new eg(this, R.style.SimpleDialog);
        egVar.message(getString(R.string.video_record_camera_permission_hint)).positiveAction(getString(R.string.video_record_setting)).negativeAction(getString(R.string.video_record_cancel));
        AppUtils.showDialogFragment(this, egVar);
    }

    private void aE() {
        if (A()) {
            if (au() != null) {
                au().switchCamera();
            }
        } else if (this.bA != null) {
            this.bA.switchCamera();
        }
    }

    private void aF() {
        float f = 1.0f;
        boolean z = true;
        if (!A()) {
            boolean z2;
            ImageView imageView = this.bJ;
            if (this.cn) {
                z2 = false;
            } else {
                z2 = true;
            }
            imageView.setSelected(z2);
            imageView = this.cl;
            if (this.cn) {
                z2 = false;
            } else {
                z2 = true;
            }
            imageView.setSelected(z2);
            if (this.cn) {
                z = false;
            }
            this.cn = z;
            ImageView imageView2 = this.cl;
            if (!this.cn) {
                f = 0.5f;
            }
            imageView2.setAlpha(f);
            aG();
        } else if (at() != null) {
            float f2;
            if (this.cn) {
                z = false;
            }
            this.cn = z;
            if (this.cn) {
                at().enablePreProcessor();
            } else {
                at().disablePreProcessor();
            }
            this.bJ.setSelected(this.cn);
            ImageView imageView3 = this.cl;
            if (this.cn) {
                f2 = 1.0f;
            } else {
                f2 = 0.5f;
            }
            imageView3.setAlpha(f2);
        }
    }

    private void aG() {
        if (this.by != null) {
            this.by.setFilter(new VideoFilter(), this.cn);
        }
    }

    protected void n() {
        if (this.az) {
            a(this.ax);
        }
    }

    protected void a(Boolean bool) {
        if (!u()) {
            if (!(!this.cp && this.bB == null && TextUtils.isEmpty(this.aq)) && this.az) {
                Builder ehVar = new eh(this, R.style.SimpleDialog);
                ehVar.message(getString(R.string.live_close_hint)).positiveAction(getString(R.string.setting_confirm)).negativeAction(getString(R.string.setting_cancel));
                AppUtils.showDialogFragment(this, ehVar);
                return;
            }
            f(false);
        }
    }

    protected boolean u() {
        if (this.bG.getVisibility() != 0) {
            return super.u();
        }
        aI();
        return true;
    }

    private void aH() {
        int i = 0;
        this.bG.setVisibility(0);
        this.z.setVisibility(8);
        this.O.setAlpha(0.3f);
        WindowUtils.setNonTransparentNavigationBar(this);
        RedEnvelopesConfig redEnvelopesConfig = ConfigInfoUtil.instance().getRedEnvelopesConfig();
        View findViewById = findViewById(R.id.ll_red_envelopes);
        if (redEnvelopesConfig == null || !redEnvelopesConfig.enable) {
            i = 8;
        }
        findViewById.setVisibility(i);
    }

    private void aI() {
        this.bG.setVisibility(8);
        this.z.setVisibility(0);
        this.O.setAlpha(1.0f);
        setTransparentNavigationBarIfNeed();
    }

    protected void I() {
        doCloseLive(true, false);
    }

    private void f(boolean z) {
        doCloseLive(z, true);
    }

    public void doCloseLive(boolean z, boolean z2) {
        if (z2) {
            W();
        }
        this.az = false;
        AppUtils.getInstance().setLiving(false);
        aK();
        if (z) {
            c();
            return;
        }
        V();
        finish();
    }

    protected void K() {
    }

    protected void L() {
    }

    protected void W() {
        sendLiveMessageAndRefreshUI(LiveMessage.createCloseMessage(this.ax.getOriginId()));
    }

    protected void ao() {
        if (this.bG.getVisibility() == 8) {
            aH();
        }
    }

    public User getLiveUser() {
        return this.ax;
    }

    protected void J() {
        if (A()) {
            i();
        } else if (this.ao && !this.ap) {
            aJ();
            d();
        } else if (!this.az || !this.isOnResume || isFinishing()) {
        } else {
            if (this.bA != null && this.bA.isCameraEnable() && this.bB != null && !this.cp) {
                return;
            }
            if (!TextUtils.isEmpty(this.ch) && !TextUtils.isEmpty(this.aA)) {
                aJ();
                T();
            } else if (this.cg.isEnabled()) {
                this.cg.setEnabled(false);
                h();
                NetRequest.getInstance().post(UrlConstants.NEW_LIVE_PUSH, new ei(this));
            }
        }
    }

    protected void ap() {
        if (this.bu != null) {
            this.d.pic_url = this.ax.headurl;
            AppUtils.getInstance().getUserInfoProvider().toShare(getActivity(), this.d, this.bu.resultCode);
        }
    }

    protected void M() {
        if (!isFinishing() && !TextUtils.isEmpty(this.aA)) {
            if (!this.ao || this.ap) {
                this.aL = new TimeDelta();
                NetRequest.getInstance().post(UrlConstants.LIVE_ROOM_CREATE, new ek(this), "live_room_server_ip", this.aI < 5);
            }
        }
    }

    private void g(String str) {
        if (!TextUtils.isEmpty(str)) {
            int[] bitrateLevel = ConfigInfoUtil.instance().getBitrateLevel(str);
            if (bitrateLevel.length > 1) {
                this.bB.setBitrateLevels(bitrateLevel);
                this.bB.setDimension(ConfigInfoUtil.instance().getBitrateWidth(str), ConfigInfoUtil.instance().getBitrateHeight(str));
            }
        }
    }

    private void aJ() {
        LogUtils.d(TAG, "live start push " + this.az);
        if (this.ao) {
            this.ar = new User();
            this.aq = "remix_wawa_" + this.ax.getOriginId() + "_" + this.ax.getOrigin();
            if (!this.ap) {
                this.aq = "remix_wawa_119743257349868601_2";
                if (UrlConstants.getLiveDomain().contains("test")) {
                    this.aq = "remix_wawa_119375132351584299_2";
                }
            }
            this.bA.releaseCamera();
            doMicConnect(this.ch, this.ap);
            T();
            i();
            return;
        }
        g(false);
        if (this.cr == 0) {
            this.cr = System.currentTimeMillis();
        }
        this.bB = MediaPublisher.create();
        this.bB.setOnConnectListener(new el(this));
        this.bB.setOnInfoListener(new em(this));
        this.bB.setOnErrorListener(new en(this));
        this.bB.setDimension(320, 568);
        this.bB.setURL(this.ch);
        try {
            this.bB.prepare();
        } catch (IllegalArgumentException e) {
            e(true);
            e.printStackTrace();
        }
    }

    protected void H() {
        LogUtils.d(TAG, "live repush...");
        g(false);
        this.cy = false;
        a(this.cN);
    }

    protected void d() {
        super.d();
        this.l.setVisibility(0);
        this.bF.setVisibility(8);
        this.ck.setVisibility(8);
        int i = PreferenceUtils.instance().getInt("liveTimes", 0);
        if (i > 2 || Z()) {
            aI();
            return;
        }
        aH();
        PreferenceUtils.instance().putInt("liveTimes", i + 1);
    }

    protected void b() {
        super.b();
        this.D.setVisibility(8);
        this.I.setVisibility(8);
        this.ci.setVisibility(0);
        this.cj.setVisibility(0);
        this.G.setVisibility(0);
        this.bU.setVisibility(0);
    }

    protected void Y() {
        super.Y();
        if (this.bG.getVisibility() == 0) {
            this.z.setVisibility(8);
        }
    }

    protected void c() {
        super.c();
        this.bG.setVisibility(8);
        this.bF.setVisibility(8);
        if (this.cx != null) {
            this.cx.setVisibility(8);
        }
        if (isFinishing() || !this.isOnResume) {
            finish();
        } else if (this.cw == null || !this.cw.isShowing()) {
            this.cw = new LivePushEndDialog(this, this, this.aE, this.aA);
            this.cw.show();
            ai();
        }
    }

    private void aK() {
        g(true);
    }

    private void g(boolean z) {
        if (this.bB != null) {
            if (z) {
                StatisticsInfo statisticsInfo = new StatisticsInfo();
                this.bB.getBitrateInfo(statisticsInfo);
                LogUtils.d(TAG, "audio send data size:" + statisticsInfo.mAudioSendDataSize + " video send data size:" + statisticsInfo.mVideoSendDataSize);
                statEvent("push_stream", "stop", Long.toString(this.ax.getOriginId()), "audio send:" + statisticsInfo.mAudioSendDataSize + " video send:" + statisticsInfo.mVideoSendDataSize, "");
            }
            this.by.setSink(null);
            this.bB.stop();
            this.bB = null;
        }
        if (z && !TextUtils.isEmpty(this.aA)) {
            NetRequest.getInstance().post(UrlConstants.DELETE_LIVE, new ep(this), "delete_live", true);
        }
    }

    protected void ag() {
        super.ag();
        J();
    }

    protected void af() {
        super.af();
        g(false);
    }

    protected void onDestroy() {
        aK();
        if (this.cL != null) {
            this.mHandler.removeCallbacks(this.cL);
        }
        this.mHandler.removeCallbacks(this.cN);
        if (!(this.cy || ((this.cz <= 0 && this.cA <= 0) || this.ch == null || this.ax == null))) {
            statEvent("live_cannot_push", Long.toString(this.ax.getOriginId()), this.ch, Integer.toString(this.cz), Integer.toString(this.cA));
        }
        super.onDestroy();
    }

    protected void c(String str) {
        if (!(this.ax == null || this.aL == null)) {
            statEventDuration("chat_server_ip_create", Long.toString(this.ax.getOriginId()), this.aL.getDelta(), Integer.toString(this.aI), str, this.aF);
        }
        this.aL = null;
    }

    private void aL() {
        if (!(this.bB == null || this.cp)) {
            this.aD.sendMessage(LiveMessage.createAnchorSuspendMessage(this.ax.getOriginId()));
            this.cp = true;
        }
        g(false);
    }

    public void onCallOffHook() {
        aL();
    }

    public void onCallIdle() {
        J();
        T();
    }

    public void onLocation(int i, double d, double d2, String str, String str2, String str3) {
        LogUtils.d("location type:" + i);
        LogUtils.d("location latitude:" + d);
        LogUtils.d("location longtitude:" + d2);
        LogUtils.d("location district:" + str2);
        LogUtils.d("location city:" + str3);
        if (i == 61 || i == 65 || i == 66 || i == 68 || i == 161 || i == 0) {
            if (this.cu == null) {
                this.cu = new Location();
            }
            this.cu.latitude = d;
            this.cu.longitude = d2;
            this.cu.province = str;
            this.cu.city = str3;
            this.cu.code = i;
            this.cu.district = str2;
            this.ct.remove(this);
            LocationCache.getInstance().setLocation(this.cu);
            if (i == 161 || i == 0) {
                NearbyEngine.saveLastLocationToLocal(this.cu);
            }
            aM();
            return;
        }
        this.cv++;
        if (this.cv >= 2) {
            this.cv = 0;
            Location lastSavedLocation = NearbyEngine.getLastSavedLocation(true);
            if (lastSavedLocation != null) {
                this.cu = lastSavedLocation;
                return;
            }
            return;
        }
        int location = this.ct.getLocation(this);
        LogUtils.d("ret:" + location);
        if (location == 6) {
            this.mHandler.postDelayed(new er(this), 2000);
        }
    }

    private boolean aM() {
        boolean aN = aN();
        boolean z = aN && this.bN.isSelected();
        h(z);
        return aN;
    }

    private boolean h(boolean z) {
        if (!z) {
            this.bN.setSelected(false);
            this.bN.setText(R.string.live_location_off);
        } else if (this.cu == null || !this.cu.isValid()) {
            this.bN.setText(R.string.live_locating);
            ay();
        } else {
            this.bN.setText(this.cu.city);
        }
        return z;
    }

    private boolean aN() {
        return NearbyEngine.instance().isLocationServiceEnabled(this);
    }

    protected void aq() {
        if (this.cw != null && this.cw.isShowing()) {
            this.cw.dismiss();
        }
        finish();
    }

    protected void ai() {
        if (this.cw != null && this.cw.getWindow() != null && this.cw.isShowing()) {
            this.cw.setLiveData(this.aQ, this.aO, this.aR, this.aS);
        }
    }

    private void aO() {
        boolean z = !this.bT.isSelected();
        this.bT.setSelected(z);
        this.O.setFontSizeRate(z ? this.cB : 1.0f / this.cB);
    }

    private void aP() {
        ScreenShotUtils.takeLiveRoomScreeShot(this, this.g);
    }

    private void aQ() {
        boolean z = true;
        if (A()) {
            if (au() != null) {
                if (this.cC) {
                    z = false;
                }
                this.cC = z;
                au().muteLocalAudioStream(this.cC);
                aR();
            }
        } else if (this.bB != null) {
            if (this.cC) {
                z = false;
            }
            this.cC = z;
            this.bB.mute(this.cC);
            aR();
        }
    }

    private void aR() {
        this.bI.setSelected(this.cC);
        if (this.cx == null) {
            this.cx = (TextView) ((ViewStub) findViewById(R.id.viewstub_mute_hint)).inflate();
        }
        this.cx.setVisibility(this.cC ? 0 : 8);
    }

    private void aS() {
        this.cc.setVisibility(this.cc.getVisibility() == 0 ? 8 : 0);
    }

    private void c(int i) {
        this.bW.setTextColor(getResources().getColor(R.color.white));
        this.bV.setTextColor(getResources().getColor(R.color.white));
        this.bX.setTextColor(getResources().getColor(R.color.white));
        this.bY.setTextColor(getResources().getColor(R.color.white));
        this.ca.setTextColor(getResources().getColor(R.color.white));
        this.bR.setAlpha(1.0f);
        switch (i) {
            case 0:
                this.bR.setSelected(false);
                this.cF = false;
                this.bR.setAlpha(0.5f);
                break;
            case 1:
                this.bR.setSelected(true);
                this.cF = true;
                this.bW.setTextColor(getResources().getColor(R.color.color_ffe25b));
                break;
            case 2:
                this.bR.setSelected(true);
                this.cF = true;
                this.bV.setTextColor(getResources().getColor(R.color.color_ffe25b));
                break;
            case 3:
                this.bR.setSelected(true);
                this.cF = true;
                this.bX.setTextColor(getResources().getColor(R.color.color_ffe25b));
                break;
            case 4:
                this.bR.setSelected(true);
                this.cF = true;
                this.ca.setTextColor(getResources().getColor(R.color.color_ffe25b));
                break;
            case 5:
                this.bR.setSelected(true);
                this.cF = true;
                this.bY.setTextColor(getResources().getColor(R.color.color_ffe25b));
                break;
        }
        this.cG = i;
        this.cc.setVisibility(4);
    }

    private void aT() {
        NetRequest.getInstance().get(UrlConstants.LIVE_GAME_PERMISSION, new es(this), "game_permission", true);
    }

    protected boolean a(View view) {
        return false;
    }

    public void doMicConnect(User user) {
        if (this.ar == null || !TextUtils.isEmpty(this.aq)) {
            sendLiveMessageAndRefreshUI(LiveMessage.createMicMessage(this.ax.getOriginId(), 1, "", user.getOrigin(), user.getOriginId()));
            super.doMicConnect(user);
            return;
        }
        ToastUtil.Short(R.string.live_mic_inviting_limit);
    }

    public void doMicConnect(int i) {
        super.doMicConnect(i);
        if (i == 3 || i == 2) {
            g(false);
            h();
            this.bA.releaseCamera();
            doMicConnect(this.ch, true);
        }
    }

    protected void x() {
        this.bA.setUpCamera();
        J();
    }

    private void aU() {
        aV();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            super.onActivityResult(i, i2, intent);
            switch (i) {
                case 0:
                    a(this.bx.getCapturedUri());
                    return;
                case 1:
                    if (intent == null || intent.getData() == null) {
                        ToastUtil.Short(getString(R.string.user_image_empty));
                        return;
                    } else {
                        a(intent.getData());
                        return;
                    }
                case Crop.REQUEST_CROP /*6709*/:
                    String path = Crop.getOutput(intent).getPath();
                    if (!TextUtils.isEmpty(path)) {
                        this.bw = path;
                        this.bL.postDelayed(new eu(this, path), 500);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void a(Uri uri) {
        File file = new File(getCacheDir(), "cropped_" + System.currentTimeMillis());
        if (file.exists()) {
            file.delete();
        }
        Crop.of(uri, Uri.fromFile(file)).withMaxSize(PictureGetHelper.IMAGE_SIZE, PictureGetHelper.IMAGE_SIZE).start(this);
    }

    public void submitAvatar(String str) {
        obtainUploadToken();
    }

    public void obtainUploadToken() {
        NetRequest.getInstance().post(UrlConstants.LIVE_COVER_UPLOAD, new ev(this), "getUploadToken", true);
    }

    public void uploadAvatarToQiniu(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("user_id", Long.toString(this.ax.getPlatformId()));
        hashMap.put("token", AppUtils.getInstance().getUserInfoProvider().getToken());
        NormalUpload normalUpload = new NormalUpload();
        normalUpload.addUploadListener(new ew(this));
        normalUpload.uploadFile(str, str2, str3, hashMap);
    }

    private void aV() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (this.cH == null) {
                this.cH = new UserPicSelectDialog(this, this.bx);
            }
            this.cH.show();
        }
    }

    protected void F() {
    }

    private void aW() {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.bg_mask_hint);
        Bitmap createBitmap = Bitmap.createBitmap(WindowUtils.getScreenWidth(), WindowUtils.getScreenHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setXfermode(new PorterDuffXfermode(Mode.XOR));
        canvas.drawBitmap(decodeResource, new Rect(0, 0, decodeResource.getWidth(), decodeResource.getHeight()), new Rect(0, 0, WindowUtils.getScreenWidth(), WindowUtils.getScreenExactHeight()), paint);
        if (VERSION.SDK_INT >= 21) {
            canvas.drawRoundRect((float) this.cd.getLeft(), (float) (this.cd.getTop() + this.ck.getTop()), (float) this.cd.getRight(), (float) (this.cd.getBottom() + this.ck.getTop()), (float) WindowUtils.dp2Px(5), (float) WindowUtils.dp2Px(5), paint);
        } else {
            canvas.drawRect((float) this.cd.getLeft(), (float) (this.cd.getTop() + this.ck.getTop()), (float) this.cd.getRight(), (float) (this.cd.getBottom() + this.ck.getTop()), paint);
        }
        this.cJ.setImageBitmap(createBitmap);
    }
}
