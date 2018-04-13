package com.microquation.linkedme.android.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.microquation.linkedme.android.c.d;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.g;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class o {
    private static volatile o a;
    private SharedPreferences b;
    private Editor c = this.b.edit();
    private final List<h> d;

    @SuppressLint({"CommitPrefEdits"})
    private o(Context context) {
        this.b = context.getSharedPreferences("LKME_Server_Request_Queue", 0);
        this.d = b(context);
    }

    public static o a(Context context) {
        if (a == null) {
            synchronized (o.class) {
                if (a == null) {
                    a = new o(context);
                }
            }
        }
        return a;
    }

    private List<h> b(Context context) {
        List<h> synchronizedList = Collections.synchronizedList(new LinkedList());
        String string = this.b.getString("LKMEServerRequestQueue", null);
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < Math.min(jSONArray.length(), 25); i++) {
                    h a = h.a(jSONArray.getJSONObject(i), context);
                    if (!(a == null || t.b(a))) {
                        synchronizedList.add(a);
                    }
                }
            } catch (JSONException e) {
            }
        }
        return synchronizedList;
    }

    private void g() {
        new Thread(new Runnable(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    synchronized (this.a.d) {
                        JSONArray jSONArray = new JSONArray();
                        for (h k : this.a.d) {
                            JSONObject k2 = k.k();
                            if (k2 != null) {
                                jSONArray.put(k2);
                            }
                        }
                        try {
                            this.a.c.putString("LKMEServerRequestQueue", jSONArray.toString()).commit();
                        } catch (ConcurrentModificationException e) {
                            b.a("Persisting Queue: ", "Failed to persit queue " + e.getMessage());
                            try {
                                this.a.c.putString("LKMEServerRequestQueue", jSONArray.toString()).commit();
                            } catch (ConcurrentModificationException e2) {
                            }
                        } catch (Throwable th) {
                            try {
                                this.a.c.putString("LKMEServerRequestQueue", jSONArray.toString()).commit();
                            } catch (ConcurrentModificationException e3) {
                            }
                        }
                    }
                    return;
                } catch (Exception e4) {
                    if (b.a()) {
                        e4.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public int a() {
        return this.d.size();
    }

    public h a(int i) {
        try {
            return (h) this.d.get(i);
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (NoSuchElementException e2) {
            return null;
        }
    }

    public void a(h hVar) {
        if (hVar != null) {
            this.d.add(hVar);
            if (a() >= 25) {
                this.d.remove(1);
            }
            g();
        }
    }

    public void a(h hVar, int i) {
        try {
            if (this.d.size() < i) {
                i = this.d.size();
            }
            this.d.add(i, hVar);
            g();
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public void a(h hVar, int i, d dVar) {
        synchronized (this.d) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                h hVar2 = (h) it.next();
                if (hVar2 != null && ((hVar2 instanceof q) || (hVar2 instanceof r))) {
                    it.remove();
                    break;
                }
            }
        }
        if (i == 0) {
            a(hVar, 0);
        } else {
            a(hVar, 1);
        }
    }

    public void a(d dVar) {
        synchronized (this.d) {
            for (h hVar : this.d) {
                if (hVar != null) {
                    if (hVar instanceof q) {
                        ((q) hVar).a(dVar);
                    } else if (hVar instanceof r) {
                        ((r) hVar).a(dVar);
                    }
                }
            }
        }
    }

    public h b() {
        try {
            h hVar = (h) this.d.remove(0);
            try {
                g();
                return hVar;
            } catch (IndexOutOfBoundsException e) {
                return hVar;
            } catch (NoSuchElementException e2) {
                return hVar;
            }
        } catch (IndexOutOfBoundsException e3) {
            return null;
        } catch (NoSuchElementException e4) {
            return null;
        }
    }

    public boolean b(h hVar) {
        boolean z = false;
        try {
            z = this.d.remove(hVar);
            g();
            return z;
        } catch (UnsupportedOperationException e) {
            return z;
        }
    }

    public h c() {
        try {
            return (h) this.d.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (NoSuchElementException e2) {
            return null;
        }
    }

    public void d() {
        try {
            this.d.clear();
            g();
        } catch (UnsupportedOperationException e) {
        }
    }

    public boolean e() {
        synchronized (this.d) {
            for (h hVar : this.d) {
                if (hVar != null && hVar.f().equals(g.RegisterClose.a())) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean f() {
        synchronized (this.d) {
            for (h hVar : this.d) {
                if (hVar != null && ((hVar instanceof q) || (hVar instanceof r))) {
                    return true;
                }
            }
            return false;
        }
    }
}
