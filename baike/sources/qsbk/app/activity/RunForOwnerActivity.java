package qsbk.app.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.alipay.sdk.cons.b;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CampaignInfo;
import qsbk.app.utils.UIHelper;

public class RunForOwnerActivity extends BaseActionBarActivity implements OnClickListener {
    private CampaignInfo a;
    private View b;
    private TextView c;
    private View d;
    private View e;
    private int f;
    private View g;
    private TextView h;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context, int i, CampaignInfo campaignInfo) {
        Intent intent = new Intent(context, RunForOwnerActivity.class);
        intent.putExtra("id", i);
        intent.putExtra("info", campaignInfo);
        context.startActivity(intent);
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected boolean d() {
        return true;
    }

    protected String f() {
        return "群大竞选";
    }

    protected int a() {
        return R.layout.layout_run_for_owner;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.f = getIntent().getIntExtra("id", -1);
        this.a = (CampaignInfo) getIntent().getSerializableExtra("info");
        g();
        i();
        j();
    }

    private void g() {
        this.b = findViewById(R.id.manifesto_layout);
        this.c = (TextView) findViewById(R.id.manifesto);
        this.d = findViewById(R.id.manifesto_modify);
        this.h = (TextView) findViewById(R.id.end_time);
        this.e = findViewById(R.id.run_for_owner_btn);
        this.g = findViewById(R.id.quit_owner_btn);
    }

    private void i() {
        if (this.a == null) {
            showLoading();
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.show();
            new SimpleHttpTask(String.format(Constants.URL_TRANSITION, new Object[]{Integer.valueOf(this.f)}) + "?tid=" + this.f, new abg(this, progressDialog)).execute();
        }
    }

    private void j() {
        if (this.a == null) {
            this.b.setVisibility(8);
            this.e.setVisibility(8);
            return;
        }
        this.h.setText(new SimpleDateFormat("报名截止时间：MM月dd日", Locale.US).format(new Date(((long) this.a.endTime) * 1000)));
        if (this.a.hasCampaigned) {
            this.b.setVisibility(0);
            this.c.setText(this.a.manifesto);
            this.d.setOnClickListener(this);
            this.e.setVisibility(8);
            this.g.setOnClickListener(this);
            return;
        }
        this.b.setVisibility(8);
        this.e.setVisibility(0);
        this.e.setOnClickListener(this);
    }

    private void k() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在处理中...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String format = String.format(Constants.URL_QUIT_FOR_OWNER, new Object[]{Integer.valueOf(this.f)});
        Map hashMap = new HashMap();
        hashMap.put(b.c, String.valueOf(this.f));
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new abh(this, progressDialog));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    private void l() {
        new abi(this, this).show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manifesto_modify:
            case R.id.run_for_owner_btn:
                ApplyForOwnerActivity.launchForResult((Context) this, this.f, this.a != null ? this.a.manifesto : "");
                return;
            case R.id.quit_owner_btn:
                l();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == ApplyForOwnerActivity.REQ_APPLY && i2 == 1) {
            this.a.hasCampaigned = true;
            this.a.manifesto = intent.getStringExtra("text");
            j();
        }
    }
}
