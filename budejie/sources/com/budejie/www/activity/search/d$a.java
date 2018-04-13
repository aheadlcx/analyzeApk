package com.budejie.www.activity.search;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.type.SearchResult;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tencent.connect.common.Constants;
import java.util.List;
import net.tsz.afinal.a.a;

class d$a extends a<String> {
    final /* synthetic */ d a;
    private int b;

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    private d$a(d dVar, int i) {
        this.a = dVar;
        this.b = 1;
        this.b = i;
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        if (this.a.isAdded()) {
            this.a.c();
        }
    }

    public void a(String str) {
        if (this.a.isAdded()) {
            SearchResult b;
            this.a.c();
            this.a.c.setVisibility(0);
            SearchResult searchResult = null;
            try {
                b = b(str);
            } catch (Exception e) {
                e.printStackTrace();
                b = searchResult;
            }
            if (b != null) {
                if (!b.getCode().equals(Constants.DEFAULT_UIN) || b.getList() == null) {
                    String msg = b.getMsg();
                    if (TextUtils.isEmpty(msg)) {
                        msg = "未找到您搜索的数据";
                    }
                    this.a.c.setVisibility(8);
                    this.a.a(msg);
                } else {
                    List list = b.getList();
                    if (this.b == 1) {
                        d.c(this.a).b(list);
                    } else {
                        d.c(this.a).a(list);
                    }
                    d.a(this.a, this.b);
                }
                if ("1".equals(b.getMore())) {
                    this.a.c.setPullLoadEnable(true);
                    return;
                } else {
                    this.a.c.setPullLoadEnable(false);
                    return;
                }
            }
            this.a.c.setVisibility(8);
            this.a.a(this.a.getString(R.string.search_error));
        }
    }

    private SearchResult b(String str) throws JsonSyntaxException {
        return (SearchResult) new Gson().fromJson(str, SearchResult.class);
    }
}
