package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils.TruncateAt;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.GroupAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class SearchGroupActivity extends BaseActionBarActivity implements LocationCallBack, PtrListener {
    private EditText a;
    private ArrayList<Object> b;
    private ArrayList<String> c;
    private a d;
    private GroupAdapter e;
    private String f;
    private int g = 1;
    private HttpTask h;
    private PtrLayout i;
    private ListView j;
    private GridView k;
    private View l;
    private View m;
    private View n;
    private LocationHelper o;
    private boolean p = false;
    private String q = null;
    private TextView r;

    private class a extends BaseAdapter {
        final /* synthetic */ SearchGroupActivity a;

        private a(SearchGroupActivity searchGroupActivity) {
            this.a = searchGroupActivity;
        }

        public int getCount() {
            return this.a.c == null ? 0 : this.a.c.size();
        }

        public String getItem(int i) {
            return (String) this.a.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new TextView(this.a);
                view.setTextSize(1, 14.0f);
                view.setBackgroundDrawable(viewGroup.getResources().getDrawable(R.drawable.search_hotword_bg));
                int i2 = (int) (10.0f * this.a.getResources().getDisplayMetrics().density);
                view.setPadding(0, i2, 0, i2);
                view.setGravity(1);
                view.setSingleLine();
                view.setEllipsize(TruncateAt.END);
            } else {
                TextView textView = (TextView) view;
            }
            view.setBackgroundDrawable(UIHelper.isNightTheme() ? viewGroup.getResources().getDrawable(R.drawable.search_hotword_bg_night) : viewGroup.getResources().getDrawable(R.drawable.search_hotword_bg));
            view.setText((CharSequence) this.a.c.get(i));
            view.setTextColor(UIHelper.isNightTheme() ? -8882028 : -6908266);
            return view;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SearchGroupActivity.class));
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setCustomView(R.layout.widget_search_view);
        supportActionBar.setDisplayShowCustomEnabled(true);
        View findViewById = findViewById(16908332);
        int i = (int) (6.0f * getResources().getDisplayMetrics().density);
        if (findViewById != null) {
            findViewById.setPadding(0, 0, i, 0);
        }
        this.o = new LocationHelper((Context) this);
        g();
        i();
        l();
    }

    private void g() {
        if (this.o.isLoaded()) {
            this.p = true;
            return;
        }
        this.p = false;
        this.o.startLocate(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.o != null) {
            this.o.distory();
            this.o = null;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            UIHelper.hideKeyboard(this);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void i() {
        int i;
        int i2 = -1;
        int i3 = -1184275;
        int i4 = -8882028;
        int i5 = -14803421;
        ((ImageView) findViewById(R.id.group_search_iv)).setImageResource(UIHelper.isNightTheme() ? R.drawable.group_search_night : R.drawable.group_search);
        this.a = (EditText) findViewById(R.id.search_text);
        EditText editText = this.a;
        if (UIHelper.isNightTheme()) {
            i = -12105387;
        } else {
            i = -1;
        }
        editText.setHintTextColor(i);
        EditText editText2 = this.a;
        if (UIHelper.isNightTheme()) {
            i2 = -8882028;
        }
        editText2.setTextColor(i2);
        this.a.setOnEditorActionListener(new abl(this));
        findViewById(R.id.search_clear).setOnClickListener(new abm(this));
        findViewById(R.id.search_clear).setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_search_clear_night : R.drawable.group_search_clear);
        this.i = (PtrLayout) findViewById(R.id.ptr);
        this.i.setBackgroundColor(UIHelper.isNightTheme() ? UIHelper.getColor(R.color.main_bg_night) : UIHelper.getColor(R.color.white));
        this.j = (ListView) findViewById(R.id.listview);
        ListView listView = this.j;
        if (UIHelper.isNightTheme()) {
            i = -14803421;
        } else {
            i = -1184275;
        }
        listView.setBackgroundColor(i);
        this.l = findViewById(R.id.search_words_box);
        this.k = (GridView) findViewById(R.id.search_words);
        this.m = findViewById(R.id.id_loading_div);
        this.n = findViewById(R.id.empty_tips);
        this.r = (TextView) findViewById(R.id.search_hot_words);
        View view = this.l;
        if (UIHelper.isNightTheme()) {
            i3 = -14803421;
        }
        view.setBackgroundColor(i3);
        GridView gridView = this.k;
        if (!UIHelper.isNightTheme()) {
            i5 = 268435455;
        }
        gridView.setBackgroundColor(i5);
        TextView textView = this.r;
        if (!UIHelper.isNightTheme()) {
            i4 = -6908266;
        }
        textView.setTextColor(i4);
        this.b = new ArrayList();
        this.e = new GroupAdapter(this.b, this, 1);
        this.j.setAdapter(this.e);
        this.i.setPtrListener(this);
        this.j.setEmptyView(this.n);
        this.j.setOnItemClickListener(new abn(this));
        this.d = new a();
        this.k.setAdapter(this.d);
        this.k.setOnItemClickListener(new abo(this));
    }

    private void j() {
        this.n.setVisibility(8);
    }

    protected boolean d() {
        return true;
    }

    public void showLoading() {
        super.showLoading();
        this.m.setVisibility(0);
    }

    public void hideLoading() {
        super.hideLoading();
        this.m.setVisibility(8);
    }

    private void k() {
        if (this.h != null) {
            this.h.cancel(true);
            this.h = null;
        }
    }

    private void a(String str) {
        this.g = 1;
        this.f = str;
        a(str, this.g, true);
    }

    private void a(String str, int i, boolean z) {
        k();
        j();
        if (z) {
            this.l.setVisibility(8);
            this.i.setLoadMoreEnable(false);
            showLoading();
        }
        if (this.p) {
            this.q = null;
            String str2 = Constants.URL_SEARCH_GROUP;
            r1 = new Object[4];
            LocationHelper locationHelper = this.o;
            r1[2] = Double.valueOf(LocationHelper.getLongitude());
            locationHelper = this.o;
            r1[3] = Double.valueOf(LocationHelper.getLatitude());
            str2 = String.format(str2, r1);
            this.h = new HttpTask(str2, str2, new abp(this, z, i));
            this.h.execute(new Void[0]);
            return;
        }
        this.q = str;
    }

    private void l() {
        showLoading();
        this.l.setVisibility(0);
        String format = String.format(Constants.URL_SEARCH_HOT_WORDS, new Object[]{Integer.valueOf(12), Integer.valueOf(1)});
        new HttpTask(format, format, new abq(this)).execute(new Void[0]);
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected String f() {
        return null;
    }

    protected int a() {
        return R.layout.activity_search_group;
    }

    public void onRefresh() {
        a(this.f);
    }

    public void onLoadMore() {
        a(this.f, this.g, false);
    }

    private void m() {
        if (this.o != null && this.q != null) {
            a(this.q);
        }
    }

    public void onLocateFail(int i) {
        this.p = true;
        m();
    }

    public void onLocateDone(double d, double d2, String str, String str2) {
        this.p = true;
        m();
    }
}
