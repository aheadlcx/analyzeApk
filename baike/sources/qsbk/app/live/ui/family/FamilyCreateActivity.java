package qsbk.app.live.ui.family;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.r0adkll.slidr.Slidr;
import com.soundcloud.android.crop.Crop;
import java.io.File;
import java.lang.Character.UnicodeBlock;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.upload.NormalUpload;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.PictureGetHelper;
import qsbk.app.core.utils.TextLengthFilter;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.SimpleDialog.Builder;
import qsbk.app.core.widget.UserPicSelectDialog;
import qsbk.app.live.R;
import qsbk.app.live.widget.FamilyLevelView;

public class FamilyCreateActivity extends BaseActivity {
    protected String a = null;
    protected String b = null;
    protected String c = null;
    protected PictureGetHelper d;
    private EditText e;
    private EditText f;
    private EditText g;
    private LinearLayout h;
    private SimpleDraweeView i;
    private FamilyLevelView j;
    private TextView k;
    private UserPicSelectDialog l;
    private User m;
    private int n = 0;
    private long o;
    private String p;
    private String q;
    private String r;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = new PictureGetHelper(this, bundle);
    }

    protected int getLayoutId() {
        return R.layout.activity_family_create;
    }

    protected void initView() {
        this.e = (EditText) $(R.id.et_family_name);
        this.f = (EditText) $(R.id.et_family_badge);
        this.g = (EditText) $(R.id.et_family_notice);
        this.h = (LinearLayout) $(R.id.ll_create);
        this.i = (SimpleDraweeView) $(R.id.iv_avatar);
        this.j = (FamilyLevelView) $(R.id.fl_level);
        this.k = (TextView) $(R.id.tv_create);
        setUp();
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.o = intent.getLongExtra("familyId", 0);
            this.b = intent.getStringExtra("familyAvatar");
            this.p = intent.getStringExtra("famillyName");
            this.q = intent.getStringExtra("familyBadge");
            this.r = intent.getStringExtra("familyNotice");
            if (this.b != null) {
                AppUtils.getInstance().getImageProvider().loadAvatar(this.i, this.b, 5);
                this.e.setText(this.p);
                this.f.setText(this.q);
                this.g.setText(this.r);
                this.j.setLevelAndName(1, this.q);
                this.n = 1;
                this.k.setText(R.string.family_edit_save);
                setTitle(getString(R.string.family_edit));
            } else {
                setTitle(getResources().getString(R.string.family_create_title));
                this.k.setText(getString(R.string.family_create_con));
            }
        }
        this.m = AppUtils.getInstance().getUserInfoProvider().getUser();
        this.f.addTextChangedListener(new e(this));
        this.h.setOnClickListener(new f(this));
        this.i.setOnClickListener(new h(this));
        if (this.n == 0) {
            a();
        }
    }

    private void a() {
        NetRequest.getInstance().get(UrlConstants.FAMILY_CREATE_DIAMOND, new i(this));
    }

    private void b() {
        Builder jVar = new j(this, R.style.SimpleDialog);
        jVar.message(getString(R.string.live_balance_not_sufficient_family_create_hint)).positiveAction(getString(R.string.live_charge)).negativeAction(getString(R.string.setting_cancel));
        AppUtils.showDialogFragment(this, jVar);
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        int i = 0;
        while (i < str.length()) {
            if (!a(str.charAt(i)) && !b(str.charAt(i))) {
                return true;
            }
            i++;
        }
        return false;
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!b(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean a(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean b(char c) {
        UnicodeBlock of = UnicodeBlock.of(c);
        if (of == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || of == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || of == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || of == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
            return true;
        }
        return false;
    }

    private int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int chineseCount = TextLengthFilter.getChineseCount(str);
        return chineseCount + (((str.length() - chineseCount) + 1) / 2);
    }

    public void submitAvatar(String str) {
        obtainUploadToken();
    }

    public void obtainUploadToken() {
        showSavingDialog(getString(R.string.user_avatar_uploading));
        String str = UrlConstants.FAMILY_CREATE_CREST_TOKEN;
        if (this.n == 1) {
            str = UrlConstants.FAMILY_UPDATE_CREST_TOKEN;
        }
        NetRequest.getInstance().post(str, new k(this), "getUploadToken", true);
    }

    public void uploadAvatarToQiniu(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("user_id", Long.toString(this.m.getPlatformId()));
        hashMap.put("token", AppUtils.getInstance().getUserInfoProvider().getToken());
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        if (this.n == 1) {
            hashMap.put("family_id", this.o + "");
        }
        NormalUpload normalUpload = new NormalUpload();
        normalUpload.addUploadListener(new l(this, str2, str));
        normalUpload.uploadFile(str, str2, str3, hashMap);
    }

    private void c() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            this.l = new UserPicSelectDialog(this, this.d);
            this.l.show();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            super.onActivityResult(i, i2, intent);
            switch (i) {
                case 0:
                    a(this.d.getCapturedUri());
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
                    Object path = Crop.getOutput(intent).getPath();
                    if (!TextUtils.isEmpty(path)) {
                        this.a = path;
                        submitAvatar(this.a);
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
        Crop.of(uri, Uri.fromFile(file)).asSquare().withMaxSize(PictureGetHelper.IMAGE_SIZE, PictureGetHelper.IMAGE_SIZE).start(this);
    }
}
