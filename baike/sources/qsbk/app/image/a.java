package qsbk.app.image;

import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

final class a extends LinkedHashMap<String, Uri> {
    a(int i) {
        super(i);
    }

    public boolean removeEldestEntry(Entry entry) {
        return size() > 100;
    }
}
