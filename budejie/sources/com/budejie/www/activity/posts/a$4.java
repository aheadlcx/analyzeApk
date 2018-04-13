package com.budejie.www.activity.posts;

import android.os.AsyncTask;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.d;
import java.util.ArrayList;
import java.util.Iterator;
import net.tsz.afinal.a.a;
import org.json.JSONException;
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

                protected void a(ArrayList<ListItemObject> arrayList) {
                    try {
                        a.c(this.a.a, false);
                        a.i(this.a.a).a(true);
                        a.p(this.a.a);
                        a.i(this.a.a).setPullLoadEnable(true);
                        if (arrayList == null || arrayList.size() <= 0) {
                            d.b(a.b(this.a.a), a.k(this.a.a), a.b(this.a.a).getString(R.string.not_new_data_click));
                            return;
                        }
                        String string = a.b(this.a.a).getString(R.string.recommend_tip_click);
                        d.b(a.b(this.a.a), a.k(this.a.a), String.format(string, new Object[]{Integer.valueOf(arrayList.size() - 1)}));
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            ListItemObject listItemObject = (ListItemObject) it.next();
                            if (listItemObject.getWid() != null || listItemObject.isInsertRemind()) {
                                a.t(this.a.a).add(0, listItemObject);
                            }
                        }
                        this.a.a.a(0, false);
                    } catch (Exception e) {
                    }
                }

                protected ArrayList<ListItemObject> a(String... strArr) {
                    String str;
                    ArrayList<ListItemObject> a;
                    String str2 = strArr[0];
                    a.b(this.a.a, com.budejie.www.j.a.a(str2).np);
                    if (a.v(this.a.a) != 0) {
                        a.D(this.a.a);
                    }
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
                    if (a != null && a.size() > 0) {
                        a.C(this.a.a).clear();
                        Iterator it = a.iterator();
                        while (it.hasNext()) {
                            ListItemObject listItemObject = (ListItemObject) it.next();
                            if (listItemObject.getWid() != null) {
                                listItemObject.isreport = false;
                                if (listItemObject.getStatus() == 1) {
                                    listItemObject.setAddtime("待审内容 " + listItemObject.getAddtime());
                                } else if (listItemObject.getStatus() == 2) {
                                    listItemObject.setAddtime("新帖内容 " + listItemObject.getAddtime());
                                } else {
                                    listItemObject.setAddtime("推荐内容 " + listItemObject.getAddtime());
                                }
                                listItemObject.opends = str;
                                listItemObject.setIsRecsysData(true);
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
