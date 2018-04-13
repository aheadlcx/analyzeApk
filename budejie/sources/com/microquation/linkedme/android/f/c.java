package com.microquation.linkedme.android.f;

import android.annotation.SuppressLint;
import java.util.Collection;
import org.json.JSONObject;

public class c extends JSONObject {
    private Collection<String> a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;

    public Collection<String> a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.h;
    }

    public String e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.b == null) {
            if (cVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(cVar.b)) {
            return false;
        }
        if (this.d == null) {
            if (cVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(cVar.d)) {
            return false;
        }
        if (this.e == null) {
            if (cVar.e != null) {
                return false;
            }
        } else if (!this.e.equals(cVar.e)) {
            return false;
        }
        if (this.g == null) {
            if (cVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(cVar.g)) {
            return false;
        }
        if (this.f == null) {
            if (cVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(cVar.f)) {
            return false;
        }
        return this.c != cVar.c ? false : this.h != cVar.h ? false : this.a == null ? cVar.a == null : this.a.toString().equals(cVar.a.toString());
    }

    public String f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    @SuppressLint({"DefaultLocale"})
    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f == null ? 0 : this.f.toLowerCase().hashCode()) + (19 * ((this.e == null ? 0 : this.e.toLowerCase().hashCode()) + (19 * ((this.d == null ? 0 : this.d.toLowerCase().hashCode()) + (19 * ((this.b == null ? 0 : this.b.toLowerCase().hashCode()) + (19 * (this.c + 19))))))))) * 19;
        if (this.g != null) {
            i = this.g.toLowerCase().hashCode();
        }
        hashCode = ((hashCode + i) * 19) + this.h;
        if (this.a == null) {
            return hashCode;
        }
        i = hashCode;
        for (String toLowerCase : this.a) {
            i = toLowerCase.toLowerCase().hashCode() + (i * 19);
        }
        return i;
    }
}
