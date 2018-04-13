package qsbk.app.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.LimitFIFOQueue;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.utils.image.issue.TaskExecutor;

public class CircleTopicManager {
    public static final int LRU = 2;
    public static final int MAX_LRU_TOP_COUNT = 6;
    public static final int RECOMMEND = 3;
    private static volatile CircleTopicManager a;
    public static ArrayList<CircleTopic> artificialTopics;
    public static ArrayList<CircleTopic> automaticTopics;
    private static ArrayList<OnTopicUpdate> b;
    public static List<CircleTopicBanner> banners = new ArrayList();
    public static boolean isShowTopic = true;
    public static int[] topicSpace = new int[0];
    private boolean c = false;
    public LimitFIFOQueue<CircleTopic> lruTopics = new LimitFIFOQueue(6);

    public interface OnTopicUpdate {
        void onTopicUpdate(Collection<CircleTopic> collection);
    }

    public interface CallBack {
        void onFailure(int i, String str);

        void onSuccess(Collection<CircleTopic> collection);
    }

    private CircleTopicManager() {
    }

    public static CircleTopicManager getInstance() {
        if (a == null) {
            synchronized (CircleTopicManager.class) {
                if (a == null) {
                    a = new CircleTopicManager();
                }
            }
        }
        return a;
    }

    public static void sortRecommendToics(Collection<CircleTopic> collection) {
        if (collection.size() != 0) {
            if (artificialTopics != null) {
                artificialTopics.clear();
            } else {
                artificialTopics = new ArrayList();
            }
            if (automaticTopics != null) {
                artificialTopics.clear();
            } else {
                automaticTopics = new ArrayList();
            }
            for (CircleTopic circleTopic : collection) {
                if (circleTopic.recomendCircleArticles == null || circleTopic.recomendCircleArticles.size() <= 0) {
                    artificialTopics.add(circleTopic);
                } else {
                    automaticTopics.add(circleTopic);
                }
            }
        }
    }

    public static void register(OnTopicUpdate onTopicUpdate) {
        if (b == null) {
            b = new ArrayList();
        }
        b.add(onTopicUpdate);
    }

    public static void unregister(OnTopicUpdate onTopicUpdate) {
        if (b != null) {
            b.remove(onTopicUpdate);
            if (b.size() == 0) {
                b = null;
            }
        }
    }

    public static void notifyTopicUpdate(Collection<CircleTopic> collection) {
        if (collection != null) {
            updateTopic(collection);
            if (b != null) {
                Iterator it = b.iterator();
                while (it.hasNext()) {
                    ((OnTopicUpdate) it.next()).onTopicUpdate(collection);
                }
            }
        }
    }

