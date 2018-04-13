package com.budejie.www.activity.recommend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.adapter.c.c;
import com.budejie.www.adapter.c.c$a;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SubscibeNumberListResult;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.c.g;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.budejie.www.widget.XListView.a;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class b extends Fragment implements c$a {
    private XListView a;
    private Toast b;
    private int c;
    private String d;
    private c e;
    private List<SuggestedFollowsListItem> f;
    private SuggestedFollowsActivity g;
    private Handler h;
    private g i;
    private a j = new a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.a();
        }

        public void b() {
            this.a.b();
        }
    };
    private net.tsz.afinal.a.a<String> k = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            if (!this.a.isDetached()) {
                this.a.a.b();
            }
        }

        public void a(String str) {
            if (!this.a.isDetached()) {
                this.a.a.b();
                if (TextUtils.isEmpty(str)) {
                    this.a.b = an.a(this.a.getActivity(), this.a.getString(R.string.no_subscibe), -1);
                    this.a.b.show();
                    return;
                }
                try {
                    SubscibeNumberListResult subscibeNumberListResult = (SubscibeNumberListResult) new Gson().fromJson(str, SubscibeNumberListResult.class);
                    this.a.c = subscibeNumberListResult.next_page;
                    int i = subscibeNumberListResult.total_page;
                    Object obj = subscibeNumberListResult.list;
                    if (obj != null) {
                        this.a.f.clear();
                        this.a.f.addAll(obj);
                        this.a.e.a(obj);
                    }
                    if (this.a.c <= i) {
                        this.a.a.setPullLoadEnable(true);
                    } else {
                        this.a.a.setPullLoadEnable(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.a.b = an.a(this.a.getActivity(), this.a.getString(R.string.no_subscibe), -1);
                    this.a.b.show();
                }
            }
        }
    };
    private net.tsz.afinal.a.a<String> l = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            if (!this.a.isDetached()) {
                this.a.a.c();
            }
        }

        public void a(String str) {
            super.onSuccess(str);
            if (!this.a.isDetached()) {
                this.a.a.c();
                if ("[]".equals(str)) {
                    this.a.a.a(this.a.g.getString(R.string.already_last), null);
                    return;
                }
                try {
                    SubscibeNumberListResult subscibeNumberListResult = (SubscibeNumberListResult) new Gson().fromJson(str, SubscibeNumberListResult.class);
                    this.a.c = subscibeNumberListResult.next_page;
                    int i = subscibeNumberListResult.total_page;
                    Object obj = subscibeNumberListResult.list;
                    if (obj != null) {
                        this.a.f.addAll(obj);
                        this.a.e.b(obj);
                    }
                    if (this.a.c <= i) {
                        this.a.a.setPullLoadEnable(true);
                        return;
                    }
                    this.a.a.setPullLoadEnable(false);
                    this.a.a.a(this.a.g.getString(R.string.already_last), null);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.a.b = an.a(this.a.getActivity(), this.a.getString(R.string.no_subscibe), -1);
                    this.a.b.show();
                }
            }
        }
    };

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.g = (SuggestedFollowsActivity) activity;
        this.d = getArguments().getString("category_id");
        this.e = new c(getActivity(), this);
        this.f = new ArrayList();
        this.h = new Handler();
        this.i = new g(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.suggested_follows_fragment_layout, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = (XListView) view.findViewById(R.id.listview);
        this.a.setAdapter(this.e);
        this.a.setPullLoadEnable(false);
        this.a.setPullRefreshEnable(true);
        this.a.setXListViewListener(this.j);
        if (this.e.getCount() <= 0) {
            this.h.postDelayed(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.d();
                }
            }, 200);
        }
    }

    public void onStart() {
        super.onStart();
        this.g.a("no_invite_data");
    }

    public void onResume() {
        super.onResume();
        if (this.e.getCount() > 0) {
            as.b().a(this.e);
            this.e.notifyDataSetChanged();
        }
    }

    private void a() {
        BudejieApplication.a.a(RequstMethod.GET, j.a(1, this.d), new j(this.g), this.k);
    }

    private void b() {
        BudejieApplication.a.a(RequstMethod.GET, j.a(this.c, this.d), new j(this.g), this.l);
    }

    public void a(final SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        if (an.a(this.g.a())) {
            com.budejie.www.util.b.a(getActivity(), suggestedFollowsListItem.uid, new net.tsz.afinal.a.a<String>(this) {
                final /* synthetic */ b b;

                public /* synthetic */ void onSuccess(Object obj) {
                    a((String) obj);
                }

                public void onStart() {
                    super.onStart();
                    this.b.g.f();
                }

                public void a(String str) {
                    if (!this.b.isDetached()) {
                        try {
                            this.b.g.e();
                            ResultBean s = z.s(str);
                            if (s != null) {
                                CharSequence code = s.getCode();
                                Object msg = s.getMsg();
                                if (TextUtils.isEmpty(msg)) {
                                    an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                                } else {
                                    an.a(this.b.getActivity(), msg, -1).show();
                                }
                                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                                    suggestedFollowsListItem.is_follow = 1;
                                    this.b.e.notifyDataSetChanged();
                                    this.b.i.a(new Fans(suggestedFollowsListItem));
                                    as.b().a(suggestedFollowsListItem);
                                    return;
                                }
                                return;
                            }
                            an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                        }
                    }
                }

                public void onFailure(Throwable th, int i, String str) {
                    if (!this.b.isDetached()) {
                        this.b.g.e();
                        an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                    }
                }
            });
            return;
        }
        an.a(this.g, new Intent(this.g, SuggestedFollowsActivity.class));
    }

    public void b(final SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        com.budejie.www.util.b.b(getActivity(), suggestedFollowsListItem.uid, new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ b b;

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onStart() {
                super.onStart();
                this.b.g.f();
            }

            public void a(String str) {
                if (!this.b.isDetached()) {
                    try {
                        this.b.g.e();
                        ResultBean s = z.s(str);
                        if (s != null) {
                            CharSequence code = s.getCode();
                            Object msg = s.getMsg();
                            if (TextUtils.isEmpty(msg)) {
                                an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                            } else {
                                an.a(this.b.getActivity(), msg, -1).show();
                            }
                            if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                                suggestedFollowsListItem.is_follow = 0;
                                this.b.e.notifyDataSetChanged();
                                this.b.i.a(suggestedFollowsListItem.uid);
                                return;
                            }
                            return;
                        }
                        an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                    }
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                if (!this.b.isDetached()) {
                    this.b.g.e();
                    an.a(this.b.getActivity(), this.b.getActivity().getString(R.string.operate_fail), -1).show();
                }
            }
        });
    }

    public void a(boolean z, SuggestedFollowsListItem suggestedFollowsListItem, int i) {
    }

    public void a(View view, SuggestedFollowsListItem suggestedFollowsListItem) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, suggestedFollowsListItem.uid);
        this.g.b.a(7, bundle).onClick(view);
    }
}
