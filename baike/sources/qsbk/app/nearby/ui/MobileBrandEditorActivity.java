package qsbk.app.nearby.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.http.HttpTask;
import qsbk.app.model.MobileBrand;
import qsbk.app.nearby.ui.JobEditorActivity.JobEditorAdapter.JobItemViewHolder;
import qsbk.app.utils.UIHelper;

public class MobileBrandEditorActivity extends BaseActionBarActivity implements OnItemClickListener {
    public static final String MOBILE_BRAND_EDIT_RESULT = "mobile_brand_result";
    ListView a = null;
    MobileBrand b = null;
    MobileBrandEditorAdapter c;

    public static final class MobileBrandEditorAdapter extends BaseAdapter {
        private int a = -1;
        private List<MobileBrand> b;
        private Context c;
        private LayoutInflater d;

        public static class MobileBrandItemViewHolder {
            private TextView a;
            private CheckBox b;

            public static MobileBrandItemViewHolder get(View view) {
                Object tag = view.getTag();
                if (tag != null && (tag instanceof JobItemViewHolder)) {
                    return (MobileBrandItemViewHolder) tag;
                }
                MobileBrandItemViewHolder mobileBrandItemViewHolder = new MobileBrandItemViewHolder();
                mobileBrandItemViewHolder.b = (CheckBox) view.findViewById(R.id.checkbox);
                mobileBrandItemViewHolder.a = (TextView) view.findViewById(R.id.title);
                view.setTag(mobileBrandItemViewHolder);
                return mobileBrandItemViewHolder;
            }
        }

        public MobileBrandEditorAdapter(Context context, List<MobileBrand> list) {
            this.c = context;
            this.b = list;
            this.d = LayoutInflater.from(this.c);
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public void setChecked(int i) {
            if (i >= 0 && i < getCount() && this.a != i) {
                this.a = i;
                notifyDataSetChanged();
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            boolean z;
            if (view == null) {
                view = this.d.inflate(R.layout.edit_job_item, viewGroup, false);
            }
            MobileBrandItemViewHolder mobileBrandItemViewHolder = MobileBrandItemViewHolder.get(view);
            mobileBrandItemViewHolder.a.setText(((MobileBrand) this.b.get(i)).getName());
            CheckBox b = mobileBrandItemViewHolder.b;
            if (i == this.a) {
                z = true;
            } else {
                z = false;
            }
            b.setChecked(z);
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Nearby_Night);
        } else {
            setTheme(R.style.Nearby_Day);
        }
    }

    protected String f() {
        return getString(R.string.mobile_brand);
    }

    protected int a() {
        return R.layout.activity_edit_job;
    }

    private void g() {
        if (this.b == null) {
            setResult(0);
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(MOBILE_BRAND_EDIT_RESULT, this.b);
        setResult(-1, intent);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        g();
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        g();
        return true;
    }

    protected void a(Bundle bundle) {
        String str;
        setActionbarBackable();
        this.a = (ListView) findViewById(R.id.listview);
        this.a.setOnItemClickListener(this);
        try {
            str = Constants.URL_MOBILE_BRAND_LIST + "?mobile_brand=" + URLEncoder.encode(Build.MODEL.toLowerCase(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = null;
        }
        a(Constants.URL_MOBILE_BRAND_LIST, str, null);
        findViewById(R.id.loadingbar).setVisibility(0);
    }

    private void a(String str, String str2, Map<String, Object> map) {
        HttpTask httpTask = new HttpTask(str, str2, new am(this));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.b = (MobileBrand) this.c.getItem(i);
        this.c.setChecked(i);
    }
}
