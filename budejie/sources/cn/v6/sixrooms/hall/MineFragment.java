package cn.v6.sixrooms.hall;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.base.SixRoomsUtils;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.constants.CustomBroadcast;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.engine.BundleInfoEngine;
import cn.v6.sixrooms.engine.GetUserVisibleEngine;
import cn.v6.sixrooms.engine.NameModifyEngine;
import cn.v6.sixrooms.engine.UploadHeadPortraitEngine;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.net.NetworkState;
import cn.v6.sixrooms.ui.fragment.BaseFragment;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.ui.phone.BillManagerActivity;
import cn.v6.sixrooms.ui.phone.ChangeUserVisibilityActivity;
import cn.v6.sixrooms.ui.phone.ExchangeBean6ToCoin6Activity;
import cn.v6.sixrooms.ui.phone.HistoryActivity;
import cn.v6.sixrooms.ui.phone.MsgVerifyFragmentActivity;
import cn.v6.sixrooms.ui.phone.MyPropActivity;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.DrawableResourceUtils;
import cn.v6.sixrooms.utils.FastDoubleClickUtil;
import cn.v6.sixrooms.utils.FileUtil;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.HandleErrorUtils;
import cn.v6.sixrooms.utils.ImageViewUtils;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MineFragment extends BaseFragment implements OnClickListener, OnTouchListener {
    public static final int BUNDLE_OR_UNBUNDLE_PHONE_REQUEST_CODE = 4;
    private RelativeLayout A;
    private SimpleDraweeView B;
    private RelativeLayout C;
    private LinearLayout D;
    private TextView E;
    private TextView F;
    private TextView G;
    private LinearLayout H;
    private TextView I;
    private RelativeLayout J;
    private EditText K;
    private ImageView L;
    private ImageView M;
    private TextView N;
    private RelativeLayout O;
    private SimpleDraweeView P;
    private SimpleDraweeView Q;
    private TextView R;
    private RelativeLayout S;
    private ImageView T;
    private ImageView U;
    private ImageView V;
    private TextView W;
    private LinearLayout X;
    private TextView Y;
    private LinearLayout Z;
    private Context a;
    private TextView aa;
    private LinearLayout ab;
    private View ac;
    private View ad;
    private LinearLayout ae;
    private TextView af;
    private LinearLayout ag;
    private LinearLayout ah;
    private TextView ai;
    private LinearLayout aj;
    private LinearLayout ak;
    private TextView al;
    private TextView am;
    private LinearLayout an;
    private ImageView ao;
    private LinearLayout ap;
    private TextView aq;
    private ImageView ar;
    private FrameLayout as;
    private Handler at = new ap(this);
    private BroadcastReceiver au = new aw(this);
    private BaseFragmentActivity b;
    public final int back_from_visibility = 5;
    private InputMethodManager c;
    private UserBean d;
    private String e;
    private BundleInfoEngine f;
    private UserInfoEngine g;
    private NameModifyEngine h;
    private UploadHeadPortraitEngine i;
    private boolean j;
    private boolean k = false;
    private String l = null;
    private String m;
    private boolean n = false;
    private Bitmap o;
    private Bitmap p;
    private String q = "";
    private String r;
    private String s;
    private boolean t = false;
    private DecimalFormat u;
    private TranslateAnimation v;
    private TranslateAnimation w;
    private File x;
    private List<File> y;
    private RelativeLayout z;

    static /* synthetic */ void a(MineFragment mineFragment) {
        if (mineFragment.a()) {
            mineFragment.an.setVisibility(0);
            mineFragment.ao.setVisibility(0);
            mineFragment.J.setVisibility(0);
            mineFragment.ab.setVisibility(0);
            mineFragment.ac.setVisibility(0);
            mineFragment.ad.setVisibility(0);
            mineFragment.ae.setVisibility(0);
            mineFragment.ag.setVisibility(0);
            mineFragment.aj.setVisibility(0);
            mineFragment.setTitleText("我");
        } else {
            mineFragment.an.setVisibility(8);
            mineFragment.ap.setVisibility(8);
            mineFragment.J.setVisibility(4);
            mineFragment.ab.setVisibility(8);
            mineFragment.ac.setVisibility(8);
            mineFragment.ad.setVisibility(8);
            mineFragment.ae.setVisibility(8);
            mineFragment.ag.setVisibility(8);
            mineFragment.aj.setVisibility(8);
            mineFragment.setTitleText(mineFragment.d.getAlias());
        }
        mineFragment.H.setVisibility(0);
        mineFragment.updateUserHeadImage();
        mineFragment.aq.setText(mineFragment.d.getFollownum());
        CharSequence fansnum = mineFragment.d.getFansnum();
        if (fansnum == null) {
            fansnum = "0";
        }
        mineFragment.I.setText(fansnum);
        mineFragment.K.setText(mineFragment.d.getAlias().trim());
        mineFragment.N.setText(mineFragment.d.getRid());
        mineFragment.d();
        if (mineFragment.a()) {
            mineFragment.c();
        }
        mineFragment.e();
    }

    static /* synthetic */ void m(MineFragment mineFragment) {
        ((InputMethodManager) mineFragment.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(mineFragment.getActivity().getCurrentFocus().getWindowToken(), 2);
        mineFragment.K.setFocusable(false);
        mineFragment.K.setFocusableInTouchMode(false);
        mineFragment.L.setVisibility(0);
        mineFragment.M.setVisibility(8);
        mineFragment.s = mineFragment.K.getText().toString();
        if (mineFragment.s.length() <= 0) {
            mineFragment.K.setText(mineFragment.r);
        } else if (!mineFragment.s.equals(mineFragment.r)) {
            mineFragment.a("正在修改昵称...");
            mineFragment.h.modifyNewName(mineFragment.s, mineFragment.d.getId(), SaveUserInfoUtils.getEncpass(mineFragment.a));
        }
        mineFragment.t = false;
    }

    public static MineFragment newInstance(String str) {
        MineFragment mineFragment = new MineFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HistoryOpenHelper.COLUMN_UID, str);
        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.e = arguments.getString(HistoryOpenHelper.COLUMN_UID);
        }
        this.u = new DecimalFormat("###,###");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.phone_fragment_mine, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getView() != null) {
            this.z = (RelativeLayout) getView().findViewById(R.id.rl_progressBar);
            this.as = (FrameLayout) getView().findViewById(R.id.title_layout);
            this.A = (RelativeLayout) getView().findViewById(R.id.rl_tx_personal);
            this.B = (SimpleDraweeView) getView().findViewById(R.id.iv_tx_personal);
            this.C = (RelativeLayout) getView().findViewById(R.id.rl_bgClickToCancel);
            this.D = (LinearLayout) getView().findViewById(R.id.ll_animationPart);
            this.E = (TextView) getView().findViewById(R.id.tv_fromCamera);
            this.F = (TextView) getView().findViewById(R.id.tv_fromGallery);
            this.G = (TextView) getView().findViewById(R.id.tv_cancel);
            this.H = (LinearLayout) getView().findViewById(R.id.ll_topPart);
            this.I = (TextView) getView().findViewById(R.id.tv_fx_personal);
            this.J = (RelativeLayout) getView().findViewById(R.id.nameEditPart);
            this.K = (EditText) getView().findViewById(R.id.et_name_personal);
            this.K.setLongClickable(false);
            this.L = (ImageView) getView().findViewById(R.id.ivEdit);
            this.M = (ImageView) getView().findViewById(R.id.ivClear);
            this.N = (TextView) getView().findViewById(R.id.tv_fh_personal);
            this.O = (RelativeLayout) getView().findViewById(R.id.rl_richPart);
            this.P = (SimpleDraweeView) getView().findViewById(R.id.current_richLevel);
            this.ar = (ImageView) getView().findViewById(R.id.richProgressIn);
            this.Q = (SimpleDraweeView) getView().findViewById(R.id.next_richLevel);
            this.R = (TextView) getView().findViewById(R.id.distanceRich);
            this.S = (RelativeLayout) getView().findViewById(R.id.rl_starPart);
            this.T = (ImageView) getView().findViewById(R.id.current_starLevel);
            this.U = (ImageView) getView().findViewById(R.id.starProgressIn);
            this.V = (ImageView) getView().findViewById(R.id.next_starLevel);
            this.W = (TextView) getView().findViewById(R.id.distanceStar);
            this.X = (LinearLayout) getView().findViewById(R.id.ll_coin6);
            this.Y = (TextView) getView().findViewById(R.id.current_coin6);
            this.Z = (LinearLayout) getView().findViewById(R.id.ll_bean6);
            this.aa = (TextView) getView().findViewById(R.id.current_bean6);
            View findViewById = getView().findViewById(R.id.ll_historical_view);
            TextView textView = (TextView) getView().findViewById(R.id.id_setting_logout);
            if ("0".equals(V6Coop.COOP_LOGIN_TYPE)) {
                textView.setOnClickListener(this);
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
            textView = (TextView) getView().findViewById(R.id.id_tv_hotline_setting);
            textView.setText(Html.fromHtml(getString(R.string.setting_hotline_number)));
            textView.setOnClickListener(this);
            if (a()) {
                findViewById.setOnClickListener(this);
                getView().findViewById(R.id.tag_historical_view).setVisibility(0);
            } else {
                findViewById.setVisibility(8);
                getView().findViewById(R.id.tag_historical_view).setVisibility(8);
            }
            this.ab = (LinearLayout) getView().findViewById(R.id.ll_manage);
            this.ac = getView().findViewById(R.id.personal_prop_top);
            this.ad = getView().findViewById(R.id.personal_prop_bottom);
            this.ae = (LinearLayout) getView().findViewById(R.id.ll_bill);
            this.af = (TextView) getView().findViewById(R.id.tv_loadingHint);
            this.ag = (LinearLayout) getView().findViewById(R.id.setUserVisiblePart);
            this.ah = (LinearLayout) getView().findViewById(R.id.llChangeUserVisibility);
            this.ai = (TextView) getView().findViewById(R.id.tvUserCurrentVisibility);
            this.aj = (LinearLayout) getView().findViewById(R.id.llSecurityPart);
            this.ak = (LinearLayout) getView().findViewById(R.id.ll_bundle_phone);
            this.al = (TextView) getView().findViewById(R.id.tv_bundle_phone_number);
            this.am = (TextView) getView().findViewById(R.id.tv_is_bundle);
            this.an = (LinearLayout) getView().findViewById(R.id.privatePart);
            this.ao = (ImageView) getView().findViewById(R.id.ivIconCamera);
            this.ap = (LinearLayout) getView().findViewById(R.id.rechargePart);
            this.aq = (TextView) getView().findViewById(R.id.tv_fx_attentionNum);
        }
        this.as.addView(View.inflate(this.a, R.layout.title_bar_hall_defult, null));
        String str = null;
        Drawable drawable = null;
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.phone_ic_back), str, drawable, "", new ay(this), null, V6Coop.getInstance().isShowBack());
        this.z.setVisibility(0);
        if (a()) {
            this.d = GlobleValue.getUserBean();
            b();
        }
        this.at.postDelayed(new av(this), 50);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.au, new IntentFilter(CustomBroadcast.COOPLOGIN_LOGIN));
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.at.postDelayed(new az(this), 50);
    }

    private boolean a() {
        return LoginUtils.isLogin() && LoginUtils.getLoginUID().equals(this.e);
    }

    public void onClick(View view) {
        if (!FastDoubleClickUtil.isFastDoubleClick()) {
            int id = view.getId();
            if (id == R.id.rl_tx_personal) {
                if (a()) {
                    this.C.setVisibility(0);
                    if (this.v == null) {
                        this.v = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
                        this.v.setDuration(200);
                        this.v.setFillAfter(true);
                        this.v.setAnimationListener(new ar(this));
                    }
                    this.D.startAnimation(this.v);
                }
            } else if (id == R.id.rl_bgClickToCancel) {
                g();
            } else if (id == R.id.tv_fromCamera) {
                this.n = hasSDcard();
                r0 = new Intent("android.media.action.IMAGE_CAPTURE");
                if (this.n) {
                    this.x = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/android/" + System.currentTimeMillis() + ".jpg");
                    r0.putExtra("output", Uri.fromFile(this.x));
                } else {
                    this.x = new File(getActivity().getDir("photo", 0).getAbsolutePath() + System.currentTimeMillis() + ".jpg");
                }
                if (this.y == null) {
                    this.y = new ArrayList();
                }
                this.y.add(this.x);
                r0.putExtra("return-data", true);
                startActivityForResult(r0, 1);
                g();
            } else if (id == R.id.tv_fromGallery) {
                r0 = new Intent("android.intent.action.PICK", null);
                r0.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(r0, 2);
                g();
            } else if (id == R.id.tv_cancel) {
                g();
            } else if (id == R.id.nameEditPart) {
                if (this.t) {
                    this.K.setText("");
                    return;
                }
                f();
                this.K.setFocusable(true);
                this.K.setFocusableInTouchMode(true);
                this.K.requestFocus();
                this.t = true;
                this.r = this.K.getText().toString();
                this.K.setSelection(this.r.length());
                this.L.setVisibility(8);
                this.M.setVisibility(0);
                this.c.showSoftInput(this.K, 0);
            } else if (id == R.id.ll_coin6) {
                SixRoomsUtils.goToRechargeActivi(getActivity());
            } else if (id == R.id.ll_bean6) {
                startActivityForResult(new Intent(this.a, ExchangeBean6ToCoin6Activity.class), 0);
            } else if (id == R.id.ll_manage) {
                startActivity(new Intent(this.a, MyPropActivity.class));
            } else if (id == R.id.ll_bundle_phone) {
                if (this.k) {
                    a(this.j);
                }
            } else if (id == R.id.ll_bundle_phone) {
                if (this.k) {
                    a(this.j);
                }
            } else if (id == R.id.ll_bill) {
                startActivity(new Intent(this.a, BillManagerActivity.class));
            } else if (id == R.id.llChangeUserVisibility) {
                if ("2".equals(this.q)) {
                    new DialogUtils(getActivity()).createConfirmDialogs(0, "提示", "本功能为至尊VIP用户专享", "确定", null).show();
                    return;
                }
                r0 = new Intent(this.a, ChangeUserVisibilityActivity.class);
                r0.putExtra("visibleStatus", this.q);
                startActivityForResult(r0, 5);
            } else if (id == R.id.ll_historical_view) {
                startActivity(new Intent(this.a, HistoryActivity.class));
            } else if (id == R.id.id_setting_logout) {
                HandleErrorUtils.logout(getActivity());
                V6Coop.getInstance().pushLogoutMsg();
            } else if (id == R.id.id_tv_hotline_setting) {
                TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService("phone");
                if (telephonyManager == null || telephonyManager.getSimState() == 1) {
                    ToastUtils.showToast(R.string.not_sim);
                    return;
                }
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(String.format("tel:%s", new Object[]{"4007061888"}))));
            }
        }
    }

    private void b() {
        c();
        d();
    }

    private void c() {
        String coin6 = this.d.getCoin6();
        if (TextUtils.isEmpty(coin6)) {
            coin6 = "0";
        }
        this.Y.setText(String.format("%s个", new Object[]{this.u.format(Long.parseLong(coin6))}));
        coin6 = this.d.getWealth();
        if (TextUtils.isEmpty(coin6)) {
            coin6 = "0";
        }
        this.aa.setText(String.format("%s个", new Object[]{this.u.format(Long.parseLong(coin6))}));
    }

    private void d() {
        ImageViewUtils.setWealthImageView(this.d.getId(), Integer.parseInt(this.d.getCoin6rank()), this.P, this.d.getIsGodPic() == 1);
        if (this.d.getIsGodPic() == 1) {
            this.Q.setVisibility(8);
        } else {
            this.Q.setVisibility(0);
            ImageViewUtils.setWealthImageView(this.d.getId(), Integer.parseInt(this.d.getCoin6rank()) + 1, this.Q, false);
        }
        String coin6late = this.d.getCoin6late();
        this.R.setText("还差" + this.u.format(Long.parseLong(coin6late)) + "六币");
        int a = a(coin6late, this.d.getCoinstep());
        LayoutParams layoutParams = (LayoutParams) this.ar.getLayoutParams();
        layoutParams.width = a;
        this.ar.setLayoutParams(layoutParams);
        this.O.setVisibility(0);
        a(this.T, DrawableResourceUtils.getStarLevelImageResource(Integer.parseInt(this.d.getWealthrank())));
        a(this.V, DrawableResourceUtils.getStarLevelImageResource(Integer.parseInt(this.d.getWealthrank()) + 1));
        coin6late = this.d.getWealtlate();
        this.W.setText("还差" + this.u.format(Long.parseLong(coin6late)) + "六豆");
        a = a(coin6late, this.d.getWealthstep());
        layoutParams = (LayoutParams) this.U.getLayoutParams();
        layoutParams.width = a;
        this.U.setLayoutParams(layoutParams);
        this.S.setVisibility(0);
    }

    private void e() {
        this.z.setVisibility(8);
    }

    protected void getBundlePhoneInfo() {
        if (a()) {
            this.f = new BundleInfoEngine(new ba(this));
            this.f.getBundleInfo(SaveUserInfoUtils.getEncpass(this.a), LoginUtils.getLoginUID());
        }
    }

    private static int a(String str, String str2) {
        int i = 0;
        float parseFloat = Float.parseFloat(str);
        float parseFloat2 = Float.parseFloat(str2);
        int dip2px = DensityUtil.dip2px(105.0f);
        parseFloat = ((parseFloat2 - parseFloat) / parseFloat2) * ((float) dip2px);
        parseFloat2 = ((float) dip2px) / 10.0f;
        if (parseFloat == 0.0f) {
            return 0;
        }
        while (i < 10) {
            if (parseFloat <= ((float) i) * parseFloat2 || parseFloat > ((float) (i + 1)) * parseFloat2) {
                i++;
            } else {
                parseFloat = ((float) (i + 1)) * parseFloat2;
                if (parseFloat >= parseFloat2 * 9.0f) {
                    parseFloat = parseFloat2 * 9.0f;
                }
                return (int) parseFloat;
            }
        }
        return (int) parseFloat;
    }

    private void a(ImageView imageView, int i) {
        if (i == -1) {
            imageView.setBackgroundColor(isAdded() ? getResources().getColor(R.color.white) : this.b.getResources().getColor(R.color.white));
        } else {
            imageView.setImageResource(i);
        }
    }

    public void updateUserHeadImage() {
        if (UrlStrs.UNKNOW_PORTRAIT.equals(this.d.getPicuser())) {
            this.B.setImageResource(R.drawable.rooms_third_leftmenu_default_portrait);
        } else {
            this.B.setImageURI(Uri.parse(this.d.getPicuser()));
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.et_name_personal) {
            switch (motionEvent.getAction()) {
                case 0:
                    f();
                    break;
                case 1:
                    this.K.setFocusable(true);
                    this.K.setFocusableInTouchMode(true);
                    this.K.requestFocus();
                    this.t = true;
                    this.r = this.K.getText().toString();
                    this.L.setVisibility(8);
                    this.M.setVisibility(0);
                    break;
            }
        }
        return false;
    }

    private void a(String str) {
        if (this.af == null) {
            this.af = (TextView) getView().findViewById(R.id.tv_loadingHint);
        }
        this.af.setText(str);
        if (this.z == null) {
            this.z = (RelativeLayout) getView().findViewById(R.id.rl_progressBar);
        }
        this.z.setVisibility(0);
    }

    private void f() {
        if (this.h == null) {
            this.h = new NameModifyEngine(new aq(this));
        }
    }

    private void g() {
        if (this.w == null) {
            this.w = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            this.w.setDuration(200);
            this.w.setFillAfter(true);
            this.w.setAnimationListener(new as(this));
        }
        this.D.startAnimation(this.w);
    }

    public boolean hasSDcard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private void a(boolean z) {
        Intent intent = new Intent(this.a, MsgVerifyFragmentActivity.class);
        if (z) {
            intent.putExtra(UserTrackerConstants.FROM, "unbundle");
            intent.putExtra("phoneNumber", this.l);
        } else {
            intent.putExtra(UserTrackerConstants.FROM, "bundle");
            intent.putExtra("phoneNumber", "");
            intent.putExtra("isneedpaawd", this.m);
        }
        startActivityForResult(intent, 4);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (LoginUtils.isLogin()) {
            this.e.equals(LoginUtils.getLoginUID());
        }
        if (a()) {
            this.d = GlobleValue.getUserBean();
            b();
        }
        Bundle extras;
        switch (i) {
            case 1:
                if (!this.n) {
                    if (intent != null) {
                        extras = intent.getExtras();
                        if (extras == null) {
                            ToastUtils.showToast(getString(R.string.saveFailedPlaeseRetry));
                            break;
                        }
                        this.o = (Bitmap) extras.getParcelable("data");
                        if (this.o == null) {
                            ToastUtils.showToast(getString(R.string.saveFailedPlaeseRetry));
                            break;
                        }
                        FileUtil.saveBitmap(this.o, this.x);
                        a(this.x.getAbsolutePath());
                        break;
                    }
                    ToastUtils.showToast(getString(R.string.thereIsNoPhoto));
                    break;
                } else if (this.x.length() > 0) {
                    a(Uri.fromFile(this.x));
                    break;
                }
                break;
            case 2:
                if (intent != null) {
                    a(intent.getData());
                    break;
                }
                break;
            case 3:
                if (intent != null) {
                    extras = intent.getExtras();
                    if (extras != null) {
                        this.p = (Bitmap) extras.getParcelable("data");
                        if (this.p != null) {
                            a(this.p);
                            break;
                        }
                    }
                    ToastUtils.showToast(getString(R.string.saveFailedPlaeseRetry));
                    break;
                }
                break;
            case 4:
                if (intent != null) {
                    if (intent.getBooleanExtra("issucceed", false)) {
                        this.k = false;
                        String str = null;
                        if (GlobleValue.getUserBean() != null) {
                            str = GlobleValue.getUserBean().getId();
                        }
                        this.f.getBundleInfo(SaveUserInfoUtils.getEncpass(this.a), str);
                        break;
                    }
                }
                return;
                break;
            case 5:
                Object stringExtra = intent.getStringExtra("currentUserStatus");
                if (!TextUtils.isEmpty(stringExtra)) {
                    if ("0".equals(stringExtra)) {
                        this.ai.setText("显身");
                    } else if ("1".equals(stringExtra)) {
                        this.ai.setText("隐身");
                    } else if ("2".equals(stringExtra)) {
                        this.ai.setText("显身");
                    }
                    GlobleValue.RESULT_BACK_FROM_PERSONAL = stringExtra;
                    break;
                }
                break;
        }
        super.onActivityResult(i, i2, intent);
    }

    private void a(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", Constants.SERVICE_SCOPE_FLAG_VALUE);
        intent.putExtra("aspectX", 4);
        intent.putExtra("aspectY", 4);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void a(Object obj) {
        a("正在上传头像...");
        if (this.i == null) {
            this.i = new UploadHeadPortraitEngine(new at(this));
        }
        String str = null;
        if (GlobleValue.getUserBean() != null) {
            str = GlobleValue.getUserBean().getId();
        }
        if (obj instanceof Bitmap) {
            this.i.sendPic((Bitmap) obj, SaveUserInfoUtils.getEncpass(this.a), str);
        } else {
            this.i.sendPic(obj.toString(), SaveUserInfoUtils.getEncpass(this.a), str);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.a = getActivity();
        this.b = (BaseFragmentActivity) getActivity();
        ((HallActivity) getActivity()).setOnHideKeyboardListener(new au(this));
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.o != null) {
            this.o.recycle();
        }
        if (this.p != null) {
            this.p.recycle();
        }
        if (this.y != null && this.y.size() > 0) {
            for (File file : this.y) {
                if (file != null && file.exists()) {
                    file.delete();
                }
            }
        }
        this.at.removeCallbacksAndMessages(null);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.au);
    }

    public void onResume() {
        super.onResume();
        if (a()) {
            this.d = GlobleValue.getUserBean();
            b();
        }
    }

    static /* synthetic */ void b(MineFragment mineFragment) {
        mineFragment.A.setOnClickListener(mineFragment);
        if (mineFragment.a()) {
            mineFragment.C.setOnClickListener(mineFragment);
            mineFragment.E.setOnClickListener(mineFragment);
            mineFragment.F.setOnClickListener(mineFragment);
            mineFragment.G.setOnClickListener(mineFragment);
        }
        if (mineFragment.a()) {
            mineFragment.K.setOnTouchListener(mineFragment);
            mineFragment.K.setOnEditorActionListener(new bd(mineFragment));
            mineFragment.J.setOnClickListener(mineFragment);
        }
        mineFragment.X.setOnClickListener(mineFragment);
        mineFragment.ab.setOnClickListener(mineFragment);
        mineFragment.ak.setOnClickListener(mineFragment);
        mineFragment.Z.setOnClickListener(mineFragment);
        mineFragment.ae.setOnClickListener(mineFragment);
        mineFragment.ah.setOnClickListener(mineFragment);
    }

    static /* synthetic */ void c(MineFragment mineFragment) {
        if (TextUtils.isEmpty(mineFragment.e)) {
            mineFragment.e();
        } else {
            mineFragment.at.sendEmptyMessage(5);
        }
    }

    static /* synthetic */ boolean d(MineFragment mineFragment) {
        if (TextUtils.isEmpty(mineFragment.e)) {
            mineFragment.e();
            ToastUtils.showToast(mineFragment.getString(R.string.cantFindUser));
            return false;
        }
        mineFragment.g = new UserInfoEngine(new bb(mineFragment));
        if (NetworkState.checkNet(mineFragment.a)) {
            mineFragment.g.getUserInfo(SaveUserInfoUtils.getEncpass(mineFragment.a), mineFragment.e);
            if (mineFragment.a()) {
                new GetUserVisibleEngine(new bc(mineFragment)).getUserVisible(mineFragment.e, SaveUserInfoUtils.getEncpass(mineFragment.a));
            }
        } else {
            ToastUtils.showToast(mineFragment.getString(R.string.tip_no_network));
        }
        mineFragment.c = (InputMethodManager) mineFragment.a.getSystemService("input_method");
        return true;
    }
}
