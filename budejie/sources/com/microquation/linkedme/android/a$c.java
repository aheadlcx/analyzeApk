package com.microquation.linkedme.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.microquation.linkedme.android.b.b;
import com.microquation.linkedme.android.b.c;
import com.microquation.linkedme.android.b.h;
import com.microquation.linkedme.android.b.t;
import com.microquation.linkedme.android.b.u;
import com.microquation.linkedme.android.util.LinkProperties;
import com.microquation.linkedme.android.util.c.a;
import com.microquation.linkedme.android.util.c.f;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class a$c extends AsyncTask<Void, Void, u> {
    int a = 0;
    h b;
    final /* synthetic */ a c;

    public a$c(a aVar, h hVar) {
        this.c = aVar;
        this.b = hVar;
        this.a = a.k(aVar).d();
    }

    protected u a(Void... voidArr) {
        try {
            if (!t.f(this.b) && !t.g(this.b) && !t.h(this.b)) {
                this.c.a(this.b.f() + "-" + a.Queue_Wait_Time.a(), String.valueOf(this.b.m()));
            } else if (t.g(this.b)) {
                JSONObject h = this.b.h();
                try {
                    h.put(a.LKME_APPS_DATA.a(), a.l(this.c).a());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.b.a(h);
            }
            if (this.b.i()) {
                this.b.a(a.l(this.c));
            }
            return this.b.c() ? a.j(this.c).a(this.b.g(), this.b.j(), this.b.g(), this.a) : a.j(this.c).a(this.b.a(a.m(this.c)), this.b.g(), this.b.f(), this.a, a.k(this.c).z());
        } catch (Exception e2) {
            return null;
        }
    }

    protected void a(u uVar) {
        int i = 0;
        super.onPostExecute(uVar);
        if (uVar != null) {
            try {
                int a = uVar.a();
                a.f(this.c, true);
                if (a == 200) {
                    a.f(this.c, true);
                    if (t.a(this.b) && (this.b instanceof c) && uVar.b() != null) {
                        a.q(this.c).put(((c) this.b).a(), uVar.b().optString("url"));
                    }
                    if (!(t.f(this.b) || t.g(this.b) || t.h(this.b))) {
                        a.n(this.c).b();
                    }
                    if (t.e(this.b)) {
                        a.r(this.c);
                        if (uVar.b() != null) {
                            int i2;
                            if (!uVar.b().has(a.LKME_SESSION_ID.a()) || TextUtils.isEmpty(uVar.b().getString(a.LKME_SESSION_ID.a()))) {
                                i2 = 0;
                            } else {
                                a.k(this.c).e(uVar.b().getString(a.LKME_SESSION_ID.a()));
                                i2 = 1;
                            }
                            if (uVar.b().has(a.LKME_IDENTITY_ID.a()) && !TextUtils.isEmpty(uVar.b().getString(a.LKME_IDENTITY_ID.a()))) {
                                if (!a.k(this.c).l().equals(uVar.b().getString(a.LKME_IDENTITY_ID.a()))) {
                                    a.q(this.c).clear();
                                    a.k(this.c).f(uVar.b().getString(a.LKME_IDENTITY_ID.a()));
                                    i2 = 1;
                                }
                            }
                            if (uVar.b().has(a.DeviceFingerprintID.a()) && !TextUtils.isEmpty(uVar.b().getString(a.DeviceFingerprintID.a()))) {
                                a.k(this.c).d(uVar.b().getString(a.DeviceFingerprintID.a()));
                                i2 = 1;
                            }
                            if (uVar.b().has(f.Params.a()) && !TextUtils.isEmpty(uVar.b().getString(f.Params.a()))) {
                                a.k(this.c).t(a.b(this.c, uVar.b().getString(f.Params.a())).getString(f.LKME_Link.a()));
                            }
                            if (i2 != 0) {
                                a.s(this.c);
                            }
                            if (t.e(this.b) && (this.b instanceof b)) {
                                com.microquation.linkedme.android.f.b.a(a.a, "post init session status ===  " + a.t(this.c));
                                a.a(this.c, a$d.INITIALISED);
                                this.b.a(uVar, a.j());
                                a.g(this.c, ((b) this.b).a());
                                com.microquation.linkedme.android.f.b.a(getClass().getSimpleName(), "处理方式：" + a.u(this.c));
                                if (a.h(this.c) != null) {
                                    JSONObject d = this.c.d();
                                    if (!d.optBoolean(a.LKME_CLICKED_LINKEDME_LINK.a(), false)) {
                                        a.o(this.c);
                                    } else if (d.length() > 0) {
                                        Intent intent = new Intent();
                                        a.a(this.c, intent, d, LinkProperties.f());
                                        a.h(this.c).a(intent, null);
                                    } else {
                                        a.o(this.c);
                                    }
                                } else if (a.g(this.c) != null) {
                                    a.p(this.c);
                                } else if (a.u(this.c) || a.v(this.c)) {
                                    com.microquation.linkedme.android.f.b.a(a.a, "open api auto jump deepLinksImmediate = " + a.u(this.c) + "dlLaunchFromYYB = " + a.v(this.c));
                                    a.w(this.c);
                                }
                            } else {
                                this.b.a(uVar, a.j());
                            }
                        }
                    } else {
                        this.b.a(uVar, a.j());
                    }
                } else if (!t.f(this.b) && !t.g(this.b)) {
                    if (t.h(this.b)) {
                        this.b.a(a, uVar.c());
                        return;
                    }
                    if (t.e(this.b)) {
                        a.a(this.c, a$d.UNINITIALISED);
                    }
                    if (a == 409) {
                        a.n(this.c).b(this.b);
                        if (t.a(this.b) && (this.b instanceof c)) {
                            ((c) this.b).b();
                        } else {
                            a.b(this.c).b("LinkedME API Error: Conflicting resource error code from API");
                            a.a(this.c, 0, a);
                        }
                    } else {
                        h hVar;
                        a.f(this.c, false);
                        ArrayList arrayList = new ArrayList();
                        while (i < a.n(this.c).a()) {
                            arrayList.add(a.n(this.c).a(i));
                            i++;
                        }
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            hVar = (h) it.next();
                            if (hVar == null || !hVar.e()) {
                                a.n(this.c).b(hVar);
                            }
                        }
                        a.a(this.c, 0);
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            hVar = (h) it2.next();
                            if (hVar != null) {
                                hVar.a(a, uVar.c());
                                if (hVar.e()) {
                                    hVar.d();
                                }
                                if (t.e(hVar)) {
                                    a.o(this.c);
                                    a.p(this.c);
                                }
                            }
                        }
                    }
                } else {
                    return;
                }
                a.a(this.c, 0);
                if (a.x(this.c) && a.t(this.c) != a$d.UNINITIALISED) {
                    a.y(this.c);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((u) obj);
    }
}
