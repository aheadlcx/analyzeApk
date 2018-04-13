package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@NotThreadSafe
public class RedirectLocations extends AbstractList<Object> {
    private final Set<URI> a = new HashSet();
    private final List<URI> b = new ArrayList();

    public boolean contains(URI uri) {
        return this.a.contains(uri);
    }

    public void add(URI uri) {
        this.a.add(uri);
        this.b.add(uri);
    }

    public boolean remove(URI uri) {
        boolean remove = this.a.remove(uri);
        if (remove) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                if (((URI) it.next()).equals(uri)) {
                    it.remove();
                }
            }
        }
        return remove;
    }

    public List<URI> getAll() {
        return new ArrayList(this.b);
    }

    public URI get(int i) {
        return (URI) this.b.get(i);
    }

    public int size() {
        return this.b.size();
    }

    public Object set(int i, Object obj) {
        URI uri = (URI) this.b.set(i, (URI) obj);
        this.a.remove(uri);
        this.a.add((URI) obj);
        if (this.b.size() != this.a.size()) {
            this.a.addAll(this.b);
        }
        return uri;
    }

    public void add(int i, Object obj) {
        this.b.add(i, (URI) obj);
        this.a.add((URI) obj);
    }

    public URI remove(int i) {
        URI uri = (URI) this.b.remove(i);
        this.a.remove(uri);
        if (this.b.size() != this.a.size()) {
            this.a.addAll(this.b);
        }
        return uri;
    }

    public boolean contains(Object obj) {
        return this.a.contains(obj);
    }
}
