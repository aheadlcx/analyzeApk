package qsbk.app.nearby.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.live.ui.LivePushActivity;
import qsbk.app.nearby.api.ILocationCallback;
import qsbk.app.nearby.api.ILocationManager;
import qsbk.app.nearby.api.LocationCache;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class HauntEditorActivity extends BaseActionBarActivity implements OnItemClickListener, ILocationCallback {
    public static final String HAUNT_EDIT_RESULT = "haunt_result";
    public static final String HAUNT_NAME_OF_HIDE = "隐形";
    public static final String HAUNT_NAME_OF_SHOW = "显示我的城区";
    ProgressBar a = null;
    ListView b = null;
    TextView c = null;
    String d = null;
    HauntEditorAdapter e;
    String[] f = null;
    private Location g;
    private ILocationManager h;
    private int i = 0;

    public static final class HauntEditorAdapter extends BaseAdapter {
        private int a = -1;
        private String[] b;
        private Context c;
        private LayoutInflater d;

        public static class ViewHolder {
            private TextView a;
            private CheckBox b;

            public static ViewHolder get(View view) {
                Object tag = view.getTag();
                if (tag != null && (tag instanceof ViewHolder)) {
                    return (ViewHolder) tag;
                }
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.b = (CheckBox) view.findViewById(R.id.checkbox);
                viewHolder.a = (TextView) view.findViewById(R.id.title);
                view.setTag(viewHolder);
                return viewHolder;
            }
        }

        public HauntEditorAdapter(Context context, String[] strArr) {
            this.c = context;
            this.b = strArr;
            this.d = LayoutInflater.from(this.c);
        }

        public int getCount() {
            return this.b.length;
        }

        public String getItem(int i) {
            return this.b[i];
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
            boolean z = false;
            if (view == null) {
                view = this.d.inflate(R.layout.edit_job_item, viewGroup, false);
            }
            ViewHolder viewHolder = ViewHolder.get(view);
            viewHolder.a.setText(this.b[i].replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ""));
            CheckBox b = viewHolder.b;
            if (i == this.a) {
                z = true;
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
        return getString(R.string.haunt);
    }

    protected int a() {
        return R.layout.activity_edit_job;
    }

    private void g() {
        if (this.d == null) {
            setResult(0);
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(HAUNT_EDIT_RESULT, this.d);
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

    protected void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            this.h.remove(this);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.a = (ProgressBar) findViewById(R.id.loadingbar);
        this.b = (ListView) findViewById(R.id.listview);
        this.b.setOnItemClickListener(this);
        if (TextUtils.isEmpty(QsbkApp.currentUser.haunt)) {
            this.a.setVisibility(0);
            this.g = LocationCache.getInstance().getLocation(LivePushActivity.INNER);
            if (this.g == null && this.h == null) {
                this.h = NearbyEngine.instance().getLastLocationManager();
                this.h.getLocation(this);
            }
            i();
        } else {
            this.f = new String[]{QsbkApp.currentUser.haunt, HAUNT_NAME_OF_HIDE};
            this.e = new HauntEditorAdapter(this, this.f);
            this.b.setAdapter(this.e);
        }
        this.c = (TextView) findViewById(R.id.haunt_hint);
        this.c.setVisibility(0);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.d = this.e.getItem(i);
        this.e.setChecked(i);
    }

    private void i() {
        if (this.g == null) {
            this.h.getLocation(this);
        } else {
            j();
        }
    }

    private void j() {
        if (this.g.city == null && this.g.district == null) {
            ToastAndDialog.makeNegativeToast(this, "加载地理位置失败，请稍后重试。").show();
            this.f = new String[]{HAUNT_NAME_OF_SHOW, HAUNT_NAME_OF_HIDE};
        } else {
            String[] strArr = new String[2];
            strArr[0] = String.format(InfoCompleteActivity.DOT_FORMAT, new Object[]{this.g.city, this.g.district});
            strArr[1] = HAUNT_NAME_OF_HIDE;
            this.f = strArr;
        }
        this.a.setVisibility(8);
        this.e = new HauntEditorAdapter(this, this.f);
        this.b.setAdapter(this.e);
    }

    public void onLocation(int i, double d, double d2, String str, String str2) {
        if (i == 161 || i == 0) {
            NearbyEngine.saveLastLocationToLocal(d, d2);
        }
        if (i == 61 || i == 65 || i == 66 || i == 68 || i == 161 || i == 0) {
            if (this.g == null) {
                this.g = new Location();
            }
            this.g.latitude = d;
            this.g.longitude = d2;
            this.g.city = str2;
            this.g.code = i;
            this.g.district = str;
            this.h.remove(this);
            j();
            LocationCache.getInstance().setLocation(this.g);
            return;
        }
        this.i++;
        this.h = NearbyEngine.instance().changeLocationMgr(this.h);
        if (this.i >= 2) {
            this.i = 0;
            Pair lastSavedPosition = NearbyEngine.getLastSavedPosition(true);
            if (lastSavedPosition != null) {
                this.g.latitude = ((Double) lastSavedPosition.first).doubleValue();
                this.g.longitude = ((Double) lastSavedPosition.second).doubleValue();
                return;
            }
            return;
        }
        this.h.getLocation(this);
    }
}
