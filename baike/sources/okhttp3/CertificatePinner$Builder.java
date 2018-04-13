package okhttp3;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public final class CertificatePinner$Builder {
    private final List<CertificatePinner$a> a = new ArrayList();

    public CertificatePinner$Builder add(String str, String... strArr) {
        if (str == null) {
            throw new NullPointerException("pattern == null");
        }
        for (String certificatePinner$a : strArr) {
            this.a.add(new CertificatePinner$a(str, certificatePinner$a));
        }
        return this;
    }

    public CertificatePinner build() {
        return new CertificatePinner(new LinkedHashSet(this.a), null);
    }
}
