package qsbk.app.im;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.model.relationship.Relationship;

public class RelationshipCacheMgr {
    private static RelationshipCacheMgr a;
    private static String b;
    private Map<String, Relationship> c = new HashMap();

    private RelationshipCacheMgr(String str) {
        b = str;
    }

    public static synchronized RelationshipCacheMgr getInstance(String str) {
        RelationshipCacheMgr relationshipCacheMgr;
        synchronized (RelationshipCacheMgr.class) {
            if (a == null || !TextUtils.equals(str, b)) {
                if (a != null) {
                    a.onDestroy();
                }
                a = new RelationshipCacheMgr(str);
            }
            relationshipCacheMgr = a;
        }
        return relationshipCacheMgr;
    }

    public void onDestroy() {
        this.c.clear();
    }

    public Relationship getRelationship(String str) {
        return (Relationship) this.c.get(str);
    }

    public Relationship putRelationship(String str, Relationship relationship) {
        if (relationship == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return (Relationship) this.c.put(str, relationship);
    }
}
