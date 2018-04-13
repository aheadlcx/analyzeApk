package qsbk.app.im.CollectEmotion;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.im.datastore.LatestUsedCollectionStore;

public class CollectionManager {
    private static final String a = CollectionManager.class.getSimpleName();
    private static final List<LatestUsedCollectionData> b = new ArrayList();
    private static final List<LatestUsedCollectionData> c = new ArrayList();
    private static CollectionManager d = null;
    private static byte[] e = new byte[0];
    private boolean f = false;
    private boolean g = false;
    private String h = null;

    private CollectionManager(String str) {
        this.h = str;
        getAll();
    }

    public static final CollectionManager getInstance(String str) {
        if (d != null && TextUtils.equals(d.h, str)) {
            return d;
        }
        d = new CollectionManager(str);
        return d;
    }

    public void getLocalLatestUsedCollectionData() {
        c.clear();
        b.clear();
        List all = LatestUsedCollectionStore.getCollectionStore(this.h).getAll();
        LatestUsedCollectionData latestUsedCollectionData = new LatestUsedCollectionData();
        latestUsedCollectionData.type = 4;
        b.add(0, latestUsedCollectionData);
        b.addAll((ArrayList) all);
        c.addAll((ArrayList) all);
    }

    public ArrayList<LatestUsedCollectionData> getAll() {
        getLocalLatestUsedCollectionData();
        return (ArrayList) b;
    }

    public ArrayList<LatestUsedCollectionData> feachAll() {
        return (ArrayList) b;
    }

    public ArrayList<LatestUsedCollectionData> feachAllData() {
        return (ArrayList) c;
    }

    public void init(Context context) {
    }

    public int check(LatestUsedCollectionData latestUsedCollectionData) {
        for (LatestUsedCollectionData latestUsedCollectionData2 : LatestUsedCollectionStore.getCollectionStore(this.h).getAll()) {
            if (latestUsedCollectionData2.type == 2 && latestUsedCollectionData.type == 2 && latestUsedCollectionData.chatMsgEmotionData.equals(latestUsedCollectionData2.chatMsgEmotionData)) {
                return latestUsedCollectionData2.id;
            }
        }
        return 0;
    }

    public int check(String str) {
        for (LatestUsedCollectionData latestUsedCollectionData : LatestUsedCollectionStore.getCollectionStore(this.h).getAll()) {
            if (latestUsedCollectionData.type == 1) {
                if (!TextUtils.isEmpty(latestUsedCollectionData.collectImageDomain.netUrl) && latestUsedCollectionData.collectImageDomain.netUrl.equals(str)) {
                    return latestUsedCollectionData.id;
                }
            } else if (latestUsedCollectionData.type == 3 && !TextUtils.isEmpty(latestUsedCollectionData.collectImageLocal.localUrl) && latestUsedCollectionData.collectImageLocal.localUrl.equals(str)) {
                return latestUsedCollectionData.id;
            }
        }
        return 0;
    }
}
