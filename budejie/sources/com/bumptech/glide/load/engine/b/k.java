package com.bumptech.glide.load.engine.b;

import com.bumptech.glide.i.e;
import com.bumptech.glide.i.h;
import com.bumptech.glide.load.b;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class k {
    private final e<b, String> a = new e(1000);

    k() {
    }

    public String a(b bVar) {
        String str;
        synchronized (this.a) {
            str = (String) this.a.b((Object) bVar);
        }
        if (str == null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                bVar.a(instance);
                str = h.a(instance.digest());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
            synchronized (this.a) {
                this.a.b(bVar, str);
            }
        }
        return str;
    }
}
