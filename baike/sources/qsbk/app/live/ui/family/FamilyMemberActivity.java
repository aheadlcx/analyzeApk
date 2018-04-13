package qsbk.app.live.ui.family;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.r0adkll.slidr.Slidr;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.live.R;
import qsbk.app.live.adapter.FamilyAllMemberAdapter;
import qsbk.app.live.model.FamilyMemberData;

public class FamilyMemberActivity extends BaseActivity {
    protected FamilyAllMemberAdapter c;
    protected List<FamilyMemberData> d = new ArrayList();
    protected SwipeRefreshLayoutBoth e;
    protected RecyclerView f;
    protected int g = 1;
    protected boolean h = false;
    protected boolean i = true;
    protected EmptyPlaceholderView j;
    protected LinearLayoutManager k;
    protected long l;
    protected int m;
    protected FamilyMemberData n;

    protected int getLayoutId() {
        return R.layout.activity_family_member;
    }

    protected void initView() {
        this.e = (SwipeRefreshLayoutBoth) $(R.id.refresher);
        this.f = (RecyclerView) $(R.id.recyclerview);
        this.j = (EmptyPlaceholderView) $(R.id.empty);
        setUp();
        setTitle(d());
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
    }

    protected String d() {
        return getString(R.string.family_member);
    }

    private void f() {
        if (this.k.getChildCount() + this.k.findFirstVisibleItemPosition() >= this.k.getItemCount() - 1) {
            e();
        }
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.l = intent.getLongExtra("familyId", 0);
            this.m = intent.getIntExtra(HTTP.IDENTITY_CODING, 0);
        }
        a();
        this.e.setOnRefreshListener(new ap(this));
        g();
    }

    protected void a() {
        this.k = new LinearLayoutManager(this, 1, false);
        this.c = new FamilyAllMemberAdapter(this, this.d, this.l);
        this.c.setIdentity(this.m);
        this.f.setLayoutManager(this.k);
        this.f.setAdapter(this.c);
    }

    protected String b() {
        return UrlConstants.FAMILY_MEMBER_WEEK_RANK;
    }

    protected void c() {
        this.c.notifyDataSetChanged();
    }

    private void g() {
        this.g = 1;
        this.e.post(new aq(this));
    }

    protected void a(BaseResponse baseResponse) {
        Collection listResponse = baseResponse.getListResponse("m", new ar(this));
        if (this.g == 1) {
            this.n = (FamilyMemberData) baseResponse.getResponse("l", new as(this));
            if (this.n != null) {
                this.n.a = baseResponse.parent.optJSONObject("t").optString(this.n.t).replace("$", this.n.a);
            }
        }
        if (listResponse == null || listResponse.size() <= 0) {
            this.i = false;
        } else {
            this.i = true;
            if (this.g == 1) {
                this.d.clear();
                if (this.n != null) {
                    this.d.add(this.n);
                }
            }
            if (this.n != null) {
                listResponse.remove(this.n);
            }
            for (int i = 0; i < listResponse.size(); i++) {
                ((FamilyMemberData) listResponse.get(i)).a = baseResponse.parent.optJSONObject("t").optString(((FamilyMemberData) listResponse.get(i)).t).replace("$", ((FamilyMemberData) listResponse.get(i)).a);
            }
            this.d.addAll(listResponse);
            c();
        }
        if (this.d == null || this.d.isEmpty()) {
            this.j.setVisibility(0);
            this.j.setTextOnly(getString(R.string.family_no_member));
        } else {
            this.j.setVisibility(8);
        }
        this.g++;
        c();
    }

    protected void e() {
        this.h = true;
        NetRequest.getInstance().get(b(), new at(this));
    }
}
