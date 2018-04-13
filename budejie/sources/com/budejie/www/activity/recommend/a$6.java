package com.budejie.www.activity.recommend;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.budejie.www.R;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.adapter.c.c$a;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.b;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class a$6 implements c$a {
    final /* synthetic */ a a;

    a$6(a aVar) {
        this.a = aVar;
    }

    public void a(final SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        b.a(this.a.getActivity(), suggestedFollowsListItem.uid, new a<String>(this) {
            final /* synthetic */ a$6 b;

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onStart() {
                super.onStart();
                a.c(this.b.a).f();
            }

            public void a(String str) {
                if (!this.b.a.isDetached()) {
                    try {
                        a.c(this.b.a).e();
                        ResultBean s = z.s(str);
                        if (s != null) {
                            CharSequence code = s.getCode();
                            Object msg = s.getMsg();
                            if (TextUtils.isEmpty(msg)) {
                                an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                            } else {
                                an.a(this.b.a.getActivity(), msg, -1).show();
                            }
                            if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                                suggestedFollowsListItem.is_follow = 1;
                                a.m(this.b.a).notifyDataSetChanged();
                                a.p(this.b.a).a(new Fans(suggestedFollowsListItem));
                                as.b().a(suggestedFollowsListItem);
                                return;
                            }
                            return;
                        }
                        an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                    }
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                if (!this.b.a.isDetached()) {
                    a.c(this.b.a).e();
                    an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                }
            }
        });
    }

    public void a(boolean z, SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        SuggestedFollowsListItem suggestedFollowsListItem2 = (SuggestedFollowsListItem) a.m(this.a).getItem(i);
        suggestedFollowsListItem2.isAdd = z;
        if (z) {
            a.k(this.a).add(suggestedFollowsListItem2);
        } else {
            a.k(this.a).remove(suggestedFollowsListItem2);
        }
        this.a.b();
    }

    public void a(View view, SuggestedFollowsListItem suggestedFollowsListItem) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, suggestedFollowsListItem.uid);
        a.c(this.a).b.a(7, bundle).onClick(view);
    }

    public void b(final SuggestedFollowsListItem suggestedFollowsListItem, int i) {
        b.b(this.a.getActivity(), suggestedFollowsListItem.uid, new a<String>(this) {
            final /* synthetic */ a$6 b;

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void onStart() {
                super.onStart();
                a.c(this.b.a).f();
            }

            public void a(String str) {
                if (!this.b.a.isDetached()) {
                    try {
                        a.c(this.b.a).e();
                        ResultBean s = z.s(str);
                        if (s != null) {
                            CharSequence code = s.getCode();
                            Object msg = s.getMsg();
                            if (TextUtils.isEmpty(msg)) {
                                an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                            } else {
                                an.a(this.b.a.getActivity(), msg, -1).show();
                            }
                            if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                                suggestedFollowsListItem.is_follow = 0;
                                a.m(this.b.a).notifyDataSetChanged();
                                a.p(this.b.a).a(suggestedFollowsListItem.uid);
                                return;
                            }
                            return;
                        }
                        an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                    }
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                if (!this.b.a.isDetached()) {
                    a.c(this.b.a).e();
                    an.a(this.b.a.getActivity(), this.b.a.getActivity().getString(R.string.operate_fail), -1).show();
                }
            }
        });
    }
}
