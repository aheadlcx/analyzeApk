package qsbk.app.live.ui.family;

import android.support.v7.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.R;
import qsbk.app.live.adapter.FamilyAllAnchoradapter;
import qsbk.app.live.model.FamilyAnchorData;

public class FamilyAnchorActivity extends FamilyMemberActivity {
    protected List<FamilyAnchorData> a = new ArrayList();
    protected FamilyAllAnchoradapter b;

    protected void a() {
        this.k = new LinearLayoutManager(this, 1, false);
        this.b = new FamilyAllAnchoradapter(this, this.a, this.l);
        this.f.setLayoutManager(this.k);
        this.f.setAdapter(this.b);
    }

    protected void a(BaseResponse baseResponse) {
        Collection listResponse = baseResponse.getListResponse("msg", new a(this));
        if (listResponse == null || listResponse.size() <= 0) {
            this.i = false;
        } else {
            this.i = true;
            if (this.g == 1) {
                this.a.clear();
            }
            for (int i = 0; i < listResponse.size(); i++) {
                ((FamilyAnchorData) listResponse.get(i)).a = baseResponse.parent.optJSONObject("t").optString(((FamilyAnchorData) listResponse.get(i)).t).replace("$", ((FamilyAnchorData) listResponse.get(i)).a);
            }
            this.a.addAll(listResponse);
            c();
        }
        if (this.a == null || this.a.isEmpty()) {
            this.j.setVisibility(0);
            this.j.setTextOnly(getString(R.string.family_no_anchor));
        } else {
            this.j.setVisibility(8);
        }
        this.g++;
        c();
    }

    protected String b() {
        return UrlConstants.FAMILY_ANCHOR_SUPPORT;
    }

    protected void c() {
        this.b.notifyDataSetChanged();
    }

    protected String d() {
        return getResources().getString(R.string.family_support_anchor);
    }
}
