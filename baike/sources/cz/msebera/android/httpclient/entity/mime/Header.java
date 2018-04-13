package cz.msebera.android.httpclient.entity.mime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Header implements Iterable<MinimalField> {
    private final List<MinimalField> a = new LinkedList();
    private final Map<String, List<MinimalField>> b = new HashMap();

    public void addField(MinimalField minimalField) {
        if (minimalField != null) {
            String toLowerCase = minimalField.getName().toLowerCase(Locale.ROOT);
            List list = (List) this.b.get(toLowerCase);
            if (list == null) {
                list = new LinkedList();
                this.b.put(toLowerCase, list);
            }
            list.add(minimalField);
            this.a.add(minimalField);
        }
    }

    public List<MinimalField> getFields() {
        return new ArrayList(this.a);
    }

    public MinimalField getField(String str) {
        if (str == null) {
            return null;
        }
        List list = (List) this.b.get(str.toLowerCase(Locale.ROOT));
        return (list == null || list.isEmpty()) ? null : (MinimalField) list.get(0);
    }

    public List<MinimalField> getFields(String str) {
        if (str == null) {
            return null;
        }
        List list = (List) this.b.get(str.toLowerCase(Locale.ROOT));
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList(list);
    }

    public int removeFields(String str) {
        if (str == null) {
            return 0;
        }
        List list = (List) this.b.remove(str.toLowerCase(Locale.ROOT));
        if (list == null || list.isEmpty()) {
            return 0;
        }
        this.a.removeAll(list);
        return list.size();
    }

    public void setField(MinimalField minimalField) {
        if (minimalField != null) {
            List list = (List) this.b.get(minimalField.getName().toLowerCase(Locale.ROOT));
            if (list == null || list.isEmpty()) {
                addField(minimalField);
                return;
            }
            list.clear();
            list.add(minimalField);
            Iterator it = this.a.iterator();
            int i = 0;
            int i2 = -1;
            while (it.hasNext()) {
                if (((MinimalField) it.next()).getName().equalsIgnoreCase(minimalField.getName())) {
                    it.remove();
                    if (i2 == -1) {
                        i2 = i;
                    }
                }
                i++;
            }
            this.a.add(i2, minimalField);
        }
    }

    public Iterator<MinimalField> iterator() {
        return Collections.unmodifiableList(this.a).iterator();
    }

    public String toString() {
        return this.a.toString();
    }
}
