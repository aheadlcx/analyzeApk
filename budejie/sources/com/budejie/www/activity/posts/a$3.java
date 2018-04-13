package com.budejie.www.activity.posts;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.http.i;
import com.budejie.www.util.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.tsz.afinal.a.a;
import org.json.JSONException;
import org.json.JSONObject;

class a$3 extends a<String> {
    final /* synthetic */ a a;

    a$3(a aVar) {
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
                final /* synthetic */ a$3 a;

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
                        a.c(this.a.a, false);
                        a.i(this.a.a).a(true);
                        a.p(this.a.a);
                        a.i(this.a.a).setPullLoadEnable(true);
                        List<ListItemObject> s = a.s(this.a.a);
                        if (s != null) {
                            String string = a.b(this.a.a).getString(R.string.recommend_tip_click);
                            d.b(a.b(this.a.a), a.k(this.a.a), String.format(string, new Object[]{Integer.valueOf(10)}));
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
                        d.b(a.b(this.a.a), a.k(this.a.a), a.b(this.a.a).getString(R.string.not_new_data_click));
                    } catch (Exception e) {
                    }
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    String str;
                    ArrayList<ListItemObject> a;
                    String str2 = strArr[0];
                    com.budejie.www.j.a.a(str2);
                    String str3 = "";
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.has("opends")) {
                            str3 = jSONObject.getString("opends");
                        }
                        str = str3;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        str = str3;
                    }
                    try {
                        a = com.budejie.www.j.a.a(this.a.a.getActivity(), str2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        a = null;
                    }
                    if (a != null) {
                        a.C(this.a.a).clear();
                        Iterator it = a.iterator();
                        while (it.hasNext()) {
                            ListItemObject listItemObject = (ListItemObject) it.next();
                            if (listItemObject.getWid() != null) {
                                listItemObject.isreport = false;
                                listItemObject.setAddtime("推荐内容 " + listItemObject.getAddtime());
                                listItemObject.opends = str;
                                a.C(this.a.a).add(listItemObject);
                            }
                        }
                    }
                    return a;
                }
            }.execute(new String[]{str});
        }
    }
}
