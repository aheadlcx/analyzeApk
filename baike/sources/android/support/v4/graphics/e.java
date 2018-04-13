package android.support.v4.graphics;

import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;

class e implements a<FontFileResourceEntry> {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public int getWeight(FontFileResourceEntry fontFileResourceEntry) {
        return fontFileResourceEntry.getWeight();
    }

    public boolean isItalic(FontFileResourceEntry fontFileResourceEntry) {
        return fontFileResourceEntry.isItalic();
    }
}
