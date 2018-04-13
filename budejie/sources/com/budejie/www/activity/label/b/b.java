package com.budejie.www.activity.label.b;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.budejie.www.R;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.label.LabelMemberHeaderView;
import com.budejie.www.activity.label.LabelMemberHeaderView$a;
import com.budejie.www.activity.label.enumeration.LabelResponseCode;
import com.budejie.www.activity.label.enumeration.LabelUserEnum;
import com.budejie.www.activity.label.enumeration.PlatePostEnum;
import com.budejie.www.activity.label.response.CommonInfoBean;
import com.budejie.www.activity.label.response.LabelUser;
import com.budejie.www.activity.label.response.LabelUser.ListBean;
import com.budejie.www.b.a;
import com.budejie.www.i.b.d;
import com.budejie.www.type.SearchItem;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.f;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class b extends a implements LabelMemberHeaderView$a, d {
    private View a;
    private XListView b;
    private String c;
    private com.budejie.www.i.a.d d;
    private com.budejie.www.activity.label.a.b e;
    private List<SearchItem> f;
    private LabelMemberHeaderView g;
    private f h;
    private Context i;
    private CommonLabelActivity j;
    private XListView.a k = new b$1(this);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a != null) {
            if (this.a.getParent() != null) {
                ((ViewGroup) this.a.getParent()).removeView(this.a);
            }
            return this.a;
        }
        this.a = layoutInflater.inflate(R.layout.fragment_label_member, viewGroup, false);
        c();
        return this.a;
    }

    private void c() {
        i();
        f();
        d();
        this.b.d();
    }

    public void b() {
        if (this.b != null) {
            this.b.d();
        }
    }

    private void d() {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        if (this.e == null) {
            this.e = new com.budejie.www.activity.label.a.b(getContext(), this.f);
            this.b.setAdapter(this.e);
            return;
        }
        this.e.notifyDataSetChanged();
    }

    private void b(LabelUser labelUser) {
        if (labelUser != null && labelUser.getList() != null) {
            this.g.setStandardText(labelUser.getForum_rule());
            this.g.setVisibility(0);
            Object<SearchItem> master = labelUser.getList().getMaster();
            if (com.budejie.www.goddubbing.c.a.a(master)) {
                this.g.setModeratorData(null);
                return;
            }
            List arrayList = new ArrayList();
            SearchItem searchItem = null;
            for (SearchItem searchItem2 : master) {
                SearchItem searchItem22;
                if (searchItem22.getRole() != LabelUserEnum.MODERATOR.getValue()) {
                    if (searchItem22.getRole() == LabelUserEnum.DEPUTY_MODERATOR.getValue()) {
                        arrayList.add(searchItem22);
                    }
                    searchItem22 = searchItem;
                }
                searchItem = searchItem22;
            }
            if (searchItem == null) {
                this.g.a(arrayList, null);
                return;
            }
            CommonLabelActivity.i = searchItem;
            this.g.a(arrayList, searchItem);
            d();
        }
    }

    public void onEventMainThread(com.budejie.www.busevent.b bVar) {
        if (bVar != null && this.d != null) {
            if (!bVar.a || this.b == null) {
                this.d.a(this.c);
            } else {
                this.b.d();
            }
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.i = context;
        this.j = (CommonLabelActivity) context;
        this.d = new com.budejie.www.i.a.d();
        this.d.a(this);
        EventBus.getDefault().register(this);
    }

    public void onDetach() {
        super.onDetach();
        if (this.d != null) {
            this.d.a();
        }
        EventBus.getDefault().unregister(this);
    }

    private void f() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.c = arguments.getString("theme_id");
            this.g.setThemeId(this.c);
        }
    }

    private void i() {
        this.b = (XListView) this.a.findViewById(R.id.list_view);
        ViewCompat.setNestedScrollingEnabled(this.b, true);
        this.b.setPullLoadEnable(false);
        this.b.setXListViewListener(this.k);
        this.g = new LabelMemberHeaderView(getContext());
        this.g.setVisibility(8);
        this.b.addHeaderView(this.g);
        this.g.setHeadListener(this);
    }

    public void a(Throwable th) {
        if (this.b != null) {
            this.b.a(false);
        }
    }

    public void a(LabelUser labelUser) {
        if (isAdded()) {
            if (this.i instanceof CommonLabelActivity) {
                ((CommonLabelActivity) this.i).a(labelUser);
            }
            this.b.a(true);
            if (labelUser != null) {
                ListBean list = labelUser.getList();
                if (list != null) {
                    Collection manito = list.getManito();
                    if (manito != null) {
                        if (this.f == null) {
                            this.f = new ArrayList();
                        }
                        this.f.clear();
                        this.f.addAll(manito);
                        d();
                        b(labelUser);
                    }
                }
            }
        }
    }

    public void a_() {
    }

    public void g() {
        if (this.j != null && this.j.f() == PlatePostEnum.MEMBER_TAB_POSITION.getCode()) {
            this.j.g();
        }
    }

    public Context h() {
        return getContext();
    }

    public void a(CommonInfoBean commonInfoBean) {
        if (commonInfoBean != null) {
            Object info = commonInfoBean.getInfo();
            if (!TextUtils.isEmpty(info)) {
                if (this.h == null) {
                    this.h = new f(getContext(), R.style.dialogTheme);
                }
                this.h.a(commonInfoBean.getCode() != LabelResponseCode.APPLY_MODERATOR_FAILED.getValue(), info, 2000);
                this.h.show();
            }
        }
    }

    public void a() {
        if (this.d != null) {
            if (an.d()) {
                this.d.b(this.c);
            } else {
                an.a(getActivity(), 0, null, null, 0);
            }
        }
    }
}
