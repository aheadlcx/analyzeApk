package qsbk.app.model;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import qsbk.app.core.utils.NetworkUtils;

public class LivePackage extends QbBean {
    public ArrayList<Live> lives = new ArrayList();
    public int pos;

    public static Pair<LivePackage, LivePackage> getLiveRecommendPackages(LiveRecommend liveRecommend, boolean z) {
        if (liveRecommend == null || liveRecommend.lives.size() == 0) {
            return null;
        }
        return getLiveRecommendPackages(liveRecommend.lives, z);
    }

    public static Pair<LivePackage, LivePackage> getLiveRecommendPackages(ArrayList<Live> arrayList, boolean z) {
        if (arrayList == null || arrayList.size() < 2) {
            return null;
        }
        LivePackage livePackage = new LivePackage();
        LivePackage livePackage2 = new LivePackage();
        if (!z) {
            if (arrayList.size() >= 2 && arrayList.size() <= 10) {
                livePackage.lives = shuffle(arrayList);
            } else if (arrayList.size() > 10) {
                livePackage.lives = shuffle(subList(arrayList, 0, 10));
            }
            return new Pair(livePackage, null);
        } else if (!NetworkUtils.getInstance().isWifiAvailable()) {
            if (arrayList.size() >= 2 && arrayList.size() <= 10) {
                livePackage.lives = shuffle(arrayList);
            } else if (arrayList.size() > 10) {
                livePackage.lives = shuffle(subList(arrayList, 0, 10));
            }
            return new Pair(livePackage, null);
        } else if (arrayList.size() >= 2 && arrayList.size() <= 5) {
            Collections.shuffle(arrayList);
            livePackage.lives = arrayList;
            return new Pair(livePackage, null);
        } else if (arrayList.size() > 5 && arrayList.size() < 10) {
            r0 = subList(arrayList, 0, 5);
            r1 = subList(arrayList, 5, arrayList.size());
            livePackage.lives = shuffle(r0);
            livePackage2.lives = shuffle(r1);
            return new Pair(livePackage, livePackage2);
        } else if (arrayList.size() < 10) {
            return null;
        } else {
            r0 = subList(arrayList, 0, 5);
            r1 = subList(arrayList, 5, 10);
            livePackage.lives = shuffle(r0);
            livePackage2.lives = shuffle(r1);
            return new Pair(livePackage, livePackage2);
        }
    }

    public static ArrayList<Live> subList(ArrayList<Live> arrayList, int i, int i2) {
        if (arrayList == null || arrayList.size() == 0 || i < 0 || i2 > arrayList.size() || i > i2) {
            return null;
        }
        ArrayList<Live> arrayList2 = new ArrayList();
        while (i < i2) {
            arrayList2.add(arrayList.get(i));
            i++;
        }
        return arrayList2;
    }

    public static ArrayList<Live> shuffle(ArrayList<Live> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        Collections.shuffle(arrayList);
        return arrayList;
    }
}