    private static File a(int i) {
        String sDPath = DeviceUtils.getSDPath();
        if (sDPath == null || sDPath.length() == 0) {
            sDPath = QsbkApp.getInstance().getFilesDir().getAbsolutePath();
        }
        String str = QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId;
        File file = new File(sDPath + File.separator + "qsbk/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, Integer.toHexString(("CircleTopic" + i).hashCode()) + "_" + str);
    }

    private static File b() {
        String sDPath = DeviceUtils.getSDPath();
        if (sDPath == null || sDPath.length() == 0) {
            sDPath = QsbkApp.getInstance().getFilesDir().getAbsolutePath();
        }
        String str = QsbkApp.currentUser == null ? "" : QsbkApp.currentUser.userId;
        File file = new File(sDPath + File.separator + "qsbk/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, Integer.toHexString("CircleTopicBanner".hashCode()) + "_" + str);
    }

    private static void b(File file, JSONObject jSONObject) {
        if (file != null) {
            TaskExecutor.getInstance().addTask(new c(jSONObject, file));
        }
    }

    private static void a(int i, JSONObject jSONObject) {
        b(a(i), jSONObject);
    }

    private static JSONObject b(int i) {
        File a = a(i);
        if (a.exists()) {
            return a(a);
        }
        return null;
    }

    private static JSONObject a(File file) {
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    } else {
                        bufferedReader.close();
                        return new JSONObject(stringBuilder.toString());
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<CircleTopic> parseTopics(JSONObject jSONObject) throws JSONException {
        int i = 0;
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        ArrayList<CircleTopic> arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            CircleTopic parseJson = CircleTopic.parseJson(jSONArray.getJSONObject(i2));
            if (parseJson != null) {
                arrayList.add(parseJson);
            }
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("new_data");
        if (optJSONArray != null) {
            while (i < optJSONArray.length()) {
                CircleTopic parseJson2 = CircleTopic.parseJson(optJSONArray.getJSONObject(i));
                if (parseJson2 != null) {
                    arrayList.add(parseJson2);
                }
                i++;
            }
        }
        return arrayList;
    }

    private static JSONObject a(Collection<CircleTopic> collection, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (CircleTopic toJson : collection) {
                jSONArray.put(CircleTopic.toJson(toJson));
            }
            jSONObject.put("data", jSONArray);
            jSONObject.put("endTime", i);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveTopicsToCache(int i, Collection<CircleTopic> collection, int i2) {
        JSONObject a;
        if (collection != null) {
            a = a((Collection) collection, i2);
        } else {
            a = null;
        }
        if (a != null) {
            a(i, a);
        } else {
            a(i, null);
        }
    }

    public static void updateTopic(Collection<CircleTopic> collection) {
        updateTopic(collection, 2);
        updateTopic(collection, 3);
    }

    public static void updateTopic(Collection<CircleTopic> collection, int i) {
        if (collection != null && !collection.isEmpty()) {
            CircleTopic circleTopic;
            if (i == 2) {
                LimitFIFOQueue lruTopics = getInstance().getLruTopics();
                if (lruTopics.size() > 0) {
                    Iterator it = lruTopics.iterator();
                    while (it.hasNext()) {
                        circleTopic = (CircleTopic) it.next();
                        for (CircleTopic circleTopic2 : collection) {
                            if (TextUtils.equals(circleTopic2.id, circleTopic.id)) {
                                circleTopic.updateWith(circleTopic2);
                                break;
                            }
                        }
                    }
                }
            }
            JSONObject b = b(i);
            if (b != null) {
                int optInt = b.optInt("endTime");
                try {
                    Collection parseTopics = parseTopics(b);
                    Object obj = null;
                    Iterator it2 = parseTopics.iterator();
                    while (it2.hasNext()) {
                        Object obj2;
                        circleTopic = (CircleTopic) it2.next();
                        for (CircleTopic circleTopic22 : collection) {
                            if (TextUtils.equals(circleTopic22.id, circleTopic.id)) {
                                circleTopic.updateWith(circleTopic22);
                                obj2 = 1;
                                break;
                            }
                        }
                        obj2 = obj;
                        obj = obj2;
                    }
                    if (obj != null) {
                        saveTopicsToCache(i, parseTopics, optInt);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static CircleTopic getFirstCircleTopic(int i) {
        if (i < 0 || artificialTopics == null || artificialTopics.size() == 0) {
            return null;
        }
        CircleTopic circleTopic = (CircleTopic) artificialTopics.get(new Random().nextInt(artificialTopics.size()));
        circleTopic.type = 1;
        return circleTopic;
    }

    public static void loadBannersFromCache() {
        JSONObject a = a(b());
        if (a != null) {
            JSONArray optJSONArray = a.optJSONArray("banner");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                banners.clear();
                banners.addAll(CircleTopicBanner.parseJsonArray(optJSONArray));
            }
        }
    }

    public static ArrayList<CircleTopic> getTweSecondCircleTopic(int i) {
        if (i < 0) {
            return new ArrayList(0);
        }
        if (automaticTopics == null) {
            return new ArrayList(0);
        }
        if (automaticTopics.size() == 0) {
            return new ArrayList(0);
        }
        ArrayList<CircleTopic> arrayList = new ArrayList();
        CircleTopic circleTopic;
        if (automaticTopics.size() == 1) {
            circleTopic = (CircleTopic) automaticTopics.get(0);
            circleTopic.type = 2;
            arrayList.add(circleTopic);
            circleTopic = (CircleTopic) automaticTopics.get(0);
            if (circleTopic.recomendCircleArticles.size() > 2) {
                circleTopic.type = 3;
            } else {
                circleTopic.type = 2;
            }
            arrayList.add(circleTopic);
            return arrayList;
        }
        int nextInt = new Random().nextInt(automaticTopics.size());
        int nextInt2 = new Random().nextInt(automaticTopics.size());
        while (nextInt == nextInt2) {
            nextInt2 = new Random().nextInt(automaticTopics.size());
        }
        circleTopic = (CircleTopic) automaticTopics.get(nextInt);
        circleTopic.type = 2;
        CircleTopic circleTopic2 = (CircleTopic) automaticTopics.get(nextInt2);
        if (circleTopic2.recomendCircleArticles.size() > 2) {
            circleTopic2.type = 3;
        } else {
            circleTopic2.type = 1;
        }
        arrayList.add(circleTopic);
        arrayList.add(circleTopic2);
        return arrayList;
    }

    public static CircleTopic getOneSecondCircleTopic(int i) {
        CircleTopic circleTopic = null;
        int i2 = 2;
        if (!(i < 0 || automaticTopics == null || automaticTopics.size() == 0)) {
            circleTopic = (CircleTopic) automaticTopics.get(new Random().nextInt(automaticTopics.size()));
            if (circleTopic.recomendCircleArticles.size() > 2) {
                if (new Random().nextInt(2) <= 0) {
                    i2 = 3;
                }
                circleTopic.type = i2;
            } else {
                circleTopic.type = 2;
            }
        }
        return circleTopic;
    }

    public static CircleTopicBanner getRandomBanner() {
        if (banners == null || banners.size() <= 0) {
            return null;
        }
        return (CircleTopicBanner) banners.get(new Random().nextInt(banners.size()));
    }

    public static ArrayList<CircleTopic> getCircleTopic(int i, int i2) {
        if (i < 0 || i2 <= 0) {
            LogUtil.e("获取CircleTopic不符合要求");
            return null;
        }
        if (i2 >= 3) {
            i2 = 3;
        }
        ArrayList<CircleTopic> arrayList = new ArrayList();
        if (artificialTopics != null) {
            if (i == 0) {
                if (i2 == 1) {
                    arrayList.add(getFirstCircleTopic(i));
                } else if (i2 == 2) {
                    arrayList.add(getFirstCircleTopic(i));
                    arrayList.add(getOneSecondCircleTopic(i));
                } else {
                    arrayList.add(getFirstCircleTopic(i));
                    arrayList.addAll(getTweSecondCircleTopic(i));
                }
            } else if (i2 == 1) {
                int nextInt = new Random().nextInt(3);
                if (nextInt == 0) {
                    arrayList.add(getFirstCircleTopic(i));
                } else if (nextInt >= 1) {
                    arrayList.add(getOneSecondCircleTopic(i));
                }
            } else if (i2 != 2) {
                arrayList.add(getFirstCircleTopic(i));
                arrayList.addAll(getTweSecondCircleTopic(i));
            } else if (new Random().nextInt(2) == 0) {
                arrayList.add(getFirstCircleTopic(i));
                arrayList.add(getOneSecondCircleTopic(i));
            } else {
                arrayList.addAll(getTweSecondCircleTopic(i));
            }
        }
        arrayList.removeAll(Collections.singleton(null));
        return arrayList;
    }

    public ArrayList<CircleTopic> loadTopicsFromCache(int i) {
        Object obj = i == 3 ? 1 : null;
        JSONObject b = b(i);
        if (b != null) {
            if (obj != null && ((long) b.optInt("endTime")) * 1000 < System.currentTimeMillis()) {
                return new ArrayList();
            }
            try {
                ArrayList<CircleTopic> parseTopics = parseTopics(b);
                if (parseTopics.size() > 0) {
                    return parseTopics;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void insertTopicToLRU(CircleTopic circleTopic) {
        if (circleTopic != null && !TextUtils.equals(CircleTopic.BLACK_ROOM_ID, circleTopic.id)) {
            this.lruTopics.add(circleTopic);
            saveTopicsToCache(2, this.lruTopics, Integer.MAX_VALUE);
        }
    }

    public void loadRecommendTopicsFromServer(CallBack callBack) {
        if (!this.c) {
            this.c = true;
            LogUtil.e("loadRecommendTopicsfromserver");
            new SimpleHttpTask(Constants.CIRCLE_TOPIC_RECOMMEND, new d(this, callBack)).execute();
        }
    }

    public void loadRecommendTopics(CallBack callBack) {
        loadBannersFromCache();
        Collection loadTopicsFromCache = loadTopicsFromCache(3);
        if (loadTopicsFromCache == null || loadTopicsFromCache.size() == 0 || banners == null || banners.size() <= 0) {
            loadRecommendTopicsFromServer(callBack);
        } else {
            callBack.onSuccess(loadTopicsFromCache);
        }
    }

    public void loadQiushiListRecommendTopics(CallBack callBack) {
        Collection loadTopicsFromCache = loadTopicsFromCache(3);
        if (loadTopicsFromCache == null || loadTopicsFromCache.size() == 0) {
            loadRecommendTopicsFromServer(callBack);
        } else {
            callBack.onSuccess(loadTopicsFromCache);
        }
    }

    public void loadLRUTopics(CallBack callBack) {
        new e(this, callBack).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void loadLRUTopicsWithUpdate(ArrayList<CircleTopic> arrayList, CallBack callBack) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        int i = 1;
        while (it.hasNext()) {
            CircleTopic circleTopic = (CircleTopic) it.next();
            if (i != 0) {
                i = 0;
            } else {
                stringBuilder.append(',');
            }
            stringBuilder.append(circleTopic.id);
        }
        new SimpleHttpTask(String.format(Constants.CIRCLE_TOPIC_PRODUCTION, new Object[]{stringBuilder.toString()}), new f(this, callBack)).execute();
    }

    public void clearLruTopics() {
        this.lruTopics.clear();
        a(2, null);
    }

    public LimitFIFOQueue<CircleTopic> getLruTopics() {
        return this.lruTopics;
    }
}
