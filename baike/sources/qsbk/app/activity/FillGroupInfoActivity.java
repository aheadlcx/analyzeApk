package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class FillGroupInfoActivity extends BaseCreateGroupActivity implements LocationCallBack {
    String a = "FillGroupInfoActivity";
    TextView b;
    EditText c;
    EditText d;
    TextView e;
    String f;
    String g;
    Double h;
    Double i;
    private ImageView j;
    private ScrollView k;
    private TextView l;
    private TextView m;
    public Uri mImageUri = null;
    private TextView n;
    private TextView o;
    private LocationHelper p;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected String e() {
        return "建群";
    }

    protected int a() {
        return R.layout.activity_fill_group_info;
    }

    protected void a(Bundle bundle) {
        int i;
        int i2 = -9802637;
        int i3 = -12171438;
        setActionbarBackable();
        this.k = (ScrollView) findViewById(R.id.fill_group_scrollview);
        this.k.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
        this.l = (TextView) findViewById(R.id.group_remind);
        TextView textView = this.l;
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -6908266;
        }
        textView.setTextColor(i);
        this.m = (TextView) findViewById(R.id.create_group_name_remind);
        textView = this.m;
        if (UIHelper.isNightTheme()) {
            i = -9802637;
        } else {
            i = -10263970;
        }
        textView.setTextColor(i);
        this.n = (TextView) findViewById(R.id.create_group_location_remind);
        textView = this.n;
        if (UIHelper.isNightTheme()) {
            i = -9802637;
        } else {
            i = -10263970;
        }
        textView.setTextColor(i);
        this.c = (EditText) findViewById(R.id.group_name);
        EditText editText = this.c;
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -10263970;
        }
        editText.setHintTextColor(i);
        editText = this.c;
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -6908266;
        }
        editText.setTextColor(i);
        this.c.addTextChangedListener(new kp(this));
        this.d = (EditText) findViewById(R.id.group_intruduction);
        this.d.setHintTextColor(UIHelper.isNightTheme() ? -12171438 : -10263970);
        editText = this.d;
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -6908266;
        }
        editText.setTextColor(i);
        this.d.addTextChangedListener(new kq(this));
        this.e = (TextView) findViewById(R.id.group_location);
        TextView textView2 = this.e;
        if (!UIHelper.isNightTheme()) {
            i3 = -6908266;
        }
        textView2.setTextColor(i3);
        this.j = (ImageView) findViewById(R.id.group_img);
        this.j.setOnClickListener(new kr(this));
        this.b = (TextView) findViewById(R.id.confirm_group_tv);
        this.b.setOnClickListener(new ks(this));
        this.o = (TextView) findViewById(R.id.create_group_introduce_remind);
        textView2 = this.o;
        if (!UIHelper.isNightTheme()) {
            i2 = -10263970;
        }
        textView2.setTextColor(i2);
        k();
        j();
    }

    private void i() {
        new Builder(this).setTitle("上传群头像").setItems(new String[]{"拍照", "相册"}, new ku(this)).setNegativeButton("取消", new kt(this)).show();
    }

    private void j() {
        if (this.p == null) {
            this.p = new LocationHelper((Context) this);
        }
        this.p.startLocate(this);
    }

    private void k() {
        int i = -5066062;
        this.b.setVisibility(0);
        if (this.q && this.r && this.t && this.s) {
            TextView textView = this.b;
            if (!UIHelper.isNightTheme()) {
                i = -1;
            }
            textView.setTextColor(i);
            this.b.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.used_btn_yellow_night : R.drawable.used_btn_yellow);
            return;
        }
        textView = this.b;
        if (!UIHelper.isNightTheme()) {
            i = -6908266;
        }
        textView.setTextColor(i);
        this.b.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.not_used_button_night : R.drawable.not_used_button);
    }

    protected boolean f() {
        if (this.j.getDrawable() == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您尚未选择图片，请重试").show();
            return false;
        } else if (this.mImageUri == null || "".equals(this.mImageUri)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您尚未选择图片，请重试").show();
            return false;
        } else {
            String trim = this.c.getText().toString().trim();
            String trim2 = this.d.getText().toString().trim();
            if (this.e.getText().toString().trim().equals("")) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "您的位置不能为空").show();
                return false;
            } else if (trim.equals("")) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "群组名称不能为空").show();
                return false;
            } else if (trim.contains("\"") || trim.contains("'")) {
                LogUtil.d("groupName invalid");
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "群组名不能包含特殊字符").show();
                return false;
            } else if (trim2.equals("")) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "群组介绍不能为空").show();
                return false;
            } else if (!trim2.contains("\"") && !trim2.contains("'")) {
                return true;
            } else {
                LogUtil.d("groupName invalid");
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "群组介绍不能包含特殊字符").show();
                return false;
            }
        }
    }

    protected void g() {
        this.mImageUri = Uri.fromFile(new File(l()));
        if (Environment.getExternalStorageState().equals("mounted")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", this.mImageUri);
            startActivityForResult(intent, 4);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "没有SD卡...").show();
    }

    protected void h_() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setType("image/*");
            startActivityForResult(intent, 3);
            return;
        }
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "没有SD卡...").show();
    }

    private Bitmap a(Uri uri) {
        Bitmap bitmap = null;
        if (uri != null) {
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private String l() {
        File file = new File(SettingPersonalBigCoverBaseActivity.PHOTO_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
        return new File(SettingPersonalBigCoverBaseActivity.PHOTO_DIR, "Group_" + SystemClock.uptimeMillis() + ".jpg").getAbsolutePath();
    }

    public void savePickedBitmap(Intent intent) {
        if (intent != null) {
            Bitmap a;
            String saveDrawable;
            Bundle extras = intent.getExtras();
            if (extras == null || extras.get("data") == null) {
                a = a(intent.getData());
                if (a == null) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "选择的图片为空").show();
                    return;
                }
            }
            a = (Bitmap) extras.get("data");
            String l = l();
            if (QsbkApp.currentUser != null) {
                saveDrawable = FileUtils.saveDrawable(a, QsbkApp.currentUser.userId, l);
            } else {
                saveDrawable = FileUtils.saveDrawable(a, "avatar", l);
            }
            FrescoImageloader.displayImage(this.j, "file://" + saveDrawable, R.drawable.group_img_light, 0, true);
            this.mImageUri = Uri.fromFile(new File(saveDrawable));
            this.q = true;
            k();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.p != null) {
            this.p.onActivityResult(i, i2, intent);
        }
        switch (i) {
            case 3:
                if (intent != null) {
                    b(intent.getData());
                    return;
                }
                return;
            case 4:
                b(this.mImageUri);
                return;
            case 5:
                savePickedBitmap(intent);
                return;
            default:
                return;
        }
    }

    private void b(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("outputFormat", CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 5);
    }

    private void m() {
        if (this.f != null && this.g != null && !"".equals(this.f) && !"".equals(this.g)) {
            this.t = true;
            k();
            this.e.setText(this.f + "·" + this.g);
        }
    }

    public void onLocateFail(int i) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "获取地理位置失败").show();
    }

    public void onLocateDone(double d, double d2, String str, String str2) {
        this.f = str2;
        this.g = str;
        this.h = Double.valueOf(d);
        this.i = Double.valueOf(d2);
        m();
    }

    protected void onDestroy() {
        if (this.p != null) {
            this.p.distory();
        }
        super.onDestroy();
    }
}
