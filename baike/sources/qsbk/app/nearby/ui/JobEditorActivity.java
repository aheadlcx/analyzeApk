package qsbk.app.nearby.ui;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.model.Job;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class JobEditorActivity extends BaseActionBarActivity implements OnItemClickListener {
    public static final String KEY_EDIT_RESULT = "edit_result";
    private ListView a;
    private JobEditorAdapter b;
    private Job c;

    public static final class JobEditorAdapter extends BaseAdapter {
        private int a = -1;
        private List<Job> b;
        private Context c;
        private LayoutInflater d;

        public static class JobItemViewHolder {
            private TextView a;
            private CheckBox b;

            public static JobItemViewHolder get(View view) {
                Object tag = view.getTag();
                if (tag != null && (tag instanceof JobItemViewHolder)) {
                    return (JobItemViewHolder) tag;
                }
                JobItemViewHolder jobItemViewHolder = new JobItemViewHolder();
                jobItemViewHolder.b = (CheckBox) view.findViewById(R.id.checkbox);
                jobItemViewHolder.a = (TextView) view.findViewById(R.id.title);
                view.setTag(jobItemViewHolder);
                return jobItemViewHolder;
            }
        }

        public JobEditorAdapter(Context context, List<Job> list) {
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
            JobItemViewHolder jobItemViewHolder = JobItemViewHolder.get(view);
            jobItemViewHolder.a.setText(((Job) this.b.get(i)).getName());
            CheckBox b = jobItemViewHolder.b;
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
        return getString(R.string.edit_job);
    }

    protected int a() {
        return R.layout.activity_edit_job;
    }

    private void g() {
        if (this.c == null) {
            setResult(0);
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_EDIT_RESULT, this.c.getName());
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
        setActionbarBackable();
        this.a = (ListView) findViewById(R.id.listview);
        this.a.setOnItemClickListener(this);
        List localJobs = NearbyEngine.instance().getLocalJobs(this);
        if (localJobs == null || localJobs.isEmpty()) {
            new al(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加载职业数据失败，请稍后再试。").show();
            finish();
            return;
        }
        this.b = new JobEditorAdapter(this, localJobs);
        this.a.setAdapter(this.b);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.c = (Job) this.b.getItem(i);
        this.b.setChecked(i);
    }
}
