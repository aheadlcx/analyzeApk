package com.budejie.www.activity.auditpost;

import android.os.AsyncTask;
import android.text.TextUtils;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.R;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import java.util.ArrayList;
import java.util.Collection;
import net.tsz.afinal.a.a;
import org.json.JSONObject;

class a$4 extends a<String> {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void onStart() {
    }

    public void a(String str) {
        if (!this.a.isDetached()) {
            if (TextUtils.isEmpty(str)) {
                an.a(a.c(this.a), a.c(this.a).getString(R.string.no_commentdata), -1).show();
                return;
            }
            try {
                new AsyncTask<String, String, ArrayList<CommentItem>>(this) {
                    final /* synthetic */ a$4 a;

                    {
                        this.a = r1;
                    }

                    protected /* synthetic */ Object doInBackground(Object[] objArr) {
                        return a((String[]) objArr);
                    }

                    protected /* synthetic */ void onPostExecute(Object obj) {
                        a((ArrayList) obj);
                    }

                    protected void onPreExecute() {
                        super.onPreExecute();
                    }

                    protected void a(ArrayList<CommentItem> arrayList) {
                        switch (a.e(this.a.a)) {
                            case 0:
                                a.d(this.a.a).a(false);
                                if (!a.f(this.a.a).isEmpty()) {
                                    this.a.a.e();
                                    a.a(this.a.a, a.f(this.a.a));
                                    a.g(this.a.a);
                                }
                                if (a.h(this.a.a) == null || a.h(this.a.a).equals("null")) {
                                    a.d(this.a.a).setPullLoadEnable(false);
                                    return;
                                } else {
                                    a.d(this.a.a).setPullLoadEnable(true);
                                    return;
                                }
                            case 1:
                                a.a(this.a.a, a.f(this.a.a));
                                a.i(this.a.a).a(a.f(this.a.a));
                                return;
                            case 2:
                                a.a(this.a.a, a.f(this.a.a));
                                a.i(this.a.a).a(a.f(this.a.a));
                                if (a.h(this.a.a) == null || a.h(this.a.a).equals("null")) {
                                    a.d(this.a.a).setPullLoadEnable(false);
                                    return;
                                } else {
                                    a.d(this.a.a).setPullLoadEnable(true);
                                    return;
                                }
                            default:
                                return;
                        }
                    }

                    protected ArrayList<CommentItem> a(String... strArr) {
                        String str = strArr[0];
                        try {
                            if (a.e(this.a.a) == 0) {
                                a.a(this.a.a, z.a(str, "0", a.c(this.a.a)));
                            } else if (a.e(this.a.a) == 1) {
                                Collection a = z.a(str, "1", a.c(this.a.a));
                                if (a != null && a.size() > 0) {
                                    for (int i = 0; i < a.f(this.a.a).size(); i++) {
                                        CommentItem commentItem = (CommentItem) a.f(this.a.a).get(i);
                                        if (commentItem.getHotNp() != null) {
                                            commentItem.setHotNp(null);
                                            a.f(this.a.a).addAll(i + 1, a);
                                            break;
                                        }
                                    }
                                }
                            } else if (a.e(this.a.a) == 2) {
                                a.f(this.a.a).addAll(z.a(str, "2", a.c(this.a.a)));
                            }
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject != null) {
                                if (jSONObject.has(StatisticCodeTable.HOT)) {
                                    JSONObject jSONObject2 = jSONObject.getJSONObject(StatisticCodeTable.HOT);
                                    if (jSONObject2.has("info")) {
                                        a.a(this.a.a, jSONObject2.getJSONObject("info").getString("np"));
                                    }
                                }
                                if (jSONObject.has("normal")) {
                                    jSONObject = jSONObject.getJSONObject("normal");
                                    if (jSONObject.has("info")) {
                                        a.b(this.a.a, jSONObject.getJSONObject("info").getString("np"));
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return a.f(this.a.a);
                    }
                }.execute(new String[]{str});
            } catch (Exception e) {
                e.printStackTrace();
                an.a(a.c(this.a), a.c(this.a).getString(R.string.parse_failed), -1).show();
            }
        }
    }

    public void onFailure(Throwable th, int i, String str) {
        if (!this.a.isDetached()) {
            if (a.e(this.a) == 0) {
                a.d(this.a).b();
            } else if (a.e(this.a) == 2) {
                a.d(this.a).c();
            }
            an.a(a.c(this.a), a.c(this.a).getString(R.string.parse_failed), -1).show();
        }
    }
}
