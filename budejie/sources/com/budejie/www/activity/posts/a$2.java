package com.budejie.www.activity.posts;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.aa;
import com.budejie.www.util.d;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.a.a;

class a$2 extends a<String> {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        if (!this.a.isDetached()) {
            a.i(this.a).a(false);
            a.p(this.a);
        }
    }

    public void a(String str) {
        super.onSuccess(str);
        if (!this.a.isDetached()) {
            new AsyncTask<String, String, ArrayList<ListItemObject>>(this) {
                final /* synthetic */ a$2 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((ArrayList) obj);
                }

                protected void a(ArrayList<ListItemObject> arrayList) {
                    try {
                        if (a.g(this.a.a) == 1 && a.q(this.a.a).name.equals("推荐") && a.r(this.a.a) == 0) {
                            List<ListItemObject> s = a.s(this.a.a);
                            if (s != null) {
                                a.c(this.a.a, false);
                                a.i(this.a.a).a(true);
                                a.p(this.a.a);
                                String string = a.b(this.a.a).getString(R.string.recommend_tip_click);
                                d.b(a.b(this.a.a), a.k(this.a.a), String.format(string, new Object[]{Integer.valueOf(10)}));
                                a.i(this.a.a).setPullLoadEnable(true);
                                for (ListItemObject listItemObject : s) {
                                    if (listItemObject.getWid() != null) {
                                        a.t(this.a.a).add(0, listItemObject);
                                    }
                                }
                                string = "";
                                if (TextUtils.isEmpty(((ListItemObject) s.get(0)).opends)) {
                                    string = a.b(this.a.a).getString(R.string.track_event_load_first_bs);
                                } else {
                                    string = a.b(this.a.a).getString(R.string.track_event_load_first_bfd);
                                }
                                i.a(string, string);
                                this.a.a.a(0, false);
                                return;
                            }
                            BudejieApplication.a.a(RequstMethod.GET, j.b(), new j(a.b(this.a.a)), a.u(this.a.a));
                        } else if (a.r(this.a.a) != 0 || a.q(this.a.a) == null || a.q(this.a.a).recsys_url == null || a.q(this.a.a).recsys_url.length() <= 0) {
                            a.c(this.a.a, false);
                            a.i(this.a.a).a(true);
                            a.p(this.a.a);
                            this.a.a.c.edit().putLong("last_refresh_" + a.q(this.a.a).getKey(), System.currentTimeMillis()).commit();
                            a.a(this.a.a, a.x(this.a.a));
                            if (a.g(this.a.a) == 3) {
                                a.i(this.a.a).b(a.b(this.a.a).getString(R.string.xlistview_footer_hint_suiji), a.y(this.a.a));
                            } else {
                                int count = a.f(this.a.a).getCount();
                                if (a.z(this.a.a) == 0 || count < a.x(this.a.a) || !this.a.a.a(a.t(this.a.a))) {
                                    a.i(this.a.a).setPullLoadEnable(true);
                                } else {
                                    a.i(this.a.a).setPullLoadEnable(false);
                                }
                            }
                            aa.a("TAG", a.g(this.a.a) + "--------");
                            if (1 == a.g(this.a.a) && !TextUtils.isEmpty(a.b(this.a.a).b)) {
                                a.b(this.a.a).sendBroadcast(new Intent(a.b(this.a.a).b));
                            }
                            if (arrayList != null) {
                                this.a.a.a(0, a.A(this.a.a));
                            }
                        } else {
                            BudejieApplication.a.a(RequstMethod.GET, j.a(a.q(this.a.a).recsys_url, a.v(this.a.a)), new j(a.b(this.a.a)), a.w(this.a.a));
                        }
                    } catch (Exception e) {
                    }
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    String str = strArr[0];
                    ListInfo a = com.budejie.www.j.a.a(str);
                    a.b(this.a.a, a.count);
                    a.a(this.a.a, a.np);
                    if (a.z(this.a.a) != 0) {
                        a.B(this.a.a);
                    }
                    ArrayList<ListItemObject> arrayList = null;
                    try {
                        arrayList = com.budejie.www.j.a.a(this.a.a.getActivity(), str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (arrayList != null) {
                        a.a(this.a.a, arrayList);
                    } else {
                        str = "";
                        if (a.q(this.a.a) != null) {
                            str = a.q(this.a.a).url;
                        }
                        MobclickAgent.onEvent(a.b(this.a.a), "E03_A13", "刷新列表数据为空－" + str);
                    }
                    return arrayList;
                }
            }.execute(new String[]{str});
        }
    }
}
