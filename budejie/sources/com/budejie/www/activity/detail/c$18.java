package com.budejie.www.activity.detail;

import android.os.AsyncTask;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.R;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import java.util.ArrayList;
import java.util.Collection;
import net.tsz.afinal.a.a;
import org.json.JSONObject;

class c$18 extends a<String> {
    final /* synthetic */ c a;

    c$18(c cVar) {
        this.a = cVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onFailure(Throwable th, int i, String str) {
        super.onFailure(th, i, str);
        c.a(this.a, false);
        c.f(this.a);
        c.g(this.a);
        if (c.h(this.a).isEmpty() && c.i(this.a)) {
            an.a(c.c(this.a), c.c(this.a).getString(R.string.data_failed), -1).show();
        }
    }

    public void a(String str) {
        super.onSuccess(str);
        new AsyncTask<String, String, ArrayList<CommentItem>>(this) {
            final /* synthetic */ c$18 a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((ArrayList) obj);
            }

            protected void a(ArrayList<CommentItem> arrayList) {
                switch (c.j(this.a.a)) {
                    case 0:
                        if (c.h(this.a.a).isEmpty()) {
                            c.m(this.a.a);
                        } else {
                            c.k(this.a.a);
                            c.a(this.a.a, c.h(this.a.a));
                            c.a(this.a.a).a(c.h(this.a.a));
                            c.l(this.a.a);
                        }
                        if (c.n(this.a.a) == null || c.n(this.a.a).equals("null")) {
                            c.f(this.a.a);
                            c.o(this.a.a);
                        }
                        if (this.a.a.isAdded() && c.p(this.a.a)) {
                            if (!this.a.a.c) {
                                c.q(this.a.a).setSelection(3);
                                break;
                            }
                            c.q(this.a.a).setSelection(2);
                            c.q(this.a.a).smoothScrollToPositionFromTop(3, this.a.a.getResources().getDimensionPixelSize(R.dimen.comment_detail_title_height), 10);
                            break;
                        }
                    case 1:
                        c.a(this.a.a, c.h(this.a.a));
                        c.a(this.a.a).a(c.h(this.a.a));
                        break;
                    case 2:
                        c.a(this.a.a, c.h(this.a.a));
                        c.a(this.a.a).a(c.h(this.a.a));
                        if (c.n(this.a.a) == null || c.n(this.a.a).equals("null")) {
                            c.f(this.a.a);
                            c.o(this.a.a);
                            break;
                        }
                }
                c.a(this.a.a, false);
            }

            protected ArrayList<CommentItem> a(String... strArr) {
                String str = strArr[0];
                try {
                    if (c.j(this.a.a) == 0) {
                        c.a(this.a.a, z.a(str, "0", c.c(this.a.a)));
                    } else if (c.j(this.a.a) == 1) {
                        Collection a = z.a(str, "1", c.c(this.a.a));
                        if (a != null && a.size() > 0) {
                            for (int i = 0; i < c.h(this.a.a).size(); i++) {
                                CommentItem commentItem = (CommentItem) c.h(this.a.a).get(i);
                                if (commentItem.getHotNp() != null) {
                                    commentItem.setHotNp(null);
                                    c.h(this.a.a).addAll(i + 1, a);
                                    break;
                                }
                            }
                        }
                    } else if (c.j(this.a.a) == 2) {
                        c.h(this.a.a).addAll(z.a(str, "2", c.c(this.a.a)));
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject != null) {
                        if (jSONObject.has(StatisticCodeTable.HOT)) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject(StatisticCodeTable.HOT);
                            if (jSONObject2.has("info")) {
                                c.b(this.a.a, jSONObject2.getJSONObject("info").getString("np"));
                            }
                        }
                        if (jSONObject.has("normal")) {
                            jSONObject = jSONObject.getJSONObject("normal");
                            if (jSONObject.has("info")) {
                                c.c(this.a.a, jSONObject.getJSONObject("info").getString("np"));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return c.h(this.a.a);
            }
        }.execute(new String[]{str});
    }
}
