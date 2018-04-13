package com.facebook.imagepipeline.h;

import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class a implements b {
    private final List<b> a;

    public a(Set<b> set) {
        this.a = new ArrayList(set.size());
        for (b bVar : set) {
            if (bVar != null) {
                this.a.add(bVar);
            }
        }
    }

    public a(b... bVarArr) {
        this.a = new ArrayList(bVarArr.length);
        for (Object obj : bVarArr) {
            if (obj != null) {
                this.a.add(obj);
            }
        }
    }

    public void a(ImageRequest imageRequest, Object obj, String str, boolean z) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(imageRequest, obj, str, z);
            } catch (Throwable e) {
                a("InternalListener exception in onRequestStart", e);
            }
        }
    }

    public void a(String str, String str2) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(str, str2);
            } catch (Throwable e) {
                a("InternalListener exception in onProducerStart", e);
            }
        }
    }

    public void a(String str, String str2, @Nullable Map<String, String> map) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(str, str2, (Map) map);
            } catch (Throwable e) {
                a("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    public void a(String str, String str2, Throwable th, @Nullable Map<String, String> map) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(str, str2, th, map);
            } catch (Throwable e) {
                a("InternalListener exception in onProducerFinishWithFailure", e);
            }
        }
    }

    public void b(String str, String str2, @Nullable Map<String, String> map) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).b(str, str2, map);
            } catch (Throwable e) {
                a("InternalListener exception in onProducerFinishWithCancellation", e);
            }
        }
    }

    public void a(String str, String str2, String str3) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(str, str2, str3);
            } catch (Throwable e) {
                a("InternalListener exception in onIntermediateChunkStart", e);
            }
        }
    }

    public void a(String str, String str2, boolean z) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(str, str2, z);
            } catch (Throwable e) {
                a("InternalListener exception in onProducerFinishWithSuccess", e);
            }
        }
    }

    public void a(ImageRequest imageRequest, String str, boolean z) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(imageRequest, str, z);
            } catch (Throwable e) {
                a("InternalListener exception in onRequestSuccess", e);
            }
        }
    }

    public void a(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(imageRequest, str, th, z);
            } catch (Throwable e) {
                a("InternalListener exception in onRequestFailure", e);
            }
        }
    }

    public void a(String str) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            try {
                ((b) this.a.get(i)).a(str);
            } catch (Throwable e) {
                a("InternalListener exception in onRequestCancellation", e);
            }
        }
    }

    public boolean b(String str) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            if (((b) this.a.get(i)).b(str)) {
                return true;
            }
        }
        return false;
    }

    private void a(String str, Throwable th) {
        com.facebook.common.c.a.b("ForwardingRequestListener", str, th);
    }
}
