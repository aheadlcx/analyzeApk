package android.support.v4.graphics;

import android.support.v4.provider.FontsContractCompat.FontInfo;

class d implements a<FontInfo> {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public int getWeight(FontInfo fontInfo) {
        return fontInfo.getWeight();
    }

    public boolean isItalic(FontInfo fontInfo) {
        return fontInfo.isItalic();
    }
}
