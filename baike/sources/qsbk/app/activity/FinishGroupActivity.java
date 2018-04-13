package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.api.BigCoverHelper.UploadListener;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class FinishGroupActivity extends BaseCreateGroupActivity implements UploadListener {
    String a = "FinishGroupActivity";
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    String f;
    String g;
    String h;
    String i;
    String j;
    String k;
    Double l;
    Double m;
    String n;
    private RelativeLayout o;
    private ImageView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private ProgressDialog t = null;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected String e() {
        return "建群";
    }

    protected int a() {
        return R.layout.activity_finish_group;
    }

    protected void a(Bundle bundle) {
        int i;
        int i2 = -1;
        int i3 = -9802626;
        int i4 = -12171438;
        setActionbarBackable();
        Bundle extras = getIntent().getExtras();
        this.f = extras.getString("groupName");
        this.g = extras.getString("groupLocationCity");
        this.h = extras.getString("groupLocationDistrict");
        this.i = extras.getString("groupIntruduction");
        this.j = extras.getString("imgUrlStr");
        LogUtil.d(this.j);
        this.k = extras.getString("redirect");
        this.l = Double.valueOf(extras.getDouble(ParamKey.LATITUDE));
        this.m = Double.valueOf(extras.getDouble(ParamKey.LONGITUDE));
        Uri parse = Uri.parse(this.j);
        this.o = (RelativeLayout) findViewById(R.id.finish_group_rel);
        this.o.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
        this.q = (TextView) findViewById(R.id.finish_group_name_remind);
        this.r = (TextView) findViewById(R.id.finish_group_location_remind);
        this.s = (TextView) findViewById(R.id.finish_group_introduce_remind);
        TextView textView = this.q;
        if (UIHelper.isNightTheme()) {
            i = -9802626;
        } else {
            i = -10263970;
        }
        textView.setTextColor(i);
        textView = this.r;
        if (UIHelper.isNightTheme()) {
            i = -9802626;
        } else {
            i = -10263970;
        }
        textView.setTextColor(i);
        TextView textView2 = this.s;
        if (!UIHelper.isNightTheme()) {
            i3 = -10263970;
        }
        textView2.setTextColor(i3);
        this.b = (TextView) findViewById(R.id.show_group_name);
        this.c = (TextView) findViewById(R.id.show_group_intruduction);
        this.d = (TextView) findViewById(R.id.show_group_location);
        this.p = (ImageView) findViewById(R.id.show_group_img);
        this.e = (TextView) findViewById(R.id.finish_group_tv);
        textView2 = this.e;
        if (UIHelper.isNightTheme()) {
            i2 = -5066062;
        }
        textView2.setTextColor(i2);
        this.e.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.used_btn_yellow_night : R.drawable.used_btn_yellow);
        TextView textView3 = this.b;
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -6908266;
        }
        textView3.setTextColor(i);
        textView3 = this.c;
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -6908266;
        }
        textView3.setTextColor(i);
        textView2 = this.d;
        if (!UIHelper.isNightTheme()) {
            i4 = -6908266;
        }
        textView2.setTextColor(i4);
        this.b.setText(this.f);
        this.c.setText(this.i);
        this.d.setText(this.g + "·" + this.h);
        this.p.setImageURI(parse);
        this.e.setOnClickListener(new lg(this));
    }

    protected void f() {
        this.t = new ProgressDialog(this);
        this.t.setCancelable(false);
        this.t.setIndeterminate(true);
        this.t.setTitle("正在建群中，请稍候...");
        this.t.setProgressStyle(0);
        this.t.show();
        upload(this, Uri.parse(this.j));
    }

    private void a(String str) {
        new Builder(this).setTitle(R.string.nearby_pop_title).setMessage(str + ",是否重试？").setPositiveButton(R.string.retry, new li(this)).setNegativeButton(R.string.app_cancel, new lh(this)).show();
    }

    public void upload(UploadListener uploadListener, Uri uri) {
        new lj(this, uploadListener, uri).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    private UploadTaskExecutor a(String str, Uri uri, UploadListener uploadListener) {
        String str2 = IO.UNDEFINED_KEY;
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(str);
        return IO.putFile(QsbkApp.getInstance().getApplicationContext(), authorizer, str2, uri, putExtra, new lk(this, str, uri, uploadListener));
    }

    protected void a(Uri uri) {
        Map hashMap = new HashMap();
        hashMap.put("name", this.f);
        hashMap.put("description", this.i);
        hashMap.put("location", this.g + "·" + this.h);
        hashMap.put("icon", this.n);
        hashMap.put(ParamKey.LATITUDE, this.l.toString());
        hashMap.put(ParamKey.LONGITUDE, this.m.toString());
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.URL_CREATE_GROUP, new ll(this));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onUploading(long j, long j2) {
    }

    public void onSuccess(Uri uri, String str) {
        Log.e(this.a, "onSuccess " + uri);
        this.t.dismiss();
    }

    public void onFail(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.t.dismiss();
        if (i == 9999) {
            g();
        }
    }

    private void g() {
        new Builder(this).setTitle(R.string.nearby_pop_title).setMessage("上传头像失败，是否重试？").setPositiveButton(R.string.retry, new ln(this)).setNegativeButton(R.string.app_cancel, new lm(this)).show();
    }
}
