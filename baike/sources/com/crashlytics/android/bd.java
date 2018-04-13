package com.crashlytics.android;

import com.crashlytics.android.internal.C0000au;
import java.io.IOException;
import java.io.InputStream;

final class bd implements C0000au {
    private /* synthetic */ byte[] a;
    private /* synthetic */ int[] b;

    bd(ba baVar, byte[] bArr, int[] iArr) {
        this.a = bArr;
        this.b = iArr;
    }

    public final void a(InputStream inputStream, int i) throws IOException {
        try {
            inputStream.read(this.a, this.b[0], i);
            int[] iArr = this.b;
            iArr[0] = iArr[0] + i;
        } finally {
            inputStream.close();
        }
    }
}
