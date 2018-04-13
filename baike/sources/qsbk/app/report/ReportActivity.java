package qsbk.app.report;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import qsbk.app.R;
import qsbk.app.compat.ThemeCompat;
import qsbk.app.model.ReportBean;
import qsbk.app.utils.UIHelper;

public class ReportActivity extends Activity {
    private ListView a;
    private AlertDialog b;
    private List<ReportBean> c = ReportUtils.RESOURCE;
    private boolean d = false;

    private class a implements OnItemClickListener {
        final /* synthetic */ ReportActivity a;

        private a(ReportActivity reportActivity) {
            this.a = reportActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Integer a = this.a.a(((TextView) view).getText().toString());
            if (a != null) {
                this.a.a(a.intValue());
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.AppTheme.share.new.Dark);
        }
        bindEvent();
    }

    @SuppressLint({"NewApi"})
    public void bindEvent() {
        if (ThemeCompat.preHoneycomb()) {
            setContentView(R.layout.activity_shareactivity_new);
            this.a = (ListView) findViewById(R.id.listview);
            this.a.setBackgroundResource(R.drawable.dialog_holo_light);
            this.a.setPadding(20, 20, 20, 20);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(20, 1, 20, 1);
            this.a.setLayoutParams(layoutParams);
        } else {
            Builder builder = new Builder(this);
            View inflate = LayoutInflater.from(this).inflate(R.layout.activity_shareactivity_new, null);
            this.a = (ListView) inflate.findViewById(R.id.listview);
            builder.setView(inflate);
            this.b = builder.show();
            this.b.setOnCancelListener(new c(this));
        }
        this.a.setOnItemClickListener(new a());
        int size = this.c.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((ReportBean) this.c.get(i)).getName();
        }
        this.a.setAdapter(new ArrayAdapter(this, ThemeCompat.getSimpleListItem(), strArr));
    }

    private Integer a(String str) {
        Integer num = null;
        if (this.c != null && this.c.size() > 0 && str != null && str.trim().length() > 0) {
            int size = this.c.size();
            int i = 0;
            while (i < size) {
                Integer valueOf;
                if (((ReportBean) this.c.get(i)).getName().equalsIgnoreCase(str)) {
                    valueOf = Integer.valueOf(((ReportBean) this.c.get(i)).getValue());
                } else {
                    valueOf = num;
                }
                i++;
                num = valueOf;
            }
        }
        return num;
    }

    private void a(int i) {
        setResult(i, new Intent());
        finish();
    }

    public void finish() {
        if (!this.d) {
            this.d = true;
            if (this.b != null && this.b.isShowing()) {
                this.b.dismiss();
            }
            super.finish();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        finish();
        return super.onTouchEvent(motionEvent);
    }
}
