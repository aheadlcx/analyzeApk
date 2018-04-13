package cn.htjyb.b.a;

import cn.htjyb.b.a.b.a;
import cn.htjyb.b.a.b.b;
import cn.htjyb.netlib.f;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class d<T> extends a<T> implements b {
    protected final ArrayList<T> _items = new ArrayList();
    protected long _offset;
    private final HashSet<a> _onClearListeners = new HashSet();
    private final HashSet<b> _onQueryFinishListeners = new HashSet();
    private cn.htjyb.netlib.d _queryTask;
    protected int _total;
    protected boolean mIsQueryMore;
    private boolean queryEmpty;

    protected abstract void fillJSONObjectHeaderInfo(JSONObject jSONObject);

    protected abstract cn.htjyb.netlib.b getHttpEngine();

    protected abstract String getQueryUrl();

    protected abstract T parseItem(JSONObject jSONObject);

    public int itemCount() {
        return this._items.size();
    }

    public T itemAt(int i) {
        if (i < 0 || i >= this._items.size()) {
            return null;
        }
        return this._items.get(i);
    }

    public void replace(int i, T t) {
        if (i >= 0 && i < this._items.size()) {
            this._items.remove(i);
            this._items.add(i, t);
        }
    }

    public void setItems(ArrayList<T> arrayList) {
        this._items.addAll(arrayList);
    }

    public void registerOnQueryFinishListener(b bVar) {
        this._onQueryFinishListeners.add(bVar);
    }

    public void unregisterOnQueryFinishedListener(b bVar) {
        this._onQueryFinishListeners.remove(bVar);
    }

    public void registerOnClearListener(a aVar) {
        this._onClearListeners.add(aVar);
    }

    public void unregisterOnClearListener(a aVar) {
        this._onClearListeners.remove(aVar);
    }

    public void refresh() {
        if (this._queryTask != null && this.mIsQueryMore) {
            cancelQuery();
        }
        if (this._queryTask == null) {
            this._offset = 0;
            this.mIsQueryMore = false;
            doQuery();
        }
    }

    public void queryMore() {
        if (!(this._queryTask == null || this.mIsQueryMore)) {
            cancelQuery();
        }
        if (this._queryTask == null) {
            this._offset = getQueryMoreOffset();
            this.mIsQueryMore = true;
            doQuery();
        }
    }

    protected long getQueryMoreOffset() {
        if (this._offset > 0) {
            return this._offset;
        }
        return (long) this._items.size();
    }

    public void cancelQuery() {
        if (this._queryTask != null) {
            this._queryTask.c();
            this._queryTask = null;
        }
    }

    public boolean hasMore() {
        if (this._offset <= 0 || this._total <= 0) {
            if (this._offset == 0 || this._total == 0) {
                if (this.queryEmpty) {
                    return false;
                }
                return true;
            } else if (this._items.size() >= this._total) {
                return false;
            } else {
                return true;
            }
        } else if (this._offset < ((long) this._total)) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        this._offset = 0;
        this._total = 0;
        this.mIsQueryMore = false;
        this._items.clear();
        cancelQuery();
        notifyListUpdate();
        Iterator it = new ArrayList(this._onClearListeners).iterator();
        while (it.hasNext()) {
            ((a) it.next()).b();
        }
    }

    public ArrayList<T> getItems() {
        return this._items;
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        jSONObject.put("offset", this._offset);
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        if (0 == this._offset) {
            if (this._items.isEmpty()) {
                this._items.clear();
            } else {
                this._items.clear();
            }
        }
        this._total = jSONObject.optInt("total");
        this._offset = (long) jSONObject.optInt("offset");
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    Object parseItem = parseItem(optJSONObject);
                    if (parseItem != null) {
                        this._items.add(parseItem);
                    }
                }
            }
        }
        if (optJSONArray == null || optJSONArray.length() == 0) {
            this.queryEmpty = true;
        } else {
            this.queryEmpty = false;
        }
        notifyListUpdate();
    }

    private void doQuery() {
        JSONObject jSONObject = new JSONObject();
        try {
            fillQueryBody(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fillJSONObjectHeaderInfo(jSONObject);
        this._queryTask = new f(getQueryUrl(), getHttpEngine(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                this.a._queryTask = null;
                if (dVar.c.a) {
                    this.a.handleQuerySuccResult(dVar.c.c);
                }
                this.a.notifyQueryFinish(dVar.c.a, dVar.c.c());
            }
        });
        this._queryTask.b();
    }

    protected boolean isQueryMore() {
        return this.mIsQueryMore;
    }

    protected void notifyQueryFinish(boolean z, String str) {
        ArrayList arrayList = new ArrayList(this._onQueryFinishListeners);
        boolean z2 = !this.mIsQueryMore;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(z, z2, str);
        }
    }
}
