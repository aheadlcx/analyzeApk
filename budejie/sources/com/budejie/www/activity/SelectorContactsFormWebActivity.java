package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.h.c;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.type.SearchItem;
import com.budejie.www.type.SearchResult;
import com.budejie.www.util.an;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tencent.connect.common.Constants;
import java.util.List;

public class SelectorContactsFormWebActivity extends Activity implements OnClickListener {
    private ListView a;
    private Dialog b;
    private Activity c;
    private Toast d;

    public class a extends BaseAdapter {
        final /* synthetic */ SelectorContactsFormWebActivity a;
        private Context b;
        private List<SearchItem> c;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(SelectorContactsFormWebActivity selectorContactsFormWebActivity, Context context, List<SearchItem> list) {
            this.a = selectorContactsFormWebActivity;
            this.b = context;
            this.c = list;
        }

        public int getCount() {
            return this.c.size();
        }

        public SearchItem a(int i) {
            return (SearchItem) this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            SelectorContactsFormWebActivity$a$a selectorContactsFormWebActivity$a$a;
            if (view == null) {
                selectorContactsFormWebActivity$a$a = new SelectorContactsFormWebActivity$a$a(this);
                view = LayoutInflater.from(this.b).inflate(R.layout.selector_contact_item_layout, null);
                selectorContactsFormWebActivity$a$a.b = (AsyncImageView) view.findViewById(R.id.contact_icon);
                selectorContactsFormWebActivity$a$a.a = (TextView) view.findViewById(R.id.contact_name);
                view.setTag(selectorContactsFormWebActivity$a$a);
            } else {
                selectorContactsFormWebActivity$a$a = (SelectorContactsFormWebActivity$a$a) view.getTag();
            }
            selectorContactsFormWebActivity$a$a.a.setText(((SearchItem) this.c.get(i)).getUsername());
            selectorContactsFormWebActivity$a$a.b.setAsyncCacheImage(((SearchItem) this.c.get(i)).getProfileImage(), R.drawable.head_portrait);
            return view;
        }
    }

    private class b extends net.tsz.afinal.a.a<String> {
        final /* synthetic */ SelectorContactsFormWebActivity a;

        private b(SelectorContactsFormWebActivity selectorContactsFormWebActivity) {
            this.a = selectorContactsFormWebActivity;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            super.onStart();
            this.a.b.show();
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.b.cancel();
            this.a.d = an.a(this.a.c, this.a.getString(R.string.search_error), -1);
            this.a.d.show();
        }

        public void a(String str) {
            super.onSuccess(str);
            this.a.b.cancel();
            SearchResult searchResult = null;
            try {
                searchResult = b(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (searchResult == null) {
                this.a.d = an.a(this.a.c, this.a.getString(R.string.search_error), -1);
                this.a.d.show();
            } else if (!searchResult.getCode().equals(Constants.DEFAULT_UIN) || searchResult.getList() == null || searchResult.getList().size() == 0) {
                String msg = searchResult.getMsg();
                if (TextUtils.isEmpty(msg)) {
                    msg = "未找到您搜索的数据";
                }
                this.a.d = an.a(this.a.c, msg, -1);
                this.a.d.show();
            } else {
                List list = searchResult.getList();
                for (int size = list.size() - 1; size >= 0; size--) {
                    SearchItem searchItem = (SearchItem) list.get(size);
                    if (TextUtils.isEmpty(searchItem.getId()) || TextUtils.isEmpty(searchItem.getUsername())) {
                        list.remove(size);
                    }
                }
                this.a.a.setAdapter(new a(this.a, this.a.c, list));
            }
        }

        private SearchResult b(String str) throws JsonSyntaxException {
            return (SearchResult) new Gson().fromJson(str, SearchResult.class);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        setTheme(c.a().b());
        setContentView(R.layout.choose_contacts_from_web_layout);
        findViewById(R.id.cancel_btn).setOnClickListener(this);
        this.a = (ListView) findViewById(R.id.contact_listview);
        this.b = new Dialog(this, R.style.dialogTheme);
        this.b.setContentView(R.layout.loaddialog);
        this.b.setCanceledOnTouchOutside(true);
        this.b.setCancelable(true);
        a(getIntent().getStringExtra(getString(R.string.REQUEST_CONTACT_NAME)));
        this.a.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SelectorContactsFormWebActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.setResult(-1, new Intent().putExtra(this.a.getString(R.string.RESPONE_RESULT_CONTACT_NAME), ((SearchItem) adapterView.getItemAtPosition(i)).getUsername()));
                this.a.finish();
            }
        });
    }

    private synchronized void a(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "http://api.budejie.com/api/api_open.php", new j().e((Context) this, str, "1"), new b());
    }

    public void onClick(View view) {
        finish();
    }
}
