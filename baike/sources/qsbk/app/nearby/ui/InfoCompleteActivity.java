package qsbk.app.nearby.ui;

import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Calendar;
import java.util.Date;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.api.BigCoverHelper;
import qsbk.app.api.BigCoverHelper.UploadListener;
import qsbk.app.api.UserHeaderHelper;
import qsbk.app.core.AsyncTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.MobileBrand;
import qsbk.app.nearby.ui.HometownDialogFragment.Hometown;
import qsbk.app.nearby.ui.HometownDialogFragment.OnHometownSelect;
import qsbk.app.utils.AstrologyUtils;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.NameLengthFilter;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.DatePickerDialogFragment;

public class InfoCompleteActivity extends BaseActionBarActivity implements OnDateSetListener, UploadListener, OnHometownSelect {
    public static final String ACTION_CHANGE_USERINFO = "action_change_userinfo";
    public static final String ACTION_KEY_FROM = "key_from";
    public static final int ACTION_VALUE_INFO_COMPLETE = 1;
    public static final int ACTION_VALUE_INFO_EDIT = 2;
    public static final String DOT_FORMAT = "%s · %s";
    private static final String a = InfoCompleteActivity.class.getSimpleName();
    private static final String[] b = new String[]{"M", "F"};
    private static final String[] c = new String[]{"single", "married", "secret", "inlove"};
    private LinearLayout A;
    private LinearLayout B;
    private LinearLayout C;
    private String D = null;
    private String E = null;
    private long F = 0;
    private String G = null;
    private String H = null;
    private String I = null;
    private String J = "";
    private String K = null;
    private String O = null;
    private String P = null;
    private Uri Q = null;
    private ProgressDialog R;
    private ProgressDialog S = null;
    private int d = -1;
    private TextView e;
    private TextView f;
    private TextView g;
    private EditText h;
    private ImageView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private UserHeaderHelper n;
    private SizeChangeScrollView o;
    private DatePickerDialogFragment p;
    private Calendar q = null;
    private ImageView r;
    private BigCoverHelper s;
    private View t;
    private LinearLayout u;
    private LinearLayout v;
    private LinearLayout w;
    private LinearLayout x;
    private LinearLayout y;
    private LinearLayout z;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return g();
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    private void k() {
        if (this.p == null) {
            this.p = new DatePickerDialogFragment();
            this.p.setMaxDate(new Date().getTime());
            this.p.setDateSetListener(this);
            this.p.setInitialTime(this.F);
        }
        if (!this.p.isAdded()) {
            this.p.show(getSupportFragmentManager(), a);
        }
    }

    private boolean l() {
        return (QsbkApp.currentUser == null || TextUtils.isEmpty(QsbkApp.currentUser.userIcon) || "null".equals(QsbkApp.currentUser.userIcon.toString())) ? false : true;
    }

    private void m() {
        if (l()) {
            FrescoImageloader.displayAvatar(this.i, UserHeaderHelper.getIconUrl(QsbkApp.currentUser));
        } else {
            this.i.setImageResource(UIHelper.getDefaultAvatar());
        }
    }

    public void submitAvatar(String str) {
        H();
        new h(this, str, str).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            super.onActivityResult(i, i2, intent);
            String stringExtra;
            switch (i) {
                case 1:
                    if (intent == null || intent.getData() == null) {
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "选择的图片为空", Integer.valueOf(0)).show();
                        return;
                    } else {
                        this.n.doCropPhoto(intent.getData());
                        return;
                    }
                case 2:
                    Object savePickedBitmap = this.n.savePickedBitmap(intent);
                    if (!TextUtils.isEmpty(savePickedBitmap)) {
                        this.P = savePickedBitmap;
                        submitAvatar(this.P);
                        return;
                    }
                    return;
                case 3:
                    this.n.doCropPhotoWithCaptured();
                    return;
                case 100:
                    stringExtra = intent.getStringExtra(JobEditorActivity.KEY_EDIT_RESULT);
                    this.O = stringExtra;
                    z();
                    g(stringExtra);
                    return;
                case 101:
                    String stringExtra2 = intent.getStringExtra(HauntEditorActivity.HAUNT_EDIT_RESULT);
                    if (stringExtra2.equals(HauntEditorActivity.HAUNT_NAME_OF_HIDE)) {
                        stringExtra = "";
                    } else {
                        stringExtra = stringExtra2;
                    }
                    this.K = stringExtra;
                    z();
                    f(stringExtra2);
                    return;
                case 102:
                    MobileBrand mobileBrand = (MobileBrand) intent.getSerializableExtra(MobileBrandEditorActivity.MOBILE_BRAND_EDIT_RESULT);
                    this.I = mobileBrand.getName();
                    this.J = mobileBrand.getCode();
                    z();
                    e(this.I);
                    return;
                case BigCoverHelper.REQCODE_CAREMA /*160*/:
                    LogUtil.d("req_camera");
                    this.s.startCrop();
                    return;
                case 161:
                    this.s.setImageUri(intent.getData());
                    this.s.startCrop();
                    return;
                case 162:
                    this.Q = (Uri) intent.getParcelableExtra("uri");
                    this.s.upload(this, this.Q);
                    return;
                default:
                    return;
            }
        }
    }

    private void a(long j) {
        if (j == -1 || j == 0) {
            c("");
            return;
        }
        if (this.q == null) {
            this.q = Calendar.getInstance();
        }
        this.q.setTimeInMillis(j);
        c(String.format(DOT_FORMAT, new Object[]{Integer.valueOf(AstrologyUtils.getAge(this.q)), AstrologyUtils.date2Astrology(this.q)}));
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.e.setText(str);
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.f.setText(str);
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.g.setText(str.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
    }

    private String a(int i) {
        return getResources().getString(i);
    }

    private void d(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "drawable://" + UIHelper.getSmallBigCover();
        }
        FrescoImageloader.displayImage(this.r, str);
    }

    private void n() {
        j(this.D);
        k(this.E);
        a(this.F);
        i(this.G);
        h(this.H);
        e(this.I);
        f(this.K);
        g(this.O);
        d(QsbkApp.currentUser.bigCover);
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.l.setText(str);
    }

    private void f(String str) {
        if (TextUtils.isEmpty(str)) {
            this.m.setText(HauntEditorActivity.HAUNT_NAME_OF_HIDE);
        } else {
            this.m.setText(str.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
        }
    }

    private void g(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.j.setText(str);
    }

    private void h(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.k.setText(str.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
    }

    private void i(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.h.setText(str);
    }

    private void j(String str) {
        if (TextUtils.isEmpty(str) || str.equals(BaseUserInfo.GENDER_UNKONW)) {
            a("");
        } else if ("F".equals(str)) {
            a(a((int) R.string.female));
        } else {
            a(a((int) R.string.male));
        }
    }

    private void k(String str) {
        if (TextUtils.isEmpty(str)) {
            b("");
        } else if ("single".equals(str)) {
            b("单身");
        } else if ("married".equals(str)) {
            b("已婚");
        } else if ("secret".equals(str)) {
            b("保密");
        } else if ("inlove".equals(str)) {
            b("恋爱中");
        }
    }

    private void q() {
        new Builder(this).setTitle("选择头像").setItems(new String[]{"拍照", "相册"}, new ae(this)).setNegativeButton("取消", new t(this)).show();
    }

    private void r() {
        int length = b.length;
        int i = 0;
        while (i < length) {
            if (b[i].equals(this.D)) {
                break;
            }
            i++;
        }
        i = -1;
        new Builder(this).setSingleChoiceItems(new String[]{a((int) R.string.male), a((int) R.string.female)}, i, new af(this)).setCancelable(true).show();
    }

    private void s() {
        int i = -1;
        int length = c.length;
        int i2 = 0;
        while (i2 < length) {
            if (c[i2].equals(this.E)) {
                break;
            }
            i2++;
            i = 2;
        }
        i2 = i;
        new Builder(this).setSingleChoiceItems(new String[]{"单身", "已婚", "保密", "恋爱中"}, i2, new ag(this)).setCancelable(true).show();
    }

    private void t() {
        startActivityForResult(new Intent(this, JobEditorActivity.class), 100);
    }

    private void u() {
        startActivityForResult(new Intent(this, MobileBrandEditorActivity.class), 102);
    }

    private void v() {
        startActivityForResult(new Intent(this, HauntEditorActivity.class), 101);
    }

    private void w() {
        new Builder(this).setTitle("换大罩").setItems(new String[]{"拍照", "相册"}, new ai(this)).setNegativeButton("取消", new ah(this)).show();
    }

    private void x() {
        HometownDialogFragment.newInstance(null).show(getSupportFragmentManager(), HometownDialogFragment.class.getSimpleName());
    }

    protected void f() {
        if (UIHelper.isNightTheme()) {
            findViewById(R.id.layerMask).setVisibility(0);
        } else {
            findViewById(R.id.layerMask).setVisibility(8);
        }
        this.u = (LinearLayout) findViewById(R.id.head_area);
        this.v = (LinearLayout) findViewById(R.id.sex_area);
        this.w = (LinearLayout) findViewById(R.id.age_astrol_area);
        this.x = (LinearLayout) findViewById(R.id.job_area);
        this.y = (LinearLayout) findViewById(R.id.hometown_area);
        this.z = (LinearLayout) findViewById(R.id.mobile_brand_area);
        this.A = (LinearLayout) findViewById(R.id.haunt_area);
        this.B = (LinearLayout) findViewById(R.id.big_cover_area);
        this.C = (LinearLayout) findViewById(R.id.emotion_area);
        this.g = (TextView) findViewById(R.id.ageAstrol);
        this.e = (TextView) findViewById(R.id.gender);
        this.f = (TextView) findViewById(R.id.emotion_choice);
        this.i = (ImageView) findViewById(R.id.userhead);
        this.h = (EditText) findViewById(R.id.username);
        this.j = (TextView) findViewById(R.id.job);
        this.k = (TextView) findViewById(R.id.hometown);
        this.l = (TextView) findViewById(R.id.mobile_brand);
        this.m = (TextView) findViewById(R.id.haunt);
        this.r = (ImageView) findViewById(R.id.bigCover);
        this.B.setOnClickListener(new aj(this));
        this.u.setOnClickListener(new ak(this));
        this.v.setOnClickListener(new j(this));
        this.C.setOnClickListener(new k(this));
        this.x.setOnClickListener(new l(this));
        this.y.setOnClickListener(new m(this));
        this.z.setOnClickListener(new n(this));
        this.A.setOnClickListener(new o(this));
        this.h.setFilters(new InputFilter[]{new NameLengthFilter(16, this, "昵称最多为8个汉字")});
        this.h.setOnTouchListener(new p(this));
        this.w.setOnClickListener(new q(this));
        this.o = (SizeChangeScrollView) findViewById(R.id.id_scroll_view);
        this.o.setOnSizeChangeListner(new r(this));
        n();
        this.t = findViewById(R.id.saveBtn);
        this.t.setOnClickListener(new s(this));
        this.h.addTextChangedListener(new u(this));
        y();
        if (this.d != 1) {
            findViewById(R.id.notice_info_complete).setVisibility(8);
        }
        if (D()) {
            ((ImageView) findViewById(R.id.bigCoverEditable)).setImageResource(UIHelper.getInfoEditable());
        } else {
            ((ImageView) findViewById(R.id.bigCoverEditable)).setImageResource(UIHelper.getInfoCountdown());
        }
        if (A()) {
            ((ImageView) findViewById(R.id.genderEditable)).setImageResource(UIHelper.getInfoEditable());
        } else {
            ((ImageView) findViewById(R.id.genderEditable)).setImageResource(UIHelper.getInfoLocked());
        }
        if (C()) {
            ((ImageView) findViewById(R.id.userHeadEditable)).setImageResource(UIHelper.getInfoEditable());
        } else {
            ((ImageView) findViewById(R.id.userHeadEditable)).setImageResource(UIHelper.getInfoCountdown());
        }
        if (B()) {
            ((ImageView) findViewById(R.id.usernameEditable)).setImageResource(UIHelper.getInfoEditable());
            return;
        }
        this.h.setCursorVisible(false);
        ((ImageView) findViewById(R.id.usernameEditable)).setImageResource(UIHelper.getInfoCountdown());
    }

    private void y() {
        if (this.d <= 0) {
            this.d = getIntent().getIntExtra(ACTION_KEY_FROM, 2);
        }
    }

    private void z() {
        this.t.setEnabled(J());
    }

    private boolean A() {
        return (QsbkApp.currentUser.gender.equals("F") || QsbkApp.currentUser.gender.equals("M")) ? false : true;
    }

    private boolean B() {
        return QsbkApp.currentUser.usrNameEday == 0;
    }

    private boolean C() {
        return QsbkApp.currentUser.usrIconEday == 0;
    }

    private boolean D() {
        return QsbkApp.currentUser.bigCoverEday == 0;
    }

    private void E() {
        if (TextUtils.isEmpty(this.D)) {
            ToastAndDialog.makeNegativeToast(this, getResources().getString(R.string.toast_input_sex)).show();
        } else if (TextUtils.isEmpty(this.E)) {
            ToastAndDialog.makeNegativeToast(this, getResources().getString(R.string.toast_input_emotion)).show();
        } else if (this.F == 0) {
            ToastAndDialog.makeNegativeToast(this, getResources().getString(R.string.toast_input_birth)).show();
        } else {
            this.G = this.h.getText().toString();
            if (TextUtils.isEmpty(this.G)) {
                ToastAndDialog.makeNegativeToast(this, getResources().getString(R.string.toast_input_username)).show();
            } else if (this.G.length() + NameLengthFilter.getChineseCount(this.G) < 4) {
                ToastAndDialog.makeNegativeToast(this, "昵称最少为2个汉字").show();
            } else if (TextUtils.isEmpty(this.H)) {
                ToastAndDialog.makeNegativeToast(this, getResources().getString(R.string.toast_input_hometown)).show();
            } else if (TextUtils.isEmpty(this.O)) {
                ToastAndDialog.makeNegativeToast(this, getResources().getString(R.string.toast_input_job)).show();
            } else if (HttpUtils.netIsAvailable()) {
                F();
            } else {
                ToastAndDialog.makeNegativeToast(this, getResources().getString(R.string.network_not_connected)).show();
            }
        }
    }

    private void F() {
        AsyncTask vVar = new v(this);
        H();
        vVar.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[0]);
    }

    private void G() {
        if (this.R != null) {
            this.R.dismiss();
        }
    }

    private void H() {
        if (this.R == null) {
            this.R = ProgressDialog.show(this, null, getString(R.string.msg_info_saving), true, false);
        }
        if (!this.R.isShowing()) {
            this.R.show();
        }
    }

    private void I() {
        try {
            if (this.S != null) {
                this.S.dismiss();
            }
        } catch (Exception e) {
        }
    }

    private void b(int i) {
        if (this.S == null) {
            this.S = new ProgressDialog(this);
            this.S.setCancelable(false);
            this.S.setIndeterminate(false);
            this.S.setTitle("上传大罩中，请稍候...");
            this.S.setProgressStyle(1);
            this.S.setMax(100);
        }
        this.S.setProgress(i);
        if (!this.S.isShowing()) {
            this.S.show();
        }
    }

    protected void onResume() {
        m();
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected String g() {
        y();
        return this.d == 1 ? getString(R.string.info_complete) : getString(R.string.my_profile_edit);
    }

    protected int a() {
        return R.layout.activity_infocomplete;
    }

    public void finish() {
        if (J()) {
            new Builder(this).setTitle(R.string.nearby_pop_title).setMessage(R.string.info_complete_exit_confirm_msg).setPositiveButton(R.string.info_complete_exit_ok, new y(this)).setNegativeButton(R.string.info_complete_exit_deny, new x(this)).setNeutralButton(R.string.info_complete_exit_cancel, new w(this)).show();
        } else {
            super.finish();
        }
    }

    private boolean J() {
        return (this.F == QsbkApp.currentUser.birthday * 1000 && ((TextUtils.isEmpty(this.D) || this.D.equals(QsbkApp.currentUser.gender)) && ((TextUtils.isEmpty(this.H) || this.H.equals(QsbkApp.currentUser.hometown)) && ((TextUtils.isEmpty(this.O) || this.O.equalsIgnoreCase(QsbkApp.currentUser.job)) && this.I.equalsIgnoreCase(QsbkApp.currentUser.mobileBrand) && this.K.equalsIgnoreCase(QsbkApp.currentUser.haunt) && this.G.equalsIgnoreCase(QsbkApp.currentUser.userName) && (TextUtils.isEmpty(this.E) || this.E.equalsIgnoreCase(QsbkApp.currentUser.emotion)))))) ? false : true;
    }

    protected void a(Bundle bundle) {
        if (bundle != null) {
            this.n = new UserHeaderHelper(this, bundle);
            this.s = new BigCoverHelper(this, bundle);
        } else {
            this.n = new UserHeaderHelper(this, null);
            this.s = new BigCoverHelper(this, null);
        }
        Log.e(a, "on InfoCompleteActivity " + QsbkApp.currentUser);
        this.F = QsbkApp.currentUser.birthday * 1000;
        this.D = QsbkApp.currentUser.gender;
        this.E = QsbkApp.currentUser.emotion;
        this.H = QsbkApp.currentUser.hometown;
        this.I = QsbkApp.currentUser.mobileBrand;
        this.K = QsbkApp.currentUser.haunt;
        this.O = QsbkApp.currentUser.job;
        this.G = QsbkApp.currentUser.userName;
        LogUtil.e(String.format("this.mSettedBirth: %s, setted sex is: %s, setted hometown %s, setted job %s , setted username %s", new Object[]{Long.valueOf(this.F), this.D, this.H, this.O, this.G}));
        LogUtil.e("mSettedEmotion ====" + this.E);
        setActionbarBackable();
        f();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.n.onSaveInstanceState(bundle);
        this.s.onSaveInstanceState(bundle);
    }

    protected void onStop() {
        super.onStop();
    }

    public void hideSoftKeyboard() {
        UIHelper.hideKeyboard(this);
    }

    public void scrollViewToBottom() {
        this.o.postDelayed(new z(this), 200);
    }

    protected boolean d() {
        return true;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        if (this.q == null) {
            this.q = Calendar.getInstance();
        }
        this.q.set(i, i2, i3);
        int age = AstrologyUtils.getAge(this.q);
        if (age < 12 || age > 99) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, getResources().getString(R.string.age_invalid), Integer.valueOf(0)).show();
            return;
        }
        this.F = this.q.getTimeInMillis();
        z();
        this.p.setInitialTime(this.F);
        a(this.F);
    }

    public void onSelected(Hometown hometown, Hometown hometown2) {
        if (hometown2 != null) {
            if (hometown2.getProvince().equals(hometown2.getCity())) {
                this.H = hometown2.getProvince();
            } else {
                this.H = String.format(DOT_FORMAT, new Object[]{hometown2.getProvince(), hometown2.getCity()});
            }
            z();
            h(this.H);
        }
    }

    public void onCancel(Hometown hometown) {
    }

    public void onUploading(long j, long j2) {
        b(j2 == 0 ? 0 : (int) ((98 * j) / j2));
    }

    public void onSuccess(Uri uri, String str) {
        Log.e(a, "onSuccess " + uri);
        I();
        d(this.Q.toString());
        QsbkApp.currentUser.bigCover = str;
        setResult(-1);
    }

    public void onFail(int i, String str) {
        I();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        if (i == 9999) {
            K();
        }
    }

    private void l(String str) {
        new Builder(this).setTitle(R.string.nearby_pop_title).setMessage("上传头像失败，是否重试？").setPositiveButton(R.string.retry, new ab(this, str)).setNegativeButton(R.string.app_cancel, new aa(this)).show();
    }

    private void K() {
        new Builder(this).setTitle(R.string.nearby_pop_title).setMessage("上传大罩失败，是否重试？").setPositiveButton(R.string.retry, new ad(this)).setNegativeButton(R.string.app_cancel, new ac(this)).show();
    }
}
